package com.example.marglov2.FragmentsIdentificación;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.marglov2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Actividad_ident_4_felicitacionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Actividad_ident_4_felicitacionFragment extends Fragment {

    //Declaracion de variables
    int puntajeSuma;
    //Declaracion VAriables
    TextView puntajeTextview;
    MediaPlayer sonido;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Actividad_ident_4_felicitacionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Actividad_ident_4_felicitacionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Actividad_ident_4_felicitacionFragment newInstance(String param1, String param2) {
        Actividad_ident_4_felicitacionFragment fragment = new Actividad_ident_4_felicitacionFragment();
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
        return inflater.inflate(R.layout.fragment_actividad_ident_4_felicitacion, container, false);
    }

//Se recomienda crear onviewcreated para trabajar c uando la lista esta creada correctamente
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(view); //nacControllermio es el nombre de la instancia

        //sonido felicitaciones
        sonido = MediaPlayer.create(getActivity(), R.raw.felicitaciones);
        sonido.start();

        //leer y mostrar puntaje de shared preferences
        SharedPreferences preferencespuntaje = getActivity().getSharedPreferences("puntajes_table", Context.MODE_PRIVATE);
        int puntaje = preferencespuntaje.getInt("puntaje",0);

        //Poner texto puntaje actividad
        String puntaje_txt = String.valueOf(puntaje);
        puntajeTextview = view.findViewById(R.id.textViewPuntaje_id);
        puntajeTextview.setText(puntaje_txt);

        //leer y mostrar puntaje TOTAL de shared preferences
        SharedPreferences preferencestotalpuntaje = getActivity().getSharedPreferences("total_puntajes_table", Context.MODE_PRIVATE);
        int puntajeTotal = preferencestotalpuntaje.getInt("total_puntaje",0);
        puntajeSuma = puntajeTotal + puntaje;


//boton intentar
        Button btn_intentar = view.findViewById(R.id.btn_reintentar);

        btn_intentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navControllermio.navigate(R.id. action_actividad_ident_4_felicitacionFragment_to_actividad_ident_4Fragment); //pasar a otro fragment con actions

                //Para actualizar barra de progreso
                guardarPreferenciasTotalPuntaje();
                getActivity().recreate();

            }

        });



//boton Siguiente
        Button btn_siguiente = view.findViewById(R.id.btn_siguienteid);

        btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navControllermio.navigate(R.id.action_actividad_ident_4_felicitacionFragment_to_actividad_comp_1Fragment);//pasar a otro fragment con actions
                //Para actualizar barra de progreso
                guardarPreferenciasTotalPuntaje();
                getActivity().recreate();
            }

        });

        //FUNCION PARA EL BOTON HACIA ATRAS PARA GUARDAR LOS PUNTAJES
        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                navControllermio.navigate(R.id. action_actividad_ident_4_felicitacionFragment_to_actividad_ident_4Fragment); //pasar a otro fragment con actions
                //Para actualizar barra de progreso
                guardarPreferenciasTotalPuntaje();
                getActivity().recreate();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()

    }

    //Shared PReferences para el TOTAL puntajes
    private void guardarPreferenciasTotalPuntaje(){
        SharedPreferences preferencestotalpuntaje = getActivity().getSharedPreferences("total_puntajes_table", Context.MODE_PRIVATE);
        int total_puntaje = puntajeSuma;
        SharedPreferences.Editor editor = preferencestotalpuntaje.edit();
        editor.putInt("total_puntaje", total_puntaje);
        editor.commit();
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


}