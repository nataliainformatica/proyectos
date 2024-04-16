package com.example.repaso1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Servicio {
    @GET("filter.php?c=Cocktail")
    Call<RespuestaCocktail> getCocktails();

    @GET("lookup.php")
    Call<CocktailDetails> getDetails(@Query("i") String cocktailId);
    @GET("random.php")
    Call<CocktailDetails> getRandom();


}
