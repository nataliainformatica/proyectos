package com.example.examenrecu2;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DAO {
    private SQLiteDatabase db;
    private Context context;

    public DAO(Context context, SQLiteDatabase db) {
        this.context = context;
        this.db = db;
    }

    public long insertar(String nombre, String apellido, String calle,String pathMediano, String pathPequeno) {
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellido", apellido);
        values.put("calle", calle);
        values.put("pathMediano", pathMediano);
        values.put("pathPequeno", pathPequeno);


        return db.insert("participante", null, values);
    }

    // Otros métodos para realizar consultas, actualizaciones, eliminaciones, etc.
    public ArrayList<Persona> get() {
        ArrayList<Persona> objetos = new ArrayList<>();

        String[] columnas = {"_id", "nombre", "apellido","calle","pathMediano", "pathPequeno"};
        Cursor cursor = db.query("participante", columnas, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String apellido = cursor.getString(2);
            String calle = cursor.getString(3);
            String pathMediano = cursor.getString(4);
            String pathPequeno = cursor.getString(5);
//    public Persona(String nombre, String apellido, String calle, String pathMediano, String pathPequeño) {
            Persona objeto = new Persona(id, nombre, apellido, calle, pathMediano, pathPequeno);
            objetos.add(objeto);
        }

        cursor.close();

        return objetos;
    }

    public void cerrarConexion() {
        db.close();
    }
}
