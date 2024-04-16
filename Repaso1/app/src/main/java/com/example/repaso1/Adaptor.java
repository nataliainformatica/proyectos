package com.example.repaso1;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adaptor extends BaseAdapter {
    private Context context;
    private List<Cocktail> cocktails;

    public Adaptor(Context context, List<Cocktail> cocktails) {
        this.context = context;
        this.cocktails = cocktails;
    }


    @Override
    public int getCount() {
        return cocktails.size();
    }

    @Override
    public Object getItem(int position) {
        return cocktails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        }

        Cocktail cocktail = cocktails.get(position);

        TextView nameTextView = convertView.findViewById(R.id.nombreCocktail);
        ImageView imageView = convertView.findViewById(R.id.imagenCocktail);

        nameTextView.setText(cocktail.getName());

        //Picasso.get()
        //    .load("URL_de_tu_imagen_o_ruta_local")
        //    .resize(200, 200) // Especifica el tamaño deseado
        //    .centerCrop() // O utiliza .fit() si quieres que la imagen se ajuste dentro del tamaño sin cortar
        //    .into(imageView); // imageView es el ImageView donde deseas mostrar la imagen
        Picasso.get().load(cocktail.getImageUrl()).into(imageView);

        return convertView;
    }

    // metodo personalizado para agregar una lista de cócteles al adaptador
    public void addAll(List<Cocktail> newCocktails) {
        cocktails.addAll(newCocktails);
        notifyDataSetChanged();
    }

}
