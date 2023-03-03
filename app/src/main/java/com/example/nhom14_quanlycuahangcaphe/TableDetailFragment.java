package com.example.nhom14_quanlycuahangcaphe;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TableDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TableDetailFragment extends Fragment {

    TextView name_table;
    Button btn_AddFood,btn_ThanhToan,btn_ThongBao;
    RecyclerView rvFoodTable;
    NavController navController;
    FoodTableAdapter foodTableAdapter;
    ArrayList<FoodBasket> foodBaskets;
    Basket basket;
    Table table;
    int totalPrice;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TableDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TableDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TableDetailFragment newInstance(String param1, String param2) {
        TableDetailFragment fragment = new TableDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        foodBaskets=new ArrayList<>();
        basket=new Basket();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        navController = Navigation.findNavController(view);

        name_table=view.findViewById(R.id.name_tableDetail);
        rvFoodTable=view.findViewById(R.id.rvFoodTable);
        btn_ThanhToan=view.findViewById(R.id.btn_ThanhToan);
        btn_ThongBao=view.findViewById(R.id.btn_ThongBao);

        table=(Table) getArguments().getSerializable("table");
        if (table.totalPrice==0)
        {
            name_table.setText(table.number_table+" / Còn trống");
        }
        else {
            name_table.setText(table.number_table+" / Đang sử dụng");
        }
        totalPrice=table.totalPrice;
        rvFoodTable = view.findViewById(R.id.rvFoodTable);
        foodTableAdapter = new FoodTableAdapter(foodBaskets);
        rvFoodTable.setAdapter(foodTableAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvFoodTable.setLayoutManager(layoutManager);
        rvFoodTable.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));


        Query query= FirebaseDatabase.getInstance().getReference().child("tables").child(table.number_table).child("foods");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                foodBaskets.clear();
                for (DataSnapshot dataSnapshot :snapshot.getChildren()) {
                    FoodBasket foodBasket=dataSnapshot.getValue(FoodBasket.class);
                    foodBaskets.add(foodBasket);
                    totalPrice+=foodBasket.getSum();
                }
                table.foods=foodBaskets;
                foodTableAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        FloatingActionButton fab =view.findViewById(R.id.btn_AddFood);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),OrderActivity.class);
                startActivity(intent);
            }
        });
        btn_ThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("table",table);
                navController.navigate(R.id.action_tableDetailFragment2_to_paymentFragment2,bundle);
                
            }
        });
        btn_ThongBao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm:ss");
                String time=simpleDateFormat.format(calendar.getTime());

                Table table2=new Table(table,time,new ArrayList<>(foodBaskets),totalPrice,"");
                HashMap<String,Object> hashMap=new HashMap<>();
                hashMap.put("time",table2.time);
                hashMap.put("foods",table2.foods);
                hashMap.put("totalPrice",table2.totalPrice);

                FirebaseDatabase.getInstance().getReference().child("tables").child(table.number_table).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(v.getContext(),"Gọi món thành công!!",Toast.LENGTH_SHORT).show();
                        foodTableAdapter.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(v.getContext(),"Gọi món thất bại!!!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        if (App.basket_order!=null)
        {
            upDatetoTable(App.basket_order);
        }
        App.basket_order=new Basket();
    }

    private void upDatetoTable(Basket basket){
        ArrayList<FoodBasket> foods=new ArrayList<>(basket.foods.values());
        for (FoodBasket foodBasket :foods) {
            foodBaskets.add(foodBasket);
            totalPrice+=foodBasket.getSum();
        }
        foodTableAdapter.notifyDataSetChanged();

    }
}