package com.example.nhom14_quanlycuahangcaphe;

import java.io.Serializable;

public class Food implements Serializable{

    String name;
    String image;
    int price;
    String foodKey;


    String foodType;


    public Food() {
    }

    public Food(String name, int price, String foodKey) {
        this.name = name;
        this.price = price;
        this.foodKey = foodKey;
    }
    public Food(String name, String image, int price, String foodKey, String foodType) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.foodKey = foodKey;
        this.foodType = foodType;
    }
    public String getFoodType() {
        return foodType;
    }


    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFoodKey() {
        return foodKey;
    }

    public void setFoodKey(String foodKey) {
        this.foodKey = foodKey;
    }



    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", image=" + image +
                ", price=" + price +
                ", foodKey='" + foodKey + '\'' +
                '}';
    }
}