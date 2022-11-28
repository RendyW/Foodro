package com.latutslab_00000053580.foodro;

public class Food {
    private int id;
    private String name;
    private int price;
    private String image;
    private User merchant;
    private int listed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getMerchant() {
        return merchant;
    }

    public void setMerchant(User merchant) {
        this.merchant = merchant;
    }

    public int getListed() {
        return listed;
    }

    public void setListed(int listed) {
        this.listed = listed;
    }
}
