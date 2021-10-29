package com.example.food_firebase.customerAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food_firebase.customer.Food_details;
import com.example.food_firebase.R;
import com.example.food_firebase.model.FoodDetails;
import com.google.firebase.database.DatabaseReference;

import java.util.List;
import java.util.UUID;

public class CustomerHomeAdapter extends RecyclerView.Adapter<CustomerHomeAdapter.ViewHolder> {

    private Context mcontext;
    private List<FoodDetails> foodDetailsList;
    DatabaseReference databaseReference;

    public CustomerHomeAdapter(Context context , List<FoodDetails> foodDetails){
        this.foodDetailsList = foodDetails;
        this.mcontext = context;
    }


    @NonNull
    @Override
    public CustomerHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.customer_menudish,parent,false);
        return new CustomerHomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodDetails foodDetails = foodDetailsList.get(position);
        Glide.with(mcontext).load(foodDetails.getImageURL()).into(holder.imageView);
        holder.Dishname.setText(foodDetails.getDishes());
        foodDetails.getRandomUID();
        foodDetails.getChefId();
        holder.Price.setText("Price: $ "+foodDetails.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uniqueID = UUID.randomUUID().toString();
                Intent intent = new Intent(mcontext, Food_details.class);
                intent.putExtra("FoodMenu",foodDetails.getRandomUID());
                intent.putExtra("ChefId",foodDetails.getChefId());
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return foodDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView Dishname,Price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.menu_image);
            Dishname = itemView.findViewById(R.id.dishname);
            Price = itemView.findViewById(R.id.dishprice);
        }
    }

}
