package com.example.food_firebase.start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.food_firebase.R;
import com.example.food_firebase.customerFoodPanel.CustomerCartFragmnet;
import com.example.food_firebase.customerFoodPanel.CustomerHomeFragment;
import com.example.food_firebase.customerFoodPanel.CustomerOrdersFragment;
import com.example.food_firebase.customerFoodPanel.CustomerProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class CustomerFoofPanel_BottomNavigation extends AppCompatActivity {
    String RandomId,UserId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_foof_panel_bottom_navigation);
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {

                    case R.id.cust_Home:
                        fragment = new CustomerHomeFragment();
                        break;
                    case R.id.cart:
                        fragment = new CustomerCartFragmnet();
                        break;
                    case R.id.Cust_order:
                        fragment = new CustomerOrdersFragment();
                        break;
                    case R.id.cust_profile:
                        fragment = new CustomerProfileFragment();
                        break;
                }
                return loadcheffragment(fragment);
            }
        });
    }



    //    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        Fragment fragment = null;
//        switch (item.getItemId()){
//            case R.id.cust_Home:
//                fragment=new CustomerHomeFragment();
//                break;
//        }
//        switch (item.getItemId()){
//            case R.id.cart:
//                fragment=new CustomerCartFragmnet();
//                break;
//        }
//        switch (item.getItemId()){
//            case R.id.cust_profile:
//                fragment=new CustomerProfileFragment();
//                break;
//        }
//        switch (item.getItemId()){
//            case R.id.Cust_order:
//                fragment=new CustomerOrdersFragment();
//                break;
//        }
//        switch (item.getItemId()){
//            case R.id.cart:
//                fragment=new CustomerCartFragmnet();
//                break;
//        }
//        return loadcheffragment(fragment);
//
//    }

    private boolean loadcheffragment(Fragment fragment) {

        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
        return false;
    }
}