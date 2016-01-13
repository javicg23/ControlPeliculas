package com.example.vesprada.controlpelicula.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vesprada.controlpelicula.MainActivity;
import com.example.vesprada.controlpelicula.R;
import com.example.vesprada.controlpelicula.conexion.DBHelperControlPeliculas;
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

public class CrearPelicula extends AppCompatActivity {

    private EditText etPeliNombre;
    private EditText etPeliSinopsis;
    private EditText etPeliDuracion;
    private EditText etPeliAnyo;
    private EditText etPeliPuntuacion;
    private EditText etPeliActor;
    private EditText etPeliDirector;
    private EditText etPeliProductor;
    private EditText etPeliGenero;

    private Button btnConfirmar;
    private Button btnAgregar;
    private AppCompatButton btnEstadoVista;
    private AppCompatButton btnEstadoNoVista;
    private AppCompatButton btnEstadoPendiente;
    private AppCompatButton btnEstadoFavorita;

    private PeliculaDAO conectorPelicula = new PeliculaDAO(this);
    private ProductorDAO conectorProductor = new ProductorDAO(this);
    private DirectorDAO conectorDirector = new DirectorDAO(this);
    private ActorDAO conectorActor = new ActorDAO(this);
    private GeneroDAO conectorGenero = new GeneroDAO(this);
    private Actor_PeliculaDAO conectorA_P = new Actor_PeliculaDAO(this);

    private Pelicula nuevaPelicula = new Pelicula();
    private Actor nuevoActor = new Actor();
    private Director nuevoDirector = new Director();
    private Productor nuevoProductor = new Productor();
    private Genero nuevoGenero = new Genero();
    private Actor_Pelicula nuevoA_P = new Actor_Pelicula();

    private int id_pelicula;
    private int id_estado = 1;
    private int id_Director;
    private int id_Productor;
    private int id_Genero;
    private int id_Actor;
    private int duracionRevisarEntero, anyoRevisarEntero, puntuacionRevisarEntero;

    private boolean vacio = false;
    private boolean controlEnteros = false;

    private String encuentraActor, encuentraPelicula, encuentraDirector, encuentraProductor, encuentraGenero;

    private ArrayList<Integer> actoresNuevos = new ArrayList<>();

