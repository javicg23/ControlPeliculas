package com.example.vesprada.controlpelicula.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vesprada.controlpelicula.modelo.Actor;
import com.example.vesprada.controlpelicula.modelo.Pelicula;

import java.util.ArrayList;
import java.util.HashMap;


public class PeliculaDAO {
    private DBHelper dbHelper;

    public PeliculaDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Pelicula pelicula) {

        //Le damos valor
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Pelicula.KEY_ID, pelicula.ID);
        values.put(Pelicula.KEY_Nombre, pelicula.Nombre);
        values.put(Pelicula.KEY_Anyo, pelicula.Anyo);
        values.put(Pelicula.KEY_Duracion, pelicula.Duracion);
        values.put(Pelicula.KEY_Sinopsis, pelicula.Sinopsis);
        values.put(Pelicula.KEY_Puntuacion, pelicula.Puntuacion);
        values.put(Pelicula.KEY_Estado, pelicula.Estado);
        values.put(Pelicula.KEY_Id_director, pelicula.Id_director);
        values.put(Pelicula.KEY_Id_genero, pelicula.Id_genero);
        values.put(Pelicula.KEY_Id_productor, pelicula.Id_productor);

        // hacemos el insert
        long actor_ID = db.insert(Actor.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) actor_ID;
    }

    public void delete(int pelicula_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Pelicula.TABLE, Pelicula.KEY_ID + "= ?", new String[]{String.valueOf(pelicula_Id)});
        db.close();
    }

    public void update(Pelicula pelicula) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Pelicula.KEY_ID, pelicula.ID);
        values.put(Pelicula.KEY_Nombre, pelicula.Nombre);
        values.put(Pelicula.KEY_Anyo, pelicula.Anyo);
        values.put(Pelicula.KEY_Duracion, pelicula.Duracion);
        values.put(Pelicula.KEY_Sinopsis, pelicula.Sinopsis);
        values.put(Pelicula.KEY_Puntuacion, pelicula.Puntuacion);
        values.put(Pelicula.KEY_Estado, pelicula.Estado);
        values.put(Pelicula.KEY_Id_director, pelicula.Id_director);
        values.put(Pelicula.KEY_Id_genero, pelicula.Id_genero);
        values.put(Pelicula.KEY_Id_productor, pelicula.Id_productor);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Actor.TABLE, values, Actor.KEY_ID + "= ?", new String[]{String.valueOf(pelicula.ID)});
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getPeliculaList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Pelicula.KEY_ID + "," +
                Pelicula.KEY_Nombre +
                Pelicula.KEY_Anyo +
                Pelicula.KEY_Duracion +
                Pelicula.KEY_Sinopsis +
                Pelicula.KEY_Puntuacion +
                Pelicula.KEY_Estado +
                Pelicula.KEY_Id_director +
                Pelicula.KEY_Id_genero +
                Pelicula.KEY_Id_productor +
                " FROM " + Actor.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> peliculaList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> student = new HashMap<String, String>();
                student.put("id", cursor.getString(cursor.getColumnIndex(Actor.KEY_ID)));
                student.put("name", cursor.getString(cursor.getColumnIndex(Actor.KEY_Nombre_completo)));
                peliculaList.add(student);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return peliculaList;

    }

    public ArrayList<HashMap<String, String>>  getActorListByName(String nameStudentSearch) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Pelicula.KEY_ID + "," +
                Pelicula.KEY_Nombre +
                Pelicula.KEY_Anyo +
                Pelicula.KEY_Duracion +
                Pelicula.KEY_Sinopsis +
                Pelicula.KEY_Puntuacion +
                Pelicula.KEY_Estado +
                Pelicula.KEY_Id_director +
                Pelicula.KEY_Id_genero +
                Pelicula.KEY_Id_productor +
                " FROM " + Actor.TABLE +
                " WHERE " + Actor.KEY_Nombre_completo + " LIKE ?";

        //Student student = new Student();
        ArrayList<HashMap<String, String>> peliculaList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {"%" + nameStudentSearch + "%"});
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> student = new HashMap<String, String>();
                student.put("id", cursor.getString(cursor.getColumnIndex(Actor.KEY_ID)));
                student.put("name", cursor.getString(cursor.getColumnIndex(Actor.KEY_Nombre_completo)));
                peliculaList.add(student);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return peliculaList;

    }
}
