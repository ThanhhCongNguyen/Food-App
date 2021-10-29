package com.example.food_firebase.start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.food_firebase.R;
import com.example.food_firebase.chefFoodPanel.ChefHomeFragment;
import com.example.food_firebase.chefFoodPanel.ChefOrderFragment;
import com.example.food_firebase.chefFoodPanel.ChefProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ChefFoodPanel_BottomNavigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_food_panel_bottom_navigation);
        BottomNavigationView navigationView = findViewById(R.id.chef_bottom_navigation);

//        navigationView.setOnNavigationItemReselectedListener(this);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.chefHome:
                        fragment=new ChefHomeFragment();
                        break;
                    case R.id.Orders:
                        fragment=new ChefOrderFragment();
                        break;
                    case R.id.chefProfile:
                        fragment=new ChefProfileFragment();
                        break;
                }
                return loadcheffragment(fragment);
            }
        });
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        Fragment fragment = null;
//        switch (item.getItemId()){
//            case R.id.chefHome:
//                fragment=new ChefHomeFragment();
//                break;
//            case R.id.PendingOrders:
//                fragment=new ChefPendingOrderFragment();
//                break;
//            case R.id.Orders:
//                fragment=new ChefOrderFragment();
//                break;
//            case R.id.chefProfile:
//                fragment=new ChefProfileFragment();
//                break;
//        }
//        return loadcheffragment(fragment);
//    }

    private boolean loadcheffragment(Fragment fragment) {

        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();
            return true;
        }
        return false;
    }
}