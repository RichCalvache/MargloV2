package com.example.marglov2.OtrosFragments;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.marglov2.Entidades.DBHelperPuntajes;
import com.example.marglov2.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Graficas1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Graficas1Fragment extends Fragment {

    int colorNegro = Color.parseColor("#FF000000");
    int colorDeteccion = Color.parseColor("#8FE1F7");
    int colorDiscriminacion = Color.parseColor("#EF9A9A");
    int colorIdentificacion = Color.parseColor("#FFF59D");
    int colorComprension = Color.parseColor("#C5E1A5");

    int puntajeTotal_Deteccion,puntajeTemp_Deteccion = 0;
    int puntajeTotal_Discriminacion, puntajeTemp_Discriminacion=0;
    int puntajeTotal_Identificacion, puntajeTemp_Identificacion=0;
    int puntajeTotal_Comprension, puntajeTemp_Comprension=0;



    String habilidad;

    DBHelperPuntajes DB;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Graficas1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Graficas1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Graficas1Fragment newInstance(String param1, String param2) {
        Graficas1Fragment fragment = new Graficas1Fragment();
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
        return inflater.inflate(R.layout.fragment_graficas1, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





        //bd
        DB = new DBHelperPuntajes(getContext());

        // funcion para controlar la navegacion hacia otras pantallas
        final NavController navControllermio = Navigation.findNavController(view); //nacControllermio es el nombre de la instancia


        String habilidad;

        //OBTENER PUNTAJES DE LA DB PARA MOSTRAR EN LAS GRAFICAS
        DB = new DBHelperPuntajes(getContext());
        Cursor res = DB.getdataPuntajes();
        if (res.getCount() == 0) {
            Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }

        while (res.moveToNext()) {


            habilidad = res.getString(2);

            switch (habilidad){
                case "Detección":
                    puntajeTotal_Deteccion = parseInt(res.getString(3)) + puntajeTemp_Deteccion;
                    puntajeTemp_Deteccion = puntajeTotal_Deteccion;
                    break;
                case "Discriminación":
                    puntajeTotal_Discriminacion = parseInt(res.getString(3)) + puntajeTemp_Discriminacion;
                    puntajeTemp_Discriminacion =puntajeTotal_Discriminacion;
                    break;
                case "Identificación":
                    puntajeTotal_Identificacion = parseInt(res.getString(3)) + puntajeTemp_Comprension;
                    puntajeTemp_Identificacion =puntajeTotal_Identificacion;
                    break;
                case "Comprensión":
                    puntajeTotal_Comprension = parseInt(res.getString(3)) + puntajeTemp_Comprension;
                    puntajeTemp_Comprension =puntajeTotal_Comprension;
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

        ArrayList<BarEntry> valores_ActividadesGeneral = new ArrayList<>();
        valores_ActividadesGeneral.add(new BarEntry(0,puntajeTotal_Deteccion));
        valores_ActividadesGeneral.add(new BarEntry(1,puntajeTotal_Discriminacion));
        valores_ActividadesGeneral.add(new BarEntry(2,puntajeTotal_Identificacion));
        valores_ActividadesGeneral.add(new BarEntry(3,puntajeTotal_Comprension));

        //PARA CAMBIAR LOS VALORES DEL EJE X, PONER STRING
        ArrayList<String> xAxisLabels = new ArrayList<>();
        xAxisLabels.add("Detección");
        xAxisLabels.add("Discrminación");
        xAxisLabels.add("Identificación");
        xAxisLabels.add("Comprensión");

        grafico_Barras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));//le da el formato al eje x
        grafico_Barras.getXAxis().setGranularity(1f); //para que salgan ordenados el eje x.. only intervals of 1 day
        grafico_Barras.getLegend().setEnabled(false);// oculta legend (cuadritos con etiqeutea)
        grafico_Barras.getAxisLeft().setTextSize(R.dimen.textSize_ejeY);//tamaño letras ejeY derecho
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
        //grafico_Barras.getXAxis().setCenterAxisLabels(true);


        BarDataSet barDataSet = new BarDataSet(valores_ActividadesGeneral, "Habilidades");
        //barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        //para los colores de las barras
        barDataSet.setColors(colorDeteccion,colorDiscriminacion,colorIdentificacion,colorComprension);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(18f);
        barDataSet.setBarBorderWidth(1.f);

        BarData barData =  new BarData(barDataSet);

        grafico_Barras.setFitBars(true);
        grafico_Barras.setData(barData);
        //grafico_Barras.getDescription().setText("Gráfico Barras Visitantes");
        grafico_Barras.animateXY(2000,2000);







    }//onviewcreated







}//final