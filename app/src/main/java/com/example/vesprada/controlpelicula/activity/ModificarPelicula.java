package com.example.vesprada.controlpelicula.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.vesprada.controlpelicula.MainActivity;
import com.example.vesprada.controlpelicula.R;
import com.example.vesprada.controlpelicula.dao.ActorDAO;
import com.example.vesprada.controlpelicula.dao.Actor_PeliculaDAO;
import com.example.vesprada.controlpelicula.dao.DirectorDAO;
import com.example.vesprada.controlpelicula.dao.GeneroDAO;
import com.example.vesprada.controlpelicula.dao.PeliculaDAO;
import com.example.vesprada.controlpelicula.dao.ProductorDAO;
import com.example.vesprada.controlpelicula.modelo.Director;
import com.example.vesprada.controlpelicula.modelo.Genero;
import com.example.vesprada.controlpelicula.modelo.Pelicula;
import com.example.vesprada.controlpelicula.modelo.Productor;

public class ModificarPelicula extends AppCompatActivity {

    private EditText etPeliNombreEditar;
    private EditText etPeliSinopsisEditar;
    private EditText etPeliDuracionEditar;
    private EditText etPeliAnyoEditar;
    private EditText etPeliPuntuacionEditar;
    private EditText etPeliActorEditar;
    private EditText etPeliDirectorEditar;
    private EditText etPeliProductorEditar;
    private EditText etPeliGeneroEditar;

    private ImageView ivPortadaEditar;

    private Button btnConfirmarEditar;
    private Button btnAgregarEditar;
    private AppCompatButton btnEstadoVistaEditar;
    private AppCompatButton btnEstadoNoVistaEditar;
    private AppCompatButton btnEstadoPendienteEditar;
    private AppCompatButton btnEstadoFavoritaEditar;

    private PeliculaDAO conectorPelicula = new PeliculaDAO(this);
    private ProductorDAO conectorProductor = new ProductorDAO(this);
    private DirectorDAO conectorDirector = new DirectorDAO(this);
    private ActorDAO conectorActor = new ActorDAO(this);
    private GeneroDAO conectorGenero = new GeneroDAO(this);
    private Actor_PeliculaDAO conectorA_P = new Actor_PeliculaDAO(this);
    private int idPelicula;
    private Pelicula peliculaEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearpeliculas);

        etPeliNombreEditar = (EditText) findViewById(R.id.etEditarNombre);
        etPeliSinopsisEditar = (EditText) findViewById(R.id.etEditarSinopsis);
        etPeliDuracionEditar = (EditText) findViewById(R.id.etEditarDuracion);
        etPeliAnyoEditar = (EditText) findViewById(R.id.etEditarAnyo);
        etPeliPuntuacionEditar = (EditText) findViewById(R.id.etEditarPuntuacion);
        etPeliActorEditar = (EditText) findViewById(R.id.etEditarActor);
        etPeliDirectorEditar = (EditText) findViewById(R.id.etEditarDirector);
        etPeliProductorEditar = (EditText) findViewById(R.id.etEditarProductor);
        etPeliGeneroEditar = (EditText) findViewById(R.id.etEditarGenero);

        ivPortadaEditar = (ImageView) findViewById(R.id.ivPortadaEditar);

        btnConfirmarEditar = (Button) findViewById(R.id.btnConfirmarPeliculaEditar);
        btnAgregarEditar = (Button) findViewById(R.id.btnAgregarActorEditar);
        btnEstadoNoVistaEditar = (AppCompatButton) findViewById(R.id.btnEstadoNoVistaEditar);
        btnEstadoVistaEditar = (AppCompatButton) findViewById(R.id.btnEstadoVistaEditar);
        btnEstadoPendienteEditar = (AppCompatButton) findViewById(R.id.btnEstadoPendienteEditar);
        btnEstadoFavoritaEditar = (AppCompatButton) findViewById(R.id.btnEstadoFavoritaEditar);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idPelicula = extras.getInt("idpeli");
        }

        peliculaEditar = conectorPelicula.getPeliculaById(idPelicula);

        Log.i("AQUIIIIIIIIIIIIIIIIIII", String.valueOf(idPelicula));
        Log.i("AQUIIIIIIIIIIIIIIIIIII", String.valueOf(peliculaEditar.id));

        ivPortadaEditar.setImageResource(this.getApplicationContext().getResources().getIdentifier(peliculaEditar.portada, "drawable", this.getApplicationContext().getPackageName()));

        if (ivPortadaEditar.getDrawable() == null) {
            ivPortadaEditar.setImageResource(this.getApplicationContext().getResources().getIdentifier("pe_null", "drawable", this.getApplicationContext().getPackageName()));
        }

        etPeliNombreEditar.setText(peliculaEditar.nombre);

        etPeliPuntuacionEditar.setText(String.valueOf(peliculaEditar.puntuacion));

        etPeliAnyoEditar.setText(String.valueOf(peliculaEditar.anyo));
        etPeliDuracionEditar.setText(String.valueOf(peliculaEditar.duracion));

        Director director = conectorDirector.getDirectorById(peliculaEditar.id_director);
        etPeliDirectorEditar.setText(String.valueOf(director.nombre_completo));

        Genero genero = conectorGenero.getGeneroById(peliculaEditar.id_genero);
        etPeliGeneroEditar.setText(String.valueOf(genero.nombre));

        Productor productor = conectorProductor.getProductorById(peliculaEditar.id_productor);
        etPeliProductorEditar.setText(productor.nombre);

        etPeliSinopsisEditar.setText(peliculaEditar.sinopsis);

        btnConfirmarEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** guardar en la base de datos */
                ////////////////////////////////////
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
