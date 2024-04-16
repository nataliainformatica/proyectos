package com.example.repaso1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.repaso1.model.IngredientsActivity;
import com.example.repaso1.model.InstructionsActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Adaptor adapter;
    private Servicio cocktailAPI;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        ArrayList<Cocktail> cocktails;

        cocktails = new ArrayList<Cocktail>();

        listView = findViewById(R.id.lv);
        adapter = new Adaptor(this, cocktails);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cocktailAPI = retrofit.create(Servicio.class);
        Call<RespuestaCocktail> call = cocktailAPI.getCocktails();
        call.enqueue(new Callback<RespuestaCocktail>() {
            @Override
            public void onResponse(Call<RespuestaCocktail> call, Response<RespuestaCocktail> response) {
                if (response.isSuccessful()) {
                    List<Cocktail> cocktails = response.body().getCocktails();
                    adapter.addAll(cocktails);
                }
            }

            @Override
            public void onFailure(Call<RespuestaCocktail> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        // Handle item selection.
        if (item.getItemId() == R.id.End) {
            finishAffinity();
            return true;
        } else if (item.getItemId() == R.id.Surprise) {
            //TODO abrir activity API RANDOM

            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Intent intent = null;

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        // posición del elemento pulsado
        int i = info.position;
        Cocktail cocktail = (Cocktail) adapter.getItem(i);
        //
        // Llamada a la API para conseguir el objeto
        /**************
         *
         */
        Call<CocktailDetails> call = cocktailAPI.getDetails(cocktail.getId());
        call.enqueue(new Callback<CocktailDetails>() {
            @Override
            public void onResponse(Call<CocktailDetails> call, Response<CocktailDetails> response) {
                if (response.isSuccessful()) {
                    Cocktail cocktail = response.body().getDrinks().get(0);
                    // abrir activity de ingredientsActivity y pasarle Cocktail
                    Intent intent = new Intent(context, IngredientsActivity.class);
                    intent.putExtra("cocktail", cocktail);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<CocktailDetails> call, Throwable t) {
                // Manejar errores de red o de Retrofit
            }
        });
        if (item.getItemId() == R.id.ingredients) {
            intent = new Intent(this, IngredientsActivity.class);
            return true;
        } else if (item.getItemId() == R.id.instructions) {
            intent = new Intent(this, InstructionsActivity.class);
            return true;
        } else

            return super.onContextItemSelected(item);


    }
}


