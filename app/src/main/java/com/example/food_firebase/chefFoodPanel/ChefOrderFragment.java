package com.example.food_firebase.chefFoodPanel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_firebase.chefAdapter.ChefStatisticsAdapter;
import com.example.food_firebase.R;
import com.example.food_firebase.model.Receipt;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChefOrderFragment extends Fragment {
    RecyclerView recyclerView;
    private List<Receipt> receiptList;
    private ChefStatisticsAdapter chefStatisticsAdapter;
    TextView Revenue;
    int total = 0;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_chef_orders,null);
        getActivity().setTitle("New Orders");
        recyclerView = v.findViewById(R.id.statistics);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        receiptList = new ArrayList<>();
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Revenue = v.findViewById(R.id.Revenue);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("statistics");
        Query query = myRef.orderByChild("id0fRecript");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                receiptList.clear();
                for( DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Receipt receipt = dataSnapshot.getValue(Receipt.class);
                    receiptList.add(0,receipt);

                }
                for (int i = 0; i < receiptList.size(); i++){
                    total += Integer.parseInt(receiptList.get(i).getTotal());
                }
                chefStatisticsAdapter = new ChefStatisticsAdapter(getContext(),receiptList);
                recyclerView.setAdapter(chefStatisticsAdapter);
                Revenue.setText("Doanh thu: $"+total);


            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
        return v;
    }
}