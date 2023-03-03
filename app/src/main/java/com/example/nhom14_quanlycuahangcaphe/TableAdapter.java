package com.example.nhom14_quanlycuahangcaphe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public interface OnTableItemClickListener {
        void onTableItemClick(Table table);

    }
    public class ViewHolderTable extends RecyclerView.ViewHolder{

        TextView name_table,time,totalPrice;
        RelativeLayout bg_table;

        public ViewHolderTable(@NonNull View itemView) {
            super(itemView);
            name_table=itemView.findViewById(R.id.name_table);
            time=itemView.findViewById(R.id.tv_time);
            totalPrice=itemView.findViewById(R.id.totalPrice);
            bg_table=itemView.findViewById(R.id.bg_table);
        }
    }

    private List<Table> mTable;
    private int TYPE_LAYOUT ;
    private OnTableItemClickListener mlistener;

    public TableAdapter(ArrayList<Table> tables,OnTableItemClickListener listener, int type_layout) {
        mTable = tables;
        mlistener = listener;
        TYPE_LAYOUT =type_layout;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_table, parent, false);
        return new TableAdapter.ViewHolderTable(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Table table=mTable.get(position);

        ViewHolderTable viewHolderTable=(ViewHolderTable) holder;
        viewHolderTable.name_table.setText("Bàn "+table.number_table);
        viewHolderTable.totalPrice.setText(table.totalPrice+"");


        if (table.totalPrice!=0)
        {
            viewHolderTable.bg_table.setBackgroundResource(R.color.green);
            viewHolderTable.time.setText(table.time);

        }
        else {
            viewHolderTable.time.setVisibility(View.GONE);
            viewHolderTable.totalPrice.setVisibility(View.GONE);
        }




        viewHolderTable.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.onTableItemClick(table);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTable.size();

    }
}
