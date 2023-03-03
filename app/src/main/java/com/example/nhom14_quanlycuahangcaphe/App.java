package com.example.nhom14_quanlycuahangcaphe;


import android.app.Application;

import java.util.ArrayList;

    public class App extends Application{

        public static Table table;
        public static Basket basket;
        public static Basket basket_order;
        public static Basket getBasket() {
            return basket;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            if (basket==null)
            {
                basket=new Basket();
            }
            if (table==null)
                table=new Table();
            if (basket_order==null)
                basket_order=new Basket();
        }


    }
