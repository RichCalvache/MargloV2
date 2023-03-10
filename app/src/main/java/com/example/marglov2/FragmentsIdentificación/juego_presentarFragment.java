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

import com.example.marglov2.Adaptadores.AdaptadorCartas2;
import com.example.marglov2.Entidades.CartasVo2;
import com.example.marglov2.Entidades.DBHelperPuntajes;
import com.example.marglov2.Interfaces.RecyclerClickJuegoInterface;
import com.example.marglov2.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link juego_presentarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class juego_presentarFragment extends Fragment implements RecyclerClickJuegoInterface {

    ArrayList<CartasVo2> listaCartas,listaTotal,listaImagenes,listaSonidos, listaImagenesPreseleccion, listaSonidosPreseleccion;
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

    public juego_presentarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment juego_presentarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static juego_presentarFragment newInstance(String param1, String param2) {
        juego_presentarFragment fragment = new juego_presentarFragment();
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
        View vista = inflater.inflate(R.layout.fragment_juego_presentar, container, false);

        listaCartas = new ArrayList<>();
        listaTotal = new ArrayList<>();

        listaImagenes = new ArrayList<>();
        listaSonidos = new ArrayList<>();

        listaImagenesPreseleccion = new ArrayList<>();
        listaSonidosPreseleccion = new ArrayList<>();


        recyclerJuego = (RecyclerView) vista.findViewById(R.id.recyclerJuegoId);
        recyclerJuego.setLayoutManager(new GridLayoutManager(getContext(), 3));//para ver el recyler con dos columnas

        llenarCartas();
        AdaptadorCartas2 adapter = new AdaptadorCartas2(listaCartas, juego_presentarFragment.this);
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
                navControllermio.navigate(R.id.actividad_ident_1_juego_instruccionesFragment);

                if(sonido != null) {
                    sonido.stop();
                    sonido.release();
                    sonido = null;
                }

            }
        });
    }


    private void llenarCartas() {

        listaImagenes.add(new CartasVo2(1,R.drawable.logofinal8,R.drawable.gallo, R.raw.gallo,"gallo"));
        listaImagenes.add(new CartasVo2(2,R.drawable.logofinal8,R.drawable.perro_ladrando, R.raw.perro_corto,"perro"));
        //rana se condnunde con cerdo listaTotal.add(new CartasVo(R.drawable.logofinal8,R.drawable.rana, R.raw.rana,"rana"));
        listaImagenes.add(new CartasVo2(3,R.drawable.logofinal8,R.drawable.flauta, R.raw.flauta,"flauta"));
        listaImagenes.add(new CartasVo2(4,R.drawable.logofinal8,R.drawable.moto24, R.raw.moto_corto,"moto"));
        listaImagenes.add(new CartasVo2(5,R.drawable.logofinal8,R.drawable.vaca, R.raw.vaca_corto,"gallo"));
        listaImagenes.add(new CartasVo2(6,R.drawable.logofinal8,R.drawable.ambulancia, R.raw.ambulancia_corto,"ambulancia"));
        listaImagenes.add(new CartasVo2(7,R.drawable.logofinal8,R.drawable.cerdo, R.raw.cerdo,"cerdo"));
        listaImagenes.add(new CartasVo2(8,R.drawable.logofinal8,R.drawable.cuy, R.raw.cuy,"cuy"));
        listaImagenes.add(new CartasVo2(9,R.drawable.logofinal8,R.drawable.disparos, R.raw.disparos,"disparos"));
        listaImagenes.add(new CartasVo2(10,R.drawable.logofinal8,R.drawable.buho, R.raw.buho,"buho"));
        listaImagenes.add(new CartasVo2(11,R.drawable.logofinal8,R.drawable.alarma, R.raw.alarm3seg,"alarma"));

        listaSonidos.add(new CartasVo2(101,R.drawable.bocinainstruccion,R.drawable.gallo, R.raw.gallo,"gallo"));
        listaSonidos.add(new CartasVo2(102,R.drawable.bocinainstruccion,R.drawable.perro_ladrando, R.raw.perro_corto,"perro"));
        //rana se condnunde con cerdo listaTotal.add(new CartasVo(R.drawable.logofinal8,R.drawable.rana, R.raw.rana,"rana"));
        listaSonidos.add(new CartasVo2(103,R.drawable.bocinainstruccion,R.drawable.flauta, R.raw.flauta,"flauta"));
        listaSonidos.add(new CartasVo2(104,R.drawable.bocinainstruccion,R.drawable.moto24, R.raw.moto_corto,"moto"));
        listaSonidos.add(new CartasVo2(105,R.drawable.bocinainstruccion,R.drawable.vaca, R.raw.vaca_corto,"gallo"));
        listaSonidos.add(new CartasVo2(106,R.drawable.bocinainstruccion,R.drawable.ambulancia, R.raw.ambulancia_corto,"ambulancia"));
        listaSonidos.add(new CartasVo2(107,R.drawable.bocinainstruccion,R.drawable.cerdo, R.raw.cerdo,"cerdo"));
        listaSonidos.add(new CartasVo2(108,R.drawable.bocinainstruccion,R.drawable.cuy, R.raw.cuy,"cuy"));
        listaSonidos.add(new CartasVo2(109,R.drawable.bocinainstruccion,R.drawable.disparos, R.raw.disparos,"disparos"));
        listaSonidos.add(new CartasVo2(110,R.drawable.bocinainstruccion,R.drawable.buho, R.raw.buho,"buho"));
        listaSonidos.add(new CartasVo2(111,R.drawable.bocinainstruccion,R.drawable.alarma, R.raw.alarm3seg,"alarma"));

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

            CartasVo2 randomImagenes = listaImagenes.get(randomIntCarta);
            CartasVo2 randomSonidos = listaSonidos.get(randomIntCarta);
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

            CartasVo2 preselectImagenes = listaImagenesPreseleccion.get(p);
            CartasVo2 preselectSonidos = listaSonidosPreseleccion.get(p);
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
}