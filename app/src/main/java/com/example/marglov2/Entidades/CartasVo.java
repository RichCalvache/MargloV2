package com.example.marglov2.Entidades;


import android.widget.ImageView;

public class CartasVo {

    private int id;
    private int logo;
    private int imagenId;
    private int sonidoId;
    private int imagenAciertoId;
    private ImageView imageView;
    //private TextView barra;

    protected boolean isFlipped = false;
    protected boolean isMatched = false;

    protected boolean isNulled = false;

    private boolean isSelected = false;


    public CartasVo(int id, int logo, int imagenId, int sonidoId, int imagenAciertoId) {
        this.id = id;
        this.logo = logo;
        this.imagenId = imagenId;
        this.sonidoId = sonidoId;
        this.imagenAciertoId = imagenAciertoId;
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

    public int getImagenAciertoId() {
        return imagenAciertoId;
    }

    public void setImagenAciertoId(int imagenAciertoId) {
        this.imagenAciertoId = imagenAciertoId;
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