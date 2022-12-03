package com.latutslab_00000053580.foodro_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.latutslab_00000053580.foodro.APIHandler;
import com.latutslab_00000053580.foodro_home.R;
import com.squareup.picasso.Picasso;

public class MenuUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0) + 1;
        String name = intent.getStringExtra("name");
        String image = intent.getStringExtra("image");

        TextView menuResto = (TextView) findViewById(R.id.menuResto);
        menuResto.setText(name);

        ImageView menuRestoGambar = (ImageView) findViewById(R.id.menuRestoGambar);
        Picasso.get().load(image).into(menuRestoGambar);

        RecyclerView menuUserRV = (RecyclerView) findViewById(R.id.menuUserRV);

        APIHandler handler = new APIHandler();
        handler.getFoodByMerchant(getApplicationContext(), id, menuUserRV);
    }
}