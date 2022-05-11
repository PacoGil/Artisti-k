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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CompraActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    Spinner spinner1;
    Button back, buy;
    TextView precioTextView;
    String preciobd;
    Double precioDigitos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        spinner1 = findViewById(R.id.spinner);
        back = findViewById(R.id.volver);
        buy = findViewById(R.id.compra);
        precioTextView = (TextView)findViewById(R.id.price);

        Bundle extras = getIntent().getExtras();
        String eventoId = extras.getSerializable("eventoId").toString();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference
            .child("evento")
            .orderByChild("artista")
            .equalTo("Bunbury")
            .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        preciobd = snapshot.child("entrada").getValue().toString();
                        String id = snapshot.child("id").getValue().toString();
                        precioTextView.setText(preciobd);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });


        String [] opciones = {"1","2","3","4","5","6","7","8"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_compra,opciones);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Integer selected = i + 1;

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