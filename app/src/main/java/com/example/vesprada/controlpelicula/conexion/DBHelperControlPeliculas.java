package com.example.vesprada.controlpelicula.conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vesprada.controlpelicula.modelo.Director;
import com.example.vesprada.controlpelicula.modelo.Genero;
import com.example.vesprada.controlpelicula.modelo.Productor;

public class DBHelperControlPeliculas extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 0.1;

    private static final String DATABASE_NAME = "ControlPeliclas";

    public DBHelperControlPeliculas(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_ACTOR = "CREATE TABLE " + Actor.TABLE + "("
                + Actor.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Actor.KEY_nombre_completo + " TEXT )";
        db.execSQL(CREATE_TABLE_ACTOR);

        String CREATE_TABLE_GENERO = "CREATE TABLE " + Genero.TABLE + "("
                + Genero.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Genero.KEY_nombre + " VARCHAR )";
        db.execSQL(CREATE_TABLE_GENERO);

        String CREATE_TABLE_DIRECTOR = "CREATE TABLE " + Director.TABLE + "("
                + Director.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Director.KEY_nombre_completo + " TEXT )";
        db.execSQL(CREATE_TABLE_DIRECTOR);

        String CREATE_TABLE_PRODUCTOR = "CREATE TABLE " + Productor.TABLE + "("
                + Productor.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Productor.KEY_nombre + " VARCHAR )";
        db.execSQL(CREATE_TABLE_PRODUCTOR);

        String CREATE_TABLE_PELICULA = "CREATE TABLE " + Pelicula.TABLE + "("
                + Pelicula.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Pelicula.KEY_nombre + " VARCHAR, "
                + Pelicula.KEY_año + " DATE, "
                + Pelicula.KEY_duracion + " INTEGER, "
                + Pelicula.KEY_sinopsis + " TEXT, "
                + Pelicula.KEY_puntuacion + " FLOAT, "
                + Pelicula.KEY_estado + " INTEGER DEFAULT 0, "
                + Pelicula.KEY_imagen + " TEXT, "
                + Pelicula.KEY_IDGenero + " INTEGER, "
                + Pelicula.KEY_IDDirector + " INTEGER, "
                + Pelicula.KEY_IDProductor + " INTEGER, "
                + " FOREIGN KEY(IDGenero) REFERENCES Genero(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
                + " FOREIGN KEY(IDDirector) REFERENCES Director(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
                + " FOREIGN KEY(IDProductor) REFERENCES Productor(ID) ON UPTADTE CASCADE ON DELETE CASCADE )";
        db.execSQL(CREATE_TABLE_PELICULA);

        String CREATE_TABLE_ACTOR_PELICULA = "CREATE TABLE " + Actor_Pelicula.TABLE + "("
                + Actor_Pelicula.KEY_IDActor + " INTEGER, "
                + Actor_Pelicula.KEY_IDPelicula + " INTEGER, "
                + " PRIMARY KEY {IDPelicula, IDActor}, "
                + " FOREIGN KEY(IDActor) REFERENCES Actor(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
                + " FOREIGN KEY(IDPelicula) REFERENCES Pelicula(ID) ON UPDATE CASCADA ON DELETE CASCADE )";


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
