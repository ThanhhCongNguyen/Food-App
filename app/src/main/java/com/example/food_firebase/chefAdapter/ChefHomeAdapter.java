package com.example.food_firebase.chefAdapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food_firebase.R;
import com.example.food_firebase.model.UpdateDishModel;
import com.example.food_firebase.model.UpdateFood;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class ChefHomeAdapter extends RecyclerView.Adapter<ChefHomeAdapter.ViewHolder> {

    private Context mcontext;
    private List<UpdateDishModel> updateDishModellist;
    DatabaseReference databaseReference;

    public ChefHomeAdapter(Context context , List<UpdateDishModel>updateDishModelslist){

        this.updateDishModellist = updateDishModelslist;
        this.mcontext = context;
    }


    @NonNull
    @Override
    public ChefHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.chef_menudish,parent,false);
        return new ChefHomeAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ChefHomeAdapter.ViewHolder holder, int position) {

        final UpdateDishModel updateDishModel = updateDishModellist.get(position);
        Glide.with(mcontext).load(updateDishModel.getImageURL()).into(holder.imageView);
        holder.Dishname.setText(updateDishModel.getDishes());
        updateDishModel.getRandomUID();
        updateDishModel.getChefId();
        holder.Price.setText("Price: $ "+updateDishModel.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mcontext)
                        .setTitle("Update")
                        .setMessage("Bạn có muốn chỉnh sửa sản phẩm này không ?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(mcontext, UpdateFood.class);
                                intent.putExtra("RandomId",updateDishModel.getRandomUID());
                                mcontext.startActivity(intent);

                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return updateDishModellist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView Dishname,Price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.chef_menu_image);
            Dishname = itemView.findViewById(R.id.chef_dishname);
            Price = itemView.findViewById(R.id.chef_dishprice);

        }
    }

}
