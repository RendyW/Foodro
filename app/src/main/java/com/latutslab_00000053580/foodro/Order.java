package com.latutslab_00000053580.foodro;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Order {
    String id;
    ArrayList<Food> foodArrayList;
    User user;
    String statusID;

    public Order(String id, ArrayList<Food> foodArrayList, User user, String statusID) {
        this.id = id;
        this.foodArrayList = foodArrayList;
        this.user = user;
        this.statusID = statusID;
    }

    public String getId() {
        return id;
    }

    public String getAllFood(){
        StringBuilder stringBuilder = new StringBuilder();

        for(Food food: foodArrayList){
            stringBuilder.append(food.foodToString());
        }

        return stringBuilder.toString();
    }

    public double getTotal(){
        double total = 0;

        for(Food food: foodArrayList){
            total += (double) food.getPrice() * food.getQuantity();
        }

        return total;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Food> getFoodArrayList() {
        return foodArrayList;
    }

    public void setFoodArrayList(ArrayList<Food> foodArrayList) {
        this.foodArrayList = foodArrayList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatusID() {
        return statusID;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }
}
