package com.example.nhom14_quanlycuahangcaphe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DrinkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrinkFragment extends Fragment {

    RecyclerView rvFood;
    FoodAdapter foodAdapter;
    ArrayList<Food> foods;
    App app;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DrinkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DrinkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DrinkFragment newInstance(String param1, String param2) {
        DrinkFragment fragment = new DrinkFragment();
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
        foods=new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drink, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFood = view.findViewById(R.id.rvDink);
        foodAdapter = new FoodAdapter(foods,this::onFoodItemClick,1);
        rvFood.setAdapter(foodAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvFood.setLayoutManager(layoutManager);
        rvFood.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        Query query= FirebaseDatabase.getInstance().getReference().child("foods");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                foods.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Food food = dataSnapshot.getValue(Food.class);
                    if (food.foodType.equals("caphe"))
                    {
                        foods.add(food);
                    }

                }
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void onFoodItemClick(Food food) {
        FoodBasket foodBasket = app.basket.getFood(food.getFoodKey());
        if (foodBasket == null)
            foodBasket = new FoodBasket(food, 1, food.getPrice());
        AddToBasketDialogFragment dialog = new
                AddToBasketDialogFragment(foodBasket);
        dialog.show(getActivity().getSupportFragmentManager(), "add_to_basket_dialog");
    }
}