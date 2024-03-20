package com.example.comicconretrofitjava;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * https://xkcd.com/
 * API  - A webcomic of romance,
 * sarcasm, math, and language.
 */
public interface XkcdService {
/*

https://xkcd.com/79/info.0.json
79_ es el identificador del cómic
 */
    @GET("{idComic}/info.0.json")
    Call<Comic> getComic(@Path("idComic") int idComic);

}
