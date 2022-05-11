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

public class AlejandroActivity extends AppCompatActivity {


    Button entradasAl, entradasAl2, entradasAl3, entradasAl4;
    float precioEntradaA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alejandro);


        entradasAl = findViewById(R.id.entradasA);
        entradasAl2 = findViewById(R.id.entradasA2);
        entradasAl3 = findViewById(R.id.entradasA3);
        entradasAl4 = findViewById(R.id.entradasA4);
        precioEntradaA = 105F;



        entradasAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlejandroActivity.this, CompraActivity.class);
                startActivity(intent);
            }
        });

        entradasAl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlejandroActivity.this, CompraActivity.class);
                startActivity(intent);
            }
        });

        entradasAl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlejandroActivity.this, CompraActivity.class);
                startActivity(intent);
            }
        });

        entradasAl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlejandroActivity.this, CompraActivity.class);
                startActivity(intent);
            }
        });



    }

}