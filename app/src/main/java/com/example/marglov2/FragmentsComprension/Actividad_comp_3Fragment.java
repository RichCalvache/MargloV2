package com.example.marglov2.FragmentsComprension;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.marglov2.Entidades.DBHelperPuntajes;
import com.example.marglov2.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Actividad_comp_3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class
Actividad_comp_3Fragment extends Fragment {

    //Declaracion de variables
    private ImageView imagen1,imagen2;
    private Button iniciar, parar,instrucciones;
    private TextView textViewAciertos, textViewInstrucc, textViewSeleccione, textViewEscuche, textViewFallaste;
    MediaPlayer sonidoInstruccion;
    int i, aciertos_actividad, global_retardo;
    int numero_sonido, numero_imagen1,numero_imagen2, randomImagenes;

    LottieAnimationView animacionSonido;

    int indice, numero_opciones;
    int randomIntListaSonidos_anterior,randomIntListaSonidos;

    String infoActividad = "¡Nada que agregar!";


    DBHelperPuntajes DB;

    //forma para el runnable
    private Handler handler;
    private Runnable runnable_iniciar,runnable_iniciar_desordenado,runnable_2_opciones,runnable_3_opciones,runnable_4_opciones,runnable_5_opciones,runnable_6_opciones,runnable_7_opciones,runnable_8_opciones;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Actividad_comp_3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Actividad_comp_3Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Actividad_comp_3Fragment newInstance(String param1, String param2) {
        Actividad_comp_3Fragment fragment = new Actividad_comp_3Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_actividad_comp_3, container, false);
    }


//creacion del onviewcreated recomendado
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        imagen1 = view.findViewById(R.id.imagen1);
        imagen2 = view.findViewById(R.id.imagen2);
        iniciar = view.findViewById(R.id.btn_iniciar);
        parar = view.findViewById(R.id.btn_parar);
        instrucciones = view.findViewById(R.id.btn_instrucciones);



        textViewAciertos = view.findViewById(R.id.textViewAciertosid);
        textViewFallaste = view.findViewById(R.id.textViewFallasteid);
        textViewInstrucc = view.findViewById(R.id.textviewInstrucc);
        textViewSeleccione = view.findViewById(R.id.textviewSeleccioneid);
        textViewEscuche = view.findViewById(R.id.textViewEscucheid);

        animacionSonido = view.findViewById(R.id.animacion_sonido);


        //imagen instrucciones
        //imageninstrucc.setImageResource(R.drawable.icon_conciencia);

        //Lista de sonidos
        List<Integer> ListaSonidos = new ArrayList<Integer>();
        ListaSonidos.add(R.raw.seleccione_balde_botella_mesa_sombrero_cuchara);
        ListaSonidos.add(R.raw.seleccione_balde_mesa_carro_escoba_sombrero_balon_cuchara_botella);
        ListaSonidos.add(R.raw.seleccione_balon_cuchara_balde_sombrero_escoba_botella);
        ListaSonidos.add(R.raw.seleccione_carro_balde_escoba_botella_balon);
        ListaSonidos.add(R.raw.seleccione_carro_balon_botella_escoba_sombrero_cuchara);
        ListaSonidos.add(R.raw.seleccione_carro_escoba_balde);
        ListaSonidos.add(R.raw.seleccione_cuchara_mesa_sombrero_balde_carro);
        ListaSonidos.add(R.raw.seleccione_escoba_sombrero);
        ListaSonidos.add(R.raw.seleccione_escoba_sombrero_balde);
        ListaSonidos.add(R.raw.seleccione_mesa_balde_balon_botella_escoba_sombrero_cuchara);
        ListaSonidos.add(R.raw.seleccione_mesa_cuchara_sombrero_pelota);
        ListaSonidos.add(R.raw.seleccione_sombrero_balde_balon_mesa_escoba_botella);

        //Lista de imagenes
        List<Integer> ListaImagenes = new ArrayList<Integer>();
        ListaImagenes.add(R.drawable.balde_botella_mesa_sombrero_cuchara);
        ListaImagenes.add(R.drawable.balon_botella_mesa_sombrero_cuchara);
        ListaImagenes.add(R.drawable.balde_mesa_carro_escoba_sombrero_balon_cuchara_botella);
        ListaImagenes.add(R.drawable.balde_mesa_carro_escoba_sombrero_balon_cuchara_anillo);
        ListaImagenes.add(R.drawable.balon_cuchara_balde_sombrero_escoba_botella);
        ListaImagenes.add(R.drawable.balon_cuchara_carro_mesa_escoba_botella);
        ListaImagenes.add(R.drawable.carro_balde_escoba_botella_balon);
        ListaImagenes.add(R.drawable.carro_balde_cuchara_botella_balon);
        ListaImagenes.add(R.drawable.carro_balon_botella_escoba_sombrero_cuchara);
        ListaImagenes.add(R.drawable.mesa_balde_botella_escoba_sombrero_cuchara);
        ListaImagenes.add(R.drawable.carro_escoba_balde);
        ListaImagenes.add(R.drawable.cuchara_escoba_balde);
        ListaImagenes.add(R.drawable.cuchara_mesa_sombrero_balde_carro);
        ListaImagenes.add(R.drawable.botella_mesa_sombrero_balde_carro);
        ListaImagenes.add(R.drawable.escoba_sombrero);
        ListaImagenes.add(R.drawable.escoba_balde);
        ListaImagenes.add(R.drawable.escoba_sombrero_balde);
        ListaImagenes.add(R.drawable.escoba_sombrero_mesa);
        ListaImagenes.add(R.drawable.mesa_balde_balon_botella_escoba_sombrero_cuchara);
        ListaImagenes.add(R.drawable.carro_balon_balde_escoba_sombrero_cuchara_botella);
        ListaImagenes.add(R.drawable.mesa_cuchara_sombrero_balon);
        ListaImagenes.add(R.drawable.mesa_cuchara_sombrero_botella);
        ListaImagenes.add(R.drawable.sombrero_balde_balon_mesa_escoba_botella);
        ListaImagenes.add(R.drawable.sombrero_balde_cuchara_botella_balon_carro_escoba);


        //Lista de sonidos
        List<Integer> ListaSonidos2opciones = new ArrayList<Integer>();
        ListaSonidos2opciones.add(R.raw.seleccione_escoba_sombrero);


        List<Integer> ListaSonidos3opciones = new ArrayList<Integer>();
        ListaSonidos3opciones.add(R.raw.seleccione_carro_escoba_balde);
        ListaSonidos3opciones.add(R.raw.seleccione_escoba_sombrero_balde);

        //Lista de sonidos
        List<Integer> ListaSonidos4opciones = new ArrayList<Integer>();
        ListaSonidos4opciones.add(R.raw.seleccione_mesa_cuchara_sombrero_pelota);

        //Lista de sonidos
        List<Integer> ListaSonidos5opciones = new ArrayList<Integer>();
        ListaSonidos5opciones.add(R.raw.seleccione_balde_botella_mesa_sombrero_cuchara);
        ListaSonidos5opciones.add(R.raw.seleccione_carro_balde_escoba_botella_balon);
        ListaSonidos5opciones.add(R.raw.seleccione_cuchara_mesa_sombrero_balde_carro);


        //Lista de sonidos
        List<Integer> ListaSonidos6opciones = new ArrayList<Integer>();
        ListaSonidos6opciones.add(R.raw.seleccione_balon_cuchara_balde_sombrero_escoba_botella);
        ListaSonidos6opciones.add(R.raw.seleccione_carro_balon_botella_escoba_sombrero_cuchara);
        ListaSonidos6opciones.add(R.raw.seleccione_sombrero_balde_balon_mesa_escoba_botella);

        //Lista de sonidos
        List<Integer> ListaSonidos7opciones = new ArrayList<Integer>();
        ListaSonidos7opciones.add(R.raw.seleccione_mesa_balde_balon_botella_escoba_sombrero_cuchara);

        //Lista de sonidos
        List<Integer> ListaSonidos8opciones = new ArrayList<Integer>();
        ListaSonidos8opciones.add(R.raw.seleccione_balde_mesa_carro_escoba_sombrero_balon_cuchara_botella);




