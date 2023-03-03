package com.example.nhom14_quanlycuahangcaphe;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodTableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public class ViewHoderFoodTable extends RecyclerView.ViewHolder{

        TextView tvFoodName,tvPriceFood,tvAmount,tvTotalPriceFood,note;

        public ViewHoderFoodTable(@NonNull View itemView) {
            super(itemView);
            tvFoodName=itemView.findViewById(R.id.tvFoodName);
            tvPriceFood=itemView.findViewById(R.id.tvPriceFood);
            tvAmount=itemView.findViewById(R.id.tvAmount);
            tvTotalPriceFood=itemView.findViewById(R.id.tvTotalPriceFood);
            note=itemView.findViewById(R.id.tv_ghichuFT);
        }
    }

    private List<FoodBasket> mFoodBasket;
    public FoodTableAdapter (List<FoodBasket> foodBaskets)
    {
        mFoodBasket=foodBaskets;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.row_foodtable,parent,false);
        return new ViewHoderFoodTable(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        FoodBasket foodBasket=mFoodBasket.get(position);
        ViewHoderFoodTable viewHoderFoodTable=(ViewHoderFoodTable) holder;
        viewHoderFoodTable.tvFoodName.setText(foodBasket.name);
        viewHoderFoodTable.tvPriceFood.setText(foodBasket.price+"");
        viewHoderFoodTable.tvAmount.setText(foodBasket.quantity+"");
        viewHoderFoodTable.tvTotalPriceFood.setText(foodBasket.sum+"");
        viewHoderFoodTable.note.setText(foodBasket.ghichu);
    }

    @Override
    public int getItemCount() {
        return mFoodBasket.size();
    }
}
