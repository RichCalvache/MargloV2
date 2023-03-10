       package com.example.marglov2.FragmentsComprension;

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
 * Use the {@link Actividad_comp_5Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Actividad_comp_5Fragment extends Fragment {

    //Declaracion de variables
    private ImageView imagen;
    private Button iniciar, parar, instrucciones;
    private TextView textViewAciertos, textViewInstrucc, textViewSeleccione, textViewEscuche, textViewFallaste, btn_si,btn_no;
    MediaPlayer sonido;
    int i, aciertos_actividad, global_retardo;
    int numero_sonido, numero_imagen, randomPregunta;

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

    public Actividad_comp_5Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Actividad_comp_5Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Actividad_comp_5Fragment newInstance(String param1, String param2) {
        Actividad_comp_5Fragment fragment = new Actividad_comp_5Fragment();
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
        return inflater.inflate(R.layout.fragment_actividad_comp_5, container, false);
    }
           //creacion del onviewcreated recomendado
           @Override
           public void onViewCreated(View view, Bundle savedInstanceState) {
               super.onViewCreated(view, savedInstanceState);


               imagen = view.findViewById(R.id.imagen1);

               iniciar = view.findViewById(R.id.btn_iniciar);
               parar = view.findViewById(R.id.btn_parar);
               instrucciones = view.findViewById(R.id.btn_instrucciones);

               btn_si = view.findViewById(R.id.btn_si);
               btn_no = view.findViewById(R.id.btn_no);


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
               ListaSonidos.add(R.raw.el_dia_esta_soleado);
               ListaSonidos.add(R.raw.el_hombre_y_la_mujer_estan_comiendo_hamburguesa);
               ListaSonidos.add(R.raw.el_hombre_y_la_mujer_estan_corriendo);
               ListaSonidos.add(R.raw.el_nino_y_el_hombre_estan_nadando_en_el_rio);
               ListaSonidos.add(R.raw.el_oficio_hombre_es_barrendero);
               ListaSonidos.add(R.raw.estas_flores_se_llaman_girasoles);
               ListaSonidos.add(R.raw.este_es_un_barco);
               ListaSonidos.add(R.raw.este_lugar_es_una_iglesia);
               ListaSonidos.add(R.raw.este_perro_es_negro);
               ListaSonidos.add(R.raw.esto_es_un_atardecer);
               ListaSonidos.add(R.raw.la_nina_esta_feliz);
               ListaSonidos.add(R.raw.soy_un_adulto);


               //Lista de imagenes si
               List<Integer> ListaImagenesSi = new ArrayList<Integer>();
               ListaImagenesSi.add(R.drawable.dia_soleado);
               ListaImagenesSi.add(R.drawable.pareja_hamburguesa);
               ListaImagenesSi.add(R.drawable.pareja_corriendo);
               ListaImagenesSi.add(R.drawable.nino_hombre_en_rio);
               ListaImagenesSi.add(R.drawable.barrendero);
               ListaImagenesSi.add(R.drawable.girasoles);
               ListaImagenesSi.add(R.drawable.barco);
               ListaImagenesSi.add(R.drawable.iglesia);
               ListaImagenesSi.add(R.drawable.perronegro);
               ListaImagenesSi.add(R.drawable.atardecer);
               ListaImagenesSi.add(R.drawable.nina_feliz);
               ListaImagenesSi.add(R.drawable.adulto);

               //Lista de imagenes no
               List<Integer> ListaImagenesNo = new ArrayList<Integer>();
               ListaImagenesNo.add(R.drawable.dia_tormenta);
               ListaImagenesNo.add(R.drawable.pareja_comiendo_pizza);
               ListaImagenesNo.add(R.drawable.pareja_caminando);
               ListaImagenesNo.add(R.drawable.nino_hombre_piscina);
               ListaImagenesNo.add(R.drawable.bombero);
               ListaImagenesNo.add(R.drawable.rosas);
               ListaImagenesNo.add(R.drawable.carro_ident_3);
               ListaImagenesNo.add(R.drawable.salon_clases);
               ListaImagenesNo.add(R.drawable.perroblanco);
               ListaImagenesNo.add(R.drawable.noche);
               ListaImagenesNo.add(R.drawable.nina_triste);
               ListaImagenesNo.add(R.drawable.nino_pequeno);


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
                       Log.d("Runnable", "Handler is working");

                       //Generacion Aleatoria de numeros imagenes y sonidos
                       int randomIntListaSonidos = (new Random().nextInt(ListaSonidos.size()));
                       int randomIntlistaImagenes = randomIntListaSonidos;

                       //Obtengo de la lista de sonidos, el sonidoFrase con la posicion random generada
                       numero_sonido = ListaSonidos.get(randomIntListaSonidos);

                       //Crea la instancia  del sonidoFrase de acuerdo a la posicion int
                       sonido = MediaPlayer.create(getActivity(), numero_sonido);

                       //Numero random entre 0 y 1 para definir si la respuesta es SI o NO
                       Random rand = new Random();
                       randomPregunta = rand.nextInt(2);
                       //Metodo para definir las imagenes dependiendo la respuesta
                       if(randomPregunta==0){//Si es cero define la respuesta como no
                           //Elige la imagen de la lista de no
                           numero_imagen = ListaImagenesNo.get(randomIntlistaImagenes);
                           imagen.setImageResource(numero_imagen);

                       }else{//Si es uno define la respuesta como si
                           //Elige la imagen de la lista de no
                           numero_imagen = ListaImagenesSi.get(randomIntlistaImagenes);
                           imagen.setImageResource(numero_imagen);
                       }


                       //para el textview aciertos no salga
                       //textViewAciertos.setVisibility(View.INVISIBLE);
                       //para el textview seleeccione / correcto no salga
                       textViewSeleccione.setVisibility(View.INVISIBLE);
                       //para que salga el texto "escuche los sonidos"
                       textViewEscuche.setVisibility(View.VISIBLE);

                       //para ocultar las imagen nuevamente
                       imagen.setVisibility(View.INVISIBLE);

                       //para activar nuevamente la opcion clickeable de los botones
                       btn_no.setClickable(true);
                       btn_si.setClickable(true);


                       reproducirSonido();


                       //Retardo
                       //global_retardo = (int) (Math.random() * 13000) + 11000;//genera retardo entre 3 y 5 segs
                       global_retardo = 20000;

                       if (i >= 13) { // just remove call backs//
                           //para parar el sonidoFrase correctamente
                           if(sonido != null) {
                               sonido.stop();
                               sonido.release();
                               sonido = null;
                           }


                           imagen.setVisibility(View.INVISIBLE);
                           btn_no.setVisibility(View.INVISIBLE);
                           btn_si.setVisibility(View.INVISIBLE);

                           iniciar.setVisibility(View.INVISIBLE);
                           parar.setVisibility(View.INVISIBLE);
                           textViewAciertos.setVisibility(View.INVISIBLE);
                           textViewFallaste.setVisibility(View.INVISIBLE);
                           handler.removeCallbacks(this);
                           Log.d("Runnable", "ok");

                           if (aciertos_actividad >=10) {

                               //guardar puntaje en BD
                               guardarPuntajeBD();

                               //Guardar puntaje en sharedpreferences
                               guardarPreferenciasPuntaje();

                               //pasar a otro fragment
                               //navControllermio.navigate(R.id.actividad_conc_3_felicitacionFragment);
                               navControllermio.navigate(R.id.action_actividad_comp_5Fragment_to_actividad_comp_5_felicitacionFragment);//pasar a otro fragment con actions

                           }
                           else{

                               navControllermio.navigate(R.id.action_actividad_comp_5Fragment_to_actividad_comp_5_erradoFragment);//pasar a otro fragment con actions

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

                       //inicio el runnable despues de 1-3 segundos
                       handler.removeCallbacks(runnable_iniciar);
                       handler.postDelayed(runnable_iniciar, retardo);
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


                       imagen.setVisibility(View.INVISIBLE);
                       btn_no.setVisibility(View.INVISIBLE);
                       btn_si.setVisibility(View.INVISIBLE);

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
                       navControllermio.navigate(R.id.action_actividad_comp_5Fragment_to_actividad_comp_5_instruccionesFragment); //pasar a otro fragment con actions

                       handler.removeCallbacks(runnable_iniciar);
                       Log.d("Runnable", "ok");
                   }
               });



//funciones on clicklistener para las imagenes

               btn_no.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       seleccionarRespuestaNo();

                   }
               });
               btn_si.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       seleccionarRespuestaSi();

                   }
               });


           }//onviewcreated




