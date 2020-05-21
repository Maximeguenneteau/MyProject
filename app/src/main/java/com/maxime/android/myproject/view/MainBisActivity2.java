package com.maxime.android.myproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maxime.android.myproject.R;
import com.maxime.android.myproject.controler.SecondController;
import com.maxime.android.myproject.controler.Singleton;
import com.maxime.android.myproject.model.Ability;

import java.util.List;

public class MainBisActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter2 mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SecondController controller;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new SecondController(
                this,
                Singleton.getGson(),
                Singleton.getSharedPreferencesInstance(getApplicationContext())
        );
        controller.onStart();
    }
    public void showList(List<Ability> abilityList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter2(abilityList, new MyAdapter2.OnItemClickListener(){
            @Override
            public void onItemClick(Ability item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void showError() {
        Toast.makeText(this,"Api Error", Toast.LENGTH_SHORT).show();
    }


    public void navigateToDetails(Ability ability) {
        Intent myIntent = new Intent(this, MainActivity2.class );
        myIntent.putExtra("AbilityKey", Singleton.getGson().toJson(ability));
        MainBisActivity2.this.startActivity(myIntent);
    }
}