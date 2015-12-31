package com.example.vesprada.controlpelicula.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vesprada.controlpelicula.R;
import com.example.vesprada.controlpelicula.modelo.Pelicula;

public class DetallePelicula extends AppCompatActivity {
    private TextView tvTituloDetalle;
    private ImageView ivPortadaDetalle;
    private TextView tvPuntuacionDetalle;
    private TextView tvAnyoDetalle;
    private TextView tvDuracionDetalle;
    private TextView tvGeneroDetalle;
    private TextView tvDirectorDetalle;
    private TextView tvProductorDetalle;
    private TextView tvActoresDetalle;
    private TextView tvSinopsisDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallepelicula);
        /*Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Pelicula value = extras.getClass(pelicula);
        }*/
        tvTituloDetalle = (TextView)findViewById(R.id.tvTituloDetalle);
        ivPortadaDetalle = (ImageView)findViewById(R.id.ivPortadaDetalle);
        tvPuntuacionDetalle = (TextView)findViewById(R.id.tvPuntuacionDetalle);
        tvAnyoDetalle = (TextView)findViewById(R.id.tvAnyoDetalle);
        tvDuracionDetalle = (TextView)findViewById(R.id.tvDuracionDetalle);
        tvGeneroDetalle = (TextView)findViewById(R.id.tvGeneroDetalle);
        tvDirectorDetalle = (TextView)findViewById(R.id.tvDirectorDetalle);
        tvProductorDetalle = (TextView)findViewById(R.id.tvProductorDetalle);
        tvActoresDetalle = (TextView)findViewById(R.id.tvActoresDetalle);
        tvSinopsisDetalle = (TextView)findViewById(R.id.tvSinopsisDetalle);
    }
}