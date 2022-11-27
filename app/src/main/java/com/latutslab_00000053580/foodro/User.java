package com.latutslab_00000053580.foodro;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private int role;
    private int active;

    public User(int id, String firstname, String lastname, String email, int role, int active){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public int getRole() {
        return role;
    }

    public int getActive() {
        return active;
    }

}