//Lista de imagenes 2 opciones
        List<Integer> ListaImagenes2opciones = new ArrayList<Integer>();
        ListaImagenes2opciones.add(R.drawable.escoba_sombrero);
        ListaImagenes2opciones.add(R.drawable.escoba_balde);


        //Lista de imagenes 3 opciones
        List<Integer> ListaImagenes3opciones = new ArrayList<Integer>();
        ListaImagenes3opciones.add(R.drawable.carro_escoba_balde);
        ListaImagenes3opciones.add(R.drawable.cuchara_escoba_balde);
        ListaImagenes3opciones.add(R.drawable.escoba_sombrero_balde);
        ListaImagenes3opciones.add(R.drawable.escoba_sombrero_mesa);


        //Lista de imagenes 4 opciones
        List<Integer> ListaImagenes4opciones = new ArrayList<Integer>();
        ListaImagenes4opciones.add(R.drawable.mesa_cuchara_sombrero_balon);
        ListaImagenes4opciones.add(R.drawable.mesa_cuchara_sombrero_botella);

        //Lista de imagenes 5 opciones
        List<Integer> ListaImagenes5opciones = new ArrayList<Integer>();
        ListaImagenes5opciones.add(R.drawable.balde_botella_mesa_sombrero_cuchara);
        ListaImagenes5opciones.add(R.drawable.balon_botella_mesa_sombrero_cuchara);
        ListaImagenes5opciones.add(R.drawable.carro_balde_escoba_botella_balon);
        ListaImagenes5opciones.add(R.drawable.carro_balde_cuchara_botella_balon);
        ListaImagenes5opciones.add(R.drawable.cuchara_mesa_sombrero_balde_carro);
        ListaImagenes5opciones.add(R.drawable.botella_mesa_sombrero_balde_carro);

        //Lista de imagenes 6 opciones
        List<Integer> ListaImagenes6opciones = new ArrayList<Integer>();
        ListaImagenes6opciones.add(R.drawable.balon_cuchara_balde_sombrero_escoba_botella);
        ListaImagenes6opciones.add(R.drawable.balon_cuchara_carro_mesa_escoba_botella);
        ListaImagenes6opciones.add(R.drawable.carro_balon_botella_escoba_sombrero_cuchara);
        ListaImagenes6opciones.add(R.drawable.mesa_balde_botella_escoba_sombrero_cuchara);
        ListaImagenes6opciones.add(R.drawable.sombrero_balde_balon_mesa_escoba_botella);
        ListaImagenes6opciones.add(R.drawable.sombrero_balde_cuchara_botella_balon_carro_escoba);

        //Lista de imagenes 7 opciones
        List<Integer> ListaImagenes7opciones = new ArrayList<Integer>();
        ListaImagenes7opciones.add(R.drawable.mesa_balde_balon_botella_escoba_sombrero_cuchara);
        ListaImagenes7opciones.add(R.drawable.carro_balon_balde_escoba_sombrero_cuchara_botella);

        //Lista de imagenes 8 opciones
        List<Integer> ListaImagenes8opciones = new ArrayList<Integer>();
        ListaImagenes8opciones.add(R.drawable.balde_mesa_carro_escoba_sombrero_balon_cuchara_botella);
        ListaImagenes8opciones.add(R.drawable.balde_mesa_carro_escoba_sombrero_balon_cuchara_anillo);

        //bd
        DB = new DBHelperPuntajes(getContext());

        // funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(view); //nacControllermio es el nombre de la instancia


        // Initialize a new Handler
        handler = new Handler();
        //handlerVisibilidad = new Handler();




