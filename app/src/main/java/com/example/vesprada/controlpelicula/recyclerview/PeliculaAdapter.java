package com.example.vesprada.controlpelicula.recyclerview;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vesprada.controlpelicula.R;
import com.example.vesprada.controlpelicula.modelo.Pelicula;

import java.util.ArrayList;

/** Clase que sirve para rellenar la informacion de cada item del recyclerview */
public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder> {

    private ArrayList<Pelicula> peliculas;

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPortada;
        private TextView tvNombrePelicula;
        private TextView tvIdGenero;
        private TextView tvPuntuacionPelicula;
        private TextView tvEstadoPelicula;
        private TextView tvIdPelicula;

        public PeliculaViewHolder(View itemView) {
            super(itemView);

            tvIdPelicula = (TextView) itemView.findViewById(R.id.tvIdPelicula);
            tvIdGenero = (TextView) itemView.findViewById(R.id.tvIdGenero);
            ivPortada = (ImageView) itemView.findViewById(R.id.ivPortada);
            tvNombrePelicula = (TextView) itemView.findViewById(R.id.tvNombrePelicula);
            tvPuntuacionPelicula = (TextView) itemView.findViewById(R.id.tvPuntuacionPelicula);
            tvEstadoPelicula = (TextView) itemView.findViewById(R.id.tvEstadoPelicula);
        }

        public void bindPelicula(Pelicula pelicula) {

            tvIdPelicula.setText(String.valueOf(pelicula.id));

            tvIdGenero.setText(String.valueOf(pelicula.id_genero));

            switch (pelicula.id_genero) {
                case 1:
                    ivPortada.setImageResource(itemView.getContext().getResources().getIdentifier("action", "drawable", itemView.getContext().getPackageName()));
                    break;
                case 2:
                    ivPortada.setImageResource(itemView.getContext().getResources().getIdentifier("comedy", "drawable", itemView.getContext().getPackageName()));
                    break;
                case 3:
                    ivPortada.setImageResource(itemView.getContext().getResources().getIdentifier("fear", "drawable", itemView.getContext().getPackageName()));
                    break;
                case 4:
                    ivPortada.setImageResource(itemView.getContext().getResources().getIdentifier("scifi", "drawable", itemView.getContext().getPackageName()));
                    break;
                case 5:
                    ivPortada.setImageResource(itemView.getContext().getResources().getIdentifier("fantasy", "drawable", itemView.getContext().getPackageName()));
                    break;
                case 6:
                    ivPortada.setImageResource(itemView.getContext().getResources().getIdentifier("drama", "drawable", itemView.getContext().getPackageName()));
                    break;
                case 7:
                    ivPortada.setImageResource(itemView.getContext().getResources().getIdentifier("romance", "drawable", itemView.getContext().getPackageName()));
                    break;
                case 8:
                    ivPortada.setImageResource(itemView.getContext().getResources().getIdentifier("suspense", "drawable", itemView.getContext().getPackageName()));
                    break;
                case 9:
                    ivPortada.setImageResource(itemView.getContext().getResources().getIdentifier("animation", "drawable", itemView.getContext().getPackageName()));
                    break;
                default:
                    ivPortada.setImageResource(itemView.getContext().getResources().getIdentifier("pe_null", "drawable", itemView.getContext().getPackageName()));
                    break;
            }

            tvNombrePelicula.setText(pelicula.nombre);
            tvPuntuacionPelicula.setText(String.valueOf(pelicula.puntuacion));
            switch (pelicula.id_estado) {
                case 1:
                    tvEstadoPelicula.setBackgroundColor(Color.parseColor("#FF0000"));
                    break;
                case 2:
                    tvEstadoPelicula.setBackgroundColor(Color.parseColor("#FFFF00"));
                    break;
                case 3:
                    tvEstadoPelicula.setBackgroundColor(Color.parseColor("#00FF00"));
                    break;
                case 4:
                    tvEstadoPelicula.setBackgroundColor(Color.parseColor("#00FFFF"));
                    break;
            }
        }
    }

    public PeliculaAdapter (ArrayList<Pelicula> peliculas){
        this.peliculas = peliculas;
    }

    @Override
    public PeliculaViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recyclerview, viewGroup, false);

        PeliculaViewHolder pvh = new PeliculaViewHolder(itemView);

        return pvh;
    }

    @Override
    public void onBindViewHolder(PeliculaViewHolder viewHolder, int pos){

        Pelicula item = peliculas.get(pos);

        viewHolder.bindPelicula(item);
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }
}
