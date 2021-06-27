package com.example.ppc_tp3_isea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
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

        InputFilter limitFilter = new MinMaxInputFilter(1, 10);
        ed2.setFilters(new InputFilter[] { limitFilter });
        ed3.setFilters(new InputFilter[] { limitFilter });

    }

    public void calculateRisk(View view){

        if (rad1.isChecked()){
            selected_radiob = true;
        } else if (rad2.isChecked()){
            selected_radiob = false;
        }

        Bundle extras = new Bundle(); // Pasamos los datos necesarios para la siguiente activity.
        extras.putDouble("riesgoProg", Double.parseDouble(ed2.getText().toString()));
        extras.putDouble("riesgoRec", Double.parseDouble(ed3.getText().toString()));
        extras.putBoolean("esquema", rad1.isChecked());

        Intent goact5 = new Intent(this, Screen5Activity.class);
        goact5.putExtras(extras);
        startActivity(goact5);
    }
}