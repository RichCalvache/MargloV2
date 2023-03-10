package com.example.marglov2.FragmentsIdentificación;

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
 * Use the {@link Actividad_ident_1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Actividad_ident_1Fragment extends Fragment {


    //Declaracion de variables
    private ImageView imagen_centro, imagen1,imagen2,imagen3;
    private Button iniciar, parar,  btn_continuar1,btn_continuar2,btn_continuar3;
    private TextView textViewAciertos, textViewInstrucc, textViewFallaste,textViewInstrucc1,textViewInstrucc2,textViewInstrucc3,textViewSeleccione,textViewEscuche;
    MediaPlayer sonido1,sonido2,sonido3, sonido_instruccion1,sonido_instruccion2;
    int i, aciertos_actividad, global_retardo;

    int randomIntLista1,randomIntLista2;
    int numero_sonido1,numero_sonido2,numero_sonido3;
    int numero_imagen01, numero_imagen02,numero_imagenRespuesta,numero_imagen1,numero_imagen2,randomImagenes;

    LottieAnimationView animacionSonido;

    String infoActividad = "¡Nada que agregar!";



    DBHelperPuntajes DB;

    //forma para el runnable
    private Handler handler_escuchar, handler_seleccionar;
    private Runnable runnable_escuchar,runnable_seleccionar;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Actividad_ident_1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Actividad_ident_1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Actividad_ident_1Fragment newInstance(String param1, String param2) {
        Actividad_ident_1Fragment fragment = new Actividad_ident_1Fragment();
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
        return inflater.inflate(R.layout.fragment_actividad_ident_1, container, false);
    }

    //creacion del onviewcreated recomendado
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        iniciar = view.findViewById(R.id.btn_iniciar);
        parar = view.findViewById(R.id.btn_parar);

        imagen_centro = view.findViewById(R.id.imagen_centro);
        imagen1 = view.findViewById(R.id.imagen1);
        imagen2 = view.findViewById(R.id.imagen2);
        imagen3 = view.findViewById(R.id.imagen3);

        btn_continuar1 = view.findViewById(R.id.btn_continuar1);
        btn_continuar2 = view.findViewById(R.id.btn_continuar2);
        btn_continuar3 = view.findViewById(R.id.btn_continuar3);

        textViewAciertos = view.findViewById(R.id.textViewAciertosid);
        textViewFallaste = view.findViewById(R.id.textViewFallasteid);
        textViewInstrucc = view.findViewById(R.id.textviewInstrucc);
        textViewInstrucc1 = view.findViewById(R.id.textviewInstrucc1);
        textViewInstrucc2 = view.findViewById(R.id.textviewInstrucc2);
        textViewInstrucc3 = view.findViewById(R.id.textviewInstrucc3);
        textViewSeleccione = view.findViewById(R.id.textviewSeleccioneid);
        textViewEscuche = view.findViewById(R.id.textViewEscucheid);

        animacionSonido = view.findViewById(R.id.animacion_sonido);

        //Lista de sonidos
        List<Integer> ListaSonidos = new ArrayList<Integer>();
        ListaSonidos.add(R.raw.alarm3seg);
        ListaSonidos.add(R.raw.ambulancia_corto);
        ListaSonidos.add(R.raw.buho);
        ListaSonidos.add(R.raw.caballo_relinchando);
        ListaSonidos.add(R.raw.cerdo);
        ListaSonidos.add(R.raw.cuy);
        ListaSonidos.add(R.raw.disparos);
        ListaSonidos.add(R.raw.flauta);
        ListaSonidos.add(R.raw.gallo);
        ListaSonidos.add(R.raw.gato_largo);
        ListaSonidos.add(R.raw.grillo);
        ListaSonidos.add(R.raw.helicopterolargo);
        ListaSonidos.add(R.raw.leon_corto);
        ListaSonidos.add(R.raw.licuadora_corto);
        ListaSonidos.add(R.raw.lluvia);
        ListaSonidos.add(R.raw.lobo_largo);
        ListaSonidos.add(R.raw.maracas);
        ListaSonidos.add(R.raw.martillo_corto);
        ListaSonidos.add(R.raw.moto_corto);
        ListaSonidos.add(R.raw.oso_corto);
        ListaSonidos.add(R.raw.pato_corto);
        ListaSonidos.add(R.raw.perro_corto);
        ListaSonidos.add(R.raw.platillos);
        ListaSonidos.add(R.raw.policia_sirena);
        ListaSonidos.add(R.raw.pollitolargo);
        ListaSonidos.add(R.raw.rana);
        ListaSonidos.add(R.raw.silbato);
        ListaSonidos.add(R.raw.tambor_corto);
        ListaSonidos.add(R.raw.tos_corto);
        ListaSonidos.add(R.raw.tren_corto);
        ListaSonidos.add(R.raw.trompetacampamento);
        ListaSonidos.add(R.raw.trueno_corto);


        //Lista de imagenes
        List<Integer> ListaImagenes = new ArrayList<Integer>();
        ListaImagenes.add(R.drawable.alarma);
        ListaImagenes.add(R.drawable.ambulancia);
        ListaImagenes.add(R.drawable.buho);
        ListaImagenes.add(R.drawable.caballo);
        ListaImagenes.add(R.drawable.cerdo);
        ListaImagenes.add(R.drawable.cuy);
        ListaImagenes.add(R.drawable.disparos);
        ListaImagenes.add(R.drawable.flauta);
        ListaImagenes.add(R.drawable.gallo);
        ListaImagenes.add(R.drawable.gato);
        ListaImagenes.add(R.drawable.grillo);
        ListaImagenes.add(R.drawable.helicoptero);
        ListaImagenes.add(R.drawable.leoncorto);
        ListaImagenes.add(R.drawable.licuadora);
        ListaImagenes.add(R.drawable.lluvia);
        ListaImagenes.add(R.drawable.lobo);
        ListaImagenes.add(R.drawable.maracas);
        ListaImagenes.add(R.drawable.martillocorto);
        ListaImagenes.add(R.drawable.moto);
        ListaImagenes.add(R.drawable.oso);
        ListaImagenes.add(R.drawable.pato);
        ListaImagenes.add(R.drawable.perro_ladrando);
        ListaImagenes.add(R.drawable.platillos);
        ListaImagenes.add(R.drawable.policia_sirena);
        ListaImagenes.add(R.drawable.pollito);
        ListaImagenes.add(R.drawable.rana);
        ListaImagenes.add(R.drawable.silbato);
        ListaImagenes.add(R.drawable.tamborlargo);
        ListaImagenes.add(R.drawable.toscorto);
        ListaImagenes.add(R.drawable.tren);
        ListaImagenes.add(R.drawable.trompeta);
        ListaImagenes.add(R.drawable.trueno_largo);

        
        //bd
        DB = new DBHelperPuntajes(getContext());

        // funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(view); //nacControllermio es el nombre de la instancia



        // Initialize a new Handler
        handler_escuchar = new Handler();
        handler_seleccionar = new Handler();


//RUNNABLE O TIMER PARA EJECUTAR PROCESO CADA CIERTO TIEMPO
        //final Handler handler = new Handler();
        //final Runnable runnable = new Runnable() {

        runnable_escuchar = new Runnable() {
            public void run() {

                //Generacion aleatoria sonidos e imagenes
                randomIntLista1 = (new Random().nextInt(ListaSonidos.size()));
                numero_sonido1 = ListaSonidos.get(randomIntLista1);
                numero_imagen01 = ListaImagenes.get(randomIntLista1);

                randomIntLista2 = (new Random().nextInt(ListaSonidos.size()));
                numero_sonido2 = ListaSonidos.get(randomIntLista2);
                numero_imagen02 = ListaImagenes.get(randomIntLista2);

                while(randomIntLista1==randomIntLista2){
                    randomIntLista2 = (new Random().nextInt(ListaSonidos.size()));
                    numero_sonido2 = ListaSonidos.get(randomIntLista2);
                    numero_imagen02 = ListaImagenes.get(randomIntLista2);
                }


                //creacion de los sonidos
                //sonido1 = MediaPlayer.create(getActivity(), numero_sonido1);
                //sonido2 = MediaPlayer.create(getActivity(), numero_sonido2);

                reproducirInstrucciones1();//al final de este proceso comienza reproducirSonidosIniciales()


            }
        };

        runnable_seleccionar = new Runnable() {
            public void run() {


                //Generacion Aleatoria de numeros imagenes y sonidos
                int randomIntListaSonidos = (new Random().nextInt(ListaSonidos.size()));

                //Obtengo de la lista de sonidos, el sonidoFrase con la posicion random generada
                numero_sonido3 = ListaSonidos.get(randomIntListaSonidos);

                //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                sonido3 = MediaPlayer.create(getActivity(), numero_sonido3);

                //Imagenes aleatorias con sonidos correspondientes
                //imagenes aleatorias (con y sin sonidoFrase)
                //Generacion Aleatoria de numeros imagenes y sonidos
                int randomIntImagen1 = (new Random().nextInt(ListaImagenes.size()));
                int randomIntImagen2 = (new Random().nextInt(ListaImagenes.size()));

                while (randomIntListaSonidos==randomIntImagen1 || randomIntListaSonidos==randomIntImagen2){
                    randomIntImagen1 = (new Random().nextInt(ListaImagenes.size()));
                    randomIntImagen2 = (new Random().nextInt(ListaImagenes.size()));
                }
                while (randomIntImagen1 == randomIntImagen2){
                    randomIntImagen1 = (new Random().nextInt(ListaImagenes.size()));
                    randomIntImagen2 = (new Random().nextInt(ListaImagenes.size()));
                }

                //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                numero_imagenRespuesta = ListaImagenes.get(randomIntListaSonidos);
                numero_imagen1 = ListaImagenes.get(randomIntImagen1);
                numero_imagen2 = ListaImagenes.get(randomIntImagen2);


                //para el textview aciertos no salga
                textViewAciertos.setVisibility(View.INVISIBLE);
                //para el textview seleeccione / correcto no salga
                textViewSeleccione.setVisibility(View.INVISIBLE);
                //para que salga el texto "escuche los sonidos"
                textViewEscuche.setVisibility(View.VISIBLE);
                textViewInstrucc1.setVisibility(View.INVISIBLE);

                //para ocultar las imagenes 1 2 y 3 nuevamente
                imagen1.setVisibility(View.INVISIBLE);
                imagen2.setVisibility(View.INVISIBLE);
                imagen3.setVisibility(View.INVISIBLE);




                //handlerVisibilidad.removeCallbacks(runnableVisibilidad);

                reproducirSonidoSeleccionar();



                //Retardo
                //global_retardo = (int) (Math.random() * 13000) + 11000;//genera retardo entre 3 y 5 segs
                global_retardo = 20000;

                if (aciertos_actividad >= 2) { // just remove call backs//
                    //para parar el sonidoFrase correctamente
                    if(sonido3 != null) {
                        sonido3.stop();
                        sonido3.release();
                        sonido3 = null;
                    }


                    imagen1.setVisibility(View.INVISIBLE);
                    imagen2.setVisibility(View.INVISIBLE);
                    imagen3.setVisibility(View.INVISIBLE);

                    animacionSonido.setVisibility(View.INVISIBLE);

                    //iniciar.setVisibility(View.INVISIBLE);
                    //parar.setVisibility(View.INVISIBLE);
                    textViewEscuche.setVisibility(View.INVISIBLE);

                    textViewInstrucc3.setVisibility(View.VISIBLE);
                    btn_continuar3.setVisibility(View.VISIBLE);




                    handler_seleccionar.removeCallbacks(this);
                    //Log.d("Runnable", "ok");

                }
                else{ // post again
                    //i++;
                    handler_seleccionar.postDelayed(this, global_retardo);//Repite el proceso cada cierto tiempo, en este caso globarl_retardo al azar
                }

            }
        };


//FUNCION PARA EL BOTON INICIAR
        iniciar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //i=0;
                //aciertos_actividad = 0;

                //defino visibilidad objetos botones y textos
                parar.setVisibility(View.VISIBLE);
                iniciar.setVisibility(View.INVISIBLE);
                textViewInstrucc.setVisibility(View.INVISIBLE);

                textViewInstrucc1.setVisibility(View.VISIBLE);
                //btn_continuar1.setVisibility(View.VISIBLE);

                //inicio el runnable despues de 1-3 segundos
                handler_escuchar.removeCallbacks(runnable_escuchar);
                handler_escuchar.postDelayed(runnable_escuchar, 2000);//inicia despues de 2 segundos

            }
        });

