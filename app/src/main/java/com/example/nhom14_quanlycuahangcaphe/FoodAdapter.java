package com.example.nhom14_quanlycuahangcaphe;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public interface OnFoodItemClickListener {
        void onFoodItemClick(Food food);

    }
    public class ViewHolderFood extends RecyclerView.ViewHolder{

        TextView name,price,quantify;
        ImageView image;

        public ViewHolderFood(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.namef);
            price=itemView.findViewById(R.id.pricef);
            image=itemView.findViewById(R.id.imagef);
            quantify=itemView.findViewById(R.id.quantify);
        }
    }

    private List<Food> mFood;
    private int TYPE_LAYOUT;
    private OnFoodItemClickListener mlistener;

    public FoodAdapter(ArrayList<Food> foods, OnFoodItemClickListener listener, int type_layout) {
        mFood = foods;
        mlistener = listener;
        TYPE_LAYOUT =type_layout;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_food, parent, false);
        return new ViewHolderFood(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Food food=mFood.get(position);
        ViewHolderFood viewHolderFood=(ViewHolderFood) holder;
        StorageReference profileRef = FirebaseStorage.getInstance().getReference().child("Food/"+ food.image);
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(viewHolderFood.image);
            }
        });
        viewHolderFood.name.setText(food.name);
        viewHolderFood.price.setText(food.price+"");
        viewHolderFood.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mlistener.onFoodItemClick(food);

            }
        });
        viewHolderFood.quantify.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return mFood.size();

    }
}
