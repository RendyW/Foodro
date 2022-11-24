package com.latutslab_00000053580.foodro_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.latutslab_00000053580.foodro.User;
import com.latutslab_00000053580.sqlite.DbUser;

public class Welcome_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        Button ButtonGET = findViewById(R.id.buttonGTS);
        ButtonGET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbUser dbUser = new DbUser(getApplicationContext());
//                User user = dbUser.Authenticate();

                dbUser.open();
                dbUser.logout();
                if(dbUser.getUser().getCount() > 0){
                    startActivity(new Intent(Welcome_Screen.this, MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                } else {
                    startActivity(new Intent(Welcome_Screen.this, Account_Setup.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        });
    }
}