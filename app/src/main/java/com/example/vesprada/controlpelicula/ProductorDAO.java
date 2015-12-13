package com.example.vesprada.controlpelicula;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Pc on 13/12/2015.
 */
public class ProductorDAO {
    private DBHelperControlPeliculas dbHelperProductor;

    public ProductorDAO(Context context) { dbHelperProductor = new DBHelperControlPeliculas(context); }

    public int insert(Productor productor) {

        SQLiteDatabase db = dbHelperProductor.getWritableDatabase();
        ContentValues values = new ContentValues();
       values.put(Productor.KEY_ID, productor.ID);
        values.put(Productor.KEY_nombre, productor.nombre);


        long ID = db.insert(Productor.TABLE, null, values);
        db.close();
        return (int) ID;
    }

    public void delete(int ID) {

        SQLiteDatabase db = dbHelperProductor.getWritableDatabase();
        db.delete(Productor.TABLE, Productor.KEY_ID + "= ?", new String[] {String.valueOf(ID)});
        db.close();
    }

    public void update (Productor productor) {

        SQLiteDatabase db = dbHelperProductor.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Productor.KEY_ID, productor.ID);
        values.put(Productor.KEY_nombre, productor.nombre);

        db.update(Productor.TABLE, values, Productor.KEY_ID + "= ?", new String[]{String.valueOf(productor.ID)});
    }

    public ArrayList<HashMap<String, String>> getProductorList() {

        SQLiteDatabase db = dbHelperProductor.getReadableDatabase();
        String selectQuery = "SELECT " +
                Productor.KEY_ID + "," +
                Productor.KEY_nombre +
                " FROM " + Productor.TABLE;

        ArrayList<HashMap<String, String>> productorLista = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> productor = new HashMap<String, String>();
                productor.put("ID", cursor.getString(cursor.getColumnIndex(Productor.KEY_ID)));
                productor.put("nombre", cursor.getString(cursor.getColumnIndex(Productor.KEY_nombre)));
                productorLista.add(productor);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productorLista;
    }

    public ArrayList<HashMap<String, String>> getProductorListByName(String nombreProductor) {

        SQLiteDatabase db = dbHelperProductor.getReadableDatabase();
        String selectQuery = "SELECT " +
                Productor.KEY_ID + "," +
                Productor.KEY_nombre +
                " FROM " + Productor.TABLE +
                " WHERE " + Productor.KEY_nombre + " LIKE ?";

        ArrayList<HashMap<String, String>> productorLista = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {"%" + nombreProductor + "%"});

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> productor = new HashMap<String, String>();
                productor.put("ID", cursor.getString(cursor.getColumnIndex(Productor.KEY_ID)));
                productor.put("nombre", cursor.getString(cursor.getColumnIndex(Productor.KEY_nombre)));
                productorLista.add(productor);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productorLista;
    }

    public Productor getProductorById(int id) {

        SQLiteDatabase db = dbHelperProductor.getReadableDatabase();
        String selectQuery = "SELECT " +
                Productor.KEY_ID + "," +
                Productor.KEY_nombre + "," +
                " FROM " + Productor.TABLE
                + " WHERE " +
                Productor.KEY_ID + "=?";

        /**
         * No sabemos para que se usa valor entero iCount y en el
         * proyecto de Eloy no se usa en ningún momento.
         * int iCount =0
         */

        Productor productor = new Productor();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(id)});

        if (cursor.moveToFirst()) {
            do {
                productor.ID = cursor.getInt(cursor.getColumnIndex(Productor.KEY_ID));
                productor.nombre = cursor.getString(cursor.getColumnIndex(Productor.KEY_nombre));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productor;
    }










































}
