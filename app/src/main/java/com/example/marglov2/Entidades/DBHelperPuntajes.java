package com.example.marglov2.Entidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperPuntajes extends SQLiteOpenHelper {

    public static final String BDNAME = "PuntajesActividades.db"; //nombre de la base de datos

    public DBHelperPuntajes(Context context) {
        super(context, BDNAME, null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table puntajes_table(fecha TEXT primary key, actividad TEXT, subhabilidad TEXT, puntaje TEXT, informacion TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists puntajes_table");
    }

    //funcion para guardar los puntajes
    public Boolean insertPuntaje(String fecha, int actividad, String subhabilidad, int puntaje, String informacion){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fecha", fecha);
        contentValues.put("actividad", actividad);
        contentValues.put("subhabilidad", subhabilidad);
        contentValues.put("puntaje", puntaje);
        contentValues.put("informacion", informacion);


        long result = MyDB.insert("puntajes_table",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean updateData(String fecha, int actividad, String subhabilidad, int puntaje, String informacion){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fecha", fecha);
        contentValues.put("actividad", actividad);
        contentValues.put("subhabilidad", subhabilidad);
        contentValues.put("puntaje", puntaje);
        contentValues.put("informacion", informacion);
        Cursor cursor = DB.rawQuery("Select * from puntajes_table where fecha = ?", new String[]{fecha});
        if (cursor.getCount() > 0) {
            long result = DB.update("puntajes_table", contentValues, "fecha=?", new String[]{fecha});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}


    public Cursor getdataPuntajes ()
    {

              SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from puntajes_table", null);
        return cursor;

    }


}//final


