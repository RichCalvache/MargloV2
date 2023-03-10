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
 * Use the {@link Actividad_comp_4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Actividad_comp_4Fragment extends Fragment {

    //Declaracion de variables
    private ImageView imagen1_2opciones,imagen2_2opciones, imagen1_3opciones,imagen2_3opciones,imagen3_3opciones,imagen1_4opciones,imagen2_4opciones,imagen3_4opciones,imagen4_4opciones;
    private Button iniciar, parar,instrucciones;
    private TextView textViewAciertos, textViewInstrucc, textViewSeleccione, textViewEscuche, textViewFallaste;
    MediaPlayer sonidoInstruccion1,sonidoInstruccion2,sonidoInstruccion3;
    int i, aciertos_actividad, global_retardo, intentos;
    int numero_sonido1,numero_sonido2,numero_sonido3, numero_imagen1,numero_imagen2,numero_imagenRespuesta1,numero_imagenRespuesta2,numero_imagenRespuesta3, numero_imagenIncorrecta, randomImagenes;

    LottieAnimationView animacionSonido;

    int randomIntListaSonidos_anterior,randomIntListaSonidos;

    String infoActividad = "¡Nada que agregar!";


    DBHelperPuntajes DB;

    //forma para el runnable
    private Handler handler;
    private Runnable runnable_ordenado,runnable_iniciar_desordenado;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Actividad_comp_4Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Actividad_comp_4Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Actividad_comp_4Fragment newInstance(String param1, String param2) {
        Actividad_comp_4Fragment fragment = new Actividad_comp_4Fragment();
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
        return inflater.inflate(R.layout.fragment_actividad_comp_4, container, false);
    }



    //creacion del onviewcreated recomendado
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //IMAGEVIEWS DEIFERENTES OPCIONES
        imagen1_2opciones = view.findViewById(R.id.imagen1_2opciones);
        imagen2_2opciones = view.findViewById(R.id.imagen2_2opciones);

        imagen1_3opciones = view.findViewById(R.id.imagen1_3opciones);
        imagen2_3opciones = view.findViewById(R.id.imagen2_3opciones);
        imagen3_3opciones = view.findViewById(R.id.imagen3_3opciones);

        imagen1_4opciones = view.findViewById(R.id.imagen1_4opciones);
        imagen2_4opciones = view.findViewById(R.id.imagen2_4opciones);
        imagen3_4opciones = view.findViewById(R.id.imagen3_4opciones);
        imagen4_4opciones = view.findViewById(R.id.imagen4_4opciones);



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

        //Lista de sonidos 2 opciones
        List<Integer> ListaSonidos2opciones = new ArrayList<Integer>();
        ListaSonidos2opciones.add(R.raw.seleccione_hombre_durmiendo);
        ListaSonidos2opciones.add(R.raw.seleccione_mujer_cocinando);
        ListaSonidos2opciones.add(R.raw.seleccione_mujer_gritando);
        ListaSonidos2opciones.add(R.raw.seleccione_arbol);
        ListaSonidos2opciones.add(R.raw.seleccione_girasol);
        ListaSonidos2opciones.add(R.raw.seleccione_pareja_pajaros);
        ListaSonidos2opciones.add(R.raw.seleccione_pareja_perros);
        ListaSonidos2opciones.add(R.raw.seleccione_mujer_cocinando);
        ListaSonidos2opciones.add(R.raw.seleccione_pareja_jugando);
        ListaSonidos2opciones.add(R.raw.seleccione_pareja_caminando);
        ListaSonidos2opciones.add(R.raw.seleccione_perro_caminando);
        ListaSonidos2opciones.add(R.raw.seleccione_gato_caminando);
        ListaSonidos2opciones.add(R.raw.seleccione_dia_soleado);
        ListaSonidos2opciones.add(R.raw.seleccione_dia_lluvioso);
        ListaSonidos2opciones.add(R.raw.seleccione_hombre_perro_chocando_mano);


        //Lista de imagenes 2 opciones
        List<Integer> ListaImagenes2opciones = new ArrayList<Integer>();
        ListaImagenes2opciones.add(R.drawable.hombre_durmiendo);
        ListaImagenes2opciones.add(R.drawable.hombre_corriendo);
        ListaImagenes2opciones.add(R.drawable.mujer_cocinando);
        ListaImagenes2opciones.add(R.drawable.mujer_gritando);
        ListaImagenes2opciones.add(R.drawable.mujer_gritando);
        ListaImagenes2opciones.add(R.drawable.mujer_cocinando);
        ListaImagenes2opciones.add(R.drawable.arbol);
        ListaImagenes2opciones.add(R.drawable.girasol);
        ListaImagenes2opciones.add(R.drawable.girasol);
        ListaImagenes2opciones.add(R.drawable.arbol);
        ListaImagenes2opciones.add(R.drawable.pareja_pajaros);
        ListaImagenes2opciones.add(R.drawable.pareja_perros);
        ListaImagenes2opciones.add(R.drawable.pareja_perros);
        ListaImagenes2opciones.add(R.drawable.pareja_pajaros);
        ListaImagenes2opciones.add(R.drawable.mujer_cocinando);
        ListaImagenes2opciones.add(R.drawable.mujer_comiendo);
        ListaImagenes2opciones.add(R.drawable.pareja_jugando);
        ListaImagenes2opciones.add(R.drawable.pareja_caminando);

        ListaImagenes2opciones.add(R.drawable.pareja_caminando);
        ListaImagenes2opciones.add(R.drawable.pareja_jugando);

        ListaImagenes2opciones.add(R.drawable.perro_caminando);
        ListaImagenes2opciones.add(R.drawable.gato_caminando);

        ListaImagenes2opciones.add(R.drawable.gato_caminando);
        ListaImagenes2opciones.add(R.drawable.perro_caminando);

        ListaImagenes2opciones.add(R.drawable.dia_soleado);
        ListaImagenes2opciones.add(R.drawable.dia_lluvioso);

        ListaImagenes2opciones.add(R.drawable.dia_lluvioso);
        ListaImagenes2opciones.add(R.drawable.dia_soleado);

        ListaImagenes2opciones.add(R.drawable.hombre_perro);
        ListaImagenes2opciones.add(R.drawable.perro_caminando);

        //Lista de sonidos 3 opciones
        List<Integer> ListaSonidos3opciones = new ArrayList<Integer>();
        ListaSonidos3opciones.add(R.raw.seleccione_mujer_enojada);
        ListaSonidos3opciones.add(R.raw.seleccione_mujer_cocinando);
        ListaSonidos3opciones.add(R.raw.seleccione_mujer_gritando);

        ListaSonidos3opciones.add(R.raw.seleccione_mujer_cocinando);
        ListaSonidos3opciones.add(R.raw.seleccione_mujer_enojada);
        ListaSonidos3opciones.add(R.raw.seleccione_mujer_gritando);

        ListaSonidos3opciones.add(R.raw.seleccione_mujer_gritando);
        ListaSonidos3opciones.add(R.raw.seleccione_mujer_cocinando);
        ListaSonidos3opciones.add(R.raw.seleccione_mujer_enojada);

        ListaSonidos3opciones.add(R.raw.seleccione_pareja_comiendo);//1
        ListaSonidos3opciones.add(R.raw.seleccione_pareja_jugando);
        ListaSonidos3opciones.add(R.raw.seleccione_pareja_caminando);

        ListaSonidos3opciones.add(R.raw.seleccione_pareja_caminando);//1
        ListaSonidos3opciones.add(R.raw.seleccione_pareja_comiendo);
        ListaSonidos3opciones.add(R.raw.seleccione_pareja_jugando);

        ListaSonidos3opciones.add(R.raw.seleccione_pareja_jugando);
        ListaSonidos3opciones.add(R.raw.seleccione_pareja_comiendo);
        ListaSonidos3opciones.add(R.raw.seleccione_hombre_mujer_bailando);

        ListaSonidos3opciones.add(R.raw.seleccione_hombre_mujer_bailando);
        ListaSonidos3opciones.add(R.raw.seleccione_hombre_mujer_jugando);
        ListaSonidos3opciones.add(R.raw.seleccione_hombre_mujer_comiendo);

        ListaSonidos3opciones.add(R.raw.seleccione_hombre_mujer_comiendo);
        ListaSonidos3opciones.add(R.raw.seleccione_hombre_mujer_bailando);
        ListaSonidos3opciones.add(R.raw.seleccione_hombre_mujer_jugando);

        ListaSonidos3opciones.add(R.raw.seleccione_nino_comiendo_verduras);
        ListaSonidos3opciones.add(R.raw.seleccione_nina_comiendo_helado);
        ListaSonidos3opciones.add(R.raw.seleccione_hombre_mujer_comiendo);

        ListaSonidos3opciones.add(R.raw.seleccione_nina_comiendo_helado);
        ListaSonidos3opciones.add(R.raw.seleccione_hombre_mujer_comiendo);
        ListaSonidos3opciones.add(R.raw.seleccione_nino_comiendo_verduras);

        ListaSonidos3opciones.add(R.raw.seleccione_hombre_mujer_comiendo);
        ListaSonidos3opciones.add(R.raw.seleccione_nino_comiendo_verduras);
        ListaSonidos3opciones.add(R.raw.seleccione_nina_comiendo_helado);

        ListaSonidos3opciones.add(R.raw.seleccione_tormenta_de_rayos);
        ListaSonidos3opciones.add(R.raw.seleccione_tormenta_nieve);
        ListaSonidos3opciones.add(R.raw.seleccione_dia_soleado);

        ListaSonidos3opciones.add(R.raw.seleccione_tormenta_nieve);
        ListaSonidos3opciones.add(R.raw.seleccione_dia_soleado);
        ListaSonidos3opciones.add(R.raw.seleccione_tormenta_de_rayos);

        ListaSonidos3opciones.add(R.raw.seleccione_hombre_perro_sentados);
        ListaSonidos3opciones.add(R.raw.seleccione_hombre_durmiendo);
        ListaSonidos3opciones.add(R.raw.seleccione_hombre_mujer_abrazados);

        ListaSonidos3opciones.add(R.raw.seleccione_hombre_mujer_abrazados);
        ListaSonidos3opciones.add(R.raw.seleccione_hombre_perro_sentados);
        ListaSonidos3opciones.add(R.raw.seleccione_hombre_durmiendo);

        ListaSonidos3opciones.add(R.raw.seleccione_hombre_durmiendo);
        ListaSonidos3opciones.add(R.raw.seleccione_hombre_mujer_abrazados);
        ListaSonidos3opciones.add(R.raw.seleccione_hombre_perro_sentados);


        //Lista de imagenes 3 opciones
        List<Integer> ListaImagenes3opciones = new ArrayList<Integer>();
        ListaImagenes3opciones.add(R.drawable.mujer_enojada);
        ListaImagenes3opciones.add(R.drawable.mujer_cocinando);
        ListaImagenes3opciones.add(R.drawable.mujer_gritando);

        ListaImagenes3opciones.add(R.drawable.mujer_cocinando);
        ListaImagenes3opciones.add(R.drawable.mujer_enojada);
        ListaImagenes3opciones.add(R.drawable.mujer_gritando);

        ListaImagenes3opciones.add(R.drawable.mujer_gritando);
        ListaImagenes3opciones.add(R.drawable.mujer_cocinando);
        ListaImagenes3opciones.add(R.drawable.mujer_enojada);

        ListaImagenes3opciones.add(R.drawable.pareja_comiendo_pizza);
        ListaImagenes3opciones.add(R.drawable.pareja_jugando);
        ListaImagenes3opciones.add(R.drawable.pareja_caminando);

        ListaImagenes3opciones.add(R.drawable.pareja_caminando);
        ListaImagenes3opciones.add(R.drawable.pareja_comiendo_pizza);
        ListaImagenes3opciones.add(R.drawable.pareja_jugando);

        ListaImagenes3opciones.add(R.drawable.pareja_jugando);
        ListaImagenes3opciones.add(R.drawable.pareja_hamburguesa);
        ListaImagenes3opciones.add(R.drawable.pareja_bailando);

        ListaImagenes3opciones.add(R.drawable.pareja_bailando);
        ListaImagenes3opciones.add(R.drawable.pareja_jugando);
        ListaImagenes3opciones.add(R.drawable.pareja_hamburguesa);

        ListaImagenes3opciones.add(R.drawable.pareja_hamburguesa);
        ListaImagenes3opciones.add(R.drawable.pareja_bailando);
        ListaImagenes3opciones.add(R.drawable.pareja_jugando);

        ListaImagenes3opciones.add(R.drawable.nino_comiendo_verduras);
        ListaImagenes3opciones.add(R.drawable.nina_comiendo_helado);
        ListaImagenes3opciones.add(R.drawable.pareja_comiendo_pizza);

        ListaImagenes3opciones.add(R.drawable.nina_comiendo_helado);
        ListaImagenes3opciones.add(R.drawable.pareja_comiendo_pizza);
        ListaImagenes3opciones.add(R.drawable.nino_comiendo_verduras);

        ListaImagenes3opciones.add(R.drawable.pareja_comiendo_pizza);
        ListaImagenes3opciones.add(R.drawable.nino_comiendo_verduras);
        ListaImagenes3opciones.add(R.drawable.nina_comiendo_helado);

        ListaImagenes3opciones.add(R.drawable.tormenta_electrica);
        ListaImagenes3opciones.add(R.drawable.tormenta_nieve);
        ListaImagenes3opciones.add(R.drawable.dia_soleado);

        ListaImagenes3opciones.add(R.drawable.tormenta_nieve);
        ListaImagenes3opciones.add(R.drawable.dia_soleado);
        ListaImagenes3opciones.add(R.drawable.tormenta_electrica);

        ListaImagenes3opciones.add(R.drawable.hombre_perro_sentados);
        ListaImagenes3opciones.add(R.drawable.hombre_durmiendo);
        ListaImagenes3opciones.add(R.drawable.pareja_abrazados);

        ListaImagenes3opciones.add(R.drawable.pareja_abrazados);
        ListaImagenes3opciones.add(R.drawable.hombre_perro_sentados);
        ListaImagenes3opciones.add(R.drawable.hombre_durmiendo);

        ListaImagenes3opciones.add(R.drawable.hombre_durmiendo);
        ListaImagenes3opciones.add(R.drawable.pareja_abrazados);
        ListaImagenes3opciones.add(R.drawable.hombre_perro_sentados);


        /*
        //Lista de sonidos
        List<Integer> ListaSonidos4opciones = new ArrayList<Integer>();
        ListaSonidos4opciones.add(R.raw.seleccione_pareja_perros);//1
        ListaSonidos4opciones.add(R.raw.seleccione_pareja_pajaros);
        ListaSonidos4opciones.add(R.raw.seleccione_pareja_jugando);
        ListaSonidos4opciones.add(R.raw.seleccione_hombre_perro_chocando_mano);
        ListaSonidos4opciones.add(R.raw.seleccione_nino_comiendo_verduras);//1
        ListaSonidos4opciones.add(R.raw.seleccione_nina_comiendo_helado);
        ListaSonidos4opciones.add(R.raw.seleccione_hombre_perro_chocando_mano);
        ListaSonidos4opciones.add(R.raw.seleccione_perro_caminando);
        ListaSonidos4opciones.add(R.raw.seleccione_arbol);//1
        ListaSonidos4opciones.add(R.raw.seleccione_girasol);
        ListaSonidos4opciones.add(R.raw.seleccione_pasto);
        ListaSonidos4opciones.add(R.raw.seleccione_rosas);
        ListaSonidos4opciones.add(R.raw.seleccione_hombre_mujer_abrazados);//1
        ListaSonidos4opciones.add(R.raw.seleccione_hombre_mujer_bailando);
        ListaSonidos4opciones.add(R.raw.seleccione_hombre_mujer_comiendo);
        ListaSonidos4opciones.add(R.raw.seleccione_hombre_mujer_jugando);
        ListaSonidos4opciones.add(R.raw.seleccione_dia_lluvioso);//1
        ListaSonidos4opciones.add(R.raw.seleccione_dia_soleado);
        ListaSonidos4opciones.add(R.raw.seleccione_tormenta_nieve);
        ListaSonidos4opciones.add(R.raw.seleccione_tormenta_de_rayos);
        ListaSonidos4opciones.add(R.raw.seleccione_perro_caminando);//1
        ListaSonidos4opciones.add(R.raw.seleccione_gato_caminando);
        ListaSonidos4opciones.add(R.raw.seleccione_aves_volando);
        ListaSonidos4opciones.add(R.raw.seleccione_mariposa_volando);


        //Lista de imagenes 4 opciones
        List<Integer> ListaImagenes4opciones = new ArrayList<Integer>();
        ListaImagenes4opciones.add(R.drawable.pareja_perros);
        ListaImagenes4opciones.add(R.drawable.pareja_pajaros);
        ListaImagenes4opciones.add(R.drawable.pareja_jugando);
        ListaImagenes4opciones.add(R.drawable.hombre_perro);//4
        ListaImagenes4opciones.add(R.drawable.nino_comiendo_verduras);//1
        ListaImagenes4opciones.add(R.drawable.nina_comiendo_helado);
        ListaImagenes4opciones.add(R.drawable.hombre_perro);
        ListaImagenes4opciones.add(R.drawable.perro_caminando);
        ListaImagenes4opciones.add(R.drawable.arbol);//1
        ListaImagenes4opciones.add(R.drawable.girasol);
        ListaImagenes4opciones.add(R.drawable.cesped);
        ListaImagenes4opciones.add(R.drawable.rosas);
        ListaImagenes4opciones.add(R.drawable.pareja_abrazados);//1
        ListaImagenes4opciones.add(R.drawable.pareja_bailando);
        ListaImagenes4opciones.add(R.drawable.pareja_hamburguesa);
        ListaImagenes4opciones.add(R.drawable.pareja_jugando);
        ListaImagenes4opciones.add(R.drawable.dia_lluvioso);//1
        ListaImagenes4opciones.add(R.drawable.dia_soleado);
        ListaImagenes4opciones.add(R.drawable.tormenta_nieve);
        ListaImagenes4opciones.add(R.drawable.tormenta_electrica);
        ListaImagenes4opciones.add(R.drawable.perro_caminando);//1
        ListaImagenes4opciones.add(R.drawable.gato_caminando);
        ListaImagenes4opciones.add(R.drawable.aves_volando);
        ListaImagenes4opciones.add(R.drawable.mariposa_volando);
*/

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
        runnable_ordenado = new Runnable() {
            public void run() {

                //Generacion Aleatoria de numeros imagenes y sonidos
                int randomIntListaSonidos0 = (new Random().nextInt(ListaSonidos2opciones.size()));

                //Obtengo de la lista de sonidos, el sonidoFrase con la posicion random generada
                numero_sonido1 = ListaSonidos2opciones.get(randomIntListaSonidos0);

                //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                sonidoInstruccion1 = MediaPlayer.create(getActivity(), numero_sonido1);

                if(i<3){
                    //Generacion Aleatoria de numeros imagenes y sonidos
                    //int randomIntListaSonidos = (new Random().nextInt(ListaSonidos2opciones.size()));
                    randomIntListaSonidos = (new Random().nextInt(ListaSonidos2opciones.size()));

                    //Obtengo de la lista de sonidos, el sonidoFrase con la posicion random generada
                    numero_sonido1 = ListaSonidos2opciones.get(randomIntListaSonidos);

                    //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                    sonidoInstruccion1 = MediaPlayer.create(getActivity(), numero_sonido1);


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

                }else if(i>=3&&i<7){
                    //Generacion Aleatoria de numeros imagenes y sonidos
                    //int randomIntListaSonidos = (new Random().nextInt(ListaSonidos3opciones.size()));
                    randomIntListaSonidos = (new Random().nextInt(ListaSonidos3opciones.size()));

                    do {
                        randomIntListaSonidos = (new Random().nextInt(ListaSonidos3opciones.size()));
                    }while (randomIntListaSonidos % 3 != 0);

                    //Obtengo de la lista de sonidos, el sonidoFrase con la posicion random generada
                    numero_sonido1 = ListaSonidos3opciones.get(randomIntListaSonidos);
                    numero_sonido2 = ListaSonidos3opciones.get(randomIntListaSonidos+1);

                    //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                    sonidoInstruccion1 = MediaPlayer.create(getActivity(), numero_sonido1);
                    sonidoInstruccion2 = MediaPlayer.create(getActivity(), numero_sonido2);

                    //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                    numero_imagenRespuesta1 = ListaImagenes3opciones.get(randomIntListaSonidos);
                    numero_imagenRespuesta2 = ListaImagenes3opciones.get(randomIntListaSonidos+1);
                    numero_imagenIncorrecta = ListaImagenes3opciones.get(randomIntListaSonidos+2);

                    /*
                    //Para generar los sonidos de forma aleatora y asi dejar sonioinstruccion iguales
                    Random rand = new Random();
                    int randomSonidos= rand.nextInt(2);//Numero random entre 0 1
                    if(randomSonidos==0){
                        //Obtengo de la lista de sonidos, el sonidoFrase con la posicion random generada
                        numero_sonido1 = ListaSonidos3opciones.get(randomIntListaSonidos);
                        numero_sonido2 = ListaSonidos3opciones.get(randomIntListaSonidos+1);

                        //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                        sonidoInstruccion1 = MediaPlayer.create(getActivity(), numero_sonido1);
                        sonidoInstruccion2 = MediaPlayer.create(getActivity(), numero_sonido2);

                        //Generacion Aleatoria de numeros imagenes y sonidos
                        int randomIntlistaImagenes0 = randomIntListaSonidos*(3);
                        int randomIntlistaImagenes = randomIntlistaImagenes0/2;

                        //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                        numero_imagenRespuesta1 = ListaImagenes3opciones.get(randomIntListaSonidos);
                        numero_imagenRespuesta2 = ListaImagenes3opciones.get(randomIntListaSonidos+1);
                        numero_imagenIncorrecta = ListaImagenes3opciones.get(randomIntListaSonidos+2);
                    }else{
                        //Obtengo de la lista de sonidos, el sonidoFrase con la posicion random generada
                        numero_sonido1 = ListaSonidos3opciones.get(randomIntListaSonidos);
                        numero_sonido2 = ListaSonidos3opciones.get(randomIntListaSonidos+1);

                        //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                        sonidoInstruccion1 = MediaPlayer.create(getActivity(), numero_sonido1);
                        sonidoInstruccion2 = MediaPlayer.create(getActivity(), numero_sonido2);

                        //Generacion Aleatoria de numeros imagenes y sonidos
                        int randomIntlistaImagenes0 = randomIntListaSonidos*(3);
                        int randomIntlistaImagenes = randomIntlistaImagenes0/2;

                        //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                        numero_imagenRespuesta1 = ListaImagenes3opciones.get(randomIntListaSonidos);
                        numero_imagenRespuesta2 = ListaImagenes3opciones.get(randomIntListaSonidos+1);
                        numero_imagenIncorrecta = ListaImagenes3opciones.get(randomIntListaSonidos+2);
                    }*/

                }
                /*
                else if(i>=5&&i<8){//va hasta 8 son 17 sonidos
                    //Generacion Aleatoria de numeros imagenes y sonidos
                    //int randomIntListaSonidos = (new Random().nextInt(ListaSonidos4opciones.size()));
                    randomIntListaSonidos = (new Random().nextInt(ListaSonidos4opciones.size()));

                    while(randomIntListaSonidos==randomIntListaSonidos_anterior){
                        randomIntListaSonidos = (new Random().nextInt(ListaSonidos4opciones.size()));
                    }

                    while (randomIntListaSonidos % 3 != 0) {
                        randomIntListaSonidos = (new Random().nextInt(ListaSonidos4opciones.size()));
                    }

                    //Obtengo de la lista de sonidos, el sonidoFrase con la posicion random generada
                    numero_sonido1 = ListaSonidos4opciones.get(randomIntListaSonidos);
                    numero_sonido2 = ListaSonidos4opciones.get(randomIntListaSonidos+1);
                    numero_sonido3 = ListaSonidos4opciones.get(randomIntListaSonidos+2);

                    //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                    sonidoInstruccion1 = MediaPlayer.create(getActivity(), numero_sonido1);
                    sonidoInstruccion2 = MediaPlayer.create(getActivity(), numero_sonido2);
                    sonidoInstruccion3 = MediaPlayer.create(getActivity(), numero_sonido3);

                    //Generacion Aleatoria de numeros imagenes y sonidos
                    //int randomIntlistaImagenes = (new Random().nextInt(ListaImagenes.size()));
                    int randomIntlistaImagenes = randomIntListaSonidos*3;

                    numero_imagenRespuesta1 = ListaImagenes4opciones.get(randomIntlistaImagenes);
                    numero_imagenRespuesta2 = ListaImagenes4opciones.get(randomIntlistaImagenes+1);
                    numero_imagenRespuesta3 = ListaImagenes4opciones.get(randomIntlistaImagenes+2);
                    numero_imagenIncorrecta = ListaImagenes4opciones.get(randomIntlistaImagenes+3);
                }*/

                randomIntListaSonidos_anterior= randomIntListaSonidos;



                //para el textview aciertos no salga
                textViewAciertos.setVisibility(View.INVISIBLE);
                //para el textview seleeccione / correcto no salga
                textViewSeleccione.setVisibility(View.INVISIBLE);
                //para que salga el texto "escuche los sonidos"
                textViewEscuche.setVisibility(View.VISIBLE);

                //para ocultar las imagenes 1 2 y 3 nuevamente
                imagen1_2opciones.setVisibility(View.INVISIBLE);
                imagen2_2opciones.setVisibility(View.INVISIBLE);

                imagen1_3opciones.setVisibility(View.INVISIBLE);
                imagen2_3opciones.setVisibility(View.INVISIBLE);
                imagen3_3opciones.setVisibility(View.INVISIBLE);

                imagen1_4opciones.setVisibility(View.INVISIBLE);
                imagen2_4opciones.setVisibility(View.INVISIBLE);
                imagen3_4opciones.setVisibility(View.INVISIBLE);
                imagen4_4opciones.setVisibility(View.INVISIBLE);



                if(i<3){//estaba antes de i<2

                    //para activar nuevamente la opcion clickeable de las imagenes
                    imagen1_2opciones.setClickable(true);
                    imagen2_2opciones.setClickable(true);

                    reproducirSonidoInstruccion_2opciones();

                }else if(i>=3&&i<7){// estaba antes i>=2&&i<6

                    //para activar nuevamente la opcion clickeable de las imagenes
                    imagen1_3opciones.setClickable(true);
                    imagen2_3opciones.setClickable(true);
                    imagen3_3opciones.setClickable(true);

                    reproducirSonidoInstruccion_3opciones();
                    intentos =0;

                }/*else if(i>=5&&i<8){//va hasta 13

                    //para activar nuevamente la opcion clickeable de las imagenes
                    imagen1_4opciones.setClickable(true);
                    imagen2_4opciones.setClickable(true);
                    imagen3_4opciones.setClickable(true);
                    imagen4_4opciones.setClickable(true);

                    reproducirSonidoInstruccion_2opciones();
                }*/


                //Retardo
                //global_retardo = (int) (Math.random() * 13000) + 11000;//genera retardo entre 3 y 5 segs
                global_retardo = 200000;

                if (i >= 6) { // just remove call backs//
                    //para parar el sonidoFrase correctamente
                    if(sonidoInstruccion1 != null) {
                        sonidoInstruccion1.stop();
                        sonidoInstruccion1.release();
                        sonidoInstruccion1 = null;
                    }


                    //para ocultar las imagenes 1 2 y 3 nuevamente
                    imagen1_2opciones.setVisibility(View.INVISIBLE);
                    imagen2_2opciones.setVisibility(View.INVISIBLE);

                    imagen1_3opciones.setVisibility(View.INVISIBLE);
                    imagen2_3opciones.setVisibility(View.INVISIBLE);
                    imagen3_3opciones.setVisibility(View.INVISIBLE);

                    imagen1_4opciones.setVisibility(View.INVISIBLE);
                    imagen2_4opciones.setVisibility(View.INVISIBLE);
                    imagen3_4opciones.setVisibility(View.INVISIBLE);
                    imagen4_4opciones.setVisibility(View.INVISIBLE);

                    animacionSonido.setVisibility(View.INVISIBLE);
                    textViewEscuche.setVisibility(View.INVISIBLE);

                    iniciar.setVisibility(View.INVISIBLE);
                    parar.setVisibility(View.INVISIBLE);
                    textViewAciertos.setVisibility(View.INVISIBLE);
                    handler.removeCallbacks(this);


                    if (aciertos_actividad >=7) {

                        //guardar puntaje en BD
                        guardarPuntajeBD();

                        //Guardar puntaje en sharedpreferences
                        guardarPreferenciasPuntaje();

                        //pasar a otro fragment
                        //navControllermio.navigate(R.id.actividad_conc_3_felicitacionFragment);
                        navControllermio.navigate(R.id.action_actividad_comp_4Fragment_to_actividad_comp_4_felicitacionFragment);//pasar a otro fragment con actions

                    }
                    else{

                        navControllermio.navigate(R.id.action_actividad_comp_4Fragment_to_actividad_comp_4_erradoFragment);//pasar a otro fragment con actions

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
                handler.removeCallbacks(runnable_ordenado);
                handler.postDelayed(runnable_ordenado, retardo);
            }
        });

//FUNCION PARA EL BOTON PARAR
        parar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(sonidoInstruccion1 != null) {
                    sonidoInstruccion1.stop();
                    sonidoInstruccion1.release();
                    sonidoInstruccion1 = null;
                }
                if(sonidoInstruccion2 != null) {
                    sonidoInstruccion2.stop();
                    sonidoInstruccion2.release();
                    sonidoInstruccion2 = null;
                }


                //para ocultar las imagenes 1 2 y 3 nuevamente
                imagen1_2opciones.setVisibility(View.INVISIBLE);
                imagen2_2opciones.setVisibility(View.INVISIBLE);

                imagen1_3opciones.setVisibility(View.INVISIBLE);
                imagen2_3opciones.setVisibility(View.INVISIBLE);
                imagen3_3opciones.setVisibility(View.INVISIBLE);

                imagen1_4opciones.setVisibility(View.INVISIBLE);
                imagen2_4opciones.setVisibility(View.INVISIBLE);
                imagen3_4opciones.setVisibility(View.INVISIBLE);
                imagen4_4opciones.setVisibility(View.INVISIBLE);

                animacionSonido.setVisibility(View.INVISIBLE);


                parar.setVisibility(View.INVISIBLE);
                iniciar.setVisibility(View.VISIBLE);
                //ayudas.setVisibility(View.VISIBLE);
                instrucciones.setVisibility(View.VISIBLE);

                textViewEscuche.setVisibility(View.INVISIBLE);
                textViewSeleccione.setVisibility(View.INVISIBLE);
                textViewAciertos.setVisibility(View.INVISIBLE);
                textViewFallaste.setVisibility(View.INVISIBLE);

                handler.removeCallbacks(runnable_ordenado);

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
                navControllermio.navigate(R.id.action_actividad_comp_4Fragment_to_actividad_comp_4_instruccionesFragment); //pasar a otro fragment con actions

                handler.removeCallbacks(runnable_ordenado);

            }
        });



