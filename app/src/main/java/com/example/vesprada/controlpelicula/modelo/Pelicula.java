package com.example.vesprada.controlpelicula.modelo;


public class Pelicula {

    //Nombre de la tabla
    public static final String TABLE = "Pelicula";

    //Campos de la tabla
    public static final String KEY_ID = "id";
    public static final String KEY_Nombre = "nombre";
    public static final String KEY_Anyo = "anyo";
    public static final String KEY_Portada = "portada";
    public static final String KEY_Duracion = "duracion";
    public static final String KEY_Sinopsis = "sinopsis";
    public static final String KEY_Puntuacion = "puntuacion";
    public static final String KEY_Id_director = "id_director";
    public static final String KEY_Id_productor = "id_productor";
    public static final String KEY_Id_genero = "id_genero";
    public static final String KEY_Id_estado = "id_estado";

    //Atributos

    public int id;
    public String nombre;
    public int anyo;
    public String portada;
    public int duracion;
    public String sinopsis;
    public float puntuacion;
    public int id_director;
    public int id_productor;
    public int id_genero;
    public int id_estado;
}
