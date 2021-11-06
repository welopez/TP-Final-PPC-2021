package com.example.ppc_tp_final_lopez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarMenuInf();
    }

    private void iniciarMenuInf(){
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        //Seteo el boton inicio como seleccionado
        bottomNavigationView.setSelectedItemId(R.id.page_inicio);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
           switch (item.getItemId()){
               case R.id.page_inicio:
                   break;
               case R.id.page_riesgo:
                   goScreen2();
                   break;
               case R.id.page_info:
                   goScreen3();
                   break;
               case R.id.page_carga:
                   goScreen4();
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

    //Metodo para que al volver se seleccione el icono correspondiente en el menu
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        bottomNavigationView.setSelectedItemId(R.id.page_inicio);
    }

    public void goScreen2(View view) {
        Intent act2 = new Intent(this, Screen2Activity.class);
        startActivity(act2);
    }

    public void goScreen3(View view) {
        Intent act3 = new Intent(this, Screen3Activity.class);
        startActivity(act3);
    }

    public void goScreen4(View view) {
        Intent act4 = new Intent(this, Screen4Activity.class);
        startActivity(act4);
    }

    public void goScreen5(View view) {
        Intent act5 = new Intent(this, Screen5Activity.class);
        startActivity(act5);
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