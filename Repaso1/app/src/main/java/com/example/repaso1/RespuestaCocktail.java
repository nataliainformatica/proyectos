package com.example.repaso1;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespuestaCocktail {
    @SerializedName("drinks")
    private List<Cocktail> cocktails;

    public List<Cocktail> getCocktails() {
        return cocktails;
    }


}
