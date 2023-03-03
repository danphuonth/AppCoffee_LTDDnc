package com.example.nhom14_quanlycuahangcaphe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ResetPassword extends AppCompatActivity {

    EditText email,pas,pas2;
    Button save;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        email=findViewById(R.id.email_reset);
        pas=findViewById(R.id.txtb_pas);
        pas2=findViewById(R.id.txtb_pas2);
        save=findViewById(R.id.btn_SavePass);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean test = true;
                if (email.getText().toString().isEmpty()) {
                    email.setError("Vui lòng nhập mail!!!");
                    test = false;
                }
                if (pas.getText().toString().isEmpty()) {
                    pas.setError("Vui lòng nhập mật khẩu!!!");
                    test = false;
                }
                if (pas2.getText().toString().isEmpty()) {
                    pas2.setError("Vui lòng nhập lại mật khẩu !!!");
                    test = false;
                }
                if (test == true) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(ResetPassword.this);
                    FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<SignInMethodQueryResult>() {
                        @Override
                        public void onSuccess(SignInMethodQueryResult signInMethodQueryResult) {
                            boolean isNull = signInMethodQueryResult.getSignInMethods().isEmpty();
                            if (isNull) {

                                dialog.setMessage("Không có tài khoản nào khớp với thông tin đó!");
                                dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }

                                });
                                AlertDialog a = dialog.create();
                                a.show();
                            } else {
                                if (pas.getText().toString() == pas2.getText().toString()) {
                                    FirebaseAuth.getInstance().getCurrentUser().updatePassword(pas.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(ResetPassword.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(ResetPassword.this, SignInActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                                } else {
                                    dialog.setMessage("Mật khẩu không trùng khớp");
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
                        }
                    });

                }
            }
        });
    }
}