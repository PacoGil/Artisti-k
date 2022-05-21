package com.example.artisti_k;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class CompraActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    Spinner spinner1;
    Button back, buy;
    TextView precioTextView;
    String preciobd, idEv, artista, lugar, fecha;
    Double precioDigitos;
    Integer selected;
    CompraEntradas compra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        spinner1 = findViewById(R.id.spinner);
        back = findViewById(R.id.volver);
        buy = findViewById(R.id.compra);
        precioTextView = findViewById(R.id.price);


        //TODO:: ANDRES Aqui recoges el put extra de los datos del artista de Eventos y haces la query
        Bundle extras = getIntent().getExtras();
        String eventoId = extras.getSerializable("eventoId").toString();

        //Obtenemos de la tabla Eventos de Firebase el precio de las entradas de cada concierto que seleccionamos
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference
            .child("evento")
            .orderByChild("id")
            .equalTo(eventoId)
            .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        preciobd = snapshot.child("entrada").getValue().toString();
                        idEv = snapshot.child("id").getValue().toString();
                        artista = snapshot.child("artista").getValue().toString();
                        lugar = snapshot.child("lugar").getValue().toString();
                        fecha = snapshot.child("fecha").getValue().toString();
                        precioTextView.setText(preciobd);


                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

        //Creamos la funcionalidad del Spinner
        String [] opciones = {"1","2","3","4","5","6","7","8"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_compra,opciones);
        spinner1.setAdapter(adapter);

        //Según las entradas elegidas, se obtendrá el precio multiplicándose el precio original por la cantidad elegida.
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected = i + 1;
                if (selected <= 1) {
                    precioTextView.setText(preciobd);
                } else {
                precioDigitos = Double.parseDouble(preciobd);
                precioDigitos = precioDigitos * selected;
                precioTextView.setText(Double.toString(precioDigitos));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //Botón para retroceder a la pantalla anterior
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        //Botón para ir a la siguiente pantalla y realizar el pago
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compra = new CompraEntradas(idEv, artista, lugar, fecha, selected.toString(), precioTextView.getText().toString());
                Intent intent = new Intent(CompraActivity.this, DatosEntrada.class);
                intent.putExtra("compraEvento", compra);
                startActivity(intent);
            }
        });
    }
}