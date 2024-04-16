package com.example.repaso1.model;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.repaso1.Cocktail;
import com.example.repaso1.R;

public class IngredientsActivity extends AppCompatActivity {
    ListView simpleListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ingredients);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        simpleListView = findViewById(R.id.simpleListView);
            Intent intent = getIntent();
            if (intent != null && intent.hasExtra("cocktail")) {
                Cocktail cocktail = (Cocktail) intent.getSerializableExtra("cocktail");
                // Ahora puedes usar el objeto cocktail según sea necesario
                if (cocktail != null) {
                    // con los datos recibidos creamos la interfaz
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                            R.layout.item_ingredients, R.id.itemTextView, cocktail.getIngredients());
                    simpleListView.setAdapter(arrayAdapter);
                }
            }


            return insets;
        });
    }



}