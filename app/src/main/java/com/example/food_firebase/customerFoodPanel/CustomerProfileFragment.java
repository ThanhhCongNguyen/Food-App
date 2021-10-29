package com.example.food_firebase.customerFoodPanel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.food_firebase.model.Client;
import com.example.food_firebase.start.MainMenu;
import com.example.food_firebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerProfileFragment extends Fragment {


    TextView firstname, lastname, address;
    TextView Email,LogOut;
    FirebaseDatabase  database;
    DatabaseReference myRef;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Profile");
        View v = inflater.inflate(R.layout.fragment_customerprofile, null);

        firstname = (TextView) v.findViewById(R.id.first_name);
        lastname = (TextView) v.findViewById(R.id.last_name);
        address = (TextView) v.findViewById(R.id.Address);
        Email = (TextView) v.findViewById(R.id.emaillay);
        LogOut = v.findViewById(R.id.logoutt);

        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Customer").child(userid);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Client client = dataSnapshot.getValue(Client.class);
                firstname.setText(client.getFname());
                lastname.setText(client.getLname());
                address.setText(client.getAddress());
                Email.setText(client.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        updateinformation();
        return v;
    }

    private void updateinformation() {


//        Update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
//                data = FirebaseDatabase.getInstance().getReference("Customer");
//                data.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        Customer customer = dataSnapshot.getValue(Customer.class);
//
//
//                        confirmpass = customer.getConfirmPassword();
//                        email = customer.getEmailId();
//                        passwordd = customer.getPassword();
//
//                        String Fname = firstname.getText().toString().trim();
//                        String Lname = lastname.getText().toString().trim();
//                        String Address = address.getText().toString().trim();
//
//                        HashMap<String, String> hashMappp = new HashMap<>();
//                        hashMappp.put("City", cityy);
//                        hashMappp.put("ConfirmPassword", confirmpass);
//                        hashMappp.put("EmailID", email);
//                        hashMappp.put("FirstName", Fname);
//                        hashMappp.put("LastName", Lname);
//                        hashMappp.put("Password", passwordd);
//                        hashMappp.put("LocalAddress", Address);
//                        hashMappp.put("State", statee);
//                        hashMappp.put("Suburban", suburban);
//                        firebaseDatabase.getInstance().getReference("Customer").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(hashMappp);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//
//
//            }
//        });

//        password.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getActivity(), CustomerPassword.class);
//                startActivity(intent);
//            }
//        });
//
//        mobileno.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent i = new Intent(getActivity(), CustomerPhonenumber.class);
//                startActivity(i);
//            }
//        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure you want to Logout ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(getActivity(), MainMenu.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);


                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

    }

    private int getIndexByString(Spinner st, String spist) {
        int index = 0;
        for (int i = 0; i < st.getCount(); i++) {
            if (st.getItemAtPosition(i).toString().equalsIgnoreCase(spist)) {
                index = i;
                break;
            }
        }
        return index;
    }
}

