package com.example.nhom14_quanlycuahangcaphe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextView btn_Reset, btn_Done, tvtotalItems;
    App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        toolbar = findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);

        btn_Reset = findViewById(R.id.btn_Reset);
        btn_Done = findViewById(R.id.btn_Done);

        btn_Reset.setOnClickListener(this);
        btn_Done.setOnClickListener(this);
        tvtotalItems = findViewById(R.id.tvTotalItems);
        app.basket = new Basket();
        tvtotalItems.setText(App.basket.foods.size() + "");

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_Done:
                BasketFragment dialog = new BasketFragment(new ArrayList<>(app.basket.foods.values()));
                dialog.show(getSupportFragmentManager(), "basket_dialog");
                break;
            case R.id.btn_Reset:
                App.basket = new Basket();
                upDateBasket();
                break;
        }


    }

    public void upDateBasket() {
        tvtotalItems.setText(App.basket.foods.size() + "");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mnu_search, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnu_Search) {
            Fragment fragment;
            fragment = new SearchFragment();

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.nav, fragment);
            ft.commit();
        }

        return super.onOptionsItemSelected(item);
    }

}