//MÉTODOS UTILIZADOS

           private void reproducirSonido(){
               //Reproducir sonido instruccion
               sonido.start();

               animacionSonido.setVisibility(View.VISIBLE);

               //una vez acabado el sonidoFrase muestra las imagenes
               sonido.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                   @Override
                   public void onCompletion(MediaPlayer player) {
                       sonido.stop();
                       sonido.release();
                       sonido = null;

                       animacionSonido.setVisibility(View.INVISIBLE);

                       mostrarImagen();
                       // play next audio file
                   }
               });
           }



           private void mostrarImagen() {
               //set textview "selecione imagen"

               imagen.setVisibility(View.VISIBLE);
               btn_no.setVisibility(View.VISIBLE);
               btn_si.setVisibility(View.VISIBLE);

               btn_no.setBackgroundResource(R.drawable.estilo_redondeado_si_y_no);
               btn_si.setBackgroundResource(R.drawable.estilo_redondeado_si_y_no);

               imagen.setBackgroundResource(R.color.white);

               textViewSeleccione.setText("Seleccione la respuesta");
               textViewSeleccione.setBackgroundResource(R.drawable.estilo_redondeado_azul);
               textViewSeleccione.setVisibility(View.VISIBLE);
               textViewEscuche.setVisibility(View.INVISIBLE);

           }


           private void seleccionarRespuestaNo() {
               if (randomPregunta == 0) {//si es cero, la respuesta es NO
                   //Acierta
                   btn_no.setBackgroundResource(R.drawable.estilo_redondeado_verde);


                   //btn_no.setBackgroundResource(R.color.verde_aciertos);
                   btn_no.setClickable(false);

                   btn_si.setVisibility(View.INVISIBLE);


                   //puntaje
                   aciertos_actividad = aciertos_actividad + 1;
                   mostrarTextviewAciertos();
                   //pasar al siguiente sonido
                   iniciarRunnable();

               } else{//se equivoca
                   btn_no.setBackgroundResource(R.drawable.estilo_redondeado_rojo);


                   //btn_no.setBackgroundResource(R.color.naranjaclarito);
                   btn_no.setClickable(false);

                   btn_si.setVisibility(View.INVISIBLE);
                   mostrarTextviewFallaste();
                   //pasar al siguiente sonido
                   iniciarRunnable();

               }
           }

           private void seleccionarRespuestaSi() {
               if (randomPregunta == 1) {//si es uno, la respuesta es SI
                   //Acierta


                   btn_si.setBackgroundResource(R.drawable.estilo_redondeado_verde);

                   //btn_si.setBackgroundResource(R.color.verde_aciertos);
                   btn_si.setClickable(false);

                   btn_no.setVisibility(View.INVISIBLE);

                   //puntaje
                   aciertos_actividad = aciertos_actividad + 1;
                   mostrarTextviewAciertos();
                   //pasar al siguiente sonido
                   iniciarRunnable();

               } else{//se equivoca

                   btn_si.setBackgroundResource(R.drawable.estilo_redondeado_rojo);

                   //btn_si.setBackgroundResource(R.color.naranjaclarito);
                   btn_si.setClickable(false);

                   btn_no.setVisibility(View.INVISIBLE);

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
               int actividadTXT = 5;
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
                       //para ocultar la imagen y los botnes si y no nuevamente
                       imagen.setVisibility(View.INVISIBLE);
                       btn_no.setVisibility(View.INVISIBLE);
                       btn_si.setVisibility(View.INVISIBLE);
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
                       //para ocultar la imagen y los botnes si y no nuevamente
                       imagen.setVisibility(View.INVISIBLE);
                       btn_no.setVisibility(View.INVISIBLE);
                       btn_si.setVisibility(View.INVISIBLE);
                   }
               }, 3000);
           }



           //FUNCION ON PAUSE DE LA CLASE
           @Override
           public void onPause() {
               handler.removeCallbacks(runnable_iniciar);

               if(sonido != null) {
                   sonido.stop();
                   sonido.release();
                   sonido = null;
               }

               super.onPause();
           }

}//final
