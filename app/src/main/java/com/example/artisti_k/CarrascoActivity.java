package com.example.artisti_k;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CarrascoActivity extends AppCompatActivity {

    Button entradasM, entradasM1, entradasM2,baseDatos;
    float precioEntradaM;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrasco);

        entradasM = findViewById(R.id.entradasM);
        entradasM1 = findViewById(R.id.entradasM2);
        entradasM2 = findViewById(R.id.entradasM3);
        precioEntradaM = 55F;

        entradasM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarrascoActivity.this, CompraActivity.class);
                startActivity(intent);
            }
        });
        entradasM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarrascoActivity.this, CompraActivity.class);
                startActivity(intent);
            }
        });
        entradasM2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarrascoActivity.this, CompraActivity.class);
                startActivity(intent);
            }
        });

    }
}