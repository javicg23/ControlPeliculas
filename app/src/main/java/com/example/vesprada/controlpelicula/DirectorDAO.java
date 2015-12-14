package com.example.vesprada.controlpelicula;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Pc on 14/12/2015.
 */
public class DirectorDAO {
    private DBHelperControlPeliculas dbHelperDirector;

    public DirectorDAO(Context context) { dbHelperDirector = new DBHelperControlPeliculas(context); }

    public int insert(Director director) {

        SQLiteDatabase db = dbHelperDirector.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Director.KEY_ID, director.ID);
        values.put(Director.KEY_nombre_completo, director.nombre_completo);
        /**
         * en la siguiente linea da error, no estoy seguro pero creo que tiene
         * que ver con el hecho de que es de tipo DATE, da error aqui, en la linea 53
         * y en la linea 141
         */
        values.put(Director.KEY_fecha_nacimiento, director.fecha_nacimiento);

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

        values.put(Director.KEY_ID, director.ID);
        values.put(Director.KEY_nombre_completo, director.nombre_completo);
        /**
         * esperando actoresDAO
         */
        values.put(Director.KEY_fecha_nacimiento, director.fecha_nacimiento);

        db.update(Director.TABLE, values, Director.KEY_ID + "= ?", new String[]{String.valueOf(director.ID)});
    }

    public ArrayList<HashMap<String, String>> getDirectorList() {

        SQLiteDatabase db = dbHelperDirector.getReadableDatabase();
        String selectQuery = "SELECT " +
                Director.KEY_ID + "," +
                Director.KEY_nombre_completo + "," +
                Director.KEY_fecha_nacimiento +
                " FROM " + Director.TABLE;

        ArrayList<HashMap<String, String>> directorLista = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> director = new HashMap<String, String>();
                director.put("ID", cursor.getString(cursor.getColumnIndex(Director.KEY_ID)));
                director.put("nombre_completo", cursor.getString(cursor.getColumnIndex(Director.KEY_nombre_completo)));
                director.put("fecha_nacimiento", cursor.getString(cursor.getColumnIndex(Director.KEY_fecha_nacimiento)));
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
                Director.KEY_nombre_completo + "," +
                Director.KEY_fecha_nacimiento +
                " FROM " + Director.TABLE +
                " WHERE " + Director.KEY_nombre_completo + " LIKE ?";

        ArrayList<HashMap<String, String>> directorLista = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{"%" + nombreDirector + "%"});

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> director = new HashMap<String, String>();
                director.put("ID", cursor.getString(cursor.getColumnIndex(Director.KEY_ID)));
                director.put("nombre_completo", cursor.getString(cursor.getColumnIndex(Director.KEY_nombre_completo)));
                director.put("fecha_nacimiento", cursor.getString(cursor.getColumnIndex(Director.KEY_fecha_nacimiento)));
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
                Director.KEY_nombre_completo + "," +
                Director.KEY_fecha_nacimiento +
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
                director.ID = cursor.getInt(cursor.getColumnIndex(Director.KEY_ID));
                director.nombre_completo = cursor.getString(cursor.getColumnIndex(Director.KEY_nombre_completo));
                /** esta parte la dejo comentada porque no me deja poner GetDate y si pongo
                 * getString me da error, a la espera de el trabajo de Dani en actoresDAO
                 * para ver que ha hecho.
                 */
                director.fecha_nacimiento = cursor.getString(cursor.getColumnIndex(Director.KEY_fecha_nacimiento));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return director;
    }


}








































