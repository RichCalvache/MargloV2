package com.example.marglov2.FragmentsIdentificación;

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
 * Use the {@link Actividad_ident_4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Actividad_ident_4Fragment extends Fragment {

    //Declaracion de variables
    private TextView  textView1, textView2, textView3;
    private Button iniciar, parar, instrucciones;
    private TextView textViewAciertos, textViewInstrucc, textViewSeleccione, textViewEscuche, textViewFallaste;
    MediaPlayer sonidoFrase;
    int i, aciertos_actividad, global_retardo;
    int numero_sonido, numero_fraseRespuesta, numero_frase1, numero_frase2, randomFrases;

    String frase ="";

    String texto_fraseRespuesta;
    String texto_frase1;
    String texto_frase2;

    LottieAnimationView animacionSonido;


    String infoActividad = "¡Nada que agregar!";


    DBHelperPuntajes DB;

    //forma para el runnable
    private Handler handler,handlerVisibilidad;
    private Runnable myRunnable, runnable_iniciar,runnableVisibilidad;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Actividad_ident_4Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Actividad_ident_4Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Actividad_ident_4Fragment newInstance(String param1, String param2) {
        Actividad_ident_4Fragment fragment = new Actividad_ident_4Fragment();
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
        return inflater.inflate(R.layout.fragment_actividad_ident_4, container, false);
    }


    //creacion del onviewcreated recomendado
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        textView1 = view.findViewById(R.id.textView1);
        textView2 = view.findViewById(R.id.textView2);
        textView3 = view.findViewById(R.id.textView3);
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
        ListaSonidos.add(R.raw.no_quiero);
        ListaSonidos.add(R.raw.hola_como_estas);
        ListaSonidos.add(R.raw.donde_esta_bano);
        ListaSonidos.add(R.raw.que_palabra);
        ListaSonidos.add(R.raw.me_pasas_el_lapiz);
        ListaSonidos.add(R.raw.las_llaves_estan_encima);
        ListaSonidos.add(R.raw.mi_primo_se_cayo);
        ListaSonidos.add(R.raw.llama_al_medico);
        ListaSonidos.add(R.raw.mi_perro_tiene_pulgas);
        ListaSonidos.add(R.raw.no_palabra);
        ListaSonidos.add(R.raw.a_lavarse_los_dientes);
        ListaSonidos.add(R.raw.tengo_mucho_sueno);
        ListaSonidos.add(R.raw.el_gorro_es_azul);
        ListaSonidos.add(R.raw.la_ropa_esta_doblada);

        //Lista de fraeses y palabras textviews
        List<String> ListaTextviewsFrases = new ArrayList<>();
        ListaTextviewsFrases.add(frase = "No quiero");
        ListaTextviewsFrases.add(frase = "Hola, ¿cómo estás?");
        ListaTextviewsFrases.add(frase = "¿Dónde está el baño?");
        ListaTextviewsFrases.add(frase = "¿Qué?");
        ListaTextviewsFrases.add(frase = "¿Me pasas el lápiz?");
        ListaTextviewsFrases.add(frase = "Las llaves están encima de la nevera");
        ListaTextviewsFrases.add(frase = "Mi primo se cayó del árbol");
        ListaTextviewsFrases.add(frase = "Llama al médico");
        ListaTextviewsFrases.add(frase = "Mi perro tiene pulgas");
        ListaTextviewsFrases.add(frase = "No");
        ListaTextviewsFrases.add(frase = "A lavarse los dientes");
        ListaTextviewsFrases.add(frase = "Tengo mucho sueño");
        ListaTextviewsFrases.add(frase = "El gorro es de color azul");
        ListaTextviewsFrases.add(frase = "La ropa está doblada");//hasta aqui frases con sonidos
        ListaTextviewsFrases.add(frase = "Yo primero");
        ListaTextviewsFrases.add(frase = "Apaga la luz");
        ListaTextviewsFrases.add(frase = "Tengo frío");
        ListaTextviewsFrases.add(frase = "¿Hay algo de comer?");
        ListaTextviewsFrases.add(frase = "Sí");
        ListaTextviewsFrases.add(frase = "Gracias");
        ListaTextviewsFrases.add(frase = "Buenos días");
        ListaTextviewsFrases.add(frase = "Estoy enfermo");
        ListaTextviewsFrases.add(frase = "Yo");
        ListaTextviewsFrases.add(frase = "La vaca está en el corral");
        ListaTextviewsFrases.add(frase = "Deje el carro en el parqueadero");
        ListaTextviewsFrases.add(frase = "La mesa está sucia");
        ListaTextviewsFrases.add(frase = "Quiero un helado");


        //bd
        DB = new DBHelperPuntajes(getContext());

        // funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(view); //nacControllermio es el nombre de la instancia


        // Initialize a new Handler
        handler = new Handler();
        handlerVisibilidad = new Handler();



//RUNNABLE O TIMER PARA EJECUTAR PROCESO CADA CIERTO TIEMPO
        //final Handler handler = new Handler();
        //final Runnable runnable = new Runnable() {
        runnable_iniciar = new Runnable() {
            public void run() {
                //Log.d("Runnable", "Handler is working");

                //Generacion Aleatoria de numeros imagenes y sonidos
                int randomIntListaSonidos = (new Random().nextInt(ListaSonidos.size()));

                //Obtengo de la lista de sonidos, el sonidode la frase  con la posicion random generada
                numero_sonido = ListaSonidos.get(randomIntListaSonidos);

                //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                sonidoFrase = MediaPlayer.create(getActivity(), numero_sonido);

                //Imagenes aleatorias con sonidos correspondientes
                //imagenes aleatorias (con y sin sonidoFrase)
                int randomIntFrase1 = (new Random().nextInt(ListaTextviewsFrases.size()));
                int randomIntFrase2 = (new Random().nextInt(ListaTextviewsFrases.size()));

                while (randomIntListaSonidos== randomIntFrase1 || randomIntListaSonidos== randomIntFrase2){
                    randomIntFrase1 = (new Random().nextInt(ListaTextviewsFrases.size()));
                    randomIntFrase2 = (new Random().nextInt(ListaTextviewsFrases.size()));
                }
                while (randomIntFrase1 == randomIntFrase2){
                    randomIntFrase1 = (new Random().nextInt(ListaTextviewsFrases.size()));
                    randomIntFrase2 = (new Random().nextInt(ListaTextviewsFrases.size()));
                }

                //Obtengo de la lista de imagenes, las imagenes con la posicion random generada
                texto_fraseRespuesta = ListaTextviewsFrases.get(randomIntListaSonidos);
                texto_frase1 = ListaTextviewsFrases.get(randomIntFrase1);
                texto_frase2 = ListaTextviewsFrases.get(randomIntFrase2);


                //para el textview aciertos no salga
                textViewAciertos.setVisibility(View.INVISIBLE);
                //para el textview seleeccione / correcto no salga
                textViewSeleccione.setVisibility(View.INVISIBLE);
                //para que salga el texto "escuche los sonidos"
                textViewEscuche.setVisibility(View.VISIBLE);

                //para ocultar los textviews 1 2 y 3 nuevamente
                textView1.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                textView3.setVisibility(View.INVISIBLE);


                //para activar nuevamente la opcion clickeable de las imagenes
                textView1.setClickable(true);
                textView2.setClickable(true);
                textView3.setClickable(true);

                //handlerVisibilidad.removeCallbacks(runnableVisibilidad);


                reproducirSonidoInstruccion();



                //Retardo
                //global_retardo = (int) (Math.random() * 13000) + 11000;//genera retardo entre 3 y 5 segs
                global_retardo = 20000;

                if (i >= 13) { // just remove call backs//
                    //para parar el sonidoFrase correctamente
                    if(sonidoFrase != null) {
                        sonidoFrase.stop();
                        sonidoFrase.release();
                        sonidoFrase = null;
                    }


                    textView1.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    textView3.setVisibility(View.INVISIBLE);

                    iniciar.setVisibility(View.INVISIBLE);
                    parar.setVisibility(View.INVISIBLE);
                    textViewAciertos.setVisibility(View.INVISIBLE);
                    handler.removeCallbacks(this);
                    //Log.d("Runnable", "ok");

                    if (aciertos_actividad >= 10) {

                        //guardar puntaje en BD
                        guardarPuntajeBD();

                        //Guardar puntaje en sharedpreferences
                        guardarPreferenciasPuntaje();

                        //pasar a otro fragment
                        navControllermio.navigate(R.id.action_actividad_ident_4Fragment_to_actividad_ident_4_felicitacionFragment);//pasar a otro fragment con actions

                    }
                    else{

                        navControllermio.navigate(R.id.action_actividad_ident_4Fragment_to_actividad_ident_4_erradoFragment);//pasar a otro fragment con actions

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
                handler.removeCallbacks(runnable_iniciar);
                handler.postDelayed(runnable_iniciar, retardo);
            }
        });

//FUNCION PARA EL BOTON PARAR
        parar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(sonidoFrase != null) {
                    sonidoFrase.stop();
                    sonidoFrase.release();
                    sonidoFrase = null;
                }

                textView1.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                textView3.setVisibility(View.INVISIBLE);

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
                Log.d("Runnable", "ok");
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
                navControllermio.navigate(R.id.action_actividad_ident_4Fragment_to_actividad_ident_4_instruccionesFragment); //pasar a otro fragment con actions

                handler.removeCallbacks(runnable_iniciar);
                Log.d("Runnable", "ok");
            }
        });



