package com.example.vesprada.controlpelicula.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vesprada.controlpelicula.conexion.DBHelperControlPeliculas;
import com.example.vesprada.controlpelicula.modelo.Actor_Pelicula;
import com.example.vesprada.controlpelicula.modelo.Pelicula;

import java.util.ArrayList;


public class PeliculaDAO {
    private static DBHelperControlPeliculas dbHelperPelicula;

    public PeliculaDAO(Context context) {
        dbHelperPelicula = new DBHelperControlPeliculas(context);
    }

    public int insert(Pelicula pelicula) {

        //Le damos valor
        SQLiteDatabase db = dbHelperPelicula.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Pelicula.KEY_Nombre, pelicula.nombre);
        values.put(Pelicula.KEY_Anyo, pelicula.anyo);
        values.put(Pelicula.KEY_Duracion, pelicula.duracion);
        values.put(Pelicula.KEY_Sinopsis, pelicula.sinopsis);
        values.put(Pelicula.KEY_Puntuacion, pelicula.puntuacion);
        values.put(Pelicula.KEY_Id_director, pelicula.id_director);
        values.put(Pelicula.KEY_Id_genero, pelicula.id_genero);
        values.put(Pelicula.KEY_Id_productor, pelicula.id_productor);
        values.put(Pelicula.KEY_Id_estado, pelicula.id_estado);

