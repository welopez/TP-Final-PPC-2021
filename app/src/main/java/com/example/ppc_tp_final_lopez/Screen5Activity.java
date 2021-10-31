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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.FileOutputStream;

public class Screen5Activity extends AppCompatActivity {

    ImageView image_covid;
    Button button_send;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen5);
        image_covid = findViewById(R.id.imageCovid);
        button_send = findViewById(R.id.buttonSend);
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                sendImage5(image_covid);
            }
        });
        queue = Volley.newRequestQueue(this);

        Double riesgoRec = getIntent().getExtras().getDouble("riesgoRec");
        Double riesgoProg = getIntent().getExtras().getDouble("riesgoProg");
        Boolean esquema = getIntent().getExtras().getBoolean("esquema");

        cargarImagen(riesgoRec, riesgoProg, esquema);

    }

    private void cargarImagen(double riesgoRecurrente, double riesgoProgreso, boolean esquema){
        String url = "http://ppc2021.edit.com.ar/service/api/imagen/";
        ImageRequest request = new ImageRequest(url + riesgoRecurrente + "/" + riesgoProgreso + "/" + esquema,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        image_covid.setImageBitmap((Bitmap) response);

                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        //image_covid.setImageResource(R.drawable.covid);
                        Toast.makeText(Screen5Activity.this, "Error en respuesta: "+ url + riesgoRecurrente + "/" + riesgoProgreso + "/" + esquema + " -->" + error.getMessage(), Toast.LENGTH_LONG).show();
                    }});
        queue.add(request);

    }

    protected void sendImage5(ImageView image){
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        BitmapDrawable drawable = (BitmapDrawable)image.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        File f = new File(getExternalCacheDir()+"/"+getResources().getString(R.string.app_name)+".png");
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

        }catch (Exception e){
            throw new RuntimeException();
        }
        startActivity(Intent.createChooser(shareintent, "Share Image"));
    }
}