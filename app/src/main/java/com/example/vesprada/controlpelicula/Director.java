package com.example.vesprada.controlpelicula;

import java.util.Date;

/**
 * Created by Pc on 14/12/2015.
 */
public class Director {
    public static final String TABLE = "Director";

    public static final String KEY_ID = "ID";
    public static final String KEY_nombre_completo = "nombre_completo";
    public static final String KEY_fecha_nacimiento = "fecha_nacimiento";

    public int ID;
    public String nombre_completo;
    public Date fecha_nacimiento;
}
