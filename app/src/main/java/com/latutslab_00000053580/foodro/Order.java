package com.latutslab_00000053580.foodro;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    private int id;
    private User customer;
    private String dateTime;
    private ArrayList<OrderDetail> orderDetails;

    public Order(int id, User customer, String dateTime, ArrayList<OrderDetail> orderDetails){
        this.id = id;
        this.customer = customer;
        this.dateTime = dateTime;
        this.orderDetails = orderDetails;
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

    public ArrayList<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public int getStatus(){
        return this.getOrderDetails().get(0).getStatus();
    }

    public String getStatusString(){
        int status = getStatus();
        switch(status){
            case 1:
                return "Prepare Food";
            case 2:
                return "Food Ready To Pickup";
            case 3:
                return "Order Finished";
            default:
                return null;
        }
    }

    public String getOrderDetailStr(){

        StringBuilder orderStr = new StringBuilder();
        for(int i=0; i<orderDetails.size(); i++){
            String orderDetail = String.format("%dx %s \n", orderDetails.get(i).getQuantity(), orderDetails.get(i).getFood().getName());
            orderStr.append(orderDetail);
        }

        return orderStr.toString();
    }

    public int getOrderDetailTotal(){

        int total=0;
        for(int i=0; i<orderDetails.size(); i++){
            total += orderDetails.get(i).calculateTotalPayment();
        }

        return total;
    }
}


