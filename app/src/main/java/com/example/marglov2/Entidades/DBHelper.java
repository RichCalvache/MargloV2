package com.example.marglov2.Entidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String BDNAME = "RegistroUsuario.db"; //nombre de la base de datos

    public DBHelper(Context context) {
        super(context, BDNAME, null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table datos_usuario(nombre TEXT primary key, apellidos TEXT, edad TEXT, genero TEXT, direccion TEXT, ciudad TEXT, celular TEXT, avatar TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists datos_usuario");
    }

    public Boolean insertData(String nombre, String apellidos, String edad, String genero, String direccion,String ciudad, String celular, int avatar){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("apellidos", apellidos);
        contentValues.put("edad", edad);
        contentValues.put("genero", genero);
        contentValues.put("direccion", direccion);
        contentValues.put("ciudad", ciudad);
        contentValues.put("celular", celular);
        contentValues.put("avatar", avatar);
        long result = MyDB.insert("datos_usuario",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean updateData(String nombre, String apellidos, String edad, String genero, String direccion,String ciudad, String celular, int avatar){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("direccion", direccion);
        contentValues.put("ciudad", ciudad);
        contentValues.put("celular", celular);
        contentValues.put("avatar", avatar);
        Cursor cursor = DB.rawQuery("Select * from datos_usuario where nombre = ?", new String[]{nombre});
        if (cursor.getCount() > 0) {
            long result = DB.update("datos_usuario", contentValues, "nombre=?", new String[]{nombre});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}

/*
    //funcion para ver si el usuario ya esta registrado o inicio sesion
    public Boolean checkLogin(String nombre){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from datos_usuario where nombre = ?", new String[]{nombre} );
        if (cursor.getCount()>0)//si existe retorna valor mayor a cero o sea true
            return true;
        else
            return false;
    }*/
    /*
    //funcion para ver si el usuario existe o no
    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username} );
        if (cursor.getCount()>0)//si existe retorna valor mayor a cero o sea true
            return true;
        else
            return false;
    }

    //funcion para comprobar usuario y password (ambos)
    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password =?", new String[]{username,password} );
        if (cursor.getCount()>0)//si existe retorna valor mayor acero
            return true;
        else
            return false;
    }*/


    public Cursor getdata ()
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from datos_usuario", null);
        return cursor;

    }


}//final

