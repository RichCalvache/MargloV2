package com.example.marglov2.OtrosFragments;

import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.marglov2.Entidades.DBHelperPuntajes;
import com.example.marglov2.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GraficasIndividualesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GraficasIndividualesFragment extends Fragment {

    //private Button btn_exportar;


    int colorNegro = Color.parseColor("#FF000000");
    int colorDeteccion = Color.parseColor("#8FE1F7");
    int colorDiscriminacion = Color.parseColor("#EF9A9A");
    int colorIdentificacion = Color.parseColor("#FFF59D");
    int colorComprension = Color.parseColor("#C5E1A5");


    int puntajeTotal_act_1_Deteccion,puntajeTemp_act_1_Deteccion = 0;
    int puntajeTotal_act_2_Deteccion,puntajeTemp_act_2_Deteccion = 0;

    int puntajeTotal_act_1_Discriminacion,puntajeTemp_act_1_Discriminacion = 0;
    int puntajeTotal_act_2_Discriminacion,puntajeTemp_act_2_Discriminacion = 0;
    int puntajeTotal_act_3_Discriminacion,puntajeTemp_act_3_Discriminacion = 0;
    int puntajeTotal_act_4_Discriminacion,puntajeTemp_act_4_Discriminacion = 0;
    int puntajeTotal_act_5_Discriminacion,puntajeTemp_act_5_Discriminacion = 0;

    int puntajeTotal_act_1_Identificacion,puntajeTemp_act_1_Identificacion = 0;
    int puntajeTotal_act_2_Identificacion,puntajeTemp_act_2_Identificacion = 0;
    int puntajeTotal_act_3_Identificacion,puntajeTemp_act_3_Identificacion = 0;
    int puntajeTotal_act_4_Identificacion,puntajeTemp_act_4_Identificacion = 0;

    int puntajeTotal_act_1_Comprension,puntajeTemp_act_1_Comprension = 0;
    int puntajeTotal_act_2_Comprension,puntajeTemp_act_2_Comprension = 0;
    int puntajeTotal_act_3_Comprension,puntajeTemp_act_3_Comprension = 0;
    int puntajeTotal_act_4_Comprension,puntajeTemp_act_4_Comprension = 0;
    int puntajeTotal_act_5_Comprension,puntajeTemp_act_5_Comprension = 0;




    String habilidad, fecha, fechaTemp = "";

    int contador_1_det =0, contador_2_det =0;
    int contador_1_discr =0, contador_2_discr =0, contador_3_discr =0, contador_4_discr =0, contador_5_discr =0;
    int contador_1_ident =0, contador_2_ident =0, contador_3_ident =0, contador_4_ident =0;
    int contador_1_compr =0, contador_2_compr =0, contador_3_compr =0, contador_4_compr =0, contador_5_compr =0;


    DBHelperPuntajes DB;



    private Spinner spinnerHabilidades, spinnerDeteccion,spinnerDiscriminacion, spinnerIdentificacion, spinnerComprension;
    private static final String[] itemsHabilidades = {"Seleccione una habilidad...","Detección","Discriminación", "Identificación", "Comprensión"};
    //private static final String[] itemsTipoGrafico = {"Gráfico de barras", "Rendimiento esperado"};
    private static final String[] itemsDeteccion = {"Seleccione una actividad...","Actividad 1", "Actividad 2"};
    private static final String[] itemsDiscriminacion = {"Seleccione una actividad...", "Actividad 1", "Actividad 2","Actividad 3","Actividad 4","Actividad 5"};
    private static final String[] itemsIdentificacion = {"Seleccione una actividad...","Actividad 1", "Actividad 2","Actividad 3","Actividad 4"};
    private static final String[] itemsComprension = {"Seleccione una actividad...", "Actividad 1", "Actividad 2","Actividad 3","Actividad 4","Actividad 5"};



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GraficasIndividualesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GraficasIndividualesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GraficasIndividualesFragment newInstance(String param1, String param2) {
        GraficasIndividualesFragment fragment = new GraficasIndividualesFragment();
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
        return inflater.inflate(R.layout.fragment_graficas_individuales, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //btn_exportar = view.findViewById(R.id.btn_exportar);

        Typeface tf = ResourcesCompat.getFont(getContext(), R.font.adamina);


        //bd
        DB = new DBHelperPuntajes(getContext());

        // funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(view); //nacControllermio es el nombre de la instancia


        String habilidad;
        int actividad;


        //SPINNER HABILIDADES
        spinnerHabilidades =  (Spinner) view.findViewById(R.id.spinnerHabilidades_id);
        ArrayAdapter<String> adapterHabilidades = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, itemsHabilidades);
        adapterHabilidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHabilidades.setAdapter(adapterHabilidades);


        //SPINNER Deteccion
        spinnerDeteccion =  (Spinner) view.findViewById(R.id.spinnerActividadesDeteccion_id);
        ArrayAdapter<String> adapterDeteccion = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, itemsDeteccion);
        adapterDeteccion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDeteccion.setAdapter(adapterDeteccion);

        //SPINNER Discriminacion
        spinnerDiscriminacion =  (Spinner) view.findViewById(R.id.spinnerActividadesDiscriminacion_id);
        ArrayAdapter<String> adapterDiscriminacion = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, itemsDiscriminacion);
        adapterDiscriminacion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDiscriminacion.setAdapter(adapterDiscriminacion);

        //SPINNER Identificacion
        spinnerIdentificacion =  (Spinner) view.findViewById(R.id.spinnerActividadesIdentificacion_id);
        ArrayAdapter<String> adapterIdentificacion = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, itemsIdentificacion);
        adapterIdentificacion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdentificacion.setAdapter(adapterIdentificacion);

        //SPINNER Comprension
        spinnerComprension =  (Spinner) view.findViewById(R.id.spinnerActividadesComprension_id);
        ArrayAdapter<String> adapterComprension = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, itemsComprension);
        adapterComprension.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerComprension.setAdapter(adapterComprension);

        //ARRAYS PARA GUARDAR LOS DATOS DE CADA ACTIVIDAD
        //DETECCION
        ArrayList<BarEntry> valores_Actividad_1_Deteccion = new ArrayList<>();
        ArrayList<BarEntry> valores_Actividad_2_Deteccion = new ArrayList<>();
        //DISCRIMINACION
        ArrayList<BarEntry> valores_Actividad_1_Discriminacion = new ArrayList<>();
        ArrayList<BarEntry> valores_Actividad_2_Discriminacion = new ArrayList<>();
        ArrayList<BarEntry> valores_Actividad_3_Discriminacion = new ArrayList<>();
        ArrayList<BarEntry> valores_Actividad_4_Discriminacion = new ArrayList<>();
        ArrayList<BarEntry> valores_Actividad_5_Discriminacion = new ArrayList<>();
        //IDENTIFICACION
        ArrayList<BarEntry> valores_Actividad_1_Identificacion = new ArrayList<>();
        ArrayList<BarEntry> valores_Actividad_2_Identificacion = new ArrayList<>();
        ArrayList<BarEntry> valores_Actividad_3_Identificacion = new ArrayList<>();
        ArrayList<BarEntry> valores_Actividad_4_Identificacion= new ArrayList<>();
        //COMPRENSION
        ArrayList<BarEntry> valores_Actividad_1_Comprension = new ArrayList<>();
        ArrayList<BarEntry> valores_Actividad_2_Comprension = new ArrayList<>();
        ArrayList<BarEntry> valores_Actividad_3_Comprension = new ArrayList<>();
        ArrayList<BarEntry> valores_Actividad_4_Comprension = new ArrayList<>();
        ArrayList<BarEntry> valores_Actividad_5_Comprension = new ArrayList<>();


        //PARA CAMBIAR LOS VALORES DEL EJE X, PONER STRING fecha
        //ArrayList<String> xAxisLabelsFecha = new ArrayList<>();
        //DETECCION
        ArrayList<String> xAxisLabelsFecha_actividad_1_Deteccion = new ArrayList<>();
        ArrayList<String> xAxisLabelsFecha_actividad_2_Deteccion = new ArrayList<>();
        //DISCRIMINACION
        ArrayList<String> xAxisLabelsFecha_actividad_1_Discriminacion = new ArrayList<>();
        ArrayList<String> xAxisLabelsFecha_actividad_2_Discriminacion = new ArrayList<>();
        ArrayList<String> xAxisLabelsFecha_actividad_3_Discriminacion = new ArrayList<>();
        ArrayList<String> xAxisLabelsFecha_actividad_4_Discriminacion = new ArrayList<>();
        ArrayList<String> xAxisLabelsFecha_actividad_5_Discriminacion = new ArrayList<>();
        //IDENTIFICACION
        ArrayList<String> xAxisLabelsFecha_actividad_1_Identificacion = new ArrayList<>();
        ArrayList<String> xAxisLabelsFecha_actividad_2_Identificacion = new ArrayList<>();
        ArrayList<String> xAxisLabelsFecha_actividad_3_Identificacion = new ArrayList<>();
        ArrayList<String> xAxisLabelsFecha_actividad_4_Identificacion = new ArrayList<>();
        //COMPRENSION
        ArrayList<String> xAxisLabelsFecha_actividad_1_Comprension = new ArrayList<>();
        ArrayList<String> xAxisLabelsFecha_actividad_2_Comprension = new ArrayList<>();
        ArrayList<String> xAxisLabelsFecha_actividad_3_Comprension = new ArrayList<>();
        ArrayList<String> xAxisLabelsFecha_actividad_4_Comprension = new ArrayList<>();
        ArrayList<String> xAxisLabelsFecha_actividad_5_Comprension = new ArrayList<>();



        //OBTENER PUNTAJES DE LA DB PARA MOSTRAR EN LAS GRAFICAS
        DB = new DBHelperPuntajes(getContext());
        Cursor res = DB.getdataPuntajes();
        if (res.getCount() == 0) {
            Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }

        while (res.moveToNext()) {


            habilidad = res.getString(2);

            //int punt = res.getInt(3);

            switch (habilidad){
                case "Detección":
                    //puntaje general
                    //puntajeTotal_Deteccion = res.getInt(3) + puntajeTemp_Deteccion;
                    //puntajeTemp_Deteccion = puntajeTotal_Deteccion;

                    actividad = res.getInt(1);

                    switch(actividad){
                        case 1:
                            //puntaje act 1 deteccion

                            fecha = res.getString(0);

                            if(!fecha.equals(fechaTemp)){
                                puntajeTotal_act_1_Deteccion = res.getInt(3);
                                valores_Actividad_1_Deteccion.add(new BarEntry(contador_1_det,puntajeTotal_act_1_Deteccion));
                                contador_1_det++;
                                //PARA CAMBIAR LOS VALORES DEL EJE X, PONER STRING
                                xAxisLabelsFecha_actividad_1_Deteccion.add(fecha);
                            }

                            break;
                        case 2:
                            //puntaje act 2 deteccion

                            fecha = res.getString(0);

                            if(!fecha.equals(fechaTemp)){
                                puntajeTotal_act_2_Deteccion = res.getInt(3);
                                valores_Actividad_2_Deteccion.add(new BarEntry(contador_2_det,puntajeTotal_act_2_Deteccion));
                                contador_2_det++;
                                //PARA CAMBIAR LOS VALORES DEL EJE X, PONER STRING
                                xAxisLabelsFecha_actividad_2_Deteccion.add(fecha);
                            }
                            break;
                    }
                    break;
                case "Discriminación":
                    //puntaje general
                    //puntajeTotal_Discriminacion = res.getInt(3) + puntajeTemp_Discriminacion;
                    //puntajeTemp_Discriminacion =puntajeTotal_Discriminacion;

                    actividad = res.getInt(1);

                    switch(actividad){
                        case 1:
                            //puntaje act 1 discriminacion
                            fecha = res.getString(0);

                            if(!fecha.equals(fechaTemp)){
                                puntajeTotal_act_1_Discriminacion = res.getInt(3);
                                valores_Actividad_1_Discriminacion.add(new BarEntry(contador_1_discr,puntajeTotal_act_1_Discriminacion));
                                contador_1_discr++;
                                //PARA CAMBIAR LOS VALORES DEL EJE X, PONER STRING
                                xAxisLabelsFecha_actividad_1_Discriminacion.add(fecha);
                            }

                            break;
                        case 2:
                            //puntaje act 2 discriminacion

                            fecha = res.getString(0);

                            if(!fecha.equals(fechaTemp)){
                                puntajeTotal_act_2_Discriminacion = res.getInt(3);
                                valores_Actividad_2_Discriminacion.add(new BarEntry(contador_2_discr,puntajeTotal_act_2_Discriminacion));
                                contador_2_discr++;
                                //PARA CAMBIAR LOS VALORES DEL EJE X, PONER STRING
                                xAxisLabelsFecha_actividad_2_Discriminacion.add(fecha);
                            }
                            break;
                        case 3:
                            //puntaje act 3 discriminacion
                            fecha = res.getString(0);

                            if(!fecha.equals(fechaTemp)){
                                puntajeTotal_act_3_Discriminacion = res.getInt(3);
                                valores_Actividad_3_Discriminacion.add(new BarEntry(contador_3_discr,puntajeTotal_act_3_Discriminacion));
                                contador_3_discr++;
                                //PARA CAMBIAR LOS VALORES DEL EJE X, PONER STRING
                                xAxisLabelsFecha_actividad_3_Discriminacion.add(fecha);
                            }
                            break;
                        case 4:
                            //puntaje act 4 discriminacion
                            fecha = res.getString(0);

                            if(!fecha.equals(fechaTemp)){
                                puntajeTotal_act_4_Discriminacion = res.getInt(3);
                                valores_Actividad_4_Discriminacion.add(new BarEntry(contador_4_discr,puntajeTotal_act_4_Discriminacion));
                                contador_4_discr++;
                                //PARA CAMBIAR LOS VALORES DEL EJE X, PONER STRING
                                xAxisLabelsFecha_actividad_4_Discriminacion.add(fecha);
                            }
                            break;
                        case 5:
                            //puntaje act 5 discriminacion
                            fecha = res.getString(0);

                            if(!fecha.equals(fechaTemp)){
                                puntajeTotal_act_5_Discriminacion = res.getInt(3);
                                valores_Actividad_5_Discriminacion.add(new BarEntry(contador_5_discr,puntajeTotal_act_5_Discriminacion));
                                contador_5_discr++;
                                //PARA CAMBIAR LOS VALORES DEL EJE X, PONER STRING
                                xAxisLabelsFecha_actividad_5_Discriminacion.add(fecha);
                            }
                            break;
                    }
                    break;
                case "Identificación":
                    //puntaje general
                    //puntajeTotal_Identificacion = res.getInt(3) + puntajeTemp_Identificacion;
                    //puntajeTemp_Identificacion =puntajeTotal_Identificacion;

                    actividad = res.getInt(1);

                    switch(actividad){
                        case 1:
                            //puntaje act 1 discriminacion
                            //puntajeTotal_act_1_Identificacion = res.getInt(3) + puntajeTemp_act_1_Identificacion;
                            //puntajeTemp_act_1_Identificacion = puntajeTotal_act_1_Identificacion;
                            fecha = res.getString(0);

                            if(!fecha.equals(fechaTemp)){
                                puntajeTotal_act_1_Identificacion = res.getInt(3);
                                valores_Actividad_1_Identificacion.add(new BarEntry(contador_1_ident,puntajeTotal_act_1_Identificacion));
                                contador_1_ident++;
                                //PARA CAMBIAR LOS VALORES DEL EJE X, PONER STRING
                                xAxisLabelsFecha_actividad_1_Identificacion.add(fecha);
                            }

                            break;
                        case 2:
                            //puntaje act 2 discriminacion
                            //puntajeTotal_act_2_Identificacion = res.getInt(3) + puntajeTemp_act_2_Identificacion;
                            //puntajeTemp_act_2_Identificacion = puntajeTotal_act_2_Identificacion;
                            fecha = res.getString(0);

                            if(!fecha.equals(fechaTemp)){
                                puntajeTotal_act_2_Identificacion = res.getInt(3);
                                valores_Actividad_2_Identificacion.add(new BarEntry(contador_2_ident,puntajeTotal_act_2_Identificacion));
                                contador_2_ident++;
                                //PARA CAMBIAR LOS VALORES DEL EJE X, PONER STRING
                                xAxisLabelsFecha_actividad_2_Identificacion.add(fecha);
                            }
                            break;
                        case 3:
                            //puntaje act 3 discriminacion
                            //puntajeTotal_act_3_Identificacion = res.getInt(3) + puntajeTemp_act_3_Identificacion;
                            //puntajeTemp_act_3_Identificacion = puntajeTotal_act_3_Identificacion;
                            fecha = res.getString(0);

                            if(!fecha.equals(fechaTemp)){
                                puntajeTotal_act_3_Identificacion = res.getInt(3);
                                valores_Actividad_3_Identificacion.add(new BarEntry(contador_3_ident,puntajeTotal_act_3_Identificacion));
                                contador_3_ident++;
                                //PARA CAMBIAR LOS VALORES DEL EJE X, PONER STRING
                                xAxisLabelsFecha_actividad_3_Identificacion.add(fecha);
                            }

                            break;
                        case 4:
                            //puntaje act 4 discriminacion
                            //puntajeTotal_act_4_Identificacion = res.getInt(3) + puntajeTemp_act_4_Identificacion;
                            //puntajeTemp_act_4_Identificacion = puntajeTotal_act_4_Identificacion;
                            fecha = res.getString(0);

                            if(!fecha.equals(fechaTemp)){
                                puntajeTotal_act_4_Identificacion = res.getInt(3);
                                valores_Actividad_4_Identificacion.add(new BarEntry(contador_4_ident,puntajeTotal_act_4_Identificacion));
                                contador_4_ident++;
                                //PARA CAMBIAR LOS VALORES DEL EJE X, PONER STRING
                                xAxisLabelsFecha_actividad_4_Identificacion.add(fecha);
                            }
                            break;
                    }
                    break;
                case "Comprensión":
                    //puntaje general
                    //puntajeTotal_Comprension = res.getInt(3) + puntajeTemp_Comprension;
                    //puntajeTemp_Comprension =puntajeTotal_Comprension;

                    actividad = res.getInt(1);

                    switch(actividad){
                        case 1:
                            //puntaje act 1 discriminacion
                            //puntajeTotal_act_1_Comprension = res.getInt(3) + puntajeTemp_act_1_Comprension;
                            //puntajeTemp_act_1_Comprension = puntajeTotal_act_1_Comprension;
                            fecha = res.getString(0);

                            if(!fecha.equals(fechaTemp)){
                                puntajeTotal_act_1_Comprension = res.getInt(3);
                                valores_Actividad_1_Comprension.add(new BarEntry(contador_1_compr,puntajeTotal_act_1_Comprension));
                                contador_1_compr++;
                                //PARA CAMBIAR LOS VALORES DEL EJE X, PONER STRING
                                xAxisLabelsFecha_actividad_1_Comprension.add(fecha);
                            }


                            break;
                        case 2:
                            //puntaje act 2 discriminacion
                            //puntajeTotal_act_2_Comprension = res.getInt(3) + puntajeTemp_act_2_Comprension;
                            //puntajeTemp_act_2_Comprension = puntajeTotal_act_2_Comprension;
                            fecha = res.getString(0);

                            if(!fecha.equals(fechaTemp)){
                                puntajeTotal_act_2_Comprension = res.getInt(3);
                                valores_Actividad_2_Comprension.add(new BarEntry(contador_2_compr,puntajeTotal_act_2_Comprension));
                                contador_2_compr++;
                                //PARA CAMBIAR LOS VALORES DEL EJE X, PONER STRING
                                xAxisLabelsFecha_actividad_2_Comprension.add(fecha);
                            }
                            break;
                        case 3:
                            //puntaje act 3 discriminacion
                            fecha = res.getString(0);

                            if(!fecha.equals(fechaTemp)){
                                puntajeTotal_act_3_Comprension = res.getInt(3);
                                valores_Actividad_3_Comprension.add(new BarEntry(contador_3_compr,puntajeTotal_act_3_Comprension));
                                contador_3_compr++;
                                //PARA CAMBIAR LOS VALORES DEL EJE X, PONER STRING
                                xAxisLabelsFecha_actividad_3_Comprension.add(fecha);

                            }
                            break;
                        case 4:
                            //puntaje act 4 discriminacion
                            fecha = res.getString(0);

                            if(!fecha.equals(fechaTemp)){
                                puntajeTotal_act_4_Comprension = res.getInt(3);
                                valores_Actividad_4_Comprension.add(new BarEntry(contador_4_compr,puntajeTotal_act_4_Comprension));
                                contador_4_compr++;
                                //PARA CAMBIAR LOS VALORES DEL EJE X, PONER STRING
                                xAxisLabelsFecha_actividad_4_Comprension.add(fecha);
                            }
                            break;
                        case 5:
                            //puntaje act 5 discriminacion
                            fecha = res.getString(0);

                            if(!fecha.equals(fechaTemp)){
                                puntajeTotal_act_5_Comprension = res.getInt(3);
                                valores_Actividad_5_Comprension.add(new BarEntry(contador_5_compr,puntajeTotal_act_5_Comprension));
                                contador_5_compr++;
                                //PARA CAMBIAR LOS VALORES DEL EJE X, PONER STRING
                                xAxisLabelsFecha_actividad_5_Comprension.add(fecha);
                            }
                            break;
                    }

                    break;
            }

        }


        //PARA LOS GRAFICOS
        //BarChart barChart = view.findViewById(R.id.graficoBarrasid);
        BarChart grafico_Barras = view.findViewById(R.id.graficoBarrasid);



        //DATASETS
        //DETECCION
        BarDataSet barDataSetDeteccion1 = new BarDataSet(valores_Actividad_1_Deteccion, "Actividad 1 Detección");
        BarDataSet barDataSetDeteccion2 = new BarDataSet(valores_Actividad_2_Deteccion, "Actividad 2 Detección");
        //DISCRIMINACION
        BarDataSet barDataSetDiscriminacion1 = new BarDataSet(valores_Actividad_1_Discriminacion, "Actividad 1 Discriminación");
        BarDataSet barDataSetDiscriminacion2 = new BarDataSet(valores_Actividad_2_Discriminacion, "Actividad 2 Discriminación");
        BarDataSet barDataSetDiscriminacion3 = new BarDataSet(valores_Actividad_3_Discriminacion, "Actividad 3 Discriminación");
        BarDataSet barDataSetDiscriminacion4 = new BarDataSet(valores_Actividad_4_Discriminacion, "Actividad 4 Discriminación");
        BarDataSet barDataSetDiscriminacion5 = new BarDataSet(valores_Actividad_5_Discriminacion, "Actividad 5 Discriminación");
        //IDENTIFICACION
        BarDataSet barDataSetIdentificacion1 = new BarDataSet(valores_Actividad_1_Identificacion, "Actividad 1 Identificación");
        BarDataSet barDataSetIdentificacion2 = new BarDataSet(valores_Actividad_2_Identificacion, "Actividad 2 Identificación");
        BarDataSet barDataSetIdentificacion3 = new BarDataSet(valores_Actividad_3_Identificacion, "Actividad 3 Identificación");
        BarDataSet barDataSetIdentificacion4 = new BarDataSet(valores_Actividad_4_Identificacion, "Actividad 4 Identificación");
        //COMPRENSION
        BarDataSet barDataSetComprension1 = new BarDataSet(valores_Actividad_1_Comprension, "Actividad 1 Comprensión");
        BarDataSet barDataSetComprension2 = new BarDataSet(valores_Actividad_2_Comprension, "Actividad 2 Comprensión");
        BarDataSet barDataSetComprension3 = new BarDataSet(valores_Actividad_3_Comprension, "Actividad 3 Comprensión");
        BarDataSet barDataSetComprension4 = new BarDataSet(valores_Actividad_4_Comprension, "Actividad 4 Comprensión");
        BarDataSet barDataSetComprension5 = new BarDataSet(valores_Actividad_5_Comprension, "Actividad 5 Comprensión");



        //para los colores de las barras
        //DETECCION
        //para los colores de las barras
        barDataSetDeteccion1.setColors(colorDeteccion);
        barDataSetDeteccion1.setValueTextColor(Color.BLACK);
        barDataSetDeteccion1.setValueTextSize(20f);
        barDataSetDeteccion1.setBarBorderWidth(1.f);
        barDataSetDeteccion1.setValueTypeface(tf);
        BarData barDataDeteccion1 =  new BarData(barDataSetDeteccion1);//crea los datos a cargar de la barra a partir del dataset
        barDataDeteccion1.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal

        //para los colores de las barras
        barDataSetDeteccion2.setColors(colorDeteccion);
        barDataSetDeteccion2.setValueTextColor(Color.BLACK);
        barDataSetDeteccion2.setValueTextSize(20f);
        barDataSetDeteccion2.setBarBorderWidth(1.f);
        barDataSetDeteccion2.setValueTypeface(tf);
        BarData barDataDeteccion2 =  new BarData(barDataSetDeteccion2);//crea los datos a cargar de la barra a partir del dataset
        barDataDeteccion2.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal

        //DISCRIMINACION
        //para los colores de las barras
        barDataSetDiscriminacion1.setColors(colorDiscriminacion);
        barDataSetDiscriminacion1.setValueTextColor(Color.BLACK);
        barDataSetDiscriminacion1.setValueTextSize(20f);
        barDataSetDiscriminacion1.setBarBorderWidth(1.f);
        barDataSetDiscriminacion1.setValueTypeface(tf);
        BarData barDataDiscriminacion1 =  new BarData(barDataSetDiscriminacion1);//crea los datos a cargar de la barra a partir del dataset
        barDataDiscriminacion1.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal
        //para los colores de las barras
        barDataSetDiscriminacion2.setColors(colorDiscriminacion);
        barDataSetDiscriminacion2.setValueTextColor(Color.BLACK);
        barDataSetDiscriminacion2.setValueTextSize(20f);
        barDataSetDiscriminacion2.setBarBorderWidth(1.f);
        barDataSetDiscriminacion2.setValueTypeface(tf);
        BarData barDataDiscriminacion2 =  new BarData(barDataSetDiscriminacion2);//crea los datos a cargar de la barra a partir del dataset
        barDataDiscriminacion2.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal
        //para los colores de las barras
        barDataSetDiscriminacion3.setColors(colorDiscriminacion);
        barDataSetDiscriminacion3.setValueTextColor(Color.BLACK);
        barDataSetDiscriminacion3.setValueTextSize(20f);
        barDataSetDiscriminacion3.setBarBorderWidth(1.f);
        barDataSetDiscriminacion3.setValueTypeface(tf);
        BarData barDataDiscriminacion3 =  new BarData(barDataSetDiscriminacion3);//crea los datos a cargar de la barra a partir del dataset
        barDataDiscriminacion3.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal
        //para los colores de las barras
        barDataSetDiscriminacion4.setColors(colorDiscriminacion);
        barDataSetDiscriminacion4.setValueTextColor(Color.BLACK);
        barDataSetDiscriminacion4.setValueTextSize(20f);
        barDataSetDiscriminacion4.setBarBorderWidth(1.f);
        barDataSetDiscriminacion4.setValueTypeface(tf);
        BarData barDataDiscriminacion4 =  new BarData(barDataSetDiscriminacion4);//crea los datos a cargar de la barra a partir del dataset
        barDataDiscriminacion4.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal
        //para los colores de las barras
        barDataSetDiscriminacion5.setColors(colorDiscriminacion);
        barDataSetDiscriminacion5.setValueTextColor(Color.BLACK);
        barDataSetDiscriminacion5.setValueTextSize(20f);
        barDataSetDiscriminacion5.setBarBorderWidth(1.f);
        barDataSetDiscriminacion5.setValueTypeface(tf);
        BarData barDataDiscriminacion5 =  new BarData(barDataSetDiscriminacion5);//crea los datos a cargar de la barra a partir del dataset
        barDataDiscriminacion5.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal

        //IDENTIFICACION
        //para los colores de las barras
        barDataSetIdentificacion1.setColors(colorIdentificacion);
        barDataSetIdentificacion1.setValueTextColor(Color.BLACK);
        barDataSetIdentificacion1.setValueTextSize(20f);
        barDataSetIdentificacion1.setBarBorderWidth(1.f);
        barDataSetIdentificacion1.setValueTypeface(tf);
        BarData barDataIdentificacion1 =  new BarData(barDataSetIdentificacion1);//crea los datos a cargar de la barra a partir del dataset
        barDataIdentificacion1.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal
        //para los colores de las barras
        barDataSetIdentificacion2.setColors(colorIdentificacion);
        barDataSetIdentificacion2.setValueTextColor(Color.BLACK);
        barDataSetIdentificacion2.setValueTextSize(20f);
        barDataSetIdentificacion2.setBarBorderWidth(1.f);
        barDataSetIdentificacion2.setValueTypeface(tf);
        BarData barDataIdentificacion2 =  new BarData(barDataSetIdentificacion2);//crea los datos a cargar de la barra a partir del dataset
        barDataIdentificacion2.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal
        //para los colores de las barras
        barDataSetIdentificacion3.setColors(colorIdentificacion);
        barDataSetIdentificacion3.setValueTextColor(Color.BLACK);
        barDataSetIdentificacion3.setValueTextSize(20f);
        barDataSetIdentificacion3.setBarBorderWidth(1.f);
        barDataSetIdentificacion3.setValueTypeface(tf);
        BarData barDataIdentificacion3 =  new BarData(barDataSetIdentificacion3);//crea los datos a cargar de la barra a partir del dataset
        barDataIdentificacion3.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal
        //para los colores de las barras
        barDataSetIdentificacion4.setColors(colorIdentificacion);
        barDataSetIdentificacion4.setValueTextColor(Color.BLACK);
        barDataSetIdentificacion4.setValueTextSize(20f);
        barDataSetIdentificacion4.setBarBorderWidth(1.f);
        barDataSetIdentificacion4.setValueTypeface(tf);
        BarData barDataIdentificacion4 =  new BarData(barDataSetIdentificacion4);//crea los datos a cargar de la barra a partir del dataset
        barDataIdentificacion4.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal

        //COMPRENSION
        //para los colores de las barras
        barDataSetComprension1.setColors(colorComprension);
        barDataSetComprension1.setValueTextColor(Color.BLACK);
        barDataSetComprension1.setValueTextSize(20f);
        barDataSetComprension1.setBarBorderWidth(1.f);
        barDataSetComprension1.setValueTypeface(tf);
        BarData barDataComprension1 =  new BarData(barDataSetComprension1);//crea los datos a cargar de la barra a partir del dataset
        barDataComprension1.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal


        //para los colores de las barras
        barDataSetComprension2.setColors(colorComprension);
        barDataSetComprension2.setValueTextColor(Color.BLACK);
        barDataSetComprension2.setValueTextSize(20f);
        barDataSetComprension2.setBarBorderWidth(1.f);
        barDataSetComprension2.setValueTypeface(tf);
        BarData barDataComprension2 =  new BarData(barDataSetComprension2);//crea los datos a cargar de la barra a partir del dataset
        barDataComprension2.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal
        //para los colores de las barras
        barDataSetComprension3.setColors(colorComprension);
        barDataSetComprension3.setValueTextColor(Color.BLACK);
        barDataSetComprension3.setValueTextSize(20f);
        barDataSetComprension3.setBarBorderWidth(1.f);
        barDataSetComprension3.setValueTypeface(tf);
        BarData barDataComprension3 =  new BarData(barDataSetComprension3);//crea los datos a cargar de la barra a partir del dataset
        barDataComprension3.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal
        //para los colores de las barras
        barDataSetComprension4.setColors(colorComprension);
        barDataSetComprension4.setValueTextColor(Color.BLACK);
        barDataSetComprension4.setValueTextSize(20f);
        barDataSetComprension4.setBarBorderWidth(1.f);
        barDataSetComprension4.setValueTypeface(tf);
        BarData barDataComprension4 =  new BarData(barDataSetComprension4);//crea los datos a cargar de la barra a partir del dataset
        barDataComprension4.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal
        //para los colores de las barras
        barDataSetComprension5.setColors(colorComprension);
        barDataSetComprension5.setValueTextColor(Color.BLACK);
        barDataSetComprension5.setValueTextSize(20f);
        barDataSetComprension5.setBarBorderWidth(1.f);
        barDataSetComprension5.setValueTypeface(tf);
        BarData barDataComprension5 =  new BarData(barDataSetComprension5);//crea los datos a cargar de la barra a partir del dataset
        barDataComprension5.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal


        // poner abajo dependiendo el caso: grafico_Barras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));//le da el formato al eje x
        grafico_Barras.getXAxis().setGranularity(1f); //para que salgan ordenados el eje x.. only intervals of 1 day
        grafico_Barras.getAxisLeft().setGranularity(1f); //para que salgan ordenados el eje y.. solo valores enteros de uno en uno

        grafico_Barras.getLegend().setEnabled(false);// oculta legend (cuadritos con etiqeutea)
        grafico_Barras.getAxisLeft().setTextSize(R.dimen.textSize_ejeY);//tamaño letras ejeY derecho
        grafico_Barras.getXAxis().setTextSize(14f);//tamaño letras eje X
        grafico_Barras.getAxisLeft().setAxisMinimum(0f);//para que empiece en zero el ejeY
        grafico_Barras.getAxisRight().setEnabled(false);//oculta eje y derecho
        grafico_Barras.getAxisLeft().setAxisLineColor(colorNegro);
        grafico_Barras.getXAxis().setAxisLineColor(colorNegro);


        //grafico_Barras.setPinchZoom(true);//zoom pellizco
        grafico_Barras.setHighlightPerTapEnabled(false);//para que no salga sombra al presionar
        grafico_Barras.setHighlightPerDragEnabled(false);//para que no salga sombra al arrastrar
        grafico_Barras.setScaleEnabled(false);

        grafico_Barras.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//para que la eqtiqueta del ejex salga abajo


        grafico_Barras.getDescription().setEnabled(false);//quitar descripcion
        //grafico_Barras.getXAxis().setCenterAxisLabels(true)
        grafico_Barras.setFitBars(true);//para que las barras salgan del mismo ancho

        //PARA AGREGAR FECHA EN 2 LINEAS Y DIAGONAL EN EL EJE X
        grafico_Barras.setXAxisRenderer(new CustomXAxisRenderer(grafico_Barras.getViewPortHandler(), grafico_Barras.getXAxis(), grafico_Barras.getTransformer(YAxis.AxisDependency.LEFT)));
        grafico_Barras.getXAxis().setLabelRotationAngle(-30f);
        grafico_Barras.setExtraBottomOffset(60);

        //grafico_Barras.setVisibleXRangeMaximum(3);//muestra solo 3 barras
        //grafico_Barras.setVisibleXRangeMinimum(3);//muestra solo 3 barras

        //grafico_Barras.setVisibleXRangeMaximum(3);//muestra solo 3 barras
        //grafico_Barras.setVisibleXRangeMinimum(3);//muestra solo 3 barras


        //"REPRODUCIR" graficas
        //grafico_Barras.setData(barData);//carga los valores a la grafica
        //grafico_Barras.getDescription().setText("Gráfico Barras Visitantes");
        //grafico_Barras.animateXY(2000,2000);

        //Typeface tf = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
        //Typeface tf = ResourcesCompat.getFont(getContext(), R.font.adamina);

        grafico_Barras.getAxisLeft().setTypeface(tf);
        grafico_Barras.getXAxis().setTypeface(tf);


        spinnerHabilidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        spinnerDeteccion.setVisibility(View.INVISIBLE);
                        spinnerDiscriminacion.setVisibility(View.INVISIBLE);
                        spinnerIdentificacion.setVisibility(View.INVISIBLE);
                        spinnerComprension.setVisibility(View.INVISIBLE);
                        grafico_Barras.clear();
                        break;
                    case 1:
                        // MUESTRA GRAFICA BARRAS DETECCION
                        spinnerDeteccion.setVisibility(View.VISIBLE);
                        spinnerDeteccion.setSelection(0);
                        spinnerDiscriminacion.setVisibility(View.INVISIBLE);
                        spinnerIdentificacion.setVisibility(View.INVISIBLE);
                        spinnerComprension.setVisibility(View.INVISIBLE);

                        //grafico_Barras.notifyDataSetChanged();
                        //grafico_Barras.invalidate();

                        grafico_Barras.clear();
                        //grafico_Barras.moveViewToAnimated(0,0, YAxis.AxisDependency.LEFT,2000);//lo lleva al último valor

                        break;
                    case 2:
                        // MUESTRA GRAFICA BARRAS DISCRIMINACION
                        spinnerDeteccion.setVisibility(View.INVISIBLE);
                        spinnerDiscriminacion.setVisibility(View.VISIBLE);
                        spinnerDiscriminacion.setSelection(0);
                        spinnerIdentificacion.setVisibility(View.INVISIBLE);
                        spinnerComprension.setVisibility(View.INVISIBLE);

                        grafico_Barras.clear();
                        //grafico_Barras.notifyDataSetChanged();
                        //grafico_Barras.invalidate();
                        break;
                    case 3:
                        // MUESTRA GRAFICA BARRAS IDENTIFICACION
                        spinnerDeteccion.setVisibility(View.INVISIBLE);
                        spinnerDiscriminacion.setVisibility(View.INVISIBLE);
                        spinnerIdentificacion.setVisibility(View.VISIBLE);
                        spinnerIdentificacion.setSelection(0);
                        spinnerComprension.setVisibility(View.INVISIBLE);

                        grafico_Barras.clear();
                        //grafico_Barras.notifyDataSetChanged();
                        //grafico_Barras.invalidate();
                        break;
                    case 4:
                        // MUESTRA GRAFICA BARRAS COMPRENSION
                        spinnerDeteccion.setVisibility(View.INVISIBLE);
                        spinnerDiscriminacion.setVisibility(View.INVISIBLE);
                        spinnerIdentificacion.setVisibility(View.INVISIBLE);
                        spinnerComprension.setVisibility(View.VISIBLE);
                        spinnerComprension.setSelection(0);
                        grafico_Barras.clear();
                        //grafico_Barras.notifyDataSetChanged();
                        //grafico_Barras.invalidate();
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerDeteccion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        grafico_Barras.clear();
                        break;
                    case 1:

                        if (valores_Actividad_1_Deteccion.size()==0){
                            Toast.makeText(getContext(), "¡Aún no has realizado esta actividad!", Toast.LENGTH_SHORT).show();
                            grafico_Barras.clear();
                        }else {
                            // MUESTRA GRAFICA ACTIVIDAD 1
                            grafico_Barras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabelsFecha_actividad_1_Deteccion));//le da el formato al eje x CARGA FECHAS RESPECTIVAS
                            //grafico_Barras.getXAxis().setValueFormatter(grafico_Barras.getDefaultValueFormatter());//le da el formato default al eje x
                            grafico_Barras.setData(barDataDeteccion1);//carga los valores a la grafica
                            grafico_Barras.setVisibleXRangeMaximum(3);//muestra solo 3 barras
                            grafico_Barras.setVisibleXRangeMinimum(3);//muestra solo 3 barras
                            barDataDeteccion1.setBarWidth(0.6f);// ajusta ancho de las barras

                            grafico_Barras.animateXY(2000,2000);
                            //grafico_Barras.moveViewToX(valores_Actividad_1_Deteccion.size());
                            grafico_Barras.moveViewToAnimated(valores_Actividad_1_Deteccion.size(),grafico_Barras.getBarData().getYMax(), YAxis.AxisDependency.RIGHT,2000);//lo lleva al último valor

                        }
                        break;

                    case 2:

                        if (valores_Actividad_2_Deteccion.size()==0){
                            Toast.makeText(getContext(), "¡Aún no has realizado esta actividad!", Toast.LENGTH_SHORT).show();
                            grafico_Barras.clear();
                        }else {
                            // MUESTRA GRAFICA ACTIVIDAD 2
                            grafico_Barras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabelsFecha_actividad_2_Deteccion));//le da el formato al eje x, CARGA FECHAS RESPECTIVAS
                            //grafico_Barras.getXAxis().setValueFormatter(grafico_Barras.getDefaultValueFormatter());//le da el formato al eje x
                            grafico_Barras.setData(barDataDeteccion2);//carga los valores a la grafica
                            grafico_Barras.setVisibleXRangeMaximum(3);//muestra solo 3 barras
                            grafico_Barras.setVisibleXRangeMinimum(3);//muestra solo 3 barras
                            barDataDeteccion2.setBarWidth(0.6f);// ajusta ancho de las barras
                            grafico_Barras.animateXY(2000,2000);
                            grafico_Barras.moveViewToAnimated(valores_Actividad_2_Deteccion.size(),grafico_Barras.getBarData().getYMax(), YAxis.AxisDependency.RIGHT,2000);//lo lleva al último valor
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerDiscriminacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        grafico_Barras.clear();
                        break;
                    case 1:

                        if (valores_Actividad_1_Discriminacion.size()==0){
                            Toast.makeText(getContext(), "¡Aún no has realizado esta actividad!", Toast.LENGTH_SHORT).show();
                            grafico_Barras.clear();
                        }else {
                            // MUESTRA GRAFICA ACTIVIDAD 1
                            grafico_Barras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabelsFecha_actividad_1_Discriminacion));//le da el formato al eje x, CARGA FECHAS RESPECTIVAS
                            grafico_Barras.setData(barDataDiscriminacion1);//carga los valores a la grafica
                            grafico_Barras.setVisibleXRangeMinimum(3);//muestra solo 3 barras
                            grafico_Barras.setVisibleXRangeMaximum(3);//muestra solo 3 barras
                            barDataDiscriminacion1.setBarWidth(0.6f);// ajusta ancho de las barras
                            grafico_Barras.animateXY(2000,2000);
                            grafico_Barras.moveViewToAnimated(valores_Actividad_1_Discriminacion.size(),grafico_Barras.getBarData().getYMax(), YAxis.AxisDependency.RIGHT,2000);//lo lleva al último valor
                        }
                        break;
                    case 2:

                        if (valores_Actividad_2_Discriminacion.size()==0){
                            Toast.makeText(getContext(), "¡Aún no has realizado esta actividad!", Toast.LENGTH_SHORT).show();
                            grafico_Barras.clear();
                        }else {
                            // MUESTRA GRAFICA ACTIVIDAD 2
                            grafico_Barras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabelsFecha_actividad_2_Discriminacion));//le da el formato al eje x, CARGA FECHAS RESPECTIVAS
                            grafico_Barras.setData(barDataDiscriminacion2);//carga los valores a la grafica
                            grafico_Barras.setVisibleXRangeMinimum(3);//muestra solo 3 barras
                            grafico_Barras.setVisibleXRangeMaximum(3);//muestra solo 3 barras
                            barDataDiscriminacion2.setBarWidth(0.6f);// ajusta ancho de las barras
                            grafico_Barras.animateXY(2000,2000);
                            grafico_Barras.moveViewToAnimated(valores_Actividad_2_Discriminacion.size(),grafico_Barras.getBarData().getYMax(), YAxis.AxisDependency.RIGHT,2000);//lo lleva al último valor
                        }
                        break;
                    case 3:

                        if (valores_Actividad_3_Discriminacion.size()==0){
                            Toast.makeText(getContext(), "¡Aún no has realizado esta actividad!", Toast.LENGTH_SHORT).show();
                            grafico_Barras.clear();
                        }else {
                            // MUESTRA GRAFICA ACTIVIDAD 3
                            grafico_Barras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabelsFecha_actividad_3_Discriminacion));//le da el formato al eje x, CARGA FECHAS RESPECTIVAS
                            grafico_Barras.setData(barDataDiscriminacion3);//carga los valores a la grafica
                            grafico_Barras.setVisibleXRangeMinimum(3);//muestra solo 3 barras
                            grafico_Barras.setVisibleXRangeMaximum(3);//muestra solo 3 barras
                            barDataDiscriminacion3.setBarWidth(0.6f);// ajusta ancho de las barras
                            grafico_Barras.animateXY(2000,2000);
                            grafico_Barras.moveViewToAnimated(valores_Actividad_3_Discriminacion.size(),grafico_Barras.getBarData().getYMax(), YAxis.AxisDependency.RIGHT,2000);//lo lleva al último valor
                        }
                        break;
                    case 4:

                        if (valores_Actividad_4_Discriminacion.size()==0){
                            Toast.makeText(getContext(), "¡Aún no has realizado esta actividad!", Toast.LENGTH_SHORT).show();
                            grafico_Barras.clear();
                        }else {
                            // MUESTRA GRAFICA ACTIVIDAD 4
                            grafico_Barras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabelsFecha_actividad_4_Discriminacion));//le da el formato al eje x, CARGA FECHAS RESPECTIVAS
                            grafico_Barras.setData(barDataDiscriminacion4);//carga los valores a la grafica
                            grafico_Barras.setVisibleXRangeMinimum(3);//muestra solo 3 barras
                            grafico_Barras.setVisibleXRangeMaximum(3);//muestra solo 3 barras
                            barDataDiscriminacion4.setBarWidth(0.6f);// ajusta ancho de las barras
                            grafico_Barras.animateXY(2000,2000);
                            grafico_Barras.moveViewToAnimated(valores_Actividad_4_Discriminacion.size(),grafico_Barras.getBarData().getYMax(), YAxis.AxisDependency.RIGHT,2000);//lo lleva al último valor
                        }
                        break;
                    case 5:

                        if (valores_Actividad_5_Discriminacion.size()==0){
                            Toast.makeText(getContext(), "¡Aún no has realizado esta actividad!", Toast.LENGTH_SHORT).show();
                            grafico_Barras.clear();
                        }else {
                            // MUESTRA GRAFICA ACTIVIDAD 5
                            grafico_Barras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabelsFecha_actividad_5_Discriminacion));//le da el formato al eje x, CARGA FECHAS RESPECTIVAS
                            grafico_Barras.setData(barDataDiscriminacion5);//carga los valores a la grafica
                            grafico_Barras.setVisibleXRangeMinimum(3);//muestra solo 3 barras
                            grafico_Barras.setVisibleXRangeMaximum(3);//muestra solo 3 barras
                            barDataDiscriminacion5.setBarWidth(0.6f);// ajusta ancho de las barras
                            grafico_Barras.animateXY(2000,2000);
                            grafico_Barras.moveViewToAnimated(valores_Actividad_5_Discriminacion.size(),grafico_Barras.getBarData().getYMax(), YAxis.AxisDependency.RIGHT,2000);//lo lleva al último valor
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerIdentificacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        grafico_Barras.clear();
                        break;
                    case 1:

                        if (valores_Actividad_1_Identificacion.size()==0){
                            Toast.makeText(getContext(), "¡Aún no has realizado esta actividad!", Toast.LENGTH_SHORT).show();
                            grafico_Barras.clear();
                        }else {
                            // MUESTRA GRAFICA ACTIVIDAD 1
                            grafico_Barras.getXAxis().setValueFormatter(grafico_Barras.getDefaultValueFormatter());//le da el formato al eje x
                            //grafico_Barras.getXAxis().setTextSize(16f);//tamaño letras ejeY derecho
                            grafico_Barras.setData(barDataIdentificacion1);//carga los valores a la grafica
                            grafico_Barras.setVisibleXRangeMinimum(3);//muestra solo 3 barras
                            grafico_Barras.setVisibleXRangeMaximum(3);//muestra solo 3 barras
                            barDataIdentificacion1.setBarWidth(0.6f);// ajusta ancho de las barras
                            grafico_Barras.animateXY(2000,2000);
                            grafico_Barras.moveViewToAnimated(valores_Actividad_1_Identificacion.size(),grafico_Barras.getBarData().getYMax(), YAxis.AxisDependency.RIGHT,2000);//lo lleva al último valor
                        }
                        break;
                    case 2:

                        if (valores_Actividad_2_Identificacion.size()==0){
                            Toast.makeText(getContext(), "¡Aún no has realizado esta actividad!", Toast.LENGTH_SHORT).show();
                            grafico_Barras.clear();
                        }else {
                            // MUESTRA GRAFICA ACTIVIDAD 2
                            grafico_Barras.getXAxis().setValueFormatter(grafico_Barras.getDefaultValueFormatter());//le da el formato al eje x
                            //grafico_Barras.getXAxis().setTextSize(16f);//tamaño letras ejeY derecho
                            grafico_Barras.setData(barDataIdentificacion2);//carga los valores a la grafica
                            grafico_Barras.setVisibleXRangeMinimum(3);//muestra solo 3 barras
                            grafico_Barras.setVisibleXRangeMaximum(3);//muestra solo 3 barras
                            barDataIdentificacion2.setBarWidth(0.6f);// ajusta ancho de las barras
                            grafico_Barras.animateXY(2000,2000);
                            grafico_Barras.moveViewToAnimated(valores_Actividad_2_Identificacion.size(),grafico_Barras.getBarData().getYMax(), YAxis.AxisDependency.RIGHT,2000);//lo lleva al último valor
                        }
                        break;
                    case 3:

                        if (valores_Actividad_3_Identificacion.size()==0){
                            Toast.makeText(getContext(), "¡Aún no has realizado esta actividad!", Toast.LENGTH_SHORT).show();
                            grafico_Barras.clear();
                        }else {
                            // MUESTRA GRAFICA ACTIVIDAD 3
                            grafico_Barras.getXAxis().setValueFormatter(grafico_Barras.getDefaultValueFormatter());//le da el formato al eje x
                            //grafico_Barras.getXAxis().setTextSize(16f);//tamaño letras ejeY derecho
                            grafico_Barras.setData(barDataIdentificacion3);//carga los valores a la grafica
                            grafico_Barras.setVisibleXRangeMinimum(3);//muestra solo 3 barras
                            grafico_Barras.setVisibleXRangeMaximum(3);//muestra solo 3 barras
                            barDataIdentificacion3.setBarWidth(0.6f);// ajusta ancho de las barras
                            grafico_Barras.animateXY(2000,2000);
                            grafico_Barras.moveViewToAnimated(valores_Actividad_3_Identificacion.size(),grafico_Barras.getBarData().getYMax(), YAxis.AxisDependency.RIGHT,2000);//lo lleva al último valor
                        }
                        break;
                    case 4:

                        if (valores_Actividad_4_Identificacion.size()==0){
                            Toast.makeText(getContext(), "¡Aún no has realizado esta actividad!", Toast.LENGTH_SHORT).show();
                            grafico_Barras.clear();
                        }else {
                            // MUESTRA GRAFICA ACTIVIDAD 4
                            grafico_Barras.getXAxis().setValueFormatter(grafico_Barras.getDefaultValueFormatter());//le da el formato al eje x
                            //grafico_Barras.getXAxis().setTextSize(16f);//tamaño letras ejeY derecho
                            grafico_Barras.setData(barDataIdentificacion4);//carga los valores a la grafica
                            grafico_Barras.setVisibleXRangeMinimum(3);//muestra solo 3 barras
                            grafico_Barras.setVisibleXRangeMaximum(3);//muestra solo 3 barras
                            barDataIdentificacion4.setBarWidth(0.6f);// ajusta ancho de las barras
                            grafico_Barras.animateXY(2000,2000);
                            grafico_Barras.moveViewToAnimated(valores_Actividad_4_Identificacion.size(),grafico_Barras.getBarData().getYMax(), YAxis.AxisDependency.RIGHT,2000);//lo lleva al último valor
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerComprension.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        grafico_Barras.clear();
                        break;
                    case 1:

                        if (valores_Actividad_1_Comprension.size()==0){
                            Toast.makeText(getContext(), "¡Aún no has realizado esta actividad!", Toast.LENGTH_SHORT).show();
                            grafico_Barras.clear();
                        }else {
                            // MUESTRA GRAFICA ACTIVIDAD 1
                            //grafico_Barras.getXAxis().setValueFormatter(grafico_Barras.getDefaultValueFormatter());//le da el formato al eje x
                            grafico_Barras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabelsFecha_actividad_1_Comprension));//le da el formato al eje x
                            grafico_Barras.setData(barDataComprension1);//carga los valores a la grafica
                            grafico_Barras.setVisibleXRangeMinimum(3);//muestra solo 3 barras
                            grafico_Barras.setVisibleXRangeMaximum(3);//muestra solo 3 barras
                            barDataComprension1.setBarWidth(0.6f);// ajusta ancho de las barras
                            //grafico_Barras.moveViewToX(valores_Actividad_1_Comprension.size());
                            grafico_Barras.animateXY(2000,2000);
                            grafico_Barras.moveViewToAnimated(valores_Actividad_1_Comprension.size(),grafico_Barras.getBarData().getYMax(), YAxis.AxisDependency.RIGHT,2000);//lo lleva al último valor

                        }
                        break;
                    case 2:

                        if (valores_Actividad_2_Comprension.size()==0){
                            Toast.makeText(getContext(), "¡Aún no has realizado esta actividad!", Toast.LENGTH_SHORT).show();
                            grafico_Barras.clear();
                        }else {
                            // MUESTRA GRAFICA ACTIVIDAD 2
                            grafico_Barras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabelsFecha_actividad_2_Comprension));//le da el formato al eje x
                            grafico_Barras.setData(barDataComprension2);//carga los valores a la grafica
                            grafico_Barras.setVisibleXRangeMinimum(3);//muestra solo 3 barras
                            grafico_Barras.setVisibleXRangeMaximum(3);//muestra solo 3 barras
                            barDataComprension2.setBarWidth(0.6f);// ajusta ancho de las barras
                            grafico_Barras.animateXY(2000,2000);
                            grafico_Barras.moveViewToAnimated(valores_Actividad_2_Comprension.size(),grafico_Barras.getBarData().getYMax(), YAxis.AxisDependency.RIGHT,2000);//lo lleva al último valor
                        }
                        break;
                    case 3:

                        if (valores_Actividad_3_Comprension.size()==0){
                            Toast.makeText(getContext(), "¡Aún no has realizado esta actividad!", Toast.LENGTH_SHORT).show();
                            grafico_Barras.clear();
                        }else {
                            // MUESTRA GRAFICA ACTIVIDAD 3
                            grafico_Barras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabelsFecha_actividad_3_Comprension));//le da el formato al eje x
                            grafico_Barras.setData(barDataComprension3);//carga los valores a la grafica
                            grafico_Barras.setVisibleXRangeMinimum(3);//muestra solo 3 barras
                            grafico_Barras.setVisibleXRangeMaximum(3);//muestra solo 3 barras
                            barDataComprension3.setBarWidth(0.6f);// ajusta ancho de las barras                            grafico_Barras.animateXY(2000,2000);
                            grafico_Barras.moveViewToAnimated(valores_Actividad_3_Comprension.size(),grafico_Barras.getBarData().getYMax(), YAxis.AxisDependency.RIGHT,2000);//lo lleva al último valor
                        }
                        break;
                    case 4:

                        if (valores_Actividad_4_Comprension.size()==0){
                            Toast.makeText(getContext(), "¡Aún no has realizado esta actividad!", Toast.LENGTH_SHORT).show();
                            grafico_Barras.clear();
                        }else {
                            // MUESTRA GRAFICA ACTIVIDAD 4
                            grafico_Barras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabelsFecha_actividad_4_Comprension));//le da el formato al eje x
                            grafico_Barras.setData(barDataComprension4);//carga los valores a la grafica
                            grafico_Barras.setVisibleXRangeMinimum(3);//muestra solo 3 barras
                            grafico_Barras.setVisibleXRangeMaximum(3);//muestra solo 3 barras
                            barDataComprension4.setBarWidth(0.6f);// ajusta ancho de las barras
                            grafico_Barras.animateXY(2000,2000);
                            grafico_Barras.moveViewToAnimated(valores_Actividad_4_Comprension.size(),grafico_Barras.getBarData().getYMax(), YAxis.AxisDependency.RIGHT,2000);//lo lleva al último valor

                        }
                        break;
                    case 5:

                        if (valores_Actividad_5_Comprension.size()==0){
                            Toast.makeText(getContext(), "¡Aún no has realizado esta actividad!", Toast.LENGTH_SHORT).show();
                            grafico_Barras.clear();
                        }else {
                            // MUESTRA GRAFICA ACTIVIDAD 4
                            grafico_Barras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabelsFecha_actividad_5_Comprension));//le da el formato al eje x
                            grafico_Barras.setData(barDataComprension5);//carga los valores a la grafica
                            grafico_Barras.animateXY(2000,2000);
                            grafico_Barras.setVisibleXRangeMinimum(3);//muestra solo 3 barras
                            grafico_Barras.setVisibleXRangeMaximum(3);//muestra solo 3 barras
                            barDataComprension5.setBarWidth(0.6f);// ajusta ancho de las barras
                            grafico_Barras.moveViewToAnimated(valores_Actividad_5_Comprension.size(),grafico_Barras.getBarData().getYMax(), YAxis.AxisDependency.RIGHT,2000);//lo lleva al último valor
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

