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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Actividad_discr_1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Actividad_discr_1Fragment extends Fragment {

    //Declaracion de variables
    private ImageView canasta;
    private Button iniciar, parar, instrucciones;
    private TextView textViewcentro,textView1,textView2, textViewAciertos, textViewInstrucc,  textViewSeleccione, textViewEscuche, textViewFallaste;
    MediaPlayer sonido, sonidovocal,sonidopalabra1,sonidopalabra2;
    int i, aciertos_actividad, global_retardo;
    int numero_sonidovocal, numero_sonidopalabra,numero_sonidopalabra1, numero_sonidopalabra2,numero_textviewVocal,numero_textviewPalabra1,numero_textviewPalabra2, randomPregunta, randomImagenes;

    int randomIntListaPalabras;
    String texto_textviewPalabra1;
    String texto_textviewPalabra2;
    String texto_textviewVocal;
    String caractervocal;
    char segundo_caracter;

    String palabra ="";

    int colorNegro = Color.parseColor("#FF000000");
    int colorRojo = Color.parseColor("#F21D2F");


    String infoActividad = "Sin ayudas";

    DBHelperPuntajes DB;

    //forma para el runnable
    private Handler handler,handlervocal,handlerpalabra1,handlerpalabra2;
    private Runnable myRunnable, runnable_iniciar, runnable_ayudas, runnablevocal,runnablepalabra1, runnablepalabra2,runnablepalabra3,runnablepalabra4;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Actividad_discr_1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Actividad_discr_1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Actividad_discr_1Fragment newInstance(String param1, String param2) {
        Actividad_discr_1Fragment fragment = new Actividad_discr_1Fragment();
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
        return inflater.inflate(R.layout.fragment_actividad_discr_1, container, false);
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

        canasta = view.findViewById(R.id.canasta);





        //Lista de sonidos VOCALES
        List<Integer> ListaSonidosVocales = new ArrayList<>();
        ListaSonidosVocales.add(R.raw.a);
        ListaSonidosVocales.add(R.raw.e);
        ListaSonidosVocales.add(R.raw.i);
        ListaSonidosVocales.add(R.raw.o);
        ListaSonidosVocales.add(R.raw.u);

        //Lista de sonidos PAREJAS VOCAL A
        List<Integer> ListaSonidosParejasA = new ArrayList<>();
        ListaSonidosParejasA.add(R.raw.pan);
        ListaSonidosParejasA.add(R.raw.pun);
        ListaSonidosParejasA.add(R.raw.van);
        ListaSonidosParejasA.add(R.raw.ven);
        ListaSonidosParejasA.add(R.raw.taza);
        ListaSonidosParejasA.add(R.raw.tiza);
        ListaSonidosParejasA.add(R.raw.masa);
        ListaSonidosParejasA.add(R.raw.mesa);
        ListaSonidosParejasA.add(R.raw.sabe);
        ListaSonidosParejasA.add(R.raw.sube);

        //Lista de sonidos PAREJAS VOCAL E
        List<Integer> ListaSonidosParejasE = new ArrayList<Integer>();
        ListaSonidosParejasE.add(R.raw.mesa);
        ListaSonidosParejasE.add(R.raw.masa);
        ListaSonidosParejasE.add(R.raw.me);
        ListaSonidosParejasE.add(R.raw.mi);
        ListaSonidosParejasE.add(R.raw.pese);
        ListaSonidosParejasE.add(R.raw.puse);
        ListaSonidosParejasE.add(R.raw.pesa);
        ListaSonidosParejasE.add(R.raw.posa);
        ListaSonidosParejasE.add(R.raw.ven);
        ListaSonidosParejasE.add(R.raw.van);
        ListaSonidosParejasE.add(R.raw.ves);
        ListaSonidosParejasE.add(R.raw.voz_palabra);

        //Lista de sonidos PAREJAS VOCAL I
        List<Integer> ListaSonidosParejasI = new ArrayList<Integer>();
        ListaSonidosParejasI.add(R.raw.bis);
        ListaSonidosParejasI.add(R.raw.bus);
        ListaSonidosParejasI.add(R.raw.lina);
        ListaSonidosParejasI.add(R.raw.luna);
        ListaSonidosParejasI.add(R.raw.mi);
        ListaSonidosParejasI.add(R.raw.me);
        ListaSonidosParejasI.add(R.raw.mico);
        ListaSonidosParejasI.add(R.raw.moco);
        ListaSonidosParejasI.add(R.raw.pin);
        ListaSonidosParejasI.add(R.raw.pon);
        ListaSonidosParejasI.add(R.raw.tiza);
        ListaSonidosParejasI.add(R.raw.taza);


        //Lista de sonidos PAREJAS VOCAL O
        List<Integer> ListaSonidosParejasO = new ArrayList<Integer>();
        ListaSonidosParejasO.add(R.raw.moco);
        ListaSonidosParejasO.add(R.raw.mico);
        ListaSonidosParejasO.add(R.raw.posa);
        ListaSonidosParejasO.add(R.raw.pesa);
        ListaSonidosParejasO.add(R.raw.pon);
        ListaSonidosParejasO.add(R.raw.pin);
        ListaSonidosParejasO.add(R.raw.tos);
        ListaSonidosParejasO.add(R.raw.tus);
        ListaSonidosParejasO.add(R.raw.voz_palabra);
        ListaSonidosParejasO.add(R.raw.ves);

        //Lista de sonidos PAREJAS VOCAL U
        List<Integer> ListaSonidosParejasU = new ArrayList<Integer>();
        ListaSonidosParejasU.add(R.raw.bus);
        ListaSonidosParejasU.add(R.raw.bis);
        ListaSonidosParejasU.add(R.raw.luna);
        ListaSonidosParejasU.add(R.raw.lina);
        ListaSonidosParejasU.add(R.raw.puse);
        ListaSonidosParejasU.add(R.raw.pese);
        ListaSonidosParejasU.add(R.raw.pun);
        ListaSonidosParejasU.add(R.raw.pan);
        ListaSonidosParejasU.add(R.raw.sube);
        ListaSonidosParejasU.add(R.raw.sabe);
        ListaSonidosParejasU.add(R.raw.tus);
        ListaSonidosParejasU.add(R.raw.tos);

        //Lista de vocales textviews VOCALES
        List<String> ListaTextviewsVocales = new ArrayList<>();
        ListaTextviewsVocales.add(palabra = "a");
        ListaTextviewsVocales.add(palabra = "e");
        ListaTextviewsVocales.add(palabra = "i");
        ListaTextviewsVocales.add(palabra = "o");
        ListaTextviewsVocales.add(palabra = "u");

        //Lista de palabras textviews PAREJAS VOCAL A
        List<String> ListaTextviewsParejasA = new ArrayList<>();
        ListaTextviewsParejasA.add(palabra = "pan");
        ListaTextviewsParejasA.add(palabra = "pun");
        ListaTextviewsParejasA.add(palabra = "van");
        ListaTextviewsParejasA.add(palabra = "ven");
        ListaTextviewsParejasA.add(palabra = "taza");
        ListaTextviewsParejasA.add(palabra = "tiza");
        ListaTextviewsParejasA.add(palabra = "masa");
        ListaTextviewsParejasA.add(palabra = "mesa");
        ListaTextviewsParejasA.add(palabra = "sabe");
        ListaTextviewsParejasA.add(palabra = "sube");

        //Lista de palabras textviews PAREJAS VOCAL E
        List<String> ListaTextviewsParejasE = new ArrayList<>();
        ListaTextviewsParejasE.add(palabra = "mesa");
        ListaTextviewsParejasE.add(palabra = "masa");
        ListaTextviewsParejasE.add(palabra = "me");
        ListaTextviewsParejasE.add(palabra = "mi");
        ListaTextviewsParejasE.add(palabra = "pese");
        ListaTextviewsParejasE.add(palabra = "puse");
        ListaTextviewsParejasE.add(palabra = "pesa");
        ListaTextviewsParejasE.add(palabra = "posa");
        ListaTextviewsParejasE.add(palabra = "ven");
        ListaTextviewsParejasE.add(palabra = "van");
        ListaTextviewsParejasE.add(palabra = "ves");
        ListaTextviewsParejasE.add(palabra = "voz");

        //Lista de palabras textviews PAREJAS VOCAL I
        List<String> ListaTextviewsParejasI = new ArrayList<>();
        ListaTextviewsParejasI.add(palabra = "bis");
        ListaTextviewsParejasI.add(palabra = "bus");
        ListaTextviewsParejasI.add(palabra = "lina");
        ListaTextviewsParejasI.add(palabra = "luna");
        ListaTextviewsParejasI.add(palabra = "mi");
        ListaTextviewsParejasI.add(palabra = "me");
        ListaTextviewsParejasI.add(palabra = "mico");
        ListaTextviewsParejasI.add(palabra = "moco");
        ListaTextviewsParejasI.add(palabra = "pin");
        ListaTextviewsParejasI.add(palabra = "pon");
        ListaTextviewsParejasI.add(palabra = "tiza");
        ListaTextviewsParejasI.add(palabra = "taza");

        //Lista de palabras textviews PAREJAS VOCAL O
        List<String> ListaTextviewsParejasO = new ArrayList<>();
        ListaTextviewsParejasO.add(palabra = "moco");
        ListaTextviewsParejasO.add(palabra = "mico");
        ListaTextviewsParejasO.add(palabra = "posa");
        ListaTextviewsParejasO.add(palabra = "pesa");
        ListaTextviewsParejasO.add(palabra = "pon");
        ListaTextviewsParejasO.add(palabra = "pin");
        ListaTextviewsParejasO.add(palabra = "tos");
        ListaTextviewsParejasO.add(palabra = "tus");
        ListaTextviewsParejasO.add(palabra = "voz");
        ListaTextviewsParejasO.add(palabra = "ves");

        //Lista de palabras textviews PAREJAS VOCAL U
        List<String> ListaTextviewsParejasU = new ArrayList<>();
        ListaTextviewsParejasU.add(palabra = "bus");
        ListaTextviewsParejasU.add(palabra = "bis");
        ListaTextviewsParejasU.add(palabra = "luna");
        ListaTextviewsParejasU.add(palabra = "lina");
        ListaTextviewsParejasU.add(palabra = "puse");
        ListaTextviewsParejasU.add(palabra = "pese");
        ListaTextviewsParejasU.add(palabra = "pun");
        ListaTextviewsParejasU.add(palabra = "pan");
        ListaTextviewsParejasU.add(palabra = "sube");
        ListaTextviewsParejasU.add(palabra = "sabe");
        ListaTextviewsParejasU.add(palabra = "tus");
        ListaTextviewsParejasU.add(palabra = "tos");


        //bd
        DB = new DBHelperPuntajes(getContext());

        // funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(view); //nacControllermio es el nombre de la instancia


        // Initialize a new Handler
        handler = new Handler();
        handlervocal = new Handler();
        handlerpalabra1 = new Handler();
        handlerpalabra2 = new Handler();




//RUNNABLE O TIMER PARA EJECUTAR PROCESO CADA CIERTO TIEMPO
        //final Handler handler = new Handler();
        //final Runnable runnable = new Runnable() {

        runnable_iniciar = new Runnable() {
            public void run() {

                    //Log.d("Runnable", "Handler is working");


                    //Generacion Aleatoria de numeros imagenes y sonidos
                    int randomIntListaVocales = (new Random().nextInt(ListaSonidosVocales.size()));
                    //int randomIntListaLargos = (new Random().nextInt(ListaSonidosCortos.size()));

                    //Obtengo de la lista de sonidos, el sonido con la posicon random generada
                    numero_sonidovocal = ListaSonidosVocales.get(randomIntListaVocales);
                    //numero_sonidolargo = ListaSonidosLargos.get(randomIntListaLargos);

                    //Crea la instancia  del sonido de acuerdo a la posicion int
                    sonidovocal = MediaPlayer.create(getActivity(), numero_sonidovocal);
                    //sonidolargo = MediaPlayer.create(getActivity(), numero_sonidolargo);


                    switch (randomIntListaVocales) {
                        case 0:
                            //configuro la vocal que va en la canasta
                            texto_textviewVocal = ListaTextviewsVocales.get(randomIntListaVocales);
                            textViewcentro.setText(texto_textviewVocal);

                            //Generacion Aleatoria de numeros imagenes y sonidos
                            randomIntListaPalabras = (new Random().nextInt(ListaSonidosParejasA.size()));

                            if (randomIntListaPalabras % 2 == 0) {

                                //Obtengo de la lista de sonidos, el sonido con la posicon random generada
                                numero_sonidopalabra1 = ListaSonidosParejasA.get(randomIntListaPalabras);
                                numero_sonidopalabra2 = ListaSonidosParejasA.get(randomIntListaPalabras + 1);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasA.get(randomIntListaPalabras);
                                texto_textviewPalabra2 = ListaTextviewsParejasA.get(randomIntListaPalabras + 1);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidopalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidopalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido

                            } else {

                                //Obtengo de la lista de sonidos, el sonido con la posicon random generada
                                numero_sonidopalabra1 = ListaSonidosParejasA.get(randomIntListaPalabras - 1);
                                numero_sonidopalabra2 = ListaSonidosParejasA.get(randomIntListaPalabras);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasA.get(randomIntListaPalabras - 1);
                                texto_textviewPalabra2 = ListaTextviewsParejasA.get(randomIntListaPalabras);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidopalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidopalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido
                            }
                            break;
                        case 1:
                            //configuro la vocal que va en la canasta
                            texto_textviewVocal = ListaTextviewsVocales.get(randomIntListaVocales);
                            textViewcentro.setText(texto_textviewVocal);

                            //Generacion Aleatoria de numeros imagenes y sonidos
                            randomIntListaPalabras = (new Random().nextInt(ListaSonidosParejasE.size()));

                            if (randomIntListaPalabras % 2 == 0) {

                                //Obtengo de la lista de sonidos, el sonido con la posicon random generada
                                numero_sonidopalabra1 = ListaSonidosParejasE.get(randomIntListaPalabras);
                                numero_sonidopalabra2 = ListaSonidosParejasE.get(randomIntListaPalabras + 1);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasE.get(randomIntListaPalabras);
                                texto_textviewPalabra2 = ListaTextviewsParejasE.get(randomIntListaPalabras + 1);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidopalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidopalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido

                            } else {

                                //Obtengo de la lista de sonidos, el sonido con la posicon random generada
                                numero_sonidopalabra1 = ListaSonidosParejasE.get(randomIntListaPalabras - 1);
                                numero_sonidopalabra2 = ListaSonidosParejasE.get(randomIntListaPalabras);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasE.get(randomIntListaPalabras - 1);
                                texto_textviewPalabra2 = ListaTextviewsParejasE.get(randomIntListaPalabras);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidopalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidopalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido
                            }
                            break;
                        case 2:
                            //configuro la vocal que va en la canasta
                            texto_textviewVocal = ListaTextviewsVocales.get(randomIntListaVocales);
                            textViewcentro.setText(texto_textviewVocal);

                            //Generacion Aleatoria de numeros imagenes y sonidos
                            randomIntListaPalabras = (new Random().nextInt(ListaSonidosParejasI.size()));

                            if (randomIntListaPalabras % 2 == 0) {

                                //Obtengo de la lista de sonidos, el sonido con la posicon random generada
                                numero_sonidopalabra1 = ListaSonidosParejasI.get(randomIntListaPalabras);
                                numero_sonidopalabra2 = ListaSonidosParejasI.get(randomIntListaPalabras + 1);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasI.get(randomIntListaPalabras);
                                texto_textviewPalabra2 = ListaTextviewsParejasI.get(randomIntListaPalabras + 1);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidopalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidopalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido

                            } else {

                                //Obtengo de la lista de sonidos, el sonido con la posicon random generada
                                numero_sonidopalabra1 = ListaSonidosParejasI.get(randomIntListaPalabras - 1);
                                numero_sonidopalabra2 = ListaSonidosParejasI.get(randomIntListaPalabras);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasI.get(randomIntListaPalabras - 1);
                                texto_textviewPalabra2 = ListaTextviewsParejasI.get(randomIntListaPalabras);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidopalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidopalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido
                            }
                            break;
                        case 3:
                            //configuro la vocal que va en la canasta
                            texto_textviewVocal = ListaTextviewsVocales.get(randomIntListaVocales);
                            textViewcentro.setText(texto_textviewVocal);


                            //Generacion Aleatoria de numeros imagenes y sonidos
                            randomIntListaPalabras = (new Random().nextInt(ListaSonidosParejasO.size()));

                            if (randomIntListaPalabras % 2 == 0) {

                                //Obtengo de la lista de sonidos, el sonido con la posicon random generada
                                numero_sonidopalabra1 = ListaSonidosParejasO.get(randomIntListaPalabras);
                                numero_sonidopalabra2 = ListaSonidosParejasO.get(randomIntListaPalabras + 1);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasO.get(randomIntListaPalabras);
                                texto_textviewPalabra2 = ListaTextviewsParejasO.get(randomIntListaPalabras + 1);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidopalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidopalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido

                            } else {

                                //Obtengo de la lista de sonidos, el sonido con la posicon random generada
                                numero_sonidopalabra1 = ListaSonidosParejasO.get(randomIntListaPalabras - 1);
                                numero_sonidopalabra2 = ListaSonidosParejasO.get(randomIntListaPalabras);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasO.get(randomIntListaPalabras - 1);
                                texto_textviewPalabra2 = ListaTextviewsParejasO.get(randomIntListaPalabras);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidopalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidopalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido
                            }
                            break;
                        case 4:
                            //configuro la vocal que va en la canasta
                            texto_textviewVocal = ListaTextviewsVocales.get(randomIntListaVocales);
                            textViewcentro.setText(texto_textviewVocal);
                            //Generacion Aleatoria de numeros imagenes y sonidos
                            randomIntListaPalabras = (new Random().nextInt(ListaSonidosParejasU.size()));

                            if (randomIntListaPalabras % 2 == 0) {

                                //Obtengo de la lista de sonidos, el sonido con la posicon random generada
                                numero_sonidopalabra1 = ListaSonidosParejasU.get(randomIntListaPalabras);
                                numero_sonidopalabra2 = ListaSonidosParejasU.get(randomIntListaPalabras + 1);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasU.get(randomIntListaPalabras);
                                texto_textviewPalabra2 = ListaTextviewsParejasU.get(randomIntListaPalabras + 1);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidopalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidopalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido

                            } else {

                                //Obtengo de la lista de sonidos, el sonido con la posicion random generada
                                numero_sonidopalabra1 = ListaSonidosParejasU.get(randomIntListaPalabras - 1);
                                numero_sonidopalabra2 = ListaSonidosParejasU.get(randomIntListaPalabras);
                                //obtengo el texto de la palabra correspondiente
                                texto_textviewPalabra1 = ListaTextviewsParejasU.get(randomIntListaPalabras - 1);
                                texto_textviewPalabra2 = ListaTextviewsParejasU.get(randomIntListaPalabras);

                                //Crea la instancia  del sonido de acuerdo a la posicion int
                                sonidopalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                                sonidopalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido
                            }
                            break;
                    }

                    //para que salga el texto "escuche los sonidos"
                    textViewEscuche.setVisibility(View.VISIBLE);

                    //para ocultar lOs TEXTVIEWS 1  y 2 nuevamente
                    textView1.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    textViewSeleccione.setVisibility(View.INVISIBLE);

                    //para que los textviews no sean clickeables
                    textView1.setClickable(false);
                    textView2.setClickable(false);


                    //Reproduccion aleatoria sonidos
                    reproducirSonidosAleatorio();

                    //Retardo
                    //global_retardo = (int) (Math.random() * 13000) + 11000;//genera retardo entre 3 y 5 segs
                    global_retardo = 60000;

                    //defino texto vocal en textview centro para ñuego comparar
                    textViewcentro.setText(texto_textviewVocal);


                if (i >= 13) { // just remove call backs//
                    //para parar el sonido correctamente
                    if(sonidovocal != null) {
                        sonidovocal.stop();
                        sonidovocal.release();
                        sonidovocal = null;
                    }
                    if(sonidopalabra1 != null) {
                        sonidopalabra1.stop();
                        sonidopalabra1.release();
                        sonidopalabra1 = null;
                    }
                    if(sonidopalabra2 != null) {
                        sonidopalabra2.stop();
                        sonidopalabra2.release();
                        sonidopalabra2 = null;
                    }

                    canasta.setVisibility(View.INVISIBLE);
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
                        navControllermio.navigate(R.id.action_actividad_discr_1Fragment_to_actividad_discr_1_felicitacionFragment);//pasar a otro fragment con actions

                    } else {

                        navControllermio.navigate(R.id.action_actividad_discr_1Fragment_to_actividad_discr_1_erradoFragment);//pasar a otro fragment con actions

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
                handlervocal.removeCallbacks(runnablevocal);
                handlerpalabra1.removeCallbacks(runnablepalabra1);
                handlerpalabra2.removeCallbacks(runnablepalabra2);
                handler.removeCallbacks(runnable_iniciar);
                handler.postDelayed(runnable_iniciar, retardo);
            }
        });

//FUNCION PARA EL BOTON PARAR
        parar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(sonidovocal != null) {
                    sonidovocal.stop();
                    sonidovocal.release();
                    sonidovocal = null;
                }
                if(sonidopalabra1 != null) {
                    sonidopalabra1.stop();
                    sonidopalabra1.release();
                    sonidopalabra1 = null;
                }
                if(sonidopalabra2 != null) {
                    sonidopalabra2.stop();
                    sonidopalabra2.release();
                    sonidopalabra2 = null;
                }

                //sonido = MediaPlayer.create(getActivity(),R.raw.telefono3seg);

                canasta.setVisibility(View.INVISIBLE);
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

                handlervocal.removeCallbacks(runnablevocal);
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
                navControllermio.navigate(R.id.action_actividad_discr_1Fragment_to_actividad_discr_1_instruccionesFragment); //pasar a otro fragment con actions


                handlervocal.removeCallbacks(runnablevocal);
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


    }

    private void reproducirSonidosAleatorio(){

        //Reproducir aleatoria sonidos cortos o largos
        Random rand = new Random();
        int random= rand.nextInt(2);//Numero random entre 0 1
        if(random==0){
            ReproducirSonidos1();

        }else{
            ReproducirSonidos2();

        }
    }

    private void ReproducirSonidos1(){

        handlervocal.postDelayed(runnablevocal = new Runnable() {
            @Override
            public void run() {

                //mostrar canasta animacion
                canasta.setVisibility(View.VISIBLE);
                Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                canasta.startAnimation(animacionDerecha);
                canasta.startAnimation(animacionIzquierda);

                sonidovocal.start();
            }
        }, 1000);


        sonidovocal.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                    sonidovocal.stop();
                    sonidovocal.release();
                    sonidovocal = null;

                    canasta.setVisibility(View.INVISIBLE);
                    textViewEscuche.setVisibility(View.INVISIBLE);
            }
        });

        handlerpalabra1.postDelayed(runnablepalabra1 = new Runnable() {
            @Override
            public void run() {

                sonidopalabra1.start();// play next audio file
                //mostrar textview derecha animacion
                textView1.setText(texto_textviewPalabra1);
                textView1.setBackgroundResource(R.drawable.estilo_recuadro_uno);
                textView1.setTextColor(colorRojo);
                textView1.setVisibility(View.VISIBLE);

                //Animation animacionZoomin = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
                //Animation animacionZoomout = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_out);
                Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                textView1.startAnimation(animacionDerecha);
                textView1.startAnimation(animacionIzquierda);
            }
        }, 4000);

        sonidopalabra1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidopalabra1.stop();
                sonidopalabra1.release();
                sonidopalabra1 = null;
            }
        });

        handlerpalabra2.postDelayed(runnablepalabra2 = new Runnable() {
            @Override
            public void run() {
                sonidopalabra2.start();
                //mostrar textview izquierda animacion
                textView2.setText(texto_textviewPalabra2);
                textView2.setBackgroundResource(R.drawable.estilo_recuadro_uno);
                textView2.setTextColor(colorRojo);
                textView2.setVisibility(View.VISIBLE);

                //Animation animacionZoomin = AnimationUtils.loadAnimation(getContext(),R.anim.zoom_in);
                //Animation animacionZoomout = AnimationUtils.loadAnimation(getContext(),R.anim.zoom_out);
                Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                textView2.startAnimation(animacionDerecha);
                textView2.startAnimation(animacionIzquierda);
            }
        }, 7000);

        sonidopalabra2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidopalabra2.stop();
                sonidopalabra2.release();
                sonidopalabra2 = null;
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

    private void ReproducirSonidos2(){

        handlervocal.postDelayed(runnablevocal = new Runnable() {
            @Override
            public void run() {

                //mostrar canasta animacion
                canasta.setVisibility(View.VISIBLE);
                //Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                //Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                canasta.startAnimation(animacionDerecha);
                canasta.startAnimation(animacionIzquierda);

                sonidovocal.start();
            }
        }, 1000);


        sonidovocal.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidovocal.stop();
                sonidovocal.release();
                sonidovocal = null;

                canasta.setVisibility(View.INVISIBLE);
                textViewEscuche.setVisibility(View.INVISIBLE);
            }
        });

        handlerpalabra1.postDelayed(runnablepalabra1 = new Runnable() {
            @Override
            public void run() {

                sonidopalabra2.start();// play next audio file
                //mostrar textview derecha animacion
                textView1.setText(texto_textviewPalabra2);
                textView1.setVisibility(View.VISIBLE);
                textView1.setBackgroundResource(R.drawable.estilo_recuadro_uno);
                textView1.setTextColor(colorRojo);
                //Animation animacionZoomin = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
                //Animation animacionZoomout = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_out);
                Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                textView1.startAnimation(animacionDerecha);
                textView1.startAnimation(animacionIzquierda);
            }
        }, 4000);

        sonidopalabra2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidopalabra2.stop();
                sonidopalabra2.release();
                sonidopalabra2 = null;
            }
        });

        handlerpalabra2.postDelayed(runnablepalabra2 = new Runnable() {
            @Override
            public void run() {
                sonidopalabra1.start();
                //mostrar textview izquierda animacion
                textView2.setText(texto_textviewPalabra1);
                textView2.setBackgroundResource(R.drawable.estilo_recuadro_uno);
                textView2.setTextColor(colorRojo);
                textView2.setVisibility(View.VISIBLE);
                //Animation animacionZoomin = AnimationUtils.loadAnimation(getContext(),R.anim.zoom_in);
                //Animation animacionZoomout = AnimationUtils.loadAnimation(getContext(),R.anim.zoom_out);
                Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                textView2.startAnimation(animacionDerecha);
                textView2.startAnimation(animacionIzquierda);
            }
        }, 7000);

        sonidopalabra1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidopalabra1.stop();
                sonidopalabra1.release();
                sonidopalabra1 = null;
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
        //checking whether first character of dropTarget equals first character of dropped
        if(texto_textviewVocal.charAt(0) == textView1.getText().toString().charAt(1)){
            //Acierta



            //textView1.setBackground(R.drawable.check); //imagen1 = imagencorto
            textView1.setBackgroundResource(R.drawable.estilo_recuadro_uno_aciertos);
            textView1.setTextColor(colorNegro);
            textView1.setClickable(false);

            textView2.setVisibility(View.INVISIBLE);


            //puntaje
            aciertos_actividad = aciertos_actividad +1;
            mostrarTextviewAciertos();
            iniciarRunnable();
        }else {
            //se equivoca
            textView1.setBackgroundResource(R.drawable.estilo_recuadro_uno_fallos);
            textView1.setTextColor(colorNegro);
            textView1.setClickable(false);

            textView2.setVisibility(View.INVISIBLE);//setClickable(false);
            mostrarTextviewFallaste();
            iniciarRunnable();
        }

    }

    private void seleccionarRespuesta2() {

        //checking whether first character of dropTarget equals first character of dropped
        if(texto_textviewVocal.charAt(0) == textView2.getText().toString().charAt(1)){
            //Acierta

            //textView1.setBackground(R.drawable.check); //imagen1 = imagencorto
            textView2.setBackgroundResource(R.drawable.estilo_recuadro_uno_aciertos);
            textView2.setTextColor(colorNegro);
            textView2.setClickable(false);

            textView1.setVisibility(View.INVISIBLE);

            //puntaje
            aciertos_actividad = aciertos_actividad +1;
            mostrarTextviewAciertos();
            iniciarRunnable();
        }else {
            //se equivoca
            textView2.setBackgroundResource(R.drawable.estilo_recuadro_uno_fallos);
            textView2.setTextColor(colorNegro);
            textView2.setClickable(false);

            textView1.setVisibility(View.INVISIBLE);//setClickable(false);
            mostrarTextviewFallaste();
            iniciarRunnable();
        }

    }



    private void iniciarRunnable(){
        //Iniciar runnable dependiendo las ayudas seleccionadas
        //global_retardo = (int) (Math.random() * 4000) + 2000; //genera retardo aleatoriamente entre un rango 1 - n
        //cargarPreferenciasAyudas();
        int retardo = 4000;
        handlervocal.removeCallbacks(runnablevocal);
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
        int actividadTXT = 2;
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

        handlervocal.removeCallbacks(runnablevocal);
        handlerpalabra1.removeCallbacks(runnablepalabra1);
        handlerpalabra2.removeCallbacks(runnablepalabra2);



        if(sonidovocal != null) {
            sonidovocal.stop();
            sonidovocal.release();
            sonidovocal = null;
        }
        if(sonidopalabra1 != null) {
            sonidopalabra1.stop();
            sonidopalabra1.release();
            sonidopalabra1 = null;
        }
        if(sonidopalabra2 != null) {
            sonidopalabra2.stop();
            sonidopalabra2.release();
            sonidopalabra2 = null;
        }
        super.onPause();
    }


}//final