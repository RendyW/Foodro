package com.latutslab_00000053580.foodro_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.latutslab_00000053580.foodro.APIHandler;

public class MerchantEditMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_edit_menu);

        EditText editName = (EditText) findViewById(R.id.addName);
        EditText editPrice = (EditText) findViewById(R.id.addPrice);
        ImageView editImage = (ImageView) findViewById(R.id.addImage);
        Button btnConfirm = (Button) findViewById(R.id.btnImage);

        Intent intent = getIntent();
        editName.setText(intent.getStringExtra("name"));
        editPrice.setText(String.valueOf(intent.getIntExtra("price", 0)));

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIHandler handler = new APIHandler();

                //todo update food
            }
        });
    }
}