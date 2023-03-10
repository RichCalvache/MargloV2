package com.example.marglov2.OtrosFragments;

import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marglov2.Entidades.DBHelper;
import com.example.marglov2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioFragment extends Fragment {

    DBHelper DB;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InicioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InicioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    MediaPlayer sonido;
    TextView bienvenidoTextview;


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
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }


    //Se recomienda crear onviewcreated para trabajar c uando la lista esta creada correctamente
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //sonido bienvenida
       // sonido = MediaPlayer.create(getActivity(), R.raw.bienvenido_mujer);
        //sonido.start();
        String nombre_txt = "";

        //leer y mostrar datos de usuario
        DB = new DBHelper(getContext());
        Cursor cursor = DB.getdata();
        if(cursor.moveToFirst()){
            do{
                nombre_txt = cursor.getString(cursor.getColumnIndex("nombre"));
                //String varaible2 = cursor.getString(cursor.getColumnIndex("column_name2"));
            }while (cursor.moveToNext());
        }
        cursor.close();


       // String nombre_txt = res.getString(0);
        bienvenidoTextview = view.findViewById(R.id.bienvenido);
        bienvenidoTextview.setText("Hola, "+ nombre_txt);


        //comprobarInicioSesion(view);

        //logo
        ImageView logo = view.findViewById(R.id.logo);

        // funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(view); //nacControllermio es el nombre de la instancia

        //boton
        Button btn_welcome = view.findViewById(R.id.botonbienvenido);

        btn_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Navigation.findNavController(v).navigate(R.id.blankFragment1);//Metodo alterno sin funcion; para hacer navegacion con boton welcome a otro fragment
                //navControllermio.navigate(R.id.menuHabilidadesFragment); //pasar a otro fragment
                navControllermio.navigate(R.id. action_inicioFragment_to_menuHabilidadesFragment); //pasar a otro fragment con actions

            }

        });

    }
}