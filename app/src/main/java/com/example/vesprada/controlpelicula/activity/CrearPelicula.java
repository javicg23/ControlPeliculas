package com.example.vesprada.controlpelicula.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.vesprada.controlpelicula.R;
import com.example.vesprada.controlpelicula.modelo.Actor;
import com.example.vesprada.controlpelicula.modelo.Director;
import com.example.vesprada.controlpelicula.modelo.Pelicula;
import com.example.vesprada.controlpelicula.modelo.Productor;

public class CrearPelicula extends AppCompatActivity {

    private EditText tvPeliNombre;
    private EditText tvPeliSinopsis;
    private EditText tvPeliDuracion;
    private EditText tvPeliAnyo;
    private EditText tvPeliPuntuacion;
    private EditText tvPeliActor;
    private EditText tvPeliDirector;
    private EditText tvPeliProductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearpeliculas);

        tvPeliNombre = (EditText) findViewById(R.id.etCrearNombre);
        tvPeliSinopsis = (EditText) findViewById(R.id.etCrearSinopsis);
        tvPeliDuracion = (EditText) findViewById(R.id.etCrearDuracion);
        tvPeliAnyo = (EditText) findViewById(R.id.etCrearAnyo);
        tvPeliPuntuacion = (EditText) findViewById(R.id.etCrearPuntuacion);
        tvPeliActor = (EditText) findViewById(R.id.etCrearActor);
        tvPeliDirector = (EditText) findViewById(R.id.etCrearDirector);
        tvPeliProductor = (EditText) findViewById(R.id.etCrearProductor);

        Pelicula nuevapelicula = new Pelicula();
        nuevapelicula.nombre = tvPeliNombre.getText().toString();
        nuevapelicula.sinopsis = tvPeliSinopsis.getText().toString();
        nuevapelicula.duracion = Integer.parseInt(tvPeliDuracion.getText().toString());
        nuevapelicula.anyo = Integer.parseInt(tvPeliAnyo.getText().toString());
        nuevapelicula.puntuacion = Float.parseFloat(tvPeliPuntuacion.getText().toString());

        Actor nuevoActor = new Actor();
        nuevoActor.nombre_completo = tvPeliActor.getText().toString();

        Director nuevoDirector = new Director();
        nuevoDirector.nombre_completo = tvPeliDirector.getText().toString();

        Productor nuevoProductor = new Productor();
        nuevoProductor.nombre = tvPeliProductor.getText().toString();

    }
}
