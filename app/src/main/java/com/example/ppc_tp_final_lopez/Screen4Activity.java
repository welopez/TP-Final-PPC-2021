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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Screen4Activity extends AppCompatActivity {

    private EditText ed1;
    private EditText ed2;
    private EditText ed3;
    private RadioButton rad1;
    private RadioButton rad2;
    private Boolean selected_radiob;
    private BottomNavigationView bottomNavigationView;

    Context context = this;
    SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);

        //Para guardar en memoria no volatil
        sharedPrefs = getSharedPreferences("ArchivoSP", context.MODE_PRIVATE);

        iniciarMenuInf();
        ed1 = (EditText) findViewById(R.id.pacienteEditText);
        ed2 = (EditText) findViewById(R.id.riesgoEditText);
        ed3 = (EditText) findViewById(R.id.riesgoRecurrenteEditText);
        rad1 = (RadioButton) findViewById(R.id.radioButtonYes);
        rad2 = (RadioButton) findViewById(R.id.radioButtonNo);

        ed1.setText(sharedPrefs.getString("Paciente", ""));
        ed2.setText(sharedPrefs.getString("RiesgoProg", ""));
        ed3.setText(sharedPrefs.getString("RiesgoRec", ""));
        if (sharedPrefs.getBoolean("Esquema", false) == true) {
            rad1.setChecked(true);
        } else {
            rad2.setChecked(true);
        }


        InputFilter limitFilter = new MinMaxInputFilter(1, 10);
        ed2.setFilters(new InputFilter[]{limitFilter});
        ed3.setFilters(new InputFilter[]{limitFilter});

    }

    public void calculateRisk(View view) {
        //Si los inputs estan cargados...
        if (verificarInputs()){
            if (rad1.isChecked()) {
                selected_radiob = true;
            } else if (rad2.isChecked()) {
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

    private boolean verificarInputs() {
        if(ed1.getText().toString().isEmpty()) {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Ingrese el paciente", Toast.LENGTH_LONG);
            toast1.show();
            return false;
        }else if(ed2.getText().toString().isEmpty()){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Ingrese el riesgo del progreso", Toast.LENGTH_LONG);
            toast1.show();
            return false;

        }else if (ed3.getText().toString().isEmpty()){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Ingrese el riesgo recurrente", Toast.LENGTH_LONG);
            toast1.show();
            return false;

        }else if (!rad1.isChecked() && !rad2.isChecked()){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Seleccione el esquema", Toast.LENGTH_LONG);
            toast1.show();
            return false;

        }else return true;
    }

    private void iniciarMenuInf(){
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        //Seteo el boton inicio como seleccionado
        bottomNavigationView.setSelectedItemId(R.id.page_carga);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.page_inicio:
                    goMain();
                    break;
                case R.id.page_riesgo:
                    goScreen2();
                    break;
                case R.id.page_info:
                    goScreen3();
                    break;
                case R.id.page_carga:
                    break;

                default:
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Icono no encontrado", Toast.LENGTH_LONG);
                    toast1.show();
            }
            return true;
        });
    }

    public void goMain() {
        Intent act1 = new Intent(this, MainActivity.class);
        startActivity(act1);
    }

    public void goScreen2() {
        Intent act2 = new Intent(this, Screen2Activity.class);
        startActivity(act2);
    }

    public void goScreen3() {
        Intent act3 = new Intent(this, Screen3Activity.class);
        startActivity(act3);
    }

    public void goScreen4() {
        Intent act4 = new Intent(this, Screen4Activity.class);
        startActivity(act4);
    }

    public void goScreen5() {
        Intent act5 = new Intent(this, Screen5Activity.class);
        startActivity(act5);
    }
}