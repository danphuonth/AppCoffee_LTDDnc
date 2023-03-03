package com.example.nhom14_quanlycuahangcaphe;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public interface OnOderItemClickListener {
        void onOrderItemClick(Order order);

    }
    public class ViewHolderOrder extends RecyclerView.ViewHolder{

        TextView nameTable,date,idOder,total;

        public ViewHolderOrder(@NonNull View itemView) {
            super(itemView);
            nameTable=itemView.findViewById(R.id.tvNameTable);
            date=itemView.findViewById(R.id.tvNgay);
            idOder=itemView.findViewById(R.id.tvMaDon);
            total=itemView.findViewById(R.id.tvtotalPriceOrder);
        }
    }

    private List<Order> mOrder;
    private int TYPE_LAYOUT;
    private OnOderItemClickListener mlistener;

    public OrderAdapter(ArrayList<Order> orders, OnOderItemClickListener listener, int type_layout) {
        mOrder = orders;
        mlistener = listener;
        TYPE_LAYOUT =type_layout;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_order, parent, false);
        return new ViewHolderOrder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Order order=mOrder.get(position);
        ViewHolderOrder viewHolderOrder=(ViewHolderOrder) holder;
        viewHolderOrder.nameTable.setText("Bàn "+order.nameTable);
        viewHolderOrder.total.setText(order.total+"");
        viewHolderOrder.date.setText(order.date+" - "+order.time);
        viewHolderOrder.idOder.setText(order.idOrder);
        viewHolderOrder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mlistener.onOrderItemClick(order);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mOrder.size();

    }
}