//funciones on clicklistener para las imagenes

        imagen1_2opciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seleccionarRespuesta1_2opciones();

            }
        });
        imagen2_2opciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seleccionarRespuesta2_2opciones();

            }
        });

        imagen1_3opciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(intentos<1){
                    seleccionarRespuesta1_Intento1_3opciones();
                }else {
                    seleccionarRespuesta1_Intento2_3opciones();
                }


            }
        });

        imagen2_3opciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intentos<1){
                    seleccionarRespuesta2_Intento1_3opciones();
                }else {
                    seleccionarRespuesta2_Intento2_3opciones();
                }
            }
        });
        imagen3_3opciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intentos<1){
                    seleccionarRespuesta3_Intento1_3opciones();
                }else {
                    seleccionarRespuesta3_Intento2_3opciones();
                }
            }
        });
/*
        imagen1_4opciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarRespuesta1_4opciones();
            }
        });

        imagen2_4opciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarRespuesta2_4opciones();
            }
        });
        imagen3_4opciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarRespuesta3_4opciones();
            }
        });
        imagen4_4opciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarRespuesta4_4opciones();
            }
        });
*/


    }//onviewcreated




//MÉTODOS UTILIZADOS

    private void iniciarRunnable(){
        //Iniciar runnable dependiendo las ayudas seleccionadas
        //global_retardo = (int) (Math.random() * 4000) + 2000; //genera retardo aleatoriamente entre un rango 1 - n
        //cargarPreferenciasAyudas();
        int retardo = 5000;

        handler.removeCallbacks(runnable_ordenado);
        handler.postDelayed(runnable_ordenado, retardo);

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
        int actividadTXT = 4;
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

    private void reproducirSonidoInstruccion_2opciones(){
        //Reproducir sonido instruccion

        sonidoInstruccion1.start();
        mostrarImagenesAleatorio_inicial_2opciones();

        //animacionSonido.setVisibility(View.VISIBLE);

        //una vez acabado el sonidoFrase muestra las imagenes
        sonidoInstruccion1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidoInstruccion1.stop();
                sonidoInstruccion1.release();
                sonidoInstruccion1 = null;

                //animacionSonido.setVisibility(View.INVISIBLE);

                mostrarImagenesAleatorio_2opciones();
                // play next audio file
            }
        });
    }

    private void reproducirSonidoInstruccion_3opciones(){
        //Reproducir sonido instruccion
        sonidoInstruccion1.start();
        mostrarImagenesAleatorio_inicial_3opciones();
        //animacionSonido.setVisibility(View.VISIBLE);


        //una vez acabado el sonidoFrase muestra las imagenes
        sonidoInstruccion1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidoInstruccion1.stop();
                sonidoInstruccion1.release();
                sonidoInstruccion1 = null;

                //animacionSonido.setVisibility(View.INVISIBLE);

                mostrarImagenesAleatorio_3opciones();
                // play next audio file
            }
        });
    }


    private void reproducirSonidoInstruccion_4opciones(){
        //Reproducir sonido instruccion

        sonidoInstruccion1.start();

        animacionSonido.setVisibility(View.VISIBLE);

        //una vez acabado el sonidoFrase muestra las imagenes
        sonidoInstruccion1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidoInstruccion1.stop();
                sonidoInstruccion1.release();
                sonidoInstruccion1 = null;

                animacionSonido.setVisibility(View.INVISIBLE);

                mostrarImagenesAleatorio_2opciones();
                // play next audio file
            }
        });
    }




