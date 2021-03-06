package com.example.artisti_k;

import static android.view.View.TEXT_ALIGNMENT_GRAVITY;
import androidx.annotation.NonNull;
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

public class CarrascoActivity extends AppCompatActivity {

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrasco);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("evento").orderByChild("artista").equalTo("Manuel Carrasco")
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                        for (DataSnapshot snapshot : datasnapshot.getChildren()){
                            String artista = snapshot.child("artista").getValue().toString();
                            String lugar = snapshot.child("lugar").getValue().toString();
                            String fecha = snapshot.child("fecha").getValue().toString();
                            String hora = snapshot.child("hora").getValue().toString();
                            String id = snapshot.child("id").getValue().toString();

                            Eventos manuelEventos = new Eventos();
                            manuelEventos.setEvento(id, new Evento(artista, lugar, fecha, hora, id));

                            LinearLayout.LayoutParams parentContentParams = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT

                            );
                            parentContentParams.setMargins(0, 100, 0, 20);

                            LinearLayout.LayoutParams wrapContentParams = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    1
                            );
                            wrapContentParams.setMargins(0, 0,0, 20);

                            LinearLayout.LayoutParams wrapContentTextParams = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    10
                            );
                            wrapContentTextParams.setMargins(25, 0,25, 0);

                            LinearLayout.LayoutParams wrapContentButtonParams = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT

                            );
                            wrapContentButtonParams.setMargins(0, 20,0, 20);

                            LinearLayout llCarrascoEvents = findViewById(R.id.llCarrascoEvents);
                            LinearLayout eventParent = new LinearLayout(llCarrascoEvents.getContext());
                            eventParent.setLayoutParams(parentContentParams);

                            LinearLayout llTextContent = new LinearLayout(llCarrascoEvents.getContext());
                            llTextContent.setLayoutParams(wrapContentTextParams);
                            llTextContent.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                            llTextContent.setOrientation(LinearLayout.VERTICAL);

                            TextView fechaTextView = new TextView(eventParent.getContext());
                            fechaTextView.setTextColor(Color.WHITE);
                            fechaTextView.setText(fecha);
                            fechaTextView.setTextAlignment(TEXT_ALIGNMENT_GRAVITY);
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

                            llCarrascoEvents.addView(eventParent);

                            botonEntradas.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                    System.out.println("what is view??? " + id);
                                    Intent intent = new Intent(CarrascoActivity.this, CompraActivity.class);
                                    intent.putExtra("eventoId", id);
                                    startActivity(intent);
                                }
                            });

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        }
}