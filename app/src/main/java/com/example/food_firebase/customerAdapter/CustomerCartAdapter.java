package com.example.food_firebase.customerAdapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.food_firebase.R;
import com.example.food_firebase.model.Cart;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CustomerCartAdapter extends RecyclerView.Adapter<CustomerCartAdapter.ViewHolder> {

    private Context mcontext;
    private List<Cart> cartModellist;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;

    public CustomerCartAdapter(Context context, List<Cart> cartModellist) {
        this.cartModellist = cartModellist;
        this.mcontext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.cart_placeorder, parent, false);
        return new CustomerCartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Cart cart = cartModellist.get(position);
        holder.dishname.setText(cart.getDishName());
        holder.PriceRs.setText("Price: $ " + cart.getPrice());
        holder.Qty.setText("× " + cart.getDishQuantity());
        holder.Totalrs.setText("Total: $ " + cart.getTotalprice());
        holder.elegantNumberButton.setNumber(cart.getDishQuantity());
        holder.elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                int num = newValue;
                int newTotal = num*Integer.parseInt(cart.getPrice());
                Cart cart1 = new Cart(cart.getIdOfCart(),cart.getChefId(), cart.getDishName(),String.valueOf(num),cart.getPrice(),String.valueOf(newTotal),cart.getRamdomId());
                firebaseDatabase.getInstance().getReference("Cart").child(userid).child(cart.getIdOfCart())
                        .setValue(cart1);
            }
        });
        holder.btnremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DeleteData();
                new AlertDialog.Builder(mcontext)
                        .setTitle("Chú ý")
                        .setMessage("Bạn có chắc chắn muốn xóa item không ?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                firebaseDatabase = FirebaseDatabase.getInstance();
                                myRef = firebaseDatabase.getReference("Cart").child(userid).child(cart.getIdOfCart());
                                myRef.removeValue(new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull  DatabaseReference ref) {
                                        Toast.makeText(mcontext,"Deleted data",Toast.LENGTH_LONG).show();

                                    }
                                });

                            }
                        })
                        .setNegativeButton("Cacel",null)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartModellist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        String id;
        TextView dishname, PriceRs, Qty, Totalrs;
        ElegantNumberButton elegantNumberButton;
        Button btnremove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dishname = itemView.findViewById(R.id.Dishname);
            PriceRs = itemView.findViewById(R.id.pricers);
            Qty = itemView.findViewById(R.id.qty);
            Totalrs = itemView.findViewById(R.id.totalrs);
            elegantNumberButton = itemView.findViewById(R.id.elegantbtn);
            btnremove = itemView.findViewById(R.id.btnRemove);
//            firebaseDatabase = FirebaseDatabase.getInstance();
//            myRef = firebaseDatabase.getReference("Cart");
//            myRef.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
        }
    }
}
