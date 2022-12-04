package com.latutslab_00000053580.foodro_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.latutslab_00000053580.foodro.APIHandler;
import com.latutslab_00000053580.sqlite.DbUser;

public class MerchantAddMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_add_menu);

        EditText addName = (EditText) findViewById(R.id.addName);
        EditText addPrice = (EditText) findViewById(R.id.addPrice);
        ImageView addImage = (ImageView) findViewById(R.id.addImage);
        Button btnAdd = (Button) findViewById(R.id.btnAddMenu);

        DbUser dbUser = new DbUser(getApplicationContext());
        dbUser.open();
        int merchantID = dbUser.getID();
        dbUser.close();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO ADD IMAGE
                APIHandler handler = new APIHandler();
                handler.createFood(getApplicationContext(),
                        addName.getText().toString(),
                        Integer.parseInt(addPrice.getText().toString()),
                        "URLIMAGE.png", merchantID
                );

                Toast.makeText(getApplicationContext(), "Item Successfuly added", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}