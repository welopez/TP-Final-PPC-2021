package com.example.ppc_tp_final_lopez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class Screen4Activity extends AppCompatActivity {

    private EditText ed1;
    private EditText ed2;
    private EditText ed3;
    private RadioButton rad1;
    private RadioButton rad2;
    private Boolean selected_radiob;

    Context context = this;
    SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);

        //Para guardar en memoria no volatil
        sharedPrefs = getSharedPreferences("ArchivoSP", context.MODE_PRIVATE);


        ed1 = (EditText)findViewById(R.id.pacienteEditText);
        ed2 = (EditText)findViewById(R.id.riesgoEditText);
        ed3 = (EditText)findViewById(R.id.riesgoRecurrenteEditText);
        rad1 = (RadioButton)findViewById(R.id.radioButtonYes);
        rad2 = (RadioButton)findViewById(R.id.radioButtonNo);

        ed1.setText(sharedPrefs.getString("Paciente", ""));
        ed2.setText(sharedPrefs.getString("RiesgoProg", ""));
        ed3.setText(sharedPrefs.getString("RiesgoRec", ""));
        if(sharedPrefs.getBoolean("Esquema", false) == true){
            rad1.setChecked(true);
        }else{
            rad2.setChecked(true);
        }


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

        Double riesgoProg = Double.parseDouble(ed2.getText().toString());
        Double riesgoRec = Double.parseDouble(ed3.getText().toString());
        Boolean esquema = rad1.isChecked();

        //Guardamos datos en memoria no volatil
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("Paciente", ed1.getText().toString());
        editor.putString("RiesgoProg", ed2.getText().toString());
        editor.putString("RiesgoRec", ed3.getText().toString());
        editor.putBoolean("Esquema", esquema);
        editor.commit();

        // Pasamos los datos necesarios para la siguiente activity.
        Bundle extras = new Bundle();
        extras.putDouble("riesgoProg", riesgoProg);
        extras.putDouble("riesgoRec", riesgoRec);
        extras.putBoolean("esquema", esquema);

        Intent goact5 = new Intent(this, Screen5Activity.class);
        goact5.putExtras(extras);
        startActivity(goact5);
    }
}