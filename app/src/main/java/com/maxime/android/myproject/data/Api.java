package com.maxime.android.myproject.data;

import com.maxime.android.myproject.model.ResponseRest;
import com.maxime.android.myproject.model.ResponseRest2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("/api/v2/pokemon")
    Call<ResponseRest> getPokemonResponse();


    @GET("/api/v2/ability")
    Call<ResponseRest2> getAbilityResponse();
}
