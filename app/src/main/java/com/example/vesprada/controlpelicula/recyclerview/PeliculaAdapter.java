package com.example.vesprada.controlpelicula.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vesprada.controlpelicula.R;
import com.example.vesprada.controlpelicula.modelo.Pelicula;

import java.util.ArrayList;

/** Clase que sirve para rellenar la informacion de cada item del recyclerview */
public class PeliculaAdapter extends ArrayAdapter<Pelicula>{

    private ArrayList<Pelicula> peliculas;

    public PeliculaAdapter(Context context, ArrayList<Pelicula> peliculas) {
        super(context, R.layout.item_recyclerview, peliculas);
        this.peliculas = peliculas;
    }

    static class ViewHolder{
        ImageView ivPortada;
        TextView tvNombrePelicula;
        TextView tvIdGenero;
        TextView tvPuntuacionPelicula;
        TextView tvEstadoPelicula;
        TextView tvIdPelicula;
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        View item = convertView;
        ViewHolder holder;

        if (item == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            item = inflater.inflate(R.layout.item_recyclerview, null);

            holder = new ViewHolder();
            holder.tvIdPelicula = (TextView) item.findViewById(R.id.tvIdPelicula);
            holder.tvIdGenero = (TextView) item.findViewById(R.id.tvIdGenero);
            holder.ivPortada = (ImageView) item.findViewById(R.id.ivPortada);
            holder.tvNombrePelicula = (TextView) item.findViewById(R.id.tvNombrePelicula);
            holder.tvPuntuacionPelicula = (TextView) item.findViewById(R.id.tvPuntuacionPelicula);
            holder.tvEstadoPelicula = (TextView) item.findViewById(R.id.tvEstadoPelicula);
            item.setTag(holder);
        } else {
            holder = (ViewHolder) item.getTag();
        }
        holder.tvIdPelicula.setText(String.valueOf(peliculas.get(position).id));

        holder.tvIdGenero.setText(String.valueOf(peliculas.get(position).id_genero));

        switch (peliculas.get(position).id_genero) {
            case 1:
                holder.ivPortada.setImageResource(item.getContext().getResources().getIdentifier("action", "drawable", item.getContext().getPackageName()));
                break;
            case 2:
                holder.ivPortada.setImageResource(item.getContext().getResources().getIdentifier("comedy", "drawable", item.getContext().getPackageName()));
                break;
            case 3:
                holder.ivPortada.setImageResource(item.getContext().getResources().getIdentifier("fear", "drawable", item.getContext().getPackageName()));
                break;
            case 4:
                holder.ivPortada.setImageResource(item.getContext().getResources().getIdentifier("scifi", "drawable", item.getContext().getPackageName()));
                break;
            case 5:
                holder.ivPortada.setImageResource(item.getContext().getResources().getIdentifier("fantasy", "drawable", item.getContext().getPackageName()));
                break;
            case 6:
                holder.ivPortada.setImageResource(item.getContext().getResources().getIdentifier("drama", "drawable", item.getContext().getPackageName()));
                break;
            case 7:
                holder.ivPortada.setImageResource(item.getContext().getResources().getIdentifier("romance", "drawable", item.getContext().getPackageName()));
                break;
            case 8:
                holder.ivPortada.setImageResource(item.getContext().getResources().getIdentifier("suspense", "drawable", item.getContext().getPackageName()));
                break;
            case 9:
                holder.ivPortada.setImageResource(item.getContext().getResources().getIdentifier("animation", "drawable", item.getContext().getPackageName()));
                break;
            default:
                holder.ivPortada.setImageResource(item.getContext().getResources().getIdentifier("pe_null", "drawable", item.getContext().getPackageName()));
                break;
        }

        holder.tvNombrePelicula.setText(peliculas.get(position).nombre);
        holder.tvPuntuacionPelicula.setText(String.valueOf(peliculas.get(position).puntuacion));
        switch (peliculas.get(position).id_estado) {
            case 1:
                holder.tvEstadoPelicula.setBackgroundColor(Color.parseColor("#FF0000"));
                break;
            case 2:
                holder.tvEstadoPelicula.setBackgroundColor(Color.parseColor("#FFFF00"));
                break;
            case 3:
                holder.tvEstadoPelicula.setBackgroundColor(Color.parseColor("#00FF00"));
                break;
            case 4:
                holder.tvEstadoPelicula.setBackgroundColor(Color.parseColor("#00FFFF"));
                break;
        }

        return item;
    }
}
