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
import com.example.vesprada.controlpelicula.dao.EstadoDAO;
import com.example.vesprada.controlpelicula.dao.GeneroDAO;
import com.example.vesprada.controlpelicula.dao.PeliculaDAO;
import com.example.vesprada.controlpelicula.dao.ProductorDAO;
import com.example.vesprada.controlpelicula.modelo.Actor;
import com.example.vesprada.controlpelicula.modelo.Director;
import com.example.vesprada.controlpelicula.modelo.Estado;
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
    private EstadoDAO conectorEstado = new EstadoDAO(this);

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

    public void insertsEstadosDefecto() {

        Estado estadoNoVista = new Estado();
        estadoNoVista.nombre_estado = "No Vista";
        conectorEstado.insert(estadoNoVista);

        Estado estadoPendiente = new Estado();
        estadoPendiente.nombre_estado = "Pendiente";
        conectorEstado.insert(estadoPendiente);

        Estado estadoVista = new Estado();
        estadoVista.nombre_estado = "Vista";
        conectorEstado.insert(estadoVista);

        Estado estadoFavorita = new Estado();
        estadoFavorita.nombre_estado = "Favorita";
        conectorEstado.insert(estadoFavorita);

    }

    public void insertsPeliclasDefecto() {

        Pelicula MisionImposible3 = new Pelicula();
        MisionImposible3.anyo = 2006;
        MisionImposible3.duracion = 126;
        MisionImposible3.nombre = "Mision Imposible 3";
        MisionImposible3.portada = "imgMI3";
        MisionImposible3.puntuacion = 5.5f;
        MisionImposible3.sinopsis = "Tras haber llevado a cabo diversas misiones, el agente especial Ethan Hunt (Tom Cruise) se ha retirado del servicio activo y se ha prometido con su amada Julia (Michelle Monaghan). Pero, cuando es secuestrado uno de los agentes entrenados por él, volverá de nuevo a la acción. También tendrá que enfrentarse a Owen Davian (Philip Seymour Hoffman), un individuo sin escrúpulos que trafica con armas y con información";
        MisionImposible3.id_director = 1;
        MisionImposible3.id_estado = 1;
        MisionImposible3.id_genero = 1;
        MisionImposible3.id_productor = 1;
        conectorPelicula.insert(MisionImposible3);

        Pelicula Norbit = new Pelicula();
        Norbit.anyo = 2007;
        Norbit.duracion = 102;
        Norbit.nombre = "Norbit";
        Norbit.portada = "imgNor";
        Norbit.puntuacion = 3.4f;
        Norbit.sinopsis = "Norbit (Eddie Murphy) no ha tenido una vida fácil. De pequeño fue abandonado ante la puerta de un restaurante chino, que hacía las veces de orfanato, donde le crió el Sr. Wong (Eddie Murphy). Las cosas se ponen aún peor cuando se ve obligado a casarse con Rasputia (Eddie Murphy), la malvada y tragona reina de la comida basura. Cuando Norbit ya no puede más y está a punto de rendirse, reaparece Kate (Thandie Newton), su gran amor de infancia... ";
        Norbit.id_director = 2;
        Norbit.id_estado = 1;
        Norbit.id_genero = 2;
        Norbit.id_productor = 2;
        conectorPelicula.insert(Norbit);

        Pelicula Soy_leyenda = new Pelicula();
        Soy_leyenda.anyo = 2007;
        Soy_leyenda.duracion = 100;
        Soy_leyenda.nombre = "Soy Leyenda";
        Soy_leyenda.portada = "imgSLe";
        Soy_leyenda.puntuacion = 6.5f;
        Soy_leyenda.sinopsis = "Año 2012. Robert Neville (Will Smith) es el último hombre vivo que hay sobre la Tierra, pero no está solo. Los demás seres humanos se han convertido en vampiros y todos ansían beber su sangre. Durante el día vive en estado de alerta, como un cazador, y busca a los muertos vivientes mientras duermen; pero durante la noche debe esconderse de ellos y esperar el amanecer. Esta pesadilla empezó hace tres años: Neville era un brillante científico, pero no pudo impedir la expansión de un terrible virus creado por el hombre. Él ha sobrevivido porque es inmune al virus; todos los días envía mensajes por radio con la esperanza de que haya otros supervivientes, pero es inútil. Lo único que puede hacer es buscar una fórmula que le permita utilizar su sangre inmune para devolverles a los hombres su naturaleza. Pero está en inferioridad de condiciones y el tiempo se acaba.";
        Soy_leyenda.id_director = 3;
        Soy_leyenda.id_estado = 1;
        Soy_leyenda.id_genero = 3;
        Soy_leyenda.id_productor = 3;
        conectorPelicula.insert(Soy_leyenda);

        Pelicula Star_Wars_VI = new Pelicula();
        Star_Wars_VI.anyo = 1983;
        Star_Wars_VI.duracion = 133;
        Star_Wars_VI.nombre = "Star Wars El Retorno del Jedi";
        Star_Wars_VI.portada = "imgSW6";
        Star_Wars_VI.puntuacion = 7.9f;
        Star_Wars_VI.sinopsis = "Para ir a Tatooine y liberar a Han Solo, Luke Skywalker y la princesa Leia deben infiltrarse en la peligrosa guarida de Jabba the Hutt, el gángster más temido de la galaxia. Una vez reunidos, el equipo recluta a tribus de Ewoks para combatir a las fuerzas imperiales en los bosques de la luna de Endor. Mientras tanto, el Emperador y Darth Vader conspiran para atraer a Luke al lado oscuro, pero el joven está decidido a reavivar el espíritu del Jedi en su padre. La guerra civil galáctica termina con un último enfrentamiento entre las fuerzas rebeldes unificadas y una segunda Estrella de la Muerte, indefensa e incompleta, en una batalla que decidirá el destino de la galaxia.";
        Star_Wars_VI.id_director = 4;
        Star_Wars_VI.id_estado = 1;
        Star_Wars_VI.id_genero = 4;
        Star_Wars_VI.id_productor = 4;
        conectorPelicula.insert(Star_Wars_VI);

        Pelicula Indiana_Calavera = new Pelicula();
        Indiana_Calavera.anyo = 2008;
        Indiana_Calavera.duracion = 125;
        Indiana_Calavera.nombre = "Indiana Jones y el reino de la calavera de cristal";
        Indiana_Calavera.portada = "imgIJ4";
        Indiana_Calavera.puntuacion = 5.5f;
        Indiana_Calavera.sinopsis = "Año 1957, en plena guerra fría. Indiana Jones (Harrison Ford) consigue de milagro salir ileso de una explosiva situación con unos agentes soviéticos en un remoto desierto al que llegó detenido junto a su amigo Mac (Ray Winstone). El decano de la Universidad (Jim Broadbent) le confiesa a su amigo el profesor Jones que las últimas misiones de Indy han fracasado, y que está a punto de ser despedido. Mientras tanto, Indiana conoce a Mutt (Shia LaBeouf), un joven rebelde que le propone un trato: si le ayuda a resolver un problema personal, él, a cambio, le facilitaría uno de los descubrimientos más espectaculares de la historia: la Calavera de Cristal de Akator, que se encuentra en un lugar remoto del Perú. Pero los agentes soviéticos, dirigidos por la fría y bella Irina Spalko (Cate Blanchett), tienen el mismo objetivo.";
        Indiana_Calavera.id_director = 5;
        Indiana_Calavera.id_estado = 1;
        Indiana_Calavera.id_genero = 5;
        Indiana_Calavera.id_productor = 5;
        conectorPelicula.insert(Indiana_Calavera);

        Pelicula The_Counselor = new Pelicula();
        The_Counselor.anyo = 2013;
        The_Counselor.duracion = 113;
        The_Counselor.nombre = "The Counselor";
        The_Counselor.portada = "imgTCo";
        The_Counselor.puntuacion = 4.7f;
        The_Counselor.sinopsis = "Un respetado abogado americano (Fassbender) decide participar por primera vez en una operación de tráfico de drogas en la frontera mexicana con el fin de conseguir dinero para casarse con su novia (Penélope Cruz). Sus contactos con los cárteles son Reiner (Javier Bardem), un capo de la droga muy enamorado de su chica, la sexy y ambiciosa Malkina (Cameron Diaz), y Westray (Brad Pitt), un intermediario amigo suyo. Primer guión cinematográfico del novelista Cormac McCarthy (La carretera, No es país para viejos).";
        The_Counselor.id_director = 6;
        The_Counselor.id_estado = 1;
        The_Counselor.id_genero = 6;
        The_Counselor.id_productor = 6;
        conectorPelicula.insert(The_Counselor);

        Pelicula Spike_Island = new Pelicula();
        Spike_Island.anyo = 2012;
        Spike_Island.duracion = 105;
        Spike_Island.nombre = "Spike Island";
        Spike_Island.portada = "imgSIs";
        Spike_Island.puntuacion = 6.4f;
        Spike_Island.sinopsis = "Los protagonistas son \"The Stone Roses\", un grupo inglés del movimiento Manchester. En 1990, la banda dio un mítico concierto en la isla de Spike, y hasta allí intentó llegar un grupo de adolescentes de Manchester, apasionados por su música. ";
        Spike_Island.id_director = 7;
        Spike_Island.id_estado = 1;
        Spike_Island.id_genero = 7;
        Spike_Island.id_productor = 7;
        conectorPelicula.insert(Spike_Island);

        Pelicula Terminator_Genesis = new Pelicula();
        Terminator_Genesis.anyo = 2015;
        Terminator_Genesis.duracion = 126;
        Terminator_Genesis.nombre = "Terminator Génesis";
        Terminator_Genesis.portada = "imgTGe";
        Terminator_Genesis.puntuacion = 5.4f;
        Terminator_Genesis.sinopsis = "Año 2032. La guerra del futuro se está librando y un grupo de rebeldes humanos tiene el sistema de inteligencia artificial Skynet contra las cuerdas. John Connor (Jason Clarke) es el líder de la resistencia, y Kyle Reese (Jai Courtney) es su fiel soldado, criado en las ruinas de una postapocalíptica California. Para salvaguardar el futuro, Connor envía a Reese a 1984 para salvar a su madre, Sarah (Emilia Clarke) de un Terminator programado para matarla con el fin de que no llegue a dar a luz a John. Pero lo que Reese encuentra en el otro lado no es como él esperaba...";
        Terminator_Genesis.id_director = 8;
        Terminator_Genesis.id_estado = 1;
        Terminator_Genesis.id_genero = 8;
        Terminator_Genesis.id_productor = 8;
        conectorPelicula.insert(Terminator_Genesis);

        Pelicula La_Jungla_4 = new Pelicula();
        La_Jungla_4.anyo = 2007;
        La_Jungla_4.duracion = 130;
        La_Jungla_4.nombre = "La jungla 4.0";
        La_Jungla_4.puntuacion = 6.24f;
        La_Jungla_4.sinopsis = "Estados Unidos. Un grupo terrorista bloquea el sistema de ordenadores que controla las comunicaciones, el transporte y el suministro de energía. El cerebro de la operación había estudiado minuciosamente hasta el más mínimo detalle, pero no había contado con John McClane (Bruce Willis), un policía de la vieja escuela, pero con los conocimientos necesarios para frustrar una amenaza terrorista de esta índole.";
        La_Jungla_4.id_director = 9;
        La_Jungla_4.id_estado = 1;
        La_Jungla_4.id_genero = 9;
        La_Jungla_4.id_productor = 9;
        conectorPelicula.insert(La_Jungla_4);

        Pelicula Los_mercenarios_2 = new Pelicula();
        Los_mercenarios_2.anyo = 2012;
        Los_mercenarios_2.duracion = 102;
        Los_mercenarios_2.nombre = "Los Mercenarios 2";
        Los_mercenarios_2.puntuacion = 6.0f;
        Los_mercenarios_2.sinopsis = "Barney Ross (Sylvester Stallone), Lee Christmas (Jason Statham), Yin Yang (Jet Li), Gunner Jensen (Dolph Lundgren), Toll Road (Randy Couture) y Hale Caesar (Terry Crews) y Billy (Liam Hemsworth), un nuevo colega, se vuelven a reunir cuando el señor Church (Bruce Willis) les encarga un trabajo aparentemente sencillo y muy lucrativo. Sin embargo, el plan se tuerce cuando un peligroso terrorista llamado Villain (Jean-Claude Van Damme) les tiende una emboscada. Entonces su único deseo será vengarse. Así es como van sembrando a su paso la destrucción y el caos entre sus enemigos hasta que se encuentran con una amenaza inesperada: cinco toneladas de plutonio apto para uso militar, una cantidad más que suficiente para cambiar el equilibrio de poder en el mundo.";
        Los_mercenarios_2.id_director = 10;
        Los_mercenarios_2.id_estado = 1;
        Los_mercenarios_2.id_genero = 10;
        Los_mercenarios_2.id_productor = 10;
        conectorPelicula.insert(Los_mercenarios_2);











    }


}

