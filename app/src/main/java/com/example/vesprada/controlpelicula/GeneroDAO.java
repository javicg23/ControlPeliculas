package com.example.vesprada.controlpelicula;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vesprada on 11/12/2015.
 */
public class GeneroDAO {
    private DBHelperControlPeliculas dbHelperGenero;

    public GeneroDAO(Context context) { dbHelperGenero = new DBHelperControlPeliculas(context); }

    public int insert(Genero genero) {

        SQLiteDatabase db = dbHelperGenero.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Genero.KEY_ID, genero.ID);
        values.put(Genero.KEY_nombre, genero.nombre);

        long ID = db.insert(Genero.TABLE, null, values);
        db.close();
        return (int) ID;
    }

    public void delete(int ID) {

        SQLiteDatabase db = dbHelperGenero.getWritableDatabase();
        db.delete(Genero.TABLE, Genero.KEY_ID + "= ?", new String[] {String.valueOf(ID)});
        db.close();
    }

    public void update (Genero genero) {

        SQLiteDatabase db = dbHelperGenero.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Genero.KEY_ID, genero.ID);
        values.put(Genero.KEY_nombre, genero.nombre);

        db.update(Genero.TABLE, values, Genero.KEY_ID + "= ?", new String[]{String.valueOf(genero.ID)});
    }

    public ArrayList<HashMap<String, String>> getGeneroList() {

        SQLiteDatabase db = dbHelperGenero.getReadableDatabase();
        String selectQuery = "SELECT " +
                Genero.KEY_ID + "," +
                Genero.KEY_nombre +
                " FROM " + Genero.TABLE;

        ArrayList<HashMap<String, String>> generoLista = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> genero = new HashMap<String, String>();
                genero.put("ID", cursor.getString(cursor.getColumnIndex(Genero.KEY_ID)));
                genero.put("nombre", cursor.getString(cursor.getColumnIndex(Genero.KEY_nombre)));
                generoLista.add(genero);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return generoLista;
    }

    public ArrayList<HashMap<String, String>> getGeneroListByName(String nombreGenero) {

        SQLiteDatabase db = dbHelperGenero.getReadableDatabase();
        String selectQuery = "SELECT " +
                Genero.KEY_ID + "," +
                Genero.KEY_nombre +
                " FROM " + Genero.TABLE +
                " WHERE " + Genero.KEY_nombre + " LIKE ?";

        ArrayList<HashMap<String, String>> generoLista = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {"%" + nombreGenero + "%"});

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> genero = new HashMap<String, String>();
                genero.put("ID", cursor.getString(cursor.getColumnIndex(Genero.KEY_ID)));
                genero.put("nombre", cursor.getString(cursor.getColumnIndex(Genero.KEY_nombre)));
                generoLista.add(genero);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return generoLista;

    }

    public Genero getGeneroById(int id) {

        SQLiteDatabase db = dbHelperGenero.getReadableDatabase();
        String selectQuery = "SELECT " +
                Genero.KEY_ID + "," +
                Genero.KEY_nombre + "," +
                " FROM " + Genero.TABLE
                + " WHERE " +
                Genero.KEY_ID + "=?";

        /** No sabemos para que se usa valor entero iCount y en el
         * proyecto de Eloy no se usa en ningún momento.
         * int iCount =0; */

        Genero genero = new Genero();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(id)});

        if (cursor.moveToFirst()) {
            do {
                genero.ID = cursor.getInt(cursor.getColumnIndex(Genero.KEY_ID));
                genero.nombre = cursor.getString(cursor.getColumnIndex(Genero.KEY_nombre));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return genero;
    }


}
































