package com.example.marglov2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.marglov2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBarTotal;
    int progreso;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//funcion para boton flotante ejemplo que venia predeterminado "email"

        setSupportActionBar(binding.appBarMain.toolbar);
       /* binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.inicioFragment, R.id.acercaDeFragment, R.id.puntajesFragment, R.id.menuHabilidadesFragment, R.id.listaConcienciaFragment, R.id.listaDiscriminacionFragment,R.id.listaIdentificacionFragment, R.id.listaComprensionFragment, R.id.miPerfilFragment)
                .setDrawerLayout(drawer)
                .build();//aqui iba setDrawerLayout(drawer) en vez de setOpenableLayout(drawer)
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Codigo para el nav header
        //NavigationView navigationView1 =(NavigationView)findViewById(R.id.nav_view);
        //View hView = navigationView1.getHeaderView(0);

        //View hView = binding.navView.getHeaderView(0);
        //DrawerLayout headerBinding = DrawerLayout.bind(headerView);

        navigationView.removeHeaderView(navigationView.getHeaderView(0));
        View hView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        ImageView imgvwNavHeader = (ImageView) hView.findViewById(R.id.imageViewAvatarNavHeader);
        TextView textNombre = (TextView) hView.findViewById(R.id.textViewNombreNavHeader);
        TextView textPuntaje = (TextView) hView.findViewById(R.id.textViewPuntosGlobalNavHeader);



        //leer y mostrar puntaje TOTAL de shared preferences
        SharedPreferences preferencestotalpuntaje = getSharedPreferences("total_puntajes_table", Context.MODE_PRIVATE);
        int puntajeTotal = preferencestotalpuntaje.getInt("total_puntaje",0);

        progreso = puntajeTotal;

        textPuntaje.setText("Total puntaje: " + puntajeTotal);

        //barra de progreso
        progressBarTotal = hView.findViewById(R.id.progressBarTotal);
        llenarBarraProgeso();
        //progressBar.setMax(100);
        //progressBar.setProgress(progreso);





        /* guardar nombre y imagen del navheader
        imgvwNavHeader.setImageResource(R.drawable.icon_mujer);
        textNombre.setText("new name");
        textPuntaje.setText("new puntaje");
        */

        //Cargando valores de Shared Preferences
        SharedPreferences preferencesnombre = getSharedPreferences("PreferenciasNombre", Context.MODE_PRIVATE);
        String nombre_usuario = preferencesnombre.getString("nombreNavHeader", "Nombre Usuario");
        textNombre.setText(nombre_usuario);

        //Cargando Avatar de SharedPReferences
        SharedPreferences preferencesavatar = getSharedPreferences("PreferenciasAvatar", Context.MODE_PRIVATE);
        int posicionavatar = preferencesavatar.getInt("posicionavatar", 1);

        switch (posicionavatar) {
            case 1:
                imgvwNavHeader.setImageResource(R.drawable.avatar1);
                break;
            case 2:
                imgvwNavHeader.setImageResource(R.drawable.avatar2);
                break;
            case 3:
                imgvwNavHeader.setImageResource(R.drawable.avatar3);
                break;
            case 4:
                imgvwNavHeader.setImageResource(R.drawable.avatar4);
                break;
            case 5:
                imgvwNavHeader.setImageResource(R.drawable.avatar5);
                break;
            case 6:
                imgvwNavHeader.setImageResource(R.drawable.avatar6);
                break;
            case 7:
                imgvwNavHeader.setImageResource(R.drawable.avatar7);
                break;
            case 8:
                imgvwNavHeader.setImageResource(R.drawable.avatar8);
                break;
            case 9:
                imgvwNavHeader.setImageResource(R.drawable.avatar9);
                break;
            case 10:
                imgvwNavHeader.setImageResource(R.drawable.avatar10);
                break;
            case 11:
                imgvwNavHeader.setImageResource(R.drawable.avatar11);
                break;
            case 12:
                imgvwNavHeader.setImageResource(R.drawable.avatar12);
                break;
            case 13:
                imgvwNavHeader.setImageResource(R.drawable.avatar13);
                break;
            case 14:
                imgvwNavHeader.setImageResource(R.drawable.avatar14);
                break;
            case 15:
                imgvwNavHeader.setImageResource(R.drawable.avatar15);
                break;
            case 16:
                imgvwNavHeader.setImageResource(R.drawable.avatar16);
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //ocultar barra de navegacion


    //LLENAR BARRA DE PORGRESO
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Fill full life progress bar here.
        llenarBarraProgeso();
    }


    private void llenarBarraProgeso(){
        progressBarTotal.setMax(500);
        progressBarTotal.setProgress(progreso);
    }


}//FINAL