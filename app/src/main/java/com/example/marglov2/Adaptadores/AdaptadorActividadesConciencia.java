package com.example.marglov2.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.marglov2.Entidades.ActividadVo;
import com.example.marglov2.FragmentsDeteccion.ListaConcienciaFragment;
import com.example.marglov2.Interfaces.RecyclerClickActividadesInterface;
import com.example.marglov2.R;


import java.util.ArrayList;

public class AdaptadorActividadesConciencia extends RecyclerView.Adapter<AdaptadorActividadesConciencia.ViewHolderActividades> {

    private RecyclerClickActividadesInterface recyclerClickActividadesInterface;
    ArrayList<ActividadVo> listaActividades;

    public AdaptadorActividadesConciencia(ArrayList<ActividadVo> listaActividades, ListaConcienciaFragment recyclerClickActividadesInterface) {
        this.listaActividades = listaActividades;
        this.recyclerClickActividadesInterface = recyclerClickActividadesInterface;
    }


    public AdaptadorActividadesConciencia.ViewHolderActividades onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_actividades_conciencia,null,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolderActividades(view );

    }

    @Override
    public void onBindViewHolder(AdaptadorActividadesConciencia.ViewHolderActividades holder, int position) {

        holder.etiNombre.setText(listaActividades.get(position).getNombre());
        holder.etiInformacion.setText(listaActividades.get(position).getInfo());
        holder.foto.setImageResource(listaActividades.get(position).getFoto());

    }

    @Override
    public int getItemCount() {
        return listaActividades.size();
    }

    public class ViewHolderActividades extends RecyclerView.ViewHolder {

        TextView etiNombre, etiInformacion;
        ImageView foto;


        public ViewHolderActividades(View itemView) {
            super(itemView);

            etiNombre = (TextView) itemView.findViewById(R.id.idNombre);
            etiInformacion = (TextView) itemView.findViewById(R.id.idInfo);
            foto = (ImageView) itemView.findViewById(R.id.idImagen);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    recyclerClickActividadesInterface.onItemClick(getAbsoluteAdapterPosition());

                }
            });

        }
    }
}
