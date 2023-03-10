package com.example.marglov2.FragmentsDeteccion;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marglov2.Entidades.DBHelperPuntajes;
import com.example.marglov2.R;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Actividad_conc_2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Actividad_conc_2Fragment extends Fragment {

    //Declaracion de variables
    private ImageView imagen;
    private Button iniciar, parar, pare,ayudas, instrucciones;
    private TextView textViewAciertos, textViewInstrucc,textViewFallaste;
    MediaPlayer sonido;
    int i, global_i, aciertos_actividad, global_retardo;
    long tiempoInicio, tiempoEscuche, tiempoAcabado;

    long duracionMilliSonido;
    double duracionSonido;


    String infoAyudas;
    Boolean AyudasVisuales, AyudasTactiles;


    DBHelperPuntajes DB;

    //forma para el runnable
    private Handler handler, handler_siguiente;
    private Runnable runnable_iniciar, runnable_VisualTactil,
            runnable_Visual,runnable_Tactil, runnable_SinAyudas, runnableSiguiente;





    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Actividad_conc_2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Actividad_conc_2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Actividad_conc_2Fragment newInstance(String param1, String param2) {
        Actividad_conc_2Fragment fragment = new Actividad_conc_2Fragment();
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
        return inflater.inflate(R.layout.fragment_actividad_conc_2, container, false);
    }

    //creacion del onviewcreated recomendado
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        imagen = view.findViewById(R.id.imagen);
        iniciar = view.findViewById(R.id.btn_iniciar);
        parar = view.findViewById(R.id.btn_parar);
        pare = view.findViewById(R.id.btn_paro);
        ayudas =view.findViewById(R.id.btn_ayudas);
        instrucciones =view.findViewById(R.id.btn_instrucciones);

        textViewAciertos = view.findViewById(R.id.textViewAciertosid);
        textViewFallaste = view.findViewById(R.id.textViewFallasteid);
        textViewInstrucc = view.findViewById(R.id.textviewInstrucc);


        //Lista de sonidos
        List<Integer> ListaSonidos = new ArrayList<Integer>();
        ListaSonidos.add(R.raw.clau);
        ListaSonidos.add(R.raw.durazno_palabra);
        ListaSonidos.add(R.raw.gallo);
        ListaSonidos.add(R.raw.gra);
        ListaSonidos.add(R.raw.mariposa_palabra);
        ListaSonidos.add(R.raw.moto_largo);
        ListaSonidos.add(R.raw.elefante);
        ListaSonidos.add(R.raw.rosa_palabra);
        ListaSonidos.add(R.raw.television_palabra);
        ListaSonidos.add(R.raw.tren_largo);
        ListaSonidos.add(R.raw.paso_de_avion);
        ListaSonidos.add(R.raw.voz);



        //Lista de imagenes
        List<Integer> ListaImagenes = new ArrayList<Integer>();
        ListaImagenes.add(R.drawable.clau);
        ListaImagenes.add(R.drawable.durazno);
        ListaImagenes.add(R.drawable.gallo);
        ListaImagenes.add(R.drawable.gra);
        ListaImagenes.add(R.drawable.mariposa);
        ListaImagenes.add(R.drawable.moto);
        ListaImagenes.add(R.drawable.elefante);
        ListaImagenes.add(R.drawable.rosa);
        ListaImagenes.add(R.drawable.television);
        ListaImagenes.add(R.drawable.tren);
        ListaImagenes.add(R.drawable.paso_de_avion);
        ListaImagenes.add(R.drawable.voz);


        //bd
        DB = new DBHelperPuntajes(getContext());

        // funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(view); //nacControllermio es el nombre de la instancia


        //vibrador
        final Vibrator vibracion_efecto = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
        //replace yourActivity.this with your own activity or if you declared a context you can write context.getSystemService(Context.VIBRATOR_SERVICE);

        // Initialize a new Handler
        handler = new Handler();
        handler_siguiente = new Handler();

        //cargar sharedPReferences Ayudas
        cargarPreferenciasAyudas();


        runnable_VisualTactil = new Runnable() {
            public void run() {
                Log.d("Runnable", "Handler is working");
                infoAyudas = "Ayudas visuales y táctiles";


                //REproduccion aleatoria sonidos
                int randomIntLista = (new Random().nextInt(ListaSonidos.size()));
                int numero_sonido = ListaSonidos.get(randomIntLista);
                sonido = MediaPlayer.create(getActivity(), numero_sonido);
                sonido.start();

                //Duracion del sonido
                duracionMilliSonido = sonido.getDuration();//duracion en milisegundos
                duracionSonido = duracionMilliSonido/1000.0;//duracion en seg


                //Imagenes aleatorias con sonidos correspondientes
                int numero_imagen = ListaImagenes.get(randomIntLista);
                imagen.setImageResource(numero_imagen);

                //sonido.start();
                vibracion_efecto.vibrate(1000);//80 represents the milliseconds (the duration of the vibration)

                //handler para que muestre la imagen y desaparezca cuando termina sonido
                mostrarImagen();

                //para activar click boton escuche
                pare.setClickable(true);

                //medir tiempo en el que inicia el sonido
                tiempoInicio = SystemClock.elapsedRealtime();


                //Defino retardo mayor si no selecciona nada
                global_retardo = (int) (Math.random() * 20000) + 18000;//genera retardo entre 3 y 5 segs


                if (i >= 2) { // just remove call backs//
                    //para parar el sonido correctamente
                    if(sonido != null) {
                        sonido.stop();
                        sonido.release();
                        sonido = null;
                    }
                    //sonido = MediaPlayer.create(getActivity(), numero_sonido);
                    vibracion_efecto.cancel();
                    imagen.setVisibility(View.INVISIBLE);
                    pare.setVisibility(View.INVISIBLE);
                    iniciar.setVisibility(View.VISIBLE);
                    parar.setVisibility(View.INVISIBLE);
                    textViewAciertos.setVisibility(View.VISIBLE);
                    handler.removeCallbacks(this);
                    Log.d("Runnable", "ok");

                    if (aciertos_actividad >=10) {

                        //guardar puntaje en BD
                        guardarPuntajeBD();

                        //Guardar puntaje en sharedpreferences
                        guardarPreferenciasPuntaje();

                        //pasar a otro fragment
                        navControllermio.navigate(R.id.action_actividad_conc_2Fragment_to_actividad_conc_2_felicitacionFragment);//pasar a otro fragment con actions


                    }
                    else{

                        navControllermio.navigate(R.id.action_actividad_conc_2Fragment_to_actividad_conc_2_erradoFragment);//pasar a otro fragment con actions

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

        runnable_Visual = new Runnable() {
            public void run() {
                Log.d("Runnable", "Handler is working");

                infoAyudas = "Ayudas visuales";


                //REproduccion aleatoria sonidos
                int randomIntLista = (new Random().nextInt(ListaSonidos.size()));
                int numero_sonido = ListaSonidos.get(randomIntLista);
                sonido = MediaPlayer.create(getActivity(), numero_sonido);
                sonido.start();

                //Duracion del sonido
                duracionMilliSonido = sonido.getDuration();//duracion en milisegundos
                duracionSonido = duracionMilliSonido/1000.0;//duracion en seg

                //Imagenes aleatorias con sonidos correspondientes
                int numero_imagen = ListaImagenes.get(randomIntLista);
                imagen.setImageResource(numero_imagen);

                //Vibracion
                //vibracion_efecto.vibrate(1000);//80 represents the milliseconds (the duration of the vibration)

                //handler para que muestre la imagen y desaparezca cuando termina sonido
                mostrarImagen();

                //para activar click boton escuche
                pare.setClickable(true);

                //medir tiempo en el que inicia el sonido
                tiempoInicio = SystemClock.elapsedRealtime();


                //Defino retardo mayor si no selecciona nada
                global_retardo = (int) (Math.random() * 15000) + 12000;//genera retardo entre 3 y 5 segs


                if (i >= 13) { // just remove call backs//
                    //para parar el sonido correctamente
                    if(sonido != null) {
                        sonido.stop();
                        sonido.release();
                        sonido = null;
                    }

                    //vibracion_efecto.cancel();
                    imagen.setVisibility(View.INVISIBLE);
                    pare.setVisibility(View.INVISIBLE);
                    iniciar.setVisibility(View.VISIBLE);
                    parar.setVisibility(View.INVISIBLE);
                    textViewAciertos.setVisibility(View.VISIBLE);
                    handler.removeCallbacks(this);
                    Log.d("Runnable", "ok");

                    if (aciertos_actividad >=10) {
                        //Navigation.findNavController(view).navigate(R.id.pantallaFelicitacionFragment);//Metodo alterno sin funcion; para hacer navegacion con boton welcome a otro fragment}

                        //guardar puntaje en BD
                        guardarPuntajeBD();

                        //Guardar puntaje en sharedpreferences
                        guardarPreferenciasPuntaje();

                        //pasar a otro fragment
                        //navControllermio.navigate(R.id.actividad_conc_2_felicitacionFragment);
                        navControllermio.navigate(R.id.action_actividad_conc_2Fragment_to_actividad_conc_2_felicitacionFragment);//pasar a otro fragment con actions
                    }
                    else{
                        // Navigation.findNavController(view).navigate(R.id.pantallaErradoFragment);//Metodo alterno sin funcion; para hacer navegacion con boton welcome a otro fragment
                        //navControllermio.navigate(R.id.actividad_conc_2_erradoFragment); //pasar a otro fragment
                        navControllermio.navigate(R.id.action_actividad_conc_2Fragment_to_actividad_conc_2_erradoFragment);//pasar a otro fragment con actions

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

        runnable_Tactil =  new Runnable() {
            public void run() {
                Log.d("Runnable", "Handler is working");

                infoAyudas = "Ayudas táctiles";


                //REproduccion aleatoria sonidos
                int randomIntLista = (new Random().nextInt(ListaSonidos.size()));
                int numero_sonido = ListaSonidos.get(randomIntLista);
                sonido = MediaPlayer.create(getActivity(), numero_sonido);
                sonido.start();


                //Duracion del sonido
                duracionMilliSonido = sonido.getDuration();//duracion en milisegundos
                duracionSonido = duracionMilliSonido/1000.0;//duracion en seg

                //Imagenes aleatorias con sonidos correspondientes
                //int numero_imagen = ListaImagenes.get(randomIntLista);
                //imagen.setImageResource(numero_imagen);

                //sonido.start();
                vibracion_efecto.vibrate(1000);//80 represents the milliseconds (the duration of the vibration)

                //Quitar imagen
                imagen.setVisibility(View.INVISIBLE);

                //para activar click boton escuche
                pare.setClickable(true);

                //medir tiempo en el que inicia el sonido
                tiempoInicio = SystemClock.elapsedRealtime();

                pasarSiguiente();


                //Defino retardo
                global_retardo = (int) (Math.random() * 20000) + 18000;//genera retardo entre 3 y 5 segs


                if (i >= 13) { // just remove call backs//
                    //para parar el sonido correctamente
                    if(sonido != null) {
                        sonido.stop();
                        sonido.release();
                        sonido = null;
                    }

                    vibracion_efecto.cancel();
                    pare.setVisibility(View.INVISIBLE);
                    iniciar.setVisibility(View.VISIBLE);
                    parar.setVisibility(View.INVISIBLE);
                    textViewAciertos.setVisibility(View.VISIBLE);
                    handler.removeCallbacks(this);
                    Log.d("Runnable", "ok");

                    if (aciertos_actividad >=10) {

                        //guardar puntaje en BD
                        guardarPuntajeBD();

                        //Guardar puntaje en sharedpreferences
                        guardarPreferenciasPuntaje();

                        //pasar a otro fragment

                        navControllermio.navigate(R.id.action_actividad_conc_2Fragment_to_actividad_conc_2_felicitacionFragment);//pasar a otro fragment con actions

                    }
                    else{

                        navControllermio.navigate(R.id.action_actividad_conc_2Fragment_to_actividad_conc_2_erradoFragment);//pasar a otro fragment con actions

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

        runnable_SinAyudas = new Runnable() {
            public void run() {
                Log.d("Runnable", "Handler is working");

                infoAyudas = "Sin Ayudas";

                //REproduccion aleatoria sonidos
                int randomIntLista = (new Random().nextInt(ListaSonidos.size()));
                int numero_sonido = ListaSonidos.get(randomIntLista);
                sonido = MediaPlayer.create(getActivity(), numero_sonido);
                sonido.start();

                //Duracion del sonido
                duracionMilliSonido = sonido.getDuration();//duracion en milisegundos
                duracionSonido = duracionMilliSonido/1000.0;//duracion en seg

                //Quitar imagen
                imagen.setVisibility(View.INVISIBLE);

                //para activar click boton escuche
                pare.setClickable(true);

                //medir tiempo en el que inicia el sonido
                tiempoInicio = SystemClock.elapsedRealtime();

                pasarSiguiente();

                //Defino retardo mayor si no selecciona nada
                global_retardo = (int) (Math.random() * 20000) + 18000;//genera retardo entre 3 y 5 segs

                if (i >= 13) { // just remove call backs//
                    //para parar el sonido correctamente
                    if(sonido != null) {
                        sonido.stop();
                        sonido.release();
                        sonido = null;
                    }
                    //vibracion_efecto.cancel();
                    pare.setVisibility(View.INVISIBLE);
                    iniciar.setVisibility(View.VISIBLE);
                    parar.setVisibility(View.INVISIBLE);
                    textViewAciertos.setVisibility(View.VISIBLE);
                    handler.removeCallbacks(this);
                    Log.d("Runnable", "ok");

                    if (aciertos_actividad >=10) {
                        //guardar puntaje en BD
                        guardarPuntajeBD();
                        //Guardar puntaje en sharedpreferences
                        guardarPreferenciasPuntaje();
                        //pasar a otro fragment
                        navControllermio.navigate(R.id.action_actividad_conc_2Fragment_to_actividad_conc_2_felicitacionFragment);//pasar a otro fragment con actions

                    }
                    else{
                        navControllermio.navigate(R.id.action_actividad_conc_2Fragment_to_actividad_conc_2_erradoFragment);//pasar a otro fragment con actions

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
                global_retardo = (int) (Math.random() * 3000) + 1000; //genera retardo aleatoriamente entre un rango 1 - n
                //global_retardo =1000;

                //defino visibilidad objetos botones y textos
                pare.setVisibility(View.VISIBLE);
                parar.setVisibility(View.VISIBLE);
                iniciar.setVisibility(View.INVISIBLE);
                ayudas.setVisibility(View.INVISIBLE);
                instrucciones.setVisibility(View.INVISIBLE);


                textViewAciertos.setVisibility(View.INVISIBLE);
                textViewInstrucc.setVisibility(View.INVISIBLE);

                //Iniciar runnable dependiendo las ayudas seleccionadas
                inciarRunnableConDelay();

            }
        });

//FUNCION PARA EL BOTON PARAR
        parar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(sonido != null) {
                    sonido.stop();
                    sonido.release();
                    sonido = null;
                }
                //sonido = MediaPlayer.create(getActivity(),R.raw.telefono3seg);
                vibracion_efecto.cancel();
                imagen.setVisibility(View.INVISIBLE);
                pare.setVisibility(View.INVISIBLE);
                parar.setVisibility(View.INVISIBLE);
                iniciar.setVisibility(View.VISIBLE);
                ayudas.setVisibility(View.VISIBLE);
                instrucciones.setVisibility(View.VISIBLE);

                textViewAciertos.setVisibility(View.INVISIBLE);
                textViewFallaste.setVisibility(View.INVISIBLE);

                handler.removeCallbacks(runnable_iniciar);
                Log.d("Runnable", "ok");

                handler_siguiente.removeCallbacks(runnableSiguiente);
                handler.removeCallbacks(runnable_VisualTactil);
                handler.removeCallbacks(runnable_Visual);
                handler.removeCallbacks(runnable_Tactil);
                handler.removeCallbacks(runnable_SinAyudas);
            }
        });

//FUNCION PARA EL BOTON parE
        pare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                tiempoEscuche = SystemClock.elapsedRealtime(); //mide el tiempo al que presiona el boton
                long elapsedMilliSeconds = tiempoEscuche-tiempoInicio; //saca la diferencia
                double elapsedSeconds = elapsedMilliSeconds / 1000.0; //tiempo transcurrido en segundos

                //Duracion del sonido
                //long duracionSonido = tiempoFinalsonido-tiempoInicio; //saca la diferencia
                //long duracionMilliSonido = sonido.getDuration();//duracion en milisegundos
                //double duracionSonido = duracionMilliSonido/1000.0;//duracion en seg

                if(elapsedSeconds>duracionSonido-0.5 && elapsedSeconds<duracionSonido+2){
                    aciertos_actividad = aciertos_actividad +1;
                    mostrarTextviewAciertos();
                    imagen.setVisibility(View.INVISIBLE);
                    pare.setClickable(false);


                    if(sonido != null) {
                        sonido.stop();
                        sonido.release();
                        sonido = null;
                    }

                    handler_siguiente.removeCallbacks(runnableSiguiente);

                    iniciarRunnable();


                }else{
                    mostrarTextviewFallaste();
                    pare.setClickable(false);
                    imagen.setVisibility(View.INVISIBLE);
                    vibracion_efecto.cancel();

                    iniciarRunnable();

                    if(sonido != null) {
                        sonido.stop();
                        sonido.release();
                        sonido = null;
                    }

                }

            }
        });


        //FUNCION PARA EL BOTON AYUDAS
        ayudas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //navControllermio.navigate(R.id.actividad_conc_2_ayudasFragment); //pasar a otro fragment
                navControllermio.navigate(R.id.action_actividad_conc_2Fragment_to_actividad_conc_2_ayudasFragment); //pasar a otro fragment con actions

                handler.removeCallbacks(runnable_iniciar);
                handler_siguiente.removeCallbacks(runnableSiguiente);
                handler.removeCallbacks(runnable_VisualTactil);
                handler.removeCallbacks(runnable_Visual);
                handler.removeCallbacks(runnable_Tactil);
                handler.removeCallbacks(runnable_SinAyudas);
            }
        });

//FUNCION PARA EL BOTON INSTRUCCIONES
        instrucciones.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                navControllermio.navigate(R.id.action_actividad_conc_2Fragment_to_actividad_conc_2_instruccionesFragment); //pasar a otro fragment con actions

                handler_siguiente.removeCallbacks(runnableSiguiente);
                handler.removeCallbacks(runnable_iniciar);
                handler.removeCallbacks(runnable_VisualTactil);
                handler.removeCallbacks(runnable_Visual);
                handler.removeCallbacks(runnable_Tactil);
                handler.removeCallbacks(runnable_SinAyudas);
            }
        });

    }//onviewcreated



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
        String subhabilidadTXT = "Detección";
        int puntajeTXT = aciertos_actividad;
        String infoTXT= infoAyudas;//Informacion sobre la actividad (Ayudas)


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

    private void cargarPreferenciasAyudas(){
        SharedPreferences preferencesayudas = getActivity().getSharedPreferences("PreferenciasAyudas", Context.MODE_PRIVATE);
        AyudasVisuales = preferencesayudas.getBoolean("AyudasVisuales",false);
        AyudasTactiles = preferencesayudas.getBoolean("AyudasTactiles",false);
    }

    private void mostrarImagen(){
        imagen.setVisibility(View.VISIBLE);
        sonido.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                imagen.setVisibility(View.INVISIBLE);


                //METODO PARA PASAR AL SIGUIENTE SONIDO SI NO SE OBTIENE RESPUESTA EN 3 SEGUNDOS
                if(sonido != null){
                    handler_siguiente.postDelayed(runnableSiguiente = new Runnable() {
                        @Override
                        public void run() {
                            tiempoAcabado = SystemClock.elapsedRealtime();
                            long elapsedMilliSeconds = tiempoAcabado-tiempoInicio; //saca la diferencia
                            double elapsedSeconds = elapsedMilliSeconds / 1000.0; //tiempo transcurrido en segundos

                            //Duracion del sonido
                            //long duracionMilliSonido = sonido.getDuration();//duracion en milisegundos
                            //double duracionSonido = duracionMilliSonido/1000.0;//duracion en seg

                            if(elapsedSeconds-duracionSonido>3){
                                mostrarTextviewFallaste();
                                iniciarRunnable();
                            }

                        }
                    }, 3000);//REtardo en milisengundos
                }


            }
        });
    }

    private void pasarSiguiente(){
        sonido.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {

                //METODO PARA PASAR AL SIGUIENTE SONIDO SI NO SE OBTIENE RESPUESTA EN 3 SEGUNDOS
                if(sonido != null){
                    handler_siguiente.postDelayed(runnableSiguiente = new Runnable() {
                        @Override
                        public void run() {
                            tiempoAcabado = SystemClock.elapsedRealtime();
                            long elapsedMilliSeconds = tiempoAcabado-tiempoInicio; //saca la diferencia
                            double elapsedSeconds = elapsedMilliSeconds / 1000.0; //tiempo transcurrido en segundos

                            //Duracion del sonido
                            //long duracionMilliSonido = sonido.getDuration();//duracion en milisegundos
                            //double duracionSonido = duracionMilliSonido/1000.0;//duracion en seg

                            if(elapsedSeconds-duracionSonido>3){
                                mostrarTextviewFallaste();
                                iniciarRunnable();
                            }

                        }
                    }, 3000);//REtardo en milisengundos
                }


            }
        });
    }



    private void mostrarTextviewAciertos(){
        //handler para que muestre la imagen 1 segundo y se desaparezca
        textViewAciertos.setVisibility(View.VISIBLE);
        textViewAciertos.setText("¡Muy bien! +" + aciertos_actividad);
        //textViewAciertos.setBackgroundResource(R.color.verde_aciertos);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewAciertos.setVisibility(View.INVISIBLE);// do your operation here
            }
        }, 2000);
    }

    private void mostrarTextviewFallaste(){
        //handler para que muestre la imagen 1 segundo y se desaparezca
        textViewFallaste.setVisibility(View.VISIBLE);
        textViewFallaste.setText("¡Fallaste!");
        //textViewFallaste.setBackgroundResource(R.color.healthwarm1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewFallaste.setVisibility(View.INVISIBLE);// do your operation here
            }
        }, 2000);
    }

    private void iniciarRunnable(){
        //Iniciar runnable dependiendo las ayudas seleccionadas
        global_retardo = (int) (Math.random() * 3000) + 2000; //genera retardo aleatoriamente entre un rango 1 - n
        cargarPreferenciasAyudas();
        handler_siguiente.removeCallbacks(runnableSiguiente);
        if(AyudasVisuales==true&&AyudasTactiles==true){
            handler.removeCallbacks(runnable_VisualTactil);
            handler.postDelayed(runnable_VisualTactil, global_retardo);
        }else if(AyudasVisuales==false&&AyudasTactiles==false){
            handler.removeCallbacks(runnable_SinAyudas);
            handler.postDelayed(runnable_SinAyudas, global_retardo);
        }if(AyudasVisuales==true&&AyudasTactiles==false){
            handler.removeCallbacks(runnable_Visual);
            handler.postDelayed(runnable_Visual, global_retardo);
        }else if(AyudasVisuales==false&&AyudasTactiles==true){
            handler.removeCallbacks(runnable_Tactil);
            handler.postDelayed(runnable_Tactil, global_retardo);
        }
    }

    private void inciarRunnableConDelay(){
        //Iniciar runnable dependiendo las ayudas seleccionadas
        cargarPreferenciasAyudas();
        handler_siguiente.removeCallbacks(runnableSiguiente);

        if(AyudasVisuales==true&&AyudasTactiles==true){
            handler.removeCallbacks(runnable_VisualTactil);
            handler.postDelayed(runnable_VisualTactil, global_retardo);
        }else if(AyudasVisuales==false&&AyudasTactiles==false){
            handler.removeCallbacks(runnable_SinAyudas);
            handler.postDelayed(runnable_SinAyudas, global_retardo);
        }if(AyudasVisuales==true&&AyudasTactiles==false){
            handler.removeCallbacks(runnable_Visual);
            handler.postDelayed(runnable_Visual, global_retardo);
        }else if(AyudasVisuales==false&&AyudasTactiles==true){
            handler.removeCallbacks(runnable_Tactil);
            handler.postDelayed(runnable_Tactil, global_retardo);
        }
    }



    private void inciarRunnableSinDelay(){
        //Iniciar runnable dependiendo las ayudas seleccionadas
        cargarPreferenciasAyudas();
        handler_siguiente.removeCallbacks(runnableSiguiente);

        if(AyudasVisuales==true&&AyudasTactiles==true){
            handler.removeCallbacks(runnable_VisualTactil);
            handler.post(runnable_VisualTactil);
        }else if(AyudasVisuales==false&&AyudasTactiles==false){
            handler.removeCallbacks(runnable_SinAyudas);
            handler.post(runnable_SinAyudas);
        }if(AyudasVisuales==true&&AyudasTactiles==false){
            handler.removeCallbacks(runnable_Visual);
            handler.post(runnable_Visual);
        }else if(AyudasVisuales==false&&AyudasTactiles==true){
            handler.removeCallbacks(runnable_Tactil);
            handler.post(runnable_Tactil);
        }
    }



    //FUNCION ON PAUSE DE LA CLASE
    @Override
    public void onPause() {
        handler_siguiente.removeCallbacks(runnableSiguiente);
        handler.removeCallbacks(runnable_iniciar);
        handler.removeCallbacks(runnable_iniciar);
        handler.removeCallbacks(runnable_VisualTactil);
        handler.removeCallbacks(runnable_Visual);
        handler.removeCallbacks(runnable_Tactil);
        handler.removeCallbacks(runnable_SinAyudas);

        if(sonido != null) {
            sonido.stop();
            sonido.release();
            sonido = null;
        }
        super.onPause();
    }
}//final