package com.example.nhom14_quanlycuahangcaphe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountRegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountRegisterFragment extends Fragment {

    NavController navController;
    EditText editMail_signup, editPass_signup, editPass2_signup;
    Button btnFinish;
    FirebaseAuth firebaseAuth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountRegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountRegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountRegisterFragment newInstance(String param1, String param2) {
        AccountRegisterFragment fragment = new AccountRegisterFragment();
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
        return inflater.inflate(R.layout.fragment_account_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        editMail_signup = view.findViewById(R.id.editMail_signup);
        editPass_signup = view.findViewById(R.id.editPass_signup);
        editPass2_signup = view.findViewById(R.id.editPass2_signup);
        btnFinish = view.findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(v -> {
            boolean test = true;
            if (editMail_signup.getText().toString().isEmpty()) {
                editMail_signup.setError("Vui lòng nhập mail!!!");
                test = false;
            }
            if (editPass_signup.getText().toString().isEmpty()) {
                editPass_signup.setError("Vui lòng nhập mật khẩu!!!");
                test = false;
            }
            if (editPass2_signup.getText().toString().isEmpty()) {
                editPass2_signup.setError("Vui lòng nhập lại mật khẩu !!!");
                test = false;
            }
            if (!(editPass_signup.getText().toString().equals(editPass2_signup.getText().toString()))) {
                editPass_signup.setError("Mật khẩu không trùng khớp!!!");
                test = false;
                return;
            }
            if (test == true) {
                HashMap<String, Object> hashMap = new HashMap();
                String name = getArguments().getString("name");
                String phone = getArguments().getString("phone");
                String address = getArguments().getString("address");
                String mail = editMail_signup.getText().toString();
                String pass = editPass_signup.getText().toString();
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                FirebaseAuth.getInstance().fetchSignInMethodsForEmail(mail).addOnSuccessListener(new OnSuccessListener<SignInMethodQueryResult>() {
                    @Override
                    public void onSuccess(SignInMethodQueryResult signInMethodQueryResult) {
                        boolean isNull = signInMethodQueryResult.getSignInMethods().isEmpty();
                        if (isNull) {
                            firebaseAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isComplete()) {
                                        String userID = firebaseAuth.getCurrentUser().getUid();
                                        Map<String, Object> user = new HashMap<>();
                                        user.put("name", name);
                                        user.put("phone", phone);
                                        user.put("address", address);
                                        user.put("email", mail);
                                        FirebaseDatabase.getInstance().getReference().child("users").child(userID).setValue(user)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(getContext(), "Đăng ký thành công.", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(getContext(),
                                                                SignInActivity.class);
                                                        intent.putExtra("email", mail);
                                                        getActivity().setResult(Activity.RESULT_OK, intent);
                                                        getActivity().finish();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(getContext(), "Đăng ký thất bại.",Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                }
                            });

                        } else {
                            dialog.setMessage("Email đã tồn tại!!!");
                            dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }

                            });
                            AlertDialog a = dialog.create();
                            a.show();
                        }

                    }
                });
            }
        });
    }
}