//RUNNABLE O TIMER PARA EJECUTAR PROCESO CADA CIERTO TIEMPO
        //final Handler handler = new Handler();
        //final Runnable runnable = new Runnable() {
        runnable_iniciar = new Runnable() {
            public void run() {

                //Generacion Aleatoria de numeros imagenes y sonidos
                int randomIntListaSonidos0 = (new Random().nextInt(ListaSonidos2opciones.size()));

                //Obtengo de la lista de sonidos, el sonidoFrase con la posicion random generada
                numero_sonido = ListaSonidos2opciones.get(randomIntListaSonidos0);

                //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                sonidoInstruccion = MediaPlayer.create(getActivity(), numero_sonido);


                /*
                //Generacion Aleatoria de numeros imagenes y sonidos
                int randomIntListaSonidos = (new Random().nextInt(ListaSonidos.size()));

                //Obtengo de la lista de sonidos, el sonidoFrase con la posicion random generada
                numero_sonido = ListaSonidos.get(randomIntListaSonidos);

                //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                sonidoInstruccion = MediaPlayer.create(getActivity(), numero_sonido);

                //Generacion Aleatoria de numeros imagenes y sonidos
                //int randomIntlistaImagenes = (new Random().nextInt(ListaImagenes.size()));
                int randomIntlistaImagenes = randomIntListaSonidos*2;

                if (randomIntlistaImagenes % 2 == 0) {
                    //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                    numero_imagen1 = ListaImagenes.get(randomIntlistaImagenes);
                    numero_imagen2 = ListaImagenes.get(randomIntlistaImagenes+1);
                } else {
                    //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                    numero_imagen1 = ListaImagenes.get(randomIntlistaImagenes - 1);
                    numero_imagen2 = ListaImagenes.get(randomIntlistaImagenes);
                }*/
                if(i<1){
                    //Generacion Aleatoria de numeros imagenes y sonidos
                    //int randomIntListaSonidos = (new Random().nextInt(ListaSonidos2opciones.size()));
                    randomIntListaSonidos = (new Random().nextInt(ListaSonidos2opciones.size()));

                    //Obtengo de la lista de sonidos, el sonidoFrase con la posicion random generada
                    numero_sonido = ListaSonidos2opciones.get(randomIntListaSonidos);

                    //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                    sonidoInstruccion = MediaPlayer.create(getActivity(), numero_sonido);

                    //Generacion Aleatoria de numeros imagenes y sonidos
                    //int randomIntlistaImagenes = (new Random().nextInt(ListaImagenes.size()));
                    int randomIntlistaImagenes = randomIntListaSonidos*2;

                    if (randomIntlistaImagenes % 2 == 0) {
                        //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                        numero_imagen1 = ListaImagenes2opciones.get(randomIntlistaImagenes);
                        numero_imagen2 = ListaImagenes2opciones.get(randomIntlistaImagenes+1);
                    } else {
                        //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                        numero_imagen1 = ListaImagenes2opciones.get(randomIntlistaImagenes - 1);
                        numero_imagen2 = ListaImagenes2opciones.get(randomIntlistaImagenes);
                    }

                }else if(i>=1&&i<3){
                    //Generacion Aleatoria de numeros imagenes y sonidos
                    //int randomIntListaSonidos = (new Random().nextInt(ListaSonidos3opciones.size()));
                    randomIntListaSonidos = (new Random().nextInt(ListaSonidos3opciones.size()));
                    //int randomIntListaSonidos_anterior= randomIntListaSonidos;
                    while(randomIntListaSonidos==randomIntListaSonidos_anterior){
                        randomIntListaSonidos = (new Random().nextInt(ListaSonidos3opciones.size()));
                    }

                    //Obtengo de la lista de sonidos, el sonidoFrase con la posicion random generada
                    numero_sonido = ListaSonidos3opciones.get(randomIntListaSonidos);

                    //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                    sonidoInstruccion = MediaPlayer.create(getActivity(), numero_sonido);

                    //Generacion Aleatoria de numeros imagenes y sonidos
                    //int randomIntlistaImagenes = (new Random().nextInt(ListaImagenes.size()));
                    int randomIntlistaImagenes = randomIntListaSonidos*2;

                    if (randomIntlistaImagenes % 2 == 0) {
                        //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                        numero_imagen1 = ListaImagenes3opciones.get(randomIntlistaImagenes);
                        numero_imagen2 = ListaImagenes3opciones.get(randomIntlistaImagenes+1);
                    } else {
                        //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                        numero_imagen1 = ListaImagenes3opciones.get(randomIntlistaImagenes - 1);
                        numero_imagen2 = ListaImagenes3opciones.get(randomIntlistaImagenes);
                    }

                }else if(i>=3&&i<4){
                    //Generacion Aleatoria de numeros imagenes y sonidos
                    //int randomIntListaSonidos = (new Random().nextInt(ListaSonidos4opciones.size()));
                    randomIntListaSonidos = (new Random().nextInt(ListaSonidos4opciones.size()));

                    //Obtengo de la lista de sonidos, el sonidoFrase con la posicion random generada
                    numero_sonido = ListaSonidos4opciones.get(randomIntListaSonidos);

                    //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                    sonidoInstruccion = MediaPlayer.create(getActivity(), numero_sonido);

                    //Generacion Aleatoria de numeros imagenes y sonidos
                    //int randomIntlistaImagenes = (new Random().nextInt(ListaImagenes.size()));
                    int randomIntlistaImagenes = randomIntListaSonidos*2;

                    if (randomIntlistaImagenes % 2 == 0) {
                        //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                        numero_imagen1 = ListaImagenes4opciones.get(randomIntlistaImagenes);
                        numero_imagen2 = ListaImagenes4opciones.get(randomIntlistaImagenes+1);
                    } else {
                        //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                        numero_imagen1 = ListaImagenes4opciones.get(randomIntlistaImagenes - 1);
                        numero_imagen2 = ListaImagenes4opciones.get(randomIntlistaImagenes);
                    }

                }else if(i>=4&&i<7){
                    //Generacion Aleatoria de numeros imagenes y sonidos
                    //int randomIntListaSonidos = (new Random().nextInt(ListaSonidos5opciones.size()));
                    //int randomIntListaSonidos_anterior= randomIntListaSonidos;
                    randomIntListaSonidos = (new Random().nextInt(ListaSonidos5opciones.size()));
                    while(randomIntListaSonidos==randomIntListaSonidos_anterior){
                        randomIntListaSonidos = (new Random().nextInt(ListaSonidos5opciones.size()));
                    }

                    //Obtengo de la lista de sonidos, el sonidoFrase con la posicion random generada
                    numero_sonido = ListaSonidos5opciones.get(randomIntListaSonidos);

                    //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                    sonidoInstruccion = MediaPlayer.create(getActivity(), numero_sonido);

                    //Generacion Aleatoria de numeros imagenes y sonidos
                    //int randomIntlistaImagenes = (new Random().nextInt(ListaImagenes.size()));
                    int randomIntlistaImagenes = randomIntListaSonidos*2;

                    if (randomIntlistaImagenes % 2 == 0) {
                        //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                        numero_imagen1 = ListaImagenes5opciones.get(randomIntlistaImagenes);
                        numero_imagen2 = ListaImagenes5opciones.get(randomIntlistaImagenes+1);
                    } else {
                        //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                        numero_imagen1 = ListaImagenes5opciones.get(randomIntlistaImagenes - 1);
                        numero_imagen2 = ListaImagenes5opciones.get(randomIntlistaImagenes);
                    }

                }else if(i>=7&&i<10){
                    //Generacion Aleatoria de numeros imagenes y sonidos
                    int randomIntListaSonidos = (new Random().nextInt(ListaSonidos6opciones.size()));
                    int randomIntListaSonidos_anterior= randomIntListaSonidos;
                    while(randomIntListaSonidos==randomIntListaSonidos_anterior){
                        randomIntListaSonidos = (new Random().nextInt(ListaSonidos6opciones.size()));
                    }

                    //Obtengo de la lista de sonidos, el sonidoFrase con la posicion random generada
                    numero_sonido = ListaSonidos6opciones.get(randomIntListaSonidos);

                    //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                    sonidoInstruccion = MediaPlayer.create(getActivity(), numero_sonido);

                    //Generacion Aleatoria de numeros imagenes y sonidos
                    //int randomIntlistaImagenes = (new Random().nextInt(ListaImagenes.size()));
                    int randomIntlistaImagenes = randomIntListaSonidos*2;

                    if (randomIntlistaImagenes % 2 == 0) {
                        //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                        numero_imagen1 = ListaImagenes6opciones.get(randomIntlistaImagenes);
                        numero_imagen2 = ListaImagenes6opciones.get(randomIntlistaImagenes+1);
                    } else {
                        //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                        numero_imagen1 = ListaImagenes6opciones.get(randomIntlistaImagenes - 1);
                        numero_imagen2 = ListaImagenes6opciones.get(randomIntlistaImagenes);
                    }

                }else if(i>=10&&i<11){
                    //Generacion Aleatoria de numeros imagenes y sonidos
                    //int randomIntListaSonidos = (new Random().nextInt(ListaSonidos7opciones.size()));
                    randomIntListaSonidos = (new Random().nextInt(ListaSonidos7opciones.size()));

                    //Obtengo de la lista de sonidos, el sonidoFrase con la posicion random generada
                    numero_sonido = ListaSonidos7opciones.get(randomIntListaSonidos);

                    //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                    sonidoInstruccion = MediaPlayer.create(getActivity(), numero_sonido);

                    //Generacion Aleatoria de numeros imagenes y sonidos
                    //int randomIntlistaImagenes = (new Random().nextInt(ListaImagenes.size()));
                    int randomIntlistaImagenes = randomIntListaSonidos*2;

                    if (randomIntlistaImagenes % 2 == 0) {
                        //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                        numero_imagen1 = ListaImagenes7opciones.get(randomIntlistaImagenes);
                        numero_imagen2 = ListaImagenes7opciones.get(randomIntlistaImagenes+1);
                    } else {
                        //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                        numero_imagen1 = ListaImagenes7opciones.get(randomIntlistaImagenes - 1);
                        numero_imagen2 = ListaImagenes7opciones.get(randomIntlistaImagenes);
                    }

                }else if(i>=11&&i<12){
                    //Generacion Aleatoria de numeros imagenes y sonidos
                    //int randomIntListaSonidos = (new Random().nextInt(ListaSonidos8opciones.size()));
                    randomIntListaSonidos = (new Random().nextInt(ListaSonidos8opciones.size()));

                    //Obtengo de la lista de sonidos, el sonidoFrase con la posicion random generada
                    numero_sonido = ListaSonidos8opciones.get(randomIntListaSonidos);

                    //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                    sonidoInstruccion = MediaPlayer.create(getActivity(), numero_sonido);

                    //Generacion Aleatoria de numeros imagenes y sonidos
                    //int randomIntlistaImagenes = (new Random().nextInt(ListaImagenes.size()));
                    int randomIntlistaImagenes = randomIntListaSonidos*2;

                    if (randomIntlistaImagenes % 2 == 0) {
                        //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                        numero_imagen1 = ListaImagenes8opciones.get(randomIntlistaImagenes);
                        numero_imagen2 = ListaImagenes8opciones.get(randomIntlistaImagenes+1);
                    } else {
                        //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                        numero_imagen1 = ListaImagenes8opciones.get(randomIntlistaImagenes - 1);
                        numero_imagen2 = ListaImagenes8opciones.get(randomIntlistaImagenes);
                    }

                }

                randomIntListaSonidos_anterior= randomIntListaSonidos;



                //para el textview aciertos no salga
                textViewAciertos.setVisibility(View.INVISIBLE);
                //para el textview seleeccione / correcto no salga
                textViewSeleccione.setVisibility(View.INVISIBLE);
                //para que salga el texto "escuche los sonidos"
                textViewEscuche.setVisibility(View.VISIBLE);

                //para ocultar las imagenes 1 2 y 3 nuevamente
                imagen1.setVisibility(View.INVISIBLE);
                imagen2.setVisibility(View.INVISIBLE);


                //para activar nuevamente la opcion clickeable de las imagenes
                imagen1.setClickable(true);
                imagen2.setClickable(true);


                reproducirSonidoInstruccion();



                //Retardo
                //global_retardo = (int) (Math.random() * 13000) + 11000;//genera retardo entre 3 y 5 segs
                global_retardo = 20000;

                if (i >= 12) { // just remove call backs//
                    //para parar el sonidoFrase correctamente
                    if(sonidoInstruccion != null) {
                        sonidoInstruccion.stop();
                        sonidoInstruccion.release();
                        sonidoInstruccion = null;
                    }


                    imagen1.setVisibility(View.INVISIBLE);
                    imagen2.setVisibility(View.INVISIBLE);
                    animacionSonido.setVisibility(View.INVISIBLE);
                    textViewEscuche.setVisibility(View.INVISIBLE);

                    iniciar.setVisibility(View.INVISIBLE);
                    parar.setVisibility(View.INVISIBLE);
                    textViewAciertos.setVisibility(View.INVISIBLE);
                    handler.removeCallbacks(this);


                    if (aciertos_actividad >=9) {

                        //guardar puntaje en BD
                        guardarPuntajeBD();

                        //Guardar puntaje en sharedpreferences
                        guardarPreferenciasPuntaje();

                        //pasar a otro fragment
                        //navControllermio.navigate(R.id.actividad_conc_3_felicitacionFragment);
                        navControllermio.navigate(R.id.action_actividad_comp_3Fragment_to_actividad_comp_3_felicitacionFragment);//pasar a otro fragment con actions

                    }
                    else{

                        navControllermio.navigate(R.id.action_actividad_comp_3Fragment_to_actividad_comp_3_erradoFragment);//pasar a otro fragment con actions

                        guardarPuntajeBD();
                        //Guardar puntaje en sharedpreferences
                        guardarPreferenciasPuntaje();

                    }
                }
                else{ // post again
                    i++;
                    handler.postDelayed(this, global_retardo);//Repite el proceso cada cierto tiempo, en este caso globarl_retardo al azar
                }
            }
        };


        runnable_iniciar_desordenado = new Runnable() {
            public void run() {
                //Log.d("Runnable", "Handler is working");

                //Generacion Aleatoria de numeros imagenes y sonidos
                int randomIntListaSonidos = (new Random().nextInt(ListaSonidos.size()));

                //Obtengo de la lista de sonidos, el sonidoFrase con la posicion random generada
                numero_sonido = ListaSonidos.get(randomIntListaSonidos);

                //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                sonidoInstruccion = MediaPlayer.create(getActivity(), numero_sonido);

                //Imagenes aleatorias con sonidos correspondientes
                //imagenes aleatorias (con y sin sonidoFrase)
                //Generacion Aleatoria de numeros imagenes y sonidos
                //int randomIntlistaImagenes = (new Random().nextInt(ListaImagenes.size()));
                int randomIntlistaImagenes = randomIntListaSonidos*2;


                if (randomIntlistaImagenes % 2 == 0) {

                    //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                    numero_imagen1 = ListaImagenes.get(randomIntlistaImagenes);
                    numero_imagen2 = ListaImagenes.get(randomIntlistaImagenes+1);

                } else {

                    //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                    numero_imagen1 = ListaImagenes.get(randomIntlistaImagenes - 1);
                    numero_imagen2 = ListaImagenes.get(randomIntlistaImagenes);

                }



                //para el textview aciertos no salga
                textViewAciertos.setVisibility(View.INVISIBLE);
                //para el textview seleeccione / correcto no salga
                textViewSeleccione.setVisibility(View.INVISIBLE);
                //para que salga el texto "escuche los sonidos"
                textViewEscuche.setVisibility(View.VISIBLE);

                //para ocultar las imagenes 1 2 y 3 nuevamente
                imagen1.setVisibility(View.INVISIBLE);
                imagen2.setVisibility(View.INVISIBLE);


                //para activar nuevamente la opcion clickeable de las imagenes
                imagen1.setClickable(true);
                imagen2.setClickable(true);


                reproducirSonidoInstruccion();


                //Retardo
                //global_retardo = (int) (Math.random() * 13000) + 11000;//genera retardo entre 3 y 5 segs
                global_retardo = 20000;

                if (i >= 13) { // just remove call backs//
                    //para parar el sonidoFrase correctamente
                    if(sonidoInstruccion != null) {
                        sonidoInstruccion.stop();
                        sonidoInstruccion.release();
                        sonidoInstruccion = null;
                    }


                    imagen1.setVisibility(View.INVISIBLE);
                    imagen2.setVisibility(View.INVISIBLE);

                    iniciar.setVisibility(View.INVISIBLE);
                    parar.setVisibility(View.INVISIBLE);
                    textViewAciertos.setVisibility(View.INVISIBLE);
                    handler.removeCallbacks(this);
                    //Log.d("Runnable", "ok");

                    if (aciertos_actividad >=10) {

                        //guardar puntaje en BD
                        guardarPuntajeBD();

                        //Guardar puntaje en sharedpreferences
                        guardarPreferenciasPuntaje();

                        //pasar a otro fragment
                        //navControllermio.navigate(R.id.actividad_conc_3_felicitacionFragment);
                        navControllermio.navigate(R.id.action_actividad_ident_3Fragment_to_actividad_ident_3_felicitacionFragment);//pasar a otro fragment con actions

                    }
                    else{

                        navControllermio.navigate(R.id.action_actividad_ident_3Fragment_to_actividad_ident_3_erradoFragment);//pasar a otro fragment con actions

                        guardarPuntajeBD();
                        //Guardar puntaje en sharedpreferences
                        guardarPreferenciasPuntaje();

                    }
                }
                else{ // post again
                    i++;
                    handler.postDelayed(this, global_retardo);//Repite el proceso cada cierto tiempo, en este caso globarl_retardo al azar
                }
            }
        };