//2 OPCIONES
private void mostrarImagenesAleatorio_inicial_2opciones() {
    //set textview "selecione imagen"


    imagen1_2opciones.setBackgroundResource(R.drawable.estilo_imagen_blanco);
    imagen2_2opciones.setBackgroundResource(R.drawable.estilo_imagen_blanco);

    imagen1_2opciones.setVisibility(View.VISIBLE);
    imagen2_2opciones.setVisibility(View.VISIBLE);

    imagen1_2opciones.setClickable(false);
    imagen2_2opciones.setClickable(false);

    textViewSeleccione.setVisibility(View.INVISIBLE);
    textViewEscuche.setVisibility(View.INVISIBLE);
    animacionSonido.setVisibility(View.INVISIBLE);

    Random rand = new Random();
    //int randomImagenes= rand.nextInt(2);
    randomImagenes = rand.nextInt(2);//numero aleatorio entre 0 y 1
    switch (randomImagenes){
        case 0:
            imagen1_2opciones.setImageResource(numero_imagen1); //imagen1 = imagencorto
            imagen2_2opciones.setImageResource(numero_imagen2); //imagen2 = imagenlargo
            break;
        case 1:
            imagen1_2opciones.setImageResource(numero_imagen2); //imagen1 = imagencorto
            imagen2_2opciones.setImageResource(numero_imagen1); //imagen2 = imagenlargo
            break;
    }
}

    private void mostrarImagenesAleatorio_2opciones() {
        //set textview "selecione imagen"


        imagen1_2opciones.setBackgroundResource(R.drawable.estilo_imagen_blanco);
        imagen2_2opciones.setBackgroundResource(R.drawable.estilo_imagen_blanco);

        imagen1_2opciones.setVisibility(View.VISIBLE);
        imagen2_2opciones.setVisibility(View.VISIBLE);

        imagen1_2opciones.setClickable(true);
        imagen2_2opciones.setClickable(true);

        textViewSeleccione.setText("Seleccione una imagen");
        textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_azul);
        textViewSeleccione.setVisibility(View.VISIBLE);
        textViewEscuche.setVisibility(View.INVISIBLE);
        animacionSonido.setVisibility(View.INVISIBLE);

        //Random rand = new Random();
        //int randomImagenes= rand.nextInt(2);
        //randomImagenes = rand.nextInt(2);//numero aleatorio entre 0 y 1
        switch (randomImagenes){
            case 0:
                imagen1_2opciones.setImageResource(numero_imagen1); //imagen1 = imagencorto
                imagen2_2opciones.setImageResource(numero_imagen2); //imagen2 = imagenlargo
                break;
            case 1:
                imagen1_2opciones.setImageResource(numero_imagen2); //imagen1 = imagencorto
                imagen2_2opciones.setImageResource(numero_imagen1); //imagen2 = imagenlargo
                break;
        }
    }

    private void seleccionarRespuesta1_2opciones() {
        if (randomImagenes == 0) {//0 muestra la imagen de la respuesta en imageview1
            //Acierta
            imagen1_2opciones.setImageResource(R.drawable.check); //imagen1 = imagencorto
            imagen1_2opciones.setBackgroundResource(R.drawable.estilo_imagen_verde);
            imagen1_2opciones.setClickable(false);

            imagen2_2opciones.setVisibility(View.INVISIBLE);


            //puntaje
            aciertos_actividad = aciertos_actividad + 1;
            mostrarTextviewAciertos_2opciones();
            //pasar al siguiente sonido
            iniciarRunnable();

        } else{//se equivoca
            imagen1_2opciones.setImageResource(R.drawable.errado); //imagen1 = imagencorto
            imagen1_2opciones.setBackgroundResource(R.drawable.estilo_imagen_rojo);
            imagen1_2opciones.setClickable(false);

            imagen2_2opciones.setVisibility(View.INVISIBLE);
            mostrarTextviewFallaste_2opciones();
            //pasar al siguiente sonido
            iniciarRunnable();

        }
    }

    private void seleccionarRespuesta2_2opciones() {
        if (randomImagenes == 1) {//0 muestra la imagen de la respuesta en imageview2
            //Acierta
            imagen2_2opciones.setImageResource(R.drawable.check); //imagen2 = imagenREspuesta
            imagen2_2opciones.setBackgroundResource(R.drawable.estilo_imagen_verde);
            imagen2_2opciones.setClickable(false);

            imagen1_2opciones.setVisibility(View.INVISIBLE);

            //puntaje
            aciertos_actividad = aciertos_actividad + 1;
            mostrarTextviewAciertos_2opciones();
            //pasar al siguiente sonido
            iniciarRunnable();

        } else{//se equivoca
            imagen2_2opciones.setImageResource(R.drawable.errado); //
            imagen2_2opciones.setBackgroundResource(R.drawable.estilo_imagen_rojo);
            imagen2_2opciones.setClickable(false);

            imagen1_2opciones.setVisibility(View.INVISIBLE);

            mostrarTextviewFallaste_2opciones();

            //pasar al siguiente sonido
            iniciarRunnable();

        }
    }

    private void mostrarTextviewAciertos_2opciones(){
        textViewEscuche.setVisibility(View.INVISIBLE);

        //handler para que muestre texview aciertos 3 segundos y se desaparezca
        textViewAciertos.setVisibility(View.VISIBLE);
        textViewAciertos.setText("¡Muy bien! +" + aciertos_actividad);
        //para mostrar el textview de abajo (textviewselecione)
        textViewSeleccione.setVisibility(View.VISIBLE);//setClickable(false);
        textViewSeleccione.setText(R.string.Felicitaciones);
        textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_verde);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewAciertos.setVisibility(View.INVISIBLE);// do your operation here
                textViewSeleccione.setVisibility(View.INVISIBLE);// do your operation here
                //para ocultar las imagenes 1 2 y 3 nuevamente
                imagen1_2opciones.setVisibility(View.INVISIBLE);
                imagen2_2opciones.setVisibility(View.INVISIBLE);
            }
        }, 3000);
    }

    private void mostrarTextviewFallaste_2opciones(){
        textViewEscuche.setVisibility(View.INVISIBLE);

        //handler para que muestre la imagen 1 segundo y se desaparezca
        textViewFallaste.setVisibility(View.VISIBLE);
        textViewFallaste.setText(R.string.Fallaste);
        //para mostrar el textview de abajo (textviewselecione)
        textViewSeleccione.setVisibility(View.VISIBLE);//setClickable(false);
        textViewSeleccione.setText(R.string.Incorrecto);
        textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_rojo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewFallaste.setVisibility(View.INVISIBLE);// do your operation here
                textViewSeleccione.setVisibility(View.INVISIBLE);// do your operation here
                //para ocultar las imagenes 1 2 y 3 nuevamente
                imagen1_2opciones.setVisibility(View.INVISIBLE);
                imagen2_2opciones.setVisibility(View.INVISIBLE);
            }
        }, 3000);
    }


