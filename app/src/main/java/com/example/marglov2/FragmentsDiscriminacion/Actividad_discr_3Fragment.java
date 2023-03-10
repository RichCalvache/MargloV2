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
 * Use the {@link Actividad_discr_3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Actividad_discr_3Fragment extends Fragment {

    //Declaracion de variables
    private ImageView carro;
    private Button iniciar, parar, instrucciones;
    private TextView textViewcentro,textView1,textView2, textViewAciertos, textViewInstrucc, textViewPregunta, textViewSeleccione, textViewEscuche, textViewFallaste;
    MediaPlayer sonido,sonidoParejas1,sonidovocal,sonidopalabra1,sonidopalabra2;
    int i, aciertos_actividad, global_retardo;
    int numero_sonidovocal, numero_sonidoParejas1, numero_sonidopalabra,numero_sonidopalabra1, numero_sonidopalabra2,numero_textviewVocal,numero_textviewPalabra1,numero_textviewPalabra2, randomPregunta, randomImagenes;

    int randomIntListaPalabras;
    String texto_textviewPalabra1;
    String texto_textviewPalabra2;
    String texto_textviewVocal;
    String caractervocal;
    char segundo_caracter;

    int colorNegro = Color.parseColor("#FF000000");
    int colorBlanco = Color.parseColor("#F2F0EB");

    String palabra ="";

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

    public Actividad_discr_3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Actividad_discr_3Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Actividad_discr_3Fragment newInstance(String param1, String param2) {
        Actividad_discr_3Fragment fragment = new Actividad_discr_3Fragment();
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
        return inflater.inflate(R.layout.fragment_actividad_discr_3, container, false);
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

        carro = view.findViewById(R.id.carro);


        //Lista de sonidos PAREJAS VOCAL A
        List<Integer> ListaSonidosParejas1 = new ArrayList<>();
        ListaSonidosParejas1.add(R.raw.coro);
        ListaSonidosParejas1.add(R.raw.loro);
        ListaSonidosParejas1.add(R.raw.robo);
        ListaSonidosParejas1.add(R.raw.loro);
        ListaSonidosParejas1.add(R.raw.foca);
        ListaSonidosParejas1.add(R.raw.roca);
        ListaSonidosParejas1.add(R.raw.pala);
        ListaSonidosParejas1.add(R.raw.bala);
        ListaSonidosParejas1.add(R.raw.tarro);
        ListaSonidosParejas1.add(R.raw.charro);
        ListaSonidosParejas1.add(R.raw.choza);
        ListaSonidosParejas1.add(R.raw.loza);
        ListaSonidosParejas1.add(R.raw.sabe);
        ListaSonidosParejas1.add(R.raw.llave);
        ListaSonidosParejas1.add(R.raw.macho);
        ListaSonidosParejas1.add(R.raw.mayo);
        ListaSonidosParejas1.add(R.raw.hacha);
        ListaSonidosParejas1.add(R.raw.ara);
        ListaSonidosParejas1.add(R.raw.jarro);
        ListaSonidosParejas1.add(R.raw.tarro);
        ListaSonidosParejas1.add(R.raw.zorro);
        ListaSonidosParejas1.add(R.raw.chorro);
        ListaSonidosParejas1.add(R.raw.pinto);
        ListaSonidosParejas1.add(R.raw.tinto);
        ListaSonidosParejas1.add(R.raw.bota);
        ListaSonidosParejas1.add(R.raw.gota);
        ListaSonidosParejas1.add(R.raw.coro);//nuevos
        ListaSonidosParejas1.add(R.raw.toro);
        ListaSonidosParejas1.add(R.raw.tarro);
        ListaSonidosParejas1.add(R.raw.barro);
        ListaSonidosParejas1.add(R.raw.hacha);
        ListaSonidosParejas1.add(R.raw.ala);
        ListaSonidosParejas1.add(R.raw.robo);
        ListaSonidosParejas1.add(R.raw.toro);
        ListaSonidosParejas1.add(R.raw.choza);
        ListaSonidosParejas1.add(R.raw.osa);
        ListaSonidosParejas1.add(R.raw.jarro);
        ListaSonidosParejas1.add(R.raw.barro);
        ListaSonidosParejas1.add(R.raw.foca);
        ListaSonidosParejas1.add(R.raw.boca);
        ListaSonidosParejas1.add(R.raw.sabe);
        ListaSonidosParejas1.add(R.raw.cabe);
        ListaSonidosParejas1.add(R.raw.zorro);
        ListaSonidosParejas1.add(R.raw.corro);
        ListaSonidosParejas1.add(R.raw.pala);
        ListaSonidosParejas1.add(R.raw.mala);
        ListaSonidosParejas1.add(R.raw.macho);
        ListaSonidosParejas1.add(R.raw.cayo);
        ListaSonidosParejas1.add(R.raw.pinto);
        ListaSonidosParejas1.add(R.raw.quinto);
        ListaSonidosParejas1.add(R.raw.bota);
        ListaSonidosParejas1.add(R.raw.mota);

        //Lista de palabras textviews PAREJAS VOCAL A
        List<String> ListaTextviewsParejas1 = new ArrayList<>();
        ListaTextviewsParejas1.add(palabra = "coro  loro");
        ListaTextviewsParejas1.add(palabra = "coro  loro");
        ListaTextviewsParejas1.add(palabra = "robo  loro");
        ListaTextviewsParejas1.add(palabra = "robo  loro");
        ListaTextviewsParejas1.add(palabra = "foca  roca");
        ListaTextviewsParejas1.add(palabra = "foca  roca");
        ListaTextviewsParejas1.add(palabra = "pala  bala");
        ListaTextviewsParejas1.add(palabra = "pala  bala");
        ListaTextviewsParejas1.add(palabra = "tarro  charro");
        ListaTextviewsParejas1.add(palabra = "tarro  charro");
        ListaTextviewsParejas1.add(palabra = "choza  loza");
        ListaTextviewsParejas1.add(palabra = "choza  loza");
        ListaTextviewsParejas1.add(palabra = "sabe  llave");
        ListaTextviewsParejas1.add(palabra = "sabe  llave");
        ListaTextviewsParejas1.add(palabra = "macho  mayo");
        ListaTextviewsParejas1.add(palabra = "macho  mayo");
        ListaTextviewsParejas1.add(palabra = "hacha  ara");
        ListaTextviewsParejas1.add(palabra = "hacha  ara");
        ListaTextviewsParejas1.add(palabra = "jarro  tarro");
        ListaTextviewsParejas1.add(palabra = "jarro  tarro");
        ListaTextviewsParejas1.add(palabra = "zorro  chorro");
        ListaTextviewsParejas1.add(palabra = "zorro  chorro");
        ListaTextviewsParejas1.add(palabra = "pinto  tinto");
        ListaTextviewsParejas1.add(palabra = "pinto  tinto");
        ListaTextviewsParejas1.add(palabra = "bota  gota");//nuevos
        ListaTextviewsParejas1.add(palabra = "bota  gota");
        ListaTextviewsParejas1.add(palabra = "coro  toro");
        ListaTextviewsParejas1.add(palabra = "coro  toro");
        ListaTextviewsParejas1.add(palabra = "tarro  barro");
        ListaTextviewsParejas1.add(palabra = "tarro  barro");
        ListaTextviewsParejas1.add(palabra = "hacha  ala");
        ListaTextviewsParejas1.add(palabra = "hacha  ala");
        ListaTextviewsParejas1.add(palabra = "robo  toro");
        ListaTextviewsParejas1.add(palabra = "robo  toro");
        ListaTextviewsParejas1.add(palabra = "choza  osa");
        ListaTextviewsParejas1.add(palabra = "choza  osa");
        ListaTextviewsParejas1.add(palabra = "jarro  barro");
        ListaTextviewsParejas1.add(palabra = "jarro  barro");
        ListaTextviewsParejas1.add(palabra = "foca  boca");
        ListaTextviewsParejas1.add(palabra = "foca  boca");
        ListaTextviewsParejas1.add(palabra = "sabe  cabe");
        ListaTextviewsParejas1.add(palabra = "sabe  cabe");
        ListaTextviewsParejas1.add(palabra = "zorro  corro");
        ListaTextviewsParejas1.add(palabra = "zorro  corro");
        ListaTextviewsParejas1.add(palabra = "pala  mala");
        ListaTextviewsParejas1.add(palabra = "pala  mala");
        ListaTextviewsParejas1.add(palabra = "macho  cayo");
        ListaTextviewsParejas1.add(palabra = "macho  cayo");
        ListaTextviewsParejas1.add(palabra = "pinto  quinto");
        ListaTextviewsParejas1.add(palabra = "pinto  quinto");
        ListaTextviewsParejas1.add(palabra = "bota  mota");
        ListaTextviewsParejas1.add(palabra = "bota  mota");

        //Lista de palabras textviews PAREJAS VOCAL A
        List<String> ListaTextviewsParejas2 = new ArrayList<>();
        ListaTextviewsParejas2.add(palabra = "coro  toro");
        ListaTextviewsParejas2.add(palabra = "coro  toro");
        ListaTextviewsParejas2.add(palabra = "robo  toro");
        ListaTextviewsParejas2.add(palabra = "robo  toro");
        ListaTextviewsParejas2.add(palabra = "foca  boca");
        ListaTextviewsParejas2.add(palabra = "foca  boca");
        ListaTextviewsParejas2.add(palabra = "pala  mala");
        ListaTextviewsParejas2.add(palabra = "pala  mala");
        ListaTextviewsParejas2.add(palabra = "tarro  barro");
        ListaTextviewsParejas2.add(palabra = "tarro  barro");
        ListaTextviewsParejas2.add(palabra = "choza  osa");
        ListaTextviewsParejas2.add(palabra = "choza  osa");
        ListaTextviewsParejas2.add(palabra = "sabe  cabe");
        ListaTextviewsParejas2.add(palabra = "sabe  cabe");
        ListaTextviewsParejas2.add(palabra = "macho  cayo");
        ListaTextviewsParejas2.add(palabra = "macho  cayo");
        ListaTextviewsParejas2.add(palabra = "hacha  ala");
        ListaTextviewsParejas2.add(palabra = "hacha  ala");
        ListaTextviewsParejas2.add(palabra = "jarro  barro");
        ListaTextviewsParejas2.add(palabra = "jarro  barro");
        ListaTextviewsParejas2.add(palabra = "zorro  corro");
        ListaTextviewsParejas2.add(palabra = "zorro  corro");
        ListaTextviewsParejas2.add(palabra = "pinto  quinto");
        ListaTextviewsParejas2.add(palabra = "pinto  quinto");
        ListaTextviewsParejas2.add(palabra = "bota  mota");
        ListaTextviewsParejas2.add(palabra = "bota  mota");
        ListaTextviewsParejas2.add(palabra = "coro  loro");
        ListaTextviewsParejas2.add(palabra = "coro  loro");
        ListaTextviewsParejas2.add(palabra = "tarro  charro");
        ListaTextviewsParejas2.add(palabra = "tarro  charro");
        ListaTextviewsParejas2.add(palabra = "hacha  ara");
        ListaTextviewsParejas2.add(palabra = "hacha  ara");
        ListaTextviewsParejas2.add(palabra = "robo  loro");
        ListaTextviewsParejas2.add(palabra = "robo  loro");
        ListaTextviewsParejas2.add(palabra = "choza  loza");
        ListaTextviewsParejas2.add(palabra = "choza  loza");
        ListaTextviewsParejas2.add(palabra = "jarro  tarro");
        ListaTextviewsParejas2.add(palabra = "jarro  tarro");
        ListaTextviewsParejas2.add(palabra = "foca  roca");
        ListaTextviewsParejas2.add(palabra = "foca  roca");
        ListaTextviewsParejas2.add(palabra = "sabe  llave");
        ListaTextviewsParejas2.add(palabra = "sabe  llave");
        ListaTextviewsParejas2.add(palabra = "zorro  chorro");
        ListaTextviewsParejas2.add(palabra = "zorro  chorro");
        ListaTextviewsParejas2.add(palabra = "pala  bala");
        ListaTextviewsParejas2.add(palabra = "pala  bala");
        ListaTextviewsParejas2.add(palabra = "macho  mayo");
        ListaTextviewsParejas2.add(palabra = "macho  mayo");
        ListaTextviewsParejas2.add(palabra = "pinto  tinto");
        ListaTextviewsParejas2.add(palabra = "pinto  tinto");
        ListaTextviewsParejas2.add(palabra = "bota  gota");
        ListaTextviewsParejas2.add(palabra = "bota  gota");

/*
        //Lista de palabras textviews PAREJAS VOCAL A
        List<String> ListaTextviewsParejas1 = new ArrayList<>();
        ListaTextviewsParejas1.add(palabra = "coro");
        ListaTextviewsParejas1.add(palabra = "loro");
        ListaTextviewsParejas1.add(palabra = "robo");
        ListaTextviewsParejas1.add(palabra = "loro");
        ListaTextviewsParejas1.add(palabra = "foca");
        ListaTextviewsParejas1.add(palabra = "roca");
        ListaTextviewsParejas1.add(palabra = "pala");
        ListaTextviewsParejas1.add(palabra = "bala");
        ListaTextviewsParejas1.add(palabra = "tarro");
        ListaTextviewsParejas1.add(palabra = "charro");
        ListaTextviewsParejas1.add(palabra = "choza");
        ListaTextviewsParejas1.add(palabra = "loza");
        ListaTextviewsParejas1.add(palabra = "sabe");
        ListaTextviewsParejas1.add(palabra = "llave");
        ListaTextviewsParejas1.add(palabra = "macho");
        ListaTextviewsParejas1.add(palabra = "mayo");
        ListaTextviewsParejas1.add(palabra = "hacha");
        ListaTextviewsParejas1.add(palabra = "ara");
        ListaTextviewsParejas1.add(palabra = "jarro");
        ListaTextviewsParejas1.add(palabra = "tarro");
        ListaTextviewsParejas1.add(palabra = "zorro");
        ListaTextviewsParejas1.add(palabra = "chorro");
        ListaTextviewsParejas1.add(palabra = "pinto");
        ListaTextviewsParejas1.add(palabra = "tinto");
        ListaTextviewsParejas1.add(palabra = "bota");
        ListaTextviewsParejas1.add(palabra = "gota");*/


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
                //int randomIntListaSonidosParejas1 = (new Random().nextInt(ListaSonidosParejas1.size()));
                //int randomIntListaLargos = (new Random().nextInt(ListaSonidosCortos.size()));

                //Obtengo de la lista de sonidos, el sonido con la posicon random generada
                //numero_sonidoParejas1 = ListaSonidosParejas1.get(randomIntListaSonidosParejas1);
                //numero_sonidolargo = ListaSonidosLargos.get(randomIntListaLargos);

                //Crea la instancia  del sonido de acuerdo a la posicion int
                //sonidoParejas1 = MediaPlayer.create(getActivity(), numero_sonidoParejas1);
                //sonidolargo = MediaPlayer.create(getActivity(), numero_sonidolargo);

                //Generacion Aleatoria de numeros imagenes y sonidos
                randomIntListaPalabras = (new Random().nextInt(ListaSonidosParejas1.size()));

                if (randomIntListaPalabras % 2 == 0) {

                    //Obtengo de la lista de sonidos, el sonido con la posicon random generada
                    numero_sonidopalabra1 = ListaSonidosParejas1.get(randomIntListaPalabras);
                    numero_sonidopalabra2 = ListaSonidosParejas1.get(randomIntListaPalabras + 1);
                    //Crea la instancia  del sonido de acuerdo a la posicion int
                    sonidopalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                    sonidopalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido

                    //asignacion aleatoria textviews izq o der
                    Random rand = new Random();
                    int random= rand.nextInt(2);//Numero random entre 0 1
                    if(random==0){
                        //obtengo el texto de la palabra correspondiente
                        texto_textviewPalabra1 = ListaTextviewsParejas1.get(randomIntListaPalabras);
                        texto_textviewPalabra2 = ListaTextviewsParejas2.get(randomIntListaPalabras);
                        //defino texto vocal en textview centro para ñuego comparar
                        textViewcentro.setText(texto_textviewPalabra1);
                    }else{
                        //obtengo el texto de la palabra correspondiente
                        texto_textviewPalabra2 = ListaTextviewsParejas1.get(randomIntListaPalabras);
                        texto_textviewPalabra1 = ListaTextviewsParejas2.get(randomIntListaPalabras);
                        //defino texto vocal en textview centro para ñuego comparar
                        textViewcentro.setText(texto_textviewPalabra2);
                    }


                } else {

                    //Obtengo de la lista de sonidos, el sonido con la posicon random generada
                    numero_sonidopalabra1 = ListaSonidosParejas1.get(randomIntListaPalabras - 1);
                    numero_sonidopalabra2 = ListaSonidosParejas1.get(randomIntListaPalabras);
                    //Crea la instancia  del sonido de acuerdo a la posicion int
                    sonidopalabra1 = MediaPlayer.create(getActivity(), numero_sonidopalabra1);
                    sonidopalabra2 = MediaPlayer.create(getActivity(), numero_sonidopalabra2);//pareja de sonido

                    //asignacion aleatoria textviews izq o der
                    Random rand = new Random();
                    int random= rand.nextInt(2);//Numero random entre 0 1
                    if(random==0){
                        //obtengo el texto de la palabra correspondiente
                        texto_textviewPalabra1 = ListaTextviewsParejas1.get(randomIntListaPalabras);
                        texto_textviewPalabra2 = ListaTextviewsParejas2.get(randomIntListaPalabras);
                        //defino texto vocal en textview centro para ñuego comparar
                        textViewcentro.setText(texto_textviewPalabra1);
                    }else{
                        //obtengo el texto de la palabra correspondiente
                        texto_textviewPalabra2 = ListaTextviewsParejas1.get(randomIntListaPalabras);
                        texto_textviewPalabra1 = ListaTextviewsParejas2.get(randomIntListaPalabras);
                        textViewcentro.setText(texto_textviewPalabra2);

                    }

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
                reproducirSonidos();

                //Retardo
                //global_retardo = (int) (Math.random() * 13000) + 11000;//genera retardo entre 3 y 5 segs
                global_retardo = 60000;

                //defino texto vocal en textview centro para ñuego comparar
                //textViewcentro.setText(texto_textviewVocal);


                if (i >= 13) { // just remove call backs//
                    //para parar el sonido correctamente

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

                    carro.setVisibility(View.INVISIBLE);
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
                        navControllermio.navigate(R.id.action_actividad_discr_3Fragment_to_actividad_discr_3_felicitacionFragment);//pasar a otro fragment con actions

                    } else {

                        navControllermio.navigate(R.id.action_actividad_discr_3Fragment_to_actividad_discr_3_erradoFragment);//pasar a otro fragment con actions

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

                carro.setVisibility(View.INVISIBLE);
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
                navControllermio.navigate(R.id.action_actividad_discr_3Fragment_to_actividad_discr_3_instruccionesFragment); //pasar a otro fragment con actions


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



    }//onviewcreated


    private void reproducirSonidos(){

        handlervocal.postDelayed(runnablevocal = new Runnable() {
            @Override
            public void run() {

                //mostrar carro animacion
                carro.setVisibility(View.VISIBLE);
                Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                carro.startAnimation(animacionDerecha);
                carro.startAnimation(animacionIzquierda);

                sonidopalabra1.start();
            }
        }, 1000);


        sonidopalabra1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidopalabra1.stop();
                sonidopalabra1.release();
                sonidopalabra1 = null;

                sonidopalabra2.start();

                //mostrar carro animacion
                carro.setVisibility(View.VISIBLE);
                Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                carro.startAnimation(animacionDerecha);
                carro.startAnimation(animacionIzquierda);

            }
        });

        sonidopalabra2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidopalabra2.stop();
                sonidopalabra2.release();
                sonidopalabra2 = null;
                textViewEscuche.setVisibility(View.INVISIBLE);
                carro.setVisibility(View.INVISIBLE);

                handlerpalabra1.postDelayed(runnablepalabra1 = new Runnable() {
                    @Override
                    public void run() {
                        //mostrar textview derecha animacion
                        textView1.setText(texto_textviewPalabra1);
                        textView1.setBackgroundResource(R.drawable.estilo_recuadro_tres);
                        textView1.setTextColor(colorBlanco);
                        textView1.setVisibility(View.VISIBLE);
                        Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                        Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                        textView1.startAnimation(animacionDerecha);
                        textView1.startAnimation(animacionIzquierda);

                    }
                }, 1000);

                handlerpalabra2.postDelayed(runnablepalabra2 = new Runnable() {
                    @Override
                    public void run() {
                        //mostrar textview izquierda animacion
                        textView2.setText(texto_textviewPalabra2);
                        textView2.setBackgroundResource(R.drawable.estilo_recuadro_tres);
                        textView2.setTextColor(colorBlanco);
                        textView2.setVisibility(View.VISIBLE);
                        Animation animacionDerecha = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_derecha);
                        Animation animacionIzquierda = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_izquierda);
                        textView2.startAnimation(animacionDerecha);
                        textView2.startAnimation(animacionIzquierda);

/*
                        textViewSeleccione.setText("Seleccione una palabra");
                        textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_azul);
                        textViewSeleccione.setVisibility(View.VISIBLE);
*/


                    }
                }, 2500);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        textViewSeleccione.setText("Seleccione la respuesta");
                        textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_azul);
                        textViewSeleccione.setVisibility(View.VISIBLE);

                        //para que los textviews solo sean clickeables hasta que se acaba el ultimo sonido
                        textView1.setClickable(true);
                        textView2.setClickable(true);

                    }
                }, 4500);//REtardo en milisengundos

            }
        });
    }

    private void seleccionarRespuesta1() {

        //obtengo texto del textview seleccionado
        String textoPalabra = textView1.getText().toString();
        String textoCarro = textViewcentro.getText().toString();
        //quito tildes de las palabras
        //textoPalabra = Normalizer.normalize(textoPalabra, Normalizer.Form.NFKD);
        //textoPalabra = textoPalabra.replaceAll("\\p{M}","");

        boolean boolPalabra = textoPalabra.contains(textoCarro);

        //checking whether first character of dropTarget equals first character of dropped
        //if(boolPalabra){
        if(textoCarro.equals(textoPalabra)){
            //Acierta

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

        //obtengo texto del textview seleccionado
        String textoPalabra = textView2.getText().toString();
        String textoCarro = textViewcentro.getText().toString();
        //quito tildes de las palabras
        //textoPalabra = Normalizer.normalize(textoPalabra, Normalizer.Form.NFKD);
        //textoPalabra = textoPalabra.replaceAll("\\p{M}","");

        boolean boolPalabra = textoPalabra.contains(textoCarro);

        //checking whether first character of dropTarget equals first character of dropped
        //if(boolPalabra){
        if(textoCarro.equals(textoPalabra)){
            //Acierta

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
        int retardo = 4500;
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
        int actividadTXT = 4;
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

