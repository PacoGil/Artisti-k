package com.example.artisti_k;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BunburyActivity extends AppCompatActivity {

    TextView fechaB1, lugarB1, horaB1, artistaB1;
    TextView fechaB2, lugarB2, horaB2, artistaB2;

    DatabaseReference databaseReference;
    Button entradas, entradas2, entradas3, entradas4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunbury);

        fechaB1 = findViewById(R.id.fechaMes1);
        lugarB1 = findViewById(R.id.lugar1);
        horaB1 = findViewById(R.id.hora1);
        artistaB1 = findViewById(R.id.artista);
        fechaB2 = findViewById(R.id.fechaMes2);
        lugarB2 = findViewById(R.id.lugar2);
        horaB2 = findViewById(R.id.hora2);
        artistaB2 = findViewById(R.id.artista2);
        entradas = findViewById(R.id.entradas1);
        entradas2 = findViewById(R.id.entradas2);
        entradas3 = findViewById(R.id.entrada3);
        entradas4 = findViewById(R.id.entradas4);
        databaseReference= FirebaseDatabase.getInstance().getReference();


        /*databaseReference.child("evento").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {





            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        entradas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(BunburyActivity.this, DatosEntrada.class);
                String lugar = lugarB1.getText().toString();
                String fecha = fechaB1.getText().toString();
                String artista = artistaB1.getText().toString();
                b.putExtra("LugarB1", lugar);
                b.putExtra("FechaB1", fecha);
                b.putExtra("ArtistaB1",artista);
                startActivity(b);
            }
        });
        entradas2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BunburyActivity.this, DatosEntrada.class);
                String lugar = lugarB2.getText().toString();
                String fecha = fechaB2.getText().toString();
                String artista = artistaB2.getText().toString();
                i.putExtra("LugarB2", lugar);
                i.putExtra("FechaB2", fecha);
                i.putExtra("ArtistaB2",artista);
                startActivity(i);
            }
        });
        entradas3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BunburyActivity.this, CompraActivity.class);
                startActivity(intent);
            }
        });
        entradas4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BunburyActivity.this, CompraActivity.class);
                startActivity(intent);
            }
        });

    }

}