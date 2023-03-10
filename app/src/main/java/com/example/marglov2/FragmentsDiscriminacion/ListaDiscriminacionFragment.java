package com.example.marglov2.FragmentsDiscriminacion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marglov2.Adaptadores.AdaptadorActividadesFonetica;
import com.example.marglov2.Entidades.ActividadVo;
import com.example.marglov2.Interfaces.RecyclerClickActividadesInterface;
import com.example.marglov2.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaDiscriminacionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaDiscriminacionFragment extends Fragment implements RecyclerClickActividadesInterface {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//Declaracion de recycler y arraylist
    ArrayList<ActividadVo> listaActividades;
    RecyclerView recyclerActividadesFonetica;

    public ListaDiscriminacionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaDiscriminacionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaDiscriminacionFragment newInstance(String param1, String param2) {
        ListaDiscriminacionFragment fragment = new ListaDiscriminacionFragment();
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
        View vista =inflater.inflate(R.layout.fragment_lista_discriminacion, container, false);

        listaActividades =  new ArrayList<>();
        recyclerActividadesFonetica = (RecyclerView) vista.findViewById(R.id.recyclerFoneticaid);
        recyclerActividadesFonetica.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarActividades();

        AdaptadorActividadesFonetica adapter =  new AdaptadorActividadesFonetica(listaActividades, ListaDiscriminacionFragment.this);
        recyclerActividadesFonetica.setAdapter(adapter);

        return vista;

    }

    private void llenarActividades() {

        listaActividades.add(new ActividadVo(getString(R.string.discr_1_nombre),"",R.drawable.icon_discriminacionflat));
        listaActividades.add(new ActividadVo(getString(R.string.discr_2_nombre),"",R.drawable.icon_discriminacionflat));
        listaActividades.add(new ActividadVo(getString(R.string.discr_3_nombre),"",R.drawable.icon_discriminacionflat));
        listaActividades.add(new ActividadVo(getString(R.string.discr_4_nombre),"",R.drawable.icon_discriminacionflat));
        listaActividades.add(new ActividadVo(getString(R.string.discr_5_nombre),"",R.drawable.icon_discriminacionflat));

        /*
        listaActividades.add(new ActividadVo("Actividad 1. Discrimina entre sonidos cortos y largos.","El usuario discrimina entre sonidos cortos y largos de un conjunto de 2, 3 o 4 opciones.",R.drawable.icon_discriminacionflat));
        listaActividades.add(new ActividadVo("Actividad 2. Discrimina entre vocales que se diferencian en la información del primer formante en contexto de sílaba o palabra.","El usuario discrimina la primera vocal de sílabas o palabras, de acuerdo a un sonido inicial.",R.drawable.icon_discriminacionflat));
        listaActividades.add(new ActividadVo("Actividad 3. Discrimina entre vocales y secuencias vocálicas decrecientes en contexto de sílabas o palabra.","El usuario discrimina entre vocales o diptongos en sílabas y palabras, de acuerdo a un sonido inicial.",R.drawable.icon_discriminacionflat));
        listaActividades.add(new ActividadVo("Actividad 4. En un conjunto de dos opciones, discrimina entre consonantes iniciales que difieren en modo de producción en sílabas o palabras.","El usuario discrimina entre 2 conjuntos de 2 palabras que difieren en la primera consonante, de acuerdo al sonido inicial.",R.drawable.icon_discriminacionflat));
        listaActividades.add(new ActividadVo("Actividad 5. Discrimina entre lenguaje hablado y sonido.","El usuario indica si escuchó una frase o un sonido del medio ambiente.",R.drawable.icon_discriminacionflat));
    */



        //listaActividades.add(new ActividadVo(getString(R.string.actividad1_nombre_fonetica),getString(R.string.actividad1_descripcion_fonetica), R.drawable.icon_discriminacionflat));
        //listaActividades.add(new ActividadVo(getString(R.string.actividad2_nombre_fonetica),getString(R.string.actividad2_descripcion_fonetica), R.drawable.icon_discriminacionflat));
        //listaActividades.add(new ActividadVo(getString(R.string.actividad3_nombre_fonetica),getString(R.string.actividad3_descripcion_fonetica), R.drawable.icon_discriminacionflat));
        //listaActividades.add(new ActividadVo(getString(R.string.actividad4_nombre_fonetica),getString(R.string.actividad4_descripcion_fonetica), R.drawable.icon_discriminacionflat));
        /*listaActividades.add(new ActividadVo(getString(R.string.actividad5_nombre_fonetica),getString(R.string.actividad5_descripcion_fonetica), R.drawable.icon_discriminacionflat));
        listaActividades.add(new ActividadVo(getString(R.string.actividad6_nombre_fonetica),getString(R.string.actividad6_descripcion_fonetica), R.drawable.icon_discriminacionflat));
        listaActividades.add(new ActividadVo(getString(R.string.actividad7_nombre_fonetica),getString(R.string.actividad7_descripcion_fonetica), R.drawable.icon_discriminacionflat));
        listaActividades.add(new ActividadVo(getString(R.string.actividad8_nombre_fonetica),getString(R.string.actividad8_descripcion_fonetica), R.drawable.icon_discriminacionflat));
        listaActividades.add(new ActividadVo(getString(R.string.actividad9_nombre_fonetica),getString(R.string.actividad9_descripcion_fonetica), R.drawable.icon_discriminacionflat));
        listaActividades.add(new ActividadVo(getString(R.string.actividad10_nombre_fonetica),getString(R.string.actividad10_descripcion_fonetica), R.drawable.icon_discriminacionflat));
         */
    }


    @Override
    public void onItemClick(int position) {

        // funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(getView());
        System.out.println("position" + position);

        switch (position) {
            case 0:
                navControllermio.navigate(R.id.action_listaDiscriminacionFragment_to_actividad_conc_3Fragment);
                break;
            case 1:
                //navControllermio.navigate(R.id.actividad_conc_1Fragment);
                navControllermio.navigate(R.id.action_listaDiscriminacionFragment_to_actividad_discr_1Fragment); //pasar a otro fragment con actions
                break;
            case 2:
                //navControllermio.navigate(R.id.actividad_conc_2Fragment);
                navControllermio.navigate(R.id. action_listaDiscriminacionFragment_to_actividad_discr_2Fragment); //pasar a otro fragment con actions
                break;
            case 3:
                //navControllermio.navigate(R.id.actividad_conc_3Fragment);
                navControllermio.navigate(R.id. action_listaDiscriminacionFragment_to_actividad_discr_3Fragment); //pasar a otro fragment con actions
                break;
            case 4:
                //navControllermio.navigate(R.id.actividad_conc_3Fragment);
                navControllermio.navigate(R.id. action_listaDiscriminacionFragment_to_actividad_discr_4Fragment); //pasar a otro fragment con actions
                break;
        }

    }


}//final