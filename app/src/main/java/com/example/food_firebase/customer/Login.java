package com.example.food_firebase.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food_firebase.start.CustomerFoofPanel_BottomNavigation;
import com.example.food_firebase.R;
import com.example.food_firebase.start.ReusableCodeForAll;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    TextInputLayout email,pass;
    Button Signin;
    TextView Forgotpassword , signup;
    FirebaseAuth Fauth;
    String emailid,pwd,emxm,pwxm;
    FirebaseDatabase  database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try{

            email = (TextInputLayout)findViewById(R.id.Lemail);
            pass = (TextInputLayout)findViewById(R.id.Lpassword);
            Signin = (Button)findViewById(R.id.button4);
            signup = (TextView) findViewById(R.id.textView4);
            Forgotpassword = (TextView)findViewById(R.id.forgotpass);

            Fauth = FirebaseAuth.getInstance();

            Signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    emailid = email.getEditText().getText().toString().trim();
                    pwd = pass.getEditText().getText().toString().trim();

                    if(isValid()){

                        final ProgressDialog mDialog = new ProgressDialog(Login.this);
                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.setCancelable(false);
                        mDialog.setMessage("Sign In Please Wait.......");
                        mDialog.show();
//                        database = FirebaseDatabase.getInstance();
//                        myRef = database.getReference("Customer");
//                        myRef.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                Customer customer = snapshot.getValue(Customer.class);
//                                emxm = customer.getEmailId();
//                                pwxm = customer.getPassword();
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });
//                        Log.e("pass",emxm+"+"+pwxm);
//                        if((emailid == emxm) && (pwd == pwxm)){
//                            Fauth.signInWithEmailAndPassword(emailid,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//
//                                    if(task.isSuccessful()){
//                                        mDialog.dismiss();
//
//                                        if(Fauth.getCurrentUser().isEmailVerified()){
//                                            mDialog.dismiss();
//                                            Toast.makeText(Login.this, "Congratulation! You Have Successfully Login", Toast.LENGTH_SHORT).show();
//                                            Intent Z = new Intent(Login.this,CustomerFoofPanel_BottomNavigation.class);
//                                            startActivity(Z);
//                                            finish();
//
//                                        }else{
//                                            ReusableCodeForAll.ShowAlert(Login.this,"Verification Failed","You Have Not Verified Your Email");
//
//                                        }
//                                    }else{
//                                        mDialog.dismiss();
//                                        ReusableCodeForAll.ShowAlert(Login.this,"Error",task.getException().getMessage());
//                                    }
//                                }
//                            });
//
//
//                        }
//                        else {
//                            ReusableCodeForAll.ShowAlert(Login.this,"Error","Thong tin tai khoan khong chinh xac");
//                            mDialog.dismiss();
//                        }


                        Fauth.signInWithEmailAndPassword(emailid,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    mDialog.dismiss();

                                    if(Fauth.getCurrentUser().isEmailVerified()){
                                        mDialog.dismiss();
                                        Toast.makeText(Login.this, "Congratulation! You Have Successfully Login", Toast.LENGTH_SHORT).show();
                                        Intent Z = new Intent(Login.this, CustomerFoofPanel_BottomNavigation.class);
                                        startActivity(Z);
                                        finish();

                                    }else{
                                        ReusableCodeForAll.ShowAlert(Login.this,"Verification Failed","You Have Not Verified Your Email");

                                    }
                                }else{
                                    mDialog.dismiss();
                                    ReusableCodeForAll.ShowAlert(Login.this,"Error",task.getException().getMessage());
                                }
                            }
                        });
                    }
                }
            });
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Login.this, Registration.class));
                    finish();
                }
            });
            Forgotpassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Login.this, ForgotPassword.class));
                    finish();
                }
            });
//            Signinphone.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startActivity(new Intent(Login.this,Loginphone.class));
//                    finish();
//                }
//            });
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
    String emailpattern  = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public boolean isValid(){

        email.setErrorEnabled(false);
        email.setError("");
        pass.setErrorEnabled(false);
        pass.setError("");

        boolean isvalid=false,isvalidemail=false,isvalidpassword=false;
        if(TextUtils.isEmpty(emailid)){
            email.setErrorEnabled(true);
            email.setError("Email is required");
        }else{
            if(emailid.matches(emailpattern)){
                isvalidemail=true;
            }else{
                email.setErrorEnabled(true);
                email.setError("Invalid Email Address");
            }
        }
        if(TextUtils.isEmpty(pwd)){

            pass.setErrorEnabled(true);
            pass.setError("Password is Required");
        }else{
            isvalidpassword=true;
        }
        isvalid=(isvalidemail && isvalidpassword)?true:false;
        return isvalid;
    }
}