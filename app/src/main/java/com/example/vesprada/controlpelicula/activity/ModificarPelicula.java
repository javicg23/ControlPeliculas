package com.example.vesprada.controlpelicula.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

/** Activity que sirve para modificar los datos de una pelicula, básicamente es parecido a
 * la activity CrearPelícula pero rellenando los campos ya de base y mostrando los actores
 * que ya existen en la película
 */
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

    private int idPeliculaEditar;
    private int id_estadoEditar;
    private int id_DirectorEditar;
    private int id_ProductorEditar;
    private int id_GeneroEditar;
    private int id_ActorEditar;
    private int duracionRevisarEnteroEditar, anyoRevisarEnteroEditar, puntuacionRevisarEnteroEditar;

    private String encuentraActorEditar, encuentraDirectorEditar, encuentraProductorEditar, encuentraGeneroEditar;

    private boolean vacioEditar = false;
    private boolean controlEnterosEditar = false;

    private Pelicula peliculaEditar;
    private Pelicula nuevaPeliculaEditar = new Pelicula();
    private Actor nuevoActorEditar = new Actor();
    private Director nuevoDirectorEditar = new Director();
    private Productor nuevoProductorEditar = new Productor();
    private Genero nuevoGeneroEditar = new Genero();
    private Actor_Pelicula nuevoA_PEditar = new Actor_Pelicula();

    private ArrayList<Integer> actoresNuevosEditar = new ArrayList<>();
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

        /** Nos traemos el id de la pelicula de la cual queramos rellenar los edittext */
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idPeliculaEditar = extras.getInt("idpeli");
        }

        peliculaEditar = conectorPelicula.getPeliculaById(idPeliculaEditar);

        id_estadoEditar = peliculaEditar.id_estado;

        etPeliNombreEditar.setText(peliculaEditar.nombre);

        etPeliPuntuacionEditar.setText(String.valueOf(peliculaEditar.puntuacion));

        etPeliAnyoEditar.setText(String.valueOf(peliculaEditar.anyo));
        etPeliDuracionEditar.setText(String.valueOf(peliculaEditar.duracion));

        listaActoresEditar = conectorA_P.getIdActorByIdPelicula(idPeliculaEditar);
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

        btnEstadoNoVistaEditar = (AppCompatButton) findViewById(R.id.btnEstadoNoVistaEditar);
        btnEstadoPendienteEditar = (AppCompatButton) findViewById(R.id.btnEstadoPendienteEditar);
        btnEstadoVistaEditar = (AppCompatButton) findViewById(R.id.btnEstadoVistaEditar);
        btnEstadoFavoritaEditar = (AppCompatButton) findViewById(R.id.btnEstadoFavoritaEditar);

        btnAgregarEditar = (Button) findViewById(R.id.btnAgregarActorEditar);
        btnAgregarEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /** Con actor hacemos las diferentes comprobaciones y cada vez nos guardamos el actor en un arraylist para luego hacer los inserts en la tabla Actor_Pelicula */
                if (etPeliActorEditar.getText().toString().equalsIgnoreCase("")) {
                    Toast toastVacio =
                            Toast.makeText(getApplicationContext(), "El campo Actor está vacío", Toast.LENGTH_SHORT);
                    toastVacio.show();
                    vacioEditar = true;
                } else {
                    String nombreActor = etPeliActorEditar.getText().toString();
                    nombreActor = nombreActor.trim();
                    nombreActor = nombreActor.replaceAll("\\s+", " ");
                    nuevoActorEditar.nombre_completo = nombreActor;
                    vacioEditar = false;
                }

                if (!vacioEditar) {
                    Actor actor = conectorActor.getActorByName(nuevoActorEditar.nombre_completo);
                    encuentraActorEditar = actor.nombre_completo;
                    if (encuentraActorEditar == null) {
                        id_ActorEditar = conectorActor.insert(nuevoActorEditar);
                        actoresNuevosEditar.add(id_ActorEditar);
                        etPeliActorEditar.setText("");
                    }
                    else {
                        id_ActorEditar = actor.id;
                        actoresNuevosEditar.add(id_ActorEditar);
                        etPeliActorEditar.setText("");
                    }
                }
            }
        });

        /** Guardar el estado de la película conforme haga click sobre un botón u otro */
        btnEstadoNoVistaEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id_estadoEditar = 1;
            }
        });

        btnEstadoPendienteEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id_estadoEditar = 2;
            }
        });


        btnEstadoVistaEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id_estadoEditar = 3;
            }
        });

        btnEstadoFavoritaEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id_estadoEditar = 4;
            }
        });

        btnConfirmarEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /** Para controlar que todos los campos numéricos sean números */
                if (controlEnterosEditar){
                    vacioEditar = false;
                }

                /** Para comprobar los campos vacíos */
                if (
                    etPeliNombreEditar.getText().toString().equalsIgnoreCase("") ||
                        etPeliDuracionEditar.getText().toString().equalsIgnoreCase("") ||
                        etPeliAnyoEditar.getText().toString().equalsIgnoreCase("") ||
                        etPeliPuntuacionEditar.getText().toString().equalsIgnoreCase("") ||
                        etPeliDirectorEditar.getText().toString().equalsIgnoreCase("") ||
                        etPeliProductorEditar.getText().toString().equalsIgnoreCase("") ||
                        etPeliGeneroEditar.getText().toString().equalsIgnoreCase("")
                    ) {

                    Toast toastVacio =
                            Toast.makeText(getApplicationContext(), "Hay algún campo vacío.", Toast.LENGTH_SHORT);
                    toastVacio.show();
                    vacioEditar = true;
                }


                if (!vacioEditar) {
                    try {
                        duracionRevisarEnteroEditar = Integer.parseInt(etPeliDuracionEditar.getText().toString());
                        anyoRevisarEnteroEditar = Integer.parseInt(etPeliAnyoEditar.getText().toString());
                        puntuacionRevisarEnteroEditar = Integer.parseInt(etPeliPuntuacionEditar.getText().toString());

                        if (puntuacionRevisarEnteroEditar < 0 || puntuacionRevisarEnteroEditar > 100) {
                            Toast toastPuntuacion =
                                    Toast.makeText(getApplicationContext(), "La puntuación debe estar entre 0 y 100.", Toast.LENGTH_SHORT);
                            toastPuntuacion.show();
                            vacioEditar = true;
                        }
                        else{
                            vacioEditar = false;
                        }
                    } catch (NumberFormatException e) {
                        Toast toastErrorNumerico =
                                Toast.makeText(getApplicationContext(), "Hay algún campo numérico que tiene letras.", Toast.LENGTH_SHORT);
                        toastErrorNumerico.show();
                        vacioEditar = true;
                        controlEnterosEditar = true;
                    }
                }

                if (!vacioEditar) {

                    String nombrePeli = etPeliNombreEditar.getText().toString();
                    nombrePeli = nombrePeli.trim();
                    nombrePeli = nombrePeli.replaceAll("\\s+", " ");
                    nuevaPeliculaEditar.nombre = nombrePeli;

                    nuevaPeliculaEditar.duracion = duracionRevisarEnteroEditar;
                    nuevaPeliculaEditar.anyo = anyoRevisarEnteroEditar;
                    nuevaPeliculaEditar.puntuacion = puntuacionRevisarEnteroEditar;

                    nuevaPeliculaEditar.sinopsis = etPeliSinopsisEditar.getText().toString();

                    if (id_estadoEditar == 1) {
                        nuevaPeliculaEditar.id_estado = 1;
                    }
                    else if (id_estadoEditar == 2) {
                        nuevaPeliculaEditar.id_estado = 2;
                    }
                    else if (id_estadoEditar == 3) {
                        nuevaPeliculaEditar.id_estado = 3;
                    }
                    else {
                        nuevaPeliculaEditar.id_estado = 4;
                    }


                    String nombreDirector = etPeliDirectorEditar.getText().toString();
                    nombreDirector = nombreDirector.trim();
                    nombreDirector = nombreDirector.replaceAll("\\s+", " ");
                    nuevoDirectorEditar.nombre_completo = nombreDirector;


                    String nombreProductor = etPeliProductorEditar.getText().toString();
                    nombreProductor = nombreProductor.trim();
                    nombreProductor = nombreProductor.replaceAll("\\s+", " ");
                    nuevoProductorEditar.nombre = nombreProductor;

                    String nombreGenero = etPeliGeneroEditar.getText().toString();
                    nombreGenero = nombreGenero.trim();
                    nombreGenero = nombreGenero.replaceAll("\\s+", " ");
                    nuevoGeneroEditar.nombre = nombreGenero;

                    /** Buscamos el director y si existe no hace falta crearlo */
                    Director director = conectorDirector.getDirectorByName(nuevoDirectorEditar.nombre_completo);
                    encuentraDirectorEditar = director.nombre_completo;

                    if (encuentraDirectorEditar == null) {
                        id_DirectorEditar = conectorDirector.insert(nuevoDirectorEditar);
                    }
                    else {
                        id_DirectorEditar = director.id;
                    }

                    /** Lo mismo que con director pero para el productor */
                    Productor productor = conectorProductor.getProductorByName(nuevoProductorEditar.nombre);
                    encuentraProductorEditar = productor.nombre;

                    if (encuentraProductorEditar == null) {
                        id_ProductorEditar = conectorProductor.insert(nuevoProductorEditar);
                    }
                    else {
                        id_ProductorEditar = productor.id;
                    }

                    /** Idem con Genero*/
                    Genero genero = conectorGenero.getGeneroByName(nuevoGeneroEditar.nombre);
                    encuentraGeneroEditar = genero.nombre;
                    if (encuentraGeneroEditar == null) {
                        id_GeneroEditar = conectorGenero.insert(nuevoGeneroEditar);
                    }
                    else {
                        id_GeneroEditar = genero.id;
                    }


                    /** Pelicula */
                    nuevaPeliculaEditar.id_director = id_DirectorEditar;
                    nuevaPeliculaEditar.id_productor = id_ProductorEditar;
                    nuevaPeliculaEditar.id_genero = id_GeneroEditar;


                    /** Actor_Pelicula */
                    for (int i = 0; i < actoresNuevosEditar.size(); i++) {
                        int actor = actoresNuevosEditar.get(i);
                        int pelicula2 = idPeliculaEditar;
                        nuevoA_PEditar.id_actor = actor;
                        nuevoA_PEditar.id_pelicula = pelicula2;
                        conectorA_P.insert(nuevoA_PEditar);
                    }
                    nuevaPeliculaEditar.id = idPeliculaEditar;
                    conectorPelicula.update(nuevaPeliculaEditar,idPeliculaEditar);

                    /** Cerramos la activity */
                    finish();
                }
            }
        });
    }
}