//FUNCION PARA EL BOTON INICIAR
        iniciar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                i=0;
                indice =0;
                //variables

                aciertos_actividad = 0;
                //int retardo = (int) (Math.random() * 3000) + 1; //genera retardo aleatoriamente entre un rango 1 - n
                int retardo =2000;
                //defino visibilidad objetos botones y textos
                parar.setVisibility(View.VISIBLE);
                iniciar.setVisibility(View.INVISIBLE);
                instrucciones.setVisibility(View.INVISIBLE);


                textViewAciertos.setVisibility(View.INVISIBLE);
                textViewInstrucc.setVisibility(View.INVISIBLE);
                //imageninstrucc.setVisibility(View.INVISIBLE);

                //textViewEscuche.setVisibility(View.VISIBLE);

                //inicio el runnable despues de 1-3 segundos
                //handler.removeCallbacks(runnable_iniciar);
                //handler.postDelayed(runnable_iniciar, retardo);
                handler.removeCallbacks(runnable_iniciar);
                handler.postDelayed(runnable_iniciar, retardo);
            }
        });

//FUNCION PARA EL BOTON PARAR
        parar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(sonidoInstruccion != null) {
                    sonidoInstruccion.stop();
                    sonidoInstruccion.release();
                    sonidoInstruccion = null;
                }


                imagen1.setVisibility(View.INVISIBLE);
                imagen2.setVisibility(View.INVISIBLE);

                animacionSonido.setVisibility(View.INVISIBLE);


                parar.setVisibility(View.INVISIBLE);
                iniciar.setVisibility(View.VISIBLE);
                //ayudas.setVisibility(View.VISIBLE);
                instrucciones.setVisibility(View.VISIBLE);

                textViewEscuche.setVisibility(View.INVISIBLE);
                textViewSeleccione.setVisibility(View.INVISIBLE);
                textViewAciertos.setVisibility(View.INVISIBLE);
                textViewFallaste.setVisibility(View.INVISIBLE);

                handler.removeCallbacks(runnable_iniciar);
                /*
                handler.removeCallbacks(runnable_2_opciones);
                handler.removeCallbacks(runnable_3_opciones);
                handler.removeCallbacks(runnable_4_opciones);
                handler.removeCallbacks(runnable_5_opciones);
                handler.removeCallbacks(runnable_6_opciones);
                handler.removeCallbacks(runnable_7_opciones);
                */



            }
        });


        //FUNCION PARA EL BOTON AYUDAS
        /*
        opciones.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //navControllermio.navigate(R.id.actividad_conc_3_opcionesFragment); //pasar a otro fragment
                navControllermio.navigate(R.id.action_actividad_ident_3Fragment_to_actividad_ident_3_instruccionesFragment); //pasar a otro fragment con actions
                handler.removeCallbacks(runnable_iniciar);
                Log.d("Runnable", "ok");


            }
        });
        */

