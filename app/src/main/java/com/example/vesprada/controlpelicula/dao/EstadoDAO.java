package com.example.vesprada.controlpelicula.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vesprada.controlpelicula.conexion.DBHelperControlPeliculas;
import com.example.vesprada.controlpelicula.modelo.Estado;

import java.util.ArrayList;

public class EstadoDAO {
    private DBHelperControlPeliculas dbhelperEstado;

    public EstadoDAO(Context context) { dbhelperEstado = new DBHelperControlPeliculas(context);}

    public int insert(Estado estado) {

        SQLiteDatabase db = dbhelperEstado.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Estado.KEY_ID, estado.id);
        values.put(Estado.KEY_Estado, estado.nombre_estado);

        long ID = db.insert(Estado.TABLE, null, values);
        db.close();
        return (int) ID;
    }

    public void delete(int ID) {

        SQLiteDatabase db = dbhelperEstado.getWritableDatabase();
        db.delete(Estado.TABLE, Estado.KEY_ID + "= ?", new String[] {String.valueOf(ID)});
        db.close();
    }

    public void update (Estado estado) {

        SQLiteDatabase db = dbhelperEstado.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Estado.KEY_ID, estado.id);
        values.put(Estado.KEY_Estado, estado.nombre_estado);

        db.update(Estado.TABLE, values, Estado.KEY_ID + "= ?", new String[]{String.valueOf(estado.id)});
    }

    public ArrayList<Estado> getEstadoList() {

        SQLiteDatabase db = dbhelperEstado.getReadableDatabase();
        String selectQuery = "SELECT " +
                Estado.KEY_ID + "," +
                Estado.KEY_Estado +
                " FROM " + Estado.TABLE;

        ArrayList<Estado> estadoList = new ArrayList<Estado>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Estado e = new Estado();
                e.id = cursor.getInt(cursor.getColumnIndex(Estado.KEY_ID));
                e.nombre_estado = cursor.getString(cursor.getColumnIndex(Estado.KEY_Estado));
                estadoList.add(e);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return estadoList;

    }

    public Estado getEstadoById(int id) {

        SQLiteDatabase db = dbhelperEstado.getReadableDatabase();
        String selectQuery = "SELECT " +
                Estado.KEY_ID + "," +
                Estado.KEY_Estado +
                " FROM " + Estado.TABLE +
                " WHERE " + Estado.KEY_ID + "=?";

        Estado e = new Estado();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(id)});

        if (cursor.moveToFirst()) {
            do {
                e.id = cursor.getInt(cursor.getColumnIndex(Estado.KEY_ID));
                e.nombre_estado = cursor.getString(cursor.getColumnIndex(Estado.KEY_Estado));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return e;
    }
}
