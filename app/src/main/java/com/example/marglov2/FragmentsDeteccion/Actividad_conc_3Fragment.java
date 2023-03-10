package com.example.marglov2.FragmentsDeteccion;


import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
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
 * Use the {@link Actividad_conc_3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Actividad_conc_3Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Actividad_conc_3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Actividad_conc_3Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Actividad_conc_3Fragment newInstance(String param1, String param2) {
        Actividad_conc_3Fragment fragment = new Actividad_conc_3Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    //Declaracion de variables
    private ImageView imagen1,imagen2;
    private Button iniciar, parar, opciones,instrucciones;
    private TextView textViewAciertos, textViewInstrucc, textViewPregunta, textViewSeleccione, textViewEscuche, textViewFallaste;
    MediaPlayer sonido, sonidocorto,sonidolargo,sonidoPregunta;
    int i, aciertos_actividad, global_retardo;
    int numero_sonidocorto, numero_sonidolargo,numero_imagencorto,numero_imagenlargo, randomPregunta, randomImagenes;

    LottieAnimationView animacionSonido;

    String infoActividad = "2 patrones";


    DBHelperPuntajes DB;

    //forma para el runnable
    private Handler handler;
    private Runnable myRunnable, runnable_iniciar;

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
        return inflater.inflate(R.layout.fragment_actividad_conc_3, container, false);
    }

//creacion del onviewcreated recomendado
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        imagen1 = view.findViewById(R.id.imagen1);
        imagen2 = view.findViewById(R.id.imagen2);
        iniciar = view.findViewById(R.id.btn_iniciar);
        parar = view.findViewById(R.id.btn_parar);
        opciones = view.findViewById(R.id.btn_opciones);
        instrucciones = view.findViewById(R.id.btn_instrucciones);

        //imageninstrucc = view.findViewById(R.id.imagenInstrucc);

        textViewAciertos = view.findViewById(R.id.textViewAciertosid);
        textViewFallaste = view.findViewById(R.id.textViewFallasteid);
        textViewInstrucc = view.findViewById(R.id.textviewInstrucc);
        textViewPregunta = view.findViewById(R.id.textViewPreguntaid);
        textViewSeleccione = view.findViewById(R.id.textviewSeleccioneid);
        textViewEscuche = view.findViewById(R.id.textViewEscucheid);

        animacionSonido = view.findViewById(R.id.animacion_sonido);

        //imagen instrucciones
        //imageninstrucc.setImageResource(R.drawable.icon_conciencia);


        //Lista de sonidos cortos
        List<Integer> ListaSonidosCortos = new ArrayList<Integer>();
        ListaSonidosCortos.add(R.raw.ambulancia_corto);
        ListaSonidosCortos.add(R.raw.bomba_corto);
        ListaSonidosCortos.add(R.raw.leon_corto);
        ListaSonidosCortos.add(R.raw.licuadora_corto);
        ListaSonidosCortos.add(R.raw.martillo_corto);
        ListaSonidosCortos.add(R.raw.tambor_corto);
        ListaSonidosCortos.add(R.raw.tos_corto);
        ListaSonidosCortos.add(R.raw.tren_corto);



        //Lista de sonidos largos
        List<Integer> ListaSonidosLargos = new ArrayList<Integer>();
        ListaSonidosLargos.add(R.raw.ambulancia_largo);
        ListaSonidosLargos.add(R.raw.bomba_largo);
        ListaSonidosLargos.add(R.raw.leon_largo);
        ListaSonidosLargos.add(R.raw.licuadora_largo);
        ListaSonidosLargos.add(R.raw.martillo_largo);
        ListaSonidosLargos.add(R.raw.tambor_largo);
        ListaSonidosLargos.add(R.raw.tos_largo);
        ListaSonidosLargos.add(R.raw.tren_largo);


        //Lista de imagenes cortas
        List<Integer> ListaImagenesCortos = new ArrayList<Integer>();
        ListaImagenesCortos.add(R.drawable.ambulancia);
        ListaImagenesCortos.add(R.drawable.bombacorto);
        ListaImagenesCortos.add(R.drawable.leoncorto);
        ListaImagenesCortos.add(R.drawable.licuadoralargo);
        ListaImagenesCortos.add(R.drawable.martillocorto);
        ListaImagenesCortos.add(R.drawable.tamborlargo);
        ListaImagenesCortos.add(R.drawable.toscorto);
        ListaImagenesCortos.add(R.drawable.tren);


        //Lista de imagenes largas
        List<Integer> ListaImagenesLargos = new ArrayList<Integer>();
        ListaImagenesLargos.add(R.drawable.ambulancia);
        ListaImagenesLargos.add(R.drawable.bombacorto);
        ListaImagenesLargos.add(R.drawable.leoncorto);
        ListaImagenesLargos.add(R.drawable.licuadoralargo);
        ListaImagenesLargos.add(R.drawable.martillocorto);
        ListaImagenesLargos.add(R.drawable.tamborlargo);
        ListaImagenesLargos.add(R.drawable.toscorto);
        ListaImagenesLargos.add(R.drawable.tren);



        //bd
        DB = new DBHelperPuntajes(getContext());

        // funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(view); //nacControllermio es el nombre de la instancia



        // Initialize a new Handler
        handler = new Handler();


//RUNNABLE O TIMER PARA EJECUTAR PROCESO CADA CIERTO TIEMPO
        //final Handler handler = new Handler();
        //final Runnable runnable = new Runnable() {
        runnable_iniciar = new Runnable() {
            public void run() {
                //Log.d("Runnable", "Handler is working");

                //Generacion Aleatoria de numeros imagenes y sonidos
                int randomIntListaCortos = (new Random().nextInt(ListaSonidosCortos.size()));
                int randomIntListaLargos = (new Random().nextInt(ListaSonidosLargos.size()));


                while (randomIntListaCortos == randomIntListaLargos){
                    //Generacion Aleatoria de numeros imagenes y sonidos
                    randomIntListaCortos = (new Random().nextInt(ListaSonidosCortos.size()));
                    randomIntListaLargos = (new Random().nextInt(ListaSonidosLargos.size()));
                }

                //Obtengo de la lista de sonidos, el sonido con la posicon random generada
                numero_sonidocorto = ListaSonidosCortos.get(randomIntListaCortos);
                numero_sonidolargo = ListaSonidosLargos.get(randomIntListaLargos);

                //Crea la instancia  del sonido de acuerdo a la posicion int
                sonidocorto = MediaPlayer.create(getActivity(), numero_sonidocorto);
                sonidolargo = MediaPlayer.create(getActivity(), numero_sonidolargo);


                //Imagenes aleatorias con sonidos correspondientes
                // Crea la instancia  de la imagen de acuerdo a la posicion int
                numero_imagencorto = ListaImagenesCortos.get(randomIntListaCortos);
                numero_imagenlargo = ListaImagenesLargos.get(randomIntListaLargos);

                //Set the images
                //imagen1.setImageResource(numero_imagencorto); //imagen1 = imagencorto
                //imagen2.setImageResource(numero_imagenlargo); //imagen2 = imagenlargo


                //set textview "selecione imagen"
                //textViewSeleccione.setText("Seleccione la imagen");

                //para el textview aciertos no salga
                textViewAciertos.setVisibility(View.INVISIBLE);
                //para el textview seleeccione / correcto no salga
                textViewSeleccione.setVisibility(View.INVISIBLE);
                //para que salga el texto "escuche los sonidos"
                textViewEscuche.setVisibility(View.VISIBLE);
                //para ocultar las imagenes 1  y 2 nuevamente
                imagen1.setVisibility(View.INVISIBLE);
                imagen2.setVisibility(View.INVISIBLE);


                //para activar nuevamente la opcion clickeable de las imagenes
                imagen1.setClickable(true);
                imagen2.setClickable(true);


                //Reproducir aleatoria sonidos cortos o largos
                reproducirAleatorio();


                //sonido.start();

                //Retardo
                //global_retardo = (int) (Math.random() * 13000) + 11000;//genera retardo entre 3 y 5 segs
                global_retardo = 35000;

                if (i >= 13) { // just remove call backs//
                    //para parar el sonido correctamente
                    if(sonidocorto != null) {
                        sonidocorto.stop();
                        sonidocorto.release();
                        sonidocorto = null;
                    }
                    if(sonidolargo != null) {
                        sonidolargo.stop();
                        sonidolargo.release();
                        sonidolargo = null;
                    }
                    if(sonidoPregunta != null) {
                        sonidoPregunta.stop();
                        sonidoPregunta.release();
                        sonidoPregunta = null;
                    }
                    //sonido = MediaPlayer.create(getActivity(), numero_sonido);
                    imagen1.setVisibility(View.INVISIBLE);
                    imagen2.setVisibility(View.INVISIBLE);
                    iniciar.setVisibility(View.VISIBLE);
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
                        navControllermio.navigate(R.id.action_actividad_conc_3Fragment_to_actividad_conc_3_felicitacionFragment);//pasar a otro fragment con actions

                    }
                    else{

                        navControllermio.navigate(R.id.action_actividad_conc_3Fragment_to_actividad_conc_3_erradoFragment);//pasar a otro fragment con actions

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
                opciones.setVisibility(View.INVISIBLE);

                textViewAciertos.setVisibility(View.INVISIBLE);
                textViewInstrucc.setVisibility(View.INVISIBLE);
                //imageninstrucc.setVisibility(View.INVISIBLE);
                textViewPregunta.setVisibility(View.INVISIBLE);
                instrucciones.setVisibility(View.INVISIBLE);

                //textViewEscuche.setVisibility(View.VISIBLE);

                //inicio el runnable despues de 1-3 segundos
                handler.removeCallbacks(runnable_iniciar);
                handler.postDelayed(runnable_iniciar, retardo);
            }
        });

//FUNCION PARA EL BOTON PARAR
        parar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(sonidocorto != null) {
                    sonidocorto.stop();
                    sonidocorto.release();
                    sonidocorto = null;
                }
                if(sonidolargo != null) {
                    sonidolargo.stop();
                    sonidolargo.release();
                    sonidolargo = null;
                }
                if(sonidoPregunta != null) {
                    sonidoPregunta.stop();
                    sonidoPregunta.release();
                    sonidoPregunta = null;
                }

                imagen1.setVisibility(View.INVISIBLE);
                imagen2.setVisibility(View.INVISIBLE);
                parar.setVisibility(View.INVISIBLE);
                iniciar.setVisibility(View.VISIBLE);
                //opciones.setVisibility(View.VISIBLE);
                instrucciones.setVisibility(View.VISIBLE);

                animacionSonido.setVisibility(View.INVISIBLE);

                textViewEscuche.setVisibility(View.INVISIBLE);
                textViewPregunta.setVisibility(View.INVISIBLE);
                textViewSeleccione.setVisibility(View.INVISIBLE);
                textViewAciertos.setVisibility(View.INVISIBLE);
                textViewFallaste.setVisibility(View.INVISIBLE);

                handler.removeCallbacks(runnable_iniciar);
                Log.d("Runnable", "ok");
            }
        });


