package com.example.vesprada.controlpelicula;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.example.vesprada.controlpelicula.activity.CrearPelicula;
import com.example.vesprada.controlpelicula.conexion.DBHelperControlPeliculas;
import com.example.vesprada.controlpelicula.dao.ActorDAO;
import com.example.vesprada.controlpelicula.dao.DirectorDAO;
import com.example.vesprada.controlpelicula.dao.GeneroDAO;
import com.example.vesprada.controlpelicula.dao.PeliculaDAO;
import com.example.vesprada.controlpelicula.dao.ProductorDAO;
import com.example.vesprada.controlpelicula.modelo.Actor;
import com.example.vesprada.controlpelicula.modelo.Director;
import com.example.vesprada.controlpelicula.modelo.Genero;
import com.example.vesprada.controlpelicula.modelo.Pelicula;
import com.example.vesprada.controlpelicula.modelo.Productor;
import com.example.vesprada.controlpelicula.recyclerview.PeliculaAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<Pelicula> peliculas = new ArrayList<>();
    private RecyclerView recView;
    private PeliculaAdapter adaptadorPelicula;
    private DBHelperControlPeliculas crearTablaPeliculas;
    private PeliculaDAO conectorPelicula = new PeliculaDAO(this);
    private GeneroDAO conectorGenero = new GeneroDAO(this);
    private ProductorDAO conectorProductor = new ProductorDAO(this);
    private DirectorDAO conectorDirector = new DirectorDAO(this);
    private ActorDAO conectorActor = new ActorDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        crearTablaPeliculas = new DBHelperControlPeliculas(getApplicationContext());


        /** crear el recyclerView con los items que toquen */

        recView = (RecyclerView) findViewById(R.id.RecView);

        adaptadorPelicula = new PeliculaAdapter(peliculas);

        recView.setAdapter(adaptadorPelicula);

        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), CrearPelicula.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.item_touch_helper_previous_elevation) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void InsertsGeneroDefecto() {

        Genero generoAccion = new Genero();
        generoAccion.nombre ="Accion";
        conectorGenero.insert(generoAccion);

        Genero generoComedia = new Genero();
        generoComedia.nombre = "Comedia";
        conectorGenero.insert(generoComedia);

        Genero generoTerror = new Genero();
        generoTerror.nombre = "Terror";
        conectorGenero.insert(generoTerror);

        Genero generoCienciaFicción = new Genero();
        generoCienciaFicción.nombre = "Ciencia Ficción";
        conectorGenero.insert(generoCienciaFicción);

        Genero generoFantasia = new Genero();
        generoFantasia.nombre = "Fantasia";
        conectorGenero.insert(generoFantasia);

        Genero generoDrama = new Genero();
        generoDrama.nombre = "Drama";
        conectorGenero.insert(generoDrama);

        Genero generoRomance = new Genero();
        generoRomance.nombre = "Romance";
        conectorGenero.insert(generoRomance);

        Genero generoSuspense = new Genero();
        generoSuspense.nombre = "Suspense";
        conectorGenero.insert(generoSuspense);
    }


    public void InstertsProductorDefecto() {

        Productor productorParamount = new Productor();
        productorParamount.nombre = "Paramount Pictures";
        conectorProductor.insert(productorParamount);

        Productor productorDreamWorks = new Productor();
        productorDreamWorks.nombre = "DreamWorks";
        conectorProductor.insert(productorDreamWorks);

        Productor productorWarnerBros = new Productor();
        productorWarnerBros.nombre = "Warner Bros";
        conectorProductor.insert(productorWarnerBros);

        Productor productorLucasfilm = new Productor();
        productorLucasfilm.nombre = "Lucasfilm";
        conectorProductor.insert(productorLucasfilm);

        Productor productorScottFreeProductions = new Productor();
        productorScottFreeProductions.nombre = "Scott Free Productions";
        conectorProductor.insert(productorScottFreeProductions);

        Productor productorBanksideFilms = new Productor();
        productorBanksideFilms.nombre = "Bankside Films";
        conectorProductor.insert(productorBanksideFilms);

        Productor productorSkydanceProductions = new Productor();
        productorSkydanceProductions.nombre = "Skydance Productions";
        conectorProductor.insert(productorSkydanceProductions);

        Productor productor20thCenturyFox = new Productor();
        productor20thCenturyFox.nombre = "20th Century Fox";
        conectorProductor.insert(productor20thCenturyFox);

        Productor productorMileniumFilms = new Productor();
        productorMileniumFilms.nombre = "Millenium Films";
        conectorProductor.insert(productorMileniumFilms);
    }

    public void insertarActoresDefecto(){

        Actor actorTomCruise = new Actor();
        actorTomCruise.nombre_completo = "Tom Cruise";
        conectorActor.insert(actorTomCruise);

        Actor actorEddieMurphy = new Actor();
        actorEddieMurphy.nombre_completo = "Eddie Murphy";
        conectorActor.insert(actorEddieMurphy);

        Actor actorWillSmith = new Actor();
        actorWillSmith.nombre_completo = "Will Smith";
        conectorActor.insert(actorWillSmith);

        Actor actorMarkHammilk = new Actor();
        actorMarkHammilk.nombre_completo = "Mark Hammilk";
        conectorActor.insert(actorMarkHammilk);

        Actor actorHarrisonFord = new Actor();
        actorHarrisonFord.nombre_completo = "Harrison Ford";
        conectorActor.insert(actorHarrisonFord);

        Actor actorJavierBardem = new Actor();
        actorJavierBardem.nombre_completo = "Javier Bardem";
        conectorActor.insert(actorJavierBardem);

        Actor actorEmiliaClarke = new Actor();
        actorEmiliaClarke.nombre_completo = "Emilia Clarke";
        conectorActor.insert(actorEmiliaClarke);

        Actor actorJasonClarke = new Actor();
        actorJasonClarke.nombre_completo = "Jason Clarke";
        conectorActor.insert(actorJasonClarke);

        Actor actorBruceWillis = new Actor();
        actorBruceWillis.nombre_completo = "Bruce Willis";
        conectorActor.insert(actorBruceWillis);

        Actor actorJasonStatham = new Actor();
        actorJasonStatham.nombre_completo = "Jason Statham";
        conectorActor.insert(actorJasonStatham);

    }
    public void insertarDirectoresDefecto(){

        Director directorJJAbrams = new Director();
        directorJJAbrams.nombre_completo = "J.J Abrams";
        conectorDirector.insert(directorJJAbrams);

        Director directorBrianRobbins = new Director();
        directorBrianRobbins.nombre_completo = "Brian Robbins";
        conectorDirector.insert(directorBrianRobbins);

        Director directorFrancisLawrence = new Director();
        directorFrancisLawrence.nombre_completo = "Francis Lawrence";
        conectorDirector.insert(directorFrancisLawrence);

        Director directorGeorgeLucas = new Director();
        directorGeorgeLucas.nombre_completo = "George Lucas";
        conectorDirector.insert(directorGeorgeLucas);

        Director directorStevenSpielberg = new Director();
        directorStevenSpielberg.nombre_completo = "Steven Spielberg";
        conectorDirector.insert(directorStevenSpielberg);

        Director directorRidleyScott = new Director();
        directorRidleyScott.nombre_completo = "Ridley Scott";
        conectorDirector.insert(directorRidleyScott);

        Director directorMatWhitecross = new Director();
        directorMatWhitecross.nombre_completo = "Mat Whitecross";
        conectorDirector.insert(directorMatWhitecross);

        Director directorAlanTaylor = new Director();
        directorAlanTaylor.nombre_completo = "Alan Taylor";
        conectorDirector.insert(directorAlanTaylor);

        Director directorLenWiseman = new Director();
        directorLenWiseman.nombre_completo = "Len Wiseman";
        conectorDirector.insert(directorLenWiseman);

        Director directorSimonWest = new Director();
        directorSimonWest.nombre_completo = "Simon West";
        conectorDirector.insert(directorSimonWest);


    }

    public void InsertsPeliclasDefecto() {

        

    }


}

