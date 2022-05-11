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

public class QueenActivity extends AppCompatActivity {

    Button entradasQu, entradasQu2, entradasQu3, entradasQu4,baseDatos;
    float precioEntradaQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queen);

        entradasQu = findViewById(R.id.entradasQ);
        entradasQu2 = findViewById(R.id.entradasQ2);
        entradasQu3 = findViewById(R.id.entradasQ3);
        entradasQu4 = findViewById(R.id.entradasQ4);
        precioEntradaQ = 67F;

        entradasQu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QueenActivity.this, CompraActivity.class);
                startActivity(intent);

            }
        });
        entradasQu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QueenActivity.this, CompraActivity.class);
                startActivity(intent);
            }
        });

        entradasQu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QueenActivity.this, CompraActivity.class);
                startActivity(intent);
            }
        });
        entradasQu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QueenActivity.this, CompraActivity.class);
                startActivity(intent);
            }
        });
    }
}