        // hacemos el insert
        long pelicula_id = db.insert(Pelicula.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) pelicula_id;
    }

    public void delete(int pelicula_id) {

        SQLiteDatabase db = dbHelperPelicula.getWritableDatabase();
        db.delete(Pelicula.TABLE, Pelicula.KEY_ID + "= ?", new String[]{String.valueOf(pelicula_id)});
        db.close();
    }

    public void update(Pelicula pelicula,int idPelicula) {

        SQLiteDatabase db = dbHelperPelicula.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Pelicula.KEY_Nombre, pelicula.nombre);
        values.put(Pelicula.KEY_Anyo, pelicula.anyo);
        values.put(Pelicula.KEY_Duracion, pelicula.duracion);
        values.put(Pelicula.KEY_Sinopsis, pelicula.sinopsis);
        values.put(Pelicula.KEY_Puntuacion, pelicula.puntuacion);
        values.put(Pelicula.KEY_Id_director, pelicula.id_director);
        values.put(Pelicula.KEY_Id_genero, pelicula.id_genero);
        values.put(Pelicula.KEY_Id_productor, pelicula.id_productor);
        values.put(Pelicula.KEY_Id_estado, pelicula.id_estado);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Pelicula.TABLE, values, Pelicula.KEY_ID + " =?", new String[]{String.valueOf(idPelicula)});

        db.close(); // Closing database connection
    }

    public ArrayList<Pelicula> getPeliculaList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelperPelicula.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Pelicula.KEY_ID + "," +
                Pelicula.KEY_Nombre + "," +
                Pelicula.KEY_Anyo + "," +
                Pelicula.KEY_Duracion + "," +
                Pelicula.KEY_Sinopsis + "," +
                Pelicula.KEY_Puntuacion + "," +
                Pelicula.KEY_Id_director + "," +
                Pelicula.KEY_Id_genero + "," +
                Pelicula.KEY_Id_productor + "," +
                Pelicula.KEY_Id_estado +
                " FROM " + Pelicula.TABLE;

        ArrayList<Pelicula> peliculaList = new ArrayList<Pelicula>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                Pelicula p = new Pelicula();
                p.id = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_ID));
                p.nombre = cursor.getString(cursor.getColumnIndex(Pelicula.KEY_Nombre));
                p.puntuacion = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Puntuacion));
                p.sinopsis = cursor.getString(cursor.getColumnIndex(Pelicula.KEY_Sinopsis));
                p.anyo = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Anyo));
                p.duracion = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Duracion));
                p.id_director = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Id_director));
                p.id_estado = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Id_estado));
                p.id_genero = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Id_genero));
                p.id_productor =  cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Id_productor));
                peliculaList.add(p);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return peliculaList;

    }

    public ArrayList<Pelicula>  getPeliculaListByName(String nombrePelicula) {
        //Open connection to read only

        SQLiteDatabase db = dbHelperPelicula.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Pelicula.KEY_ID + "," +
                Pelicula.KEY_Nombre + "," +
                Pelicula.KEY_Anyo + "," +
                Pelicula.KEY_Duracion + "," +
                Pelicula.KEY_Sinopsis + "," +
                Pelicula.KEY_Puntuacion + "," +
                Pelicula.KEY_Id_director + "," +
                Pelicula.KEY_Id_genero + "," +
                Pelicula.KEY_Id_productor + "," +
                Pelicula.KEY_Id_estado +
                " FROM " + Pelicula.TABLE +
                " WHERE " + Pelicula.KEY_Nombre + " LIKE ?";



        ArrayList<Pelicula> peliculaList = new ArrayList<Pelicula>();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {"%" + nombrePelicula + "%"});
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {

            do {
                Pelicula p = new Pelicula();
                p.id = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_ID));
                p.nombre = cursor.getString(cursor.getColumnIndex(Pelicula.KEY_Nombre));
                p.puntuacion = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Puntuacion));
                p.sinopsis = cursor.getString(cursor.getColumnIndex(Pelicula.KEY_Sinopsis));
                p.anyo = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Anyo));
                p.duracion = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Duracion));
                p.id_director = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Id_director));
                p.id_estado = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Id_estado));
                p.id_genero = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Id_genero));
                p.id_productor =  cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Id_productor));

                peliculaList.add(p);


            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return peliculaList;
    }

    public Pelicula getPeliculaById(int id) {

        SQLiteDatabase db = dbHelperPelicula.getReadableDatabase();
        String selectQuery = "SELECT " +
                Pelicula.KEY_ID + "," +
                Pelicula.KEY_Nombre + "," +
                Pelicula.KEY_Anyo + "," +
                Pelicula.KEY_Duracion + "," +
                Pelicula.KEY_Sinopsis + "," +
                Pelicula.KEY_Puntuacion + "," +
                Pelicula.KEY_Id_director + "," +
                Pelicula.KEY_Id_genero + "," +
                Pelicula.KEY_Id_productor + "," +
                Pelicula.KEY_Id_estado +
                " FROM " + Pelicula.TABLE
                + " WHERE " + Pelicula.KEY_ID + "=?";

        Pelicula p = new Pelicula();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(id)});

        if (cursor.moveToFirst()) {
            do {
                p.id = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_ID));
                p.nombre = cursor.getString(cursor.getColumnIndex(Pelicula.KEY_Nombre));
                p.puntuacion = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Puntuacion));
                p.sinopsis = cursor.getString(cursor.getColumnIndex(Pelicula.KEY_Sinopsis));
                p.anyo = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Anyo));
                p.duracion = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Duracion));
                p.id_director = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Id_director));
                p.id_estado = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Id_estado));
                p.id_genero = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Id_genero));
                p.id_productor =  cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Id_productor));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return p;
    }

    public ArrayList<Pelicula>  getPeliculaListByIdDirector(int idDirector) {
        SQLiteDatabase db = dbHelperPelicula.getReadableDatabase();
        String selectQuery = "SELECT " +
                Pelicula.KEY_ID + "," +
                Pelicula.KEY_Nombre + "," +
                Pelicula.KEY_Anyo + "," +
                Pelicula.KEY_Duracion + "," +
                Pelicula.KEY_Sinopsis + "," +
                Pelicula.KEY_Puntuacion + "," +
                Pelicula.KEY_Id_director + "," +
                Pelicula.KEY_Id_genero + "," +
                Pelicula.KEY_Id_productor + "," +
                Pelicula.KEY_Id_estado +
                " FROM " + Pelicula.TABLE
                + " WHERE " + Pelicula.KEY_Id_director + "=?";

        ArrayList<Pelicula> peliculaList = new ArrayList<Pelicula>();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(idDirector)});

        if (cursor.moveToFirst()){
            do{

                Pelicula pelicula = new Pelicula();
                pelicula.id = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_ID));
                pelicula.nombre = cursor.getString(cursor.getColumnIndex(Pelicula.KEY_Nombre));
                pelicula.puntuacion = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Puntuacion));
                pelicula.sinopsis = cursor.getString(cursor.getColumnIndex(Pelicula.KEY_Sinopsis));
                pelicula.anyo = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Anyo));
                pelicula.duracion = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Duracion));
                pelicula.id_director = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Id_director));
                pelicula.id_estado = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Id_estado));
                pelicula.id_genero = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Id_genero));
                pelicula.id_productor =  cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Id_productor));

                peliculaList.add(pelicula);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return peliculaList;
    }
    public ArrayList<Pelicula>  getPeliculaListByIdActor(int idActor){
        SQLiteDatabase db = dbHelperPelicula.getReadableDatabase();

        String selectQuery = "SELECT " +
                Pelicula.KEY_ID + "," +
                Pelicula.KEY_Nombre + "," +
                Pelicula.KEY_Anyo + "," +
                Pelicula.KEY_Duracion + "," +
                Pelicula.KEY_Sinopsis + "," +
                Pelicula.KEY_Puntuacion + "," +
                Pelicula.KEY_Id_director + "," +
                Pelicula.KEY_Id_genero + "," +
                Pelicula.KEY_Id_productor + "," +
                Pelicula.KEY_Id_estado +
                " FROM " + Pelicula.TABLE
                + " WHERE " + Pelicula.KEY_ID + " IN " + " (SELECT " + Actor_Pelicula.KEY_ID_Pelicula + " FROM " + Actor_Pelicula.TABLE
                + " WHERE " + Actor_Pelicula.KEY_ID_Actor + "=?)";
        ArrayList<Pelicula> peliculaList = new ArrayList<Pelicula>();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(idActor)});

        if (cursor.moveToFirst()){
            do{
                Pelicula pelicula = new Pelicula();
                pelicula.id = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_ID));
                pelicula.nombre = cursor.getString(cursor.getColumnIndex(Pelicula.KEY_Nombre));
                pelicula.puntuacion = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Puntuacion));
                pelicula.sinopsis = cursor.getString(cursor.getColumnIndex(Pelicula.KEY_Sinopsis));
                pelicula.anyo = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Anyo));
                pelicula.duracion = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Duracion));
                pelicula.id_director = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Id_director));
                pelicula.id_estado = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Id_estado));
                pelicula.id_genero = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Id_genero));
                pelicula.id_productor =  cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_Id_productor));

                peliculaList.add(pelicula);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return peliculaList;
    }

    public Pelicula getPeliculaByName(String name){
        SQLiteDatabase db = dbHelperPelicula.getReadableDatabase();

        String selectQuery = "SELECT " +
                Pelicula.KEY_ID + "," +
                Pelicula.KEY_Nombre +
                " FROM " + Pelicula.TABLE
                + " WHERE " + Pelicula.KEY_Nombre +  " = ?";

        Pelicula pelicula = new Pelicula();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(name)});

        if(cursor.moveToFirst()){
            do{
                pelicula.id = cursor.getInt(cursor.getColumnIndex(Pelicula.KEY_ID));
                pelicula.nombre = cursor.getString(cursor.getColumnIndex(Pelicula.KEY_Nombre));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return pelicula;
    }
}