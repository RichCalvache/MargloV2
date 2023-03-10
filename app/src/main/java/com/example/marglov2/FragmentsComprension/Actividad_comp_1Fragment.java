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
 * Use the {@link Actividad_comp_1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Actividad_comp_1Fragment extends Fragment {

    //Declaracion de variables
    private ImageView imagen1, imagen2, imagen3, imagen4;
    private Button iniciar, parar, instrucciones;
    private TextView textViewAciertos, textViewInstrucc, textViewSeleccione, textViewEscuche, textViewFallaste;
    MediaPlayer sonidoInstruccion;
    int i, aciertos_actividad, global_retardo;
    int numero_sonido, numero_imagenRespuesta,numero_imagenIncorrecta, numero_imagenComplemento1,numero_imagenComplemento2;

    int randomGrandePequeno, randomPosicionesPequenos, randomImagenes;
    int randomIntImagenRespuesta, randomIntImagenIncorrecta,randomIntImagenComplemento1,randomIntImagenComplemento2,randomIntImagenComplemento3,randomIntImagenComplemento4;

    LottieAnimationView animacionSonido;

    String infoActividad = "¡Nada que agregar!";


    DBHelperPuntajes DB;

    //forma para el runnable
    private Handler handler;
    private Runnable runnable_iniciar;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Actividad_comp_1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Actividad_comp_1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Actividad_comp_1Fragment newInstance(String param1, String param2) {
        Actividad_comp_1Fragment fragment = new Actividad_comp_1Fragment();
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
        return inflater.inflate(R.layout.fragment_actividad_comp_1, container, false);
    }


//creacion del onviewcreated recomendado
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        imagen1 = view.findViewById(R.id.imagen1);
        imagen2 = view.findViewById(R.id.imagen2);
        imagen3 = view.findViewById(R.id.imagen3);
        imagen4 = view.findViewById(R.id.imagen4);
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

        //Lista de sonidos GRANDES
        List<Integer> ListaSonidosGrandes = new ArrayList<Integer>();
        ListaSonidosGrandes.add(R.raw.seleccione_alicate_amarillo_grande);
        ListaSonidosGrandes.add(R.raw.seleccione_destornillador_amarillo_grande);
        ListaSonidosGrandes.add(R.raw.seleccione_machete_negro_grande);
        ListaSonidosGrandes.add(R.raw.seleccione_machete_rojo_grande);
        ListaSonidosGrandes.add(R.raw.seleccione_martillo_cafe_grande);
        ListaSonidosGrandes.add(R.raw.seleccione_puntilla_gris_grande);
        ListaSonidosGrandes.add(R.raw.seleccione_serrucho_amarillo_grande);

        //Lista de sonidos
        List<Integer> ListaSonidosPequenos = new ArrayList<Integer>();
        ListaSonidosPequenos.add(R.raw.seleccione_destornillador_naranja_pequeno);
        ListaSonidosPequenos.add(R.raw.seleccione_machete_rojo_pequeno);
        ListaSonidosPequenos.add(R.raw.seleccione_martillo_cafe_pequeno);
        ListaSonidosPequenos.add(R.raw.seleccione_martillo_negro_pequeno);
        ListaSonidosPequenos.add(R.raw.seleccione_metro_amarillo_pequeno);
        ListaSonidosPequenos.add(R.raw.seleccione_puntilla_gris_pequena);



        //Lista de imagenes GRANDES
        List<Integer> ListaImagenesGrandes = new ArrayList<Integer>();
        ListaImagenesGrandes.add(R.drawable.alicate_amarillo);
        ListaImagenesGrandes.add(R.drawable.destornillador_amarillo);
        ListaImagenesGrandes.add(R.drawable.machete_negro);
        ListaImagenesGrandes.add(R.drawable.machete_rojo);
        ListaImagenesGrandes.add(R.drawable.martillo_cafe);
        ListaImagenesGrandes.add(R.drawable.puntilla);
        ListaImagenesGrandes.add(R.drawable.serrucho_amarillo);
        ListaImagenesGrandes.add(R.drawable.alicate_negro);//imagenes sin sonido
        ListaImagenesGrandes.add(R.drawable.metro_naranja);
        ListaImagenesGrandes.add(R.drawable.destornillador_naranja);
        ListaImagenesGrandes.add(R.drawable.martillo_negro);
        ListaImagenesGrandes.add(R.drawable.metro_amarillo);


        //Lista de imagenes PEQUENOS
        List<Integer> ListaImagenesPequenos = new ArrayList<Integer>();
        ListaImagenesPequenos.add(R.drawable.destornillador_naranja);
        ListaImagenesPequenos.add(R.drawable.machete_rojo);
        ListaImagenesPequenos.add(R.drawable.martillo_cafe);
        ListaImagenesPequenos.add(R.drawable.martillo_negro);
        ListaImagenesPequenos.add(R.drawable.metro_amarillo);
        ListaImagenesPequenos.add(R.drawable.puntilla);
        ListaImagenesPequenos.add(R.drawable.alicate_negro);//imagenes sin sonido
        ListaImagenesPequenos.add(R.drawable.alicate_amarillo);
        ListaImagenesPequenos.add(R.drawable.metro_naranja);
        ListaImagenesPequenos.add(R.drawable.destornillador_amarillo);
        ListaImagenesPequenos.add(R.drawable.machete_negro);
        ListaImagenesPequenos.add(R.drawable.serrucho_amarillo);


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

                //DEFINO RANDOM ENTRE 0 Y 1 PARA ELEGIR ENTRE SONIDOS DE GRANDES O PEQUEÑOS
                Random rand = new Random();
                randomGrandePequeno = rand.nextInt(2);//numero aleatorio entre 0 y 1
                if(randomGrandePequeno==0){//SI ES CERO, ELIGE UN SONIDO DE LA LISTA DE GRANDES

                    //Generacion Aleatoria de numeros imagenes y sonidos
                    int randomIntSonidoRespuesta = (new Random().nextInt(ListaSonidosGrandes.size()));

                    //Obtengo de la lista de sonidos, el sonidoFrase con la posicion random generada
                    numero_sonido = ListaSonidosGrandes.get(randomIntSonidoRespuesta);

                    //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                    sonidoInstruccion = MediaPlayer.create(getActivity(), numero_sonido);

                    //Obtengo la imagen correspondiente al sonido
                    randomIntImagenRespuesta = randomIntSonidoRespuesta;

                    randomIntImagenComplemento1 = (new Random().nextInt(ListaImagenesGrandes.size()));
                    randomIntImagenComplemento2 = (new Random().nextInt(ListaImagenesGrandes.size()));

                    //para que no se repitan las imagenes
                    while (randomIntImagenComplemento1==randomIntImagenRespuesta){
                        randomIntImagenComplemento1 = (new Random().nextInt(ListaImagenesGrandes.size()));
                    }

                    while (randomIntImagenComplemento1==randomIntImagenComplemento2){
                        randomIntImagenComplemento2 = (new Random().nextInt(ListaImagenesGrandes.size()));
                    }

                    //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                    numero_imagenRespuesta = ListaImagenesGrandes.get(randomIntImagenRespuesta);
                    numero_imagenIncorrecta = ListaImagenesGrandes.get(randomIntImagenComplemento1);
                    numero_imagenComplemento1 = ListaImagenesGrandes.get(randomIntImagenRespuesta);//misma imagen pero diferente tamaño
                    numero_imagenComplemento2 = ListaImagenesGrandes.get(randomIntImagenComplemento2);


                    //Generacion Aleatoria de numeros imagenes restantes
                    /*
                    randomIntImagenIncorrecta = (new Random().nextInt(ListaImagenesGrandes.size()));
                    //para que no se repitan las imagenes
                    while (randomIntImagenRespuesta==randomIntImagenIncorrecta){
                        randomIntImagenIncorrecta = (new Random().nextInt(ListaImagenesGrandes.size()));
                    }

                    randomIntImagenComplemento1 = (new Random().nextInt(ListaImagenesPequenos.size()));
                    randomIntImagenComplemento2 = (new Random().nextInt(ListaImagenesPequenos.size()));

                    //para que no se repitan las imagenes
                    while (randomIntImagenComplemento1==randomIntImagenComplemento2){
                        //randomIntImagenComplemento1 = (new Random().nextInt(ListaImagenesPequenos.size()));
                        randomIntImagenComplemento2 = (new Random().nextInt(ListaImagenesPequenos.size()));
                    }


                    //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                    numero_imagenRespuesta = ListaImagenesGrandes.get(randomIntImagenRespuesta);
                    numero_imagenIncorrecta = ListaImagenesGrandes.get(randomIntImagenIncorrecta);
                    numero_imagenComplemento1 = ListaImagenesPequenos.get(randomIntImagenComplemento1);
                    numero_imagenComplemento2 = ListaImagenesPequenos.get(randomIntImagenComplemento2);
*/

                }else{//SI ES UNO, ELIGE UN SONIDO DE LA LISTA DE PEQUEÑOS

                    //Generacion Aleatoria de numeros imagenes y sonidos
                    int randomIntSonidoRespuesta = (new Random().nextInt(ListaSonidosPequenos.size()));

                    //Obtengo de la lista de sonidos, el sonidoFrase con la posicion random generada
                    numero_sonido = ListaSonidosPequenos.get(randomIntSonidoRespuesta);

                    //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                    sonidoInstruccion = MediaPlayer.create(getActivity(), numero_sonido);

                    //Obtengo la imagen correspondiente al sonido
                    randomIntImagenRespuesta = randomIntSonidoRespuesta;

                    randomIntImagenComplemento3 = (new Random().nextInt(ListaImagenesPequenos.size()));
                    randomIntImagenComplemento4 = (new Random().nextInt(ListaImagenesPequenos.size()));

                    //para que no se repitan las imagenes
                    while (randomIntImagenComplemento3==randomIntImagenRespuesta){
                        randomIntImagenComplemento3 = (new Random().nextInt(ListaImagenesPequenos.size()));
                    }

                    while (randomIntImagenComplemento3==randomIntImagenComplemento4){
                        randomIntImagenComplemento4 = (new Random().nextInt(ListaImagenesPequenos.size()));
                    }

                    //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                    numero_imagenRespuesta = ListaImagenesPequenos.get(randomIntImagenRespuesta);
                    numero_imagenIncorrecta = ListaImagenesPequenos.get(randomIntImagenComplemento3);//misma imagen pero diferente tamaño
                    numero_imagenComplemento1 = ListaImagenesPequenos.get(randomIntImagenRespuesta);
                    numero_imagenComplemento2 = ListaImagenesPequenos.get(randomIntImagenComplemento4);

/*
                    //Generacion Aleatoria de numeros imagenes restantes
                    randomIntImagenIncorrecta = (new Random().nextInt(ListaImagenesPequenos.size()));
                    //para que no se repitan las imagenes
                    while (randomIntImagenRespuesta==randomIntImagenIncorrecta){
                        randomIntImagenIncorrecta = (new Random().nextInt(ListaImagenesPequenos.size()));
                    }

                    randomIntImagenComplemento3 = (new Random().nextInt(ListaImagenesGrandes.size()));
                    randomIntImagenComplemento4 = (new Random().nextInt(ListaImagenesGrandes.size()));

                    //para que no se repitan las imagenes
                    while (randomIntImagenComplemento3 == randomIntImagenComplemento4){
                        //randomIntImagenComplemento3 = (new Random().nextInt(ListaImagenesGrandes.size()));
                        randomIntImagenComplemento4 = (new Random().nextInt(ListaImagenesGrandes.size()));
                    }

                    //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                    numero_imagenRespuesta = ListaImagenesPequenos.get(randomIntImagenRespuesta);
                    numero_imagenIncorrecta = ListaImagenesPequenos.get(randomIntImagenIncorrecta);
                    numero_imagenComplemento1 = ListaImagenesGrandes.get(randomIntImagenComplemento3);
                    numero_imagenComplemento2 = ListaImagenesGrandes.get(randomIntImagenComplemento4);
*/
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
                imagen3.setVisibility(View.INVISIBLE);
                imagen4.setVisibility(View.INVISIBLE);


                //para activar nuevamente la opcion clickeable de las imagenes
                imagen1.setClickable(true);
                imagen2.setClickable(true);
                imagen3.setClickable(true);
                imagen4.setClickable(true);

                //para poner nuevamente todas las imagenes del mismo tamaño
                imagen1.setScaleX(1f);
                imagen1.setScaleY(1f);
                imagen2.setScaleX(1f);
                imagen2.setScaleY(1f);
                imagen3.setScaleX(1f);
                imagen3.setScaleY(1f);
                imagen4.setScaleX(1f);
                imagen4.setScaleY(1f);


                reproducirSonidoInstruccion();



                //Retardo
                //global_retardo = (int) (Math.random() * 13000) + 11000;//genera retardo entre 3 y 5 segs
                global_retardo = 20000;

                if (i >= 2) { // just remove call backs//
                    //para parar el sonidoFrase correctamente
                    if(sonidoInstruccion != null) {
                        sonidoInstruccion.stop();
                        sonidoInstruccion.release();
                        sonidoInstruccion = null;
                    }


                    imagen1.setVisibility(View.INVISIBLE);
                    imagen2.setVisibility(View.INVISIBLE);
                    imagen3.setVisibility(View.INVISIBLE);
                    imagen4.setVisibility(View.INVISIBLE);

                    iniciar.setVisibility(View.INVISIBLE);
                    parar.setVisibility(View.INVISIBLE);
                    textViewEscuche.setVisibility(View.INVISIBLE);
                    animacionSonido.setVisibility(View.INVISIBLE);


                    handler.removeCallbacks(this);
                    //Log.d("Runnable", "ok");

                    if (aciertos_actividad >=1) {

                        //guardar puntaje en BD
                        guardarPuntajeBD();

                        //Guardar puntaje en sharedpreferences
                        guardarPreferenciasPuntaje();

                        //pasar a otro fragment
                        //navControllermio.navigate(R.id.actividad_conc_3_felicitacionFragment);
                        navControllermio.navigate(R.id.action_actividad_comp_1Fragment_to_actividad_comp_1_felicitacionFragment);//pasar a otro fragment con actions

                    }
                    else{

                        navControllermio.navigate(R.id.action_actividad_comp_1Fragment_to_actividad_comp_1_erradoFragment);//pasar a otro fragment con actions

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

                textViewInstrucc.setVisibility(View.INVISIBLE);

                //inicio el runnable despues de 1-3 segundos
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
                imagen3.setVisibility(View.INVISIBLE);
                imagen4.setVisibility(View.INVISIBLE);

                animacionSonido.setVisibility(View.INVISIBLE);

                parar.setVisibility(View.INVISIBLE);
                iniciar.setVisibility(View.VISIBLE);
                //opciones.setVisibility(View.VISIBLE);
                instrucciones.setVisibility(View.VISIBLE);

                textViewEscuche.setVisibility(View.INVISIBLE);
                textViewSeleccione.setVisibility(View.INVISIBLE);
                textViewAciertos.setVisibility(View.INVISIBLE);
                textViewFallaste.setVisibility(View.INVISIBLE);

                handler.removeCallbacks(runnable_iniciar);
                //Log.d("Runnable", "ok");
            }
        });


//FUNCION PARA EL BOTON INSTRUCCIONES
        instrucciones.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //navControllermio.navigate(R.id.actividad_conc_2_ayudasFragment); //pasar a otro fragment
                navControllermio.navigate(R.id.action_actividad_comp_1Fragment_to_actividad_comp_1_instruccionesFragment); //pasar a otro fragment con actions

                handler.removeCallbacks(runnable_iniciar);
                //Log.d("Runnable", "ok");
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
        imagen4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarRespuesta4();
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


        imagen1.setBackgroundResource(R.color.white);
        imagen2.setBackgroundResource(R.color.white);
        imagen3.setBackgroundResource(R.color.white);
        imagen4.setBackgroundResource(R.color.white);

        imagen1.setVisibility(View.VISIBLE);
        imagen2.setVisibility(View.VISIBLE);
        imagen3.setVisibility(View.VISIBLE);
        imagen4.setVisibility(View.VISIBLE);

        textViewSeleccione.setText("Seleccione una imagen");
        textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_azul);
        textViewSeleccione.setVisibility(View.VISIBLE);
        textViewEscuche.setVisibility(View.INVISIBLE);

        if (randomGrandePequeno==0){//SI ES CERO, ELIGE UN SONIDO DE LA LISTA DE GRANDES

            Random rand = new Random();
            randomPosicionesPequenos= rand.nextInt(2);//numero aleatorio entre 0 y 1
            if(randomPosicionesPequenos==0){//SI ES CERO, LAS POSICIONES DE LAS IMAGENES PEQUEÑAS SERAN 1 Y 4

                imagen1.setScaleX(0.5f);
                imagen1.setScaleY(0.5f);
                imagen4.setScaleX(0.5f);
                imagen4.setScaleY(0.5f);

                randomImagenes = rand.nextInt(2);//numero aleatorio entre 0 y 1 para definir aleatorio posicion entre 2 y 3
                if (randomImagenes==0){//SI ES CERO, LA RESPUESTA VA EN LA POSICION 2
                    imagen2.setImageResource(numero_imagenRespuesta); //imagen2 = imagenrespuesta
                    imagen1.setImageResource(numero_imagenComplemento1); //imagen1 = complemento1
                    imagen4.setImageResource(numero_imagenComplemento2); //imagen4 = complemento2
                    imagen3.setImageResource(numero_imagenIncorrecta); //imagen3 = imagenincorrecta


                }else {//SI ES UNO, LA RESPUESTA VA EN LA POSICION 3
                    imagen3.setImageResource(numero_imagenRespuesta); //imagen3 = imagenrespuesta
                    imagen1.setImageResource(numero_imagenComplemento1); //imagen1 = complemento1
                    imagen4.setImageResource(numero_imagenComplemento2); //imagen4 = complemento2
                    imagen2.setImageResource(numero_imagenIncorrecta); //imagen2 = imagenincorrecta

                }

            }else{//SI ES UNO, LAS POSICIONES DE LAS IMAGENES PEQUEÑAS SERAN 2 y 3

                imagen2.setScaleX(0.5f);
                imagen2.setScaleY(0.5f);
                imagen3.setScaleX(0.5f);
                imagen3.setScaleY(0.5f);

                randomImagenes = rand.nextInt(2);//numero aleatorio entre 0 y 1 para definir aleatorio posicion entre 1 y 4
                if (randomImagenes==0){//SI ES CERO, LA RESPUESTA VA EN LA POSICION 1
                    imagen1.setImageResource(numero_imagenRespuesta); //imagen1 = imagenrespuesta
                    imagen2.setImageResource(numero_imagenComplemento1); //imagen2 = complemento1
                    imagen3.setImageResource(numero_imagenComplemento2); //imagen3 = complemento2
                    imagen4.setImageResource(numero_imagenIncorrecta); //imagen4 = imagenincorrecta

                }else {//SI ES UNO, LA RESPUESTA VA EN LA POSICION 4
                    imagen4.setImageResource(numero_imagenRespuesta); //imagen4 = imagenrespuesta
                    imagen2.setImageResource(numero_imagenComplemento1); //imagen2 = complemento1
                    imagen3.setImageResource(numero_imagenComplemento2); //imagen3 = complemento2
                    imagen1.setImageResource(numero_imagenIncorrecta); //imagen1 = imagenincorrecta
                }

            }

        }else {//SI ES UNO, ELIGE UN SONIDO DE LA LISTA DE PEQUEÑOS
            Random rand = new Random();
            randomPosicionesPequenos= rand.nextInt(2);//numero aleatorio entre 0 y 1
            if(randomPosicionesPequenos==0){//SI ES CERO, LAS POSICIONES DE LAS IMAGENES PEQUEÑAS SERAN 1 Y 4

                imagen1.setScaleX(0.5f);
                imagen1.setScaleY(0.5f);
                imagen4.setScaleX(0.5f);
                imagen4.setScaleY(0.5f);

                randomImagenes = rand.nextInt(2);//numero aleatorio entre 0 y 1 pra definir aleatorio posicion entre 1 y 4
                if (randomImagenes==0){//SI ES CERO, LA RESPUESTA VA EN LA POSICION 1
                    imagen1.setImageResource(numero_imagenRespuesta); //imagen1 = imagenrespuesta
                    imagen2.setImageResource(numero_imagenComplemento1); //imagen2 = complemento1
                    imagen3.setImageResource(numero_imagenComplemento2); //imagen3 = complemento2
                    imagen4.setImageResource(numero_imagenIncorrecta); //imagen4 = imagenincorrecta

                }else {//SI ES UNO, LA RESPUESTA VA EN LA POSICION 4
                    imagen4.setImageResource(numero_imagenRespuesta); //imagen4 = imagenrespuesta
                    imagen2.setImageResource(numero_imagenComplemento1); //imagen2 = complemento1
                    imagen3.setImageResource(numero_imagenComplemento2); //imagen3 = complemento2
                    imagen1.setImageResource(numero_imagenIncorrecta); //imagen1 = imagenincorrecta

                }

            }else{//SI ES UNO, LAS POSICIONES DE LAS IMAGENES PEQUEÑAS SERAN 2 y 3

                imagen2.setScaleX(0.5f);
                imagen2.setScaleY(0.5f);
                imagen3.setScaleX(0.5f);
                imagen3.setScaleY(0.5f);

                randomImagenes = rand.nextInt(2);//numero aleatorio entre 0 y 1 para definir aleatorio posicion entre 2 y 3
                if (randomImagenes==0){//SI ES CERO, LA RESPUESTA VA EN LA POSICION 2
                    imagen1.setImageResource(numero_imagenComplemento1); //imagen1 =  complemento1
                    imagen2.setImageResource(numero_imagenRespuesta); //imagen2 = imagenrespuesta
                    imagen3.setImageResource(numero_imagenIncorrecta); //imagen3 = imagenincorrecta
                    imagen4.setImageResource(numero_imagenComplemento2); //imagen4 = complemento2

                }else {//SI ES UNO, LA RESPUESTA VA EN LA POSICION 3
                    imagen1.setImageResource(numero_imagenComplemento1); //imagen1 =  complemento1
                    imagen3.setImageResource(numero_imagenRespuesta); //imagen3 = imagenrespuesta
                    imagen2.setImageResource(numero_imagenIncorrecta); //imagen2 = imagenincorrecta
                    imagen4.setImageResource(numero_imagenComplemento2); //imagen4 = complemento2
                }

            }

        }


    }



    private void seleccionarRespuesta1() {//METODO PARA EL IMAGEVIEW 1

        //RANDOM ENTRE 0 Y 1 PARA ELEGIR ENTRE SONIDOS DE GRANDES O PEQUEÑOS
        if(randomGrandePequeno==0){//SI ES CERO, ELIGE UN SONIDO DE LA LISTA DE GRANDES

            //RANDOM POSICIONES PEQUENOS
            if(randomPosicionesPequenos==0){//SI ES CERO, LAS POSICIONES DE LAS IMAGENES PEQUEÑAS SERAN 1 Y 4

                //Se equivoca en ambos casos porque deberia ir una imagen grande em 1
                //RANDOM POSICION IMAGENES CORRECTA E INCORRECTA
                if (randomImagenes==0){//SI ES CERO, LA RESPUESTA VA EN LA POSICION 1
                    //SE EQUIVOCA
                    imagen1.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                    imagen1.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                    imagen1.setClickable(false);

                    imagen2.setVisibility(View.INVISIBLE);
                    imagen3.setVisibility(View.INVISIBLE);
                    imagen4.setVisibility(View.INVISIBLE);

                    mostrarTextviewFallaste();

                    //pasar al siguiente sonido
                    iniciarRunnable();

                }else {//SI ES UNO, LA RESPUESTA VA EN LA POSICION 4
                    //SE EQUIVOCA
                    imagen1.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                    imagen1.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                    imagen1.setClickable(false);

                    imagen2.setVisibility(View.INVISIBLE);
                    imagen3.setVisibility(View.INVISIBLE);
                    imagen4.setVisibility(View.INVISIBLE);

                    mostrarTextviewFallaste();

                    //pasar al siguiente sonido
                    iniciarRunnable();
                }

            }else{//SI ES UNO, LAS POSICIONES DE LAS IMAGENES PEQUEÑAS SERAN 2 y 3

                //RANDOM POSICION IMAGENES CORRECTA E INCORRECTA
                if (randomImagenes==0){//SI ES CERO, LA RESPUESTA VA EN LA POSICION 1
                    //ACIERTA
                    imagen1.setImageResource(R.drawable.check); //imagen1 = imagencorto
                    imagen1.setBackgroundResource(R.drawable.estilo_imagen_verde);
                    imagen1.setClickable(false);

                    imagen2.setVisibility(View.INVISIBLE);
                    imagen3.setVisibility(View.INVISIBLE);
                    imagen4.setVisibility(View.INVISIBLE);

                    //puntaje
                    aciertos_actividad = aciertos_actividad + 1;
                    mostrarTextviewAciertos();
                    //pasar al siguiente sonido
                    iniciarRunnable();

                }else {//SI ES UNO, LA RESPUESTA VA EN LA POSICION 4
                    //SE EQUIVOCA
                    imagen1.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                    imagen1.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                    imagen1.setClickable(false);

                    imagen2.setVisibility(View.INVISIBLE);
                    imagen3.setVisibility(View.INVISIBLE);
                    imagen4.setVisibility(View.INVISIBLE);

                    mostrarTextviewFallaste();

                    //pasar al siguiente sonido
                    iniciarRunnable();
                }

            }
            
        }else{//SI ES UNO, ELIGE UN SONIDO DE LA LISTA DE PEQUEÑOS

            //RANDOM POSICIONES PEQUENOS
            if(randomPosicionesPequenos==0){//SI ES CERO, LAS POSICIONES DE LAS IMAGENES PEQUEÑAS SERAN 1 Y 4

                //RANDOM POSICION IMAGENES CORRECTA E INCORRECTA
                if (randomImagenes==0){//SI ES CERO, LA RESPUESTA VA EN LA POSICION 1
                    //ACIERTA
                    imagen1.setImageResource(R.drawable.check); //imagen1 = imagencorto
                    imagen1.setBackgroundResource(R.drawable.estilo_imagen_verde);
                    imagen1.setClickable(false);

                    imagen2.setVisibility(View.INVISIBLE);
                    imagen3.setVisibility(View.INVISIBLE);
                    imagen4.setVisibility(View.INVISIBLE);

                    //puntaje
                    aciertos_actividad = aciertos_actividad + 1;
                    mostrarTextviewAciertos();
                    //pasar al siguiente sonido
                    iniciarRunnable();

                }else {//SI ES UNO, LA RESPUESTA VA EN LA POSICION 4
                    //SE EQUIVOCA
                    imagen1.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                    imagen1.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                    imagen1.setClickable(false);

                    imagen2.setVisibility(View.INVISIBLE);
                    imagen3.setVisibility(View.INVISIBLE);
                    imagen4.setVisibility(View.INVISIBLE);

                    mostrarTextviewFallaste();

                    //pasar al siguiente sonido
                    iniciarRunnable();
                }

            }else{//SI ES UNO, LAS POSICIONES DE LAS IMAGENES PEQUEÑAS SERAN 2 y 3

                //SE EQUIVOCA en ambos casos, ya que la imagen 1 debe ser pequeña
                imagen1.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                imagen1.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                imagen1.setClickable(false);

                imagen2.setVisibility(View.INVISIBLE);
                imagen3.setVisibility(View.INVISIBLE);
                imagen4.setVisibility(View.INVISIBLE);

                mostrarTextviewFallaste();

                //pasar al siguiente sonido
                iniciarRunnable();
            }
        }
    }

    private void seleccionarRespuesta2() {//METODO PARA EL IMAGEVIEW 2

        //RANDOM ENTRE 0 Y 1 PARA ELEGIR ENTRE SONIDOS DE GRANDES O PEQUEÑOS
        if(randomGrandePequeno==0){//SI ES CERO, ELIGE UN SONIDO DE LA LISTA DE GRANDES

            //RANDOM POSICIONES PEQUENOS
            if(randomPosicionesPequenos==0){//SI ES CERO, LAS POSICIONES DE LAS IMAGENES PEQUEÑAS SERAN 1 Y 4

                //RANDOM POSICION IMAGENES CORRECTA E INCORRECTA
                if (randomImagenes==0){//SI ES CERO, LA RESPUESTA VA EN LA POSICION 2
                    //ACIERTA
                    imagen2.setImageResource(R.drawable.check); //imagen1 = imagencorto
                    imagen2.setBackgroundResource(R.drawable.estilo_imagen_verde);
                    imagen2.setClickable(false);

                    imagen1.setVisibility(View.INVISIBLE);
                    imagen3.setVisibility(View.INVISIBLE);
                    imagen4.setVisibility(View.INVISIBLE);

                    //puntaje
                    aciertos_actividad = aciertos_actividad + 1;
                    mostrarTextviewAciertos();
                    //pasar al siguiente sonido
                    iniciarRunnable();



                }else {//SI ES UNO, LA RESPUESTA VA EN LA POSICION 3
                    //SE EQUIVOCA
                    imagen2.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                    imagen2.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                    imagen2.setClickable(false);

                    imagen1.setVisibility(View.INVISIBLE);
                    imagen3.setVisibility(View.INVISIBLE);
                    imagen4.setVisibility(View.INVISIBLE);

                    mostrarTextviewFallaste();

                    //pasar al siguiente sonido
                    iniciarRunnable();
                }

            }else{//SI ES UNO, LAS POSICIONES DE LAS IMAGENES PEQUEÑAS SERAN 2 y 3

                //SE EQUIVOCA en ambas
                imagen2.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                imagen2.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                imagen2.setClickable(false);

                imagen1.setVisibility(View.INVISIBLE);
                imagen3.setVisibility(View.INVISIBLE);
                imagen4.setVisibility(View.INVISIBLE);

                mostrarTextviewFallaste();

                //pasar al siguiente sonido
                iniciarRunnable();
                /*

                //RANDOM POSICION IMAGENES CORRECTA E INCORRECTA
                if (randomImagenes==0){//SI ES CERO, LA RESPUESTA VA EN LA POSICION 1
                    //ACIERTA
                    imagen1.setImageResource(R.drawable.check); //imagen1 = imagencorto
                    imagen1.setBackgroundResource(R.color.verde_aciertos);
                    imagen1.setClickable(false);

                    imagen2.setVisibility(View.INVISIBLE);
                    imagen3.setVisibility(View.INVISIBLE);
                    imagen4.setVisibility(View.INVISIBLE);

                    //puntaje
                    aciertos_actividad = aciertos_actividad + 1;
                    mostrarTextviewAciertos();
                    //pasar al siguiente sonido
                    iniciarRunnable();

                }else {//SI ES UNO, LA RESPUESTA VA EN LA POSICION 4
                    //SE EQUIVOCA
                    imagen1.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                    imagen1.setBackgroundResource(R.color.naranjaclarito);
                    imagen1.setClickable(false);

                    imagen2.setVisibility(View.INVISIBLE);
                    imagen3.setVisibility(View.INVISIBLE);
                    imagen4.setVisibility(View.INVISIBLE);

                    mostrarTextviewFallaste();

                    //pasar al siguiente sonido
                    iniciarRunnable();
                }*/

            }

        }else{//SI ES UNO, ELIGE UN SONIDO DE LA LISTA DE PEQUEÑOS

            //RANDOM POSICIONES PEQUENOS
            if(randomPosicionesPequenos==0){//SI ES CERO, LAS POSICIONES DE LAS IMAGENES PEQUEÑAS SERAN 1 Y 4

                //SE EQUIVOCA en ambas
                imagen2.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                imagen2.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                imagen2.setClickable(false);

                imagen1.setVisibility(View.INVISIBLE);
                imagen3.setVisibility(View.INVISIBLE);
                imagen4.setVisibility(View.INVISIBLE);

                mostrarTextviewFallaste();

                //pasar al siguiente sonido
                iniciarRunnable();

            }else{//SI ES UNO, LAS POSICIONES DE LAS IMAGENES PEQUEÑAS SERAN 2 y 3

                //RANDOM POSICION IMAGENES CORRECTA E INCORRECTA
                if (randomImagenes==0){//SI ES CERO, LA RESPUESTA VA EN LA POSICION 2
                    //ACIERTA
                    imagen2.setImageResource(R.drawable.check); //imagen1 = imagencorto
                    imagen2.setBackgroundResource(R.drawable.estilo_imagen_verde);
                    imagen2.setClickable(false);

                    imagen1.setVisibility(View.INVISIBLE);
                    imagen3.setVisibility(View.INVISIBLE);
                    imagen4.setVisibility(View.INVISIBLE);

                    //puntaje
                    aciertos_actividad = aciertos_actividad + 1;
                    mostrarTextviewAciertos();
                    //pasar al siguiente sonido
                    iniciarRunnable();



                }else {//SI ES UNO, LA RESPUESTA VA EN LA POSICION 3
                    //SE EQUIVOCA
                    imagen2.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                    imagen2.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                    imagen2.setClickable(false);

                    imagen1.setVisibility(View.INVISIBLE);
                    imagen3.setVisibility(View.INVISIBLE);
                    imagen4.setVisibility(View.INVISIBLE);

                    mostrarTextviewFallaste();

                    //pasar al siguiente sonido
                    iniciarRunnable();
                }
            }
        }
    }

    private void seleccionarRespuesta3() {//METODO PARA EL IMAGEVIEW 3

        //RANDOM ENTRE 0 Y 1 PARA ELEGIR ENTRE SONIDOS DE GRANDES O PEQUEÑOS
        if(randomGrandePequeno==0){//SI ES CERO, ELIGE UN SONIDO DE LA LISTA DE GRANDES

            //RANDOM POSICIONES PEQUENOS
            if(randomPosicionesPequenos==0){//SI ES CERO, LAS POSICIONES DE LAS IMAGENES PEQUEÑAS SERAN 1 Y 4

                //RANDOM POSICION IMAGENES CORRECTA E INCORRECTA
                if (randomImagenes==0){//SI ES CERO, LA RESPUESTA VA EN LA POSICION 2
                    //SE EQUIVOCA
                    imagen3.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                    imagen3.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                    imagen3.setClickable(false);

                    imagen1.setVisibility(View.INVISIBLE);
                    imagen2.setVisibility(View.INVISIBLE);
                    imagen4.setVisibility(View.INVISIBLE);

                    mostrarTextviewFallaste();

                    //pasar al siguiente sonido
                    iniciarRunnable();

                }else {//SI ES UNO, LA RESPUESTA VA EN LA POSICION 3
                    //ACIERTA
                    imagen3.setImageResource(R.drawable.check); //imagen1 = imagencorto
                    imagen3.setBackgroundResource(R.drawable.estilo_imagen_verde);
                    imagen3.setClickable(false);

                    imagen1.setVisibility(View.INVISIBLE);
                    imagen2.setVisibility(View.INVISIBLE);
                    imagen4.setVisibility(View.INVISIBLE);

                    //puntaje
                    aciertos_actividad = aciertos_actividad + 1;
                    mostrarTextviewAciertos();
                    //pasar al siguiente sonido
                    iniciarRunnable();
                }

            }else{//SI ES UNO, LAS POSICIONES DE LAS IMAGENES PEQUEÑAS SERAN 2 y 3

                //SE EQUIVOCA en ambas
                imagen3.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                imagen3.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                imagen3.setClickable(false);

                imagen1.setVisibility(View.INVISIBLE);
                imagen2.setVisibility(View.INVISIBLE);
                imagen4.setVisibility(View.INVISIBLE);

                mostrarTextviewFallaste();

                //pasar al siguiente sonido
                iniciarRunnable();

            }

        }else{//SI ES UNO, ELIGE UN SONIDO DE LA LISTA DE PEQUEÑOS

            //RANDOM POSICIONES PEQUENOS
            if(randomPosicionesPequenos==0){//SI ES CERO, LAS POSICIONES DE LAS IMAGENES PEQUEÑAS SERAN 1 Y 4

                //SE EQUIVOCA en ambas
                imagen3.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                imagen3.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                imagen3.setClickable(false);

                imagen1.setVisibility(View.INVISIBLE);
                imagen2.setVisibility(View.INVISIBLE);
                imagen4.setVisibility(View.INVISIBLE);

                mostrarTextviewFallaste();

                //pasar al siguiente sonido
                iniciarRunnable();

            }else{//SI ES UNO, LAS POSICIONES DE LAS IMAGENES PEQUEÑAS SERAN 2 y 3

                //RANDOM POSICION IMAGENES CORRECTA E INCORRECTA
                if (randomImagenes==0){//SI ES CERO, LA RESPUESTA VA EN LA POSICION 2
                    //SE EQUIVOCA
                    imagen3.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                    imagen3.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                    imagen3.setClickable(false);

                    imagen1.setVisibility(View.INVISIBLE);
                    imagen2.setVisibility(View.INVISIBLE);
                    imagen4.setVisibility(View.INVISIBLE);

                    mostrarTextviewFallaste();

                    //pasar al siguiente sonido
                    iniciarRunnable();

                }else {//SI ES UNO, LA RESPUESTA VA EN LA POSICION 3
                    //ACIERTA
                    imagen3.setImageResource(R.drawable.check); //imagen1 = imagencorto
                    imagen3.setBackgroundResource(R.drawable.estilo_imagen_verde);
                    imagen3.setClickable(false);

                    imagen1.setVisibility(View.INVISIBLE);
                    imagen2.setVisibility(View.INVISIBLE);
                    imagen4.setVisibility(View.INVISIBLE);

                    //puntaje
                    aciertos_actividad = aciertos_actividad + 1;
                    mostrarTextviewAciertos();
                    //pasar al siguiente sonido
                    iniciarRunnable();
                }
            }
        }
    }

    private void seleccionarRespuesta4() {//METODO PARA EL IMAGEVIEW 4

        //RANDOM ENTRE 0 Y 1 PARA ELEGIR ENTRE SONIDOS DE GRANDES O PEQUEÑOS
        if(randomGrandePequeno==0){//SI ES CERO, ELIGE UN SONIDO DE LA LISTA DE GRANDES

            //RANDOM POSICIONES PEQUENOS
            if(randomPosicionesPequenos==0){//SI ES CERO, LAS POSICIONES DE LAS IMAGENES PEQUEÑAS SERAN 1 Y 4

                //Se equivoca en ambos casos porque deberia ir una imagen grande en 4
                imagen4.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                imagen4.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                imagen4.setClickable(false);

                imagen2.setVisibility(View.INVISIBLE);
                imagen3.setVisibility(View.INVISIBLE);
                imagen1.setVisibility(View.INVISIBLE);

                mostrarTextviewFallaste();

                //pasar al siguiente sonido
                iniciarRunnable();

            }else{//SI ES UNO, LAS POSICIONES DE LAS IMAGENES PEQUEÑAS SERAN 2 y 3

                //RANDOM POSICION IMAGENES CORRECTA E INCORRECTA
                if (randomImagenes==0){//SI ES CERO, LA RESPUESTA VA EN LA POSICION 1
                    //SE EQUIVOCA
                    imagen4.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                    imagen4.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                    imagen4.setClickable(false);

                    imagen2.setVisibility(View.INVISIBLE);
                    imagen3.setVisibility(View.INVISIBLE);
                    imagen1.setVisibility(View.INVISIBLE);

                    mostrarTextviewFallaste();

                    //pasar al siguiente sonido
                    iniciarRunnable();

                }else {//SI ES UNO, LA RESPUESTA VA EN LA POSICION 4
                    //ACIERTA
                    imagen4.setImageResource(R.drawable.check); //imagen1 = imagencorto
                    imagen4.setBackgroundResource(R.drawable.estilo_imagen_verde);
                    imagen4.setClickable(false);

                    imagen2.setVisibility(View.INVISIBLE);
                    imagen3.setVisibility(View.INVISIBLE);
                    imagen1.setVisibility(View.INVISIBLE);

                    //puntaje
                    aciertos_actividad = aciertos_actividad + 1;
                    mostrarTextviewAciertos();
                    //pasar al siguiente sonido
                    iniciarRunnable();
                }
            }
        }else{//SI ES UNO, ELIGE UN SONIDO DE LA LISTA DE PEQUEÑOS

            //RANDOM POSICIONES PEQUENOS
            if(randomPosicionesPequenos==0){//SI ES CERO, LAS POSICIONES DE LAS IMAGENES PEQUEÑAS SERAN 1 Y 4

                //RANDOM POSICION IMAGENES CORRECTA E INCORRECTA
                if (randomImagenes==0){//SI ES CERO, LA RESPUESTA VA EN LA POSICION 1
                    //SE EQUIVOCA
                    imagen4.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                    imagen4.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                    imagen4.setClickable(false);

                    imagen2.setVisibility(View.INVISIBLE);
                    imagen3.setVisibility(View.INVISIBLE);
                    imagen1.setVisibility(View.INVISIBLE);

                    mostrarTextviewFallaste();

                    //pasar al siguiente sonido
                    iniciarRunnable();

                }else {//SI ES UNO, LA RESPUESTA VA EN LA POSICION 4
                    //ACIERTA
                    imagen4.setImageResource(R.drawable.check); //imagen1 = imagencorto
                    imagen4.setBackgroundResource(R.drawable.estilo_imagen_verde);
                    imagen4.setClickable(false);

                    imagen2.setVisibility(View.INVISIBLE);
                    imagen3.setVisibility(View.INVISIBLE);
                    imagen1.setVisibility(View.INVISIBLE);

                    //puntaje
                    aciertos_actividad = aciertos_actividad + 1;
                    mostrarTextviewAciertos();
                    //pasar al siguiente sonido
                    iniciarRunnable();
                }

            }else{//SI ES UNO, LAS POSICIONES DE LAS IMAGENES PEQUEÑAS SERAN 2 y 3

                //SE EQUIVOCA en ambos casos, ya que la imagen 4 debe ser pequeña
                imagen4.setImageResource(R.drawable.errado); //imagen1 = imagencorto
                imagen4.setBackgroundResource(R.drawable.estilo_imagen_rojo);
                imagen4.setClickable(false);

                imagen2.setVisibility(View.INVISIBLE);
                imagen3.setVisibility(View.INVISIBLE);
                imagen1.setVisibility(View.INVISIBLE);

                mostrarTextviewFallaste();

                //pasar al siguiente sonido
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
                imagen3.setVisibility(View.INVISIBLE);
                imagen4.setVisibility(View.INVISIBLE);
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
                imagen4.setVisibility(View.INVISIBLE);
            }
        }, 3000);
    }



    //FUNCION ON PAUSE DE LA CLASE
    @Override
    public void onPause() {
        handler.removeCallbacks(runnable_iniciar);

        if(sonidoInstruccion != null) {
            sonidoInstruccion.stop();
            sonidoInstruccion.release();
            sonidoInstruccion = null;
        }

        super.onPause();
    }

}//final
