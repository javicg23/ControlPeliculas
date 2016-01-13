package com.example.vesprada.controlpelicula.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vesprada.controlpelicula.MainActivity;
import com.example.vesprada.controlpelicula.R;
import com.example.vesprada.controlpelicula.dao.ActorDAO;
import com.example.vesprada.controlpelicula.dao.Actor_PeliculaDAO;
import com.example.vesprada.controlpelicula.dao.DirectorDAO;
import com.example.vesprada.controlpelicula.dao.GeneroDAO;
import com.example.vesprada.controlpelicula.dao.PeliculaDAO;
import com.example.vesprada.controlpelicula.dao.ProductorDAO;
import com.example.vesprada.controlpelicula.modelo.Actor;
import com.example.vesprada.controlpelicula.modelo.Actor_Pelicula;
import com.example.vesprada.controlpelicula.modelo.Director;
import com.example.vesprada.controlpelicula.modelo.Genero;
import com.example.vesprada.controlpelicula.modelo.Pelicula;
import com.example.vesprada.controlpelicula.modelo.Productor;

import java.util.ArrayList;

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
    private TextView tvActoresEditar;

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
    private int puntuacionPeliculaEditar;
    private Pelicula peliculaEditar;
    private ArrayList<Actor_Pelicula> listaActoresEditar = new ArrayList<Actor_Pelicula>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificarpelicula);

        etPeliNombreEditar = (EditText) findViewById(R.id.etEditarNombre);
        etPeliSinopsisEditar = (EditText) findViewById(R.id.etEditarSinopsis);
        etPeliDuracionEditar = (EditText) findViewById(R.id.etEditarDuracion);
        etPeliAnyoEditar = (EditText) findViewById(R.id.etEditarAnyo);
        etPeliPuntuacionEditar = (EditText) findViewById(R.id.etEditarPuntuacion);
        etPeliActorEditar = (EditText) findViewById(R.id.etEditarActor);
        etPeliDirectorEditar = (EditText) findViewById(R.id.etEditarDirector);
        etPeliProductorEditar = (EditText) findViewById(R.id.etEditarProductor);
        etPeliGeneroEditar = (EditText) findViewById(R.id.etEditarGenero);
        tvActoresEditar = (TextView) findViewById(R.id.tvActoresEditar);

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

        etPeliNombreEditar.setText(peliculaEditar.nombre);

        etPeliPuntuacionEditar.setText(String.valueOf(peliculaEditar.puntuacion));

        etPeliAnyoEditar.setText(String.valueOf(peliculaEditar.anyo));
        etPeliDuracionEditar.setText(String.valueOf(peliculaEditar.duracion));

        listaActoresEditar = conectorA_P.getIdActorByIdPelicula(idPelicula);
        String listaActores = "";
        for (int i = 0; i < listaActoresEditar.size(); i++){
            Actor actor = conectorActor.getActorById(listaActoresEditar.get(i).id_actor);
            if (i < listaActoresEditar.size()-1){
                listaActores = listaActores + actor.nombre_completo + ", ";
            }
            else{
                listaActores = listaActores + actor.nombre_completo;
            }
        }
        tvActoresEditar.setText(listaActores);

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
    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbindDrawables(findViewById(R.id.modificarPelicula));
        System.gc();
    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }
}
