package com.example.marglov2.FragmentsComprension;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marglov2.Adaptadores.AdaptadorActividadesComprension;
import com.example.marglov2.Entidades.ActividadVo;
import com.example.marglov2.Interfaces.RecyclerClickActividadesInterface;
import com.example.marglov2.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaComprensionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaComprensionFragment extends Fragment implements RecyclerClickActividadesInterface {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Declaracion de recycler y arraylist
    ArrayList<ActividadVo> listaActividades;
    RecyclerView recyclerActividadesComprension;

    public ListaComprensionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaComprensionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaComprensionFragment newInstance(String param1, String param2) {
        ListaComprensionFragment fragment = new ListaComprensionFragment();
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
        View vista =inflater.inflate(R.layout.fragment_lista_comprension, container, false);

        listaActividades =  new ArrayList<>();
        recyclerActividadesComprension = (RecyclerView) vista.findViewById(R.id.recyclerComprensionid);
        recyclerActividadesComprension.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarActividades();

        AdaptadorActividadesComprension adapter =  new AdaptadorActividadesComprension(listaActividades, ListaComprensionFragment.this);
        recyclerActividadesComprension.setAdapter(adapter);

        return vista;
    }

    private void llenarActividades() {

        listaActividades.add(new ActividadVo(getString(R.string.comp_1_nombre),"",R.drawable.icon_comprensionflat));
        listaActividades.add(new ActividadVo(getString(R.string.comp_2_nombre),"",R.drawable.icon_comprensionflat));
        listaActividades.add(new ActividadVo(getString(R.string.comp_3_nombre),"",R.drawable.icon_comprensionflat));
        listaActividades.add(new ActividadVo(getString(R.string.comp_4_nombre),"",R.drawable.icon_comprensionflat));
        listaActividades.add(new ActividadVo(getString(R.string.comp_5_nombre),"",R.drawable.icon_comprensionflat));
/*
        listaActividades.add(new ActividadVo("Actividad 1. Sigue la instrucción y responde entre 4 opciones.","El usuario elige una imagen de acuerdo a una instruccion incial",R.drawable.icon_comprensionflat));
        listaActividades.add(new ActividadVo("Actividad 2. Sigue instrucciones que contengan 4 elementos destacados dentro de un conjunto cerrado de 6 opciones.","El usuario selecciona la imagen de acuerdo a la instrucción inicial.",R.drawable.icon_comprensionflat));
        listaActividades.add(new ActividadVo("Actividad 3. En un conjunto cerrado de 8 opciones secuencia 3 elementos destacados de un mensaje.","El usuario selecciona la imagen de acuerdo a la instrucción inicial.",R.drawable.icon_comprensionflat));
        listaActividades.add(new ActividadVo("Actividad 4. Elige la imagen correspondiente a la descripción realizada.","El usuario selecciona la imagen de acuerdo a la descripción realizada.",R.drawable.icon_comprensionflat));
        listaActividades.add(new ActividadVo("Actividad 5. Responde Sí o No a cada afirmación.","El usuario debe responder Sí o No, después de escuchar una afirmación.",R.drawable.icon_comprensionflat));
/*
        listaActividades.add(new ActividadVo(getString(R.string.actividad1_nombre_comprension),getString(R.string.actividad1_descripcion_comprension), R.drawable.icon_comprensionflat));
        listaActividades.add(new ActividadVo(getString(R.string.actividad1_nombre_comprension),getString(R.string.actividad1_descripcion_comprension), R.drawable.icon_comprensionflat));
        listaActividades.add(new ActividadVo(getString(R.string.actividad1_nombre_comprension),getString(R.string.actividad1_descripcion_comprension), R.drawable.icon_comprensionflat));
        listaActividades.add(new ActividadVo(getString(R.string.actividad1_nombre_comprension),getString(R.string.actividad1_descripcion_comprension), R.drawable.icon_comprensionflat));
        listaActividades.add(new ActividadVo(getString(R.string.actividad1_nombre_comprension),getString(R.string.actividad1_descripcion_comprension), R.drawable.icon_comprensionflat));

 */
    }

    @Override
    public void onItemClick(int position) {

// funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(getView());
        System.out.println("position" + position);

        switch (position) {

            case 0:
                navControllermio.navigate(R.id.action_listaComprensionFragment_to_actividad_comp_1Fragment);
                break;
            case 1:
                navControllermio.navigate(R.id.action_listaComprensionFragment_to_actividad_comp_2Fragment);
                break;
            case 2:
                navControllermio.navigate(R.id.action_listaComprensionFragment_to_actividad_comp_3Fragment);
                break;
            case 3:
                navControllermio.navigate(R.id.action_listaComprensionFragment_to_actividad_comp_4Fragment);
                break;
            case 4:
                navControllermio.navigate(R.id.action_listaComprensionFragment_to_actividad_comp_5Fragment);
                break;

             /*
            case 0:
                navControllermio.navigate(R.id.action_listaComprensionFragment_to_actividad_comp_2Fragment);
                break;
            case 1:
                navControllermio.navigate(R.id.action_listaComprensionFragment_to_actividad_comp_3Fragment);
                break;
                */
        }

    }

}