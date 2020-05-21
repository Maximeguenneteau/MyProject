package com.maxime.android.myproject.controler;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maxime.android.myproject.model.Ability;
import com.maxime.android.myproject.model.ResponseRest2;
import com.maxime.android.myproject.view.MainBisActivity2;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondController {
    public static final String BASE_URL = "https://pokeapi.co/";
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainBisActivity2 view;


    public SecondController(MainBisActivity2 mainBisActivity2, Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainBisActivity2;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }


    public void onStart() {
        List<Ability> abilityList = getDataFromCache();
        if (getDataFromCache() != null) {
            view.showList(abilityList);
        } else {
            Apicall();
        }
    }

    private void Apicall() {
        Call<ResponseRest2> call = Singleton.getApi().getAbilityResponse();
        call.enqueue(new Callback<ResponseRest2>() {
            @Override
            public void onResponse(Call<ResponseRest2> call, Response<ResponseRest2> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Ability> abilityList = response.body().getResults();
                    savedList(abilityList);
                    view.showList(abilityList);
                } else {
                    view.showError();
                }
            }


            @Override
            public void onFailure(Call<ResponseRest2> call, Throwable t) {
                view.showError();

            }
        });
    }

    private void savedList(List<Ability> abilityList) {
        String jsonString = gson.toJson(abilityList);
        sharedPreferences
                .edit()
                .putString("jsonAbilityList", jsonString)
                .apply();
    }

    private List<Ability> getDataFromCache() {
        String jsonAbility = sharedPreferences.getString("jsonAbilityList", null);
        if (jsonAbility == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<Ability>>() {
            }.getType();
            return gson.fromJson(jsonAbility, listType);
        }
    }


    public void onItemClick(Ability ability) {
        view.navigateToDetails(ability);
    }
}

