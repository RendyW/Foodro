package com.latutslab_00000053580.foodro;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private User customer;
    private LocalDateTime dateTime;
    private OrderDetail[] orderDetails;

    public Order(int id, User customer, LocalDateTime dateTime, Food[] foods, int[] quantity){
        this.id = id;
        this.customer = customer;
        this.dateTime = dateTime;
        OrderDetail[] orderDetails1 = new OrderDetail[foods.length];

        for(int i = 0; i < foods.length; i++){
            orderDetails1[i] = new OrderDetail(id, foods[i], quantity[i]);
        }
        this.orderDetails = orderDetails1;
    }

    public int getId() {
        return id;
    }

    public User getCustomer() {
        return customer;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public OrderDetail[] getOrderDetails() {
        return orderDetails;
    }
}


