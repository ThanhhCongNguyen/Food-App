package com.example.food_firebase.customerFoodPanel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_firebase.model.Cart;
import com.example.food_firebase.customerAdapter.CustomerCartAdapter;
import com.example.food_firebase.R;
import com.example.food_firebase.model.Receipt;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;  // Import the LocalDateTime class


public class CustomerCartFragmnet extends Fragment {
    RecyclerView recyclerView;
    private List<Cart> updateCartList;
    private CustomerCartAdapter adapter;
    FirebaseDatabase database;
    DatabaseReference myRef;
    LinearLayout linearLayout_id_btn,linearLayout_2;
    TextView total,thongbao;
    Button remove,order;
    int total_t = 0;
    private List<Receipt> receiptList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customercart, null);
        getActivity().setTitle("Cart");
        linearLayout_id_btn = v.findViewById(R.id.TotalBtns);
        recyclerView = v.findViewById(R.id.recyclecart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        updateCartList = new ArrayList<>();
        remove = v.findViewById(R.id.RM);
        order = v.findViewById(R.id.PO);
        receiptList = new ArrayList<>();
        linearLayout_2 = v.findViewById(R.id.id_button);
        thongbao = v.findViewById(R.id.txt_thongbao);
        linearLayout_id_btn.setVisibility(View.INVISIBLE);
        linearLayout_2.setVisibility(View.INVISIBLE);
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Cart").child(userid);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                updateCartList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Cart cart = dataSnapshot.getValue(Cart.class);
                    updateCartList.add(cart);


                }
                adapter = new CustomerCartAdapter(getContext(),updateCartList);
                recyclerView.setAdapter(adapter);
                if(updateCartList.size() > 0){
                    thongbao.setVisibility(View.INVISIBLE);
                    linearLayout_id_btn.setVisibility(View.VISIBLE);
                    linearLayout_2.setVisibility(View.VISIBLE);
                }
                else {
                    thongbao.setVisibility(View.VISIBLE);
                    linearLayout_id_btn.setVisibility(View.INVISIBLE);
                    linearLayout_2.setVisibility(View.INVISIBLE);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(updateCartList.size() != 0){
                    new AlertDialog.Builder(getContext())
                            .setTitle("Chú ý")
                            .setMessage("Bạn có muốn xóa tất cả item không ?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    linearLayout_id_btn.setVisibility(View.INVISIBLE);
                                    linearLayout_2.setVisibility(View.INVISIBLE);
                                    thongbao.setVisibility(View.VISIBLE);
                                    String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    database = FirebaseDatabase.getInstance();
                                    myRef = database.getReference("Cart").child(userid);
                                    myRef.removeValue(new DatabaseReference.CompletionListener() {
                                        @Override
                                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                            Toast.makeText(getContext(), "Deleted data", Toast.LENGTH_LONG).show();

                                        }
                                    });
                                }
                            })
                            .setNegativeButton("Cancel",null)
                            .show();

                }
                else {
                    Toast.makeText(getContext(),"Bạn chưa chọn item nào",Toast.LENGTH_LONG).show();
                }

            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Thông báo")
                        .setMessage("Bạn có muốn mua sản phẩm này không ?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String randomid = UUID.randomUUID().toString();
                                database = FirebaseDatabase.getInstance();
//                                Date currentTime = Calendar.getInstance().getTime();
                                LocalDateTime myDateObj = LocalDateTime.now();
                                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                                String formattedDate = myDateObj.format(myFormatObj);

                                if(updateCartList.size() != 0){
                                    for(int i = 0; i < updateCartList.size(); i++){
                                        total_t += Integer.parseInt(updateCartList.get(i).getTotalprice());
                                    }
                                    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    Receipt.count += 1;
                                    Receipt receipt = new Receipt(String.valueOf(Receipt.count),userId,updateCartList,String.valueOf(total_t),formattedDate);
                                    database.getInstance().getReference("Receipt").child(userId).child(String.valueOf(Receipt.count)).setValue(receipt);
                                    database.getInstance().getReference("statistics").child(String.valueOf(Receipt.count)).setValue(receipt);
                                    myRef = database.getReference("Cart").child(userid);
                                    myRef.removeValue();

                                    Toast.makeText(getContext(),"Mua hàng thành công", Toast.LENGTH_LONG).show();
                                    linearLayout_id_btn.setVisibility(View.INVISIBLE);
                                    thongbao.setVisibility(View.VISIBLE);
                                    linearLayout_2.setVisibility(View.INVISIBLE);

                                }
                                else {
                                    Toast.makeText(getContext(),"Bạn chưa mua item nào",Toast.LENGTH_LONG).show();
                                }

                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .show();
            }
        });
        return v;
    }

}
