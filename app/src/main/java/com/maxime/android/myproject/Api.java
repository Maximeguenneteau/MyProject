package com.maxime.android.myproject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("/api/v2/pokemon")
    Call<ResponseRest> getPokemonResponse();

}
