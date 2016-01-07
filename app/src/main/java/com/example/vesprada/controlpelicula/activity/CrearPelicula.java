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
import com.example.vesprada.controlpelicula.dao.DirectorDAO;
import com.example.vesprada.controlpelicula.dao.GeneroDAO;
import com.example.vesprada.controlpelicula.dao.PeliculaDAO;
import com.example.vesprada.controlpelicula.dao.ProductorDAO;
import com.example.vesprada.controlpelicula.modelo.Actor;
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

    private PeliculaDAO conectorPelicula = new PeliculaDAO(this);
    private ProductorDAO conectorProductor = new ProductorDAO(this);
    private DirectorDAO conectorDirector = new DirectorDAO(this);
    private ActorDAO conectorActor = new ActorDAO(this);
    private GeneroDAO conectorGenero = new GeneroDAO(this);

    private Pelicula nuevapelicula = new Pelicula();
    private Actor nuevoActor = new Actor();
    private Director nuevoDirector = new Director();
    private Productor nuevoProductor = new Productor();
    private Genero nuevoGenero = new Genero();

    private boolean encontrarPelicula = false;
    private boolean encontrarActor = false;
    private boolean encontrarDirector = false;
    private boolean encontrarProductor = false;
    private boolean encontrarGenero = false;
    private boolean vacio = false;

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



        btnConfirmar.findViewById(R.id.btnConfirmarPelicula);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /**
                 * Pelicula
                 */

                if (etPeliNombre.getText().toString().equalsIgnoreCase("")) {
                    Toast toastVacioPel =
                            Toast.makeText(getApplicationContext(), "El campo Nombre está vacío", Toast.LENGTH_SHORT);
                    toastVacioPel.show();
                    vacio = true;
                } else {
                    nuevapelicula.nombre = etPeliNombre.getText().toString();
                }

                if (etPeliDuracion.getText().toString().equalsIgnoreCase("")) {
                    Toast toastVacioDur =
                            Toast.makeText(getApplicationContext(), "El campo Duración está vacío", Toast.LENGTH_SHORT);
                    toastVacioDur.show();
                    vacio = true;
                } else {
                    nuevapelicula.duracion = Integer.parseInt(etPeliDuracion.getText().toString());
                }

                if (etPeliAnyo.getText().toString().equalsIgnoreCase("")) {
                    Toast toastVacioAny =
                            Toast.makeText(getApplicationContext(), "El campo Año está vacío", Toast.LENGTH_SHORT);
                    toastVacioAny.show();
                    vacio = true;
                } else {
                    nuevapelicula.anyo = Integer.parseInt(etPeliAnyo.getText().toString());
                }

                if (etPeliPuntuacion.getText().toString().equalsIgnoreCase("")) {
                    Toast toastVacioPun =
                            Toast.makeText(getApplicationContext(), "El campo Puntuación está vacío", Toast.LENGTH_SHORT);
                    toastVacioPun.show();
                    vacio = true;
                } else {
                    nuevapelicula.puntuacion = Integer.parseInt(etPeliPuntuacion.getText().toString());
                }

                nuevapelicula.sinopsis = etPeliSinopsis.getText().toString();
                nuevapelicula.id_estado = 1;

                if (!vacio) {
                    SQLiteDatabase dbPelicula = dbhelpercrearpelicula.getReadableDatabase();
                    String selectQueryPelicula = "SELECT " +
                            Pelicula.KEY_Nombre +
                            " FROM " + Pelicula.TABLE;

                    ArrayList<Pelicula> listapeliculas = new ArrayList<>();

                    Cursor cursorPelicula = dbPelicula.rawQuery(selectQueryPelicula, null);

                    if (cursorPelicula.moveToFirst()) {
                        do {
                            Pelicula p = new Pelicula();
                            p.nombre = cursorPelicula.getString(cursorPelicula.getColumnIndex(Pelicula.KEY_Nombre));
                            listapeliculas.add(p);
                        } while (cursorPelicula.moveToNext());
                    }

                    cursorPelicula.close();
                    dbPelicula.close();

                    for (int i = 0; i < listapeliculas.size(); i++) {
                        if ((listapeliculas.get(i).nombre).equalsIgnoreCase(etPeliNombre.getText().toString())) {

                            Toast toastExistePel =
                                    Toast.makeText(getApplicationContext(), "Ya existe esa pelicula", Toast.LENGTH_SHORT);
                            toastExistePel.show();
                            encontrarPelicula = true;
                            break;
                        }
                    }

                    if (!encontrarPelicula) {
                        conectorPelicula.insert(nuevapelicula);
                    }


                } else {
                    vacio = false;
                }

                /**
                 * Actor
                 */

                if (etPeliActor.getText().toString().equalsIgnoreCase("")) {
                    Toast toastVacioAct =
                            Toast.makeText(getApplicationContext(), "El campo Actor está vacío", Toast.LENGTH_SHORT);
                    toastVacioAct.show();
                    vacio = true;
                } else {
                    nuevoActor.nombre_completo = etPeliActor.getText().toString();
                }

                if (!vacio) {

                    SQLiteDatabase dbActor = dbhelpercrearpelicula.getReadableDatabase();
                    String selectQueryActor = "SELECT " +
                            Actor.KEY_Nombre_completo +
                            " FROM " + Actor.TABLE;

                    ArrayList<Actor> listactores = new ArrayList<>();

                    Cursor cursorActor = dbActor.rawQuery(selectQueryActor, null);

                    if (cursorActor.moveToFirst()) {
                        do {
                            Actor a = new Actor();
                            a.nombre_completo = cursorActor.getString(cursorActor.getColumnIndex(Actor.KEY_Nombre_completo));
                            listactores.add(a);
                        } while (cursorActor.moveToNext());
                    }

                    cursorActor.close();
                    dbActor.close();

                    for (int i = 0; i < listactores.size(); i++) {
                        if ((listactores.get(i).nombre_completo).equalsIgnoreCase(etPeliActor.getText().toString())) {

                            Toast toastExisteAct =
                                    Toast.makeText(getApplicationContext(), "Ya existe ese actor", Toast.LENGTH_SHORT);
                            toastExisteAct.show();
                            encontrarActor = true;
                            break;
                        }
                    }

                    if (!encontrarActor) {
                        conectorActor.insert(nuevoActor);
                    }

                } else {
                    vacio = false;
                }

                /**
                 * Director
                 */

                if (etPeliDirector.getText().toString().equalsIgnoreCase("")) {
                    Toast toastVacioDir =
                            Toast.makeText(getApplicationContext(), "El campo Director está vacío", Toast.LENGTH_SHORT);
                    toastVacioDir.show();
                    vacio = true;
                } else {
                    nuevoDirector.nombre_completo = etPeliDirector.getText().toString();
                }

                if (!vacio) {
                    SQLiteDatabase dbDirector = dbhelpercrearpelicula.getReadableDatabase();
                    String selectQueryDirector = "SELECT " +
                            Director.KEY_Nombre_completo +
                            " FROM " + Director.TABLE;

                    ArrayList<Director> listadirectores = new ArrayList<>();

                    Cursor cursorDirector = dbDirector.rawQuery(selectQueryDirector, null);

                    if (cursorDirector.moveToFirst()) {
                        do {
                            Director d = new Director();
                            d.nombre_completo = cursorDirector.getString(cursorDirector.getColumnIndex(Director.KEY_Nombre_completo));
                            listadirectores.add(d);
                        } while (cursorDirector.moveToNext());
                    }

                    cursorDirector.close();
                    dbDirector.close();

                    for (int i = 0; i < listadirectores.size(); i++) {
                        if ((listadirectores.get(i).nombre_completo).equalsIgnoreCase(etPeliDirector.getText().toString())) {

                            Toast toastExisteDir =
                                    Toast.makeText(getApplicationContext(), "Ya existe ese director", Toast.LENGTH_SHORT);
                            toastExisteDir.show();
                            encontrarDirector = true;
                            break;
                        }
                    }

                    if (!encontrarDirector) {
                        conectorDirector.insert(nuevoDirector);
                    }

                } else {
                    vacio = false;
                }


                /**
                 * Productor
                 */

                if (etPeliProductor.getText().toString().equalsIgnoreCase("")) {
                    Toast toastVacioPro =
                            Toast.makeText(getApplicationContext(), "El campo Productor está vacío", Toast.LENGTH_SHORT);
                    toastVacioPro.show();
                    vacio = true;
                } else {
                    nuevoProductor.nombre = etPeliProductor.getText().toString();
                }

                if (!vacio) {
                    SQLiteDatabase dbProductor = dbhelpercrearpelicula.getReadableDatabase();
                    String selectQueryProductor = "SELECT " +
                            Productor.KEY_Nombre +
                            " FROM " + Productor.TABLE;

                    ArrayList<Productor> listaproductores = new ArrayList<>();

                    Cursor cursorProductor = dbProductor.rawQuery(selectQueryProductor, null);

                    if (cursorProductor.moveToFirst()) {
                        do {
                            Productor p = new Productor();
                            p.nombre = cursorProductor.getString(cursorProductor.getColumnIndex(Productor.KEY_Nombre));
                            listaproductores.add(p);
                        } while (cursorProductor.moveToNext());
                    }

                    cursorProductor.close();
                    dbProductor.close();

                    for (int i = 0; i < listaproductores.size(); i++) {
                        if ((listaproductores.get(i).nombre).equalsIgnoreCase(etPeliProductor.getText().toString())) {

                            Toast toastExistePro =
                                    Toast.makeText(getApplicationContext(), "Ya existe ese productor", Toast.LENGTH_SHORT);
                            toastExistePro.show();
                            encontrarProductor = true;
                            break;
                        }
                    }

                    if (!encontrarProductor) {
                        conectorProductor.insert(nuevoProductor);
                    }

                } else {
                    vacio = false;
                }


                /**
                 * Genero
                 */

                if (etPeliGenero.getText().toString().equalsIgnoreCase("")) {
                    Toast toastVacioGen =
                            Toast.makeText(getApplicationContext(), "El campo Género está vacío", Toast.LENGTH_SHORT);
                    toastVacioGen.show();
                    vacio = true;
                } else {
                    nuevoGenero.nombre = etPeliGenero.getText().toString();
                }

                if (!vacio) {
                    SQLiteDatabase dbGenero = dbhelpercrearpelicula.getReadableDatabase();
                    String selectQueryGenero = "SELECT " +
                            Genero.KEY_Nombre +
                            " FROM " + Genero.TABLE;

                    ArrayList<Genero> listageneros = new ArrayList<>();

                    Cursor cursorGenero = dbGenero.rawQuery(selectQueryGenero, null);

                    if (cursorGenero.moveToFirst()) {
                        do {
                            Genero g = new Genero();
                            g.nombre = cursorGenero.getString(cursorGenero.getColumnIndex(Genero.KEY_Nombre));
                            listageneros.add(g);
                        } while (cursorGenero.moveToNext());
                    }

                    cursorGenero.close();
                    dbGenero.close();

                    for (int i = 0; i < listageneros.size(); i++) {
                        if ((listageneros.get(i).nombre).equalsIgnoreCase(etPeliGenero.getText().toString())) {

                            Toast toastExisteGen =
                                    Toast.makeText(getApplicationContext(), "Ya existe ese Genero", Toast.LENGTH_SHORT);
                            toastExisteGen.show();
                            encontrarGenero = true;
                            break;
                        }
                    }
                    if (!encontrarGenero) {
                        conectorGenero.insert(nuevoGenero);
                    }

                } else {
                    vacio = false;
                }
            }
        });

    /** comprobar que en la base de datos no existe el actor creado para añadiro
     * si existe nada sino lo añades
     * hacer un listener para el boton confirmar
     * la cadena de edittext hay que hacerle un trim para quitar espacios por delante y por detras y luego el nombre
     * replace all para sustituir los espacios interiores de sobra (busca cual era: //s /s --s -s)
     * listener para cojer los actores nuevos
     */
    }
}
