package com.example.comicconretrofitjava;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.comicconretrofitjava.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        EdgeToEdge.enable(this);
        setContentView(view);
        binding.indeterminateBar.setVisibility(View.GONE);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://xkcd.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        XkcdService service = retrofit.create(XkcdService.class);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.indeterminateBar.setVisibility(View.VISIBLE);

                Call<Comic> call = service.getComic(new Random().nextInt(1000));

                call.enqueue(new Callback<Comic>() {
                    @Override
                    public void onResponse(Call<Comic> call, Response<Comic> response) {
                    Comic comic = response.body();
                    try {
                        if (comic != null) {
                            binding.textView2.setText(comic.getTitle());
                            // Muestra el ProgressBar
                            binding.indeterminateBar.setVisibility(View.VISIBLE);
                            // Carga la idmagen usando Picasso
                            Picasso.get().load(comic.getImg()).placeholder(R.drawable.opps)
                                    .error(R.drawable.opps).into(binding.imageView);
                            binding.indeterminateBar.setVisibility(View.GONE);
                        }
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this,"Hemos tenido un error", Toast.LENGTH_SHORT).show();
                    }
                    }

                    @Override
                    public void onFailure(Call<Comic> call, Throwable throwable) {
                        //
                        Toast.makeText(MainActivity.this,"Hemos tenido un error", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }
}