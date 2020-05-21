package com.maxime.android.myproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maxime.android.myproject.R;
import com.maxime.android.myproject.controler.MainController;
import com.maxime.android.myproject.controler.Singleton;
import com.maxime.android.myproject.model.Pokemon;

import java.util.List;

public class MainBisActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(
                this,
                Singleton.getGson(),
                Singleton.getSharedPreferencesInstance(getApplicationContext())
        );
        controller.onStart();
    }
    public void showList(List<Pokemon> pokemonList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(pokemonList, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pokemon item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void showError() {
        Toast.makeText(this,"Api Error", Toast.LENGTH_SHORT).show();
    }


    public void navigateToDetails(Pokemon pokemon) {
        Intent myIntent = new Intent(MainBisActivity.this, MainActivity2.class );
        myIntent.putExtra("PokemonKey", Singleton.getGson().toJson(pokemon));
        MainBisActivity.this.startActivity(myIntent);
    }
}




