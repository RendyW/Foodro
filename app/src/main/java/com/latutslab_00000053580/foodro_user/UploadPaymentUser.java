package com.latutslab_00000053580.foodro_user;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.latutslab_00000053580.foodro_home.R;

public class UploadPaymentUser extends AppCompatActivity {

    private final int GALLERY_REQ_CODE = 1000;

    ImageView uploadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_payment_user);

        uploadImage = (ImageView)findViewById(R.id.uploadImage);
        Button btnUpload = (Button)findViewById(R.id.btnOpenGallery);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, GALLERY_REQ_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){

            if(requestCode==GALLERY_REQ_CODE){
                // for gallery

                uploadImage.setImageURI(data.getData());

            }
        }
    }
}