package com.example.ppc_tp_final_lopez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileOutputStream;

public class Screen3Activity extends AppCompatActivity {

    private ImageView image1;
    private Button button1;
    private ImageView image2;
    private Button button2;
    private ImageView image3;
    private Button button3;
    private ImageView image4;
    private Button button4;
    private ImageView image5;
    private Button button5;
    private ImageView image6;
    private Button button6;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);
        image1 = findViewById(R.id.Photo1);
        button1 = findViewById(R.id.button_Photo1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendImage(image1);
            }
        });
        image2 = findViewById(R.id.Photo2);
        button2 = findViewById(R.id.button_Photo2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendImage(image2);
            }
        });
        image3 = findViewById(R.id.Photo3);
        button3 = findViewById(R.id.button_Photo3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendImage(image3);
            }
        });
        image4 = findViewById(R.id.Photo4);
        button4 = findViewById(R.id.button_Photo4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendImage(image4);
            }
        });
        image5 = findViewById(R.id.Photo5);
        button5 = findViewById(R.id.button_Photo5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendImage(image5);
            }
        });
        image6 = findViewById(R.id.Photo6);
        button6 = findViewById(R.id.button_Photo6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendImage(image6);
            }
        });
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.page_info);
    }

    protected void sendImage(ImageView image) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        File f = new File(getExternalCacheDir() + "/" + getResources().getString(R.string.app_name) + ".png");
        Intent shareintent;

        try {
            FileOutputStream outputStream = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

            outputStream.flush();
            outputStream.close();

            shareintent = new Intent(Intent.ACTION_SEND);
            shareintent.setType("image/*");
            shareintent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
            shareintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        } catch (Exception e) {
            throw new RuntimeException();
        }
        startActivity(Intent.createChooser(shareintent, "Share Image"));
    }
}