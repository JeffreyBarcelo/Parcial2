package com.example.formativa_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BaseDatos extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "user.db";
    public static final String TABLE_NAME = "user_registrado";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "ESTRATO";
    public static final String COL_4 = "SALARIO";
    public static final String COL_5 = "NIVEL";

    public BaseDatos(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY ,NOMBRE TEXT, ESTRATO TEXT, SALARIO TEXT, NIVEL TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertarDato(String id, String nombre, String estrato, String salario, String nivel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, nombre);
        contentValues.put(COL_3, estrato);
        contentValues.put(COL_4, salario);
        contentValues.put(COL_5, nivel);
        Long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;

        } else {
            return true;
        }
    }

    public Cursor obtenerDatos() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    public boolean actualizarDatos(String id, String nombre, String estrato, String salario, String nivel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, nombre);
        contentValues.put(COL_3, estrato);
        contentValues.put(COL_4, salario);
        contentValues.put(COL_5, nivel);
        db.update(TABLE_NAME, contentValues, "id = ?", new String[]{id});
        return true;
    }

    public Cursor buscar( String identificacion) {

        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM REGISTROS WHERE ID='" + identificacion + "'", null);
        return c;

    }
    public Integer eliminarDatos (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }


}