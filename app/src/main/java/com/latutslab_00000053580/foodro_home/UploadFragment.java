package com.latutslab_00000053580.foodro_home;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.latutslab_00000053580.foodro.APIHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UploadFragment extends Fragment {

    ImageView imgPreview;
    Button btnUpload, btnPhoto;
    Bitmap photo;
    APIHandler handler = new APIHandler();
    private final int IMG_REQUEST = 1111;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload, container, false);

        imgPreview = view.findViewById(R.id.imgPreview);
        btnPhoto = view.findViewById(R.id.btnPhoto);
        btnUpload = view.findViewById(R.id.btnUpload);
//        btnUpload.setEnabled(false);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMG_REQUEST);
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(photo!=null){
                    //TODO: seting mau ngapain klo tombol upload dipencet
//                    handler.createOrder(getContext(), userid, foods[], quantity[], encodeImage(photo));

                }
            }
        });

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();
            try{
                photo = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                imgPreview.setImageBitmap(photo);
                btnUpload.setEnabled(true);
            }catch(IOException e){
                Log.e("UPLOADIMG",  e.toString());
            }
        }
    }
    public String encodeImage(Bitmap bm){
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, ba);
        byte[] imgByte = ba.toByteArray();
        String encode = Base64.encodeToString(imgByte,Base64.DEFAULT);
        return encode;
    }
}