//FUNCION PARA EL BOTON PARAR
        parar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(sonido1 != null) {
                    sonido1.stop();
                    sonido1.release();
                    sonido1 = null;
                }
                if(sonido2 != null) {
                    sonido2.stop();
                    sonido2.release();
                    sonido2 = null;
                }
                if(sonido3 != null) {
                    sonido3.stop();
                    sonido3.release();
                    sonido3 = null;
                }
                if(sonido_instruccion1 != null) {
                    sonido_instruccion1.stop();
                    sonido_instruccion1.release();
                    sonido_instruccion1 = null;
                }
                if(sonido_instruccion2 != null) {
                    sonido_instruccion2.stop();
                    sonido_instruccion2.release();
                    sonido_instruccion2 = null;
                }

                iniciar.setVisibility(View.VISIBLE);
                textViewInstrucc.setVisibility(View.VISIBLE);

                parar.setVisibility(View.INVISIBLE);
                textViewAciertos.setVisibility(View.INVISIBLE);
                textViewFallaste.setVisibility(View.INVISIBLE);
                textViewInstrucc1.setVisibility(View.INVISIBLE);
                textViewInstrucc2.setVisibility(View.INVISIBLE);
                textViewInstrucc3.setVisibility(View.INVISIBLE);
                btn_continuar1.setVisibility(View.INVISIBLE);
                btn_continuar2.setVisibility(View.INVISIBLE);
                btn_continuar3.setVisibility(View.INVISIBLE);
                textViewEscuche.setVisibility(View.INVISIBLE);
                textViewSeleccione.setVisibility(View.INVISIBLE);

                imagen_centro.setVisibility(View.INVISIBLE);
                imagen1.setVisibility(View.INVISIBLE);
                imagen2.setVisibility(View.INVISIBLE);
                imagen3.setVisibility(View.INVISIBLE);

                animacionSonido.setVisibility(View.INVISIBLE);


                aciertos_actividad = 0;

                handler_escuchar.removeCallbacks(runnable_escuchar);
                handler_seleccionar.removeCallbacks(runnable_seleccionar);
            }
        });



