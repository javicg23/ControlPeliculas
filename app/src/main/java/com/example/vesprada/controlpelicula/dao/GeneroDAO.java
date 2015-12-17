package com.example.vesprada.controlpelicula.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vesprada.controlpelicula.conexion.DBHelperControlPeliculas;

import java.util.ArrayList;
import java.util.HashMap;


public class GeneroDAO {
    private DBHelperControlPeliculas dbHelperGenero;

    public GeneroDAO(Context context) { dbHelperGenero = new DBHelperControlPeliculas(context); }

    public int insert(Genero genero) {

        SQLiteDatabase db = dbHelperGenero.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Genero.KEY_ID, genero.id);
        values.put(Genero.KEY_Nombre, genero.nombre);

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

        values.put(Genero.KEY_ID, genero.id);
        values.put(Genero.KEY_Nombre, genero.nombre);

        db.update(Genero.TABLE, values, Genero.KEY_ID + "= ?", new String[]{String.valueOf(genero.id)});
    }

    public ArrayList<HashMap<String, String>> getGeneroList() {

        SQLiteDatabase db = dbHelperGenero.getReadableDatabase();
        String selectQuery = "SELECT " +
                Genero.KEY_ID + "," +
                Genero.KEY_Nombre +
                " FROM " + Genero.TABLE;

        ArrayList<HashMap<String, String>> generoLista = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> genero = new HashMap<String, String>();
                genero.put("id", cursor.getString(cursor.getColumnIndex(Genero.KEY_ID)));
                genero.put("nombre", cursor.getString(cursor.getColumnIndex(Genero.KEY_Nombre)));
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
                Genero.KEY_Nombre +
                " FROM " + Genero.TABLE +
                " WHERE " + Genero.KEY_Nombre + " LIKE ?";

        ArrayList<HashMap<String, String>> generoLista = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {"%" + nombreGenero + "%"});

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> genero = new HashMap<String, String>();
                genero.put("id", cursor.getString(cursor.getColumnIndex(Genero.KEY_ID)));
                genero.put("nombre", cursor.getString(cursor.getColumnIndex(Genero.KEY_Nombre)));
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
                Genero.KEY_Nombre + "," +
                " FROM " + Genero.TABLE
                + " WHERE " +
                Genero.KEY_ID + "=?";


        Genero genero = new Genero();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(id)});

        if (cursor.moveToFirst()) {
            do {
                genero.id = cursor.getInt(cursor.getColumnIndex(Genero.KEY_ID));
                genero.nombre = cursor.getString(cursor.getColumnIndex(Genero.KEY_Nombre));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return genero;
    }


}