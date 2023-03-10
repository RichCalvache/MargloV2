
package com.example.marglov2.Adaptadores;


import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marglov2.Entidades.CartasVo;
import com.example.marglov2.Entidades.DBHelperPuntajes;
import com.example.marglov2.FragmentsIdentificación.Actividad_ident_1_juegoFragment;
import com.example.marglov2.Interfaces.RecyclerClickJuegoInterface;
import com.example.marglov2.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class AdaptadorCartas extends RecyclerView.Adapter<AdaptadorCartas.ViewHolderCartas> {

    //MediaPlayer sonido;

    private RecyclerClickJuegoInterface recyclerClickJuegoInterface;

    ArrayList<CartasVo> listaCartas, listaSeleccion;

    ArrayList<ViewHolderCartas> myViewHolders = new ArrayList<>();


    RecyclerView recyclerViewJuego;

    private CartasVo cartaSeleccionada1,cartaSeleccionada2;


    //View view;
    int posicionMarcada = -1;
    int pos = 0;
    int posicion_anterior=1;
    int cont=0;
    int puntos =0;

    DBHelperPuntajes DB;

    private Context context;

    int moradoCartas = Color.parseColor("#B6A8ED");//ContextCompat.getColor(context, R.color.massagecolor1);
    int blancoCartas = Color.parseColor("#FFFFFFFF");
    //int moradoCartas = context.getResources().getColor(R.color.kisspngspeechpantone3, Resources.Theme.);
    //int blancoCartas = context.getResources().getColor(R.color.white);//ContextCompat.getColor(Activity as Context ,R.color.massagecolor1);


    private boolean isBusy= false;



    public AdaptadorCartas(ArrayList<CartasVo> listaCartas, Actividad_ident_1_juegoFragment recyclerClickJuegoInterface) {
        this.listaCartas = listaCartas;
        this.recyclerClickJuegoInterface = (RecyclerClickJuegoInterface) recyclerClickJuegoInterface;
    }



    @Override
    public ViewHolderCartas onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_cartas,null,false);
        //Se agrega esto para que al rotar ocupe toda la pantalla
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);

        // funcion para controlar la navegacion hacia otras pantallas
        //final NavController navControllermio = Navigation.findNavController(view); //nacControllermio es el nombre de la instancia
        DB = new DBHelperPuntajes(parent.getContext());
        return new ViewHolderCartas(view);


    }

    @Override
    public void onBindViewHolder(ViewHolderCartas holder, int position) {

        myViewHolders.add(position,holder);//Arraylist de viewholders, importante para manejar las views...




        //holder.imagen.setImageResource(listaCartas.get(position).getLogo());
/*
        RecyclerView.ViewHolder rv_view = recyclerViewJuego.findViewHolderForAdapterPosition(position);

        CardView cardviewCartaSeleccionada = rv_view.itemView.findViewById(R.id.cardCarta);

 */

        final int pos=position;
        final CartasVo carta = listaCartas.get(position);

//        final CartasVo2 carta = listaCartas.get(position);

        //holder.itemView.setBackgroundColor(carta.isSelected() ? Color.CYAN : Color.WHITE);
        //holder.itemView.set(carta.isSelected() ? Color.CYAN : Color.WHITE);
        //voltea la carta si esta seleccionada o no
        holder.imagen.setImageResource(carta.isSelected() ? listaCartas.get(position).getImagenId() : listaCartas.get(position).getLogo());
        holder.cardviewCarta.setCardBackgroundColor(carta.isSelected() ? blancoCartas : moradoCartas);
        holder.barra_abajo.setBackgroundResource(carta.isSelected() ? R.color.teal_200 : R.color.therapycolor2);
        holder.barra_abajo.setBackgroundResource(carta.isSelected() ? R.color.teal_200 : R.color.therapycolor2);
        holder.barra_arriba.setBackgroundResource(carta.isSelected() ? R.color.teal_200 : R.color.therapycolor2);
        holder.barra_izquierda.setBackgroundResource(carta.isSelected() ? R.color.teal_200 : R.color.therapycolor2);
        holder.barra_derecha.setBackgroundResource(carta.isSelected() ? R.color.teal_200 : R.color.therapycolor2);

        //holder.barra_arriba.setBackgroundResource(R.color.white);
        // holder.barra_izquierda.setBackgroundResource(R.color.white);
        // holder.barra_derecha.setBackgroundResource(R.color.white);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerClickJuegoInterface.onItemClick(position);
                carta.setSelected(!carta.isSelected());
                holder.imagen.setImageResource(carta.isSelected() ? listaCartas.get(position).getImagenId() : listaCartas.get(position).getLogo());
                holder.cardviewCarta.setCardBackgroundColor(carta.isSelected() ? blancoCartas : moradoCartas);

                holder.barra_abajo.setBackgroundResource(carta.isSelected() ? R.color.teal_200 : R.color.therapycolor2);
                holder.barra_arriba.setBackgroundResource(carta.isSelected() ? R.color.teal_200 : R.color.therapycolor2);
                holder.barra_izquierda.setBackgroundResource(carta.isSelected() ? R.color.teal_200 : R.color.therapycolor2);
                holder.barra_derecha.setBackgroundResource(carta.isSelected() ? R.color.teal_200 : R.color.therapycolor2);

                posicionMarcada=pos;

                // funcion para controlar la navegacion hacia otras pantallas
                final NavController navControllermio = Navigation.findNavController(view); //nacControllermio es el nombre de la instancia



                //listaCartas.get(position).setSelected(!listaCartas.get(position).isSelected());


                ///holder.imagen.setImageResource(listaCartas.get(position).isSelected() ? listaCartas.get(position).getImagenId() : listaCartas.get(position).getLogo());
                //notifyDataSetChanged();
                //notifyItemChanged(position);

                //posicion_anterior =position;


/*
                if (posicion_anterior == position) {
                    // Unselect currently selected w/c means no selection.
                    //posicionMarcada = -1;
                    cartaSeleccionada1.setSelected(!cartaSeleccionada1.isSelected());

                    cartaSeleccionada1 = null;

                } else {
                    posicionMarcada = position;
                }

 */
                //notifyDataSetChanged();
                //notifyItemChanged(position);


                if (isBusy) {
                    //cont=0;
                    return;
                }

                if(listaCartas.get(position).getIsMatched()==true) {
                    return;
                }

                if (cartaSeleccionada1==null){
                    cartaSeleccionada1 = listaCartas.get(position);//pasa el boton seleccionado
                    cartaSeleccionada1.flip();
                    cartaSeleccionada1.setSelected(!cartaSeleccionada1.isSelected());

                    posicion_anterior = position;
                    //posicion_anterior = posicionMarcada;

                    //holder.imagen.setImageResource(cartaSeleccionada1.isSelected() ? listaCartas.get(position).getImagenId() : listaCartas.get(position).getLogo());
                    return;
                }



              /*  if(cartaSeleccionada1.getId() == listaCartas.get(position).getId()){//revisa si selecciona el mismo dos veces
                    //cartaSeleccionada1=null;
                    //return;
                    //listaCartas.get(position).setSelected(!listaCartas.get(position).isSelected());
                    //return;

                    cartaSeleccionada2=listaCartas.get(position);
                    cartaSeleccionada2.flip();
                    cartaSeleccionada2.setSelected(!cartaSeleccionada2.isSelected());


                    //holder.imagen.setImageResource(cartaSeleccionada2.isSelected() ? cartaSeleccionada2.getImagenId() : cartaSeleccionada2.getLogo());

                    //metodo para hacer un clickeables las otras cartas
                    for(int k=0; k< myViewHolders.size();k++){
                        myViewHolders.get(k).itemView.setClickable(false);

                    }


                    isBusy=true;

                    final Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            cartaSeleccionada2.flip();
                            cartaSeleccionada1.flip();

                            //cartaSeleccionada1.setSelected(!cartaSeleccionada1.isSelected());
                            //cartaSeleccionada2.setSelected(!cartaSeleccionada2.isSelected());

                            myViewHolders.get(posicion_anterior).imagen.setImageResource(cartaSeleccionada1.isSelected() ? listaCartas.get(posicion_anterior).getImagenId() : listaCartas.get(posicion_anterior).getLogo());
                            holder.imagen.setImageResource(cartaSeleccionada2.isSelected() ? listaCartas.get(position).getImagenId() : listaCartas.get(position).getLogo());

                            holder.barra_abajo.setBackgroundResource(carta.isSelected() ? R.color.teal_200 : R.color.white);
                            holder.barra_arriba.setBackgroundResource(carta.isSelected() ? R.color.teal_200 : R.color.white);
                            holder.barra_izquierda.setBackgroundResource(carta.isSelected() ? R.color.teal_200 : R.color.white);
                            holder.barra_derecha.setBackgroundResource(carta.isSelected() ? R.color.teal_200 : R.color.white);

                            myViewHolders.get(posicion_anterior).barra_abajo.setBackgroundResource(cartaSeleccionada1.isSelected() ? R.color.teal_200 : R.color.white);
                            myViewHolders.get(posicion_anterior).barra_arriba.setBackgroundResource(cartaSeleccionada1.isSelected() ? R.color.teal_200 : R.color.white);
                            myViewHolders.get(posicion_anterior).barra_izquierda.setBackgroundResource(cartaSeleccionada1.isSelected() ? R.color.teal_200 : R.color.white);
                            myViewHolders.get(posicion_anterior).barra_derecha.setBackgroundResource(cartaSeleccionada1.isSelected() ? R.color.teal_200 : R.color.white);


                            //hol.setClickable(false);

                            //metodo para hacer un clickeables las otras cartas
                            for(int k=0; k< myViewHolders.size();k++){
                                myViewHolders.get(k).itemView.setClickable(true);

                            }


                            // holder.imagen.setImageResource(listaCartas.get(position).getLogo());
                            //holder.imagen.setImageResource(listaCartas.get(posicion_anterior).getLogo());


                            //holder.barra_abajo.setBackgroundResource(R.color.white);
                            //holder.barra_arriba.setBackgroundResource(R.color.white);
                            // holder.barra_izquierda.setBackgroundResource(R.color.white);
                            // holder.barra_derecha.setBackgroundResource(R.color.white);


                            cartaSeleccionada2 = null;
                            cartaSeleccionada1 = null;

                            //notifyItemChanged(position);
                            //notifyItemChanged(posicion_anterior);

                            //holder.imagen.setBackgroundResource(listaCartas.get(position).getLogo());

                            isBusy=false;
                            cont=0;
                            //notifyItemChanged(position);
                            //notifyItemChanged(posicion_anterior);

                            //recyclerViewJuego.setClickable(true);




                        }
                    },500);

                }
*/

                if(cartaSeleccionada1.getImagenAciertoId() == listaCartas.get(position).getImagenAciertoId()){//si el 1ero tiene el mismo imagenId del segundo seleccionado entonces suma punto o hace match

                    //REvisa si elegio la misma carta
                    if(cartaSeleccionada1.getId() == listaCartas.get(position).getId()){//revisa si selecciona el mismo dos veces
                        //cartaSeleccionada1=null;
                        //return;
                        //listaCartas.get(position).setSelected(!listaCartas.get(position).isSelected());
                        //return;

                        cartaSeleccionada2=listaCartas.get(position);
                        cartaSeleccionada2.flip();
                        cartaSeleccionada2.setSelected(!cartaSeleccionada2.isSelected());



                        //holder.imagen.setImageResource(cartaSeleccionada2.isSelected() ? cartaSeleccionada2.getImagenId() : cartaSeleccionada2.getLogo());

                        //metodo para hacer un clickeables las otras cartas
                        for(int k=0; k< myViewHolders.size();k++){
                            myViewHolders.get(k).itemView.setClickable(false);

                        }


                        isBusy=true;

                        final Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                cartaSeleccionada2.flip();
                                cartaSeleccionada1.flip();

                                //cartaSeleccionada1.setSelected(!cartaSeleccionada1.isSelected());
                                //cartaSeleccionada2.setSelected(!cartaSeleccionada2.isSelected());

                                myViewHolders.get(posicion_anterior).imagen.setImageResource(cartaSeleccionada1.isSelected() ? listaCartas.get(posicion_anterior).getImagenId() : listaCartas.get(posicion_anterior).getLogo());
                                holder.imagen.setImageResource(cartaSeleccionada2.isSelected() ? listaCartas.get(position).getImagenId() : listaCartas.get(position).getLogo());

                                holder.cardviewCarta.setCardBackgroundColor(carta.isSelected() ? blancoCartas : moradoCartas);
                                holder.barra_abajo.setBackgroundResource(carta.isSelected() ? R.color.teal_200 : R.color.therapycolor2);
                                holder.barra_arriba.setBackgroundResource(carta.isSelected() ? R.color.teal_200 : R.color.therapycolor2);
                                holder.barra_izquierda.setBackgroundResource(carta.isSelected() ? R.color.teal_200 : R.color.therapycolor2);
                                holder.barra_derecha.setBackgroundResource(carta.isSelected() ? R.color.teal_200 : R.color.therapycolor2);

                                myViewHolders.get(posicion_anterior).cardviewCarta.setCardBackgroundColor(carta.isSelected() ? blancoCartas : moradoCartas);
                                myViewHolders.get(posicion_anterior).barra_abajo.setBackgroundResource(cartaSeleccionada1.isSelected() ? R.color.teal_200 : R.color.therapycolor2);
                                myViewHolders.get(posicion_anterior).barra_arriba.setBackgroundResource(cartaSeleccionada1.isSelected() ? R.color.teal_200 : R.color.therapycolor2);
                                myViewHolders.get(posicion_anterior).barra_izquierda.setBackgroundResource(cartaSeleccionada1.isSelected() ? R.color.teal_200 : R.color.therapycolor2);
                                myViewHolders.get(posicion_anterior).barra_derecha.setBackgroundResource(cartaSeleccionada1.isSelected() ? R.color.teal_200 : R.color.therapycolor2);


                                //hol.setClickable(false);

                                //metodo para hacer un clickeables las otras cartas
                                for(int k=0; k< myViewHolders.size();k++){
                                    myViewHolders.get(k).itemView.setClickable(true);

                                }


                                // holder.imagen.setImageResource(listaCartas.get(position).getLogo());
                                //holder.imagen.setImageResource(listaCartas.get(posicion_anterior).getLogo());


                                //holder.barra_abajo.setBackgroundResource(R.color.white);
                                //holder.barra_arriba.setBackgroundResource(R.color.white);
                                // holder.barra_izquierda.setBackgroundResource(R.color.white);
                                // holder.barra_derecha.setBackgroundResource(R.color.white);


                                cartaSeleccionada2 = null;
                                cartaSeleccionada1 = null;

                                //notifyItemChanged(position);
                                //notifyItemChanged(posicion_anterior);

                                //holder.imagen.setBackgroundResource(listaCartas.get(position).getLogo());

                                isBusy=false;
                                //cont=0;
                                //notifyItemChanged(position);
                                //notifyItemChanged(posicion_anterior);

                                //recyclerViewJuego.setClickable(true);




                            }
                        },500);

                    }else {
                        //ACIERTA


                        listaCartas.get(position).flip();
                        listaCartas.get(position).setSelected(!listaCartas.get(position).isSelected());


                        //holder.imagen.setImageResource(listaCartas.get(position).isSelected() ? listaCartas.get(position).getImagenId() : listaCartas.get(position).getLogo());
                        //myViewHolders.get(posicion_anterior).imagen.setImageResource(cartaSeleccionada1.isSelected() ? listaCartas.get(posicion_anterior).getImagenId() : listaCartas.get(posicion_anterior).getLogo());
                        //RecyclerView.ViewHolder rv_view = recyclerViewJuego.findViewHolderForLayoutPosition(holder.getBindingAdapterPosition());

                        //holder.itemView.setClickable(false);
                        //myViewHolders.get(posicion_anterior).itemView.setVisibility(View.INVISIBLE);

                        for (int k = 0; k < myViewHolders.size(); k++) {
                            myViewHolders.get(k).itemView.setClickable(false);

                        }


                        final Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {


                                for (int k = 0; k < myViewHolders.size(); k++) {
                                    myViewHolders.get(k).itemView.setClickable(true);

                                }

                                //ocultar las cartas despues de ganar
                                //holder.itemView.setVisibility(View.INVISIBLE);
                                //myViewHolders.get(posicion_anterior).itemView.setVisibility(View.INVISIBLE);




                                //dejar las cartas volteadas, sin click, y poner imagenAcierto
                                holder.imagen.setImageResource(listaCartas.get(position).getImagenAciertoId());
                                myViewHolders.get(posicion_anterior).imagen.setImageResource(listaCartas.get(posicion_anterior).getImagenAciertoId());

                                //holder.imagen.setImageResource(carta.isSelected() ? listaCartas.get(position).getImagenId() : listaCartas.get(position).getLogo());

                                //para quitar las barras despues de acertar
                                holder.cardviewCarta.setCardBackgroundColor(blancoCartas);
                                holder.barra_abajo.setBackgroundResource(R.color.white);
                                holder.barra_arriba.setBackgroundResource(R.color.white);
                                holder.barra_izquierda.setBackgroundResource(R.color.white);
                                holder.barra_derecha.setBackgroundResource(R.color.white);

                                myViewHolders.get(posicion_anterior).cardviewCarta.setCardBackgroundColor(blancoCartas);
                                myViewHolders.get(posicion_anterior).barra_abajo.setBackgroundResource(R.color.white);
                                myViewHolders.get(posicion_anterior).barra_arriba.setBackgroundResource(R.color.white);
                                myViewHolders.get(posicion_anterior).barra_izquierda.setBackgroundResource(R.color.white);
                                myViewHolders.get(posicion_anterior).barra_derecha.setBackgroundResource(R.color.white);

                                myViewHolders.get(posicion_anterior).itemView.setEnabled(false);
                                holder.itemView.setEnabled(false);

                                puntos = puntos+2;

                                if(puntos>=12){

                                    //guardar puntaje en BD
                                    guardarPuntajeBD();

                                    //Guardar puntaje en sharedpreferences
                                    //guardarPreferenciasPuntaje();

                                    navControllermio.navigate(R.id.action_actividad_ident_1_juegoFragment_to_actividad_ident_1_felicitacionFragment);


                                }

                                //holder.cardviewCarta.setVisibility(listaCartas.get(position).isSelected() ? View.INVISIBLE : View.VISIBLE);

                            }
                        }, 2000);


                        listaCartas.get(position).setMatched(true);
                        cartaSeleccionada1.setMatched(true);
                        //holder.cardviewCarta.setVisibility(View.INVISIBLE);

                        cartaSeleccionada1 = null;

                        return;
                    }
                }
                else {
                    cartaSeleccionada2=listaCartas.get(position);
                    cartaSeleccionada2.flip();
                    cartaSeleccionada2.setSelected(!cartaSeleccionada2.isSelected());


                    //holder.imagen.setImageResource(cartaSeleccionada2.isSelected() ? cartaSeleccionada2.getImagenId() : cartaSeleccionada2.getLogo());

                    //metodo para hacer un clickeables las otras cartas
                    for(int k=0; k< myViewHolders.size();k++){
                        myViewHolders.get(k).itemView.setClickable(false);

                    }


                    isBusy=true;

                    final Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            cartaSeleccionada2.flip();
                            cartaSeleccionada1.flip();

                            //cartaSeleccionada1.setSelected(!cartaSeleccionada1.isSelected());
                            //cartaSeleccionada2.setSelected(!cartaSeleccionada2.isSelected());

                            myViewHolders.get(posicion_anterior).imagen.setImageResource(cartaSeleccionada1.isSelected() ? listaCartas.get(posicion_anterior).getImagenId() : listaCartas.get(posicion_anterior).getLogo());
                            holder.imagen.setImageResource(cartaSeleccionada2.isSelected() ? listaCartas.get(position).getImagenId() : listaCartas.get(position).getLogo());


                            holder.cardviewCarta.setCardBackgroundColor(cartaSeleccionada1.isSelected() ? blancoCartas : moradoCartas);
                            holder.barra_abajo.setBackgroundResource(cartaSeleccionada2.isSelected() ? R.color.teal_200 : R.color.therapycolor2);
                            holder.barra_arriba.setBackgroundResource(cartaSeleccionada2.isSelected() ? R.color.teal_200 : R.color.therapycolor2);
                            holder.barra_izquierda.setBackgroundResource(cartaSeleccionada2.isSelected() ? R.color.teal_200 : R.color.therapycolor2);
                            holder.barra_derecha.setBackgroundResource(cartaSeleccionada2.isSelected() ? R.color.teal_200 : R.color.therapycolor2);

                            myViewHolders.get(posicion_anterior).cardviewCarta.setCardBackgroundColor(cartaSeleccionada1.isSelected() ? blancoCartas : moradoCartas);
                            myViewHolders.get(posicion_anterior).barra_abajo.setBackgroundResource(cartaSeleccionada1.isSelected() ? R.color.teal_200 : R.color.therapycolor2);
                            myViewHolders.get(posicion_anterior).barra_arriba.setBackgroundResource(cartaSeleccionada1.isSelected() ? R.color.teal_200 : R.color.therapycolor2);
                            myViewHolders.get(posicion_anterior).barra_izquierda.setBackgroundResource(cartaSeleccionada1.isSelected() ? R.color.teal_200 : R.color.therapycolor2);
                            myViewHolders.get(posicion_anterior).barra_derecha.setBackgroundResource(cartaSeleccionada1.isSelected() ? R.color.teal_200 : R.color.therapycolor2);


                            //hol.setClickable(false);

                            //metodo para hacer un clickeables las otras cartas
                            for(int k=0; k< myViewHolders.size();k++){
                                myViewHolders.get(k).itemView.setClickable(true);

                            }


                            // holder.imagen.setImageResource(listaCartas.get(position).getLogo());
                            //holder.imagen.setImageResource(listaCartas.get(posicion_anterior).getLogo());


                            //holder.barra_abajo.setBackgroundResource(R.color.white);
                            //holder.barra_arriba.setBackgroundResource(R.color.white);
                            // holder.barra_izquierda.setBackgroundResource(R.color.white);
                            // holder.barra_derecha.setBackgroundResource(R.color.white);


                            cartaSeleccionada2 = null;
                            cartaSeleccionada1 = null;

                            //notifyItemChanged(position);
                            //notifyItemChanged(posicion_anterior);

                            //holder.imagen.setBackgroundResource(listaCartas.get(position).getLogo());

                            isBusy=false;


                            //cont=0;
                            //notifyItemChanged(position);
                            //notifyItemChanged(posicion_anterior);

                            //recyclerViewJuego.setClickable(true);




                        }
                    },500);

                }




            }
        });





