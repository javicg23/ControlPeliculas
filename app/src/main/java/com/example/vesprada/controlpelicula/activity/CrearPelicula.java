package com.example.vesprada.controlpelicula.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import com.example.vesprada.controlpelicula.R;
import com.example.vesprada.controlpelicula.conexion.DBHelperControlPeliculas;
import com.example.vesprada.controlpelicula.dao.ActorDAO;
import com.example.vesprada.controlpelicula.dao.DirectorDAO;
import com.example.vesprada.controlpelicula.dao.EstadoDAO;
import com.example.vesprada.controlpelicula.dao.GeneroDAO;
import com.example.vesprada.controlpelicula.dao.PeliculaDAO;
import com.example.vesprada.controlpelicula.dao.ProductorDAO;
import com.example.vesprada.controlpelicula.modelo.Actor;
import com.example.vesprada.controlpelicula.modelo.Director;
import com.example.vesprada.controlpelicula.modelo.Pelicula;
import com.example.vesprada.controlpelicula.modelo.Productor;

import java.util.ArrayList;

public class CrearPelicula extends AppCompatActivity {

    private EditText tvPeliNombre;
    private EditText tvPeliSinopsis;
    private EditText tvPeliDuracion;
    private EditText tvPeliAnyo;
    private EditText tvPeliPuntuacion;
    private EditText tvPeliActor;
    private EditText tvPeliDirector;
    private EditText tvPeliProductor;
    private EditText tvPeliGenero;
    private Button btnConfirmar;
    private PeliculaDAO conectorPelicula = new PeliculaDAO(this);
    private ProductorDAO conectorProductor = new ProductorDAO(this);
    private DirectorDAO conectorDirector = new DirectorDAO(this);
    private ActorDAO conectorActor = new ActorDAO(this);
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
        //tvPeliGenero = (EditText) findViewById(R.id.etCrearGenero);


        Pelicula nuevapelicula = new Pelicula();
        nuevapelicula.nombre = tvPeliNombre.getText().toString();
        nuevapelicula.sinopsis = tvPeliSinopsis.getText().toString();
        nuevapelicula.duracion = Integer.parseInt(tvPeliDuracion.getText().toString());
        nuevapelicula.anyo = Integer.parseInt(tvPeliAnyo.getText().toString());
        nuevapelicula.puntuacion = Float.parseFloat(tvPeliPuntuacion.getText().toString());
        nuevapelicula.id_estado = 1;


        DBHelperControlPeliculas dbhelpercrearpelicula;
        dbhelpercrearpelicula = new DBHelperControlPeliculas(this);



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
        boolean encontrarPelicula = false;
        for (int i=0; i<listapeliculas.size(); i++) {
            if ((listapeliculas.get(i).nombre).equalsIgnoreCase(tvPeliNombre.getText().toString())) {

                Toast toast1 =
                        Toast.makeText(getApplicationContext(), "Ya existe esa pelicula", Toast.LENGTH_SHORT);
                toast1.show();
                encontrarPelicula = true;
                break;
            }
        }

        if (!encontrarPelicula) {
            conectorPelicula.insert(nuevapelicula);
        }

        Actor nuevoActor = new Actor();
        nuevoActor.nombre_completo = tvPeliActor.getText().toString();

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
        boolean encontrarActor = false;
        for (int i=0; i<listactores.size(); i++) {
            if ((listactores.get(i).nombre_completo).equalsIgnoreCase(tvPeliActor.getText().toString())) {

                Toast toast2 =
                        Toast.makeText(getApplicationContext(), "Ya existe ese actor", Toast.LENGTH_SHORT);
                toast2.show();
                encontrarActor = true;
                break;
            }
        }

        if (!encontrarActor) {
            conectorActor.insert(nuevoActor);
        }


        Director nuevoDirector = new Director();
        nuevoDirector.nombre_completo = tvPeliDirector.getText().toString();

        SQLiteDatabase dbDirector = dbhelpercrearpelicula.getReadableDatabase();
        String selectQueryDirector = "SELECT " +
                Director.KEY_Nombre_completo +
                " FROM " + Director.TABLE;

        ArrayList<Director> listadirectores = new ArrayList<>();

        Cursor cursorDirector = dbDirector.rawQuery(selectQueryDirector, null);

        if(cursorDirector.moveToFirst()) {
            do {
                Director d = new Director();
                d.nombre_completo = cursorActor.getString(cursorDirector.getColumnIndex(Director.KEY_Nombre_completo));
                listadirectores.add(d);
            } while (cursorDirector.moveToNext());
        }

        cursorDirector.close();
        dbDirector.close();
        boolean encontrarDirector = false;
        for (int i=0; i<listadirectores.size(); i++) {
            if ((listadirectores.get(i).nombre_completo).equalsIgnoreCase(tvPeliDirector.getText().toString())) {

                Toast toast3 =
                        Toast.makeText(getApplicationContext(), "Ya existe ese director", Toast.LENGTH_SHORT);
                toast3.show();
                encontrarDirector = true;
                break;
            }
        }

        if (!encontrarDirector) {
            conectorDirector.insert(nuevoDirector);
        }

        Productor nuevoProductor = new Productor();
        nuevoProductor.nombre = tvPeliProductor.getText().toString();

        SQLiteDatabase dbProductor = dbhelpercrearpelicula.getReadableDatabase();
        String selectQueryProductor = "SELECT " +
                Productor.KEY_Nombre +
                " FROM " + Productor.TABLE;

        ArrayList<Productor> listaproductores = new ArrayList<>();

        Cursor  cursorProductor = dbProductor.rawQuery(selectQueryProductor, null);

        if(cursorProductor.moveToFirst()) {
            do {
                Productor p = new Productor();
                p.nombre = cursorProductor.getString(cursorProductor.getColumnIndex(Productor.KEY_Nombre));
                listaproductores.add(p);
            } while (cursorProductor.moveToNext());
        }

        cursorProductor.close();
        dbProductor.close();
        boolean encontrarProductor = false;
        for (int i=0; i<listaproductores.size(); i++) {
            if ((listadirectores.get(i).nombre_completo).equalsIgnoreCase(tvPeliProductor.getText().toString())) {

                Toast toast4 =
                        Toast.makeText(getApplicationContext(), "Ya existe ese productor", Toast.LENGTH_SHORT);
                toast4.show();
                encontrarProductor = true;
                break;
            }
        }

        if (!encontrarProductor) {
            conectorProductor.insert(nuevoProductor);
        }

        btnConfirmar.findViewById(R.id.btnConfirmarPelicula);
        btnConfirmar.OnClickListener(this);




    /** comprobar que en la base de datos no existe el actor creado para ñaadiro
     * si existe nada sino lo añades
     * hacer un listener
     * la cadena de edittext hay que hacerle un trim para quitar espacios por delante y por detras y luego el nombre
     * replace all para sustituir los espacios  //s /s --s -s
     * listener para cojer los actores nuevos
     * agregar actor se añada en la tabla agregar actor
     */
    }
}
