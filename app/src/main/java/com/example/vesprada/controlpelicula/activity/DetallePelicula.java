package com.example.vesprada.controlpelicula.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vesprada.controlpelicula.R;
import com.example.vesprada.controlpelicula.dao.ActorDAO;
import com.example.vesprada.controlpelicula.dao.Actor_PeliculaDAO;
import com.example.vesprada.controlpelicula.dao.DirectorDAO;
import com.example.vesprada.controlpelicula.dao.GeneroDAO;
import com.example.vesprada.controlpelicula.dao.PeliculaDAO;
import com.example.vesprada.controlpelicula.dao.ProductorDAO;
import com.example.vesprada.controlpelicula.modelo.Actor;
import com.example.vesprada.controlpelicula.modelo.Actor_Pelicula;
import com.example.vesprada.controlpelicula.modelo.Director;
import com.example.vesprada.controlpelicula.modelo.Genero;
import com.example.vesprada.controlpelicula.modelo.Pelicula;
import com.example.vesprada.controlpelicula.modelo.Productor;

import java.util.ArrayList;

/** Activty que sirve para sacar toda la información de una película */
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
    private ImageButton btnBorrar;
    private ImageButton btnEditar;


    private PeliculaDAO conectorPelicula = new PeliculaDAO(this);
    private GeneroDAO conectorGenero = new GeneroDAO(this);
    private ActorDAO conectorActor = new ActorDAO(this);
    private Actor_PeliculaDAO conectorActorPelicula = new Actor_PeliculaDAO(this);
    private ProductorDAO conectorProductor = new ProductorDAO(this);
    private DirectorDAO conectorDirector = new DirectorDAO(this);
    private int idPelicula;
    private Pelicula pelicula;
    private ArrayList<Actor_Pelicula> listaActoresEnteros = new ArrayList<Actor_Pelicula>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallepelicula);

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
        btnEditar = (ImageButton) findViewById(R.id.btnEditar);
        btnBorrar = (ImageButton) findViewById(R.id.btnBorrar);

        /** Nos traemos el id del item en el cual hayamos pulsado del recyclerview */

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idPelicula = extras.getInt("id_pelicula");
        }
        fillFields();
    }

    @Override
    protected void onResume(){
        super.onResume();
        fillFields();
    }

    protected void fillFields(){

        pelicula = conectorPelicula.getPeliculaById(idPelicula);

        /** Le ponemos una imagen diferente dependiendo del género al que corresponda */
        switch (pelicula.id_genero) {
            case 1:
                ivPortadaDetalle.setImageResource(this.getApplicationContext().getResources().getIdentifier("action", "drawable", this.getApplicationContext().getPackageName()));
                break;
            case 2:
                ivPortadaDetalle.setImageResource(this.getApplicationContext().getResources().getIdentifier("comedy", "drawable", this.getApplicationContext().getPackageName()));
                break;
            case 3:
                ivPortadaDetalle.setImageResource(this.getApplicationContext().getResources().getIdentifier("fear", "drawable", this.getApplicationContext().getPackageName()));
                break;
            case 4:
                ivPortadaDetalle.setImageResource(this.getApplicationContext().getResources().getIdentifier("scifi", "drawable", this.getApplicationContext().getPackageName()));
                break;
            case 5:
                ivPortadaDetalle.setImageResource(this.getApplicationContext().getResources().getIdentifier("fantasy", "drawable", this.getApplicationContext().getPackageName()));
                break;
            case 6:
                ivPortadaDetalle.setImageResource(this.getApplicationContext().getResources().getIdentifier("drama", "drawable", this.getApplicationContext().getPackageName()));
                break;
            case 7:
                ivPortadaDetalle.setImageResource(this.getApplicationContext().getResources().getIdentifier("romance", "drawable", this.getApplicationContext().getPackageName()));
                break;
            case 8:
                ivPortadaDetalle.setImageResource(this.getApplicationContext().getResources().getIdentifier("suspense", "drawable", this.getApplicationContext().getPackageName()));
                break;
            case 9:
                ivPortadaDetalle.setImageResource(this.getApplicationContext().getResources().getIdentifier("animation", "drawable", this.getApplicationContext().getPackageName()));
                break;
            default:
                ivPortadaDetalle.setImageResource(this.getApplicationContext().getResources().getIdentifier("pe_null", "drawable", this.getApplicationContext().getPackageName()));
                break;
        }

        tvTituloDetalle.setText(pelicula.nombre);

        /** Dependiendo de la puntuacion se muestra diferente el color donde sale el número */
        int puntuacionPeliDetalle = pelicula.puntuacion;
        tvPuntuacionDetalle.setText(String.valueOf(puntuacionPeliDetalle));
        if (puntuacionPeliDetalle >= 0 && puntuacionPeliDetalle <= 35){
            tvPuntuacionDetalle.setBackgroundColor(Color.parseColor("#FF0000"));
        }
        else if (puntuacionPeliDetalle > 35 && puntuacionPeliDetalle < 70) {
            tvPuntuacionDetalle.setBackgroundColor(Color.parseColor("#DDDD00"));
        }
        else if (puntuacionPeliDetalle >= 70 && puntuacionPeliDetalle <= 100){
            tvPuntuacionDetalle.setBackgroundColor(Color.parseColor("#00FF00"));
        }

        tvAnyoDetalle.setText(String.valueOf(pelicula.anyo));
        tvDuracionDetalle.setText(String.valueOf(pelicula.duracion));

        Director director = conectorDirector.getDirectorById(pelicula.id_director);

        tvDirectorDetalle.setText(String.valueOf(director.nombre_completo));

        Genero genero = conectorGenero.getGeneroById(pelicula.id_genero);
        tvGeneroDetalle.setText(String.valueOf(genero.nombre));

        Productor productor = conectorProductor.getProductorById(pelicula.id_productor);
        tvProductorDetalle.setText(productor.nombre);

        tvSinopsisDetalle.setText(pelicula.sinopsis);

        /** Para mostrar todos los actores recorremos un arraylist */
        listaActoresEnteros = conectorActorPelicula.getIdActorByIdPelicula(idPelicula);
        String listaActores = "";
        for (int i = 0; i < listaActoresEnteros.size(); i++){
            Actor actor = conectorActor.getActorById(listaActoresEnteros.get(i).id_actor);
            if (i < listaActoresEnteros.size()-1){
                listaActores = listaActores + actor.nombre_completo + ", ";
            }
            else{
                listaActores = listaActores + actor.nombre_completo;
            }
        }
        tvActoresDetalle.setText(listaActores);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ModificarPelicula.class);
                myIntent.putExtra("idpeli", idPelicula);
                startActivity(myIntent);
            }
        });
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conectorPelicula.delete(idPelicula);
                finish();
            }
        });
    }
}