/*
    if (posicionMarcada==position) {
            holder.imagen.setImageResource(listaCartas.get(position).getImagenId());


            holder.barra_abajo.setBackgroundResource(R.color.teal_200);
            holder.barra_arriba.setBackgroundResource(R.color.teal_200);
            holder.barra_izquierda.setBackgroundResource(R.color.teal_200);
            holder.barra_derecha.setBackgroundResource(R.color.teal_200);
            //para voltear las imagenes, primero se debe definir que cargue el logo y no la imagen
            // holder.imagen.setImageResource(listaCartas.get(position).getImagenId());

        }else{
            holder.imagen.setImageResource(listaCartas.get(position).getLogo());


            holder.barra_abajo.setBackgroundResource(R.color.white);
            holder.barra_arriba.setBackgroundResource(R.color.white);
            holder.barra_izquierda.setBackgroundResource(R.color.white);
            holder.barra_derecha.setBackgroundResource(R.color.white);
            //holder.barra.setBackgroundColor(view.getResources().getColor(R.color.white));
        }
*/
    }

    @Override
    public int getItemCount() {
        //return listaCartas.size();
        return listaCartas == null ? 0 : listaCartas.size();
    }

    public class ViewHolderCartas extends RecyclerView.ViewHolder {

        TextView barra_abajo,barra_arriba,barra_derecha,barra_izquierda;
        ImageView imagen;
        CardView cardviewCarta,cardviewCartaSeleccionada;
        RecyclerView recyclerViewJuego;

        //RecyclerView.ViewHolder rv_view = recyclerViewJuego.findViewHolderForAdapterPosition(getAbsoluteAdapterPosition());



        public ViewHolderCartas(View itemView) {
            super(itemView);

            //this.context = context;


            //RecyclerView.ViewHolder rv_view = recyclerViewJuego.findViewHolderForAdapterPosition(2);


            //RecyclerView.ViewHolder rv_view = recyclerViewJuego.findViewHolderForAdapterPosition(getBindingAdapterPosition());

            barra_abajo = (TextView) itemView.findViewById(R.id.barraSeleccionId);
            barra_arriba = (TextView) itemView.findViewById(R.id.barraSeleccionArribaId);

            barra_derecha = (TextView) itemView.findViewById(R.id.barraSeleccionDerechaId);
            barra_izquierda = (TextView) itemView.findViewById(R.id.barraSeleccionIzquierdaId);

            //RecyclerView.ViewHolder rv_view = recyclerViewJuego.findViewHolderForAdapterPosition(getLayoutPosition());

            //view  = recyclerViewJuego.findViewHolderForAdapterPosition(pos).itemView;

            imagen = (ImageView) itemView.findViewById(R.id.idImagen);

            cardviewCarta = itemView.findViewById(R.id.cardCarta);

            recyclerViewJuego = itemView.findViewById(R.id.recyclerJuegoId);

            //View rv_view = recyclerViewJuego.findViewHolderForLayoutPosition(getLayoutPosition()).itemView;
            //RecyclerView.ViewHolder rv_view = recyclerViewJuego.findViewHolderForAdapterPosition(getLayoutPosition());
            //cardviewCartaSeleccionada = rv_view.itemView.findViewById(R.id.cardCarta);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerClickJuegoInterface.onItemClick(getAbsoluteAdapterPosition());
                    notifyDataSetChanged();



                    //final CartasVo2 carta = listaCartas.get(getAbsoluteAdapterPosition());

                    //recyclerClickAvatarsInterface.onItemClick(getAdapterPosition());
                    //recyclerClickJuegoInterface.onItemClick(getAbsoluteAdapterPosition());

                    // RecyclerView.ViewHolder rv_view = recyclerViewJuego.findViewHolderForAdapterPosition(getAbsoluteAdapterPosition());

                    //cardviewCartaSeleccionada = rv_view.itemView.findViewById(R.id.cardCarta);

                    //carta.setSelected(!carta.isSelected());
                    //imagen.setImageResource(carta.isSelected() ? listaCartas.get(getAbsoluteAdapterPosition()).getImagenId() : listaCartas.get(getAbsoluteAdapterPosition()).getLogo());


                    //posicionMarcada=getAbsoluteAdapterPosition();

                    //listaCartas.get(getAbsoluteAdapterPosition()).setSelected(!listaCartas.get(getAbsoluteAdapterPosition()).isSelected());


                    //imagen.setImageResource(listaCartas.get(getAbsoluteAdapterPosition()).isSelected() ? listaCartas.get(getAbsoluteAdapterPosition()).getImagenId() : listaCartas.get(getAbsoluteAdapterPosition()).getLogo());
                    //notifyDataSetChanged();
                    //  notifyItemChanged(position);

                    //posicion_anterior =position;

/*
                if (posicionMarcada == position) {
                    // Unselect currently selected w/c means no selection.
                    posicionMarcada = -1;
                } else {
                    posicionMarcada = position;
                }
                //notifyDataSetChanged();
                notifyItemChanged(position);
*/

/*
                    if (isBusy) {
                        //cont=0;
                        return;
                    }

                    if(listaCartas.get(getAbsoluteAdapterPosition()).getIsMatched()==true) {
                        return;
                    }

                    if (cartaSeleccionada1==null){
                        cartaSeleccionada1 = listaCartas.get(getAbsoluteAdapterPosition());//pasa el boton seleccionado
                        cartaSeleccionada1.flip();
                        cartaSeleccionada1.setSelected(!cartaSeleccionada1.isSelected());
                        //posicion_anterior = holder.getOldPosition();
                        posicion_anterior = posicionMarcada;

                        imagen.setImageResource(cartaSeleccionada1.isSelected() ? listaCartas.get(getAbsoluteAdapterPosition()).getImagenId() : listaCartas.get(getAbsoluteAdapterPosition()).getLogo());





                        return;
                    }

                    if(cartaSeleccionada1.getId() == listaCartas.get(getAbsoluteAdapterPosition()).getId()){//revisa si selecciona el mismo dos veces

                        return;
                    }


                    if(cartaSeleccionada1.getImagenId() == listaCartas.get(getAbsoluteAdapterPosition()).getImagenId()){//si el 1ero tiene el mismo imagenId del segundo seleccionado entonces suma punto o hace match
                        listaCartas.get(getAbsoluteAdapterPosition()).flip();

                        listaCartas.get(getAbsoluteAdapterPosition()).setSelected(!listaCartas.get(getAbsoluteAdapterPosition()).isSelected());


                        imagen.setImageResource(listaCartas.get(getAbsoluteAdapterPosition()).isSelected() ? listaCartas.get(getAbsoluteAdapterPosition()).getImagenId() : listaCartas.get(getAbsoluteAdapterPosition()).getLogo());


                        final Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //holder.imagen.setImageResource(R.drawable.check);
                                cardviewCarta.setVisibility(View.INVISIBLE);
                                cardviewCarta.setClickable(false);

                                //recyclerViewJuego.findViewHolderForAdapterPosition(pos).itemView;
                                //RecyclerView.ViewHolder rv_view = recyclerViewJuego.findViewHolderForAdapterPosition(posicion_anterior);

                                //CardView cardviewCartaSeleccionada = rv_view.itemView.findViewById(R.id.cardCarta);

                                RecyclerView.ViewHolder rv_view = recyclerViewJuego.findViewHolderForAdapterPosition(getLayoutPosition());
                                cardviewCartaSeleccionada = rv_view.itemView.findViewById(R.id.cardCarta);

                                View viewItem = recyclerViewJuego.getLayoutManager().findViewByPosition(getLayoutPosition());
                                View icon = viewItem.findViewById(R.id.view);

                                cardviewCartaSeleccionada.setVisibility(View.INVISIBLE);




                                cardviewCarta.setVisibility(listaCartas.get(getAbsoluteAdapterPosition()).isSelected() ? View.INVISIBLE : View.VISIBLE);

                            }
                        },2000);



                        listaCartas.get(getAbsoluteAdapterPosition()).setMatched(true);
                        cartaSeleccionada1.setMatched(true);
                        //holder.cardviewCarta.setVisibility(View.INVISIBLE);

                        cartaSeleccionada1 = null;

                        return;
                    }
                    else {
                        cartaSeleccionada2=listaCartas.get(getAbsoluteAdapterPosition());
                        cartaSeleccionada2.flip();
                        cartaSeleccionada2.setSelected(!cartaSeleccionada2.isSelected());


                        imagen.setImageResource(cartaSeleccionada2.isSelected() ? cartaSeleccionada2.getImagenId() : cartaSeleccionada2.getLogo());


                        isBusy=true;

                        final Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                cartaSeleccionada2.flip();
                                cartaSeleccionada1.flip();

                                cartaSeleccionada1.setSelected(!cartaSeleccionada1.isSelected());
                                cartaSeleccionada2.setSelected(!cartaSeleccionada2.isSelected());

                                imagen.setImageResource(cartaSeleccionada1.isSelected() ? cartaSeleccionada1.getImagenId() : cartaSeleccionada1.getLogo());
                                imagen.setImageResource(cartaSeleccionada2.isSelected() ? cartaSeleccionada2.getImagenId() : cartaSeleccionada2.getLogo());



                                // holder.imagen.setImageResource(listaCartas.get(position).getLogo());
                                //holder.imagen.setImageResource(listaCartas.get(posicion_anterior).getLogo());


                                //holder.barra_abajo.setBackgroundResource(R.color.white);
                                //holder.barra_arriba.setBackgroundResource(R.color.white);
                                // holder.barra_izquierda.setBackgroundResource(R.color.white);
                                // holder.barra_derecha.setBackgroundResource(R.color.white);


                                cartaSeleccionada2 = null;
                                cartaSeleccionada1 = null;

                                //notifyItemChanged(position);
                                //notifyItemChanged(posicion_anterior);

                                //holder.imagen.setBackgroundResource(listaCartas.get(position).getLogo());

                                isBusy=false;
                                cont=0;
                                notifyItemChanged(getAbsoluteAdapterPosition());
                                notifyItemChanged(posicion_anterior);




                            }
                        },500);

                    }
*/

                }



            });



        }



    }


    //Shared PReferences para puntajes
    private void guardarPuntajeBD(){
        //guardar puntaje en BD
        int actividadTXT = 1;
        String subhabilidadTXT = "Identificación";
        int puntajeTXT = puntos;
        String infoTXT= "Nada que agregar";//Informacion sobre la actividad (Ayudas)


        String fechaTXT;
        Calendar calendar;
        SimpleDateFormat simpleDateFormat;

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aaa");
        fechaTXT = simpleDateFormat.format(calendar.getTime()).toString();

        //insertar datos a la bd
        Boolean checkinsertpuntaje = DB.insertPuntaje(fechaTXT, actividadTXT,subhabilidadTXT,puntajeTXT, infoTXT);
        if (checkinsertpuntaje == true) {
            //Toast.makeText(getContext(), "Puntaje guardado", Toast.LENGTH_SHORT).show();
        }else {
            //Toast.makeText(getContext(), "Falló registro de puntaje!", Toast.LENGTH_SHORT).show();
        }

    }

}
