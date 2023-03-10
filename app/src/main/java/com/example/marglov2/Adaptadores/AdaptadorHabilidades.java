package com.example.marglov2.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.marglov2.Entidades.ActividadVo;
import com.example.marglov2.Interfaces.RecyclerClickActividadesInterface;
import com.example.marglov2.OtrosFragments.MenuHabilidadesFragment;
import com.example.marglov2.R;

import java.util.ArrayList;


public class AdaptadorHabilidades extends RecyclerView.Adapter<AdaptadorHabilidades.ViewHolderHabilidades> {

    private RecyclerClickActividadesInterface recyclerClickActividadesInterface;
    ArrayList<ActividadVo> listaHabilidades;


    public AdaptadorHabilidades(ArrayList<ActividadVo> listaHabilidades, MenuHabilidadesFragment recyclerClickActividadesInterface) {
        this.listaHabilidades = listaHabilidades;
        this.recyclerClickActividadesInterface = recyclerClickActividadesInterface;
    }

    public AdaptadorHabilidades.ViewHolderHabilidades onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_habilidades, null,false);
        //Se agrega esto para que al rotar ocupe toda la pantalla
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);

        return new AdaptadorHabilidades.ViewHolderHabilidades(view);
    }

    @Override
    public void onBindViewHolder(AdaptadorHabilidades.ViewHolderHabilidades holder, int position) {
        holder.etiNombre.setText(listaHabilidades.get(position).getNombre());
        holder.etiInformacion.setText(listaHabilidades.get(position).getInfo());
        holder.foto.setImageResource(listaHabilidades.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listaHabilidades.size();
    }

    public class ViewHolderHabilidades extends RecyclerView.ViewHolder {

        TextView etiNombre, etiInformacion;
        ImageView foto;


        public ViewHolderHabilidades(View itemView) {
            super(itemView);

            etiNombre = (TextView) itemView.findViewById(R.id.idNombre);
            etiInformacion = (TextView) itemView.findViewById(R.id.idInfo);
            foto = (ImageView) itemView.findViewById(R.id.idImagen);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //recyclerClickActividadesInterface.onItemClick(getAdapterPosition());//getabsouleposition
                    recyclerClickActividadesInterface.onItemClick(getAbsoluteAdapterPosition());
                }
            });

        }
    }
}
