package com.example.food_firebase.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.example.food_firebase.R;
import com.example.food_firebase.start.ReusableCodeForAll;
import com.example.food_firebase.model.Client;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registration extends AppCompatActivity {

        TextInputLayout Fname,Lname,Email,Pass,cpass,localaddress,area;
        Spinner Cityspin;
        Button signup, Emaill;
        FirebaseAuth FAuth;
        DatabaseReference databaseReference;
        FirebaseDatabase firebaseDatabase;
        String fname,lname,emailid,password,confpassword,Localaddress,Area,statee,cityy;
        String role="Customer";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registration);

            Fname = (TextInputLayout)findViewById(R.id.Fname);
            Lname = (TextInputLayout)findViewById(R.id.Lname);
            Email = (TextInputLayout)findViewById(R.id.Emailid);
            Pass = (TextInputLayout)findViewById(R.id.Password);
            cpass = (TextInputLayout)findViewById(R.id.confirmpass);
            localaddress = (TextInputLayout)findViewById(R.id.Localaddress);
            signup = (Button)findViewById(R.id.button);
            Emaill = (Button)findViewById(R.id.emailcustom);

            databaseReference = firebaseDatabase.getInstance().getReference("Customer");
            FAuth = FirebaseAuth.getInstance();

            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    fname = Fname.getEditText().getText().toString().trim();
                    lname = Lname.getEditText().getText().toString().trim();
                    emailid = Email.getEditText().getText().toString().trim();
                    password = Pass.getEditText().getText().toString().trim();
                    confpassword = cpass.getEditText().getText().toString().trim();
                    Localaddress = localaddress.getEditText().getText().toString().trim();

                    if (isValid()){
                        final ProgressDialog mDialog = new ProgressDialog(Registration.this);
                        mDialog.setCancelable(false);
                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.setMessage("Registration in progress please wait......");
                        mDialog.show();

                        FAuth.createUserWithEmailAndPassword(emailid,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){
                                    String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    databaseReference = FirebaseDatabase.getInstance().getReference("User").child(useridd);
                                    final HashMap<String , String> hashMap = new HashMap<>();
                                    hashMap.put("Role",role);
                                    databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Client client = new Client(useridd,fname,lname,emailid,password,confpassword,Localaddress);

                                            firebaseDatabase.getInstance().getReference("Customer")
                                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .setValue(client).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    mDialog.dismiss();

                                                    FAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {

                                                            if(task.isSuccessful()){
                                                                AlertDialog.Builder builder = new AlertDialog.Builder(Registration.this);
                                                                builder.setMessage("You Have Registered! Make Sure To Verify Your Email");
                                                                builder.setCancelable(false);
                                                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {

                                                                        dialog.dismiss();

                                                                    }
                                                                });
                                                                AlertDialog Alert = builder.create();
                                                                Alert.show();
                                                            }else{
                                                                mDialog.dismiss();
                                                                ReusableCodeForAll.ShowAlert(Registration.this,"Error",task.getException().getMessage());
                                                            }
                                                        }
                                                    });

                                                }
                                            });

                                        }
                                    });
                                }else {
                                    mDialog.dismiss();
                                    ReusableCodeForAll.ShowAlert(Registration.this, "Error", task.getException().getMessage());
                                }
                            }
                        });
                    }

                }
            });

            Emaill.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Registration.this, Login.class));
                    finish();
                }
            });


        }


        String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        public boolean isValid(){
            Email.setErrorEnabled(false);
            Email.setError("");
            Fname.setErrorEnabled(false);
            Fname.setError("");
            Lname.setErrorEnabled(false);
            Lname.setError("");
            Pass.setErrorEnabled(false);
            Pass.setError("");
            cpass.setErrorEnabled(false);
            cpass.setError("");
            localaddress.setErrorEnabled(false);
            localaddress.setError("");

            boolean isValid=false,isValidlocaladd=false,isValidlname=false,isValidname=false,isValidemail=false,isValidpassword=false,isValidconfpassword=false;
            if(TextUtils.isEmpty(fname)){
                Fname.setErrorEnabled(true);
                Fname.setError("Enter First Name");
            }else{
                isValidname = true;
            }
            if(TextUtils.isEmpty(lname)){
                Lname.setErrorEnabled(true);
                Lname.setError("Enter Last Name");
            }else{
                isValidlname = true;
            }
            if(TextUtils.isEmpty(emailid)){
                Email.setErrorEnabled(true);
                Email.setError("Email Is Required");
            }else{
                if(emailid.matches(emailpattern)){
                    isValidemail = true;
                }else{
                    Email.setErrorEnabled(true);
                    Email.setError("Enter a Valid Email Id");
                }
            }
            if(TextUtils.isEmpty(password)){
                Pass.setErrorEnabled(true);
                Pass.setError("Enter Password");
            }else{
                if(password.length()<8){
                    Pass.setErrorEnabled(true);
                    Pass.setError("Password is Weak");
                }else{
                    isValidpassword = true;
                }
            }
            if(TextUtils.isEmpty(confpassword)){
                cpass.setErrorEnabled(true);
                cpass.setError("Enter Password Again");
            }else{
                if(!password.equals(confpassword)){
                    cpass.setErrorEnabled(true);
                    cpass.setError("Password Dosen't Match");
                }else{
                    isValidconfpassword = true;
                }
            }

            if(TextUtils.isEmpty(Localaddress)){
                localaddress.setErrorEnabled(true);
                localaddress.setError("Fields Can't Be Empty");
            }else{
                isValidlocaladd = true;
            }

            isValid = (  isValidconfpassword && isValidpassword  && isValidemail && isValidname && isValidlocaladd && isValidlname) ? true : false;
            return isValid;


        }
}