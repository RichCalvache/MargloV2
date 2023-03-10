package com.example.marglov2.FragmentsComprension;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

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
 * Use the {@link Actividad_comp_3_erradoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Actividad_comp_3_erradoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Actividad_comp_3_erradoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Actividad_comp_3_erradoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Actividad_comp_3_erradoFragment newInstance(String param1, String param2) {
        Actividad_comp_3_erradoFragment fragment = new Actividad_comp_3_erradoFragment();
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
        return inflater.inflate(R.layout.fragment_actividad_comp_3_errado, container, false);
    }



    //Se recomienda crear onviewcreated para trabajar c uando la lista esta creada correctamente
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(view); //nacControllermio es el nombre de la instancia

        //boton
        Button btn_reintentar = view.findViewById(R.id.btn_reintentar);
        TextView puntajeTextview;

        //leer y mostrar puntaje de shared preferences
        SharedPreferences preferencespuntaje = getActivity().getSharedPreferences("puntajes_table", Context.MODE_PRIVATE);
        int puntaje = preferencespuntaje.getInt("puntaje",0);

        String puntaje_txt = String.valueOf(puntaje);
        puntajeTextview = view.findViewById(R.id.textViewPuntaje_id);
        puntajeTextview.setText(puntaje_txt);

        btn_reintentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //navControllermio.navigate(R.id.actividad_conc_3Fragment); //pasar a otro fragment
                navControllermio.navigate(R.id.action_actividad_comp_3_erradoFragment_to_actividad_comp_3Fragment); //pasar a otro fragment con actions


            }
        });

    }


}//final