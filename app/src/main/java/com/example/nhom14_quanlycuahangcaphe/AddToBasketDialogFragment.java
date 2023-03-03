package com.example.nhom14_quanlycuahangcaphe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddToBasketDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddToBasketDialogFragment extends DialogFragment implements
        View.OnClickListener {
    FoodBasket food;
    Button btnHuy,btnAddtoBasket;
    ImageView btnSubtract, btnAdd;
    TextView tvName ;
    TextView tvPrice ;
    TextView tvQuantity,ghichu1,ghichu2,ghichu3;
    EditText ghichu;
    FragmentActivity activity;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddToBasketDialogFragment() {
        // Required empty public constructor
    }
    public AddToBasketDialogFragment(FoodBasket food) {
        this.food = food;
//        this.activity=a;
        Log.d("ABC", food.toString());
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddToBasketDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddToBasketDialogFragment newInstance(String param1, String param2) {
        AddToBasketDialogFragment fragment = new AddToBasketDialogFragment();
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
        return inflater.inflate(R.layout.fragment_add_to_basket_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvName = view.findViewById(R.id.tvName);
        tvPrice = view.findViewById(R.id.tvGia);
        tvQuantity = view.findViewById(R.id.tvQuantity);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnSubtract = view.findViewById(R.id.btnSubtract);
        btnAddtoBasket=view.findViewById(R.id.btn_Them);
        btnHuy = view.findViewById(R.id.btnHuy);
        ghichu=view.findViewById(R.id.et_ghichu);
        ghichu1=view.findViewById(R.id.ex_ghichu1);
        ghichu2=view.findViewById(R.id.ex_ghichu2);
        ghichu3=view.findViewById(R.id.ex_ghichu3);
        ghichu1.setOnClickListener(this);
        ghichu2.setOnClickListener(this);
        ghichu3.setOnClickListener(this);
        btnHuy.setOnClickListener(this);
        btnAddtoBasket.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnSubtract.setOnClickListener(this);
        tvName.setText(food.name);
        tvPrice.setText(food.price+ " VND");
        updateStats();
    }

    @Override
    public void onResume() {

        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        getDialog().setCancelable(true);
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnSubtract:
                food.decrease();
                updateStats();
                break;
            case R.id.btnAdd:
                food.increase();
                updateStats();
                break;
            case R.id.btn_Them:
                if (food.quantity>0) {
                    food.ghichu=ghichu.getText().toString();
                    App.basket.addFood(food);
                }
                else {
                    if (App.basket.foods.containsValue(food))
                    {
                        App.basket.foods.remove(food.getFoodKey(),food);
                    }
                }

                ((OrderActivity)getActivity()).upDateBasket();
                getDialog().dismiss();
                break;
            case R.id.btnHuy:
                getDialog().dismiss();
                break;
            case R.id.ex_ghichu1:
                ghichu.setText(ghichu.getText().toString()+ghichu1.getText().toString()+", ");
                ghichu1.setBackgroundResource(R.drawable.boder_selected);
                break;
            case R.id.ex_ghichu2:
                ghichu.setText(ghichu.getText().toString()+ghichu2.getText().toString()+", ");
                ghichu2.setBackgroundResource(R.drawable.boder_selected);

                break;
            case R.id.ex_ghichu3:
                ghichu.setText(ghichu.getText().toString()+ghichu3.getText().toString()+", ");
                ghichu3.setBackgroundResource(R.drawable.boder_selected);

                break;
        }
    }
    private void updateStats() {

        tvQuantity.setText(String.valueOf(food.quantity));
    }

}