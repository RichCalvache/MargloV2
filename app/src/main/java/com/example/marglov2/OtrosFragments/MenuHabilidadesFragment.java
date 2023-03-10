package com.example.marglov2.OtrosFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marglov2.Adaptadores.AdaptadorHabilidades;
import com.example.marglov2.Entidades.ActividadVo;
import com.example.marglov2.Interfaces.RecyclerClickActividadesInterface;
import com.example.marglov2.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuHabilidadesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuHabilidadesFragment extends Fragment implements RecyclerClickActividadesInterface {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<ActividadVo> listaHabilidades;
    RecyclerView recyclerHabilidades;


    public MenuHabilidadesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuHabilidadesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuHabilidadesFragment newInstance(String param1, String param2) {
        MenuHabilidadesFragment fragment = new MenuHabilidadesFragment();
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

        View vista =inflater.inflate(R.layout.fragment_menu_habilidades, container, false);

        listaHabilidades =  new ArrayList<>();
        recyclerHabilidades = (RecyclerView) vista.findViewById(R.id.recyclerMenuHabilidadesid);
        recyclerHabilidades.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarHabilidades();

        AdaptadorHabilidades adapter =  new AdaptadorHabilidades(listaHabilidades, MenuHabilidadesFragment.this);
        recyclerHabilidades.setAdapter(adapter);

        return vista;

    }

    private void llenarHabilidades() {

        listaHabilidades.add(new ActividadVo(getString(R.string.conciencia_nombre),getString(R.string.conciencia_descripcion), R.drawable.icon_concienciaflat));
        listaHabilidades.add(new ActividadVo(getString(R.string.fonetica_nombre),getString(R.string.fonetica_descripcion),R.drawable.icon_discriminacionflat));
        listaHabilidades.add(new ActividadVo(getString(R.string.identificación_nombre),getString(R.string.identificación_descripcion),R.drawable.icon_identificacionflat));
        listaHabilidades.add(new ActividadVo(getString(R.string.comprension_nombre),getString(R.string.comprension_descripcion),R.drawable.icon_comprensionflat));
        // otra forma de escribir sin el string listaActividades.add(new ActividadVo("Actividad 20. Presencia de sonidos graves fuertes ","El usuario indica que oyó algo cuando hay un ruido fuerte (tambor).",R.drawable.logounicauca));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // funcion para controlar la navegacion hacia otras pantallas


    }

    @Override
    public void onItemClick(int position) {

// funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(getView());
        System.out.println("position"+position);

        switch (position){
            case 0: //navControllermio.navigate(R.id.listaConcienciaFragment);
                    navControllermio.navigate(R.id. action_menuHabilidadesFragment_to_listaConcienciaFragment); //pasar a otro fragment con actions
            break;
            case 1: //navControllermio.navigate(R.id.listaFoneticaFragment);
                navControllermio.navigate(R.id. action_menuHabilidadesFragment_to_listaFoneticaFragment2); //pasar a otro fragment con actions
                break;
            case 2: //navControllermio.navigate(R.id.listaComprensionFragment);
                    navControllermio.navigate(R.id. action_menuHabilidadesFragment_to_listaIdentificacionFragment); //pasar a otro fragment con actions
                break;
            case 3: //navControllermio.navigate(R.id.listaComprensionFragment);
                navControllermio.navigate(R.id. action_menuHabilidadesFragment_to_listaComprensionFragment); //pasar a otro fragment con actions
                break;
        }




    }


}