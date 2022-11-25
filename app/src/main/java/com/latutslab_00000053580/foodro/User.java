package com.latutslab_00000053580.foodro;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
    private int active;
    public enum Role{
        CUSTOMER,
        MERCHANT,
        ADMIN
    }

    public User(int id, String firstname, String lastname, String email, Role role, int active){
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

    public Role getRole() {
        return role;
    }

    public int getActive() {
        return active;
    }

}
