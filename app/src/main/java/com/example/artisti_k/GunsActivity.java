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

public class GunsActivity extends AppCompatActivity {

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guns);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("evento").orderByChild("artista").equalTo("Guns N' Roses")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String artista = snapshot.child("artista").getValue().toString();
                            String lugar = snapshot.child("lugar").getValue().toString();
                            String fecha = snapshot.child("fecha").getValue().toString();
                            String hora = snapshot.child("hora").getValue().toString();
                            String id = snapshot.child("id").getValue().toString();

                            Eventos gunsEventos = new Eventos();
                            gunsEventos.setEvento(id, new Evento(artista, lugar, fecha, hora, id));

                            LinearLayout.LayoutParams parentContentParams = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                            );
                            parentContentParams.setMargins(0, 100, 0, 0);

                            LinearLayout.LayoutParams wrapContentParams = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    0
                            );
                            wrapContentParams.setMargins(0, 0,10, 0);

                            LinearLayout.LayoutParams wrapContentTextParams = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    8
                            );
                            wrapContentTextParams.setMargins(10, 0,0, 0);

                            LinearLayout.LayoutParams wrapContentButtonParams = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    0
                            );
                            wrapContentButtonParams.setMargins(0, 20,0, 20);

                            LinearLayout llGunsEvents = findViewById(R.id.llGunsEvents);
                            LinearLayout eventParent = new LinearLayout(llGunsEvents.getContext());
                            eventParent.setLayoutParams(parentContentParams);

                            LinearLayout llTextContent = new LinearLayout(llGunsEvents.getContext());
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
                            botonEntradas.setBackgroundResource(R.color.mi);
                            botonEntradas.setId(View.generateViewId());

                            eventParent.addView(fechaTextView);
                            eventParent.addView(llTextContent);
                            llTextContent.addView(horaTextView);
                            llTextContent.addView(lugarTextView);
                            llTextContent.addView(artistaTextView);
                            eventParent.addView(botonEntradas);

                            llGunsEvents.addView(eventParent);

                            botonEntradas.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {

                                    Intent intent = new Intent(GunsActivity.this, CompraActivity.class);
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
        }
}