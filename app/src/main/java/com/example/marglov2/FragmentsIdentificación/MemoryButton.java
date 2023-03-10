package com.example.marglov2.FragmentsIdentificación;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.GridLayout;

import com.example.marglov2.R;

public class MemoryButton extends androidx.appcompat.widget.AppCompatButton {

    protected int row;
    protected int column;
    protected int frontDrawableId;
    protected int sonidoId;

    protected boolean isFlipped = false;
    protected boolean isMatched = false;

    protected Drawable front;
    protected Drawable back;


    public MemoryButton(Context context, int r, int c, int frontImageDrawableId, int soundId)
    {
        super(context);


        row =r;
        column = c;
        frontDrawableId = frontImageDrawableId;
        sonidoId= soundId;

        //front = AppCompatDrawableManager.get().getDrawable(context,frontImageDrawableId);
        front = context.getDrawable(frontImageDrawableId);
        back = context.getDrawable(R.drawable.logofinal9);

        setBackground(back);

        GridLayout.LayoutParams tempParams = new GridLayout.LayoutParams(GridLayout.spec(r),GridLayout.spec(c));



        tempParams.width = (int) getResources().getDisplayMetrics().density*100;
        tempParams.height = (int) getResources().getDisplayMetrics().density*100;

        setLayoutParams(tempParams);

    }

    public boolean getIsMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public int getFrontDrawableId() {
        return frontDrawableId;
    }

    public int getSoundId() {
        return sonidoId;
    }

    public void flip ()
    {
        if(isMatched)
            return;

        if(isFlipped)
        {
            setBackground(back);
            isFlipped = false;
        }
        else
        {
            setBackground(front);
            isFlipped = true;
        }

    }
}
