package com.latutslab_00000053580.foodro;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    private int id;
    private User customer;
    private String dateTime;
    private OrderDetail[] orderDetails;

    public Order(int id, User customer, String dateTime, ArrayList<Food> foods, int[] quantity){
        this.id = id;
        this.customer = customer;
        this.dateTime = dateTime;
        OrderDetail[] orderDetails1 = new OrderDetail[foods.size()];

        for(int i = 0; i < foods.size(); i++){
            orderDetails1[i] = new OrderDetail(id, foods.get(i), quantity[i]);
        }
        this.orderDetails = orderDetails1;
    }

    public int getId() {
        return id;
    }

    public User getCustomer() {
        return customer;
    }

    public String getDateTime() {
        return dateTime;
    }

    public OrderDetail[] getOrderDetails() {
        return orderDetails;
    }

    public String getOrderDetailStr(){

        StringBuilder orderStr = new StringBuilder();
        for(int i=0; i<orderDetails.length; i++){
            String orderDetail = String.format("%dx %s \n", orderDetails[i].getQuantity(), orderDetails[i].getFood().getName());
            orderStr.append(orderDetail);
        }

        return orderStr.toString();
    }

    public int getOrderDetailTotal(){

        int total=0;
        for(int i=0; i<orderDetails.length; i++){
            total += orderDetails[i].calculateTotalPayment();
        }

        return total;
    }
}


