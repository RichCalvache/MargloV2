

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
 * Use the {@link Actividad_comp_2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Actividad_comp_2Fragment extends Fragment {

    //Declaracion de variables
    private ImageView imagen1, imagen2;
    private Button iniciar, parar, instrucciones;
    private TextView textViewAciertos, textViewInstrucc, textViewSeleccione, textViewEscuche, textViewFallaste;
    MediaPlayer sonidoInstruccion;
    int i, aciertos_actividad, global_retardo;
    int numero_sonido, numero_imagen1, numero_imagen2, randomImagenes;

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

    public Actividad_comp_2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Actividad_comp_2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Actividad_comp_2Fragment newInstance(String param1, String param2) {
        Actividad_comp_2Fragment fragment = new Actividad_comp_2Fragment();
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
        return inflater.inflate(R.layout.fragment_actividad_comp_2, container, false);
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
        ListaSonidos.add(R.raw.seleccione_florero_debajo_mesa);
        ListaSonidos.add(R.raw.seleccione_martillo_debajo_mesa);
        ListaSonidos.add(R.raw.seleccione_anillo_debajo_mesa);
        ListaSonidos.add(R.raw.seleccione_cuchara_encima_mesa);
        ListaSonidos.add(R.raw.seleccione_balon_debajo_mesa);
        ListaSonidos.add(R.raw.seleccione_martillo_encima_y_zapatos_debajo_mesa);
        ListaSonidos.add(R.raw.seleccione_vaso_debajo_y_florero_encima_mesa);
        ListaSonidos.add(R.raw.seleccione_balon_encima_y_perro_debajo_mesa);
        ListaSonidos.add(R.raw.seleccione_perro_debajo_y_anillo_encima_mesa);
        ListaSonidos.add(R.raw.seleccione_florero_y_zapatos_encima_y_vaso_debajo_mesa);
        ListaSonidos.add(R.raw.seleccione_anillo_encima_y_balon_debajo_mesa);

        //Lista de imagenes
        List<Integer> ListaImagenes = new ArrayList<Integer>();
        ListaImagenes.add(R.drawable.florero_debajo_mesa);
        ListaImagenes.add(R.drawable.florero_encima_mesa);
        ListaImagenes.add(R.drawable.martillo_debajo_mesa);
        ListaImagenes.add(R.drawable.martillo_encima_mesa);
        ListaImagenes.add(R.drawable.anillo_debajo_mesa);
        ListaImagenes.add(R.drawable.anillo_encima_mesa);
        ListaImagenes.add(R.drawable.cuchara_encima_mesa);
        ListaImagenes.add(R.drawable.cuchara_debajo_mesa);
        ListaImagenes.add(R.drawable.balon_debajo_mesa);
        ListaImagenes.add(R.drawable.balon_encima_mesa);
        ListaImagenes.add(R.drawable.martillo_encima_y_zapatos_debajo_mesa);
        ListaImagenes.add(R.drawable.martillo_debajo_y_zapatos_encima_mesa);
        ListaImagenes.add(R.drawable.vaso_debajo_y_florero_encima_mesa);
        ListaImagenes.add(R.drawable.vaso_encima_y_florero_debajo_mesa);
        ListaImagenes.add(R.drawable.balon_encima_y_perro_debajo_mesa);//imagenes con sonidoFrase = 13
        ListaImagenes.add(R.drawable.balon_debajo_y_perro_encima_mesa);
        ListaImagenes.add(R.drawable.perro_debajo_y_anillo_encima_mesa);
        ListaImagenes.add(R.drawable.perro_encima_y_anillo_debajo_mesa);
        ListaImagenes.add(R.drawable.florero_encima_y_zapatos_encima_y_vaso_debajo_mesa);
        ListaImagenes.add(R.drawable.florero_encima_y_zapatos_debajo_y_vaso_debajo_mesa);
        ListaImagenes.add(R.drawable.anillo_encima_y_balon_debajo_mesa);
        ListaImagenes.add(R.drawable.anillo_encima_y_balon_encima_mesa);


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
                    numero_imagen2 = ListaImagenes.get(randomIntlistaImagenes + 1);

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


                    if (aciertos_actividad >=10) {

                        //guardar puntaje en BD
                        guardarPuntajeBD();

                        //Guardar puntaje en sharedpreferences
                        guardarPreferenciasPuntaje();

                        //pasar a otro fragment
                        //navControllermio.navigate(R.id.actividad_conc_3_felicitacionFragment);
                        navControllermio.navigate(R.id.action_actividad_comp_2Fragment_to_actividad_comp_2_felicitacionFragment);//pasar a otro fragment con actions

                    }
                    else{

                        navControllermio.navigate(R.id.action_actividad_comp_2Fragment_to_actividad_comp_2_erradoFragment);//pasar a otro fragment con actions

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
                navControllermio.navigate(R.id.action_actividad_comp_2Fragment_to_actividad_comp_2_instruccionesFragment); //pasar a otro fragment con actions

                handler.removeCallbacks(runnable_iniciar);

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
        textViewSeleccione.setText(R.string.Correcto);
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
                imagen1.setVisibility(View.INVISIBLE);
                imagen2.setVisibility(View.INVISIBLE);
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
