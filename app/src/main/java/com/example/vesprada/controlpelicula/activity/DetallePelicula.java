package com.example.vesprada.controlpelicula.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.vesprada.controlpelicula.R;
import com.example.vesprada.controlpelicula.modelo.Pelicula;

public class DetallePelicula extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallepelicula);
        /*Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Pelicula value = extras.getClass(pelicula);
        }*/
    }
}