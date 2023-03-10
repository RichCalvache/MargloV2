package com.example.marglov2.FragmentsIdentificación;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marglov2.Adaptadores.AdaptadorCartas;
import com.example.marglov2.Entidades.CartasVo;
import com.example.marglov2.Entidades.DBHelperPuntajes;
import com.example.marglov2.Interfaces.RecyclerClickJuegoInterface;
import com.example.marglov2.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Actividad_ident_1_juegoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Actividad_ident_1_juegoFragment extends Fragment implements RecyclerClickJuegoInterface {

    ArrayList<CartasVo> listaCartas,listaTotal,listaImagenes,listaSonidos, listaImagenesPreseleccion, listaSonidosPreseleccion;
    RecyclerView recyclerJuego;

    MediaPlayer sonido;

    int randomIntCarta_anterior;
    int randomIntCarta;

    //Declaracion de variables
    private ImageView imagen_centro, imagen1,imagen2,imagen3;
    private Button iniciar, parar,instrucciones;
    private TextView textViewAciertos, textViewInstrucc, textViewFallaste, textViewSeleccione, textViewEscuche;
    MediaPlayer sonido1,sonido2;
    int i, aciertos_actividad, global_retardo;

    private boolean isBusy= false;

    DBHelperPuntajes DB;






    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Actividad_ident_1_juegoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Actividad_ident_1_juegoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Actividad_ident_1_juegoFragment newInstance(String param1, String param2) {
        Actividad_ident_1_juegoFragment fragment = new Actividad_ident_1_juegoFragment();
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
        View vista = inflater.inflate(R.layout.fragment_actividad_ident_1_juego, container, false);

        listaCartas = new ArrayList<>();
        listaTotal = new ArrayList<>();

        listaImagenes = new ArrayList<>();
        listaSonidos = new ArrayList<>();

        listaImagenesPreseleccion = new ArrayList<>();
        listaSonidosPreseleccion = new ArrayList<>();


        recyclerJuego = (RecyclerView) vista.findViewById(R.id.recyclerJuegoId);
        recyclerJuego.setLayoutManager(new GridLayoutManager(getContext(), 3));//para ver el recyler con dos columnas

        llenarCartas();
        AdaptadorCartas adapter = new AdaptadorCartas(listaCartas, Actividad_ident_1_juegoFragment.this);
        recyclerJuego.setAdapter(adapter);


        //JUEGO
        iniciar = vista.findViewById(R.id.btn_iniciar);
        parar = vista.findViewById(R.id.btn_parar);
        instrucciones = vista.findViewById(R.id.btn_instrucciones);
        textViewAciertos = vista.findViewById(R.id.textViewAciertosid);
        textViewFallaste = vista.findViewById(R.id.textViewFallasteid);
        textViewInstrucc = vista.findViewById(R.id.textviewInstrucc);



        //bd
        DB = new DBHelperPuntajes(getContext());

        return vista;
    }

    //creacion del onviewcreated recomendado
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(getView()); //nacControllermio es el nombre de la instancia


        //FUNCION PARA EL BOTON INICIAR
        iniciar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //i=0;
                //aciertos_actividad = 0;

                //defino visibilidad objetos botones y textos
                parar.setVisibility(View.VISIBLE);
                iniciar.setVisibility(View.INVISIBLE);
                instrucciones.setVisibility(View.INVISIBLE);
                textViewInstrucc.setVisibility(View.INVISIBLE);
                recyclerJuego.setVisibility(View.VISIBLE);




                //inicio el runnable despues de 1-3 segundos
                //handler_escuchar.removeCallbacks(runnable_escuchar);
                //handler_escuchar.postDelayed(runnable_escuchar, 2000);//inicia despues de 2 segundos

            }
        });


        //FUNCION PARA EL BOTON PARAR
        parar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //i=0;
                //aciertos_actividad = 0;

                //defino visibilidad objetos botones y textos
                parar.setVisibility(View.INVISIBLE);
                iniciar.setVisibility(View.VISIBLE);
                instrucciones.setVisibility(View.VISIBLE);
                textViewInstrucc.setVisibility(View.INVISIBLE);
                recyclerJuego.setVisibility(View.INVISIBLE);

                if(sonido != null) {
                    sonido.stop();
                    sonido.release();
                    sonido = null;
                }

                getActivity().recreate();

                //inicio el runnable despues de 1-3 segundos
                //handler_escuchar.removeCallbacks(runnable_escuchar);
                //handler_escuchar.postDelayed(runnable_escuchar, 2000);//inicia despues de 2 segundos

            }
        });


        //FUNCION PARA EL BOTON INSTRUCCIONES
        instrucciones.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //navControllermio.navigate(R.id.actividad_conc_2_ayudasFragment); //pasar a otro fragment
                navControllermio.navigate(R.id.action_actividad_ident_1_juegoFragment_to_actividad_ident_1_juego_instruccionesFragment);

                if(sonido != null) {
                    sonido.stop();
                    sonido.release();
                    sonido = null;
                }

            }
        });
    }


    private void llenarCartas() {

        listaImagenes.add(new CartasVo(1,R.drawable.logofinal8,R.drawable.gallo, R.raw.gallo,R.drawable.gallo));
        listaImagenes.add(new CartasVo(2,R.drawable.logofinal8,R.drawable.perro_ladrando, R.raw.perro_corto,R.drawable.perro_ladrando));
        //rana se condnunde con cerdo listaTotal.add(new CartasVo(R.drawable.logofinal8,R.drawable.rana, R.raw.rana,"rana"));
        listaImagenes.add(new CartasVo(3,R.drawable.logofinal8,R.drawable.flauta, R.raw.flauta,R.drawable.flauta));
        listaImagenes.add(new CartasVo(4,R.drawable.logofinal8,R.drawable.moto24, R.raw.moto_corto,R.drawable.moto24));
        listaImagenes.add(new CartasVo(5,R.drawable.logofinal8,R.drawable.vaca, R.raw.vaca_corto,R.drawable.vaca));
        listaImagenes.add(new CartasVo(6,R.drawable.logofinal8,R.drawable.ambulancia, R.raw.ambulancia_corto,R.drawable.ambulancia));
        listaImagenes.add(new CartasVo(7,R.drawable.logofinal8,R.drawable.cerdo, R.raw.cerdo,R.drawable.cerdo));
        listaImagenes.add(new CartasVo(8,R.drawable.logofinal8,R.drawable.cuy, R.raw.cuy,R.drawable.cuy));
        listaImagenes.add(new CartasVo(9,R.drawable.logofinal8,R.drawable.disparos, R.raw.disparos,R.drawable.disparos));
        listaImagenes.add(new CartasVo(10,R.drawable.logofinal8,R.drawable.buho, R.raw.buho,R.drawable.buho));
        listaImagenes.add(new CartasVo(11,R.drawable.logofinal8,R.drawable.alarma, R.raw.alarm3seg,R.drawable.alarma));
        listaImagenes.add(new CartasVo(12,R.drawable.logofinal8,R.drawable.pollito, R.raw.pollitolargo,R.drawable.pollito));
        listaImagenes.add(new CartasVo(13,R.drawable.logofinal8,R.drawable.trompeta, R.raw.trompetacampamento,R.drawable.trompeta));
        listaImagenes.add(new CartasVo(14,R.drawable.logofinal8,R.drawable.silbato, R.raw.silbato,R.drawable.silbato));


        //Asi al voltear un sonido se muestra la imagen de las ondas sonoras
        listaSonidos.add(new CartasVo(101,R.drawable.bocina_color,R.drawable.ondas_sonoras, R.raw.gallo,R.drawable.gallo));
        listaSonidos.add(new CartasVo(102,R.drawable.bocina_color,R.drawable.ondas_sonoras, R.raw.perro_corto,R.drawable.perro_ladrando));
        //rana se condnunde con cerdo listaTotal.add(new CartasVo(R.drawable.logofinal8,R.drawable.rana, R.raw.rana,"rana"));
        listaSonidos.add(new CartasVo(103,R.drawable.bocina_color,R.drawable.ondas_sonoras, R.raw.flauta,R.drawable.flauta));
        listaSonidos.add(new CartasVo(104,R.drawable.bocina_color,R.drawable.ondas_sonoras, R.raw.moto_corto,R.drawable.moto24));
        listaSonidos.add(new CartasVo(105,R.drawable.bocina_color,R.drawable.ondas_sonoras, R.raw.vaca_corto,R.drawable.vaca));
        listaSonidos.add(new CartasVo(106,R.drawable.bocina_color,R.drawable.ondas_sonoras, R.raw.ambulancia_corto,R.drawable.ambulancia));
        listaSonidos.add(new CartasVo(107,R.drawable.bocina_color,R.drawable.ondas_sonoras, R.raw.cerdo,R.drawable.cerdo));
        listaSonidos.add(new CartasVo(108,R.drawable.bocina_color,R.drawable.ondas_sonoras, R.raw.cuy,R.drawable.cuy));
        listaSonidos.add(new CartasVo(109,R.drawable.bocina_color,R.drawable.ondas_sonoras, R.raw.disparos,R.drawable.disparos));
        listaSonidos.add(new CartasVo(110,R.drawable.bocina_color,R.drawable.ondas_sonoras, R.raw.buho,R.drawable.buho));
        listaSonidos.add(new CartasVo(111,R.drawable.bocina_color,R.drawable.ondas_sonoras, R.raw.alarm3seg,R.drawable.alarma));
        listaSonidos.add(new CartasVo(112,R.drawable.bocina_color,R.drawable.ondas_sonoras, R.raw.pollitolargo,R.drawable.pollito));
        listaSonidos.add(new CartasVo(113,R.drawable.bocina_color,R.drawable.ondas_sonoras, R.raw.trompetacampamento,R.drawable.trompeta));
        listaSonidos.add(new CartasVo(114,R.drawable.bocina_color,R.drawable.ondas_sonoras, R.raw.silbato,R.drawable.silbato));

        /*
//Asi al voltear pone imagen
        listaSonidos.add(new CartasVo(101,R.drawable.bocina_color,R.drawable.gallo, R.raw.gallo,"gallo"));
        listaSonidos.add(new CartasVo(102,R.drawable.bocina_color,R.drawable.perro_ladrando, R.raw.perro_corto,"perro"));
        //rana se condnunde con cerdo listaTotal.add(new CartasVo(R.drawable.logofinal8,R.drawable.rana, R.raw.rana,"rana"));
        listaSonidos.add(new CartasVo(103,R.drawable.bocina_color,R.drawable.flauta, R.raw.flauta,"flauta"));
        listaSonidos.add(new CartasVo(104,R.drawable.bocina_color,R.drawable.moto24, R.raw.moto_corto,"moto"));
        listaSonidos.add(new CartasVo(105,R.drawable.bocina_color,R.drawable.vaca, R.raw.vaca_corto,"gallo"));
        listaSonidos.add(new CartasVo(106,R.drawable.bocina_color,R.drawable.ambulancia, R.raw.ambulancia_corto,"ambulancia"));
        listaSonidos.add(new CartasVo(107,R.drawable.bocina_color,R.drawable.cerdo, R.raw.cerdo,"cerdo"));
        listaSonidos.add(new CartasVo(108,R.drawable.bocina_color,R.drawable.cuy, R.raw.cuy,"cuy"));
        listaSonidos.add(new CartasVo(109,R.drawable.bocina_color,R.drawable.disparos, R.raw.disparos,"disparos"));
        listaSonidos.add(new CartasVo(110,R.drawable.bocina_color,R.drawable.buho, R.raw.buho,"buho"));
        listaSonidos.add(new CartasVo(111,R.drawable.bocina_color,R.drawable.alarma, R.raw.alarm3seg,"alarma"));
*/
/*
        listaSonidos.add(new CartasVo(R.drawable.logofinal8,R.drawable.bocinainstruccion, R.raw.gallo,"gallo"));
        listaSonidos.add(new CartasVo(R.drawable.logofinal8,R.drawable.bocinainstruccion, R.raw.perro_corto,"perro"));
        //rana se condnunde con cerdo listaTotal.add(new CartasVo(R.drawable.logofinal8,R.drawable.rana, R.raw.rana,"rana"));
        listaSonidos.add(new CartasVo(R.drawable.logofinal8,R.drawable.bocinainstruccion, R.raw.flauta,"flauta"));
        listaSonidos.add(new CartasVo(R.drawable.logofinal8,R.drawable.bocinainstruccion, R.raw.moto_corto,"moto"));
        listaSonidos.add(new CartasVo(R.drawable.logofinal8,R.drawable.bocinainstruccion, R.raw.vaca_corto,"gallo"));
        listaSonidos.add(new CartasVo(R.drawable.logofinal8,R.drawable.bocinainstruccion, R.raw.ambulancia_corto,"ambulancia"));
        listaSonidos.add(new CartasVo(R.drawable.logofinal8,R.drawable.bocinainstruccion, R.raw.cerdo,"cerdo"));
        listaSonidos.add(new CartasVo(R.drawable.logofinal8,R.drawable.bocinainstruccion, R.raw.cuy,"cuy"));
        listaSonidos.add(new CartasVo(R.drawable.logofinal8,R.drawable.bocinainstruccion, R.raw.disparos,"disparos"));
        listaSonidos.add(new CartasVo(R.drawable.logofinal8,R.drawable.bocinainstruccion, R.raw.buho,"buho"));
        listaSonidos.add(new CartasVo(R.drawable.logofinal8,R.drawable.bocinainstruccion, R.raw.alarm3seg,"alarma"));
*/





        //para preseleccionar las cartas y revolverlas
        for (int j=0;j<=5;j++){

            //Generacion Aleatoria de numeros
            //Random rand;
            randomIntCarta = (new Random().nextInt(listaImagenes.size()));

            if(randomIntCarta_anterior == randomIntCarta){

                do {
                    //Generacion Aleatoria de numeros
                    randomIntCarta = (new Random().nextInt(listaImagenes.size()));
                }while (randomIntCarta_anterior == randomIntCarta);
            }

            CartasVo randomImagenes = listaImagenes.get(randomIntCarta);
            CartasVo randomSonidos = listaSonidos.get(randomIntCarta);
            listaImagenesPreseleccion.add(randomImagenes);
            listaSonidosPreseleccion.add(randomSonidos);
            listaImagenes.remove(randomImagenes);
            listaSonidos.remove(randomSonidos);


        }
        randomIntCarta_anterior = randomIntCarta;

        //REvolver la lista de cartas
        Collections.shuffle(listaImagenesPreseleccion);
        Collections.shuffle(listaSonidosPreseleccion);

        //para llenar las cartas
        for (int p=0;p<=5;p++){

            CartasVo preselectImagenes = listaImagenesPreseleccion.get(p);
            CartasVo preselectSonidos = listaSonidosPreseleccion.get(p);
            listaCartas.add(preselectImagenes);
            listaCartas.add(preselectSonidos);

        }




        //para llenar la lista dos veces manualmente
        /*for (int j=0;j<=1;j++){
            listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.gallo, R.raw.gallo,"gallo"));
            listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.perro_ladrando, R.raw.perro_corto,"perro"));
            listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.rana, R.raw.rana,"rana"));
            listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.flauta, R.raw.flauta,"flauta"));
            listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.moto24, R.raw.moto_corto,"moto"));
            listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.vaca, R.raw.vaca_corto,"gallo"));
        }*/
/*
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.gallo, R.raw.gallo,"gallo"));
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.perro_ladrando, R.raw.perro_corto,"perro"));
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.rana, R.raw.rana,"rana"));
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.flauta, R.raw.flauta,"flauta"));
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.moto24, R.raw.moto_corto,"moto"));
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.vaca, R.raw.vaca_corto,"gallo"));

        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.gallo, R.raw.gallo,"gallo"));
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.perro_ladrando, R.raw.perro_corto,"perro"));
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.rana, R.raw.rana,"rana"));
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.flauta, R.raw.flauta,"flauta"));
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.moto24, R.raw.moto_corto,"moto"));
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.vaca, R.raw.vaca_corto,"gallo"));
*/

    }

    /*
    private void llenarCartas1() {

        listaTotal.add(new CartasVo(R.drawable.logofinal8,R.drawable.gallo, R.raw.gallo,"gallo"));
        listaTotal.add(new CartasVo(R.drawable.logofinal8,R.drawable.perro_ladrando, R.raw.perro_corto,"perro"));
        //rana se condnunde con cerdo listaTotal.add(new CartasVo(R.drawable.logofinal8,R.drawable.rana, R.raw.rana,"rana"));
        listaTotal.add(new CartasVo(R.drawable.logofinal8,R.drawable.flauta, R.raw.flauta,"flauta"));
        listaTotal.add(new CartasVo(R.drawable.logofinal8,R.drawable.moto24, R.raw.moto_corto,"moto"));
        listaTotal.add(new CartasVo(R.drawable.logofinal8,R.drawable.vaca, R.raw.vaca_corto,"gallo"));
        listaTotal.add(new CartasVo(R.drawable.logofinal8,R.drawable.ambulancia, R.raw.ambulancia_corto,"ambulancia"));
        listaTotal.add(new CartasVo(R.drawable.logofinal8,R.drawable.cerdo, R.raw.cerdo,"cerdo"));
        listaTotal.add(new CartasVo(R.drawable.logofinal8,R.drawable.cuy, R.raw.cuy,"cuy"));
        listaTotal.add(new CartasVo(R.drawable.logofinal8,R.drawable.disparos, R.raw.disparos,"disparos"));
        listaTotal.add(new CartasVo(R.drawable.logofinal8,R.drawable.buho, R.raw.buho,"buho"));
        listaTotal.add(new CartasVo(R.drawable.logofinal8,R.drawable.alarma, R.raw.alarm3seg,"alarma"));

        //para llenar la lista dos veces aleatoriamente
        //int randomIntCarta_anterior=-1;
        //int randomIntCarta=0;
        for (int j=0;j<=5;j++){

            //Generacion Aleatoria de numeros
            //Random rand;
            randomIntCarta = (new Random().nextInt(listaTotal.size()));

            if(randomIntCarta_anterior == randomIntCarta){

                do {
                    //Generacion Aleatoria de numeros
                    randomIntCarta = (new Random().nextInt(listaTotal.size()));
                }while (randomIntCarta_anterior == randomIntCarta);
            }

            CartasVo random = listaTotal.get(randomIntCarta);
            listaCartas.add(random);
            listaCartas.add(random);
            listaTotal.remove(random);

        }
        randomIntCarta_anterior = randomIntCarta;
        Collections.shuffle(listaCartas);




        //para llenar la lista dos veces manualmente
        /*for (int j=0;j<=1;j++){
            listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.gallo, R.raw.gallo,"gallo"));
            listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.perro_ladrando, R.raw.perro_corto,"perro"));
            listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.rana, R.raw.rana,"rana"));
            listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.flauta, R.raw.flauta,"flauta"));
            listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.moto24, R.raw.moto_corto,"moto"));
            listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.vaca, R.raw.vaca_corto,"gallo"));
        }*/


