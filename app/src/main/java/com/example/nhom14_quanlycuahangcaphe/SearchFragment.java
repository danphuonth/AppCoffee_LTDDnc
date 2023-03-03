package com.example.nhom14_quanlycuahangcaphe;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    FoodAdapter foodAdapter;
    RecyclerView rvSearch;
    TextView tvHuySearch,tvtotalItems;
    ArrayList<Food> foods;
    ArrayList<Food> foodFilters;
    SearchView searchView;
    TextView btn_Reset,btn_Done;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        App.basket=new Basket();
        rvSearch=view.findViewById(R.id.rvSearch);
        tvHuySearch=view.findViewById(R.id.tvHuySearch);
        searchView=view.findViewById(R.id.sv_search);

        foods=new ArrayList();

        foodFilters=new ArrayList<>();
        foodAdapter = new FoodAdapter(foods,this::onFoodItemClick,1);
        rvSearch.setAdapter(foodAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvSearch.setLayoutManager(layoutManager);
        rvSearch.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                FilterbyNameFood(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                FilterbyNameFood(newText);
                return false;
            }
        });
        tvHuySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;
                fragment = new MenuFragment();

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.nav, fragment);
                ft.commit();


            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();


    }

    private void onFoodItemClick(Food food) {
        FoodBasket foodBasket = App.basket.getFood(food.getFoodKey());
        if (foodBasket == null)
            foodBasket = new FoodBasket(food, 1, food.getPrice());
        AddToBasketDialogFragment dialog = new
                AddToBasketDialogFragment(foodBasket);
        dialog.show(getActivity().getSupportFragmentManager(), "add_to_basket_dialog");
    }
    public void FilterbyNameFood(String s)
    {
        Query query= FirebaseDatabase.getInstance().getReference().child("foods");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                foods.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Food food = dataSnapshot.getValue(Food.class);
                    if (food.name.toUpperCase().contains(s.toUpperCase()))
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

}