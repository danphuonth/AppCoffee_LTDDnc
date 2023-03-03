package com.example.nhom14_quanlycuahangcaphe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonRegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonRegisterFragment extends Fragment {

    EditText editName_signup,editPhone_signup,editAddress_signup;
    Button btnNext;
    NavController navController;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PersonRegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonRegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonRegisterFragment newInstance(String param1, String param2) {
        PersonRegisterFragment fragment = new PersonRegisterFragment();
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
        return inflater.inflate(R.layout.fragment_person_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        editName_signup = view.findViewById(R.id.editName_signup);
        editPhone_signup = view.findViewById(R.id.editPhone_signup);
        editAddress_signup = view.findViewById(R.id.editAddress_signup);
        btnNext = view.findViewById(R.id.buttonNext);
        btnNext.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("name", editName_signup.getText().toString());
            bundle.putString("phone", editPhone_signup.getText().toString());
            bundle.putString("address", editAddress_signup.getText().toString());
            boolean test=true;
            if (editName_signup.getText().toString().isEmpty())
            {
                editName_signup.setError("Vui lòng nhập tên!!!");
                test=false;
            }
            if (editPhone_signup.getText().toString().isEmpty())
            {
                editPhone_signup.setError("Vui lòng nhập số điện thoại!!!");
                test=false;
            }
            if (editAddress_signup.getText().toString().isEmpty())
            {
                editAddress_signup.setError("Vui lòng nhập địa chỉ!!!");
                test=false;
            }
            if (test==true) {

                navController.navigate(R.id.action_personRegisterFragment_to_accountRegisterFragment,bundle);
            }
        });
    }

}