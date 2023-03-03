package com.example.nhom14_quanlycuahangcaphe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class SignUpActivity extends AppCompatActivity {
    AppBarConfiguration appBarConfiguration;
    NavController navController;
    Toolbar toolbar;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        navController = Navigation.findNavController(this,R.id.navsignin);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.personRegisterFragment,
                R.id.accountRegisterFragment)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController,
                appBarConfiguration);
        NavigationUI.setupWithNavController( toolbar,navController);


    }
}