package com.example.food_firebase.chefFoodPanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.food_firebase.chefAdapter.ChefHomeAdapter;
import com.example.food_firebase.start.MainMenu;
import com.example.food_firebase.R;
import com.example.food_firebase.model.UpdateDishModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChefHomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView recyclerView;
    private List<UpdateDishModel> updateDishModelList;
    private ChefHomeAdapter adapter;
    String State, City, Sub;
    FirebaseDatabase database;
    DatabaseReference myRef;
    SwipeRefreshLayout swipeRefreshLayout;
    SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_chef_home, null);
        getActivity().setTitle("Home");
        setHasOptionsMenu(true);
        recyclerView = v.findViewById(R.id.recycle_menu_chef);
        recyclerView.setHasFixedSize(true);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.move);
        recyclerView.startAnimation(animation);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        updateDishModelList = new ArrayList<>();
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipelayout_chef);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.green);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                chefmenu();

            }
        });
        return v;
    }

    @Override
    public void onRefresh() {
        chefmenu();
    }

    private void chefmenu() {

        swipeRefreshLayout.setRefreshing(true);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("FoodDetails");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                updateDishModelList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UpdateDishModel updateDishModel = postSnapshot.getValue(UpdateDishModel.class);
                    updateDishModelList.add(updateDishModel);


                }
                adapter = new ChefHomeAdapter(getContext(), updateDishModelList);
                recyclerView.setAdapter(adapter);

                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
//                swipeRefreshLayout.setRefreshing(false);

            }
        });

    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.logout,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int idd = item.getItemId();
        if(idd == R.id.LOGOUT){
            Logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Logout() {

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity(), MainMenu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}

