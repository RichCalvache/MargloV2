package com.example.marglov2.FragmentsIdentificación;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marglov2.R;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Actividad_ident_2_instruccionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Actividad_ident_2_instruccionesFragment extends Fragment {

    //Declaracion de variables
    private ImageView imagen, imageninstrucc;
    private Button instrucciones, ejemplo,parar;
    private TextView textViewReproducirInstrucc, textViewInstrucc,textViewReproducirEjemplo;
    MediaPlayer sonido_instrucciones, sonido_ejemplo;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Actividad_ident_2_instruccionesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Actividad_ident_2_instruccionesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Actividad_ident_2_instruccionesFragment newInstance(String param1, String param2) {
        Actividad_ident_2_instruccionesFragment fragment = new Actividad_ident_2_instruccionesFragment();
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
        return inflater.inflate(R.layout.fragment_actividad_ident_2_instrucciones, container, false);
    }




    //creacion del onviewcreated recomendado
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //imagen = view.findViewById(R.id.imagen);
        instrucciones = view.findViewById(R.id.btn_playInstrucciones);
        ejemplo = view.findViewById(R.id.btn_playEjemplo);
        parar = view.findViewById(R.id.btn_pararInstrucciones);

        textViewReproducirInstrucc = view.findViewById(R.id.textviewReproducirInstrucc);
        textViewReproducirEjemplo = view.findViewById(R.id.textviewReproducirEjemplo);
        textViewInstrucc = view.findViewById(R.id.textviewInstrucc);
        //imageninstrucc = view.findViewById(R.id.imagenInstrucc);

        //Reproducir sonidos
        //sonido_instrucciones = MediaPlayer.create(getActivity(), R.raw.ident_4_instrucciones);
        //sonido_ejemplo = MediaPlayer.create(getActivity(), R.raw.ident_3_ejemplo);


        instrucciones.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Toast.makeText(getContext(), "¡FALTAN LAS GRABACIONES!", Toast.LENGTH_SHORT).show();
                /*
                sonido_instrucciones = MediaPlayer.create(getActivity(), R.raw.ident_4_instrucciones);

                //defino visibilidad objetos botones y textos
                textViewInstrucc.setVisibility(View.VISIBLE);
                parar.setVisibility(View.VISIBLE);

                ejemplo.setVisibility(View.INVISIBLE);
                textViewReproducirEjemplo.setVisibility(View.INVISIBLE);

                sonido_instrucciones.start();
                sonido_instrucciones.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        //defino visibilidad objetos botones y textos
                        textViewInstrucc.setVisibility(View.VISIBLE);

                        ejemplo.setVisibility(View.VISIBLE);
                        textViewReproducirEjemplo.setVisibility(View.VISIBLE);
                        parar.setVisibility(View.INVISIBLE);
                    }
                });
                 */



            }
        });

        ejemplo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Toast.makeText(getContext(), "¡FALTAN LAS GRABACIONES!", Toast.LENGTH_SHORT).show();
/*
                sonido_ejemplo = MediaPlayer.create(getActivity(), R.raw.conc_2_ejemplo);

                //defino visibilidad objetos botones y textos
                textViewInstrucc.setVisibility(View.INVISIBLE);
                parar.setVisibility(View.VISIBLE);

                instrucciones.setVisibility(View.INVISIBLE);
                textViewReproducirInstrucc.setVisibility(View.INVISIBLE);
                sonido_ejemplo.start();


                sonido_ejemplo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        //defino visibilidad objetos botones y textos
                        textViewInstrucc.setVisibility(View.VISIBLE);

                        instrucciones.setVisibility(View.VISIBLE);
                        textViewReproducirInstrucc.setVisibility(View.VISIBLE);
                        parar.setVisibility(View.INVISIBLE);
                    }
                });

 */



            }
        });

        parar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                if(sonido_instrucciones != null) {
                    sonido_instrucciones.stop();
                    sonido_instrucciones.release();
                    sonido_instrucciones=null;
                }
                if(sonido_ejemplo != null) {
                    sonido_ejemplo.stop();
                    sonido_ejemplo.release();
                    sonido_ejemplo =null;
                }

                //defino visibilidad objetos botones y textos
                textViewInstrucc.setVisibility(View.VISIBLE);

                ejemplo.setVisibility(View.VISIBLE);
                textViewReproducirEjemplo.setVisibility(View.VISIBLE);
                instrucciones.setVisibility(View.VISIBLE);
                textViewReproducirInstrucc.setVisibility(View.VISIBLE);
                parar.setVisibility(View.INVISIBLE);

            }
        });


    }

    //FUNCION ON PAUSE DE LA CLASE
    @Override
    public void onPause() {

        if(sonido_instrucciones != null) {
            sonido_instrucciones.stop();
            sonido_instrucciones.release();
            sonido_instrucciones = null;
        }
        if(sonido_ejemplo != null) {
            sonido_ejemplo.stop();
            sonido_ejemplo.release();
            sonido_ejemplo = null;
        }
        super.onPause();
    }

}