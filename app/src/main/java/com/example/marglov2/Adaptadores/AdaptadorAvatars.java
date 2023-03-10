package com.example.marglov2.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marglov2.Entidades.AvatarVo;
import com.example.marglov2.Interfaces.RecyclerClickAvatarsInterface;
import com.example.marglov2.OtrosFragments.EditarPerfilFragment;
import com.example.marglov2.OtrosFragments.RegistroUsuarioFragment;
import com.example.marglov2.R;

import java.util.ArrayList;

public class AdaptadorAvatars extends RecyclerView.Adapter<AdaptadorAvatars.ViewHolderAvatars> {


    private RecyclerClickAvatarsInterface recyclerClickAvatarsInterface;
    ArrayList<AvatarVo> listaAvatars;

    View view;
    int posicionMarcada =0;
    int pos =0;


    public AdaptadorAvatars(ArrayList<AvatarVo> listaAvatars, RegistroUsuarioFragment recyclerClickAvatarsInterface) {
        this.listaAvatars = listaAvatars;
        this.recyclerClickAvatarsInterface = (RecyclerClickAvatarsInterface) recyclerClickAvatarsInterface;
    }

    public AdaptadorAvatars(ArrayList<AvatarVo> listaAvatars, EditarPerfilFragment recyclerClickAvatarsInterface) {
        this.listaAvatars = listaAvatars;
        this.recyclerClickAvatarsInterface = (RecyclerClickAvatarsInterface) recyclerClickAvatarsInterface;
    }



    @Override
    public ViewHolderAvatars onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_avatars,null,false);
        //Se agrega esto para que al rotar ocupe toda la pantalla
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolderAvatars(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderAvatars holder, int position) {

        holder.avatar.setImageResource(listaAvatars.get(position).getAvatarId());

        final int pos=position;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerClickAvatarsInterface.onItemClick(position);

                posicionMarcada=pos;
                notifyDataSetChanged();

            }
        });

        if (posicionMarcada==position){
            holder.barra.setBackgroundResource(R.color.teal_200);

        }else{
            holder.barra.setBackgroundResource(R.color.white);
            //holder.barra.setBackgroundColor(view.getResources().getColor(R.color.white));
        }





    }

    @Override
    public int getItemCount() {
        return listaAvatars.size();
    }

    public class ViewHolderAvatars extends RecyclerView.ViewHolder {

        TextView barra;
        ImageView avatar;
        CardView cardAvatar;

        public ViewHolderAvatars(View itemView) {
            super(itemView);

            barra = (TextView) itemView.findViewById(R.id.barraSeleccionId);
            avatar = (ImageView) itemView.findViewById(R.id.idAvatar);

            cardAvatar = itemView.findViewById(R.id.cardAvatar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //recyclerClickAvatarsInterface.onItemClick(getAdapterPosition());
                    recyclerClickAvatarsInterface.onItemClick(getAbsoluteAdapterPosition());
                    /*pos=getAdapterPosition();
                    posicionMarcada=pos;
                    notifyDataSetChanged();

                    if (posicionMarcada==pos){
                        barra.setBackgroundResource(R.color.teal_200);

                    }/*else (posicionMarcada != pos){
                        barra.setBackgroundResource(R.color.white);
                        //holder.barra.setBackgroundColor(view.getResources().getColor(R.color.white));
                    }
                    notifyDataSetChanged();*/


                }



            });


        }
    }
}
