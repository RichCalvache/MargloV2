package com.example.marglov2.Entidades;

import android.widget.ImageView;

public class CartasVo2 {

    private int id;
    private int logo;
    private int imagenId;
    private int sonidoId;
    private String nombre;
    private ImageView imageView;
    //private TextView barra;

    protected boolean isFlipped = false;
    protected boolean isMatched = false;

    protected boolean isNulled = false;

    private boolean isSelected = false;


    public CartasVo2(int id, int logo, int imagenId, int sonidoId, String nombre) {
        this.id = id;
        this.logo = logo;
        this.imagenId = imagenId;
        this.sonidoId = sonidoId;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }

    public int getSonidoId() {
        return sonidoId;
    }

    public void setSonidoId(int sonidoId) {
        this.sonidoId = sonidoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    //metodo cartas voltear
    public boolean getIsMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public boolean getIsNulled() {
        return isNulled;
    }

    public void setNulled(boolean nulled) {
        isNulled = nulled;
    }



    public void flip ()
    {
        if(isMatched)
            return;

        if(isFlipped)
        {
            //imageView.setBackgroundResource(getLogo());
            isFlipped = false;
        }
        else
        {
            //setBackground(front);
            //77setImagenId(getImagenId());
            //imageView.setBackgroundResource(getImagenId());
            isFlipped = true;
        }

    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public boolean isSelected() {
        return isSelected;
    }




}