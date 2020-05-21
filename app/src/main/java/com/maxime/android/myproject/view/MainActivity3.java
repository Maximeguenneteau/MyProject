package com.maxime.android.myproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.maxime.android.myproject.R;
import com.maxime.android.myproject.controler.Singleton;
import com.maxime.android.myproject.model.Ability;


public class MainActivity3 extends AppCompatActivity {
    private TextView details2;
    private TextView Url2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_adapter_3);
        details2 = findViewById(R.id.textView5);
        Url2 = findViewById(R.id.textView6);
        Intent intent = getIntent();
        String abilityJson = intent.getStringExtra("AbilityKey");
        Ability ability = Singleton.getGson().fromJson(abilityJson, Ability.class);
        show(ability);
    }

    private void show(Ability ability) {
        details2.setText(ability.getName());
        Url2.setText(ability.getUrl());
    }
}
