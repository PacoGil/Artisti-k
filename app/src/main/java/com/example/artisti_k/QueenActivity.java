package com.example.artisti_k;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QueenActivity extends AppCompatActivity {

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queen);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("evento").orderByChild("artista").equalTo("God Save the Queen")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String artista = snapshot.child("artista").getValue().toString();
                            String lugar = snapshot.child("lugar").getValue().toString();
                            String fecha = snapshot.child("fecha").getValue().toString();
                            String hora = snapshot.child("hora").getValue().toString();
                            String id = snapshot.child("id").getValue().toString();

                            Eventos queenEventos = new Eventos();
                            queenEventos.setEvento(id, new Evento(artista, lugar, fecha, hora, id));

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

                            LinearLayout llQueenEvents = findViewById(R.id.llQueenEvents);
                            LinearLayout eventParent = new LinearLayout(llQueenEvents.getContext());
                            eventParent.setLayoutParams(parentContentParams);

                            LinearLayout llTextContent = new LinearLayout(llQueenEvents.getContext());
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

                            llQueenEvents.addView(eventParent);

                            botonEntradas.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    System.out.println("what is view??? " + id);
                                    Intent intent = new Intent(QueenActivity.this, CompraActivity.class);
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