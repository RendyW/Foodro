package com.latutslab_00000053580.foodro;

import java.util.ArrayList;

public class User {

    String username;
    String imageURL;
    int userID;

    public User(String username, String imageURL, int userID) {
        this.username = username;
        this.imageURL = imageURL;
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return username;
    }

    public int getUserID() {
        return userID;
    }
}
