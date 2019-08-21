package com.workshop.android.java;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class CameraActivity extends AppCompatActivity {
    private Context context = this;

    private Button buttonCamera;
    private ImageView previewPhoto;

    private int ID_PEMANGGIL = 1;
    private int ID_PERMISSION_CAMERA = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        buttonCamera = (Button) findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fungsi panggil camera
                if(checkPermissionCamera()){
                    ambilImageDariCamera();
                }
            }
        });

        previewPhoto = (ImageView) findViewById(R.id.previewPhoto);
    }

    private boolean checkPermissionCamera(){
        int currentAPIVersion = Build.VERSION.SDK_INT;

        if(currentAPIVersion >= Build.VERSION_CODES.M){
            //cek runtime permission untuk camera
            if(checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED){
                //belum ada ijin akses
                requestPermissions(new String[] { Manifest.permission.CAMERA },
                        ID_PERMISSION_CAMERA);
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == ID_PERMISSION_CAMERA){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                ambilImageDariCamera();
            }
            else{
                Toast.makeText(context,
                        "Anda harus memberikan ijin akses camera!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    //ini utk memanggil intent camera
    private void ambilImageDariCamera(){
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, ID_PEMANGGIL);
    }

    //untuk meng-handle balikan dari intent camera dsb
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == ID_PEMANGGIL){
                tampilkanImageDariCamera(data);
            }
        }
    }

    private void tampilkanImageDariCamera(Intent data){
        Bitmap bitmap = null;

        if(data != null){
            //konversi menjadi bitmap
            bitmap = (Bitmap) data.getExtras().get("data");

            //tampilkan kedalam ImageView
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, outputStream);
            previewPhoto.setImageBitmap(bitmap);


        }
    }
}
