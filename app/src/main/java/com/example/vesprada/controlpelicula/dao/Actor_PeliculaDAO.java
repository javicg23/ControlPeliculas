package com.example.vesprada.controlpelicula.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.vesprada.controlpelicula.conexion.DBHelperControlPeliculas;


public class Actor_PeliculaDAO {
    private DBHelperControlPeliculas dbHelperActor_Pelicula;

    public Actor_PeliculaDAO(Context context) { dbHelperActor_Pelicula = new DBHelperControlPeliculas(context); }

    public int insert(Actor_Pelicula actor_pelicula){
        SQLiteDatabase db = dbHelperActor_Pelicula.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Actor_Pelicula.KEY_ID_Actor, actor_pelicula.id_actor);
        values.put(Actor_Pelicula.KEY_ID_Pelicula, actor_pelicula.id_pelicula);
        long ID = db.insert(Actor_Pelicula.TABLE, null, values);
        db.close();
        return (int) ID;
    }
}
