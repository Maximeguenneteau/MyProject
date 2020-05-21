package com.maxime.android.myproject.data;

import com.maxime.android.myproject.model.ResponseRest;
import com.maxime.android.myproject.model.ResponseRest2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("Maximeguenneteau/MyProject/master/pokemon.json")
    Call<ResponseRest> getPokemonResponse();


    @GET("Maximeguenneteau/MyProject/master/ability.json")
    Call<ResponseRest2> getAbilityResponse();
}