/*
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.gallo, R.raw.gallo,"gallo"));
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.perro_ladrando, R.raw.perro_corto,"perro"));
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.rana, R.raw.rana,"rana"));
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.flauta, R.raw.flauta,"flauta"));
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.moto24, R.raw.moto_corto,"moto"));
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.vaca, R.raw.vaca_corto,"gallo"));

        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.gallo, R.raw.gallo,"gallo"));
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.perro_ladrando, R.raw.perro_corto,"perro"));
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.rana, R.raw.rana,"rana"));
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.flauta, R.raw.flauta,"flauta"));
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.moto24, R.raw.moto_corto,"moto"));
        listaCartas.add(new CartasVo(R.drawable.logofinal8,R.drawable.vaca, R.raw.vaca_corto,"gallo"));
*/

    //}



    @Override
    public void onItemClick(int position) {




//METODO PARA REPRODUCIR SONIDOS
        if(sonido != null) {
            sonido.stop();
            sonido.release();
            sonido = null;
        }

        sonido = MediaPlayer.create(getContext(), listaCartas.get(position).getSonidoId());


        sonido.start();

        sonido.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(sonido != null) {
                    sonido.stop();
                    sonido.release();
                    sonido = null;

                }
            }
        });





    }



    //FUNCION ON PAUSE DE LA CLASE
    @Override
    public void onPause() {


        if(sonido != null) {
            sonido.stop();
            sonido.release();
            sonido = null;
        }

        super.onPause();
    }

    @Override
    public void onDestroy() {
        if(sonido != null) {
            sonido.stop();
            sonido.release();
            sonido = null;
        }
        super.onDestroy();
    }

    @Override
    public void onPrimaryNavigationFragmentChanged(boolean isPrimaryNavigationFragment) {
        if(sonido != null) {
            sonido.stop();
            sonido.release();
            sonido = null;
        }
        super.onPrimaryNavigationFragmentChanged(isPrimaryNavigationFragment);
    }

}