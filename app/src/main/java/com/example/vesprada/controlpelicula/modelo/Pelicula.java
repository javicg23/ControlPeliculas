package com.example.vesprada.controlpelicula.modelo;


public class Pelicula {
    //Nombre de la tabla
    public static final String TABLE = "Pelicula";

    //Campos de la tabla
    public static final String KEY_ID = "id";
    public static final String KEY_Nombre = "nombre";
    public static final String KEY_Anyo = "anyo";
    public static final String KEY_Duracion = "duracion";
    public static final String KEY_Sinopsis = "sinopsis";
    public static final String KEY_Puntuacion = "puntuacion";
    public static final String KEY_Estado = "estado";
    public static final String KEY_Id_director = "id_director";
    public static final String KEY_Id_productor = "id_productor";
    public static final String KEY_Id_genero = "id_genero";

    //Atributos

    public int ID;
    public String Nombre;
    public String Anyo;
    public int Duracion;
    public String Sinopsis;
    public float Puntuacion;
    public int Estado;
    public int Id_director;
    public int Id_productor;
    public int Id_genero;



}
