package com.example.nhom14_quanlycuahangcaphe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FoodBasketAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public class ViewHoderFoodBasket extends RecyclerView.ViewHolder{

        TextView tvFoodName,tvPriceFood,tvAmount,tvTotalPriceFood,note;

        public ViewHoderFoodBasket(@NonNull View itemView) {
            super(itemView);
            tvFoodName=itemView.findViewById(R.id.tvFoodNameBasket);
            tvPriceFood=itemView.findViewById(R.id.tvPriceFoodBasket);
            tvAmount=itemView.findViewById(R.id.tvQuantityBasket);
            tvTotalPriceFood=itemView.findViewById(R.id.tvTotalPriceFoodBasket);
            note=itemView.findViewById(R.id.tv_ghichu);
        }
    }

    private List<FoodBasket> mFoodBasket;
    public FoodBasketAdapter (ArrayList<FoodBasket> foodBaskets)
    {
        mFoodBasket=foodBaskets;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.row_food_basket,parent,false);
        return new ViewHoderFoodBasket(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        FoodBasket foodBasket=mFoodBasket.get(position);
        ViewHoderFoodBasket viewHoderFoodBasket=(ViewHoderFoodBasket)holder;
        viewHoderFoodBasket.tvFoodName.setText(foodBasket.name);
        viewHoderFoodBasket.tvPriceFood.setText(foodBasket.price+"");
        viewHoderFoodBasket.tvAmount.setText(foodBasket.quantity+"");
        viewHoderFoodBasket.tvTotalPriceFood.setText(foodBasket.sum+"");
        viewHoderFoodBasket.note.setText(foodBasket.ghichu);

    }

    @Override
    public int getItemCount() {
        return mFoodBasket.size();
    }
}
