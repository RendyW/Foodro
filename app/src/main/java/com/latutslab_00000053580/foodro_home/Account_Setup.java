package com.latutslab_00000053580.foodro_home;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.latutslab_00000053580.foodro.User;
import com.latutslab_00000053580.sqlite.DbUser;

public class Account_Setup extends AppCompatActivity {

    EditText inputPass, inputUsername;
    CheckBox showpassword;
    APIHandler handler = new APIHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setup);

        inputPass = findViewById(R.id.inputPass);
        inputUsername = findViewById(R.id.inputUsername);
        showpassword = findViewById(R.id.showpassword);

        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if(b){
                    inputPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    inputPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        Button btnsignup = findViewById(R.id.signupBtn);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Account_Setup.this, SignUp.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        Button btnLogin = findViewById(R.id.loginBtn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = handler.login(getBaseContext(),inputUsername.getText().toString(), inputPass.getText().toString());

                DbUser dbUser = new DbUser(Account_Setup.this);
                dbUser.open();
                dbUser.addUser(user);



//                Cursor cursor = dbUser.getUser();
//                cursor.moveToFirst();
//                Log.e("User", cursor.getString(1));
            }
        });
    }
    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}