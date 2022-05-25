package com.example.artisti_k;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinalActivity extends AppCompatActivity {

    Button ajustesUsuario, menuPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        ajustesUsuario = findViewById(R.id.botonVerCompra);
        menuPrincipal = findViewById(R.id.buttonMenuPrincipal);

        ajustesUsuario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FinalActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        menuPrincipal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FinalActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}