package com.example.examenrecu2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLiteOpenHelper proporciona métodos para obtener instancias de SQLiteDatabase
 * La recomendación común es tener una única instancia de  MiBDHelper que se comparta
 * en toda la aplicación para garantizar una gestión coherente de la bbdd.
 * Ésto puede ser más eficiente que crear una nueva instancia de SQLiteOpenHelper cada vez que se requiera y puede lograrse
 * mediante un diseño Singleton.
 */
public class MiBDHelper extends SQLiteOpenHelper {
    private static final String NOMBRE_BD = "miBaseDeDatos.db";
    private static final int VERSION_BD = 7;

    private static MiBDHelper instancia;
    private static SQLiteDatabase baseDeDatos;
    private static final String CREAR_TABLA_PARTICIPANTE =
            "CREATE TABLE participante (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, calle TEXT, pathMediano TEXT, pathPequeno TEXT);";
////    public Persona(String nombre, String apellido, String calle, String pathMediano, String pathPequeño) {
    private MiBDHelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    public static synchronized MiBDHelper obtenerInstancia(Context context) {
        if (instancia == null) {
            instancia = new MiBDHelper(context.getApplicationContext());
        }
        return instancia;
    }
    public static SQLiteDatabase obtenerBaseDeDatos(Context context) {
        if (baseDeDatos == null || !baseDeDatos.isOpen()) {
            instancia = obtenerInstancia(context);
            baseDeDatos = instancia.getWritableDatabase();
        }
        return baseDeDatos;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_PARTICIPANTE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Código para actualizar la base de datos cuando el número de versión cambia
        db.execSQL("DROP TABLE IF EXISTS participante;");
        onCreate(db);
    }
}