//funciones on clicklistener para las imagenes

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
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seleccionarRespuesta3();

            }
        });

    }//onviewcreated




//MÉTODOS UTILIZADOS

    private void reproducirSonidoInstruccion(){
        //Reproducir sonido instruccion
        sonidoFrase.start();

        animacionSonido.setVisibility(View.VISIBLE);


        //una vez acabado el sonidoFrase muestra las imagenes
        sonidoFrase.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                sonidoFrase.stop();
                sonidoFrase.release();
                sonidoFrase = null;

                animacionSonido.setVisibility(View.INVISIBLE);

                mostrarFrasesAleatorio();
                // play next audio file
            }
        });
    }




    private void mostrarFrasesAleatorio() {
        //set textview "selecione imagen"


        textView1.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.VISIBLE);
        textView3.setVisibility(View.VISIBLE);

        textView1.setBackgroundResource(R.drawable.estilo_redondeado_si_y_no);
        textView2.setBackgroundResource(R.drawable.estilo_redondeado_si_y_no);
        textView3.setBackgroundResource(R.drawable.estilo_redondeado_si_y_no);


        textViewSeleccione.setText("Seleccione una frase");
        textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_azul);
        textViewSeleccione.setVisibility(View.VISIBLE);
        textViewEscuche.setVisibility(View.INVISIBLE);



        Random rand = new Random();
        //int randomImagenes= rand.nextInt(2);
        randomFrases = rand.nextInt(3);//numero aleatorio entre 0 y 3
        switch (randomFrases){
            case 0:
                textView1.setText(texto_fraseRespuesta);
                textView2.setText(texto_frase1);
                textView3.setText(texto_frase2);
                break;
            case 1:
                textView1.setText(texto_frase1);
                textView2.setText(texto_fraseRespuesta);
                textView3.setText(texto_frase2);
                break;
            case 2:
                textView1.setText(texto_frase1);
                textView2.setText(texto_frase2);
                textView3.setText(texto_fraseRespuesta);
                break;
        }
    }


    private void seleccionarRespuesta1() {
        if (randomFrases == 0) {//0 muestra la imagen de la respuesta en imageview1
            //Acierta
            textView1.setText("¡Correcto!");
            textView1.setBackgroundResource(R.drawable.estilo_redondeado_verde);
            textView1.setClickable(false);

            textView2.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);

            //puntaje
            aciertos_actividad = aciertos_actividad + 1;
            mostrarTextviewAciertos();
            //pasar al siguiente sonido
            iniciarRunnable();

        } else{//se equivoca
            textView1.setText("¡Incorrecto!");
            textView1.setBackgroundResource(R.drawable.estilo_redondeado_rojo);
            textView1.setClickable(false);

            textView2.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);

            mostrarTextviewFallaste();

            //pasar al siguiente sonido
            iniciarRunnable();

        }
    }

    private void seleccionarRespuesta2() {
        if (randomFrases == 1) {//0 muestra la imagen de la respuesta en imageview2
            //Acierta
            textView2.setText("¡Correcto!");
            textView2.setBackgroundResource(R.drawable.estilo_redondeado_verde);
            textView2.setClickable(false);

            textView1.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);

            //puntaje
            aciertos_actividad = aciertos_actividad + 1;
            mostrarTextviewAciertos();
            //pasar al siguiente sonido
            iniciarRunnable();

        } else{//se equivoca
            textView2.setText("¡Incorrecto!");
            textView2.setBackgroundResource(R.drawable.estilo_redondeado_rojo);
            textView2.setClickable(false);

            textView1.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);

            mostrarTextviewFallaste();

            //pasar al siguiente sonido
            iniciarRunnable();
        }
    }

    private void seleccionarRespuesta3() {
        if (randomFrases == 2) {//0 muestra la imagen de la respuesta en imageview3
            //Acierta
            textView3.setText("¡Correcto!");
            textView3.setBackgroundResource(R.drawable.estilo_redondeado_verde);
            textView3.setClickable(false);

            textView1.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.INVISIBLE);

            //puntaje
            aciertos_actividad = aciertos_actividad + 1;
            mostrarTextviewAciertos();
            //pasar al siguiente sonido
            iniciarRunnable();

        } else{//se equivoca
            textView3.setText("¡Incorrecto!");
            textView3.setBackgroundResource(R.drawable.estilo_redondeado_rojo);
            textView3.setClickable(false);

            textView1.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.INVISIBLE);

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

        handlerVisibilidad.removeCallbacksAndMessages(runnableVisibilidad);

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
                textView1.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                textView3.setVisibility(View.INVISIBLE);
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
                textView1.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                textView3.setVisibility(View.INVISIBLE);
            }
        }, 3000);
    }



    //FUNCION ON PAUSE DE LA CLASE
    @Override
    public void onPause() {
        handler.removeCallbacks(runnable_iniciar);

        if(sonidoFrase != null) {
            sonidoFrase.stop();
            sonidoFrase.release();
            sonidoFrase = null;
        }

        super.onPause();
    }

}//final
