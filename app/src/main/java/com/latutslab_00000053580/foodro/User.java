package com.latutslab_00000053580.foodro;

import java.util.ArrayList;

public class User {

    String username;
    int userID;

    public User(String name, int userID) {
        this.username = name;
        this.userID = userID;
    }

    public String getName() {
        return username;
    }

    public int getUserID() {
        return userID;
    }
}
