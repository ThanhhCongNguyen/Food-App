package com.example.food_firebase.chefAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_firebase.R;
import com.example.food_firebase.model.Client;
import com.example.food_firebase.model.Receipt;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ChefStatisticsAdapter extends RecyclerView.Adapter<ChefStatisticsAdapter.ViewHolder> {

    private Context mcontext;
    private List<Receipt> receiptList;
    FirebaseDatabase database;
    DatabaseReference myRef;
    public ChefStatisticsAdapter(Context mcontext, List<Receipt> receiptList) {
            this.mcontext = mcontext;
            this.receiptList = receiptList;
            }


    @NonNull
    @Override
    public ChefStatisticsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mcontext).inflate(R.layout.statistics, parent, false);
            return new ChefStatisticsAdapter.ViewHolder(view);
            }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Receipt receipt = receiptList.get(position);
            String Custommer_Id = receipt.getId0fCustomer();
            String item = "";
            for (int i = 0; i < receipt.getCartList().size(); i++){
                item += ("  - "+ receipt.getCartList().get(i).getDishName()+" ( "+receipt.getCartList().get(i).getPrice()+" x "
                    +receipt.getCartList().get(i).getDishQuantity()+" )"+"\n");
                }
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("Customer").child(Custommer_Id);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull  DataSnapshot snapshot) {
                    Client client = snapshot.getValue(Client.class);
                    holder.Customer_Name.setText("Name: "+client.getFname()+" "+client.getLname());
                    holder.Email.setText("Email: "+client.getEmail());

                }

                @Override
                public void onCancelled(@NonNull  DatabaseError error) {

                }
            });
            holder.Items.setText(item);
            holder.Total_Price.setText("Total price: $ " + receipt.getTotal());
            holder.Day.setText("Date:"+ receipt.getDay());
            }

    @Override
    public int getItemCount() {
            return receiptList.size();
            }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name, Total_Price, Day,Customer_Name, Email, Items;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name_sta);
            Total_Price = itemView.findViewById(R.id.total_price_sta);
            Day = itemView.findViewById(R.id.dayOfWeek_sta);
            Customer_Name = itemView.findViewById(R.id.customer_sta);
            Email = itemView.findViewById(R.id.email_sta);
            Items = itemView.findViewById(R.id.item);
        }
    }
}