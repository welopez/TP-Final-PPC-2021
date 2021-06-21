package com.example.ppc_tp3_isea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.android.volley.RequestQueue;

public class Screen4Activity extends AppCompatActivity {

    private EditText ed1;
    private EditText ed2;
    private EditText ed3;
    private RadioButton rad1;
    private RadioButton rad2;
    private Boolean selected_radiob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);

        ed1 = (EditText)findViewById(R.id.pacienteEditText);
        ed2 = (EditText)findViewById(R.id.riesgoEditText);
        ed3 = (EditText)findViewById(R.id.riesgoRecurrenteEditText);
        rad1 = (RadioButton)findViewById(R.id.radioButtonYes);
        rad2 = (RadioButton)findViewById(R.id.radioButtonNo);
    }

    public void calculateRisk(View view){

        if (rad1.isChecked()){
            selected_radiob = true;
        } else if (rad2.isChecked()){
            selected_radiob = false;
        }
        Intent goact5 = new Intent(this, Screen5Activity.class);
        startActivity(goact5);
    }
}