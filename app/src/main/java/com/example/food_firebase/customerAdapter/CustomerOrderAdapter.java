package com.example.food_firebase.customerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_firebase.R;
import com.example.food_firebase.model.Receipt;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class CustomerOrderAdapter extends RecyclerView.Adapter<CustomerOrderAdapter.ViewHolder> {

    private Context mcontext;
    private List<Receipt> receiptList;
    DatabaseReference databaseReference;

    public CustomerOrderAdapter(Context mcontext, List<Receipt> receiptList) {
        this.mcontext = mcontext;
        this.receiptList = receiptList;
    }


    @NonNull
    @Override
    public CustomerOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.receipt_line, parent, false);
        return new CustomerOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Receipt receipt = receiptList.get(position);
        String item = "";
        for (int i = 0; i < receipt.getCartList().size(); i++){
            item += ("  - "+ receipt.getCartList().get(i).getDishName()+" ($"+receipt.getCartList().get(i).getPrice()+" x "
                    +receipt.getCartList().get(i).getDishQuantity()+")"+"\n");
        }
        holder.Item.setText(item);
        holder.Total_Price.setText("Total price: $ " + receipt.getTotal());
        holder.Day.setText("Date: "+receipt.getDay());
    }

    @Override
    public int getItemCount() {
        return receiptList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Item,Name, Total_Price, Day;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name);
            Total_Price = itemView.findViewById(R.id.total_price);
            Day = itemView.findViewById(R.id.dayOfWeek);
            Item = itemView.findViewById(R.id.Items);
        }
    }
}