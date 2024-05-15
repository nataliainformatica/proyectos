package com.example.examenrecu2;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FaseUnoActivity extends AppCompatActivity {
    private final Context contexto = this;
    private TextView textViewNombre, textViewApellido;
    private ImageButton buttonAvanzar, buttonRetroceder;
    private ImageView imagen;
    private ArrayList<Persona> personas;
    private static final String miURL = "https://raw.githubusercontent.com/nataliVbike/eclipse/master/file";
    private static int miPosicion = 0;
    private SQLiteDatabase baseDeDatos;
    private DAO participantesDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseDeDatos = MiBDHelper.obtenerBaseDeDatos(this);
        participantesDAO = new DAO(this, baseDeDatos);
        textViewApellido = findViewById(R.id.textViewApellido);
        textViewNombre = findViewById(R.id.textViewNombre);
        buttonAvanzar = findViewById(R.id.imageViewFlechaDerecha);
        buttonRetroceder = findViewById(R.id.imageViewFlechaIzquierda);
        imagen = findViewById(R.id.imageView);
        personas = participantesDAO.get();
        if (personas.isEmpty()) {
            llamadaVolley();
        } else cargarPersona();

        buttonAvanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miPosicion++;
                if (miPosicion >=personas.size()) {
                    miPosicion = 0;
                }
                cargarPersona();
            }
        });
        buttonRetroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miPosicion--;
                if (miPosicion < 0) {
                    miPosicion = personas.size() - 1;
                }
                cargarPersona();
            }
        });
    }

    private void llamadaVolley() {
        personas = new ArrayList<>();
        RequestQueue reques = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, miURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        String nombre, apellido, calle, pathMediano, pathPequeño;

                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject name = jsonArray.getJSONObject(i).getJSONObject("name");
                                nombre = name.getString("first");
                                Log.d("Personas", nombre);
                                apellido = name.getString("last");

                                JSONObject street = jsonArray.getJSONObject(i).getJSONObject("location").getJSONObject("street");
                                calle = street.getString("name") + " " + street.getString("number");
                                JSONObject picture = jsonArray.getJSONObject(i).getJSONObject("picture");
                                pathMediano = picture.getString("medium");
                                pathPequeño = picture.getString("thumbnail");
                                Persona persona = new Persona(nombre, apellido, calle, pathMediano, pathPequeño);
                                personas.add(persona);

                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        cargarPersona();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error", volleyError.getMessage());
            }
        });
        reques.add(jsonArrayRequest);
    }

    public void cargarPersona() {
        textViewNombre.setText(personas.get(miPosicion).getNombre());
        textViewApellido.setText(personas.get(miPosicion).getApellido());
        Picasso.get().load(personas.get(miPosicion).getPathMediano()).fit().into(imagen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mimenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection.
        if (item.getItemId() == R.id.copiaSeguridad) {
            Log.d("", "");
            hacerCopia();
            return true;
        } else if (item.getItemId() == R.id.fase2T) {
            Log.d("", "");
            return true;
        } else
            return super.onOptionsItemSelected(item);

    }

    private void hacerCopia() {

        // recorrer personas  e insertar todos sus elementos en la BBDD
        for (Persona p : personas) {
            participantesDAO.insertar(p.getNombre(), p.getApellido(), p.getCalle(), p.getPathMediano(), p.getPathPequeno());
        }
        ;


    }

    @Override
    protected void onPause() {
        SharedPreferences sp = this.getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("miPosicion", miPosicion);
        super.onPause();
    }
}