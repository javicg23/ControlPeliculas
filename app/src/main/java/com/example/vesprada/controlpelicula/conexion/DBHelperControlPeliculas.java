package com.example.vesprada.controlpelicula.conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.vesprada.controlpelicula.modelo.Actor;
import com.example.vesprada.controlpelicula.modelo.Actor_Pelicula;
import com.example.vesprada.controlpelicula.modelo.Director;
import com.example.vesprada.controlpelicula.modelo.Genero;
import com.example.vesprada.controlpelicula.modelo.Pelicula;
import com.example.vesprada.controlpelicula.modelo.Productor;

public class DBHelperControlPeliculas extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 13;

    private static final String DATABASE_NAME = "ControlPeliculas.db";

    public DBHelperControlPeliculas(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {



        String CREATE_TABLE_ACTOR = "CREATE TABLE " + Actor.TABLE + "("
                + Actor.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Actor.KEY_Nombre_completo + " TEXT)";
        db.execSQL(CREATE_TABLE_ACTOR);

        String CREATE_TABLE_GENERO = "CREATE TABLE " + Genero.TABLE + "("
                + Genero.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Genero.KEY_Nombre + " VARCHAR )";
        db.execSQL(CREATE_TABLE_GENERO);

        String CREATE_TABLE_DIRECTOR = "CREATE TABLE " + Director.TABLE + "("
                + Director.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Director.KEY_Nombre_completo + " TEXT )";
        db.execSQL(CREATE_TABLE_DIRECTOR);

        String CREATE_TABLE_PRODUCTOR = "CREATE TABLE " + Productor.TABLE + "("
                + Productor.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Productor.KEY_Nombre + " VARCHAR )";
        db.execSQL(CREATE_TABLE_PRODUCTOR);


        String CREATE_TABLE_PELICULA = "CREATE TABLE " + Pelicula.TABLE + "("
                + Pelicula.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Pelicula.KEY_Nombre + " VARCHAR, "
                + Pelicula.KEY_Anyo + " DATE, "
                + Pelicula.KEY_Duracion + " INTEGER, "
                + Pelicula.KEY_Sinopsis + " TEXT, "
                + Pelicula.KEY_Puntuacion + " FLOAT, "
                + Pelicula.KEY_Estado + " INTEGER DEFAULT 0, "
                + Pelicula.KEY_Portada + " TEXT, "
                + Pelicula.KEY_Id_genero + " INTEGER, "
                + Pelicula.KEY_Id_director + " INTEGER, "
                + Pelicula.KEY_Id_productor + " INTEGER, "
                + " FOREIGN KEY(" + Pelicula.KEY_Id_genero + ") REFERENCES " + Genero.TABLE + "(" + Genero.KEY_ID + ") ON UPDATE CASCADE ON DELETE CASCADE, "
                + " FOREIGN KEY(" + Pelicula.KEY_Id_director + ") REFERENCES " + Director.TABLE + "(" + Director.KEY_ID + ") ON UPDATE CASCADE ON DELETE CASCADE, "
                + " FOREIGN KEY(" + Pelicula.KEY_Id_productor + ") REFERENCES " + Productor.TABLE + "(" + Productor.KEY_ID + ") ON UPDATE CASCADE ON DELETE CASCADE)";
        db.execSQL(CREATE_TABLE_PELICULA);

        String CREATE_TABLE_ACTOR_PELICULA = "CREATE TABLE " + Actor_Pelicula.TABLE + "("
                + Actor_Pelicula.KEY_ID_Actor + " INTEGER, "
                + Actor_Pelicula.KEY_ID_Pelicula + " INTEGER, "
                + " PRIMARY KEY (" + Actor_Pelicula.KEY_ID_Pelicula + "," + Actor_Pelicula.KEY_ID_Actor + "), "
                + " FOREIGN KEY (" + Actor_Pelicula.KEY_ID_Actor + ") REFERENCES " + Actor.TABLE + "(" + Actor.KEY_ID + ") ON UPDATE CASCADE ON DELETE CASCADE, "
                + " FOREIGN KEY (" + Actor_Pelicula.KEY_ID_Pelicula + ") REFERENCES " + Pelicula.TABLE + "(" + Pelicula.KEY_ID + ") ON UPDATE CASCADE ON DELETE CASCADE)";
        Log.v("info", CREATE_TABLE_ACTOR_PELICULA);
        db.execSQL(CREATE_TABLE_ACTOR_PELICULA);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Actor.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Genero.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Director.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Productor.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Pelicula.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Actor_Pelicula.TABLE);

        onCreate(db);
    }
}