/*
        btn_exportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                grafico_Barras.saveToGallery("grafica_MARGLO", 100);
                Toast.makeText(getContext(), "¡Gráfica guardada!", Toast.LENGTH_SHORT).show();


            }
        });
*/


    }//onviewcreated


    public class CustomXAxisRenderer extends XAxisRenderer {//clase para poder poner la fecha en el eje x
        public CustomXAxisRenderer(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer trans) {
            super(viewPortHandler, xAxis, trans);
        }

        @Override
        protected void drawLabel(Canvas c, String formattedLabel, float x, float y,
                                 MPPointF anchor, float angleDegrees) {
            String line[] = formattedLabel.split(" ");//me divide la cadena al encontrar un " " espacio
            /*Utils.drawXAxisValue(c, line[0], x, y, mAxisLabelPaint, anchor, angleDegrees);
            for (int i = 1; i < line.length; i++) { // we've already processed 1st line
                Utils.drawXAxisValue(c, line[i], x, y + mAxisLabelPaint.getTextSize() * i,
                        mAxisLabelPaint, anchor, angleDegrees);
            }*/
            Utils.drawXAxisValue(c, line[0], x, y, mAxisLabelPaint, anchor, angleDegrees);
            Utils.drawXAxisValue(c, line[1], x + mAxisLabelPaint.getTextSize(), y + mAxisLabelPaint.getTextSize(), mAxisLabelPaint, anchor, angleDegrees);
        }
    }

    public class MyValueFormatterEnteros extends ValueFormatter {//clase para quitar decimales de los datos de las barras

        private DecimalFormat mFormat;

        public MyValueFormatterEnteros() {
            mFormat = new DecimalFormat("###,###,##0"); // sin decimales
        }

        @Override
        public String getBarLabel(BarEntry barEntry) {
            // write your logic here
            return mFormat.format(barEntry.getY());
        }
    }


}//final