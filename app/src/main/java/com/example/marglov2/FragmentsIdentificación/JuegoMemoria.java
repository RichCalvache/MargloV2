package com.example.marglov2.FragmentsIdentificación;


public class JuegoMemoria {
    private String mAnimal_name;
    private int mAnimal_slika;
    private int mSound;


    public JuegoMemoria(String animalName, int slika, int sound) {
        this.setmAnimal_name(animalName);
        this.setmAnimal_slika(slika);
        this.setmSound(sound);
    }

    public String getmAnimal_name() {
        return mAnimal_name;
    }

    public void setmAnimal_name(String mAnimal_name) {
        this.mAnimal_name = mAnimal_name;
    }

    public int getmAnimal_slika() {
        return mAnimal_slika;
    }

    public void setmAnimal_slika(int mAnimal_slika) {
        this.mAnimal_slika = mAnimal_slika;
    }

    public int getmSound() {
        return mSound;
    }

    public void setmSound(int mSound) {
        this.mSound = mSound;
    }

    /*
    //putting data
    public static ArrayList<JuegoMemoria> createZivotinjeList() {
        ArrayList<JuegoMemoria> animals = new ArrayList<JuegoMemoria>();

        animals.add(new JuegoMemoria("Sheep", R.drawable.domestic_sheep, R.raw.gun));
        animals.add(new JuegoMemoria("Sparrow", R.drawable.birds_sparrow, R.raw.birds_sparrow));
        animals.add(new JuegoMemoria("Dolphin", R.drawable.sea_dolphin, R.raw.dolphin));
        animals.add(new JuegoMemoria("Bumblebee", R.drawable.insects_bumblebee, R.raw.bumblebee));
        animals.add(new JuegoMemoria("Tractor", R.drawable.cars_tractor, R.raw.tractor));
        animals.add(new JuegoMemoria("Baby Cry", R.drawable.laugh_baby_cry, R.raw.baby_cray));
        animals.add(new JuegoMemoria("Wind", R.drawable.nature_wind, R.raw.wind));
        animals.add(new JuegoMemoria("Golf", R.drawable.effects_golf, R.raw.golf));
        animals.add(new JuegoMemoria("Clock", R.drawable.others_clock, R.raw.clock));


        return animals;
    }
     */
}
