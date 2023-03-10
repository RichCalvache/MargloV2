package com.example.marglov2.FragmentsDeteccion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marglov2.Adaptadores.AdaptadorActividadesConciencia;
import com.example.marglov2.Entidades.ActividadVo;
import com.example.marglov2.Interfaces.RecyclerClickActividadesInterface;
import com.example.marglov2.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaConcienciaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaConcienciaFragment extends Fragment implements RecyclerClickActividadesInterface {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Declaracion de recycler y arraylist
    ArrayList<ActividadVo> listaActividades;
    RecyclerView recyclerActividadesConciencia;

    public ListaConcienciaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaConcienciaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaConcienciaFragment newInstance(String param1, String param2) {
        ListaConcienciaFragment fragment = new ListaConcienciaFragment();
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

        View vista =inflater.inflate(R.layout.fragment_lista_conciencia, container, false);

        listaActividades =  new ArrayList<>();
        recyclerActividadesConciencia = (RecyclerView) vista.findViewById(R.id.recyclerConcienciaid);
        recyclerActividadesConciencia.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarActividades();

        AdaptadorActividadesConciencia adapter =  new AdaptadorActividadesConciencia(listaActividades, ListaConcienciaFragment.this);
        recyclerActividadesConciencia.setAdapter(adapter);

        return vista;

    }

    private void llenarActividades() {

        listaActividades.add(new ActividadVo(getString(R.string.conc_1_nombre),"",R.drawable.icon_concienciaflat));
        listaActividades.add(new ActividadVo(getString(R.string.conc_2_nombre),"",R.drawable.icon_concienciaflat));
        //listaActividades.add(new ActividadVo("Actividad 2. Detecta cuando se detiene un sonido continuo.","El usuario indica cuando se detiene un ruido continuo del medio ambiente, una silaba o una palabra)",R.drawable.icon_concienciaflat));

        //listaActividades.add(new ActividadVo("Actividad 1. Detecta la presencia de sonidos graves.","El usuario indica cuando escucha un sonido grave (la sílaba Ba o un ruido fuerte del medio ambiente)",R.drawable.icon_concienciaflat));
        //listaActividades.add(new ActividadVo("Actividad 2. Detecta cuando se detiene un sonido continuo.","El usuario indica cuando se detiene un ruido continuo del medio ambiente, una silaba o una palabra)",R.drawable.icon_concienciaflat));

        //listaActividades.add(new ActividadVo(getString(R.string.actividad123_nombre),getString(R.string.actividad123_descripcion),R.drawable.icon_concienciaflat));
        //listaActividades.add(new ActividadVo(getString(R.string.actividad45_nombre),getString(R.string.actividad45_descripcion),R.drawable.icon_concienciaflat));
        //listaActividades.add(new ActividadVo(getString(R.string.actividad8910_nombre),getString(R.string.actividad8910_descripcion),R.drawable.icon_concienciaflat));


        /*
        listaActividades.add(new ActividadVo(getString(R.string.actividad1_nombre),getString(R.string.actividad1_descripcion),R.drawable.icon_conciencia));
        listaActividades.add(new ActividadVo(getString(R.string.actividad2_nombre),getString(R.string.actividad2_descripcion),R.drawable.icon_conciencia));
        listaActividades.add(new ActividadVo(getString(R.string.actividad3_nombre),getString(R.string.actividad3_descripcion),R.drawable.icon_conciencia));
        listaActividades.add(new ActividadVo(getString(R.string.actividad4_nombre),getString(R.string.actividad4_descripcion),R.drawable.icon_conciencia));
        listaActividades.add(new ActividadVo(getString(R.string.actividad5_nombre),getString(R.string.actividad5_descripcion),R.drawable.icon_conciencia));
        listaActividades.add(new ActividadVo(getString(R.string.actividad6_nombre),getString(R.string.actividad6_descripcion),R.drawable.icon_conciencia));
        listaActividades.add(new ActividadVo(getString(R.string.actividad7_nombre),getString(R.string.actividad7_descripcion),R.drawable.icon_conciencia));
        listaActividades.add(new ActividadVo(getString(R.string.actividad8_nombre),getString(R.string.actividad8_descripcion),R.drawable.icon_conciencia));
        listaActividades.add(new ActividadVo(getString(R.string.actividad9_nombre),getString(R.string.actividad9_descripcion),R.drawable.icon_conciencia));

        // otra forma de escribir sin el string listaActividades.add(new ActividadVo("Actividad 20. Presencia de sonidos graves fuertes ","El usuario indica que oyó algo cuando hay un ruido fuerte (tambor).",R.drawable.logounicauca));
         */
    }



    @Override
    public void onItemClick(int position) {

// funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(getView());
        System.out.println("position" + position);

        switch (position) {
            case 0:
                //navControllermio.navigate(R.id.actividad_conc_1Fragment);
                navControllermio.navigate(R.id. action_listaConcienciaFragment_to_actividad_conc_1Fragment); //pasar a otro fragment con actions
                break;
            case 1:
                //navControllermio.navigate(R.id.actividad_conc_2Fragment);
                navControllermio.navigate(R.id. action_listaConcienciaFragment_to_actividad_conc_2Fragment); //pasar a otro fragment con actions
                break;
            /*case 2:
                //navControllermio.navigate(R.id.actividad_conc_3Fragment);
                navControllermio.navigate(R.id. action_listaConcienciaFragment_to_actividad_conc_3Fragment); //pasar a otro fragment con actions
                break;
             */
        }

    }



}//final