//FUNCION PARA EL BOTON AYUDAS
        /*opciones.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //navControllermio.navigate(R.id.actividad_conc_3_opcionesFragment); //pasar a otro fragment
                navControllermio.navigate(R.id.action_actividad_conc_3Fragment_to_actividad_conc_3_opcionesFragment); //pasar a otro fragment con actions
                handler.removeCallbacks(runnable_iniciar);
                Log.d("Runnable", "ok");


            }
        });*/

//FUNCION PARA EL BOTON INSTRUCCIONES
        instrucciones.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //navControllermio.navigate(R.id.actividad_conc_2_ayudasFragment); //pasar a otro fragment
                navControllermio.navigate(R.id.action_actividad_conc_3Fragment_to_actividad_conc_3_instruccionesFragment); //pasar a otro fragment con actions


                handler.removeCallbacks(runnable_iniciar);
                //Log.d("Runnable", "ok");
            }
        });



//funciones on clicklistener para las imagenes
        imagen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sonidoPregunta != null) {
                    sonidoPregunta.stop();
                    sonidoPregunta.release();
                    sonidoPregunta = null;
                }

                seleccionarRespuesta1();

            }
        });
        imagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sonidoPregunta != null) {
                    sonidoPregunta.stop();
                    sonidoPregunta.release();
                    sonidoPregunta = null;
                }

                seleccionarRespuesta2();

            }
        });



    }//onviewcreated




