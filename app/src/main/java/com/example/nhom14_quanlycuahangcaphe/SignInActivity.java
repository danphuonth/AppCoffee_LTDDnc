package com.example.nhom14_quanlycuahangcaphe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class SignInActivity extends AppCompatActivity {

    Button buttonSignUp, buttonSignIn;
    EditText editTextEmail, editTextPassword;
    FirebaseAuth firebaseAuth;
    CheckBox check_DangNhap;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView btn_ResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        buttonSignUp = findViewById(R.id.btn_sign_up);
        buttonSignIn = findViewById(R.id.btn_sign_in);
        editTextEmail = findViewById(R.id.email_signin);
        editTextPassword = findViewById(R.id.pass_signin);
        check_DangNhap = findViewById(R.id.check_DangNhap);
        btn_ResetPassword = findViewById(R.id.btn_Forgot);

        firebaseAuth = FirebaseAuth.getInstance();
        Intent i = getIntent();
        initReferences();
        editTextEmail.setText(sharedPreferences.getString("email", ""));

        editTextPassword.setText(sharedPreferences.getString("pass", ""));

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean test = true;
                if (editTextEmail.getText().toString().isEmpty()) {
                    editTextEmail.setError("Vui lòng nhập dữ liệu!!!");
                    test = false;
                }
                if (editTextPassword.getText().toString().isEmpty()) {
                    editTextPassword.setError("Vui lòng nhập dữ liệu!!!");
                    test = false;
                }
                if (test == true) {
                    DangNhap();
                }
            }
        });


        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        btn_ResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, ResetPassword.class);
                startActivity(intent);
            }
        });
    }

    private void DangNhap() {

        boolean test = true;
        if (editTextEmail.getText().toString().isEmpty()) {
            editTextEmail.setError("Vui lòng nhập mail!!!");
            test = false;
        }
        if (editTextPassword.getText().toString().isEmpty()) {
            editTextPassword.setError("Vui lòng nhập mật khẩu!!!");
            test = false;
        }
        if (test == true) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(SignInActivity.this);
            FirebaseAuth.getInstance().fetchSignInMethodsForEmail(editTextEmail.getText().toString()).addOnSuccessListener(new OnSuccessListener<SignInMethodQueryResult>() {
                @Override
                public void onSuccess(SignInMethodQueryResult signInMethodQueryResult) {
                    boolean isNull = signInMethodQueryResult.getSignInMethods().isEmpty();
                    if (isNull) {

                        dialog.setMessage("Tài khoản không tồn tại");
                        dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }

                        });
                        AlertDialog a = dialog.create();
                        a.show();
                    } else {
                        firebaseAuth.signInWithEmailAndPassword(editTextEmail.getText().toString(), editTextPassword.getText().toString())
                               .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                   @Override
                                   public void onSuccess(AuthResult authResult) {
                                       if (check_DangNhap.isChecked())
                        {
                            editor.putString("email", editTextEmail.getText().toString());
                            editor.putString("pass", editTextPassword.getText().toString());
                            editor.commit();
                        }
                        else {
                            editor.putString("email","");
                            editor.putString("pass","");
                            editor.commit();
                        }
                        Intent intent=new Intent(SignInActivity.this,MainActivity.class);
                        startActivity(intent);
                                   }
                               })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialog.setMessage("Mật khẩu không đúng!");
                                        dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }

                                        });
                                        AlertDialog a = dialog.create();
                                        a.show();
                                    }
                                });
                    }

                }
            });
        }
    }




    private void initReferences()
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }

}