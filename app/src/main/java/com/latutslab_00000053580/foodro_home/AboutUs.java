package com.latutslab_00000053580.foodro_home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_about_us);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}