//MÉTODOS UTILIZADOS

    //metodo para reproducir uno despues del otro
    private void reproducirCorto(){

        textViewEscuche.setVisibility(View.VISIBLE);

        //animacionSonido.setVisibility(View.VISIBLE);
        //animacionSonido.playAnimation();


        //Reproduce primero sonido corto
        sonidocorto.start();
        animacionSonido.setVisibility(View.VISIBLE);


        //reproducion de sonidos
        //sonidocorto.prepareAsync();
        sonidocorto.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidocorto.stop();
                animacionSonido.setVisibility(View.INVISIBLE);
                //sonidocorto.release();
                //sonidocorto = null;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sonidolargo.start();// play next audio file
                        animacionSonido.setVisibility(View.VISIBLE);

                    }
                }, 2000);//REtardo en milisengundos




            }
        });

        //Aqui es para que una vez acabado el segundo sonido (sonido largo) muesrtre las imagenes con la pregunta
        sonidolargo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidolargo.stop();
                animacionSonido.setVisibility(View.INVISIBLE);

                //sonidolargo.release();
                //sonidolargo = null;

                //animacionSonido.pauseAnimation();
                //animacionSonido.setVisibility(View.INVISIBLE);


                mostrarImagenesAleatorio();// play next audio file
                mostrarPregunta();
            }
        });
    }
    private void reproducirLargo(){
        //reproducion de sonidos
        textViewEscuche.setVisibility(View.VISIBLE);

        //animacionSonido.setVisibility(View.VISIBLE);
        //animacionSonido.playAnimation();

        sonidolargo.start();
        animacionSonido.setVisibility(View.VISIBLE);


        sonidolargo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidolargo.stop();
                animacionSonido.setVisibility(View.INVISIBLE);

                //sonidolargo.release();
                //sonidolargo = null;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sonidocorto.start();// play next audio file
                        animacionSonido.setVisibility(View.VISIBLE);

                    }
                }, 2000);//REtardo en milisengundos
            }

        });
        //Aqui es para que una vez acabado el segundo sonido (sonido corto) muesrtre las imagenes con la pregunta
        sonidocorto.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidocorto.stop();
                animacionSonido.setVisibility(View.INVISIBLE);

                //sonidocorto.release();
                //sonidocorto = null;

                //animacionSonido.pauseAnimation();
                //animacionSonido.setVisibility(View.INVISIBLE);

                mostrarImagenesAleatorio();
                mostrarPregunta();// play next audio file


            }
        });

    }

    private void reproducirAleatorio(){
        //Reproducir aleatoria sonidos cortos o largos
        Random rand = new Random();
        int randomCortoLArgo= rand.nextInt(2);//Numero random entre 0 1
        if(randomCortoLArgo==0){
            reproducirCorto();

        }else{
            reproducirLargo();
        }


    }

    private void mostrarImagenesAleatorio(){
        Random rand = new Random();
        //int randomImagenes= rand.nextInt(2);
        randomImagenes= rand.nextInt(2);
        if(randomImagenes==0){
            //para que muestre la imagen del corto en imageview1 y el largo en el 2
            imagen1.setVisibility(View.VISIBLE);
            imagen2.setVisibility(View.VISIBLE);
            //textViewPregunta.setVisibility(View.VISIBLE);
            //textViewSeleccione.setVisibility(View.VISIBLE);
            //textViewSeleccione.setBackgroundResource(R.color.teal_200);
            textViewEscuche.setVisibility(View.INVISIBLE);
            imagen1.setBackgroundResource(R.color.white);
            imagen2.setBackgroundResource(R.color.white);
            imagen1.setImageResource(numero_imagencorto); //imagen1 = imagencorto
            imagen2.setImageResource(numero_imagenlargo); //imagen2 = imagenlargo

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    imagen1.setVisibility(View.INVISIBLE);// do your operation here
                    imagen2.setVisibility(View.INVISIBLE);
                    textViewPregunta.setVisibility(View.INVISIBLE);
                    //textViewSeleccione.setVisibility(View.INVISIBLE);
                }
            }, 15000);//REtardo en milisengundos


        }else{
            //handler para que muestre la imagen 1 segundo y se desaparezca
            imagen1.setVisibility(View.VISIBLE);
            imagen2.setVisibility(View.VISIBLE);
            //textViewPregunta.setVisibility(View.VISIBLE);
            //textViewSeleccione.setVisibility(View.VISIBLE);
            //textViewSeleccione.setBackgroundResource(R.color.teal_200);
            textViewEscuche.setVisibility(View.INVISIBLE);
            imagen1.setBackgroundResource(R.color.white);
            imagen2.setBackgroundResource(R.color.white);
            imagen2.setImageResource(numero_imagencorto); //imagen1 = imagencorto
            imagen1.setImageResource(numero_imagenlargo); //imagen2 = imagenlargo

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    imagen1.setVisibility(View.INVISIBLE);// do your operation here
                    imagen2.setVisibility(View.INVISIBLE);
                    textViewPregunta.setVisibility(View.INVISIBLE);
                    //textViewSeleccione.setVisibility(View.INVISIBLE);
                }
            }, 15000);//REtardo en milisengundos


        }


    }



    private void mostrarPregunta(){
        Random rand = new Random();
        //int randomPregunta= rand.nextInt(2);
        randomPregunta= rand.nextInt(2);
        if(randomPregunta==0){//0 = pregunta largo
            textViewPregunta.setVisibility(View.VISIBLE);
            textViewPregunta.setText("Seleccione sonido LARGO" );
            sonidoPregunta = MediaPlayer.create(getActivity(), R.raw.seleccione_sonido_largo);
            sonidoPregunta.start();
            //textViewPregunta.setBackgroundResource(R.color.naranja);

            sonidoPregunta.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer player) {
                    sonidoPregunta.stop();
                    sonidoPregunta.release();
                    sonidoPregunta = null;
                }

            });

        }else{//pregunta corto
            textViewPregunta.setVisibility(View.VISIBLE);
            textViewPregunta.setText("Seleccione sonido CORTO" );
            sonidoPregunta = MediaPlayer.create(getActivity(), R.raw.seleccione_sonido_corto);
            sonidoPregunta.start();
            //textViewPregunta.setBackgroundResource(R.color.naranja);

            sonidoPregunta.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer player) {
                    sonidoPregunta.stop();
                    sonidoPregunta.release();
                    sonidoPregunta = null;
                }

            });
        }
    }

    private void seleccionarRespuesta1(){
        if(randomPregunta==0){//0 pregunta largo
            if(randomImagenes==0){//0 pone imagen corto en 1 y largo en 2
                //se equivoca
                imagen1.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                imagen1.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                imagen1.setClickable(false);
                imagen2.setVisibility(View.INVISIBLE);

                mostrarTextviewFallaste();
                iniciarRunnable();

            }else{//pone imagen corto en 2 y largo en 1
                //Acierta
                imagen1.setImageResource(R.drawable.check); //imagen1 = imagencorto
                imagen1.setBackgroundResource(R.drawable.estilo_imagen_verde);
                imagen2.setVisibility(View.INVISIBLE);
                imagen1.setClickable(false);

                //puntaje
                aciertos_actividad = aciertos_actividad +1;
                mostrarTextviewAciertos();
                iniciarRunnable();

            }


        }else{//pregunta corto
            if(randomImagenes==0){//0 pone imagen corto en 1 y largo en 2
                //Acierta
                imagen1.setImageResource(R.drawable.check); //imagen1 = imagencorto
                imagen1.setBackgroundResource(R.drawable.estilo_imagen_verde);
                imagen1.setClickable(false);
                imagen2.setVisibility(View.INVISIBLE);

                //puntaje
                aciertos_actividad = aciertos_actividad +1;
                mostrarTextviewAciertos();
                iniciarRunnable();

            }else{//pone imagen corto en 2 y largo en 1
                //se equivoca
                imagen1.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                imagen1.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                imagen1.setClickable(false);
                imagen2.setVisibility(View.INVISIBLE);

                mostrarTextviewFallaste();
                iniciarRunnable();

            }
        }
    }

    private void seleccionarRespuesta2(){
        if(randomPregunta==0){//0 pregunta largo
            if(randomImagenes==0){//0 pone imagen corto en 1 y largo en 2
                //Acierta
                imagen2.setImageResource(R.drawable.check); //imagen1 = imagencorto
                imagen2.setBackgroundResource(R.drawable.estilo_imagen_verde);
                imagen2.setClickable(false);
                imagen1.setVisibility(View.INVISIBLE);

                //puntaje
                aciertos_actividad = aciertos_actividad +1;
                mostrarTextviewAciertos();
                iniciarRunnable();

            }else{//pone imagen corto en 2 y largo en 1
                //se equivoca
                imagen2.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                imagen2.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                imagen2.setClickable(false);
                imagen1.setVisibility(View.INVISIBLE);

                mostrarTextviewFallaste();
                iniciarRunnable();

            }
        }else{//pregunta corto
            if(randomImagenes==0){//0 pone imagen corto en 1 y largo en 2
                //se equivoca
                imagen2.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                imagen2.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                imagen2.setClickable(false);
                imagen1.setVisibility(View.INVISIBLE);

                mostrarTextviewFallaste();
                iniciarRunnable();

            }else{//pone imagen corto en 2 y largo en 1
                //Acierta
                imagen2.setImageResource(R.drawable.check); //imagen1 = imagencorto
                imagen2.setBackgroundResource(R.drawable.estilo_imagen_verde);
                imagen2.setClickable(false);
                imagen1.setVisibility(View.INVISIBLE);

                //puntaje
                aciertos_actividad = aciertos_actividad +1;
                mostrarTextviewAciertos();
                iniciarRunnable();

            }
        }
    }


    private void iniciarRunnable(){
        //Iniciar runnable dependiendo las ayudas seleccionadas
        //global_retardo = (int) (Math.random() * 4000) + 2000; //genera retardo aleatoriamente entre un rango 1 - n
        //cargarPreferenciasAyudas();
        int retardo = 5000;
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
        int actividadTXT = 1;
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
        textViewPregunta.setVisibility(View.INVISIBLE);


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
                imagen1.setVisibility(View.INVISIBLE);// do your operation here
                imagen2.setVisibility(View.INVISIBLE);// do your operation here
            }
        }, 2000);
    }


    private void mostrarTextviewFallaste(){
        textViewEscuche.setVisibility(View.INVISIBLE);
        textViewPregunta.setVisibility(View.INVISIBLE);


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
                imagen1.setVisibility(View.INVISIBLE);// do your operation here
                imagen2.setVisibility(View.INVISIBLE);// do your operation here

            }
        }, 2000);
    }




    //FUNCION ON PAUSE DE LA CLASE
    @Override
    public void onPause() {
        handler.removeCallbacks(runnable_iniciar);

        if(sonidocorto != null) {
            sonidocorto.stop();
            sonidocorto.release();
            sonidocorto = null;
        }
        if(sonidolargo != null) {
            sonidolargo.stop();
            sonidolargo.release();
            sonidolargo = null;
        }
        if(sonidoPregunta != null) {
            sonidoPregunta.stop();
            sonidoPregunta.release();
            sonidoPregunta = null;
        }
        super.onPause();
    }
}//final