//FUNCION PARA EL BOTON continuar
        btn_continuar1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(sonido_instruccion1 != null) {
                    sonido_instruccion1.stop();
                    sonido_instruccion1.release();
                    sonido_instruccion1 = null;
                }
                handler_escuchar.removeCallbacks(runnable_escuchar);
                handler_seleccionar.removeCallbacks(runnable_seleccionar);

                textViewInstrucc1.setVisibility(View.INVISIBLE);
                btn_continuar1.setVisibility(View.INVISIBLE);

                //esperar 2 segundos y pasar al siguiente runnable de seleccionar sonidos
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reproducirSonidosIniciales();//inicia el otro metodo
                    }
                }, 100);//REtardo en milisengundos

            }
        });

        btn_continuar2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(sonido_instruccion2 != null) {
                    sonido_instruccion2.stop();
                    sonido_instruccion2.release();
                    sonido_instruccion2 = null;
                }

                textViewInstrucc2.setVisibility(View.INVISIBLE);
                btn_continuar2.setVisibility(View.INVISIBLE);

                handler_escuchar.removeCallbacks(runnable_escuchar);

                handler_seleccionar.removeCallbacks(runnable_seleccionar);
                handler_seleccionar.postDelayed(runnable_seleccionar, 1000); //inicia el runnable seleccionar sonidos con 2 segundos de retardo

            }
        });

        btn_continuar3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handler_escuchar.removeCallbacks(runnable_escuchar);
                handler_seleccionar.removeCallbacks(runnable_seleccionar);
                navControllermio.navigate(R.id. action_actividad_ident_1Fragment_to_actividad_ident_1_juegoFragment);
                //pasar al fragment del juego

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
        imagen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarRespuesta3();
            }
        });



    }//onviewcreated

    private void reproducirInstrucciones1(){

        sonido_instruccion1 = MediaPlayer.create(getActivity(), R.raw.ident_1_instrucciones_1);
        sonido_instruccion1.start();

        textViewInstrucc1.setVisibility(View.VISIBLE);
        btn_continuar1.setVisibility(View.VISIBLE);

        sonido_instruccion1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonido_instruccion1.stop();
                sonido_instruccion1.release();
                sonido_instruccion1=null;

                textViewInstrucc1.setVisibility(View.INVISIBLE);
                btn_continuar1.setVisibility(View.INVISIBLE);

                //esperar 2 segundos y pasar al siguiente runnable de seleccionar sonidos
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reproducirSonidosIniciales();//inicia el otro metodo
                    }
                }, 2000);//REtardo en milisengundos



            }
        });


    }

    private void reproducirSonidosIniciales(){

        //creacion de los sonidos
        sonido1 = MediaPlayer.create(getActivity(), numero_sonido1);
        sonido2 = MediaPlayer.create(getActivity(), numero_sonido2);

        //REPRODUCCION SONIDOS
        sonido1.start();
        imagen_centro.setImageResource(numero_imagen01);

        imagen_centro.setVisibility(View.VISIBLE);
        textViewEscuche.setVisibility(View.VISIBLE);


        sonido1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonido1.stop();
                sonido1.release();
                sonido1=null;

                imagen_centro.setVisibility(View.INVISIBLE);
                textViewEscuche.setVisibility(View.INVISIBLE);


                imagen_centro.setImageResource(numero_imagen02);//cambion imagen a la del sonido 2


                //para que inicie el siguiente sonido 3 segundos despues
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        sonido2.start();// play next audio file
                        imagen_centro.setVisibility(View.VISIBLE);
                        textViewEscuche.setVisibility(View.VISIBLE);

                    }
                }, 2000);//REtardo en milisengundos

            }
        });

        sonido2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonido2.stop();
                sonido2.release();
                sonido2=null;

                imagen_centro.setVisibility(View.INVISIBLE);
                textViewEscuche.setVisibility(View.INVISIBLE);


                //para que inicie el siguiente sonido 3 segundos despues
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        reproducirInstrucciones2();//pasar a las siguientes instrucciones

                    }
                }, 2000);//REtardo en milisengundos

            }
        });



    }

    private void reproducirInstrucciones2(){

        sonido_instruccion2 = MediaPlayer.create(getActivity(), R.raw.ident_1_instrucciones_2);
        sonido_instruccion2.start();

        textViewInstrucc2.setVisibility(View.VISIBLE);
        btn_continuar2.setVisibility(View.VISIBLE);

        sonido_instruccion2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonido_instruccion2.stop();
                sonido_instruccion2.release();
                sonido_instruccion2=null;

                textViewInstrucc1.setVisibility(View.INVISIBLE);
                textViewInstrucc2.setVisibility(View.INVISIBLE);

                btn_continuar2.setVisibility(View.INVISIBLE);

                handler_seleccionar.removeCallbacks(runnable_seleccionar);
                handler_seleccionar.postDelayed(runnable_seleccionar, 2000); //inicia el runnable seleccionar sonidos con 2 segundos de retardo


            }
        });


    }


    private void reproducirSonidoSeleccionar(){
        //Reproducir sonido
        sonido3.start();
        animacionSonido.setVisibility(View.VISIBLE);


        //una vez acabado el sonidoFrase muestra las imagenes
        sonido3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonido3.stop();
                //sonido3.release();
                animacionSonido.setVisibility(View.INVISIBLE);

                mostrarImagenesAleatorio();
                // play next audio file
            }
        });
    }

    private void mostrarImagenesAleatorio() {
        //set textview "selecione imagen"


        imagen1.setBackgroundResource(R.color.white);
        imagen2.setBackgroundResource(R.color.white);
        imagen3.setBackgroundResource(R.color.white);

        imagen1.setVisibility(View.VISIBLE);
        imagen2.setVisibility(View.VISIBLE);
        imagen3.setVisibility(View.VISIBLE);

        //para activar nuevamente la opcion clickeable de las imagenes
        imagen1.setClickable(true);
        imagen2.setClickable(true);
        imagen3.setClickable(true);

        textViewSeleccione.setText("Seleccione una imagen");
        textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_azul);
        textViewSeleccione.setVisibility(View.VISIBLE);
        textViewEscuche.setVisibility(View.INVISIBLE);



        Random rand = new Random();
        //int randomImagenes= rand.nextInt(2);
        randomImagenes = rand.nextInt(3);//numero aleatorio entre 0 y 3
        switch (randomImagenes){
            case 0:
                imagen1.setImageResource(numero_imagenRespuesta); //imagen1 = imagencorto
                imagen2.setImageResource(numero_imagen1); //imagen2 = imagenlargo
                imagen3.setImageResource(numero_imagen2); //imagen2 = imagenlargo
                break;
            case 1:
                imagen1.setImageResource(numero_imagen1); //imagen1 = imagencorto
                imagen2.setImageResource(numero_imagenRespuesta); //imagen2 = imagenlargo
                imagen3.setImageResource(numero_imagen2); //imagen2 = imagenlargo
                break;
            case 2:
                imagen1.setImageResource(numero_imagen1); //imagen1 = imagencorto
                imagen2.setImageResource(numero_imagen2); //imagen2 = imagenlargo
                imagen3.setImageResource(numero_imagenRespuesta); //imagen2 = imagenlargo
                break;
        }
    }

    private void seleccionarRespuesta1() {
        if (randomImagenes == 0) {//0 muestra la imagen de la respuesta en imageview1
            //Acierta
            imagen1.setImageResource(R.drawable.check); //imagen1 = imagencorto
            //imagen1.setBackgroundResource(R.color.verde_aciertos);
            imagen1.setBackgroundResource(R.drawable.estilo_imagen_verde);
            imagen1.setClickable(false);

            imagen2.setVisibility(View.INVISIBLE);
            imagen3.setVisibility(View.INVISIBLE);

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
            imagen3.setVisibility(View.INVISIBLE);

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
            imagen3.setVisibility(View.INVISIBLE);

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
            imagen3.setVisibility(View.INVISIBLE);

            mostrarTextviewFallaste();

            //pasar al siguiente sonido
            iniciarRunnable();

        }
    }

    private void seleccionarRespuesta3() {
        if (randomImagenes == 2) {//0 muestra la imagen de la respuesta en imageview3
            //Acierta
            imagen3.setImageResource(R.drawable.check); //imagen3 = imagenRespuesta
            imagen3.setBackgroundResource(R.drawable.estilo_imagen_verde);
            imagen3.setClickable(false);

            imagen1.setVisibility(View.INVISIBLE);
            imagen2.setVisibility(View.INVISIBLE);

            //puntaje
            aciertos_actividad = aciertos_actividad + 1;
            mostrarTextviewAciertos();
            //pasar al siguiente sonido
            iniciarRunnable();

        } else{//se equivoca
            imagen3.setImageResource(R.drawable.errado); //
            imagen3.setBackgroundResource(R.drawable.estilo_imagen_rojo);
            imagen3.setClickable(false);

            imagen1.setVisibility(View.INVISIBLE);
            imagen2.setVisibility(View.INVISIBLE);

            mostrarTextviewFallaste();

            //pasar al siguiente sonido
            iniciarRunnable();

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
                imagen3.setVisibility(View.INVISIBLE);
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
                imagen3.setVisibility(View.INVISIBLE);
            }
        }, 3000);
    }

    private void iniciarRunnable(){
        //Iniciar runnable dependiendo las ayudas seleccionadas
        int retardo = 5000;

            handler_seleccionar.removeCallbacks(runnable_seleccionar);
            handler_seleccionar.postDelayed(runnable_seleccionar, retardo);
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
        String subhabilidadTXT = "Identificación";
        int puntajeTXT = aciertos_actividad;
        String infoTXT= infoActividad;//Informacion sobre la actividad (Ayudas)

        String fechaTXT;
        Calendar calendar;
        SimpleDateFormat simpleDateFormat;

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aaa");
        fechaTXT = simpleDateFormat.format(calendar.getTime()).toString();

        //insertar datos a la bd
        Boolean checkinsertpuntaje = DB.insertPuntaje(fechaTXT, actividadTXT,subhabilidadTXT,puntajeTXT, infoTXT);
        if (checkinsertpuntaje == true) {
            Toast.makeText(getContext(), "Puntaje guardado", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "Falló registro de puntaje!", Toast.LENGTH_SHORT).show();
        }

    }






    //FUNCION ON PAUSE DE LA CLASE
    @Override
    public void onPause() {
        handler_escuchar.removeCallbacks(runnable_escuchar);
        handler_seleccionar.removeCallbacks(runnable_seleccionar);

        if(sonido1 != null) {
            sonido1.stop();
            sonido1.release();
            sonido1 = null;
        }
        if(sonido2 != null) {
            sonido2.stop();
            sonido2.release();
            sonido2 = null;
        }
        if(sonido3 != null) {
            sonido3.stop();
            sonido3.release();
            sonido3 = null;
        }
        if(sonido_instruccion1 != null) {
            sonido_instruccion1.stop();
            sonido_instruccion1.release();
            sonido_instruccion1 = null;
        }
        if(sonido_instruccion2 != null) {
            sonido_instruccion2.stop();
            sonido_instruccion2.release();
            sonido_instruccion2 = null;
        }
        super.onPause();
    }


}//final