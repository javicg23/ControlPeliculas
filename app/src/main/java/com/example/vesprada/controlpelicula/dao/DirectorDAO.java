package com.example.vesprada.controlpelicula.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vesprada.controlpelicula.conexion.DBHelperControlPeliculas;
import com.example.vesprada.controlpelicula.modelo.Director;

import java.util.ArrayList;
import java.util.HashMap;

public class DirectorDAO {
    private static DBHelperControlPeliculas dbHelperDirector;

    public DirectorDAO(Context context) { dbHelperDirector = new DBHelperControlPeliculas(context); }

    public int insert(Director director) {

        SQLiteDatabase db = dbHelperDirector.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Director.KEY_ID, director.id);
        values.put(Director.KEY_Nombre_completo, director.nombre_completo);

        long ID = db.insert(Director.TABLE, null, values);
        db.close();
        return (int) ID;
    }

    public void delete(int ID) {

        SQLiteDatabase db = dbHelperDirector.getWritableDatabase();
        db.delete(Director.TABLE, Director.KEY_ID + "= ?", new String[] {String.valueOf(ID)});
        db.close();
    }

    public void update (Director director) {

        SQLiteDatabase db = dbHelperDirector.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Director.KEY_ID, director.id);
        values.put(Director.KEY_Nombre_completo, director.nombre_completo);

        db.update(Director.TABLE, values, Director.KEY_ID + "= ?", new String[]{String.valueOf(director.id)});
    }

    public ArrayList<HashMap<String, String>> getDirectorList() {

        SQLiteDatabase db = dbHelperDirector.getReadableDatabase();
        String selectQuery = "SELECT " +
                Director.KEY_ID + "," +
                Director.KEY_Nombre_completo +
                " FROM " + Director.TABLE;

        ArrayList<HashMap<String, String>> directorLista = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> director = new HashMap<String, String>();
                director.put("id", cursor.getString(cursor.getColumnIndex(Director.KEY_ID)));
                director.put("nombre_completo", cursor.getString(cursor.getColumnIndex(Director.KEY_Nombre_completo)));
                directorLista.add(director);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return directorLista;
    }

    public ArrayList<HashMap<String, String>> getDirectorListByName(String nombreDirector) {

        SQLiteDatabase db = dbHelperDirector.getReadableDatabase();
        String selectQuery = "SELECT " +
                Director.KEY_ID + "," +
                Director.KEY_Nombre_completo +
                " FROM " + Director.TABLE +
                " WHERE " + Director.KEY_Nombre_completo + " LIKE ?";

        ArrayList<HashMap<String, String>> directorLista = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{"%" + nombreDirector + "%"});

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> director = new HashMap<String, String>();
                director.put("id", cursor.getString(cursor.getColumnIndex(Director.KEY_ID)));
                director.put("nombre_completo", cursor.getString(cursor.getColumnIndex(Director.KEY_Nombre_completo)));
                directorLista.add(director);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return directorLista;
    }

    public Director getDirectorListById(int id) {

        SQLiteDatabase db = dbHelperDirector.getReadableDatabase();
        String selectQuery = "SELECT " +
                Director.KEY_ID + "," +
                Director.KEY_Nombre_completo +
                " FROM " + Director.TABLE +
                " WHERE " + Director.KEY_ID + "=?";

        /**
         * No sabemos para que se usa valor entero iCount y en el
         * proyecto de Eloy no se usa en ningún momento.
         * int iCount =0
         */

        Director director = new Director();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(id)});

        if (cursor.moveToFirst()) {
            do {
                director.id = cursor.getInt(cursor.getColumnIndex(Director.KEY_ID));
                director.nombre_completo = cursor.getString(cursor.getColumnIndex(Director.KEY_Nombre_completo));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return director;
    }
}