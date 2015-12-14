package com.example.vesprada.controlpelicula.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vesprada.controlpelicula.conexion.DBHelperControlPeliculas;
import com.example.vesprada.controlpelicula.modelo.Actor;

import java.util.ArrayList;
import java.util.HashMap;


public class ActorDAO {

    private DBHelperControlPeliculas dbHelper;

    public ActorDAO(Context context) {
        dbHelper = new DBHelperControlPeliculas(context);
    }

    public int insert(Actor actor) {

        //Cojemos los valores
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Actor.KEY_ID, actor.ID);
        values.put(Actor.KEY_Nombre_completo, actor.Nombre_completo);

        // hacemos el insert
        long actor_ID = db.insert(Actor.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) actor_ID;
    }

    public void delete(int actor_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Actor.TABLE, Actor.KEY_ID + "= ?", new String[]{String.valueOf(actor_Id)});
        db.close();
    }

    public void update(Actor actor) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Actor.KEY_ID, actor.ID);
        values.put(Actor.KEY_Nombre_completo,actor.Nombre_completo);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Actor.TABLE, values, Actor.KEY_ID + "= ?", new String[]{String.valueOf(actor.ID)});
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>>  getActorList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Actor.KEY_ID + "," +
                Actor.KEY_Nombre_completo +
                " FROM " + Actor.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> actorList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> student = new HashMap<String, String>();
                student.put("id", cursor.getString(cursor.getColumnIndex(Actor.KEY_ID)));
                student.put("name", cursor.getString(cursor.getColumnIndex(Actor.KEY_Nombre_completo)));
                actorList.add(student);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return actorList;

    }

    public ArrayList<HashMap<String, String>>  getActorListByName(String nameStudentSearch) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Actor.KEY_ID + "," +
                Actor.KEY_Nombre_completo +
                " FROM " + Actor.TABLE +
                " WHERE " + Actor.KEY_Nombre_completo + " LIKE ?";

        //Student student = new Student();
        ArrayList<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {"%" + nameStudentSearch + "%"});
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> student = new HashMap<String, String>();
                student.put("id", cursor.getString(cursor.getColumnIndex(Actor.KEY_ID)));
                student.put("name", cursor.getString(cursor.getColumnIndex(Actor.KEY_Nombre_completo)));
                studentList.add(student);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;

    }
    public Actor getActorById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Actor.KEY_ID + "," +
                Actor.KEY_Nombre_completo +
                " FROM " + Actor.TABLE
                + " WHERE " +
                Actor.KEY_ID + "=?";

        //int iCount = 0;
        Actor actor = new Actor();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                actor.ID =cursor.getInt(cursor.getColumnIndex(Actor.KEY_ID));
                actor.Nombre_completo =cursor.getString(cursor.getColumnIndex(Actor.KEY_Nombre_completo));


            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return actor;
    }
}
