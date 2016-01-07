package com.example.vesprada.controlpelicula;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.vesprada.controlpelicula.activity.CrearPelicula;
import com.example.vesprada.controlpelicula.activity.DetallePelicula;
import com.example.vesprada.controlpelicula.conexion.DBHelperControlPeliculas;
import com.example.vesprada.controlpelicula.dao.ActorDAO;
import com.example.vesprada.controlpelicula.dao.Actor_PeliculaDAO;
import com.example.vesprada.controlpelicula.dao.DirectorDAO;
import com.example.vesprada.controlpelicula.dao.EstadoDAO;
import com.example.vesprada.controlpelicula.dao.GeneroDAO;
import com.example.vesprada.controlpelicula.dao.PeliculaDAO;
import com.example.vesprada.controlpelicula.dao.ProductorDAO;
import com.example.vesprada.controlpelicula.modelo.Actor;
import com.example.vesprada.controlpelicula.modelo.Actor_Pelicula;
import com.example.vesprada.controlpelicula.modelo.Director;
import com.example.vesprada.controlpelicula.modelo.Estado;
import com.example.vesprada.controlpelicula.modelo.Genero;
import com.example.vesprada.controlpelicula.modelo.Pelicula;
import com.example.vesprada.controlpelicula.modelo.Productor;
import com.example.vesprada.controlpelicula.recyclerview.PeliculaAdapter;
import com.example.vesprada.controlpelicula.recyclerview.RecyclerItemClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
    private RecyclerView recView;
    private EditText buscadorPeliculas;
    private ImageButton imgButtonBuscador;
    private PeliculaAdapter adaptadorPelicula;

    private DBHelperControlPeliculas crearTablaPeliculas;
    private PeliculaDAO conectorPelicula = new PeliculaDAO(this);
    private GeneroDAO conectorGenero = new GeneroDAO(this);
    private ProductorDAO conectorProductor = new ProductorDAO(this);
    private DirectorDAO conectorDirector = new DirectorDAO(this);
    private ActorDAO conectorActor = new ActorDAO(this);
    private EstadoDAO conectorEstado = new EstadoDAO(this);
    private Actor_PeliculaDAO conectorActorPelicula = new Actor_PeliculaDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /** Inserts por defecto */

        insertsGeneroDefecto();
        InstertsProductorDefecto();
        insertarActoresDefecto();
        insertarDirectoresDefecto();
        insertsEstadosDefecto();
        insertsPeliculasDefecto();
        insertsPeliculasActoresDefecto();


        buscadorPeliculas = (EditText) findViewById(R.id.etBuscador);
        imgButtonBuscador = (ImageButton) findViewById(R.id.btnBuscador);

        crearTablaPeliculas = new DBHelperControlPeliculas(getApplicationContext());

        recView = (RecyclerView) findViewById(R.id.RecView);

        initList();

        imgButtonBuscador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchItem(buscadorPeliculas.toString().toLowerCase());
            }
        });

        recView.setItemAnimator(new DefaultItemAnimator());
        recView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        TextView id_pelicula = (TextView)(view.findViewById(R.id.tvIdPelicula));
                        int idPelicula = Integer.parseInt(id_pelicula.getText().toString());
                        Intent myIntent = new Intent(view.getContext(), DetallePelicula.class);
                        myIntent.putExtra("id_pelicula", idPelicula);
                        startActivity(myIntent);
                    }
                })
        );

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

    /** Método para iniciar la lista de peliculas */
    public void initList(){
        listaPeliculas = conectorPelicula.getPeliculaList();
        adaptadorPelicula = new PeliculaAdapter(listaPeliculas);
        recView.setAdapter(adaptadorPelicula);
        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    /** Método para buscar según el buscador de las películas */
    public void searchItem (String txtToSearch) {
        listaPeliculas = conectorPelicula.getPeliculaListByName(txtToSearch);
        adaptadorPelicula = new PeliculaAdapter(listaPeliculas);
        recView.setAdapter(adaptadorPelicula);
        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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

        if (id == R.id.nav_ord_fav) {
            // Handle the camera action
        } else if (id == R.id.nav_ord_vistas) {

        } else if (id == R.id.nav_ord_pendientes) {

        } else if (id == R.id.nav_ord_novistas) {

        } else if (id == R.id.nav_movie) {

        } else if (id == R.id.nav_actor) {

        } else if (id == R.id.nav_view) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void insertsGeneroDefecto() {

        Genero generoAccion = new Genero();
        generoAccion.id = 1;
        generoAccion.nombre ="Accion";
        conectorGenero.insert(generoAccion);

        Genero generoComedia = new Genero();
        generoComedia.id = 2;
        generoComedia.nombre = "Comedia";
        conectorGenero.insert(generoComedia);

        Genero generoTerror = new Genero();
        generoTerror.id = 3;
        generoTerror.nombre = "Terror";
        conectorGenero.insert(generoTerror);

        Genero generoCienciaFicción = new Genero();
        generoCienciaFicción.id = 4;
        generoCienciaFicción.nombre = "Ciencia Ficción";
        conectorGenero.insert(generoCienciaFicción);

        Genero generoFantasia = new Genero();
        generoFantasia.id = 5;
        generoFantasia.nombre = "Fantasia";
        conectorGenero.insert(generoFantasia);

        Genero generoDrama = new Genero();
        generoDrama.id = 6;
        generoDrama.nombre = "Drama";
        conectorGenero.insert(generoDrama);

        Genero generoRomance = new Genero();
        generoRomance.id = 7;
        generoRomance.nombre = "Romance";
        conectorGenero.insert(generoRomance);

        Genero generoSuspense = new Genero();
        generoSuspense.id = 8;
        generoSuspense.nombre = "Suspense";
        conectorGenero.insert(generoSuspense);
    }


    public void InstertsProductorDefecto() {

        Productor productorParamount = new Productor();
        productorParamount.id = 1;
        productorParamount.nombre = "Paramount Pictures";
        conectorProductor.insert(productorParamount);

        Productor productorDreamWorks = new Productor();
        productorDreamWorks.id = 2;
        productorDreamWorks.nombre = "DreamWorks";
        conectorProductor.insert(productorDreamWorks);

        Productor productorWarnerBros = new Productor();
        productorWarnerBros.id = 3;
        productorWarnerBros.nombre = "Warner Bros";
        conectorProductor.insert(productorWarnerBros);

        Productor productorLucasfilm = new Productor();
        productorLucasfilm.id = 4;
        productorLucasfilm.nombre = "Lucasfilm";
        conectorProductor.insert(productorLucasfilm);

        Productor productorScottFreeProductions = new Productor();
        productorScottFreeProductions.id = 5;
        productorScottFreeProductions.nombre = "Scott Free Productions";
        conectorProductor.insert(productorScottFreeProductions);

        Productor productorBanksideFilms = new Productor();
        productorBanksideFilms.id = 6;
        productorBanksideFilms.nombre = "Bankside Films";
        conectorProductor.insert(productorBanksideFilms);

        Productor productorSkydanceProductions = new Productor();
        productorSkydanceProductions.id = 7;
        productorSkydanceProductions.nombre = "Skydance Productions";
        conectorProductor.insert(productorSkydanceProductions);

        Productor productor20thCenturyFox = new Productor();
        productor20thCenturyFox.id = 8;
        productor20thCenturyFox.nombre = "20th Century Fox";
        conectorProductor.insert(productor20thCenturyFox);

        Productor productorMileniumFilms = new Productor();
        productorMileniumFilms.id = 9;
        productorMileniumFilms.nombre = "Millenium Films";
        conectorProductor.insert(productorMileniumFilms);
    }

    public void insertarActoresDefecto(){

        Actor actorTomCruise = new Actor();
        actorTomCruise.id = 1;
        actorTomCruise.nombre_completo = "Tom Cruise";
        conectorActor.insert(actorTomCruise);

        Actor actorEddieMurphy = new Actor();
        actorEddieMurphy.id = 2;
        actorEddieMurphy.nombre_completo = "Eddie Murphy";
        conectorActor.insert(actorEddieMurphy);

        Actor actorWillSmith = new Actor();
        actorWillSmith.id = 3;
        actorWillSmith.nombre_completo = "Will Smith";
        conectorActor.insert(actorWillSmith);

        Actor actorMarkHammilk = new Actor();
        actorMarkHammilk.id = 4;
        actorMarkHammilk.nombre_completo = "Mark Hammilk";
        conectorActor.insert(actorMarkHammilk);

        Actor actorHarrisonFord = new Actor();
        actorHarrisonFord.id = 5;
        actorHarrisonFord.nombre_completo = "Harrison Ford";
        conectorActor.insert(actorHarrisonFord);

        Actor actorJavierBardem = new Actor();
        actorJavierBardem.id = 6;
        actorJavierBardem.nombre_completo = "Javier Bardem";
        conectorActor.insert(actorJavierBardem);

        Actor actorEmiliaClarke = new Actor();
        actorEmiliaClarke.id = 7;
        actorEmiliaClarke.nombre_completo = "Emilia Clarke";
        conectorActor.insert(actorEmiliaClarke);

        Actor actorJasonClarke = new Actor();
        actorJasonClarke.id = 8;
        actorJasonClarke.nombre_completo = "Jason Clarke";
        conectorActor.insert(actorJasonClarke);

        Actor actorBruceWillis = new Actor();
        actorBruceWillis.id = 9;
        actorBruceWillis.nombre_completo = "Bruce Willis";
        conectorActor.insert(actorBruceWillis);

        Actor actorJasonStatham = new Actor();
        actorJasonStatham.id = 10;
        actorJasonStatham.nombre_completo = "Jason Statham";
        conectorActor.insert(actorJasonStatham);

    }
    public void insertarDirectoresDefecto(){

        Director directorJJAbrams = new Director();
        directorJJAbrams.id = 1;
        directorJJAbrams.nombre_completo = "J.J Abrams";
        conectorDirector.insert(directorJJAbrams);

        Director directorBrianRobbins = new Director();
        directorBrianRobbins.id = 2;
        directorBrianRobbins.nombre_completo = "Brian Robbins";
        conectorDirector.insert(directorBrianRobbins);

        Director directorFrancisLawrence = new Director();
        directorFrancisLawrence.id = 3;
        directorFrancisLawrence.nombre_completo = "Francis Lawrence";
        conectorDirector.insert(directorFrancisLawrence);

        Director directorGeorgeLucas = new Director();
        directorGeorgeLucas.id = 4;
        directorGeorgeLucas.nombre_completo = "George Lucas";
        conectorDirector.insert(directorGeorgeLucas);

        Director directorStevenSpielberg = new Director();
        directorStevenSpielberg.id = 5;
        directorStevenSpielberg.nombre_completo = "Steven Spielberg";
        conectorDirector.insert(directorStevenSpielberg);

        Director directorRidleyScott = new Director();
        directorRidleyScott.id = 6;
        directorRidleyScott.nombre_completo = "Ridley Scott";
        conectorDirector.insert(directorRidleyScott);

        Director directorMatWhitecross = new Director();
        directorMatWhitecross.id = 7;
        directorMatWhitecross.nombre_completo = "Mat Whitecross";
        conectorDirector.insert(directorMatWhitecross);

        Director directorAlanTaylor = new Director();
        directorAlanTaylor.id = 8;
        directorAlanTaylor.nombre_completo = "Alan Taylor";
        conectorDirector.insert(directorAlanTaylor);

        Director directorLenWiseman = new Director();
        directorLenWiseman.id = 9;
        directorLenWiseman.nombre_completo = "Len Wiseman";
        conectorDirector.insert(directorLenWiseman);

        Director directorSimonWest = new Director();
        directorSimonWest.id = 10;
        directorSimonWest.nombre_completo = "Simon West";
        conectorDirector.insert(directorSimonWest);


    }

    public void insertsEstadosDefecto() {

        Estado estadoNoVista = new Estado();
        estadoNoVista.id = 1;
        estadoNoVista.nombre_estado = "No Vista";
        conectorEstado.insert(estadoNoVista);

        Estado estadoPendiente = new Estado();
        estadoPendiente.id = 2;
        estadoPendiente.nombre_estado = "Pendiente";
        conectorEstado.insert(estadoPendiente);

        Estado estadoVista = new Estado();
        estadoVista.id = 3;
        estadoVista.nombre_estado = "Vista";
        conectorEstado.insert(estadoVista);

        Estado estadoFavorita = new Estado();
        estadoFavorita.id = 4;
        estadoFavorita.nombre_estado = "Favorita";
        conectorEstado.insert(estadoFavorita);

    }

    public void insertsPeliculasDefecto() {

        Pelicula misionImposible3 = new Pelicula();
        misionImposible3.id = 1;
        misionImposible3.anyo = 2006;
        misionImposible3.duracion = 126;
        misionImposible3.nombre = "Mision Imposible 3";
        misionImposible3.portada = "imgMI3";
        misionImposible3.puntuacion = 10;
        misionImposible3.sinopsis = "Tras haber llevado a cabo diversas misiones, el agente especial Ethan Hunt (Tom Cruise) se ha retirado del servicio activo y se ha prometido con su amada Julia (Michelle Monaghan). Pero, cuando es secuestrado uno de los agentes entrenados por él, volverá de nuevo a la acción. También tendrá que enfrentarse a Owen Davian (Philip Seymour Hoffman), un individuo sin escrúpulos que trafica con armas y con información";
        misionImposible3.id_director = 1;
        misionImposible3.id_estado = 1;
        misionImposible3.id_genero = 1;
        misionImposible3.id_productor = 1;
        conectorPelicula.insert(misionImposible3);

        Pelicula norbit = new Pelicula();
        norbit.id = 2;
        norbit.anyo = 2007;
        norbit.duracion = 102;
        norbit.nombre = "Norbit";
        norbit.portada = "imgNor";
        norbit.puntuacion = 20;
        norbit.sinopsis = "Norbit (Eddie Murphy) no ha tenido una vida fácil. De pequeño fue abandonado ante la puerta de un restaurante chino, que hacía las veces de orfanato, donde le crió el Sr. Wong (Eddie Murphy). Las cosas se ponen aún peor cuando se ve obligado a casarse con Rasputia (Eddie Murphy), la malvada y tragona reina de la comida basura. Cuando Norbit ya no puede más y está a punto de rendirse, reaparece Kate (Thandie Newton), su gran amor de infancia... ";
        norbit.id_director = 2;
        norbit.id_estado = 1;
        norbit.id_genero = 2;
        norbit.id_productor = 2;
        conectorPelicula.insert(norbit);

        Pelicula soyLeyenda = new Pelicula();
        soyLeyenda.id = 3;
        soyLeyenda.anyo = 2007;
        soyLeyenda.duracion = 100;
        soyLeyenda.nombre = "Soy Leyenda";
        soyLeyenda.portada = "imgSLe";
        soyLeyenda.puntuacion = 30;
        soyLeyenda.sinopsis = "Año 2012. Robert Neville (Will Smith) es el último hombre vivo que hay sobre la Tierra, pero no está solo. Los demás seres humanos se han convertido en vampiros y todos ansían beber su sangre. Durante el día vive en estado de alerta, como un cazador, y busca a los muertos vivientes mientras duermen; pero durante la noche debe esconderse de ellos y esperar el amanecer. Esta pesadilla empezó hace tres años: Neville era un brillante científico, pero no pudo impedir la expansión de un terrible virus creado por el hombre. Él ha sobrevivido porque es inmune al virus; todos los días envía mensajes por radio con la esperanza de que haya otros supervivientes, pero es inútil. Lo único que puede hacer es buscar una fórmula que le permita utilizar su sangre inmune para devolverles a los hombres su naturaleza. Pero está en inferioridad de condiciones y el tiempo se acaba.";
        soyLeyenda.id_director = 3;
        soyLeyenda.id_estado = 2;
        soyLeyenda.id_genero = 1;
        soyLeyenda.id_productor = 3;
        conectorPelicula.insert(soyLeyenda);

        Pelicula starWarsVI = new Pelicula();
        starWarsVI.id = 4;
        starWarsVI.anyo = 1983;
        starWarsVI.duracion = 133;
        starWarsVI.nombre = "Star Wars El Retorno del Jedi";
        starWarsVI.portada = "imgSW6";
        starWarsVI.puntuacion = 40;
        starWarsVI.sinopsis = "Para ir a Tatooine y liberar a Han Solo, Luke Skywalker y la princesa Leia deben infiltrarse en la peligrosa guarida de Jabba the Hutt, el gángster más temido de la galaxia. Una vez reunidos, el equipo recluta a tribus de Ewoks para combatir a las fuerzas imperiales en los bosques de la luna de Endor. Mientras tanto, el Emperador y Darth Vader conspiran para atraer a Luke al lado oscuro, pero el joven está decidido a reavivar el espíritu del Jedi en su padre. La guerra civil galáctica termina con un último enfrentamiento entre las fuerzas rebeldes unificadas y una segunda Estrella de la Muerte, indefensa e incompleta, en una batalla que decidirá el destino de la galaxia.";
        starWarsVI.id_director = 4;
        starWarsVI.id_estado = 2;
        starWarsVI.id_genero = 4;
        starWarsVI.id_productor = 4;
        conectorPelicula.insert(starWarsVI);

        Pelicula indianaCalavera = new Pelicula();
        indianaCalavera.id = 5;
        indianaCalavera.anyo = 2008;
        indianaCalavera.duracion = 125;
        indianaCalavera.nombre = "Indiana Jones y el reino de la calavera de cristal";
        indianaCalavera.portada = "imgIJ4";
        indianaCalavera.puntuacion = 50;
        indianaCalavera.sinopsis = "Año 1957, en plena guerra fría. Indiana Jones (Harrison Ford) consigue de milagro salir ileso de una explosiva situación con unos agentes soviéticos en un remoto desierto al que llegó detenido junto a su amigo Mac (Ray Winstone). El decano de la Universidad (Jim Broadbent) le confiesa a su amigo el profesor Jones que las últimas misiones de Indy han fracasado, y que está a punto de ser despedido. Mientras tanto, Indiana conoce a Mutt (Shia LaBeouf), un joven rebelde que le propone un trato: si le ayuda a resolver un problema personal, él, a cambio, le facilitaría uno de los descubrimientos más espectaculares de la historia: la Calavera de Cristal de Akator, que se encuentra en un lugar remoto del Perú. Pero los agentes soviéticos, dirigidos por la fría y bella Irina Spalko (Cate Blanchett), tienen el mismo objetivo.";
        indianaCalavera.id_director = 5;
        indianaCalavera.id_estado = 3;
        indianaCalavera.id_genero = 1;
        indianaCalavera.id_productor = 5;
        conectorPelicula.insert(indianaCalavera);

        Pelicula theCounselor = new Pelicula();
        theCounselor.id = 6;
        theCounselor.anyo = 2013;
        theCounselor.duracion = 113;
        theCounselor.nombre = "The Counselor";
        theCounselor.portada = "imgTCo";
        theCounselor.puntuacion = 60;
        theCounselor.sinopsis = "Un respetado abogado americano (Fassbender) decide participar por primera vez en una operación de tráfico de drogas en la frontera mexicana con el fin de conseguir dinero para casarse con su novia (Penélope Cruz). Sus contactos con los cárteles son Reiner (Javier Bardem), un capo de la droga muy enamorado de su chica, la sexy y ambiciosa Malkina (Cameron Diaz), y Westray (Brad Pitt), un intermediario amigo suyo. Primer guión cinematográfico del novelista Cormac McCarthy (La carretera, No es país para viejos).";
        theCounselor.id_director = 6;
        theCounselor.id_estado = 3;
        theCounselor.id_genero = 6;
        theCounselor.id_productor = 6;
        conectorPelicula.insert(theCounselor);

        Pelicula spikeIsland = new Pelicula();
        spikeIsland.id = 7;
        spikeIsland.anyo = 2012;
        spikeIsland.duracion = 105;
        spikeIsland.nombre = "Spike Island";
        spikeIsland.portada = "imgSIs";
        spikeIsland.puntuacion = 70;
        spikeIsland.sinopsis = "Los protagonistas son \"The Stone Roses\", un grupo inglés del movimiento Manchester. En 1990, la banda dio un mítico concierto en la isla de Spike, y hasta allí intentó llegar un grupo de adolescentes de Manchester, apasionados por su música. ";
        spikeIsland.id_director = 7;
        spikeIsland.id_estado = 4;
        spikeIsland.id_genero = 6;
        spikeIsland.id_productor = 7;
        conectorPelicula.insert(spikeIsland);

        Pelicula terminatorGenesis = new Pelicula();
        terminatorGenesis.id = 8;
        terminatorGenesis.anyo = 2015;
        terminatorGenesis.duracion = 126;
        terminatorGenesis.nombre = "Terminator Génesis";
        terminatorGenesis.portada = "imgTGe";
        terminatorGenesis.puntuacion = 80;
        terminatorGenesis.sinopsis = "Año 2032. La guerra del futuro se está librando y un grupo de rebeldes humanos tiene el sistema de inteligencia artificial Skynet contra las cuerdas. John Connor (Jason Clarke) es el líder de la resistencia, y Kyle Reese (Jai Courtney) es su fiel soldado, criado en las ruinas de una postapocalíptica California. Para salvaguardar el futuro, Connor envía a Reese a 1984 para salvar a su madre, Sarah (Emilia Clarke) de un Terminator programado para matarla con el fin de que no llegue a dar a luz a John. Pero lo que Reese encuentra en el otro lado no es como él esperaba...";
        terminatorGenesis.id_director = 8;
        terminatorGenesis.id_estado = 4;
        terminatorGenesis.id_genero = 4;
        terminatorGenesis.id_productor = 1;
        conectorPelicula.insert(terminatorGenesis);

        Pelicula laJungla4 = new Pelicula();
        laJungla4.id = 9;
        laJungla4.anyo = 2007;
        laJungla4.duracion = 130;
        laJungla4.nombre = "La jungla 4.0";
        laJungla4.portada = "imgLj4";
        laJungla4.puntuacion = 90;
        laJungla4.sinopsis = "Estados Unidos. Un grupo terrorista bloquea el sistema de ordenadores que controla las comunicaciones, el transporte y el suministro de energía. El cerebro de la operación había estudiado minuciosamente hasta el más mínimo detalle, pero no había contado con John McClane (Bruce Willis), un policía de la vieja escuela, pero con los conocimientos necesarios para frustrar una amenaza terrorista de esta índole.";
        laJungla4.id_director = 9;
        laJungla4.id_estado = 1;
        laJungla4.id_genero = 1;
        laJungla4.id_productor = 9;
        conectorPelicula.insert(laJungla4);

        Pelicula losMercenarios2 = new Pelicula();
        losMercenarios2.id = 10;
        losMercenarios2.anyo = 2012;
        losMercenarios2.duracion = 102;
        losMercenarios2.nombre = "Los Mercenarios 2";
        losMercenarios2.portada = "imgLm2";
        losMercenarios2.puntuacion = 100;
        losMercenarios2.sinopsis = "Barney Ross (Sylvester Stallone), Lee Christmas (Jason Statham), Yin Yang (Jet Li), Gunner Jensen (Dolph Lundgren), Toll Road (Randy Couture) y Hale Caesar (Terry Crews) y Billy (Liam Hemsworth), un nuevo colega, se vuelven a reunir cuando el señor Church (Bruce Willis) les encarga un trabajo aparentemente sencillo y muy lucrativo. Sin embargo, el plan se tuerce cuando un peligroso terrorista llamado Villain (Jean-Claude Van Damme) les tiende una emboscada. Entonces su único deseo será vengarse. Así es como van sembrando a su paso la destrucción y el caos entre sus enemigos hasta que se encuentran con una amenaza inesperada: cinco toneladas de plutonio apto para uso militar, una cantidad más que suficiente para cambiar el equilibrio de poder en el mundo.";
        losMercenarios2.id_director = 10;
        losMercenarios2.id_estado = 1;
        losMercenarios2.id_genero = 1;
        losMercenarios2.id_productor = 10;
        conectorPelicula.insert(losMercenarios2);
    }

    public void insertsPeliculasActoresDefecto() {
        Actor_Pelicula misionImposible3 = new Actor_Pelicula();
        misionImposible3.id_pelicula = 1;
        misionImposible3.id_actor = 1;
        conectorActorPelicula.insert(misionImposible3);

        Actor_Pelicula norbit = new Actor_Pelicula();
        norbit.id_pelicula = 2;
        norbit.id_actor = 2;
        conectorActorPelicula.insert(norbit);

        Actor_Pelicula soyLeyenda = new Actor_Pelicula();
        soyLeyenda.id_pelicula = 3;
        soyLeyenda.id_actor = 3;
        conectorActorPelicula.insert(soyLeyenda);

        Actor_Pelicula starWarsVI = new Actor_Pelicula();
        starWarsVI.id_pelicula = 4;
        starWarsVI.id_actor = 4;
        conectorActorPelicula.insert(starWarsVI);

        Actor_Pelicula starWarsVIv2 = new Actor_Pelicula();
        starWarsVIv2.id_pelicula = 4;
        starWarsVIv2.id_actor = 5;
        conectorActorPelicula.insert(starWarsVIv2);

        Actor_Pelicula indianaCalavera = new Actor_Pelicula();
        indianaCalavera.id_pelicula = 5;
        indianaCalavera.id_actor = 5;
        conectorActorPelicula.insert(indianaCalavera);

        Actor_Pelicula theCounselor = new Actor_Pelicula();
        theCounselor.id_pelicula = 6;
        theCounselor.id_actor = 6;
        conectorActorPelicula.insert(theCounselor);

        Actor_Pelicula spikeIsland = new Actor_Pelicula();
        spikeIsland.id_pelicula = 7;
        spikeIsland.id_actor = 7;
        conectorActorPelicula.insert(spikeIsland);

        Actor_Pelicula terminatorGenesis = new Actor_Pelicula();
        terminatorGenesis.id_pelicula = 8;
        terminatorGenesis.id_actor = 8;
        conectorActorPelicula.insert(terminatorGenesis);

        Actor_Pelicula laJungla4 = new Actor_Pelicula();
        laJungla4.id_pelicula = 9;
        laJungla4.id_actor = 9;
        conectorActorPelicula.insert(laJungla4);

        Actor_Pelicula losMercenarios2 = new Actor_Pelicula();
        losMercenarios2.id_pelicula = 10;
        losMercenarios2.id_actor = 10;
        conectorActorPelicula.insert(losMercenarios2);
    }
}