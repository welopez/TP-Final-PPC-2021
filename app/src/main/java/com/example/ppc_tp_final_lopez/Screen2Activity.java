package com.example.ppc_tp_final_lopez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

public class Screen2Activity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private TextView textView2;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        textView2 = (TextView) findViewById(R.id.textView2);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.page_riesgo);

        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(this);
        String url = "http://ppc2021.edit.com.ar/service/api/info";

        if (checkOnlineState() == true) {
            // Request a JSON response from the provided URL.
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            textView2.setText(response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    textView2.setText("Error en respuesta: " + url + " -->" + error.getMessage());
                }
            });

            // Add the request to the RequestQueue.
            queue.add(jsonObjectRequest);
        } else {
            textView2.setText("No hay conexión a internet.");
        }
    }

    public void goScreen4Calculate(View view) {
        Intent act4 = new Intent(this, Screen4Activity.class);
        startActivity(act4);
    }

    public boolean checkOnlineState() {
        ConnectivityManager CManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = CManager.getActiveNetworkInfo();
        if (nInfo != null && nInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
}