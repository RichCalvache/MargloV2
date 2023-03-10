package com.example.marglov2.OtrosFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.marglov2.Entidades.DBHelper;
import com.example.marglov2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsuarioCreadoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsuarioCreadoFragment extends Fragment {

    DBHelper DB;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView numavatar;

    public UsuarioCreadoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UsuarioCreadoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UsuarioCreadoFragment newInstance(String param1, String param2) {
        UsuarioCreadoFragment fragment = new UsuarioCreadoFragment();
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
        return inflater.inflate(R.layout.fragment_usuario_creado, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // comprobar Inicio de sesion
        SharedPreferences preferenceslogin = getActivity().getSharedPreferences("PreferenciasInicioSesion", Context.MODE_PRIVATE);
        boolean estadousuario = preferenceslogin.getBoolean("iniciosesion", false);
        if(estadousuario == true) {
            //Toast.makeText(getContext(), "Usuario ya registrado", Toast.LENGTH_SHORT).show();
            //Navigation.findNavController((view)).navigate(R.id.inicioFragment);
            Navigation.findNavController((view)).navigate(R.id.action_usuarioCreadoFragment_to_inicioFragment); //pasar a otro fragment con actions
            getActivity().recreate();

            // navControllermio.navigate(R.id.usuarioCreadoFragment);
            //guardarPreferenciasLogin(true);

        } else {
            Toast.makeText(getContext(), "No hay usuarios", Toast.LENGTH_SHORT).show();
            //Navigation.findNavController((view)).navigate(R.id.registroUsuarioFragment);
            Navigation.findNavController((view)).navigate(R.id.action_usuarioCreadoFragment_to_registroUsuarioFragment); //pasar a otro fragment con actions
            //guardarPreferenciasLogin(false);
        }


        //DATOS DE USUARIO
        /*
        DB = new DBHelper(getContext());
        Cursor res = DB.getdata();
        if(res.getCount()==0){
            Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("Nombre :"+res.getString(0)+"\n");
            buffer.append("Edad :"+res.getString(1)+"\n");
            buffer.append("Género :"+res.getString(2)+"\n\n");
            buffer.append("Dirección :"+res.getString(3)+"\n\n");
            buffer.append("Ciudad :"+res.getString(4)+"\n\n");
            buffer.append("Celular :"+res.getString(5)+"\n\n");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle("Datos de Usuario");
        builder.setMessage(buffer.toString());
        builder.show();

         */


        SharedPreferences preferencesavatar = getActivity().getSharedPreferences("PreferenciasAvatar", Context.MODE_PRIVATE);
        int numeroAvatar = preferencesavatar.getInt("posicionavatar",1);

        String posicionAvatar = String.valueOf(numeroAvatar);
        numavatar= view.findViewById(R.id.textnroavatarid);
        numavatar.setText(posicionAvatar);
        //numavatar.setText(posicionAvatar);



        

    }
}