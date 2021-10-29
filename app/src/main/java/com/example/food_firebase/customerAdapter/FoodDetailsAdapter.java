package com.example.food_firebase.customerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.food_firebase.R;
import com.example.food_firebase.model.FoodDetails;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class FoodDetailsAdapter extends RecyclerView.Adapter<FoodDetailsAdapter.ViewHolder>{
    private Context mcontext;
    private List<FoodDetails> foodDetailsList;
    DatabaseReference databaseReference;

    public FoodDetailsAdapter(Context mcontext, List<FoodDetails> foodDetailsList) {
        this.mcontext = mcontext;
        this.foodDetailsList = foodDetailsList;
    }

    @NonNull
    @Override
    public FoodDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.lines_food,parent,false);
        return new FoodDetailsAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FoodDetailsAdapter.ViewHolder holder, int position) {
//        UpdateDishModel updateDishModel = (UpdateDishModel) getIntent().getSerializableExtra("ThongtinChitiet");

        final FoodDetails foodDetails = foodDetailsList.get(position);
//        Glide.with(mcontext).load(foodDetails.getImageURL()).into(holder.imageView);
//        holder.Foodname.setText(foodDetails.getDishes());
//        foodDetails.getRandomUID();
//        foodDetails.getChefId();
//        holder.FoodPrice.setText("Price: $ "+foodDetails.getPrice());
//        holder.FoodDescription.setText(""+foodDetails.getDescription());
//        holder.FoodQuantity.setText(""+foodDetails.getQuantity());


    }

    @Override
    public int getItemCount() {
        return foodDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView Foodname, ChefName, ChefLoaction, FoodQuantity,FoodPrice,
                FoodDescription;
        ImageView imageView;
        ElegantNumberButton additem;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Foodname = (TextView) itemView.findViewById(R.id.food_name);
//            ChefName = (TextView) itemView.findViewById(R.id.chef_name);
//            ChefLoaction = (TextView) itemView.findViewById(R.id.chef_location);
//            FoodQuantity = (TextView) itemView.findViewById(R.id.food_quantity);
            FoodPrice = (TextView) itemView.findViewById(R.id.food_price);
            FoodDescription = (TextView) itemView.findViewById(R.id.food_description);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            additem = (ElegantNumberButton) itemView.findViewById(R.id.number_btn);

        }
    }
}
