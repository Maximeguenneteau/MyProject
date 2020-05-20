package com.maxime.android.myproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.maxime.android.myproject.R;
import com.maxime.android.myproject.data.Api;
import com.maxime.android.myproject.model.Pokemon;
import com.maxime.android.myproject.model.ResponseRest;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private static final String BASE_URL = "https://pokeapi.co/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("Application", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        List<Pokemon> pokemonList = getDataFromCache();
        if(getDataFromCache() !=null){
            showList(pokemonList);
        }else{}
        Apicall();
    }

    private List<Pokemon> getDataFromCache() {
       String jsonPokemon = sharedPreferences.getString("jsonPokemonList", null);
       if(jsonPokemon == null){
           return null;
       } else {
           Type listType = new TypeToken<List<Pokemon>>() {
           }.getType();
           return gson.fromJson(jsonPokemon, listType);
       }
    }

    private void showList(List<Pokemon> pokemonList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(pokemonList);
        recyclerView.setAdapter(mAdapter);
    }


    private void Apicall(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            Api API = retrofit.create(Api.class);

            Call<ResponseRest> call = API.getPokemonResponse();
            call.enqueue(new Callback<ResponseRest>() {
                @Override
                public void onResponse(Call<ResponseRest> call, Response<ResponseRest> response) {
                    if(response.isSuccessful() && response.body() !=null) {
                        List<Pokemon> pokemonList = response.body().getResults();
                        Toast.makeText(getApplicationContext(),"Api Success",Toast.LENGTH_SHORT).show();
                        savedList(pokemonList);
                        showList(pokemonList);
                    } else {
                        showError();
                    }
                }



                @Override
                public void onFailure(Call<ResponseRest> call, Throwable t) {
                    showError();

                }
            });
            }

    private void savedList(List<Pokemon> pokemonList) {
        String jsonString = gson.toJson(pokemonList);
        sharedPreferences
                .edit()
                .putString("jsonPokemonList", jsonString)
                .apply();
        Toast.makeText(this,"List Saved", Toast.LENGTH_SHORT).show();
    }

    private void showError() {
        Toast.makeText(this,"Api Error", Toast.LENGTH_SHORT).show();
    }


}



