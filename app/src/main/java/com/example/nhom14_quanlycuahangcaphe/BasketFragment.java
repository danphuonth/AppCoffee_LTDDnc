package com.example.nhom14_quanlycuahangcaphe;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BasketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BasketFragment extends DialogFragment implements
        View.OnClickListener {

    RecyclerView rv_sp;
    Button btn_XacNhan,btn_Huy;
    FoodBasketAdapter foodBasketAdapter;
    ArrayList<FoodBasket> foods;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BasketFragment( ArrayList<FoodBasket> foods) {
        // Required empty public constructor
        this.foods=foods;
    }
    public BasketFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BasketFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BasketFragment newInstance(String param1, String param2) {
        BasketFragment fragment = new BasketFragment();
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
        return inflater.inflate(R.layout.fragment_basket, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_sp=view.findViewById(R.id.rv_sp);

        btn_XacNhan=view.findViewById(R.id.btn_XacNhan);
        btn_Huy=view.findViewById(R.id.btn_Huy);

        btn_Huy.setOnClickListener(this);
        btn_XacNhan.setOnClickListener(this);


        foodBasketAdapter = new FoodBasketAdapter(new ArrayList<>(App.basket.foods.values()));
        rv_sp.setAdapter(foodBasketAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_sp.setLayoutManager(layoutManager);
        rv_sp.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.btn_XacNhan: {
                ArrayList<FoodBasket> arrayList = new ArrayList<>(App.basket.foods.values());
                if (arrayList.isEmpty()) {
                    Toast.makeText(getContext(), "Rỗng", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    App.basket_order=App.getBasket();
                   getDialog().dismiss();
                   getActivity().finish();
                    }
                }

            case R.id.btn_Huy:
                getDialog().dismiss();
                break;
        }

    }
}