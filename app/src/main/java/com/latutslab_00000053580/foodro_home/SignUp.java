package com.latutslab_00000053580.foodro_home;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    AppCompatButton signupBTN;

    AlertDialog.Builder builderdialog;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupBTN = (AppCompatButton) findViewById(R.id.signupBtn2page);
        signupBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(R.layout.custom_dialog);
            }
        });
    }

    private void showAlertDialog(int _dialog) {
        builderdialog = new AlertDialog.Builder(this);
        View layoutview = getLayoutInflater().inflate(_dialog, null);

        AppCompatButton dialogbutton = layoutview.findViewById(R.id.btnstatusok);
        builderdialog.setView(layoutview);
        alertDialog = builderdialog.create();
        alertDialog.show();

        dialogbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Data successfully saved", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();

                startActivity(new Intent(SignUp.this, Account_Setup.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
}