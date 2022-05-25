package com.example.artisti_k;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton settings;
    Button fechasB, fechasM, fechasAl, fechasQu, fechasG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fechasB = findViewById(R.id.fechasBunbury);
        fechasM = findViewById(R.id.fechasManuelCarrasco);
        fechasAl = findViewById(R.id.fechasAlejandro);
        fechasQu = findViewById(R.id.fechasQueen);
        fechasG = findViewById(R.id.fechasGuns);
        settings = findViewById(R.id.settingsButton);

        fechasB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, BunburyActivity.class);
                startActivity(intent);
            }
        });

        fechasM.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, CarrascoActivity.class);
                startActivity(intent);
            }
        });

        fechasAl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AlejandroActivity.class);
                startActivity(intent);
            }
        });

        fechasQu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, QueenActivity.class);
                startActivity(intent);
            }
        });

        fechasG.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, GunsActivity.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}