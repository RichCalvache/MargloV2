package com.example.marglov2.OtrosFragments;

import android.content.Context;
import android.content.SharedPreferences;
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
 * Use the {@link MiPerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MiPerfilFragment extends Fragment {

    DBHelper DB;
    MediaPlayer sonido;

    TextView nombreTextview, apellidosTextview, edadTextview, generoTextview, direccionTextview, ciudadTextview, celularTextview;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MiPerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MiPerfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MiPerfilFragment newInstance(String param1, String param2) {
        MiPerfilFragment fragment = new MiPerfilFragment();
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
        return inflater.inflate(R.layout.fragment_mi_perfil, container, false);
    }

    //Se recomienda crear onviewcreated para trabajar c uando la lista esta creada correctamente
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //sonido bienvenida
        // sonido = MediaPlayer.create(getActivity(), R.raw.bienvenido_mujer);
        //sonido.start();
        String nombre_txt = "";
        String apellidos_txt = "";
        String edad_txt= "";
        String genero_txt= "";
        String ciudad_txt= "";
        String direccion_txt= "";
        String celular_txt= "";

        //logo
        ImageView imagen_perfil = view.findViewById(R.id.imagenperfil_id);

        //Cargando Avatar de SharedPReferences
        SharedPreferences preferencesavatar = getActivity().getSharedPreferences("PreferenciasAvatar", Context.MODE_PRIVATE);
        int posicionavatar = preferencesavatar.getInt("posicionavatar",1);

        switch (posicionavatar) {
            case 1:
                imagen_perfil.setImageResource(R.drawable.avatar1);
                break;
            case 2:
                imagen_perfil.setImageResource(R.drawable.avatar2);
                break;
            case 3:
                imagen_perfil.setImageResource(R.drawable.avatar3);
                break;
            case 4:
                imagen_perfil.setImageResource(R.drawable.avatar4);
                break;
            case 5:
                imagen_perfil.setImageResource(R.drawable.avatar5);
                break;
            case 6:
                imagen_perfil.setImageResource(R.drawable.avatar6);
                break;
            case 7:
                imagen_perfil.setImageResource(R.drawable.avatar7);
                break;
            case 8:
                imagen_perfil.setImageResource(R.drawable.avatar8);
                break;
            case 9:
                imagen_perfil.setImageResource(R.drawable.avatar9);
                break;
            case 10:
                imagen_perfil.setImageResource(R.drawable.avatar10);
                break;
            case 11:
                imagen_perfil.setImageResource(R.drawable.avatar11);
                break;
            case 12:
                imagen_perfil.setImageResource(R.drawable.avatar12);
                break;
            case 13:
                imagen_perfil.setImageResource(R.drawable.avatar13);
                break;
            case 14:
                imagen_perfil.setImageResource(R.drawable.avatar14);
                break;
            case 15:
                imagen_perfil.setImageResource(R.drawable.avatar15);
                break;
            case 16:
                imagen_perfil.setImageResource(R.drawable.avatar16);
                break;
        }


        //leer y mostrar datos de usuario
        DB = new DBHelper(getContext());
        Cursor cursor = DB.getdata();
        if(cursor.moveToFirst()){
            do{
                nombre_txt = cursor.getString(cursor.getColumnIndex("nombre"));
                apellidos_txt = cursor.getString(cursor.getColumnIndex("apellidos"));
                edad_txt= cursor.getString(cursor.getColumnIndex("edad"));
                genero_txt = cursor.getString(cursor.getColumnIndex("genero"));
                ciudad_txt = cursor.getString(cursor.getColumnIndex("ciudad"));
                direccion_txt = cursor.getString(cursor.getColumnIndex("direccion"));
                celular_txt = cursor.getString(cursor.getColumnIndex("celular"));

                //String varaible2 = cursor.getString(cursor.getColumnIndex("column_name2"));
            }while (cursor.moveToNext());
        }
        cursor.close();


        // String nombre_txt = res.getString(0);
        nombreTextview = view.findViewById(R.id.nombreTextid);
        nombreTextview.setText(nombre_txt);

        apellidosTextview = view.findViewById(R.id.apellidosTextid);
        apellidosTextview.setText(apellidos_txt);

        edadTextview = view.findViewById(R.id.edadTextid);
        edadTextview.setText(edad_txt);

        generoTextview = view.findViewById(R.id.generoTextid);
        generoTextview.setText(genero_txt);

        ciudadTextview = view.findViewById(R.id.ciudadTextid);
        ciudadTextview.setText(ciudad_txt);

        direccionTextview = view.findViewById(R.id.direccionTextid);
        direccionTextview.setText(direccion_txt);

        celularTextview = view.findViewById(R.id.celularTextid);
        celularTextview.setText(celular_txt);


        // funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(view); //nacControllermio es el nombre de la instancia

        //boton
        Button btn_editar = view.findViewById(R.id.editar_btnid);

        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Navigation.findNavController(v).navigate(R.id.blankFragment1);//Metodo alterno sin funcion; para hacer navegacion con boton welcome a otro fragment
                //navControllermio.navigate(R.id.editarPerfilFragment); //pasar a otro fragment
                navControllermio.navigate(R.id. action_miPerfilFragment_to_editarPerfilFragment); //pasar a otro fragment con actions

            }

        });

    }
}

