package com.example.vesprada.controlpelicula.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vesprada.controlpelicula.conexion.DBHelperControlPeliculas;
import com.example.vesprada.controlpelicula.modelo.Genero;

import java.util.ArrayList;
import java.util.HashMap;


public class GeneroDAO {
    private static DBHelperControlPeliculas dbHelperGenero;

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





    public Genero getGeneroById(int id) {

        SQLiteDatabase db = dbHelperGenero.getReadableDatabase();
        String selectQuery = "SELECT " +
                Genero.KEY_ID + "," +
                Genero.KEY_Nombre +
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

    public Genero getGeneroByName(String name){
        SQLiteDatabase db = dbHelperGenero.getReadableDatabase();

        String selectQuery = "SELECT " +
                Genero.KEY_ID + "," +
                Genero.KEY_Nombre +
                " FROM " + Genero.TABLE
                + " WHERE " + Genero.KEY_Nombre +  "LIKE ?";

        Genero genero = new Genero();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {"%" + name + "%"});

        if(cursor.moveToFirst()){
            do{
                genero.id = cursor.getInt(cursor.getColumnIndex(Genero.KEY_ID));
                genero.nombre = cursor.getString(cursor.getColumnIndex(Genero.KEY_Nombre));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return genero;

    }
}