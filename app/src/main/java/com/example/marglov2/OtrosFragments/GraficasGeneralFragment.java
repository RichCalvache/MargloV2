package com.example.marglov2.OtrosFragments;

import android.database.Cursor;
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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.marglov2.Entidades.DBHelperPuntajes;
import com.example.marglov2.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GraficasGeneralFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GraficasGeneralFragment extends Fragment {

    private Button btn_vergraficas;

    int colorNegro = Color.parseColor("#FF000000");
    int colorDeteccion = Color.parseColor("#8FE1F7");
    int colorDiscriminacion = Color.parseColor("#EF9A9A");
    int colorIdentificacion = Color.parseColor("#FFF59D");
    int colorComprension = Color.parseColor("#C5E1A5");





    String habilidad;

    DBHelperPuntajes DB;



    private Spinner spinnerHabilidades;

    private static final String[] itemsHabilidades = {"Seleccione una habilidad...","General","Detección","Discriminación", "Identificación", "Comprensión"};
    private static final String[] itemsTipoGrafico = {"Gráfico de barras", "Rendimiento esperado"};
    private static final String[] itemsDeteccion = {"Actividad 1", "Actividad 2"};





    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GraficasGeneralFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GraficasGeneralFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GraficasGeneralFragment newInstance(String param1, String param2) {
        GraficasGeneralFragment fragment = new GraficasGeneralFragment();
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
        return inflater.inflate(R.layout.fragment_graficas_general, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_vergraficas = view.findViewById(R.id.btn_ver_graficasfecha);

        Typeface tf = ResourcesCompat.getFont(getContext(), R.font.adamina);

        //bd
        DB = new DBHelperPuntajes(getContext());

        // funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(view); //nacControllermio es el nombre de la instancia


        String habilidad;
        int actividad;

        int puntajeTotal_Deteccion=0,puntajeTemp_Deteccion = 0;
        int puntajeTotal_Discriminacion=0, puntajeTemp_Discriminacion=0;
        int puntajeTotal_Identificacion=0, puntajeTemp_Identificacion=0;
        int puntajeTotal_Comprension=0, puntajeTemp_Comprension=0;

        int puntajeTotal_act_1_Deteccion=0,puntajeTemp_act_1_Deteccion = 0;
        int puntajeTotal_act_2_Deteccion=0,puntajeTemp_act_2_Deteccion = 0;

        int puntajeTotal_act_1_Discriminacion=0,puntajeTemp_act_1_Discriminacion = 0;
        int puntajeTotal_act_2_Discriminacion=0,puntajeTemp_act_2_Discriminacion = 0;
        int puntajeTotal_act_3_Discriminacion=0,puntajeTemp_act_3_Discriminacion = 0;
        int puntajeTotal_act_4_Discriminacion=0,puntajeTemp_act_4_Discriminacion = 0;
        int puntajeTotal_act_5_Discriminacion=0,puntajeTemp_act_5_Discriminacion = 0;

        int puntajeTotal_act_1_Identificacion=0,puntajeTemp_act_1_Identificacion = 0;
        int puntajeTotal_act_2_Identificacion=0,puntajeTemp_act_2_Identificacion = 0;
        int puntajeTotal_act_3_Identificacion=0,puntajeTemp_act_3_Identificacion = 0;
        int puntajeTotal_act_4_Identificacion=0,puntajeTemp_act_4_Identificacion = 0;

        int puntajeTotal_act_1_Comprension=0,puntajeTemp_act_1_Comprension = 0;
        int puntajeTotal_act_2_Comprension=0,puntajeTemp_act_2_Comprension = 0;
        int puntajeTotal_act_3_Comprension=0,puntajeTemp_act_3_Comprension = 0;
        int puntajeTotal_act_4_Comprension=0,puntajeTemp_act_4_Comprension = 0;
        int puntajeTotal_act_5_Comprension=0,puntajeTemp_act_5_Comprension = 0;


        //SPINNER HABILIDADES
        spinnerHabilidades = (Spinner) view.findViewById(R.id.spinnerHabilidades_id);
        ArrayAdapter<String> adapterHabilidades = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, itemsHabilidades);
        adapterHabilidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHabilidades.setAdapter(adapterHabilidades);



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

            switch (habilidad) {
                case "Detección":
                    //puntaje general
                    puntajeTotal_Deteccion = res.getInt(3) + puntajeTemp_Deteccion;
                    puntajeTemp_Deteccion = puntajeTotal_Deteccion;

                    actividad = res.getInt(1);

                    switch (actividad) {
                        case 1:
                            //puntaje act 1 deteccion
                            puntajeTotal_act_1_Deteccion = res.getInt(3) + puntajeTemp_act_1_Deteccion;
                            puntajeTemp_act_1_Deteccion = puntajeTotal_act_1_Deteccion;
                            break;
                        case 2:
                            //puntaje act 1 deteccion
                            puntajeTotal_act_2_Deteccion = res.getInt(3) + puntajeTemp_act_2_Deteccion;
                            puntajeTemp_act_2_Deteccion = puntajeTotal_act_2_Deteccion;
                            break;
                    }

                    break;
                case "Discriminación":
                    //puntaje general
                    puntajeTotal_Discriminacion = res.getInt(3) + puntajeTemp_Discriminacion;
                    puntajeTemp_Discriminacion = puntajeTotal_Discriminacion;

                    actividad = res.getInt(1);

                    switch (actividad) {
                        case 1:
                            //puntaje act 1 discriminacion
                            puntajeTotal_act_1_Discriminacion = res.getInt(3) + puntajeTemp_act_1_Discriminacion;
                            puntajeTemp_act_1_Discriminacion = puntajeTotal_act_1_Discriminacion;
                            break;
                        case 2:
                            //puntaje act 2 discriminacion
                            puntajeTotal_act_2_Discriminacion = res.getInt(3) + puntajeTemp_act_2_Discriminacion;
                            puntajeTemp_act_2_Discriminacion = puntajeTotal_act_2_Discriminacion;
                            break;
                        case 3:
                            //puntaje act 3 discriminacion
                            puntajeTotal_act_3_Discriminacion = res.getInt(3) + puntajeTemp_act_3_Discriminacion;
                            puntajeTemp_act_3_Discriminacion = puntajeTotal_act_3_Discriminacion;
                            break;
                        case 4:
                            //puntaje act 4 discriminacion
                            puntajeTotal_act_4_Discriminacion = res.getInt(3) + puntajeTemp_act_4_Discriminacion;
                            puntajeTemp_act_4_Discriminacion = puntajeTotal_act_4_Discriminacion;
                            break;
                        case 5:
                            //puntaje act 5 discriminacion
                            puntajeTotal_act_5_Discriminacion = res.getInt(3) + puntajeTemp_act_5_Discriminacion;
                            puntajeTemp_act_5_Discriminacion = puntajeTotal_act_5_Discriminacion;
                            break;
                    }
                    break;
                case "Identificación":
                    //puntaje general
                    puntajeTotal_Identificacion = res.getInt(3) + puntajeTemp_Identificacion;
                    puntajeTemp_Identificacion = puntajeTotal_Identificacion;

                    actividad = res.getInt(1);

                    switch (actividad) {
                        case 1:
                            //puntaje act 1 discriminacion
                            puntajeTotal_act_1_Identificacion = res.getInt(3) + puntajeTemp_act_1_Identificacion;
                            puntajeTemp_act_1_Identificacion = puntajeTotal_act_1_Identificacion;
                            break;
                        case 2:
                            //puntaje act 2 discriminacion
                            puntajeTotal_act_2_Identificacion = res.getInt(3) + puntajeTemp_act_2_Identificacion;
                            puntajeTemp_act_2_Identificacion = puntajeTotal_act_2_Identificacion;
                            break;
                        case 3:
                            //puntaje act 3 discriminacion
                            puntajeTotal_act_3_Identificacion = res.getInt(3) + puntajeTemp_act_3_Identificacion;
                            puntajeTemp_act_3_Identificacion = puntajeTotal_act_3_Identificacion;
                            break;
                        case 4:
                            //puntaje act 4 discriminacion
                            puntajeTotal_act_4_Identificacion = res.getInt(3) + puntajeTemp_act_4_Identificacion;
                            puntajeTemp_act_4_Identificacion = puntajeTotal_act_4_Identificacion;
                            break;
                    }
                    break;
                case "Comprensión":
                    //puntaje general
                    puntajeTotal_Comprension = res.getInt(3) + puntajeTemp_Comprension;
                    puntajeTemp_Comprension = puntajeTotal_Comprension;

                    actividad = res.getInt(1);

                    switch (actividad) {
                        case 1:
                            //puntaje act 1 discriminacion
                            puntajeTotal_act_1_Comprension = res.getInt(3) + puntajeTemp_act_1_Comprension;
                            puntajeTemp_act_1_Comprension = puntajeTotal_act_1_Comprension;
                            break;
                        case 2:
                            //puntaje act 2 discriminacion
                            puntajeTotal_act_2_Comprension = res.getInt(3) + puntajeTemp_act_2_Comprension;
                            puntajeTemp_act_2_Comprension = puntajeTotal_act_2_Comprension;
                            break;
                        case 3:
                            //puntaje act 3 discriminacion
                            puntajeTotal_act_3_Comprension = res.getInt(3) + puntajeTemp_act_3_Comprension;
                            puntajeTemp_act_3_Comprension = puntajeTotal_act_3_Comprension;
                            break;
                        case 4:
                            //puntaje act 4 discriminacion
                            puntajeTotal_act_4_Comprension = res.getInt(3) + puntajeTemp_act_4_Comprension;
                            puntajeTemp_act_4_Comprension = puntajeTotal_act_4_Comprension;
                            break;
                        case 5:
                            //puntaje act 5 discriminacion
                            puntajeTotal_act_5_Comprension = res.getInt(3) + puntajeTemp_act_5_Comprension;
                            puntajeTemp_act_5_Comprension = puntajeTotal_act_5_Comprension;
                            break;
                    }

                    break;
            }
            /*
            buffer.append("Fecha: " + res.getString(0) + "\n");
            buffer.append("Actividad: " + res.getString(1) + "\n");
            buffer.append("Habilidad: " + res.getString(2) + "\n");
            buffer.append("Puntaje: " + res.getString(3) + "\n");
            buffer.append("Información: " + res.getString(4) + "\n\n");
            */
            //puntajeTemp_Deteccion = puntajeTotal_Deteccion;
            //puntajeTemp_Discriminacion =puntajeTotal_Discriminacion;
        }


        //PARA LOS GRAFICOS
        //BarChart barChart = view.findViewById(R.id.graficoBarrasid);
        BarChart grafico_Barras = view.findViewById(R.id.graficoBarrasid);

        /* EJEMPLO
        ArrayList<BarEntry> visitors = new ArrayList<>();
        visitors.add(new BarEntry(2014,420));
        visitors.add(new BarEntry(2015,430));
        visitors.add(new BarEntry(2016,430));
        visitors.add(new BarEntry(2017,410));
        visitors.add(new BarEntry(2018,100));
        visitors.add(new BarEntry(2019,100));
        visitors.add(new BarEntry(2020,200));

        BarDataSet barDataSet = new BarDataSet(visitors, "visitors");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData =  new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Gráfico Barras Visitantes");
        barChart.animateY(2000);

         */

        //ARREGLOS DE VALORES
        ArrayList<BarEntry> valores_ActividadesGeneral = new ArrayList<>();
        valores_ActividadesGeneral.add(new BarEntry(0, puntajeTotal_Deteccion));
        valores_ActividadesGeneral.add(new BarEntry(1, puntajeTotal_Discriminacion));
        valores_ActividadesGeneral.add(new BarEntry(2, puntajeTotal_Identificacion));
        valores_ActividadesGeneral.add(new BarEntry(3, puntajeTotal_Comprension));




        ArrayList<BarEntry> valores_ActividadesDeteccion = new ArrayList<>();
        valores_ActividadesDeteccion.add(new BarEntry(0, puntajeTotal_act_1_Deteccion));
        valores_ActividadesDeteccion.add(new BarEntry(1, puntajeTotal_act_2_Deteccion));

        ArrayList<BarEntry> valores_ActividadesDiscriminacion = new ArrayList<>();
        valores_ActividadesDiscriminacion.add(new BarEntry(0, puntajeTotal_act_1_Discriminacion));
        valores_ActividadesDiscriminacion.add(new BarEntry(1, puntajeTotal_act_2_Discriminacion));
        valores_ActividadesDiscriminacion.add(new BarEntry(2, puntajeTotal_act_3_Discriminacion));
        valores_ActividadesDiscriminacion.add(new BarEntry(3, puntajeTotal_act_4_Discriminacion));
        valores_ActividadesDiscriminacion.add(new BarEntry(4, puntajeTotal_act_5_Discriminacion));

        ArrayList<BarEntry> valores_ActividadesIdentificacion = new ArrayList<>();
        valores_ActividadesIdentificacion.add(new BarEntry(0, puntajeTotal_act_1_Identificacion));
        valores_ActividadesIdentificacion.add(new BarEntry(1, puntajeTotal_act_2_Identificacion));
        valores_ActividadesIdentificacion.add(new BarEntry(2, puntajeTotal_act_3_Identificacion));
        valores_ActividadesIdentificacion.add(new BarEntry(3, puntajeTotal_act_4_Identificacion));

        ArrayList<BarEntry> valores_ActividadesComprension = new ArrayList<>();
        valores_ActividadesComprension.add(new BarEntry(0, puntajeTotal_act_1_Comprension));
        valores_ActividadesComprension.add(new BarEntry(1, puntajeTotal_act_2_Comprension));
        valores_ActividadesComprension.add(new BarEntry(2, puntajeTotal_act_3_Comprension));
        valores_ActividadesComprension.add(new BarEntry(3, puntajeTotal_act_4_Comprension));
        valores_ActividadesComprension.add(new BarEntry(4, puntajeTotal_act_5_Comprension));

        //PARA CAMBIAR LOS VALORES DEL EJE X, PONER STRING
        ArrayList<String> xAxisLabels = new ArrayList<>();
        xAxisLabels.add("Detección");
        xAxisLabels.add("Discrminación");
        xAxisLabels.add("Identificación");
        xAxisLabels.add("Comprensión");


        ArrayList<String> xAxisLabelsDeteccion = new ArrayList<>();
        xAxisLabelsDeteccion.add("Actividad 1");
        xAxisLabelsDeteccion.add("Actividad 2");

        ArrayList<String> xAxisLabelsDiscriminacion = new ArrayList<>();
        xAxisLabelsDiscriminacion.add("Actividad 1");
        xAxisLabelsDiscriminacion.add("Actividad 2");
        xAxisLabelsDiscriminacion.add("Actividad 3");
        xAxisLabelsDiscriminacion.add("Actividad 4");
        xAxisLabelsDiscriminacion.add("Actividad 5");

        ArrayList<String> xAxisLabelsIdentificacion = new ArrayList<>();
        xAxisLabelsIdentificacion.add("Actividad 1");
        xAxisLabelsIdentificacion.add("Actividad 2");
        xAxisLabelsIdentificacion.add("Actividad 3");
        xAxisLabelsIdentificacion.add("Actividad 4");

        ArrayList<String> xAxisLabelsComprension = new ArrayList<>();
        xAxisLabelsComprension.add("Actividad 1");
        xAxisLabelsComprension.add("Actividad 2");
        xAxisLabelsComprension.add("Actividad 3");
        xAxisLabelsComprension.add("Actividad 4");
        xAxisLabelsComprension.add("Actividad 5");


        BarDataSet barDataSet = new BarDataSet(valores_ActividadesGeneral, "Habilidades");
        //para las actividades individuales
        BarDataSet barDataSetDeteccion = new BarDataSet(valores_ActividadesDeteccion, "Actividades Detección");
        BarDataSet barDataSetDiscriminacion = new BarDataSet(valores_ActividadesDiscriminacion, "Actividades Discriminación");
        BarDataSet barDataSetIdentificacion = new BarDataSet(valores_ActividadesIdentificacion, "Actividades Identificación");
        BarDataSet barDataSetComprension = new BarDataSet(valores_ActividadesComprension, "Actividades Comprensión");

        //barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        //para los colores de las barras
        barDataSet.setColors(colorDeteccion, colorDiscriminacion, colorIdentificacion, colorComprension);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(20f);
        barDataSet.setBarBorderWidth(1.f);
        barDataSet.setValueTypeface(tf);
        BarData barData = new BarData(barDataSet);//crea los datos a cargar de la barra a partir del dataset
        barData.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal


        //para los colores de las barras
        barDataSetDeteccion.setColors(colorDeteccion);
        barDataSetDeteccion.setValueTextColor(Color.BLACK);
        barDataSetDeteccion.setValueTextSize(20f);
        barDataSetDeteccion.setBarBorderWidth(1.f);
        barDataSetDeteccion.setValueTypeface(tf);
        BarData barDataDeteccion = new BarData(barDataSetDeteccion);//crea los datos a cargar de la barra a partir del dataset
        barDataDeteccion.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal


        //para los colores de las barras
        barDataSetDiscriminacion.setColors(colorDiscriminacion);
        barDataSetDiscriminacion.setValueTextColor(Color.BLACK);
        barDataSetDiscriminacion.setValueTextSize(20f);
        barDataSetDiscriminacion.setBarBorderWidth(1.f);
        barDataSetDiscriminacion.setValueTypeface(tf);
        BarData barDataDiscriminacion =  new BarData(barDataSetDiscriminacion);//crea los datos a cargar de la barra a partir del dataset
        barDataDiscriminacion.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal


        //para los colores de las barras
        barDataSetIdentificacion.setColors(colorIdentificacion);
        barDataSetIdentificacion.setValueTextColor(Color.BLACK);
        barDataSetIdentificacion.setValueTextSize(18f);
        barDataSetIdentificacion.setBarBorderWidth(1.f);
        barDataSetIdentificacion.setValueTypeface(tf);
        BarData barDataIdentificacion =  new BarData(barDataSetIdentificacion);//crea los datos a cargar de la barra a partir del dataset
        barDataIdentificacion.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal


        //para los colores de las barras
        barDataSetComprension.setColors(colorComprension);
        barDataSetComprension.setValueTextColor(Color.BLACK);
        barDataSetComprension.setValueTextSize(18f);
        barDataSetComprension.setBarBorderWidth(1.f);
        barDataSetComprension.setValueTypeface(tf);
        BarData barDataComprension =  new BarData(barDataSetComprension);//crea los datos a cargar de la barra a partir del dataset
        barDataComprension.setValueFormatter(new MyValueFormatterEnteros());//para que los datos salgan sin decimal


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
        //grafico_Barras.getXAxis().setTypeface(t);

        grafico_Barras.getDescription().setEnabled(false);//quitar descripcion
        //grafico_Barras.getXAxis().setCenterAxisLabels(true)
        grafico_Barras.setFitBars(true);//para que las barras salgan del mismo ancho

        //"REPRODUCIR" graficas
        //grafico_Barras.setData(barData);//carga los valores a la grafica
        //grafico_Barras.getDescription().setText("Gráfico Barras Visitantes");
        //grafico_Barras.animateXY(2000,2000);

        //PARA AGREGAR FECHA EN 2 LINEAS Y DIAGONAL EN EL EJE X
        //grafico_Barras.setXAxisRenderer(new GraficasIndividualesFragment.CustomXAxisRenderer(grafico_Barras.getViewPortHandler(), grafico_Barras.getXAxis(), grafico_Barras.getTransformer(YAxis.AxisDependency.LEFT)));
        grafico_Barras.getXAxis().setLabelRotationAngle(-60f);
        grafico_Barras.setExtraBottomOffset(60);

        grafico_Barras.getAxisLeft().setTypeface(tf);
        grafico_Barras.getXAxis().setTypeface(tf);


        spinnerHabilidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        grafico_Barras.clear();
                        break;
                    case 1:
                        // MUESTRA GRAFICA BARRAS GENERAL
                        grafico_Barras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));//le da el formato al eje x
                        grafico_Barras.setData(barData);//carga los valores a la grafica
                        grafico_Barras.animateXY(2000,2000);
                        break;
                    case 2:
                        // MUESTRA GRAFICA BARRAS DETECCION
                        grafico_Barras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabelsDeteccion));//le da el formato al eje x
                        //grafico_Barras.getXAxis().setTextSize(16f);//tamaño letras ejeY derecho
                        grafico_Barras.setData(barDataDeteccion);//carga los valores a la grafica
                        grafico_Barras.animateXY(2000,2000);
                        break;
                    case 3:
                        // MUESTRA GRAFICA BARRAS DISCRIMINACION
                        grafico_Barras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabelsDiscriminacion));//le da el formato al eje x
                        //grafico_Barras.getXAxis().setTextSize(16f);//tamaño letras ejeY derecho
                        grafico_Barras.setData(barDataDiscriminacion);//carga los valores a la grafica
                        grafico_Barras.animateXY(2000,2000);
                        break;
                    case 4:
                        // MUESTRA GRAFICA BARRAS IDENTIFICACION
                        grafico_Barras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabelsIdentificacion));//le da el formato al eje x
                        //grafico_Barras.getXAxis().setTextSize(16f);//tamaño letras ejeY derecho
                        grafico_Barras.setData(barDataIdentificacion);//carga los valores a la grafica
                        grafico_Barras.animateXY(2000,2000);
                        break;
                    case 5:
                        // MUESTRA GRAFICA BARRAS COMPRENSION
                        grafico_Barras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabelsComprension));//le da el formato al eje x
                        //grafico_Barras.getXAxis().setTextSize(16f);//tamaño letras ejeY derecho
                        grafico_Barras.setData(barDataComprension);//carga los valores a la grafica
                        grafico_Barras.animateXY(2000,2000);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //FUNCION PARA EL BOTON GRAFICAS
        btn_vergraficas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                grafico_Barras.clear();
                spinnerHabilidades.setSelection(0);
                //navControllermio.navigate(R.id.actividad_conc_1_ayudasFragment); //pasar a otro fragment
                //navControllermio.navigate(R.id.action_actividad_discr_3Fragment_to_actividad_discr_3_instruccionesFragment); //pasar a otro fragment con actions
                navControllermio.navigate(R.id.action_graficasGeneralFragment_to_graficasIndividualesFragment);


            }
        });



    }//onviewcreated



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