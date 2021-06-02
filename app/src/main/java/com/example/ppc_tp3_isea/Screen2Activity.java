package com.example.ppc_tp3_isea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Screen2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
    }

    public void goScreen4Calculate(View view){
        Intent act4 = new Intent(this, Screen4Activity.class);
        startActivity(act4);
    }
}