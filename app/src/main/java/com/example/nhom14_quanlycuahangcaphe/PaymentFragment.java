package com.example.nhom14_quanlycuahangcaphe;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentFragment extends Fragment {

    Button btn_ThanhToan;
    TextView tv_totalPrice, tv_count, tv_change;
    EditText et_discount, et_money;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PaymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PaymentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaymentFragment newInstance(String param1, String param2) {
        PaymentFragment fragment = new PaymentFragment();
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
        return inflater.inflate(R.layout.fragment_payment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_change =  view.findViewById(R.id.tv_change);
        tv_totalPrice = view.findViewById(R.id.tv_totalPrice);
        tv_count = view.findViewById(R.id.tv_count);
        btn_ThanhToan = view.findViewById(R.id.btnThanhToan);
        et_money = view.findViewById(R.id.et_money);
        et_discount = view.findViewById(R.id.et_discount);

        Table table = (Table) getArguments().getSerializable("table");

        tv_totalPrice.setText(table.totalPrice + "");
        tv_count.setText(tv_totalPrice.getText());

        et_discount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TinhTien(s.toString());
                TienThua(et_money.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                TinhTien(s.toString());
                TienThua(et_money.getText().toString());

            }
        });

        tv_change.setText("0");
        et_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TienThua(s.toString());
            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void afterTextChanged(Editable s) {

                TienThua(s.toString());

            }
        });

    }

    public void TinhTien(String s) {
        int change = 0;
        if (s.isEmpty() || s == "0") {
            change = 0;
        } else {
            change = Integer.parseInt(tv_totalPrice.getText().toString()) - Integer.parseInt(s);
            if (change <= 0) {
                change = 0;
            }
        }
        tv_count.setText(change + "");
    }

    public void TienThua(String s) {
        int change = 0;
        if (s.isEmpty() || s == "0") {
            change = 0;
        } else {
            change = Integer.parseInt(s) - Integer.parseInt(tv_count.getText().toString());
            if (change < 0) {
                btn_ThanhToan.setBackgroundResource(R.color.colorPrimary);
            }
            else {
                btn_ThanhToan.setBackgroundResource(R.color.green);
                btn_ThanhToan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Table table=(Table) getArguments().getSerializable("table");
                        AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
                        dialog.setTitle("Thanh Toán");
                        dialog.setMessage("Xác nhận thanh toán???");
                        dialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        dialog.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
                                String date=simpleDateFormat.format(new Date());
                                String idOrder =FirebaseDatabase.getInstance().getReference().child("orders").push().getKey();
                                String idUser= FirebaseAuth.getInstance().getUid();
                                Order order=new Order(idOrder,date,table,idUser);
                                FirebaseDatabase.getInstance().getReference().child("orders").child(order.idOrder).setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        HashMap<String,Object> hashMap=new HashMap<>();
                                        hashMap.put("foods",null);
                                        hashMap.put("totalPrice",0);
                                        hashMap.put("time","");
                                        FirebaseDatabase.getInstance().getReference().child("tables").child(table.number_table).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Intent intent=new Intent(getContext(),MainActivity.class);
                                                startActivity(intent);
                                                Toast.makeText(getContext(),"Thanh toán thành công",Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {

                                                        Toast.makeText(getContext(),"Không thành công",Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }

                                })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                                            }
                                        });


                            }
                        });

                        AlertDialog a=dialog.create();
                        a.show();
                    }
                });
            }
        }
            tv_change.setText(change + "");
    }
}