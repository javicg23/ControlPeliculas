package com.example.vesprada.controlpelicula.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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

    Button btnConfirmar;
    Button btnAgregar;

    private PeliculaDAO conectorPelicula = new PeliculaDAO(this);
    private ProductorDAO conectorProductor = new ProductorDAO(this);
    private DirectorDAO conectorDirector = new DirectorDAO(this);
    private ActorDAO conectorActor = new ActorDAO(this);
    private GeneroDAO conectorGenero = new GeneroDAO(this);
    private Actor_PeliculaDAO conectorA_P = new Actor_PeliculaDAO(this);

    private Pelicula nuevapelicula = new Pelicula();
    private Actor nuevoActor = new Actor();
    private Director nuevoDirector = new Director();
    private Productor nuevoProductor = new Productor();
    private Genero nuevoGenero = new Genero();
    private Actor_Pelicula nuevoA_P = new Actor_Pelicula();

    private int id_pelicula;

    private boolean encontrarPelicula = false;
    private boolean encontrarActor = false;
    private boolean encontrarDirector = false;
    private boolean encontrarProductor = false;
    private boolean encontrarGenero = false;
    private boolean vacio = false;

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

                    encuentraActor = conectorActor.getActorByName(nuevoActor.nombre_completo).nombre_completo;
                    if (encuentraActor == null) {
                        conectorActor.insert(nuevoActor);
                        actoresNuevos.add(nuevoActor.id);
                        etPeliActor.setText("");
                    }

                    /**
                     *
                     *
                     ArrayList<Actor> listaActores;

                     listaActores = conectorActor.getActorList();

                    for (int i = 0; i < listaActores.size(); i++) {
                        if ((listaActores.get(i).nombre_completo).equalsIgnoreCase(etPeliActor.getText().toString())) {
                            encontrarActor = true;
                            break;
                        }
                    }

                    if (!encontrarActor) {
                        conectorActor.insert(nuevoActor);
                        actoresNuevos.add(nuevoActor.id);
                        etPeliActor.setText("");
                    }
                     */
                }
            }
        });


        btnConfirmar = (Button) findViewById(R.id.btnConfirmarPelicula);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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

                if (!vacio) {

                    String nombrePeli = etPeliNombre.getText().toString();
                    nombrePeli = nombrePeli.trim();
                    nombrePeli = nombrePeli.replaceAll("\\s+", " ");
                    nuevapelicula.nombre = nombrePeli;
                    nuevapelicula.duracion = Integer.parseInt(etPeliDuracion.getText().toString());
                    nuevapelicula.anyo = Integer.parseInt(etPeliAnyo.getText().toString());
                    nuevapelicula.puntuacion = Integer.parseInt(etPeliPuntuacion.getText().toString());
                    nuevapelicula.sinopsis = etPeliSinopsis.getText().toString();
                    nuevapelicula.id_estado = 1;

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
                     * Pelicula
                     */

                    encuentraPelicula = conectorPelicula.getPeliculaByName(nuevapelicula.nombre).nombre;
                    if (encuentraPelicula == null) {
                        id_pelicula = conectorPelicula.insert(nuevapelicula);
                    }
                /**
                    ArrayList<Pelicula> listapeliculas;

                    listapeliculas = conectorPelicula.getPeliculaList();

                    for (int i = 0; i < listapeliculas.size(); i++) {
                        if ((listapeliculas.get(i).nombre).equalsIgnoreCase(etPeliNombre.getText().toString())) {
                            encontrarPelicula = true;
                            break;
                        }
                    }

                    if (!encontrarPelicula) {
                       id_pelicula = conectorPelicula.insert(nuevapelicula);
                    }
                 */

                    /**
                     * Director
                     */
                    encuentraDirector = conectorDirector.getDirectorByName(nuevoDirector.nombre_completo).nombre_completo;
                    if (encuentraDirector == null) {
                        conectorDirector.insert(nuevoDirector);
                    }

                    /**
                    ArrayList<Director> listadirectores = new ArrayList<>();

                    listadirectores = conectorDirector.getDirectorList();

                    for (int i = 0; i < listadirectores.size(); i++) {
                        if ((listadirectores.get(i).nombre_completo).equalsIgnoreCase(etPeliDirector.getText().toString())) {
                            encontrarDirector = true;
                            break;
                        }
                    }

                    if (!encontrarDirector) {
                        conectorDirector.insert(nuevoDirector);
                    }
                    */

                    /**
                     * Productor
                     */
                    encuentraProductor = conectorProductor.getProductorByName(nuevoProductor.nombre).nombre;
                    if (encuentraProductor == null) {
                        conectorProductor.insert(nuevoProductor);
                    }

                    /**
                    ArrayList<Productor> listaproductores = new ArrayList<>();

                    listaproductores = conectorProductor.getProductorList();

                    for (int i = 0; i < listaproductores.size(); i++) {
                        if ((listaproductores.get(i).nombre).equalsIgnoreCase(etPeliProductor.getText().toString())) {
                            encontrarProductor = true;
                            break;
                        }
                    }

                    if (!encontrarProductor) {
                        conectorProductor.insert(nuevoProductor);
                    }
                    */

                    /**
                     * Genero
                     */
                    encuentraGenero = conectorGenero.getGeneroByName(nuevoGenero.nombre).nombre;
                    if (encuentraGenero == null) {
                        conectorGenero.insert(nuevoGenero);
                    }

                    /**
                    ArrayList<Genero> listageneros = new ArrayList<>();

                    listageneros = conectorGenero.getGeneroList();

                    for (int i = 0; i < listageneros.size(); i++) {
                        if ((listageneros.get(i).nombre).equalsIgnoreCase(etPeliGenero.getText().toString())) {
                            encontrarGenero = true;
                            break;
                        }
                    }
                    if (!encontrarGenero) {
                        conectorGenero.insert(nuevoGenero);
                    }
                    */

                    /**
                     * Actor_Pelicula
                     */

                    for (int i = 0; i<actoresNuevos.size(); i++) {
                        int actor = actoresNuevos.get(i);
                        int pelicula = id_pelicula;
                        nuevoA_P.id_actor = actor;
                        nuevoA_P.id_pelicula = pelicula;
                        conectorA_P.insert(nuevoA_P);
                    }


                }
            }
        });
    }
}
