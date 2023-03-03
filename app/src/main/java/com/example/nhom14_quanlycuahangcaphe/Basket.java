package com.example.nhom14_quanlycuahangcaphe;

import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Basket implements Serializable {
    public HashMap<String, FoodBasket> foods;
    public int totalPrice;
    public int totalItem;
    public Basket() {
        foods = new HashMap<>();
        totalPrice = 0;
        totalItem = 0;
    }
    public void addFood(FoodBasket food) {
        if (foods.get(food.foodKey)==null)
        {
            foods.put(food.getFoodKey(), food);

        }
        else foods.get(food.foodKey).quantity+= food.quantity;

    }
    public FoodBasket getFood(String key) {
        return foods.get(key);
    }
    public void calculateBasket() {
        totalPrice = 0;
        totalItem = 0;
        for (FoodBasket foodBasket : foods.values()) {
            totalPrice += (foodBasket.price * foodBasket.quantity);
            totalItem += foodBasket.quantity;
        }
    }
    public void setBasket(ArrayList<FoodBasket> list) {
        for (FoodBasket foodBasket:list) {
            foods.put(foodBasket.foodKey,foodBasket);
        }
    }
    public String getTotalPrice() {
        return totalPrice + " VND";
    }
    public int getTotalItem() {
        return totalItem;
    }
    @Override
    public String toString() {
        return "Basket{" +
                "foods=" + foods +
                ", totalPrice=" + totalPrice +
                ", totalItem=" + totalItem +
                '}';
    }
}

