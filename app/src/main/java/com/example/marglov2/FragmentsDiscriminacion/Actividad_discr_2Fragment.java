package com.example.marglov2.FragmentsDiscriminacion;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marglov2.Entidades.DBHelperPuntajes;
import com.example.marglov2.R;

import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Actividad_discr_2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Actividad_discr_2Fragment extends Fragment {

    //Declaracion de variables
    private ImageView sombrero;
    private Button iniciar, parar, instrucciones;
    private TextView textViewcentro,textView1,textView2, textViewAciertos, textViewInstrucc, textViewPregunta, textViewSeleccione, textViewEscuche, textViewFallaste;
    MediaPlayer sonido, sonidoVocal,sonidoDiptongo, sonidoPalabra1, sonidoPalabra2;
    int i, aciertos_actividad, global_retardo;
    int numero_sonidovocal, numero_sonidoDiptongo, numero_sonidopalabra,numero_sonidopalabra1, numero_sonidopalabra2,numero_textviewVocal,numero_textviewPalabra1,numero_textviewPalabra2, randomPregunta, randomImagenes;

    int randomIntListaPalabras;
    int randomInicial;
    String texto_textviewPalabra1;
    String texto_textviewPalabra2;
    String texto_textviewVocal;
    String texto_textviewDiptongo;
    String textoPalabra;
    String textoSombrero;
    char segundo_caracter;


    String vocal ="";
    String diptongo ="";
    String palabra ="";

    int colorNegro = Color.parseColor("#FF000000");
    int colorAzul = Color.parseColor("#2B308C");

    String infoActividad = "Sin ayudas";

    DBHelperPuntajes DB;

    //forma para el runnable
    private Handler handler, handlerVocalDiptongo,handlerpalabra1,handlerpalabra2;
    private Runnable myRunnable, runnable_iniciar, runnable_ayudas, runnableVocalDiptongo,runnablepalabra1, runnablepalabra2,runnablepalabra3,runnablepalabra4;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Actividad_discr_2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Actividad_discr_2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Actividad_discr_2Fragment newInstance(String param1, String param2) {
        Actividad_discr_2Fragment fragment = new Actividad_discr_2Fragment();
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
        return inflater.inflate(R.layout.fragment_actividad_discr_2, container, false);
    }
    //creacion del onviewcreated recomendado
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        iniciar = view.findViewById(R.id.btn_iniciar);
        parar = view.findViewById(R.id.btn_parar);
        instrucciones = view.findViewById(R.id.btn_instrucciones);

        textViewcentro = view.findViewById(R.id.textView);
        textView1 = view.findViewById(R.id.textView1);
        textView2 = view.findViewById(R.id.textView2);

        textViewAciertos = view.findViewById(R.id.textViewAciertosid);
        textViewFallaste = view.findViewById(R.id.textViewFallasteid);
        textViewInstrucc = view.findViewById(R.id.textviewInstrucc);
        //textViewPregunta = view.findViewById(R.id.textViewPreguntaid);
        textViewSeleccione = view.findViewById(R.id.textviewSeleccioneid);
        textViewEscuche = view.findViewById(R.id.textViewEscucheid);

        sombrero = view.findViewById(R.id.sombrero);


        //Lista de sonidos VOCALES
        List<Integer> ListaSonidosVocales = new ArrayList<>();
        ListaSonidosVocales.add(R.raw.a);
        ListaSonidosVocales.add(R.raw.e);
        ListaSonidosVocales.add(R.raw.i);
        ListaSonidosVocales.add(R.raw.o);
        ListaSonidosVocales.add(R.raw.u);

        //Lista de sonidos DIPTONGOS
        List<Integer> ListaSonidosDiptongos = new ArrayList<>();
        ListaSonidosDiptongos.add(R.raw.ai);
        ListaSonidosDiptongos.add(R.raw.ei);
        ListaSonidosDiptongos.add(R.raw.ia);
        ListaSonidosDiptongos.add(R.raw.ie);
        ListaSonidosDiptongos.add(R.raw.oi);
        ListaSonidosDiptongos.add(R.raw.ue);


        //Lista de sonidos PAREJAS VOCAL A
        List<Integer> ListaSonidosParejasA = new ArrayList<>();
        ListaSonidosParejasA.add(R.raw.pan2);
        ListaSonidosParejasA.add(R.raw.pais);
        ListaSonidosParejasA.add(R.raw.pala);
        ListaSonidosParejasA.add(R.raw.paila);
        ListaSonidosParejasA.add(R.raw.pasaje);
        ListaSonidosParejasA.add(R.raw.paisaje);

        //Lista de sonidos PAREJAS VOCAL E
        List<Integer> ListaSonidosParejasE = new ArrayList<Integer>();
        ListaSonidosParejasE.add(R.raw.ser);
        ListaSonidosParejasE.add(R.raw.seis);
        ListaSonidosParejasE.add(R.raw.ven2);
        ListaSonidosParejasE.add(R.raw.bien);
        ListaSonidosParejasE.add(R.raw.pesa2);
        ListaSonidosParejasE.add(R.raw.pieza);
        ListaSonidosParejasE.add(R.raw.fe);
        ListaSonidosParejasE.add(R.raw.feria);
        ListaSonidosParejasE.add(R.raw.ten);
        ListaSonidosParejasE.add(R.raw.tenia);

        //Lista de sonidos PAREJAS VOCAL I
        List<Integer> ListaSonidosParejasI = new ArrayList<Integer>();
        ListaSonidosParejasI.add(R.raw.mil);
        ListaSonidosParejasI.add(R.raw.miel);
        ListaSonidosParejasI.add(R.raw.billar);
        ListaSonidosParejasI.add(R.raw.bailar);

        //Lista de sonidos PAREJAS VOCAL O
        List<Integer> ListaSonidosParejasO = new ArrayList<Integer>();
        ListaSonidosParejasO.add(R.raw.dos);
        ListaSonidosParejasO.add(R.raw.doy);
        ListaSonidosParejasO.add(R.raw.rosa);
        ListaSonidosParejasO.add(R.raw.raiz);

        //Lista de sonidos PAREJAS VOCAL U
        List<Integer> ListaSonidosParejasU = new ArrayList<Integer>();
        ListaSonidosParejasU.add(R.raw.nube);
        ListaSonidosParejasU.add(R.raw.nueve);
        ListaSonidosParejasU.add(R.raw.amuleto);
        ListaSonidosParejasU.add(R.raw.almuerzo);


        //Lista de sonidos PAREJAS DIPTONGO AI
        List<Integer> ListaSonidosParejasAI = new ArrayList<Integer>();
        ListaSonidosParejasAI.add(R.raw.pais);
        ListaSonidosParejasAI.add(R.raw.pan2);
        ListaSonidosParejasAI.add(R.raw.paila);
        ListaSonidosParejasAI.add(R.raw.pala);
        ListaSonidosParejasAI.add(R.raw.paisaje);
        ListaSonidosParejasAI.add(R.raw.pasaje);
        ListaSonidosParejasAI.add(R.raw.bailar);
        ListaSonidosParejasAI.add(R.raw.billar);
        ListaSonidosParejasAI.add(R.raw.raiz);
        ListaSonidosParejasAI.add(R.raw.rosa);

        //Lista de sonidos PAREJAS DIPTONGO EI
        List<Integer> ListaSonidosParejasEI = new ArrayList<Integer>();
        ListaSonidosParejasEI.add(R.raw.seis);
        ListaSonidosParejasEI.add(R.raw.ser);

        //Lista de sonidos PAREJAS DIPTONGO IA
        List<Integer> ListaSonidosParejasIA = new ArrayList<Integer>();
        ListaSonidosParejasIA.add(R.raw.tenia);
        ListaSonidosParejasIA.add(R.raw.ten);
        ListaSonidosParejasIA.add(R.raw.feria);
        ListaSonidosParejasIA.add(R.raw.fe);

        //Lista de sonidos PAREJAS DIPTONGO IE
        List<Integer> ListaSonidosParejasIE = new ArrayList<Integer>();
        ListaSonidosParejasIE.add(R.raw.bien);
        ListaSonidosParejasIE.add(R.raw.ven2);
        ListaSonidosParejasIE.add(R.raw.pieza);
        ListaSonidosParejasIE.add(R.raw.pesa2);
        ListaSonidosParejasIE.add(R.raw.miel);
        ListaSonidosParejasIE.add(R.raw.mil);

        //Lista de sonidos PAREJAS DIPTONGO OI
        List<Integer> ListaSonidosParejasOI = new ArrayList<Integer>();
        ListaSonidosParejasOI.add(R.raw.doy);
        ListaSonidosParejasOI.add(R.raw.dos);

        //Lista de sonidos PAREJAS DIPTONGO UE
        List<Integer> ListaSonidosParejasUE = new ArrayList<Integer>();
        ListaSonidosParejasUE.add(R.raw.nueve);
        ListaSonidosParejasUE.add(R.raw.nube);
        ListaSonidosParejasUE.add(R.raw.almuerzo);
        ListaSonidosParejasUE.add(R.raw.amuleto);


        //Lista de vocales textviews VOCALES
        List<String> ListaTextviewsVocales = new ArrayList<>();
        ListaTextviewsVocales.add(vocal = "a");
        ListaTextviewsVocales.add(vocal = "e");
        ListaTextviewsVocales.add(vocal = "i");
        ListaTextviewsVocales.add(vocal = "o");
        ListaTextviewsVocales.add(vocal = "u");

        //Lista de vocales textviews VOCALES
        List<String> ListaTextviewsDiptongos = new ArrayList<>();
        ListaTextviewsDiptongos.add(diptongo = "ai");
        ListaTextviewsDiptongos.add(diptongo = "ei");
        ListaTextviewsDiptongos.add(diptongo = "ia");
        ListaTextviewsDiptongos.add(diptongo = "ie");
        ListaTextviewsDiptongos.add(diptongo = "oi");
        ListaTextviewsDiptongos.add(diptongo = "ue");



        //Lista de palabras textviews PAREJAS VOCAL A
        List<String> ListaTextviewsParejasA = new ArrayList<>();
        ListaTextviewsParejasA.add(palabra = "pan");
        ListaTextviewsParejasA.add(palabra = "país");
        ListaTextviewsParejasA.add(palabra = "pala");
        ListaTextviewsParejasA.add(palabra = "paila");
        ListaTextviewsParejasA.add(palabra = "pasaje");
        ListaTextviewsParejasA.add(palabra = "paisaje");

        //Lista de palabras textviews PAREJAS VOCAL E
        List<String> ListaTextviewsParejasE = new ArrayList<>();
        ListaTextviewsParejasE.add(palabra = "ser");
        ListaTextviewsParejasE.add(palabra = "seis");
        ListaTextviewsParejasE.add(palabra = "ven");
        ListaTextviewsParejasE.add(palabra = "bien");
        ListaTextviewsParejasE.add(palabra = "pesa");
        ListaTextviewsParejasE.add(palabra = "pieza");
        ListaTextviewsParejasE.add(palabra = "fé");
        ListaTextviewsParejasE.add(palabra = "feria");
        ListaTextviewsParejasE.add(palabra = "ten");
        ListaTextviewsParejasE.add(palabra = "tenía");

        //Lista de palabras textviews PAREJAS VOCAL I
        List<String> ListaTextviewsParejasI = new ArrayList<>();
        ListaTextviewsParejasI.add(palabra = "mil");
        ListaTextviewsParejasI.add(palabra = "miel");
        ListaTextviewsParejasI.add(palabra = "billar");
        ListaTextviewsParejasI.add(palabra = "bailar");

        //Lista de palabras textviews PAREJAS VOCAL O
        List<String> ListaTextviewsParejasO = new ArrayList<>();
        //ListaTextviewsParejasO.add(palabra = "dos");
        //ListaTextviewsParejasO.add(palabra = "doy"); lo quito por peticion (posible confusion o dos doy)
        ListaTextviewsParejasO.add(palabra = "rosa");
        ListaTextviewsParejasO.add(palabra = "raíz");

        //Lista de palabras textviews PAREJAS VOCAL U
        List<String> ListaTextviewsParejasU = new ArrayList<>();
        ListaTextviewsParejasU.add(palabra = "nube");
        ListaTextviewsParejasU.add(palabra = "nueve");
        ListaTextviewsParejasU.add(palabra = "amuleto");
        ListaTextviewsParejasU.add(palabra = "almuerzo");

        //Lista de palabras textviews PAREJAS DIPTONGO AI
        List<String> ListaTextviewsParejasAI = new ArrayList<>();
        ListaTextviewsParejasAI.add(palabra = "país");
        ListaTextviewsParejasAI.add(palabra = "pan");
        ListaTextviewsParejasAI.add(palabra = "paila");
        ListaTextviewsParejasAI.add(palabra = "pala");
        ListaTextviewsParejasAI.add(palabra = "paisaje");
        ListaTextviewsParejasAI.add(palabra = "pasaje");
        ListaTextviewsParejasAI.add(palabra = "bailar");
        ListaTextviewsParejasAI.add(palabra = "billar");
        ListaTextviewsParejasAI.add(palabra = "raíz");
        ListaTextviewsParejasAI.add(palabra = "rosa");

        //Lista de palabras textviews PAREJAS DIPTONGO EI
        List<String> ListaTextviewsParejasEI = new ArrayList<>();
        ListaTextviewsParejasEI.add(palabra = "seis");
        ListaTextviewsParejasEI.add(palabra = "ser");

        //Lista de palabras textviews PAREJAS DIPTONGO IA
        List<String> ListaTextviewsParejasIA = new ArrayList<>();
        ListaTextviewsParejasIA.add(palabra = "tenía");
        ListaTextviewsParejasIA.add(palabra = "ten");
        ListaTextviewsParejasIA.add(palabra = "feria");
        ListaTextviewsParejasIA.add(palabra = "fé");

        //Lista de palabras textviews PAREJAS DIPTONGO IE
        List<String> ListaTextviewsParejasIE = new ArrayList<>();
        ListaTextviewsParejasIE.add(palabra = "bien");
        ListaTextviewsParejasIE.add(palabra = "ven");
        ListaTextviewsParejasIE.add(palabra = "pieza");
        ListaTextviewsParejasIE.add(palabra = "pesa");
        ListaTextviewsParejasIE.add(palabra = "miel");
        ListaTextviewsParejasIE.add(palabra = "mil");

        //Lista de palabras textviews PAREJAS DIPTONGO OI
        List<String> ListaTextviewsParejasOI = new ArrayList<>();
        ListaTextviewsParejasOI.add(palabra = "doy");
        ListaTextviewsParejasOI.add(palabra = "dos");

        //Lista de palabras textviews PAREJAS DIPTONGO UE
        List<String> ListaTextviewsParejasUE = new ArrayList<>();
        ListaTextviewsParejasUE.add(palabra = "nueve");
        ListaTextviewsParejasUE.add(palabra = "nube");
        ListaTextviewsParejasUE.add(palabra = "almuerzo");
        ListaTextviewsParejasUE.add(palabra = "amuleto");




        //bd
        DB = new DBHelperPuntajes(getContext());

        // funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(view); //nacControllermio es el nombre de la instancia


        // Initialize a new Handler
        handler = new Handler();
        handlerVocalDiptongo = new Handler();
        handlerpalabra1 = new Handler();
        handlerpalabra2 = new Handler();




//RUNNABLE O TIMER PARA EJECUTAR PROCESO CADA CIERTO TIEMPO
        //final Handler handler = new Handler();
        //final Runnable runnable = new Runnable() {

        runnable_iniciar = new Runnable() {
            public void run() {

                //Log.d("Runnable", "Handler is working");

                //para que salga el texto "escuche los sonidos"
                textViewEscuche.setVisibility(View.VISIBLE);

                //para ocultar lOs TEXTVIEWS 1  y 2 nuevamente
                textView1.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                textViewSeleccione.setVisibility(View.INVISIBLE);

                //para que los textviews no sean clickeables
                textView1.setClickable(false);
                textView2.setClickable(false);


                //Generar un numero aleatorio para escoger entre vocal o diptongo
                Random rand = new Random();
                randomInicial = rand.nextInt(2);//Numero randomInicial entre 0 1
                if(randomInicial ==0){

                    //Genera Sonidos con Vocales

                    //Generacion Aleatoria de numeros imagenes y sonidos
                    int randomIntListaVocales = (new Random().nextInt(ListaSonidosVocales.size()));
                    //int randomIntListaLargos = (new Random().nextInt(ListaSonidosCortos.size()));

                    //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                    numero_sonidovocal = ListaSonidosVocales.get(randomIntListaVocales);
                    //numero_sonidolargo = ListaSonidosLargos.get(randomIntListaLargos);

                    //Crea la instancia  del sonido de acuerdo a la posicion int
                    sonidoVocal = MediaPlayer.create(getActivity(), numero_sonidovocal);
                    //sonidolargo = MediaPlayer.create(getActivity(), numero_sonidolargo);


                    switch (randomIntListaVocales) {
                        case 0:
                            //configuro la vocal que va en la sombrero
                            texto_textviewVocal = ListaTextviewsVocales.get(randomIntListaVocales);
                            textViewcentro.setText(texto_textviewVocal);

                            //Generacion Aleatoria de numeros imagenes y sonidos
                            randomIntListaPalabras = (new Random().nextInt(ListaSonidosParejasA.size()));

                            if (randomIntListaPalabras % 2 == 0) {

                                //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasA.get(randomIntListaPalabras);
                                numero_sonidopalabra2 = ListaSonidosParejasA.get(randomIntListaPalabras + 1);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasA.get(randomIntListaPalabras);
                                texto_textviewPalabra2 = ListaTextviewsParejasA.get(randomIntListaPalabras + 1);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido

                            } else {

                                //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasA.get(randomIntListaPalabras - 1);
                                numero_sonidopalabra2 = ListaSonidosParejasA.get(randomIntListaPalabras);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasA.get(randomIntListaPalabras - 1);
                                texto_textviewPalabra2 = ListaTextviewsParejasA.get(randomIntListaPalabras);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido
                            }
                            break;
                        case 1:
                            //configuro la vocal que va en la sombrero
                            texto_textviewVocal = ListaTextviewsVocales.get(randomIntListaVocales);
                            textViewcentro.setText(texto_textviewVocal);

                            //Generacion Aleatoria de numeros imagenes y sonidos
                            randomIntListaPalabras = (new Random().nextInt(ListaSonidosParejasE.size()));

                            if (randomIntListaPalabras % 2 == 0) {

                                //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasE.get(randomIntListaPalabras);
                                numero_sonidopalabra2 = ListaSonidosParejasE.get(randomIntListaPalabras + 1);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasE.get(randomIntListaPalabras);
                                texto_textviewPalabra2 = ListaTextviewsParejasE.get(randomIntListaPalabras + 1);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido

                            } else {

                                //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasE.get(randomIntListaPalabras - 1);
                                numero_sonidopalabra2 = ListaSonidosParejasE.get(randomIntListaPalabras);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasE.get(randomIntListaPalabras - 1);
                                texto_textviewPalabra2 = ListaTextviewsParejasE.get(randomIntListaPalabras);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido
                            }
                            break;
                        case 2:
                            //configuro la vocal que va en la sombrero
                            texto_textviewVocal = ListaTextviewsVocales.get(randomIntListaVocales);
                            textViewcentro.setText(texto_textviewVocal);

                            //Generacion Aleatoria de numeros imagenes y sonidos
                            randomIntListaPalabras = (new Random().nextInt(ListaSonidosParejasI.size()));

                            if (randomIntListaPalabras % 2 == 0) {

                                //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasI.get(randomIntListaPalabras);
                                numero_sonidopalabra2 = ListaSonidosParejasI.get(randomIntListaPalabras + 1);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasI.get(randomIntListaPalabras);
                                texto_textviewPalabra2 = ListaTextviewsParejasI.get(randomIntListaPalabras + 1);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido

                            } else {

                                //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasI.get(randomIntListaPalabras - 1);
                                numero_sonidopalabra2 = ListaSonidosParejasI.get(randomIntListaPalabras);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasI.get(randomIntListaPalabras - 1);
                                texto_textviewPalabra2 = ListaTextviewsParejasI.get(randomIntListaPalabras);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido
                            }
                            break;
                        case 3:
                            //configuro la vocal que va en la sombrero
                            texto_textviewVocal = ListaTextviewsVocales.get(randomIntListaVocales);
                            textViewcentro.setText(texto_textviewVocal);


                            //Generacion Aleatoria de numeros imagenes y sonidos
                            randomIntListaPalabras = (new Random().nextInt(ListaSonidosParejasO.size()));

                            if (randomIntListaPalabras % 2 == 0) {

                                //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasO.get(randomIntListaPalabras);
                                numero_sonidopalabra2 = ListaSonidosParejasO.get(randomIntListaPalabras + 1);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasO.get(randomIntListaPalabras);
                                texto_textviewPalabra2 = ListaTextviewsParejasO.get(randomIntListaPalabras + 1);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido

                            } else {

                                //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasO.get(randomIntListaPalabras - 1);
                                numero_sonidopalabra2 = ListaSonidosParejasO.get(randomIntListaPalabras);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasO.get(randomIntListaPalabras - 1);
                                texto_textviewPalabra2 = ListaTextviewsParejasO.get(randomIntListaPalabras);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido
                            }
                            break;
                        case 4:
                            //configuro la vocal que va en la sombrero
                            texto_textviewVocal = ListaTextviewsVocales.get(randomIntListaVocales);
                            textViewcentro.setText(texto_textviewVocal);
                            //Generacion Aleatoria de numeros imagenes y sonidos
                            randomIntListaPalabras = (new Random().nextInt(ListaSonidosParejasU.size()));

                            if (randomIntListaPalabras % 2 == 0) {

                                //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasU.get(randomIntListaPalabras);
                                numero_sonidopalabra2 = ListaSonidosParejasU.get(randomIntListaPalabras + 1);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasU.get(randomIntListaPalabras);
                                texto_textviewPalabra2 = ListaTextviewsParejasU.get(randomIntListaPalabras + 1);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido

                            } else {

                                //Obtengo de la lista de sonidos, el sonido con la posicion randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasU.get(randomIntListaPalabras - 1);
                                numero_sonidopalabra2 = ListaSonidosParejasU.get(randomIntListaPalabras);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasU.get(randomIntListaPalabras - 1);
                                texto_textviewPalabra2 = ListaTextviewsParejasU.get(randomIntListaPalabras);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido
                            }
                            break;
                    }

                } else{

                    //Genera Sonidos con Diptongos

                    //Generacion Aleatoria de numeros imagenes y sonidos
                    int randomIntListaDiptongos = (new Random().nextInt(ListaSonidosDiptongos.size()));
                    //int randomIntListaLargos = (new Random().nextInt(ListaSonidosCortos.size()));

                    //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                    numero_sonidoDiptongo = ListaSonidosDiptongos.get(randomIntListaDiptongos);
                    //numero_sonidolargo = ListaSonidosLargos.get(randomIntListaLargos);

                    //Crea la instancia  del sonido de acuerdo a la posicion int
                    sonidoDiptongo = MediaPlayer.create(getActivity(), numero_sonidoDiptongo);
                    //sonidolargo = MediaPlayer.create(getActivity(), numero_sonidolargo);


                    switch (randomIntListaDiptongos) {
                        case 0:
                            //configuro el diptongo que va en el sombrero
                            texto_textviewDiptongo = ListaTextviewsDiptongos.get(randomIntListaDiptongos);
                            textViewcentro.setText(texto_textviewDiptongo);

                            //Generacion Aleatoria de numeros imagenes y sonidos
                            randomIntListaPalabras = (new Random().nextInt(ListaSonidosParejasAI.size()));

                            if (randomIntListaPalabras % 2 == 0) {

                                //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasAI.get(randomIntListaPalabras);
                                numero_sonidopalabra2 = ListaSonidosParejasAI.get(randomIntListaPalabras + 1);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasAI.get(randomIntListaPalabras);
                                texto_textviewPalabra2 = ListaTextviewsParejasAI.get(randomIntListaPalabras + 1);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido

                            } else {

                                //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasAI.get(randomIntListaPalabras - 1);
                                numero_sonidopalabra2 = ListaSonidosParejasAI.get(randomIntListaPalabras);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasAI.get(randomIntListaPalabras - 1);
                                texto_textviewPalabra2 = ListaTextviewsParejasAI.get(randomIntListaPalabras);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido
                            }
                            break;
                        case 1:
                            //configuro el diptongo que va en el sombrero
                            texto_textviewDiptongo = ListaTextviewsDiptongos.get(randomIntListaDiptongos);
                            textViewcentro.setText(texto_textviewDiptongo);

                            //Generacion Aleatoria de numeros imagenes y sonidos
                            randomIntListaPalabras = (new Random().nextInt(ListaSonidosParejasEI.size()));

                            if (randomIntListaPalabras % 2 == 0) {

                                //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasEI.get(randomIntListaPalabras);
                                numero_sonidopalabra2 = ListaSonidosParejasEI.get(randomIntListaPalabras + 1);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasEI.get(randomIntListaPalabras);
                                texto_textviewPalabra2 = ListaTextviewsParejasEI.get(randomIntListaPalabras + 1);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido

                            } else {

                                //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasEI.get(randomIntListaPalabras - 1);
                                numero_sonidopalabra2 = ListaSonidosParejasEI.get(randomIntListaPalabras);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasEI.get(randomIntListaPalabras - 1);
                                texto_textviewPalabra2 = ListaTextviewsParejasEI.get(randomIntListaPalabras);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido
                            }
                            break;
                        case 2:
                            //configuro el diptongo que va en el sombrero
                            texto_textviewDiptongo = ListaTextviewsDiptongos.get(randomIntListaDiptongos);
                            textViewcentro.setText(texto_textviewDiptongo);

                            //Generacion Aleatoria de numeros imagenes y sonidos
                            randomIntListaPalabras = (new Random().nextInt(ListaSonidosParejasIA.size()));

                            if (randomIntListaPalabras % 2 == 0) {

                                //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasIA.get(randomIntListaPalabras);
                                numero_sonidopalabra2 = ListaSonidosParejasIA.get(randomIntListaPalabras + 1);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasIA.get(randomIntListaPalabras);
                                texto_textviewPalabra2 = ListaTextviewsParejasIA.get(randomIntListaPalabras + 1);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido

                            } else {

                                //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasIA.get(randomIntListaPalabras - 1);
                                numero_sonidopalabra2 = ListaSonidosParejasIA.get(randomIntListaPalabras);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasIA.get(randomIntListaPalabras - 1);
                                texto_textviewPalabra2 = ListaTextviewsParejasIA.get(randomIntListaPalabras);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido
                            }
                            break;
                        case 3:
                            //configuro el diptongo que va en el sombrero
                            texto_textviewDiptongo = ListaTextviewsDiptongos.get(randomIntListaDiptongos);
                            textViewcentro.setText(texto_textviewDiptongo);

                            //Generacion Aleatoria de numeros imagenes y sonidos
                            randomIntListaPalabras = (new Random().nextInt(ListaSonidosParejasIE.size()));

                            if (randomIntListaPalabras % 2 == 0) {

                                //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasIE.get(randomIntListaPalabras);
                                numero_sonidopalabra2 = ListaSonidosParejasIE.get(randomIntListaPalabras + 1);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasIE.get(randomIntListaPalabras);
                                texto_textviewPalabra2 = ListaTextviewsParejasIE.get(randomIntListaPalabras + 1);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido

                            } else {

                                //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasIE.get(randomIntListaPalabras - 1);
                                numero_sonidopalabra2 = ListaSonidosParejasIE.get(randomIntListaPalabras);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasIE.get(randomIntListaPalabras - 1);
                                texto_textviewPalabra2 = ListaTextviewsParejasIE.get(randomIntListaPalabras);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido
                            }
                            break;
                        case 4:

                            //configuro el diptongo que va en el sombrero
                            texto_textviewDiptongo = ListaTextviewsDiptongos.get(randomIntListaDiptongos);
                            textViewcentro.setText(texto_textviewDiptongo);

                            //Generacion Aleatoria de numeros imagenes y sonidos
                            randomIntListaPalabras = (new Random().nextInt(ListaSonidosParejasOI.size()));

                            if (randomIntListaPalabras % 2 == 0) {

                                //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasOI.get(randomIntListaPalabras);
                                numero_sonidopalabra2 = ListaSonidosParejasOI.get(randomIntListaPalabras + 1);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasOI.get(randomIntListaPalabras);
                                texto_textviewPalabra2 = ListaTextviewsParejasOI.get(randomIntListaPalabras + 1);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido

                            } else {

                                //Obtengo de la lista de sonidos, el sonido con la posicion randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasOI.get(randomIntListaPalabras - 1);
                                numero_sonidopalabra2 = ListaSonidosParejasOI.get(randomIntListaPalabras);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasOI.get(randomIntListaPalabras - 1);
                                texto_textviewPalabra2 = ListaTextviewsParejasOI.get(randomIntListaPalabras);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido
                            }
                            break;

                        case 5:

                            //configuro el diptongo que va en el sombrero
                            texto_textviewDiptongo = ListaTextviewsDiptongos.get(randomIntListaDiptongos);
                            textViewcentro.setText(texto_textviewDiptongo);

                            //Generacion Aleatoria de numeros imagenes y sonidos
                            randomIntListaPalabras = (new Random().nextInt(ListaSonidosParejasUE.size()));

                            if (randomIntListaPalabras % 2 == 0) {

                                //Obtengo de la lista de sonidos, el sonido con la posicon randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasUE.get(randomIntListaPalabras);
                                numero_sonidopalabra2 = ListaSonidosParejasUE.get(randomIntListaPalabras + 1);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasUE.get(randomIntListaPalabras);
                                texto_textviewPalabra2 = ListaTextviewsParejasUE.get(randomIntListaPalabras + 1);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido

                            } else {

                                //Obtengo de la lista de sonidos, el sonido con la posicion randomInicial generada
                                numero_sonidopalabra1 = ListaSonidosParejasUE.get(randomIntListaPalabras - 1);
                                numero_sonidopalabra2 = ListaSonidosParejasUE.get(randomIntListaPalabras);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasUE.get(randomIntListaPalabras - 1);
                                texto_textviewPalabra2 = ListaTextviewsParejasUE.get(randomIntListaPalabras);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidoPalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidoPalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido
                            }
                            break;
                    }


                }


                //Reproduccion aleatoria sonidos
                reproducirSonidosAleatorio();




                //Retardo
                //global_retardo = (int) (Math.randomInicial() * 13000) + 11000;//genera retardo entre 3 y 5 segs
                global_retardo = 60000;

                //defino texto vocal en textview centro para luego comparar
                //textViewcentro.setText(texto_textviewVocal);


                if (i >= 13) { // just remove call backs//
                    //para parar el sonido correctamente
                    if(sonidoVocal != null) {
                        sonidoVocal.stop();
                        sonidoVocal.release();
                        sonidoVocal = null;
                    }
                    if(sonidoDiptongo != null) {
                        sonidoDiptongo.stop();
                        sonidoDiptongo.release();
                        sonidoDiptongo = null;
                    }
                    if(sonidoPalabra1 != null) {
                        sonidoPalabra1.stop();
                        sonidoPalabra1.release();
                        sonidoPalabra1 = null;
                    }
                    if(sonidoPalabra2 != null) {
                        sonidoPalabra2.stop();
                        sonidoPalabra2.release();
                        sonidoPalabra2 = null;
                    }

                    sombrero.setVisibility(View.INVISIBLE);
                    textView1.setVisibility(View.INVISIBLE);
                    textView1.setVisibility(View.INVISIBLE);
                    iniciar.setVisibility(View.VISIBLE);
                    parar.setVisibility(View.INVISIBLE);
                    textViewEscuche.setVisibility(View.INVISIBLE);
                    textViewAciertos.setVisibility(View.INVISIBLE);
                    textViewFallaste.setVisibility(View.INVISIBLE);
                    handler.removeCallbacks(this);
                    //Log.d("Runnable", "ok");

                    if (aciertos_actividad >= 10) {

                        //guardar puntaje en BD
                        guardarPuntajeBD();

                        //Guardar puntaje en sharedpreferences
                        guardarPreferenciasPuntaje();

                        //pasar a otro fragment
                        navControllermio.navigate(R.id.action_actividad_discr_2Fragment_to_actividad_discr_2_felicitacionFragment);//pasar a otro fragment con actions

                    } else {

                        navControllermio.navigate(R.id.action_actividad_discr_2Fragment_to_actividad_discr_2_erradoFragment);//pasar a otro fragment con actions

                        guardarPuntajeBD();
                        //Guardar puntaje en sharedpreferences
                        guardarPreferenciasPuntaje();
                    }
                }else{ // post again
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

                //inicio el runnable despues de 1-3 segundos
                handlerVocalDiptongo.removeCallbacks(runnableVocalDiptongo);
                handlerpalabra1.removeCallbacks(runnablepalabra1);
                handlerpalabra2.removeCallbacks(runnablepalabra2);
                handler.removeCallbacks(runnable_iniciar);
                handler.postDelayed(runnable_iniciar, retardo);
            }
        });

//FUNCION PARA EL BOTON PARAR
        parar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(sonidoVocal != null) {
                    sonidoVocal.stop();
                    sonidoVocal.release();
                    sonidoVocal = null;
                }
                if(sonidoDiptongo != null) {
                    sonidoDiptongo.stop();
                    sonidoDiptongo.release();
                    sonidoDiptongo = null;
                }
                if(sonidoPalabra1 != null) {
                    sonidoPalabra1.stop();
                    sonidoPalabra1.release();
                    sonidoPalabra1 = null;
                }
                if(sonidoPalabra2 != null) {
                    sonidoPalabra2.stop();
                    sonidoPalabra2.release();
                    sonidoPalabra2 = null;
                }

                //sonido = MediaPlayer.create(getActivity(),R.raw.telefono3seg);

                sombrero.setVisibility(View.INVISIBLE);
                textView1.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                parar.setVisibility(View.INVISIBLE);
                iniciar.setVisibility(View.VISIBLE);
                //ayudas.setVisibility(View.VISIBLE);
                instrucciones.setVisibility(View.VISIBLE);
                textViewEscuche.setVisibility(View.INVISIBLE);
                //textViewPregunta.setVisibility(View.INVISIBLE);
                textViewSeleccione.setVisibility(View.INVISIBLE);
                textViewAciertos.setVisibility(View.INVISIBLE);
                textViewFallaste.setVisibility(View.INVISIBLE);

                handlerVocalDiptongo.removeCallbacks(runnableVocalDiptongo);
                handlerpalabra1.removeCallbacks(runnablepalabra1);
                handlerpalabra2.removeCallbacks(runnablepalabra2);
                handler.removeCallbacks(runnable_iniciar);
                //Log.d("Runnable", "ok");
            }
        });