//3 OPCIONES
private void mostrarImagenesAleatorio_inicial_3opciones() {
    //set textview "selecione imagen"


    imagen1_3opciones.setBackgroundResource(R.drawable.estilo_imagen_blanco);
    imagen2_3opciones.setBackgroundResource(R.drawable.estilo_imagen_blanco);
    imagen3_3opciones.setBackgroundResource(R.drawable.estilo_imagen_blanco);

    imagen1_3opciones.setVisibility(View.VISIBLE);
    imagen2_3opciones.setVisibility(View.VISIBLE);
    imagen3_3opciones.setVisibility(View.VISIBLE);

    imagen1_3opciones.setClickable(false);
    imagen2_3opciones.setClickable(false);
    imagen3_3opciones.setClickable(false);



    textViewSeleccione.setVisibility(View.INVISIBLE);
    textViewEscuche.setVisibility(View.INVISIBLE);

    animacionSonido.setVisibility(View.INVISIBLE);

    Random rand = new Random();
    //int randomImagenes= rand.nextInt(2);
    randomImagenes = rand.nextInt(3);//numero aleatorio entre 0 y 3
    switch (randomImagenes){
        case 0:
            imagen1_3opciones.setImageResource(numero_imagenRespuesta1); //imagen1 = imagencorto
            imagen2_3opciones.setImageResource(numero_imagenRespuesta2); //imagen2 = imagenlargo
            imagen3_3opciones.setImageResource(numero_imagenIncorrecta); //imagen2 = imagenlargo
            break;
        case 1:
            imagen1_3opciones.setImageResource(numero_imagenIncorrecta); //imagen1 = imagencorto
            imagen2_3opciones.setImageResource(numero_imagenRespuesta1); //imagen2 = imagenlargo
            imagen3_3opciones.setImageResource(numero_imagenRespuesta2); //imagen2 = imagenlargo
            break;
        case 2:
            imagen1_3opciones.setImageResource(numero_imagenRespuesta2); //imagen1 = imagencorto
            imagen2_3opciones.setImageResource(numero_imagenIncorrecta); //imagen2 = imagenlargo
            imagen3_3opciones.setImageResource(numero_imagenRespuesta1); //imagen2 = imagenlargo
            break;
    }
}

    private void mostrarImagenesAleatorio_3opciones() {
        //set textview "selecione imagen"


        imagen1_3opciones.setBackgroundResource(R.drawable.estilo_imagen_blanco);
        imagen2_3opciones.setBackgroundResource(R.drawable.estilo_imagen_blanco);
        imagen3_3opciones.setBackgroundResource(R.drawable.estilo_imagen_blanco);

        imagen1_3opciones.setVisibility(View.VISIBLE);
        imagen2_3opciones.setVisibility(View.VISIBLE);
        imagen3_3opciones.setVisibility(View.VISIBLE);

        imagen1_3opciones.setClickable(true);
        imagen2_3opciones.setClickable(true);
        imagen3_3opciones.setClickable(true);

        textViewSeleccione.setText(R.string.Seleccione_una_imagen);
        textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_azul);
        textViewSeleccione.setVisibility(View.VISIBLE);
        textViewEscuche.setVisibility(View.INVISIBLE);
        animacionSonido.setVisibility(View.INVISIBLE);

        //Random rand = new Random();
        //int randomImagenes= rand.nextInt(2);
        //randomImagenes = rand.nextInt(3);//numero aleatorio entre 0 y 3
        switch (randomImagenes){
            case 0:
                imagen1_3opciones.setImageResource(numero_imagenRespuesta1); //imagen1 = imagencorto
                imagen2_3opciones.setImageResource(numero_imagenRespuesta2); //imagen2 = imagenlargo
                imagen3_3opciones.setImageResource(numero_imagenIncorrecta); //imagen2 = imagenlargo
                break;
            case 1:
                imagen1_3opciones.setImageResource(numero_imagenIncorrecta); //imagen1 = imagencorto
                imagen2_3opciones.setImageResource(numero_imagenRespuesta1); //imagen2 = imagenlargo
                imagen3_3opciones.setImageResource(numero_imagenRespuesta2); //imagen2 = imagenlargo
                break;
            case 2:
                imagen1_3opciones.setImageResource(numero_imagenRespuesta2); //imagen1 = imagencorto
                imagen2_3opciones.setImageResource(numero_imagenIncorrecta); //imagen2 = imagenlargo
                imagen3_3opciones.setImageResource(numero_imagenRespuesta1); //imagen2 = imagenlargo
                break;
        }
    }

    private void seleccionarRespuesta1_Intento1_3opciones() {
        if (randomImagenes == 0) {//0 muestra la imagen de la respuesta1 en imageview1
            //Acierta

            imagen1_3opciones.setImageResource(R.drawable.check); //imagen1 = imagencorto
            imagen1_3opciones.setBackgroundResource(R.drawable.estilo_imagen_verde);
            imagen1_3opciones.setClickable(false);

            imagen2_3opciones.setVisibility(View.INVISIBLE);
            imagen3_3opciones.setVisibility(View.INVISIBLE);

            //puntaje
            aciertos_actividad = aciertos_actividad + 1;
            mostrarTextviewAciertos_3opciones();

            //pasar al siguiente sonido
            intentos = intentos +1;

            if (intentos<2){

                //si el intento es 1 pasa al siguiente sonido despues de
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reproducirSonido_Intento2_3opciones();
                    }
                }, 5000);
            }else {
                iniciarRunnable();
            }


        } else{//se equivoca
            imagen1_3opciones.setImageResource(R.drawable.errado); //imagen1 = imagencorto
            imagen1_3opciones.setBackgroundResource(R.drawable.estilo_imagen_rojo);
            imagen1_3opciones.setClickable(false);

            imagen2_3opciones.setVisibility(View.INVISIBLE);
            imagen3_3opciones.setVisibility(View.INVISIBLE);

            mostrarTextviewFallaste_3opciones();

            //pasar al siguiente sonido
            //iniciarRunnable();
            //pasar al siguiente sonido
            intentos = intentos +1;

            if (intentos<2){

                //si el intento es 1 pasa al siguiente sonido despues de
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reproducirSonido_Intento2_3opciones();
                    }
                }, 5000);
            }else {
                iniciarRunnable();
            }

        }
    }

    private void seleccionarRespuesta2_Intento1_3opciones() {
        if (randomImagenes == 1) {//1 muestra la imagen de la respuesta1 en imageview2
            //Acierta
            imagen2_3opciones.setImageResource(R.drawable.check); //imagen2 = imagenREspuesta
            imagen2_3opciones.setBackgroundResource(R.drawable.estilo_imagen_verde);
            imagen2_3opciones.setClickable(false);

            imagen1_3opciones.setVisibility(View.INVISIBLE);
            imagen3_3opciones.setVisibility(View.INVISIBLE);

            //puntaje
            aciertos_actividad = aciertos_actividad + 1;
            mostrarTextviewAciertos_3opciones();
            //pasar al siguiente sonido
            //iniciarRunnable();
            //pasar al siguiente sonido
            intentos = intentos +1;

            if (intentos<2){

                //si el intento es 1 pasa al siguiente sonido despues de
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reproducirSonido_Intento2_3opciones();
                    }
                }, 5000);
            }else {
                iniciarRunnable();
            }

        } else{//se equivoca
            imagen2_3opciones.setImageResource(R.drawable.errado); //
            imagen2_3opciones.setBackgroundResource(R.drawable.estilo_imagen_rojo);
            imagen2_3opciones.setClickable(false);

            imagen1_3opciones.setVisibility(View.INVISIBLE);
            imagen3_3opciones.setVisibility(View.INVISIBLE);

            mostrarTextviewFallaste_3opciones();

            //pasar al siguiente sonido
            //iniciarRunnable();
            //pasar al siguiente sonido
            intentos = intentos +1;

            if (intentos<2){

                //si el intento es 1 pasa al siguiente sonido despues de
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reproducirSonido_Intento2_3opciones();
                    }
                }, 5000);
            }else {
                iniciarRunnable();
            }

        }
    }

    private void seleccionarRespuesta3_Intento1_3opciones() {
        if (randomImagenes == 2) {//2 muestra la imagen de la respuesta1 en imageview3
            //Acierta
            imagen3_3opciones.setImageResource(R.drawable.check); //imagen3 = imagenRespuesta
            imagen3_3opciones.setBackgroundResource(R.drawable.estilo_imagen_verde);
            imagen3_3opciones.setClickable(false);

            imagen1_3opciones.setVisibility(View.INVISIBLE);
            imagen2_3opciones.setVisibility(View.INVISIBLE);

            //puntaje
            aciertos_actividad = aciertos_actividad + 1;
            mostrarTextviewAciertos_3opciones();
            //pasar al siguiente sonido
            //iniciarRunnable();
            //pasar al siguiente sonido
            intentos = intentos +1;

            if (intentos<2){

                //si el intento es 1 pasa al siguiente sonido despues de
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reproducirSonido_Intento2_3opciones();
                    }
                }, 5000);
            }else {
                iniciarRunnable();
            }

        } else{//se equivoca
            imagen3_3opciones.setImageResource(R.drawable.errado); //
            imagen3_3opciones.setBackgroundResource(R.drawable.estilo_imagen_rojo);
            imagen3_3opciones.setClickable(false);

            imagen1_3opciones.setVisibility(View.INVISIBLE);
            imagen2_3opciones.setVisibility(View.INVISIBLE);

            mostrarTextviewFallaste_3opciones();

            //pasar al siguiente sonido
            //iniciarRunnable();
            //pasar al siguiente sonido
            intentos = intentos +1;

            if (intentos<2){

                //si el intento es 1 pasa al siguiente sonido despues de
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reproducirSonido_Intento2_3opciones();
                    }
                }, 5000);
            }else {
                iniciarRunnable();
            }

        }
    }

    private void mostrarTextviewAciertos_3opciones(){
        textViewEscuche.setVisibility(View.INVISIBLE);

        //handler para que muestre texview aciertos 3 segundos y se desaparezca
        textViewAciertos.setVisibility(View.VISIBLE);
        textViewAciertos.setText("¡Muy bien! +" + aciertos_actividad);
        //para mostrar el textview de abajo (textviewselecione)
        textViewSeleccione.setVisibility(View.VISIBLE);//setClickable(false);
        textViewSeleccione.setText(R.string.Correcto);
        textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_verde);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewAciertos.setVisibility(View.INVISIBLE);// do your operation here
                textViewSeleccione.setVisibility(View.INVISIBLE);// do your operation here
                //para ocultar las imagenes 1 2 y 3 nuevamente
                imagen1_3opciones.setVisibility(View.INVISIBLE);
                imagen2_3opciones.setVisibility(View.INVISIBLE);
                imagen3_3opciones.setVisibility(View.INVISIBLE);

                //PARA QUE LAS IMAGENES NO SE QUEDEN SELECCIONADAS
                imagen1_3opciones.setClickable(true);
                imagen2_3opciones.setClickable(true);
                imagen3_3opciones.setClickable(true);

                imagen1_3opciones.setBackgroundResource(R.drawable.estilo_imagen_blanco);
                imagen2_3opciones.setBackgroundResource(R.drawable.estilo_imagen_blanco);
                imagen3_3opciones.setBackgroundResource(R.drawable.estilo_imagen_blanco);

                switch (randomImagenes) {
                    case 0:
                        imagen1_3opciones.setImageResource(numero_imagenRespuesta1); //imagen1 = imagencorto
                        imagen2_3opciones.setImageResource(numero_imagenRespuesta2); //imagen2 = imagenlargo
                        imagen3_3opciones.setImageResource(numero_imagenIncorrecta); //imagen2 = imagenlargo
                        break;
                    case 1:
                        imagen1_3opciones.setImageResource(numero_imagenIncorrecta); //imagen1 = imagencorto
                        imagen2_3opciones.setImageResource(numero_imagenRespuesta1); //imagen2 = imagenlargo
                        imagen3_3opciones.setImageResource(numero_imagenRespuesta2); //imagen2 = imagenlargo
                        break;
                    case 2:
                        imagen1_3opciones.setImageResource(numero_imagenRespuesta2); //imagen1 = imagencorto
                        imagen2_3opciones.setImageResource(numero_imagenIncorrecta); //imagen2 = imagenlargo
                        imagen3_3opciones.setImageResource(numero_imagenRespuesta1); //imagen2 = imagenlargo
                        break;
                }
            }
        }, 3000);
    }


    private void mostrarTextviewFallaste_3opciones(){
        textViewEscuche.setVisibility(View.INVISIBLE);

        //handler para que muestre la imagen 1 segundo y se desaparezca
        textViewFallaste.setVisibility(View.VISIBLE);
        textViewFallaste.setText(R.string.Fallaste);
        //para mostrar el textview de abajo (textviewselecione)
        textViewSeleccione.setVisibility(View.VISIBLE);//setClickable(false);
        textViewSeleccione.setText(R.string.Incorrecto);
        textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_rojo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewFallaste.setVisibility(View.INVISIBLE);// do your operation here
                textViewSeleccione.setVisibility(View.INVISIBLE);// do your operation here
                //para ocultar las imagenes 1 2 y 3 nuevamente
                imagen1_3opciones.setVisibility(View.INVISIBLE);
                imagen2_3opciones.setVisibility(View.INVISIBLE);
                imagen3_3opciones.setVisibility(View.INVISIBLE);

                imagen1_3opciones.setClickable(true);
                imagen2_3opciones.setClickable(true);
                imagen3_3opciones.setClickable(true);

                imagen1_3opciones.setBackgroundResource(R.drawable.estilo_imagen_blanco);
                imagen2_3opciones.setBackgroundResource(R.drawable.estilo_imagen_blanco);
                imagen3_3opciones.setBackgroundResource(R.drawable.estilo_imagen_blanco);

                switch (randomImagenes){
                    case 0:
                        imagen1_3opciones.setImageResource(numero_imagenRespuesta1); //imagen1 = imagencorto
                        imagen2_3opciones.setImageResource(numero_imagenRespuesta2); //imagen2 = imagenlargo
                        imagen3_3opciones.setImageResource(numero_imagenIncorrecta); //imagen2 = imagenlargo
                        break;
                    case 1:
                        imagen1_3opciones.setImageResource(numero_imagenIncorrecta); //imagen1 = imagencorto
                        imagen2_3opciones.setImageResource(numero_imagenRespuesta1); //imagen2 = imagenlargo
                        imagen3_3opciones.setImageResource(numero_imagenRespuesta2); //imagen2 = imagenlargo
                        break;
                    case 2:
                        imagen1_3opciones.setImageResource(numero_imagenRespuesta2); //imagen1 = imagencorto
                        imagen2_3opciones.setImageResource(numero_imagenIncorrecta); //imagen2 = imagenlargo
                        imagen3_3opciones.setImageResource(numero_imagenRespuesta1); //imagen2 = imagenlargo
                        break;
                }
            }
        }, 3000);
    }

    private void reproducirSonido_Intento2_3opciones(){
        //Reproducir sonido instruccion
        sonidoInstruccion2.start();

        mostrarImagenes_inicial_Intento2_3opciones();
        //animacionSonido.setVisibility(View.VISIBLE);


        //una vez acabado el sonidoFrase muestra las imagenes
        sonidoInstruccion2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidoInstruccion2.stop();
                sonidoInstruccion2.release();
                sonidoInstruccion2 = null;

                //animacionSonido.setVisibility(View.INVISIBLE);

                mostrarImagenes_Intento2_3opciones();
                // play next audio file
            }
        });
    }

    private void mostrarImagenes_inicial_Intento2_3opciones() {
        //set textview "seleccione imagen"
        imagen1_3opciones.setVisibility(View.VISIBLE);
        imagen2_3opciones.setVisibility(View.VISIBLE);
        imagen3_3opciones.setVisibility(View.VISIBLE);

        imagen1_3opciones.setClickable(false);
        imagen2_3opciones.setClickable(false);
        imagen3_3opciones.setClickable(false);

        textViewSeleccione.setText(R.string.Seleccione_una_imagen);
        textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_azul);
        textViewSeleccione.setVisibility(View.VISIBLE);
        textViewEscuche.setVisibility(View.INVISIBLE);
        animacionSonido.setVisibility(View.INVISIBLE);
    }

    private void mostrarImagenes_Intento2_3opciones() {
        //set textview "seleccione imagen"
        imagen1_3opciones.setVisibility(View.VISIBLE);
        imagen2_3opciones.setVisibility(View.VISIBLE);
        imagen3_3opciones.setVisibility(View.VISIBLE);

        imagen1_3opciones.setClickable(true);
        imagen2_3opciones.setClickable(true);
        imagen3_3opciones.setClickable(true);

        textViewSeleccione.setText(R.string.Seleccione_una_imagen);
        textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_azul);
        textViewSeleccione.setVisibility(View.VISIBLE);
        textViewEscuche.setVisibility(View.INVISIBLE);
        animacionSonido.setVisibility(View.INVISIBLE);
    }

    private void seleccionarRespuesta1_Intento2_3opciones() {
        if (randomImagenes == 2) {//0 muestra la imagen de la respuesta2 en imageview2
            //Acierta

            imagen1_3opciones.setImageResource(R.drawable.check); //imagen1 = imagencorto
            imagen1_3opciones.setBackgroundResource(R.drawable.estilo_imagen_verde);
            imagen1_3opciones.setClickable(false);
            imagen2_3opciones.setClickable(false);
            imagen3_3opciones.setClickable(false);

            //imagen2_3opciones.setVisibility(View.INVISIBLE);
            //imagen3_3opciones.setVisibility(View.INVISIBLE);

            //puntaje
            aciertos_actividad = aciertos_actividad + 1;
            mostrarTextviewAciertos_3opciones();

            //pasar al siguiente sonido
            iniciarRunnable();


        } else{//se equivoca
            imagen1_3opciones.setImageResource(R.drawable.errado); //imagen1 = imagencorto
            imagen1_3opciones.setBackgroundResource(R.drawable.estilo_imagen_rojo);
            imagen1_3opciones.setClickable(false);
            imagen2_3opciones.setClickable(false);
            imagen3_3opciones.setClickable(false);

            //imagen2_3opciones.setVisibility(View.INVISIBLE);
            //imagen3_3opciones.setVisibility(View.INVISIBLE);

            mostrarTextviewFallaste_3opciones();

            //pasar al siguiente sonido
            iniciarRunnable();
        }
    }

    private void seleccionarRespuesta2_Intento2_3opciones() {
        if (randomImagenes == 0) {//1 muestra la imagen de la respuesta en imageview2
            //Acierta
            imagen2_3opciones.setImageResource(R.drawable.check); //imagen2 = imagenREspuesta
            imagen2_3opciones.setBackgroundResource(R.drawable.estilo_imagen_verde);
            imagen2_3opciones.setClickable(false);
            imagen1_3opciones.setClickable(false);
            imagen3_3opciones.setClickable(false);

            //imagen1_3opciones.setVisibility(View.INVISIBLE);
            //imagen3_3opciones.setVisibility(View.INVISIBLE);

            //puntaje
            aciertos_actividad = aciertos_actividad + 1;
            mostrarTextviewAciertos_3opciones();

            //pasar al siguiente sonido
            iniciarRunnable();

        } else{//se equivoca
            imagen2_3opciones.setImageResource(R.drawable.errado); //
            imagen2_3opciones.setBackgroundResource(R.drawable.estilo_imagen_rojo);
            imagen2_3opciones.setClickable(false);
            imagen1_3opciones.setClickable(false);
            imagen3_3opciones.setClickable(false);

           // imagen1_3opciones.setVisibility(View.INVISIBLE);
            //imagen3_3opciones.setVisibility(View.INVISIBLE);

            mostrarTextviewFallaste_3opciones();

            //pasar al siguiente sonido
            iniciarRunnable();

        }
    }

    private void seleccionarRespuesta3_Intento2_3opciones() {
        if (randomImagenes == 1) {//2 muestra la imagen de la respuesta en imageview3
            //Acierta
            imagen3_3opciones.setImageResource(R.drawable.check); //imagen3 = imagenRespuesta
            imagen3_3opciones.setBackgroundResource(R.drawable.estilo_imagen_verde);
            imagen3_3opciones.setClickable(false);
            imagen2_3opciones.setClickable(false);
            imagen1_3opciones.setClickable(false);

            //imagen1_3opciones.setVisibility(View.INVISIBLE);
            //imagen2_3opciones.setVisibility(View.INVISIBLE);

            //puntaje
            aciertos_actividad = aciertos_actividad + 1;
            mostrarTextviewAciertos_3opciones();

            //pasar al siguiente sonido
            iniciarRunnable();

        } else{//se equivoca
            imagen3_3opciones.setImageResource(R.drawable.errado); //
            imagen3_3opciones.setBackgroundResource(R.drawable.estilo_imagen_rojo);
            imagen3_3opciones.setClickable(false);
            imagen2_3opciones.setClickable(false);
            imagen1_3opciones.setClickable(false);

            //imagen1_3opciones.setVisibility(View.INVISIBLE);
            //imagen2_3opciones.setVisibility(View.INVISIBLE);

            mostrarTextviewFallaste_3opciones();

            //pasar al siguiente sonido
            iniciarRunnable();

        }
    }



    //FUNCION ON PAUSE DE LA CLASE
    @Override
    public void onPause() {
        handler.removeCallbacks(runnable_ordenado);


        if(sonidoInstruccion1 != null) {
            sonidoInstruccion1.stop();
            sonidoInstruccion1.release();
            sonidoInstruccion1 = null;
        }

        super.onPause();
    }

}//final
