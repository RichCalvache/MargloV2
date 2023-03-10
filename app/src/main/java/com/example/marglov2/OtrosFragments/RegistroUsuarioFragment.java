package com.example.marglov2.OtrosFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.marglov2.Adaptadores.AdaptadorAvatars;
import com.example.marglov2.Entidades.AvatarVo;
import com.example.marglov2.Entidades.DBHelper;
import com.example.marglov2.Interfaces.RecyclerClickAvatarsInterface;
import com.example.marglov2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistroUsuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroUsuarioFragment extends Fragment implements RecyclerClickAvatarsInterface {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<AvatarVo> listaAvatars;
    RecyclerView recyclerAvatars;

    FloatingActionButton fabRegistro;
    EditText nombre_usuario, apellidos, edad, direccion, ciudad, celular;
    RadioButton radioM, radioF;
    DBHelper DB;

    public RegistroUsuarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistroUsuarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistroUsuarioFragment newInstance(String param1, String param2) {
        RegistroUsuarioFragment fragment = new RegistroUsuarioFragment();
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
        View vista = inflater.inflate(R.layout.fragment_registro_usuario, container, false);

        listaAvatars = new ArrayList<>();
        recyclerAvatars = (RecyclerView) vista.findViewById(R.id.recyclerAvatarsId);
        recyclerAvatars.setLayoutManager(new GridLayoutManager(getContext(), 2));//para ver el recyler con dos columnas

        llenarAvatars();
        AdaptadorAvatars adapter = new AdaptadorAvatars(listaAvatars, RegistroUsuarioFragment.this);
        recyclerAvatars.setAdapter(adapter);

        fabRegistro = vista.findViewById(R.id.idFabRegistro);
        nombre_usuario = vista.findViewById(R.id.nombreTextid);
        apellidos = vista.findViewById(R.id.apellidosTextid);
        edad = vista.findViewById(R.id.edadTextid);
        direccion = vista.findViewById(R.id.direccionid);
        ciudad = vista.findViewById(R.id.ciudadid);
        celular = vista.findViewById(R.id.celularid);
        radioM = vista.findViewById(R.id.radioM);
        radioF = vista.findViewById(R.id.radioF);

        String nombreTXT = nombre_usuario.getText().toString();

        DB = new DBHelper(getContext());



        fabRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarJugador();
                guardarInicioSesion();
                Navigation.findNavController(view).navigate(R.id.usuarioCreadoFragment);
            }
        });

        return vista;
    }

    private void llenarAvatars() {

        listaAvatars.add(new AvatarVo(1, R.drawable.avatar1, "Avatar1"));
        listaAvatars.add(new AvatarVo(2, R.drawable.avatar2, "Avatar2"));
        listaAvatars.add(new AvatarVo(3, R.drawable.avatar3, "Avatar3"));
        listaAvatars.add(new AvatarVo(4, R.drawable.avatar4, "Avatar4"));
        listaAvatars.add(new AvatarVo(5, R.drawable.avatar5, "Avatar5"));
        listaAvatars.add(new AvatarVo(6, R.drawable.avatar6, "Avatar6"));
        listaAvatars.add(new AvatarVo(7, R.drawable.avatar7, "Avatar7"));
        listaAvatars.add(new AvatarVo(8, R.drawable.avatar8, "Avatar8"));
        listaAvatars.add(new AvatarVo(9, R.drawable.avatar9, "Avatar9"));
        listaAvatars.add(new AvatarVo(10, R.drawable.avatar10, "Avatar10"));
        listaAvatars.add(new AvatarVo(11, R.drawable.avatar11, "Avatar11"));
        listaAvatars.add(new AvatarVo(12, R.drawable.avatar12, "Avatar12"));
        listaAvatars.add(new AvatarVo(13, R.drawable.avatar13, "Avatar13"));
        listaAvatars.add(new AvatarVo(14, R.drawable.avatar14, "Avatar14"));
        listaAvatars.add(new AvatarVo(15, R.drawable.avatar15, "Avatar15"));
        listaAvatars.add(new AvatarVo(16, R.drawable.avatar16, "Avatar16"));
    }

    static class variables{
        public static int posicionAvatar = 1;
    }

    private void registrarJugador() {

        String nombreTXT = nombre_usuario.getText().toString();
        String apellidosTXT = apellidos.getText().toString();
        String edadTXT = edad.getText().toString();
        String direccionTXT = direccion.getText().toString();
        String ciudadTXT = ciudad.getText().toString();
        String celularTXT = celular.getText().toString();

        int avatarTXT;
        //avatarTXT = variables.posicionAvatar; otra forma de guardar el valor sin necesidad de sharedpreferences

        //cargar valor de posicion avatar
        SharedPreferences preferencesavatar = getActivity().getSharedPreferences("PreferenciasAvatar", Context.MODE_PRIVATE);
        int posicionAvatar = preferencesavatar.getInt("posicionavatar",1);
        avatarTXT = posicionAvatar;

        //guarda el nombre de usuario en sharedpreferences para el nav header
        SharedPreferences preferencesnombre = getActivity().getSharedPreferences("PreferenciasNombre", Context.MODE_PRIVATE);
        //String nombreNavHeader = nombre_usuario.getText().toString();
        SharedPreferences.Editor editor = preferencesnombre.edit();
        editor.putString("nombreNavHeader", nombreTXT);
        editor.commit();


        String generoTXT = "";
        if (radioM.isChecked() == true) {
            generoTXT = "Masculino";
        } else if (radioF.isChecked() == true) {
            generoTXT = "Fememino";
        } else {
            generoTXT = "No seleccionado";
        }

        //insertar datos a la bd
        Boolean checkinsertdata = DB.insertData(nombreTXT, apellidosTXT, edadTXT, generoTXT, direccionTXT, ciudadTXT, celularTXT, avatarTXT);
        if (checkinsertdata == true) {
            Toast.makeText(getContext(), "Registro de usuario éxitoso", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "Falló registro de usuario", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onItemClick(int position) {
        //final NavController navControllermio = Navigation.findNavController(getView());

        System.out.println("position"+position);


        switch (position) {
            case 0:
                variables.posicionAvatar = 1;
                System.out.println("position"+position);
                //navControllermio.navigate(R.id.usuarioCreadoFragment);
                guardarPreferenciasAvatar();
                break;
            case 1:
                variables.posicionAvatar = 2;
                guardarPreferenciasAvatar();
                break;
            case 2:
                variables.posicionAvatar = 3;
                guardarPreferenciasAvatar();
                break;
            case 3:
                variables.posicionAvatar = 4;
                guardarPreferenciasAvatar();
                break;
            case 4:
                variables.posicionAvatar = 5;
                guardarPreferenciasAvatar();
                break;
            case 5:
                variables.posicionAvatar = 6;
                guardarPreferenciasAvatar();
                break;
            case 6:
                variables.posicionAvatar = 7;
                guardarPreferenciasAvatar();
                break;
            case 7:
                variables.posicionAvatar = 8;
                guardarPreferenciasAvatar();
                break;
            case 8:
                variables.posicionAvatar = 9;
                guardarPreferenciasAvatar();
                break;

            case 9:
                variables.posicionAvatar = 10;
                guardarPreferenciasAvatar();
                break;

            case 10:
                variables.posicionAvatar = 11;
                System.out.println("position"+position);
                //navControllermio.navigate(R.id.usuarioCreadoFragment);
                guardarPreferenciasAvatar();
                break;
            case 11:
                variables.posicionAvatar = 12;
                guardarPreferenciasAvatar();
                break;
            case 12:
                variables.posicionAvatar = 13;
                guardarPreferenciasAvatar();
                break;
            case 13:
                variables.posicionAvatar = 14;
                guardarPreferenciasAvatar();
                break;
            case 14:
                variables.posicionAvatar = 15;
                guardarPreferenciasAvatar();
                break;
            case 15:
                variables.posicionAvatar = 16;
                guardarPreferenciasAvatar();
                break;

        }

    }

    private void guardarPreferenciasAvatar(){
        SharedPreferences preferencesavatar = getActivity().getSharedPreferences("PreferenciasAvatar", Context.MODE_PRIVATE);
        int numero_avatar= variables.posicionAvatar;
        SharedPreferences.Editor editor = preferencesavatar.edit();
        editor.putInt("posicionavatar", numero_avatar);
        editor.commit();
    }


    private void guardarInicioSesion(){
        SharedPreferences preferenceslogin = getActivity().getSharedPreferences("PreferenciasInicioSesion", Context.MODE_PRIVATE);

        boolean iniciosesion = true;
        SharedPreferences.Editor editor = preferenceslogin.edit();
        editor.putBoolean("iniciosesion", iniciosesion);
        editor.commit();
    }

    //metodo para cargar preferencias...
    private void cargarPreferencias(){
        SharedPreferences preferences = getActivity().getSharedPreferences("DatosLogin ", Context.MODE_PRIVATE);
        int posicionAvatar = preferences.getInt("posicionavatar",1);
    }

}
