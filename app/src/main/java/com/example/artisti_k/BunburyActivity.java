package com.example.artisti_k;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BunburyActivity extends AppCompatActivity {

    TextView fechaB1, lugarB1, horaB1, artistaB1;
    TextView fechaB2, lugarB2, horaB2, artistaB2;

    DatabaseReference databaseReference;
    Button entradas, entradas2, entradas3, entradas4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunbury);

/*        fechaB1 = findViewById(R.id.fechaMes1);
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
        entradas4 = findViewById(R.id.entradas4);*/



        /*LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        LinearLayout llBunburyEvents = findViewById(R.id.llBunburyEvents);
        LinearLayout eventParent = new LinearLayout(llBunburyEvents.getContext());


        TextView horaTextView = new TextView(eventParent.getContext());
        horaTextView.setText("HORA");

        TextView lugarTextView = new TextView(eventParent.getContext());
        lugarTextView.setText("LUGAR");


        eventParent.addView(horaTextView);
        eventParent.addView(lugarTextView);
        llBunburyEvents.addView(eventParent);*/
        // llBunburyEvents.addView(eventParent);
        // llBunburyEvents.addView(lugarTextView);




        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.child("evento").orderByChild("artista").equalTo("Bunbury")
            .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String artista = snapshot.child("artista").getValue().toString();
                        String lugar = snapshot.child("lugar").getValue().toString();
                        String fecha = snapshot.child("fecha").getValue().toString();
                        String hora = snapshot.child("hora").getValue().toString();
                        String id = snapshot.child("id").getValue().toString();

                        Eventos bunburyEventos = new Eventos();
                        bunburyEventos.setEvento(id, new Evento(artista, lugar, fecha, hora, id));

                        LinearLayout.LayoutParams parentContentParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        parentContentParams.setMargins(0, 40, 0, 0);

                        LinearLayout.LayoutParams wrapContentParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                2
                        );

                        LinearLayout.LayoutParams wrapContentTextParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                2
                        );

                        LinearLayout.LayoutParams wrapContentButtonParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                0
                        );
                        wrapContentTextParams.setMargins(30, 0,20, 40);

                        LinearLayout llBunburyEvents = findViewById(R.id.llBunburyEvents);
                        LinearLayout eventParent = new LinearLayout(llBunburyEvents.getContext());
                        eventParent.setLayoutParams(parentContentParams);

                        LinearLayout llTextContent = new LinearLayout(llBunburyEvents.getContext());
                        llTextContent.setLayoutParams(wrapContentTextParams);
                        llTextContent.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                        llTextContent.setOrientation(LinearLayout.VERTICAL);

                        TextView fechaTextView = new TextView(eventParent.getContext());
                        fechaTextView.setTextColor(Color.WHITE);
                        fechaTextView.setText(fecha);
                        fechaTextView.setLayoutParams(wrapContentParams);

                        TextView horaTextView = new TextView(eventParent.getContext());
                        horaTextView.setLayoutParams(wrapContentParams);
                        horaTextView.setTextColor(Color.WHITE);
                        horaTextView.setText(hora);

                        TextView lugarTextView = new TextView(eventParent.getContext());
                        lugarTextView.setLayoutParams(wrapContentParams);
                        lugarTextView.setTextColor(Color.WHITE);
                        lugarTextView.setText(lugar);

                        TextView artistaTextView = new TextView(eventParent.getContext());
                        artistaTextView.setLayoutParams(wrapContentParams);
                        artistaTextView.setTextColor(Color.WHITE);
                        artistaTextView.setText(artista);

                        Button botonEntradas = new Button(eventParent.getContext());
                        botonEntradas.setLayoutParams(wrapContentButtonParams);
                        botonEntradas.setText("entradas");
                        botonEntradas.setBackgroundColor(2);
                        botonEntradas.setId(View.generateViewId());

                        eventParent.addView(fechaTextView);
                        eventParent.addView(llTextContent);
                        llTextContent.addView(horaTextView);
                        llTextContent.addView(lugarTextView);
                        llTextContent.addView(artistaTextView);
                        eventParent.addView(botonEntradas);

                        llBunburyEvents.addView(eventParent);

                        botonEntradas.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                System.out.println("what is view??? " + id);
                                Intent intent = new Intent(BunburyActivity.this, CompraActivity.class);
                                intent.putExtra("eventoId", id);
                                startActivity(intent);
                            }
                        });

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });




        /*entradas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*Intent b = new Intent(BunburyActivity.this, DatosEntrada.class);
                String lugar = lugarB1.getText().toString();
                String fecha = fechaB1.getText().toString();
                String artista = artistaB1.getText().toString();
                b.putExtra("LugarB1", lugar);
                b.putExtra("FechaB1", fecha);
                b.putExtra("ArtistaB1",artista);
                startActivity(b);*//*
            }
        });
        entradas2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*Intent i = new Intent(BunburyActivity.this, DatosEntrada.class);
                String lugar = lugarB2.getText().toString();
                String fecha = fechaB2.getText().toString();
                String artista = artistaB2.getText().toString();
                i.putExtra("LugarB2", lugar);
                i.putExtra("FechaB2", fecha);
                i.putExtra("ArtistaB2",artista);
                startActivity(i);*//*
            }
        });
        entradas3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*Intent intent = new Intent(BunburyActivity.this, CompraActivity.class);
                startActivity(intent);*//*
            }
        });
        entradas4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*Intent intent = new Intent(BunburyActivity.this, CompraActivity.class);
                startActivity(intent);*//*
            }
        });*/

    }

}