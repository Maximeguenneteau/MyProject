package com.maxime.android.myproject.controler;


import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maxime.android.myproject.model.Pokemon;
import com.maxime.android.myproject.model.ResponseRest;
import com.maxime.android.myproject.view.MainBisActivity;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {
    public static final String BASE_URL = "https://pokeapi.co/";
    public static final String BASE_URL2 = "https://raw.githubusercontent.com/";
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainBisActivity view;


    public MainController(MainBisActivity mainBisActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view= mainBisActivity;
        this.gson=gson;
        this.sharedPreferences=sharedPreferences;
    }

    public void onStart() {
        List<Pokemon> pokemonList = getDataFromCache();
        if (getDataFromCache() != null) {
            view.showList(pokemonList);
        } else {
            Apicall();
        }
    }

        private void Apicall(){
            Call<ResponseRest> call = Singleton.getApi().getPokemonResponse();
            call.enqueue(new Callback<ResponseRest>() {
                @Override
                public void onResponse(Call<ResponseRest> call, Response<ResponseRest> response) {
                    if(response.isSuccessful() && response.body() !=null) {
                        List<Pokemon> pokemonList = response.body().getResults();
                        savedList(pokemonList);
                        view.showList(pokemonList);
                    } else {
                        view.showError();
                    }
                }



                @Override
                public void onFailure(Call<ResponseRest> call, Throwable t) {
                    view.showError();

                }
            });
        }

        private void savedList(List<Pokemon> pokemonList) {
            String jsonString = gson.toJson(pokemonList);
            sharedPreferences
                    .edit()
                    .putString("jsonPokemonList", jsonString)
                    .apply();
        }

        private List<Pokemon> getDataFromCache(){
            String jsonPokemon = sharedPreferences.getString("jsonPokemonList", null);
            if(jsonPokemon == null){
                return null;
            } else {
                Type listType = new TypeToken<List<Pokemon>>() {
                }.getType();
                return gson.fromJson(jsonPokemon, listType);
            }
        }

     public void onItemClick(Pokemon pokemon){
        view.navigateToDetails(pokemon);
     }

     public void onButtonClick(){
}

}




