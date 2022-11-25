package com.latutslab_00000053580.foodro;

public class OrderDetail {
    private int id;
    private Food food;
    private Status status;
    private int quantity;

    public enum Status{
        PREPARING,
        READY,
        COMPLETED
    }

    public OrderDetail(int id, Food food, int quantity){
        this.id = id;
        this.food = food;
        this.status = Status.PREPARING;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public Food getFood() {
        return food;
    }

    public Status getStatus() {
        return status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public int calculateTotalPayment(int quantity, int price){
        return quantity*price;
    }
}


