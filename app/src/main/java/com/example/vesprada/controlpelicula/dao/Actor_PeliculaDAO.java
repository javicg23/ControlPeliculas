package com.example.vesprada.controlpelicula.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vesprada.controlpelicula.conexion.DBHelperControlPeliculas;
import com.example.vesprada.controlpelicula.modelo.Actor_Pelicula;

import java.util.ArrayList;


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

    public ArrayList<Actor_Pelicula> getIdActorByIdPelicula(int idPelicula){
        SQLiteDatabase db = dbHelperActor_Pelicula.getReadableDatabase();
        String selectQuery = "SELECT " +
                Actor_Pelicula.KEY_ID_Pelicula + " ,"
                + Actor_Pelicula.KEY_ID_Actor
                + " FROM " + Actor_Pelicula.TABLE
                + " WHERE " +  Actor_Pelicula.KEY_ID_Pelicula + " =?";

        ArrayList<Actor_Pelicula> actorPeliculaList = new ArrayList<Actor_Pelicula>();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(idPelicula)});

        if (cursor.moveToFirst()) {

            do {
                Actor_Pelicula a_p = new Actor_Pelicula();
                a_p.id_pelicula = cursor.getInt(cursor.getColumnIndex(Actor_Pelicula.KEY_ID_Pelicula));
                a_p.id_actor = cursor.getInt(cursor.getColumnIndex(Actor_Pelicula.KEY_ID_Actor));

                actorPeliculaList.add(a_p);


            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return actorPeliculaList;
    }
}