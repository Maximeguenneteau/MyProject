package com.maxime.android.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
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
    private static final String BASE_URL = "https://pokeapi.co/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showList();
        Apicall();
    }

    private void showList() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }
        mAdapter = new MyAdapter(input);
        recyclerView.setAdapter(mAdapter);
    }


    private void Apicall(){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

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

                private void showError() {
                    Toast.makeText(this,"Api Error", Toast.LENGTH_SHORT).show();
                }


        }