    private DBHelperControlPeliculas dbhelpercrearpelicula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearpeliculas);

        etPeliNombre = (EditText) findViewById(R.id.etCrearNombre);
        etPeliSinopsis = (EditText) findViewById(R.id.etCrearSinopsis);
        etPeliDuracion = (EditText) findViewById(R.id.etCrearDuracion);
        etPeliAnyo = (EditText) findViewById(R.id.etCrearAnyo);
        etPeliPuntuacion = (EditText) findViewById(R.id.etCrearPuntuacion);
        etPeliActor = (EditText) findViewById(R.id.etCrearActor);
        etPeliDirector = (EditText) findViewById(R.id.etCrearDirector);
        etPeliProductor = (EditText) findViewById(R.id.etCrearProductor);
        etPeliGenero = (EditText) findViewById(R.id.etCrearGenero);


        dbhelpercrearpelicula = new DBHelperControlPeliculas(this);


        btnAgregar = (Button) findViewById(R.id.btnAgregarActor);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Actor
                 */

                if (etPeliActor.getText().toString().equalsIgnoreCase("")) {
                    Toast toastVacio =
                            Toast.makeText(getApplicationContext(), "El campo Actor está vacío", Toast.LENGTH_SHORT);
                    toastVacio.show();
                    vacio = true;
                } else {
                    String nombreActor = etPeliActor.getText().toString();
                    nombreActor = nombreActor.trim();
                    nombreActor = nombreActor.replaceAll("\\s+", " ");
                    nuevoActor.nombre_completo = nombreActor;
                    vacio = false;
                }

                if (!vacio) {
                    Actor actor = conectorActor.getActorByName(nuevoActor.nombre_completo);
                    encuentraActor = actor.nombre_completo;
                    if (encuentraActor == null) {
                        id_Actor = conectorActor.insert(nuevoActor);
                        actoresNuevos.add(id_Actor);
                        etPeliActor.setText("");
                    }
                    else {
                        id_Actor = actor.id;
                        actoresNuevos.add(id_Actor);
                        etPeliActor.setText("");
                    }
                }
            }
        });

        /**
         * Guardar el Estado de la película conforme haga click sobre un botón u otro
         */

        btnEstadoNoVista = (AppCompatButton) findViewById(R.id.btnEstadoNoVista);
        btnEstadoPendiente = (AppCompatButton) findViewById(R.id.btnEstadoPendiente);
        btnEstadoVista = (AppCompatButton) findViewById(R.id.btnEstadoVista);
        btnEstadoFavorita = (AppCompatButton) findViewById(R.id.btnEstadoFavorita);

        btnEstadoNoVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id_estado = 1;
            }
        });

        btnEstadoPendiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id_estado = 2;
            }
        });


        btnEstadoVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id_estado = 3;
            }
        });

        btnEstadoFavorita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id_estado = 4;
            }
        });

        if (controlEnteros){
            vacio = false;
        }
        btnConfirmar = (Button) findViewById(R.id.btnConfirmarPelicula);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (controlEnteros){
                    vacio = false;
                }

                if (
                        etPeliNombre.getText().toString().equalsIgnoreCase("") ||
                        etPeliDuracion.getText().toString().equalsIgnoreCase("") ||
                        etPeliAnyo.getText().toString().equalsIgnoreCase("") ||
                        etPeliPuntuacion.getText().toString().equalsIgnoreCase("") ||
                        etPeliDirector.getText().toString().equalsIgnoreCase("") ||
                        etPeliProductor.getText().toString().equalsIgnoreCase("") ||
                        etPeliGenero.getText().toString().equalsIgnoreCase("")
                        ) {

                    Toast toastVacio =
                            Toast.makeText(getApplicationContext(), "Hay algún campo vacío.", Toast.LENGTH_SHORT);
                    toastVacio.show();
                    vacio = true;
                }

                /**
                 * Busca si hay una pelicula en la base de datos y si hay alguna pelicula pone vacio = true para que no cree la nueva pelicula.
                 */
                Pelicula pelicula = conectorPelicula.getPeliculaByName(etPeliNombre.getText().toString());
                encuentraPelicula = pelicula.nombre;
                if (encuentraPelicula != null) {
                    Toast toastPuntuacion =
                            Toast.makeText(getApplicationContext(), "Esa Pelicula ya existe.", Toast.LENGTH_SHORT);
                    toastPuntuacion.show();
                    vacio = true;
                }
                else{
                    vacio = false;
                }

                if (!vacio) {
                    try {
                        duracionRevisarEntero = Integer.parseInt(etPeliDuracion.getText().toString());
                        anyoRevisarEntero = Integer.parseInt(etPeliAnyo.getText().toString());
                        puntuacionRevisarEntero = Integer.parseInt(etPeliPuntuacion.getText().toString());

                        if (puntuacionRevisarEntero < 0 || puntuacionRevisarEntero > 100) {
                            Toast toastPuntuacion =
                                    Toast.makeText(getApplicationContext(), "La puntuación debe estar entre 0 y 100.", Toast.LENGTH_SHORT);
                            toastPuntuacion.show();
                            vacio = true;
                        }
                        else{
                            vacio = false;
                        }
                    } catch (NumberFormatException e) {
                        Toast toastErrorNumerico =
                                Toast.makeText(getApplicationContext(), "Hay algún campo numérico que tiene letras.", Toast.LENGTH_SHORT);
                        toastErrorNumerico.show();
                        vacio = true;
                        controlEnteros = true;
                    }
                }

                if (!vacio) {

                    String nombrePeli = etPeliNombre.getText().toString();
                    nombrePeli = nombrePeli.trim();
                    nombrePeli = nombrePeli.replaceAll("\\s+", " ");
                    nuevaPelicula.nombre = nombrePeli;

                    nuevaPelicula.duracion = duracionRevisarEntero;
                    nuevaPelicula.anyo = anyoRevisarEntero;
                    nuevaPelicula.puntuacion = puntuacionRevisarEntero;

                    nuevaPelicula.sinopsis = etPeliSinopsis.getText().toString();

                    if (id_estado == 1) {
                        nuevaPelicula.id_estado = 1;
                    }
                    else if (id_estado == 2) {
                        nuevaPelicula.id_estado = 2;
                    }
                    else if (id_estado == 3) {
                        nuevaPelicula.id_estado = 3;
                    }
                    else {
                        nuevaPelicula.id_estado = 4;
                    }


                    String nombreDirector = etPeliDirector.getText().toString();
                    nombreDirector = nombreDirector.trim();
                    nombreDirector = nombreDirector.replaceAll("\\s+", " ");
                    nuevoDirector.nombre_completo = nombreDirector;


                    String nombreProductor = etPeliProductor.getText().toString();
                    nombreProductor = nombreProductor.trim();
                    nombreProductor = nombreProductor.replaceAll("\\s+", " ");
                    nuevoProductor.nombre = nombreProductor;

                    String nombreGenero = etPeliGenero.getText().toString();
                    nombreGenero = nombreGenero.trim();
                    nombreGenero = nombreGenero.replaceAll("\\s+", " ");
                    nuevoGenero.nombre = nombreGenero;

                    /**
                     * Director
                     */

                    Director director = conectorDirector.getDirectorByName(nuevoDirector.nombre_completo);
                    encuentraDirector = director.nombre_completo;

                    if (encuentraDirector == null) {
                        id_Director = conectorDirector.insert(nuevoDirector);
                    }
                    else {
                        id_Director = director.id;
                    }
                    /**
                     * Productor
                     */

                    Productor productor = conectorProductor.getProductorByName(nuevoProductor.nombre);
                    encuentraProductor = productor.nombre;
                    if (encuentraProductor == null) {
                        id_Productor = conectorProductor.insert(nuevoProductor);
                    }
                    else {
                        id_Productor = productor.id;
                    }

                    /**
                     * Genero
                     */

                    Genero genero = conectorGenero.getGeneroByName(nuevoGenero.nombre);
                    encuentraGenero = genero.nombre;
                    if (encuentraGenero == null) {
                        id_Genero = conectorGenero.insert(nuevoGenero);
                    }
                    else {
                        id_Genero = genero.id;
                    }


                    /**
                     * Pelicula
                     */

                        nuevaPelicula.id_director = id_Director;
                        nuevaPelicula.id_productor = id_Productor;
                        nuevaPelicula.id_genero = id_Genero;
                        id_pelicula = conectorPelicula.insert(nuevaPelicula);


                    /**
                     * Actor_Pelicula
                     */

                    for (int i = 0; i < actoresNuevos.size(); i++) {
                        int actor = actoresNuevos.get(i);
                        int pelicula2 = id_pelicula;
                        nuevoA_P.id_actor = actor;
                        nuevoA_P.id_pelicula = pelicula2;
                        conectorA_P.insert(nuevoA_P);
                    }

                    finish();
                }
            }
        });
    }
}