package com.example.vesprada.controlpelicula.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vesprada.controlpelicula.conexion.DBHelperControlPeliculas;
import com.example.vesprada.controlpelicula.modelo.Productor;

import java.util.ArrayList;

public class ProductorDAO {
    private static DBHelperControlPeliculas dbHelperProductor;

    public ProductorDAO(Context context) { dbHelperProductor = new DBHelperControlPeliculas(context); }

    public int insert(Productor productor) {

        SQLiteDatabase db = dbHelperProductor.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Productor.KEY_Nombre, productor.nombre);


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

        values.put(Productor.KEY_Nombre, productor.nombre);

        db.update(Productor.TABLE, values, Productor.KEY_ID + "= ?", new String[]{String.valueOf(productor.id)});
    }

    public ArrayList<Productor> getProductorList() {

        SQLiteDatabase db = dbHelperProductor.getReadableDatabase();
        String selectQuery = "SELECT " +
                Productor.KEY_ID + "," +
                Productor.KEY_Nombre +
                " FROM " + Productor.TABLE;

        ArrayList<Productor> productorLista = new ArrayList<Productor>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Productor productor = new Productor();
                productor.id = cursor.getInt(cursor.getColumnIndex(Productor.KEY_ID));
                productor.nombre = cursor.getString(cursor.getColumnIndex(Productor.KEY_Nombre));
                productorLista.add(productor);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productorLista;
    }

    public ArrayList<Productor> getProductorListByName(String nombreProductor) {

        SQLiteDatabase db = dbHelperProductor.getReadableDatabase();
        String selectQuery = "SELECT " +
                Productor.KEY_ID + "," +
                Productor.KEY_Nombre +
                " FROM " + Productor.TABLE +
                " WHERE " + Productor.KEY_Nombre + " LIKE ?";

        ArrayList<Productor> productorLista = new ArrayList<Productor>();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {"%" + nombreProductor + "%"});

        if (cursor.moveToFirst()) {
            do {
                Productor productor = new Productor();
                productor.id = cursor.getInt(cursor.getColumnIndex(Productor.KEY_ID));
                productor.nombre = cursor.getString(cursor.getColumnIndex(Productor.KEY_Nombre));
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
                Productor.KEY_Nombre +
                " FROM " + Productor.TABLE
                + " WHERE " +
                Productor.KEY_ID + "=?";


        Productor productor = new Productor();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(id)});

        if (cursor.moveToFirst()) {
            do {
                productor.id = cursor.getInt(cursor.getColumnIndex(Productor.KEY_ID));
                productor.nombre = cursor.getString(cursor.getColumnIndex(Productor.KEY_Nombre));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productor;
    }

    public Productor getProductorByName(String name){
        SQLiteDatabase db = dbHelperProductor.getReadableDatabase();

        String selectQuery = "SELECT " +
                Productor.KEY_ID + "," +
                Productor.KEY_Nombre +
                " FROM " + Productor.TABLE
                + " WHERE " + Productor.KEY_Nombre +  " = ?";

        Productor productor = new Productor();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(name)});

        if(cursor.moveToFirst()){
            do{
                productor.id = cursor.getInt(cursor.getColumnIndex(Productor.KEY_ID));
                productor.nombre = cursor.getString(cursor.getColumnIndex(Productor.KEY_Nombre));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return productor;
    }
}