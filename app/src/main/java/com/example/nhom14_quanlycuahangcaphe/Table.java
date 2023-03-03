package com.example.nhom14_quanlycuahangcaphe;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;

public class Table implements Serializable {
    String time;
    String number_table;
    int totalPrice;
    ArrayList<FoodBasket> foods;
    String floor;
    String note;


    public Table() {
    }

    public Table(String number_table, String floor, String time, int totalPrice, ArrayList<FoodBasket> foods) {
        this.time = time;
        this.number_table = number_table;
        this.totalPrice = totalPrice;
        this.floor = floor;
        this.foods = foods;
    }

    public Table(Table table, String time, ArrayList<FoodBasket> foods, int totalPrice, String note) {
        this.number_table = table.number_table;
        this.floor = table.floor;
        this.time = time;
        this.totalPrice = totalPrice;
        this.foods = foods;
        this.note = note;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumber_table() {
        return number_table;
    }

    public void setNumber_table(String name_table) {
        this.number_table = name_table;
    }

    public int getTotalPrice() {

        return getTotal();

    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public int getTotal() {
        int s = 0;
        if (foods.size() != 0) {
            for (FoodBasket foodBasket : foods) {
                s += (foodBasket.price * foodBasket.quantity);
            }
        }
        return s;

    }
}


