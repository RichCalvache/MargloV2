package com.example.marglov2.OtrosFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.marglov2.Entidades.DBHelperPuntajes;
import com.example.marglov2.R;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PuntajesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PuntajesFragment extends Fragment {

    DBHelperPuntajes DB;
    ProgressBar progressBar;
    int puntajeTotal;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PuntajesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PuntajesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PuntajesFragment newInstance(String param1, String param2) {
        PuntajesFragment fragment = new PuntajesFragment();
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
        return inflater.inflate(R.layout.fragment_puntajes, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(view); //nacControllermio es el nombre de la instancia


        //boton
        Button btn_historial = view.findViewById(R.id.historial_btnid);
        Button btn_graficas = view.findViewById(R.id.graficas_btnid);


        //leer y mostrar puntaje TOTAL de shared preferences
        SharedPreferences preferencestotalpuntaje = getActivity().getSharedPreferences("total_puntajes_table", Context.MODE_PRIVATE);
        int puntajeTotal = preferencestotalpuntaje.getInt("total_puntaje",0);

        //barra de progreso
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        progressBar.setMax(500);
        progressBar.setProgress(puntajeTotal);

        btn_historial .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //MOSTRAR PUNTAJES
                DB = new DBHelperPuntajes(getContext());
                Cursor res = DB.getdataPuntajes();
                if (res.getCount() == 0) {
                    Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Fecha: " + res.getString(0) + "\n");
                    buffer.append("Actividad: " + res.getString(1) + "\n");
                    buffer.append("Habilidad: " + res.getString(2) + "\n");
                    buffer.append("Puntaje: " + res.getString(3) + "\n");
                    buffer.append("Información: " + res.getString(4) + "\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                builder.setTitle("Historial de Puntajes:");
                builder.setMessage(buffer.toString());
                builder.show();

            }

        });


        //FUNCION PARA EL BOTON GRAFICAS
        btn_graficas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //navControllermio.navigate(R.id.actividad_conc_1_ayudasFragment); //pasar a otro fragment
                //navControllermio.navigate(R.id.action_actividad_discr_3Fragment_to_actividad_discr_3_instruccionesFragment); //pasar a otro fragment con actions
                navControllermio.navigate(R.id.action_puntajesFragment_to_graficasGeneralFragment);


            }
        });

    }


}