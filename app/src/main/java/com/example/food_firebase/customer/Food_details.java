package com.example.food_firebase.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.food_firebase.R;
import com.example.food_firebase.model.Cart;
import com.example.food_firebase.model.FoodDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class Food_details extends AppCompatActivity{
    ImageView imageView;
    TextView ChefName, ChefLocation, FoodQuantity, FoodPrice, FoodDescription,Foodname;
    ElegantNumberButton elegantNumberButton;
    FirebaseDatabase  database;
    DatabaseReference myRef;
    String RandomId, ChefID;
    Button btnAddCart;
    String giasp,Tenchitiet,Hinhanhchitiet;
    FirebaseDatabase firebaseDatabase;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lines_food);
        Foodname = findViewById(R.id.food_name);
        imageView = findViewById(R.id.image);
        FoodPrice = findViewById(R.id.food_price);
        btnAddCart = findViewById(R.id.btnaddcart);
        FoodDescription = findViewById(R.id.food_description);
        elegantNumberButton = findViewById(R.id.number_btn);
        database = FirebaseDatabase.getInstance();
        RandomId = getIntent().getStringExtra("FoodMenu");
        myRef = database.getReference("FoodDetails").child(RandomId);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    FoodDetails foodDetails = snapshot.getValue(FoodDetails.class);
                    Glide.with(Food_details.this).load(foodDetails.getImageURL()).into(imageView);
                    Foodname.setText(""+foodDetails.getDishes());
                    FoodPrice.setText("Price: $ " +foodDetails.getPrice());
                    FoodDescription.setText("" + foodDetails.getDescription());
                    giasp = foodDetails.getPrice();
                    Tenchitiet = foodDetails.getDishes();
                    Hinhanhchitiet = foodDetails.getImageURL();
                    ChefID = foodDetails.getChefId();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int sl = Integer.parseInt(elegantNumberButton.getNumber());
                if(sl == 0){
                    Toast.makeText(Food_details.this,"Vui lòng nhập số lượng sản phẩm",Toast.LENGTH_SHORT).show();
                }
                else {

                    long Giamoi = sl * Integer.parseInt(giasp);
                    String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    String idCart = UUID.randomUUID().toString();
                    ArrayList<Cart>arrayList = new ArrayList<>();
                    Cart cart = new Cart(idCart,ChefID,Tenchitiet,String.valueOf(sl),giasp,String.valueOf(Giamoi),RandomId);
                    firebaseDatabase.getInstance().getReference("Cart").child(userid).child(idCart)
                            .setValue(cart).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(Food_details.this,"Cart Posted Successfully!",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}