//FUNCION PARA EL BOTON AYUDAS
        instrucciones.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //navControllermio.navigate(R.id.actividad_conc_1_ayudasFragment); //pasar a otro fragment
                navControllermio.navigate(R.id.action_actividad_discr_2Fragment_to_actividad_discr_2_instruccionesFragment); //pasar a otro fragment con actions


                handlerVocalDiptongo.removeCallbacks(runnableVocalDiptongo);
                handlerpalabra1.removeCallbacks(runnablepalabra1);
                handlerpalabra2.removeCallbacks(runnablepalabra2);
                handler.removeCallbacks(runnable_iniciar);
            }
        });


        //Metodo de SOLO SELECCIONAR
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarRespuesta1();
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarRespuesta2();
            }
        });




    }//onviewcreated




//MÉTODOS UTILIZADOS

    private void reproducirSonidosAleatorio(){

        //Reproducir sonidos Aleatoriamente
        if(randomInicial== 0){
            //Reproduccion aleatoria sonidos
            reproducirSonidosVocalAleatorio();
            //defino texto vocal en textview centro para luego comparar
            textViewcentro.setText(texto_textviewVocal);

        }else {
            reproducirSonidosDiptongoAleatorio();
            //defino texto vocal en textview centro para luego comparar
            textViewcentro.setText(texto_textviewDiptongo);
        }

    }

    private void reproducirSonidosVocalAleatorio(){

        //Reproducir aleatoria sonidos
        Random rand = new Random();
        int random= rand.nextInt(2);//Numero random entre 0 1
        if(random==0){
            reproducirVocalSonidos1();

        }else{
            reproducirVocalSonidos2();

        }
    }

    private void reproducirVocalSonidos1(){

        handlerVocalDiptongo.postDelayed(runnableVocalDiptongo = new Runnable() {
            @Override
            public void run() {

                //mostrar sombrero animacion
                sombrero.setVisibility(View.VISIBLE);
                Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                sombrero.startAnimation(animacionDerecha);
                sombrero.startAnimation(animacionIzquierda);

                sonidoVocal.start();
            }
        }, 1000);


        sonidoVocal.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidoVocal.stop();
                sonidoVocal.release();
                sonidoVocal = null;

                sombrero.setVisibility(View.INVISIBLE);
                textViewEscuche.setVisibility(View.INVISIBLE);

            }
        });

        handlerpalabra1.postDelayed(runnablepalabra1 = new Runnable() {
            @Override
            public void run() {

                sonidoPalabra1.start();// play next audio file
                //mostrar textview derecha animacion
                textView1.setText(texto_textviewPalabra1);
                textView1.setBackgroundResource(R.drawable.estilo_recuadro_dos);
                textView1.setTextColor(colorAzul);
                textView1.setVisibility(View.VISIBLE);

                Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                textView1.startAnimation(animacionDerecha);
                textView1.startAnimation(animacionIzquierda);
            }
        }, 4000);

        sonidoPalabra1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidoPalabra1.stop();
                sonidoPalabra1.release();
                sonidoPalabra1 = null;
            }
        });

        handlerpalabra2.postDelayed(runnablepalabra2 = new Runnable() {
            @Override
            public void run() {
                sonidoPalabra2.start();
                //mostrar textview izquierda animacion
                textView2.setText(texto_textviewPalabra2);
                textView2.setBackgroundResource(R.drawable.estilo_recuadro_dos);
                textView2.setTextColor(colorAzul);
                textView2.setVisibility(View.VISIBLE);

                //Animation animacionZoomin = AnimationUtils.loadAnimation(getContext(),R.anim.zoom_in);
                //Animation animacionZoomout = AnimationUtils.loadAnimation(getContext(),R.anim.zoom_out);
                Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                textView2.startAnimation(animacionDerecha);
                textView2.startAnimation(animacionIzquierda);
            }
        }, 7000);

        sonidoPalabra2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidoPalabra2.stop();
                sonidoPalabra2.release();
                sonidoPalabra2 = null;
                //textViewEscuche.setVisibility(View.INVISIBLE);

                textViewSeleccione.setText("Seleccione una palabra");
                textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_azul);
                textViewSeleccione.setVisibility(View.VISIBLE);


                //para que los textviews solo sean clickeables hasta que se acaba el ultimo sonido
                textView1.setClickable(true);
                textView2.setClickable(true);

            }
        });
    }

    private void reproducirVocalSonidos2(){

        handlerVocalDiptongo.postDelayed(runnableVocalDiptongo = new Runnable() {
            @Override
            public void run() {

                //mostrar sombrero animacion
                sombrero.setVisibility(View.VISIBLE);
                Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                sombrero.startAnimation(animacionDerecha);
                sombrero.startAnimation(animacionIzquierda);

                sonidoVocal.start();
            }
        }, 1000);

        sonidoVocal.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidoVocal.stop();
                sonidoVocal.release();
                sonidoVocal = null;

                sombrero.setVisibility(View.INVISIBLE);
                textViewEscuche.setVisibility(View.INVISIBLE);
            }
        });

        handlerpalabra1.postDelayed(runnablepalabra1 = new Runnable() {
            @Override
            public void run() {

                sonidoPalabra2.start();
                //mostrar textview izquierda animacion
                textView1.setText(texto_textviewPalabra2);
                textView1.setBackgroundResource(R.drawable.estilo_recuadro_dos);
                textView1.setTextColor(colorAzul);
                textView1.setVisibility(View.VISIBLE);
                Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                textView1.startAnimation(animacionDerecha);
                textView1.startAnimation(animacionIzquierda);
            }
        }, 4000);

        sonidoPalabra2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidoPalabra2.stop();
                sonidoPalabra2.release();
                sonidoPalabra2 = null;
            }
        });

        handlerpalabra2.postDelayed(runnablepalabra2 =new Runnable() {
            @Override
            public void run() {

                sonidoPalabra1.start();// play next audio file
                //mostrar textview derecha animacion
                textView2.setText(texto_textviewPalabra1);
                textView2.setBackgroundResource(R.drawable.estilo_recuadro_dos);
                textView2.setTextColor(colorAzul);
                textView2.setVisibility(View.VISIBLE);
                //Animation animacionZoomin = AnimationUtils.loadAnimation(getContext(),R.anim.zoom_in);
                //Animation animacionZoomout = AnimationUtils.loadAnimation(getContext(),R.anim.zoom_out);
                Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                textView2.startAnimation(animacionDerecha);
                textView2.startAnimation(animacionIzquierda);
            }
        }, 7000);





        sonidoPalabra1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidoPalabra1.stop();
                sonidoPalabra1.release();
                sonidoPalabra1 = null;
                //textViewEscuche.setVisibility(View.INVISIBLE);

                textViewSeleccione.setText("Seleccione una palabra");
                textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_azul);
                textViewSeleccione.setVisibility(View.VISIBLE);

                //para que los textviews solo sean clickeables hasta que se acaba el ultimo sonido
                textView1.setClickable(true);
                textView2.setClickable(true);
            }
        });

    }

    private void reproducirSonidosDiptongoAleatorio(){

        //Reproducir aleatoria sonidos
        Random rand = new Random();
        int random= rand.nextInt(2);//Numero random entre 0 1
        if(random==0){
            reproducirDiptongoSonidos1();

        }else{
            reproducirDiptongoSonidos2();

        }
    }

    private void reproducirDiptongoSonidos1(){

        handlerVocalDiptongo.postDelayed(runnableVocalDiptongo = new Runnable() {
            @Override
            public void run() {

                //mostrar sombrero animacion
                sombrero.setVisibility(View.VISIBLE);
                Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                sombrero.startAnimation(animacionDerecha);
                sombrero.startAnimation(animacionIzquierda);

                sonidoDiptongo.start();
            }
        }, 1000);


        sonidoDiptongo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidoDiptongo.stop();
                sonidoDiptongo.release();
                sonidoDiptongo = null;

                sombrero.setVisibility(View.INVISIBLE);
                textViewEscuche.setVisibility(View.INVISIBLE);
            }
        });

        handlerpalabra1.postDelayed(runnablepalabra1 = new Runnable() {
            @Override
            public void run() {

                sonidoPalabra1.start();// play next audio file
                //mostrar textview derecha animacion
                textView1.setText(texto_textviewPalabra1);
                textView1.setBackgroundResource(R.drawable.estilo_recuadro_dos);
                textView1.setTextColor(colorAzul);
                textView1.setVisibility(View.VISIBLE);

                Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                textView1.startAnimation(animacionDerecha);
                textView1.startAnimation(animacionIzquierda);
            }
        }, 4000);

        sonidoPalabra1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidoPalabra1.stop();
                sonidoPalabra1.release();
                sonidoPalabra1 = null;
            }
        });

        handlerpalabra2.postDelayed(runnablepalabra2 = new Runnable() {
            @Override
            public void run() {
                sonidoPalabra2.start();
                //mostrar textview izquierda animacion
                textView2.setText(texto_textviewPalabra2);
                textView2.setBackgroundResource(R.drawable.estilo_recuadro_dos);
                textView2.setTextColor(colorAzul);
                textView2.setVisibility(View.VISIBLE);

                Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                textView2.startAnimation(animacionDerecha);
                textView2.startAnimation(animacionIzquierda);
            }
        }, 7000);

        sonidoPalabra2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidoPalabra2.stop();
                sonidoPalabra2.release();
                sonidoPalabra2 = null;
                //textViewEscuche.setVisibility(View.INVISIBLE);

                textViewSeleccione.setText("Seleccione una palabra");
                textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_azul);
                textViewSeleccione.setVisibility(View.VISIBLE);

                //para que los textviews solo sean clickeables hasta que se acaba el ultimo sonido
                textView1.setClickable(true);
                textView2.setClickable(true);;
            }
        });
    }

    private void reproducirDiptongoSonidos2(){

        handlerVocalDiptongo.postDelayed(runnableVocalDiptongo = new Runnable() {
            @Override
            public void run() {

                //mostrar sombrero animacion
                sombrero.setVisibility(View.VISIBLE);
                Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                sombrero.startAnimation(animacionDerecha);
                sombrero.startAnimation(animacionIzquierda);

                sonidoDiptongo.start();
            }
        }, 1000);

        sonidoDiptongo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidoDiptongo.stop();
                sonidoDiptongo.release();
                sonidoDiptongo = null;

                sombrero.setVisibility(View.INVISIBLE);
                textViewEscuche.setVisibility(View.INVISIBLE);
            }
        });

        handlerpalabra1.postDelayed(runnablepalabra1 = new Runnable() {
            @Override
            public void run() {

                sonidoPalabra2.start();
                //mostrar textview izquierda animacion
                textView1.setText(texto_textviewPalabra2);
                textView1.setBackgroundResource(R.drawable.estilo_recuadro_dos);
                textView1.setTextColor(colorAzul);
                textView1.setVisibility(View.VISIBLE);

                Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                textView1.startAnimation(animacionDerecha);
                textView1.startAnimation(animacionIzquierda);
            }
        }, 4000);

        sonidoPalabra2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidoPalabra2.stop();
                sonidoPalabra2.release();
                sonidoPalabra2 = null;
            }
        });

        handlerpalabra2.postDelayed(runnablepalabra2 =new Runnable() {
            @Override
            public void run() {

                sonidoPalabra1.start();// play next audio file
                //mostrar textview derecha animacion
                textView2.setText(texto_textviewPalabra1);
                textView2.setBackgroundResource(R.drawable.estilo_recuadro_dos);
                textView2.setTextColor(colorAzul);
                textView2.setVisibility(View.VISIBLE);

                Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                textView2.startAnimation(animacionDerecha);
                textView2.startAnimation(animacionIzquierda);
            }
        }, 7000);



        sonidoPalabra1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidoPalabra1.stop();
                sonidoPalabra1.release();
                sonidoPalabra1 = null;
                //textViewEscuche.setVisibility(View.INVISIBLE);

                textViewSeleccione.setText("Seleccione una palabra");
                textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_azul);
                textViewSeleccione.setVisibility(View.VISIBLE);

                //para que los textviews solo sean clickeables hasta que se acaba el ultimo sonido
                textView1.setClickable(true);
                textView2.setClickable(true);
            }
        });

    }

    private void seleccionarRespuesta1() {

        //obtengo texto del textview seleccionado
        textoPalabra = textView1.getText().toString();
        textoSombrero = textViewcentro.getText().toString();
        //quito tildes de las palabras
        textoPalabra = Normalizer.normalize(textoPalabra, Normalizer.Form.NFKD);
        textoPalabra = textoPalabra.replaceAll("\\p{M}","");

        //busco si la palabra seleccionada contiene los caracteres del sombrero
        //boolean boolPalabra = textoPalabra.contains(textoSombrero);

        if(randomInicial==0){//VOCALES

            boolean boolDiptongo = false;

            String[] diptongos = {"ai","ei","ia","ie","oi","ue","oy"};

            for (String diptongo: diptongos){
                if (textoPalabra.contains(diptongo)){
                    boolDiptongo=true;//Se equivoca
                    break;
                }
            }

            if(boolDiptongo==true){
                //se equivoca
                textView1.setBackgroundResource(R.drawable.estilo_recuadro_uno_fallos);
                textView1.setTextColor(colorNegro);
                textView1.setClickable(false);

                textView2.setVisibility(View.INVISIBLE);//setClickable(false);
                mostrarTextviewFallaste();
                iniciarRunnable();
            }else{
                //Acierta
                textView1.setBackgroundResource(R.drawable.estilo_recuadro_uno_aciertos);
                textView1.setTextColor(colorNegro);
                textView1.setClickable(false);

                textView2.setVisibility(View.INVISIBLE);

                //puntaje
                aciertos_actividad = aciertos_actividad +1;
                mostrarTextviewAciertos();
                iniciarRunnable();
            }


        }else{//DIPTONGOS
            //busco si la palabra seleccionada contiene los caracteres del sombrero
            //boolean boolPalabra = textoPalabra.contains(textoSombrero);

            boolean boolDiptongo = false;

            String[] diptongos = {"ai","ei","ia","ie","oi","ue","oy"};

            for (String diptongo: diptongos){
                if (textoPalabra.contains(diptongo)){
                    boolDiptongo=true;//contiene diptongo
                    break;
                }
            }

            if(boolDiptongo==true){
                //Acierta
                textView1.setBackgroundResource(R.drawable.estilo_recuadro_uno_aciertos);
                textView1.setTextColor(colorNegro);
                textView1.setClickable(false);

                textView2.setVisibility(View.INVISIBLE);


                //puntaje
                aciertos_actividad = aciertos_actividad +1;
                mostrarTextviewAciertos();
                iniciarRunnable();

            }else{
                //se equivoca
                textView1.setBackgroundResource(R.drawable.estilo_recuadro_uno_fallos);
                textView1.setTextColor(colorNegro);
                textView1.setClickable(false);

                textView2.setVisibility(View.INVISIBLE);//setClickable(false);
                mostrarTextviewFallaste();
                iniciarRunnable();
            }
        }


    }

    private void seleccionarRespuesta2() {

        //obtengo texto del textview seleccionado
        textoPalabra = textView2.getText().toString();
        textoSombrero = textViewcentro.getText().toString();
        //quito tildes de las palabras
        textoPalabra = Normalizer.normalize(textoPalabra, Normalizer.Form.NFKD);
        textoPalabra = textoPalabra.replaceAll("\\p{M}","");

        //busco si la palabra seleccionada contiene los caracteres del sombrero
        //boolean boolPalabra = textoPalabra.contains(textoSombrero);

        if(randomInicial==0){//VOCALES

            boolean boolDiptongo = false;

            String[] diptongos = {"ai","ei","ia","ie","oi","ue","oy"};

            for (String diptongo: diptongos){
                if (textoPalabra.contains(diptongo)){
                    boolDiptongo=true;//Se equivoca
                    break;
                }
            }

            if(boolDiptongo==true){
                //se equivoca
                textView2.setBackgroundResource(R.drawable.estilo_recuadro_uno_fallos);
                textView2.setTextColor(colorNegro);
                textView2.setClickable(false);

                textView1.setVisibility(View.INVISIBLE);//setClickable(false);
                mostrarTextviewFallaste();
                iniciarRunnable();
            }else{
                //Acierta
                textView2.setBackgroundResource(R.drawable.estilo_recuadro_uno_aciertos);
                textView2.setTextColor(colorNegro);
                textView2.setClickable(false);

                textView1.setVisibility(View.INVISIBLE);

                //puntaje
                aciertos_actividad = aciertos_actividad +1;
                mostrarTextviewAciertos();
                iniciarRunnable();
            }


        }else{//DIPTONGOS
            //busco si la palabra seleccionada contiene los caracteres del sombrero
            //boolean boolPalabra = textoPalabra.contains(textoSombrero);

            boolean boolDiptongo = false;

            String[] diptongos = {"ai","ei","ia","ie","oi","ue","oy"};

            for (String diptongo: diptongos){
                if (textoPalabra.contains(diptongo)){
                    boolDiptongo=true;//contiene diptongo
                    break;
                }
            }

            if(boolDiptongo==true){
                //Acierta
                textView2.setBackgroundResource(R.drawable.estilo_recuadro_uno_aciertos);
                textView2.setTextColor(colorNegro);
                textView2.setClickable(false);

                textView1.setVisibility(View.INVISIBLE);

                //puntaje
                aciertos_actividad = aciertos_actividad +1;
                mostrarTextviewAciertos();
                iniciarRunnable();

            }else{
                //se equivoca
                textView2.setBackgroundResource(R.drawable.estilo_recuadro_uno_fallos);
                textView2.setTextColor(colorNegro);
                textView2.setClickable(false);

                textView1.setVisibility(View.INVISIBLE);//setClickable(false);
                mostrarTextviewFallaste();
                iniciarRunnable();
            }
        }


    }


    private void iniciarRunnable(){
        //Iniciar runnable dependiendo las ayudas seleccionadas
        //global_retardo = (int) (Math.random() * 4000) + 2000; //genera retardo aleatoriamente entre un rango 1 - n
        //cargarPreferenciasAyudas();
        int retardo = 4000;
        handlerVocalDiptongo.removeCallbacks(runnableVocalDiptongo);
        handlerpalabra1.removeCallbacks(runnablepalabra1);
        handlerpalabra2.removeCallbacks(runnablepalabra2);
        handler.removeCallbacks(runnable_iniciar);
        handler.postDelayed(runnable_iniciar, retardo);
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
        String subhabilidadTXT = "Discriminación";
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

                textView1.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.INVISIBLE);
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

                textView1.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.INVISIBLE);
            }
        }, 3000);
    }


    //FUNCION ON PAUSE DE LA CLASE
    @Override
    public void onPause() {
        handler.removeCallbacks(runnable_iniciar);
        handlerVocalDiptongo.removeCallbacks(runnableVocalDiptongo);
        handlerpalabra1.removeCallbacks(runnablepalabra1);
        handlerpalabra2.removeCallbacks(runnablepalabra2);

        if(sonidoVocal != null) {
            sonidoVocal.stop();
            sonidoVocal.release();
            sonidoVocal = null;
        }
        if(sonidoDiptongo != null) {
            sonidoDiptongo.stop();
            sonidoDiptongo.release();
            sonidoDiptongo = null;
        }
        if(sonidoPalabra1 != null) {
            sonidoPalabra1.stop();
            sonidoPalabra1.release();
            sonidoPalabra1 = null;
        }
        if(sonidoPalabra2 != null) {
            sonidoPalabra2.stop();
            sonidoPalabra2.release();
            sonidoPalabra2 = null;
        }
        super.onPause();
    }


}//final