package com.example.nhom14_quanlycuahangcaphe;

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
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryDetailFragment extends Fragment {
    TextView table,maDH,datetime,total,thanhtoan;
    RecyclerView rv_sanpham;
    FoodTableAdapter foodTableAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryDetailFragment newInstance(String param1, String param2) {
        HistoryDetailFragment fragment = new HistoryDetailFragment();
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
        return inflater.inflate(R.layout.fragment_history_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        table=view.findViewById(R.id.tv_nametable);
        maDH=view.findViewById(R.id.tv_maDH);
        total=view.findViewById(R.id.tv_total);
        datetime=view.findViewById(R.id.tv_datetime);
        rv_sanpham=view.findViewById(R.id.rv_sanpham);
        thanhtoan=view.findViewById(R.id.tv_thanhtoan);
        Order order=(Order) getArguments().getSerializable("order");

        table.setText(order.nameTable);
        maDH.setText(order.idOrder+"");
        total.setText(order.total+"");
        datetime.setText(order.date+" "+order.time);
        thanhtoan.setText(order.total+"");

        foodTableAdapter = new FoodTableAdapter(order.foods);
        rv_sanpham.setAdapter(foodTableAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_sanpham.setLayoutManager(layoutManager);
        rv_sanpham.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
    }
}