package com.example.marglov2.FragmentsIdentificación;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marglov2.Adaptadores.AdaptadorActividadesIdentificacion;
import com.example.marglov2.Entidades.ActividadVo;
import com.example.marglov2.Interfaces.RecyclerClickActividadesInterface;
import com.example.marglov2.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaIdentificacionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaIdentificacionFragment extends Fragment implements RecyclerClickActividadesInterface {

    //Declaracion de recycler y arraylist
    ArrayList<ActividadVo> listaActividades;
    RecyclerView recyclerActividadesIddentificacion;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListaIdentificacionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaIdentificacionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaIdentificacionFragment newInstance(String param1, String param2) {
        ListaIdentificacionFragment fragment = new ListaIdentificacionFragment();
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

        View vista = inflater.inflate(R.layout.fragment_lista_identificacion, container, false);

        listaActividades = new ArrayList<>();
        recyclerActividadesIddentificacion = (RecyclerView) vista.findViewById(R.id.recyclerIdentificacionid);
        recyclerActividadesIddentificacion.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarActividades();

        AdaptadorActividadesIdentificacion adapter = new AdaptadorActividadesIdentificacion(listaActividades, ListaIdentificacionFragment.this);
        recyclerActividadesIddentificacion.setAdapter(adapter);

        return vista;

    }

    private void llenarActividades() {


        listaActividades.add(new ActividadVo(getString(R.string.ident_1_nombre),"",R.drawable.icon_identificacionflat));
        listaActividades.add(new ActividadVo(getString(R.string.ident_2_nombre),"",R.drawable.icon_identificacionflat));
        listaActividades.add(new ActividadVo(getString(R.string.ident_3_nombre),"",R.drawable.icon_identificacionflat));
        listaActividades.add(new ActividadVo(getString(R.string.ident_4_nombre),"",R.drawable.icon_identificacionflat));

        /*
        listaActividades.add(new ActividadVo("Actividad 1. Emparejar los sonidos ( juego de memoria).","Esta actividad se divide en 3 secciones, el usuario sigue las instrucciones y finaliza con el juego de emparejamiento.",R.drawable.icon_identificacionflat));
        listaActividades.add(new ActividadVo("Actividad 2. Identifica entre el singular y plural de una palabra.","El usuario selecciona si escuchó la palabra en singula o en plural.",R.drawable.icon_identificacionflat));
        listaActividades.add(new ActividadVo("Actividad 3. Sigue instrucciones que tengan 1 elemento destacado dentro de un conjunto cerrado de 3 opciones.","El usuario selecciona la imagen de acuerdo a la instrucción inicial.",R.drawable.icon_identificacionflat));
        listaActividades.add(new ActividadVo("Actividad 4. Identifica entre 3 expresiones que varían de longitud.","El usuario selecciona una frase, de acuerdo al sonido inicial.",R.drawable.icon_identificacionflat));
*/
        //listaActividades.add(new ActividadVo(getString(R.string.actividad123_nombre), getString(R.string.actividad123_descripcion), R.drawable.icon_identificacionflat));
        //listaActividades.add(new ActividadVo(getString(R.string.actividad45_nombre), getString(R.string.actividad45_descripcion), R.drawable.icon_identificacionflat));
        //listaActividades.add(new ActividadVo(getString(R.string.actividad8910_nombre), getString(R.string.actividad8910_descripcion), R.drawable.icon_identificacionflat));
        //listaActividades.add(new ActividadVo(getString(R.string.actividad45_nombre), getString(R.string.actividad45_descripcion), R.drawable.icon_identificacionflat));

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
                //navControllermio.navigate(R.id.juego_presentarFragment);
                navControllermio.navigate(R.id.action_listaIdentificacionFragment_to_actividad_ident_1Fragment);
                //navControllermio.navigate(R.id.action_listaIdentificacionFragment_to_actividad_ident_2Fragment);
                break;
            case 1:
                navControllermio.navigate(R.id.action_listaIdentificacionFragment_to_actividad_ident_2Fragment);
                //navControllermio.navigate(R.id.action_listaIdentificacionFragment_to_actividad_ident_3Fragment);
                break;
            case 2:
                navControllermio.navigate(R.id.action_listaIdentificacionFragment_to_actividad_ident_3Fragment);
                //navControllermio.navigate(R.id.action_listaIdentificacionFragment_to_actividad_ident_4Fragment);
                break;

            case 3:
                navControllermio.navigate(R.id.action_listaIdentificacionFragment_to_actividad_ident_4Fragment);
                break;
        }

    }

}



