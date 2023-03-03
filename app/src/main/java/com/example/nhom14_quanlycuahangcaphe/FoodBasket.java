package com.example.nhom14_quanlycuahangcaphe;

public class FoodBasket extends Food {
    String foodKey;
    int quantity;
    String name;
    int price;
    int sum;
    String ghichu;


    public FoodBasket() {
    }


    public FoodBasket(String foodKey, String name, int price, int quantity, int sum, String ghichu) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sum = sum;
        this.foodKey=foodKey;
        this.ghichu=ghichu;
    }

    public FoodBasket(Food food, int quantity, int sum){
        this.name = food.name;
        this.price=food.price;
        this.quantity = quantity;
        this.sum = sum;
        this.foodKey=food.foodKey;
    }


    public void increase() {
        quantity++;
        sum = price * quantity;
    }

    public void decrease() {
        if (quantity > 0) {
            quantity--;
            sum = price * quantity;
        }
    }



    public double getSum() {
        return price * quantity;
    }

    public int getQuantity(){
        return quantity;
    }


    @Override
    public String getFoodKey() {
        return foodKey;
    }

    @Override
    public void setFoodKey(String foodKey) {
        this.foodKey = foodKey;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }
    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}