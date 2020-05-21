package com.maxime.android.myproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.maxime.android.myproject.R;
import com.maxime.android.myproject.controler.Singleton;
import com.maxime.android.myproject.model.Pokemon;

public class MainActivity2 extends AppCompatActivity {
    private TextView details;
    private TextView Url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_adapter2);
        details = findViewById(R.id.textView2);
        Url = findViewById(R.id.textView);
        Intent intent = getIntent();
        String pokemonJson = intent.getStringExtra("PokemonKey");
        Pokemon pokemon = Singleton.getGson().fromJson(pokemonJson, Pokemon.class);
        show(pokemon);
    }

    private void show(Pokemon pokemon) {
        details.setText(pokemon.getName());
        Url.setText(pokemon.getUrl());
    }
}