//FUNCION PARA EL BOTON INSTRUCCIONES
        instrucciones.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //navControllermio.navigate(R.id.actividad_conc_2_ayudasFragment); //pasar a otro fragment
                navControllermio.navigate(R.id.action_actividad_comp_3Fragment_to_actividad_comp_3_instruccionesFragment); //pasar a otro fragment con actions

                handler.removeCallbacks(runnable_iniciar);
                /*
                handler.removeCallbacks(runnable_2_opciones);
                handler.removeCallbacks(runnable_3_opciones);
                handler.removeCallbacks(runnable_4_opciones);
                handler.removeCallbacks(runnable_5_opciones);
                handler.removeCallbacks(runnable_6_opciones);
                handler.removeCallbacks(runnable_7_opciones);
                */
            }
        });



//funciones on clicklistener para las imagenes

        imagen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seleccionarRespuesta1();

            }
        });
        imagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seleccionarRespuesta2();

            }
        });


    }//onviewcreated




//MÉTODOS UTILIZADOS

    private void reproducirSonidoInstruccion(){
        //Reproducir sonido instruccion

        sonidoInstruccion.start();

        animacionSonido.setVisibility(View.VISIBLE);

        //una vez acabado el sonidoFrase muestra las imagenes
        sonidoInstruccion.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidoInstruccion.stop();
                sonidoInstruccion.release();
                sonidoInstruccion = null;

                animacionSonido.setVisibility(View.INVISIBLE);

                mostrarImagenesAleatorio();
                // play next audio file
            }
        });
    }




    private void mostrarImagenesAleatorio() {
        //set textview "selecione imagen"


        imagen1.setBackgroundResource(R.drawable.estilo_imagen_blanco);
        imagen2.setBackgroundResource(R.drawable.estilo_imagen_blanco);


        imagen1.setVisibility(View.VISIBLE);
        imagen2.setVisibility(View.VISIBLE);

        textViewSeleccione.setText("Seleccione una imagen");
        textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_azul);
        textViewSeleccione.setVisibility(View.VISIBLE);
        textViewEscuche.setVisibility(View.INVISIBLE);

        Random rand = new Random();
        //int randomImagenes= rand.nextInt(2);
        randomImagenes = rand.nextInt(2);//numero aleatorio entre 0 y 1
        switch (randomImagenes){
            case 0:
                imagen1.setImageResource(numero_imagen1); //imagen1 = imagencorto
                imagen2.setImageResource(numero_imagen2); //imagen2 = imagenlargo
                break;
            case 1:
                imagen1.setImageResource(numero_imagen2); //imagen1 = imagencorto
                imagen2.setImageResource(numero_imagen1); //imagen2 = imagenlargo
                break;
        }
    }


    private void seleccionarRespuesta1() {
        if (randomImagenes == 0) {//0 muestra la imagen de la respuesta en imageview1
            //Acierta
            imagen1.setImageResource(R.drawable.check); //imagen1 = imagencorto
            imagen1.setBackgroundResource(R.drawable.estilo_imagen_verde);
            imagen1.setClickable(false);

            imagen2.setVisibility(View.INVISIBLE);


            //puntaje
            aciertos_actividad = aciertos_actividad + 1;
            mostrarTextviewAciertos();
            //pasar al siguiente sonido
            iniciarRunnable();

        } else{//se equivoca
            imagen1.setImageResource(R.drawable.errado); //imagen1 = imagencorto
            imagen1.setBackgroundResource(R.drawable.estilo_imagen_rojo);
            imagen1.setClickable(false);

            imagen2.setVisibility(View.INVISIBLE);
            mostrarTextviewFallaste();
            //pasar al siguiente sonido
            iniciarRunnable();

        }
    }

    private void seleccionarRespuesta2() {
        if (randomImagenes == 1) {//0 muestra la imagen de la respuesta en imageview2
            //Acierta
            imagen2.setImageResource(R.drawable.check); //imagen2 = imagenREspuesta
            imagen2.setBackgroundResource(R.drawable.estilo_imagen_verde);
            imagen2.setClickable(false);

            imagen1.setVisibility(View.INVISIBLE);

            //puntaje
            aciertos_actividad = aciertos_actividad + 1;
            mostrarTextviewAciertos();
            //pasar al siguiente sonido
            iniciarRunnable();

        } else{//se equivoca
            imagen2.setImageResource(R.drawable.errado); //
            imagen2.setBackgroundResource(R.drawable.estilo_imagen_rojo);
            imagen2.setClickable(false);

            imagen1.setVisibility(View.INVISIBLE);

            mostrarTextviewFallaste();

            //pasar al siguiente sonido
            iniciarRunnable();

        }
    }


    private void iniciarRunnable(){
        //Iniciar runnable dependiendo las ayudas seleccionadas
        //global_retardo = (int) (Math.random() * 4000) + 2000; //genera retardo aleatoriamente entre un rango 1 - n
        //cargarPreferenciasAyudas();
        int retardo = 5000;

        handler.removeCallbacks(runnable_iniciar);
        handler.postDelayed(runnable_iniciar, retardo);
/*
        switch (numero_opciones){
            case 2:
                handler.removeCallbacks(runnable_2_opciones);
                handler.postDelayed(runnable_2_opciones, retardo);
            case 3:
                handler.removeCallbacks(runnable_3_opciones);
                handler.postDelayed(runnable_3_opciones, retardo);
            case 4:
                handler.removeCallbacks(runnable_4_opciones);
                handler.postDelayed(runnable_4_opciones, retardo);
            case 5:
                handler.removeCallbacks(runnable_5_opciones);
                handler.postDelayed(runnable_5_opciones, retardo);
            case 6:
                handler.removeCallbacks(runnable_6_opciones);
                handler.postDelayed(runnable_6_opciones, retardo);
            case 7:
                handler.removeCallbacks(runnable_7_opciones);
                handler.postDelayed(runnable_7_opciones, retardo);
        }
*/

    }

    //Shared PReferences para puntajes
    private void guardarPreferenciasPuntaje(){
        SharedPreferences preferencespuntaje = getActivity().getSharedPreferences("puntajes_table", Context.MODE_PRIVATE);

        int puntaje = aciertos_actividad;
        SharedPreferences.Editor editor = preferencespuntaje.edit();
        editor.putInt("puntaje", puntaje);
        editor.commit();
    }

    private void guardarPuntajeBD(){
        //guardar puntaje en BD
        int actividadTXT = 3;
        String subhabilidadTXT = "Comprensión";
        int puntajeTXT = aciertos_actividad;
        String infoTXT= infoActividad;//Informacion sobre la actividad (Ayudas)


        String fechaTXT;
        Calendar calendar;
        SimpleDateFormat simpleDateFormat;

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aaa");
        fechaTXT = simpleDateFormat.format(calendar.getTime()).toString();

        //insertar datos a la bd
        Boolean checkinsertpuntaje = DB.insertPuntaje(fechaTXT, actividadTXT,subhabilidadTXT,puntajeTXT, infoTXT);        if (checkinsertpuntaje == true) {
            Toast.makeText(getContext(), "Puntaje guardado", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "Falló registro de puntaje!", Toast.LENGTH_SHORT).show();
        }

    }

    private void mostrarTextviewAciertos(){
        textViewEscuche.setVisibility(View.INVISIBLE);

        //handler para que muestre texview aciertos 3 segundos y se desaparezca
        textViewAciertos.setVisibility(View.VISIBLE);
        textViewAciertos.setText("¡Muy bien! +" + aciertos_actividad);
        //para mostrar el textview de abajo (textviewselecione)
        textViewSeleccione.setVisibility(View.VISIBLE);//setClickable(false);
        textViewSeleccione.setText("¡Correcto!");
        textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_verde);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewAciertos.setVisibility(View.INVISIBLE);// do your operation here
                textViewSeleccione.setVisibility(View.INVISIBLE);// do your operation here
                //para ocultar las imagenes 1 2 y 3 nuevamente
                imagen1.setVisibility(View.INVISIBLE);
                imagen2.setVisibility(View.INVISIBLE);
            }
        }, 3000);
    }


    private void mostrarTextviewFallaste(){
        textViewEscuche.setVisibility(View.INVISIBLE);

        //handler para que muestre la imagen 1 segundo y se desaparezca
        textViewFallaste.setVisibility(View.VISIBLE);
        textViewFallaste.setText("¡Fallaste!");
        //para mostrar el textview de abajo (textviewselecione)
        textViewSeleccione.setVisibility(View.VISIBLE);//setClickable(false);
        textViewSeleccione.setText("¡Incorrecto!");
        textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_rojo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewFallaste.setVisibility(View.INVISIBLE);// do your operation here
                textViewSeleccione.setVisibility(View.INVISIBLE);// do your operation here
                //para ocultar las imagenes 1 2 y 3 nuevamente
                imagen1.setVisibility(View.INVISIBLE);
                imagen2.setVisibility(View.INVISIBLE);
            }
        }, 3000);
    }



    //FUNCION ON PAUSE DE LA CLASE
    @Override
    public void onPause() {
        handler.removeCallbacks(runnable_iniciar);
        /*
        handler.removeCallbacks(runnable_2_opciones);
        handler.removeCallbacks(runnable_3_opciones);
        handler.removeCallbacks(runnable_4_opciones);
        handler.removeCallbacks(runnable_5_opciones);
        handler.removeCallbacks(runnable_6_opciones);
        handler.removeCallbacks(runnable_7_opciones);
         */

        if(sonidoInstruccion != null) {
            sonidoInstruccion.stop();
            sonidoInstruccion.release();
            sonidoInstruccion = null;
        }

        super.onPause();
    }

}//final
