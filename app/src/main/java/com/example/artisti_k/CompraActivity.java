package com.example.artisti_k;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class CompraActivity extends AppCompatActivity {



    Spinner spinner1;
    Button back, buy;
    TextView precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        spinner1 = findViewById(R.id.spinner);
        back = findViewById(R.id.volver);
        buy = findViewById(R.id.compra);
        precio = findViewById(R.id.price);


        String [] opciones = {"1","2","3","4","5","6","7","8"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_compra,opciones);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompraActivity.this, DatosEntrada.class);
                startActivity(intent);
            }
        });
    }
}