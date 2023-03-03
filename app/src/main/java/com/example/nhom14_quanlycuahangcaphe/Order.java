package com.example.nhom14_quanlycuahangcaphe;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {

    String idOrder;
    int total;
    ArrayList<FoodBasket> foods;
    String date;
    String nameTable;
    String idUserOrder;
    String time;
    public  Order(){}

    public Order(String idOrder, String date,Table table, String idUserOrder) {
        this.idOrder = idOrder;
        this.total = table.totalPrice;
        this.foods = table.foods;
        this.date = date;
        this.nameTable = table.number_table;
        this.idUserOrder = idUserOrder;
        this.time=table.time;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<FoodBasket> getFoods() {
        return foods;
    }

    public void setFoodBaskets(ArrayList<FoodBasket> foods) {
        this.foods = foods;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public String getIdUserOrder() {
        return idUserOrder;
    }

    public void setIdUserOrder(String idUserOrder) {
        this.idUserOrder = idUserOrder;
    }
    public void setFoods(ArrayList<FoodBasket> foods) {
        this.foods = foods;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }




}
