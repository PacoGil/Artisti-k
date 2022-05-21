package com.example.artisti_k;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HistoricoActivity extends AppCompatActivity {


    TextView fechaHistory, artistaHistory,lugarHistory, entradasHistory, totalHistory;
    String idID, fechaHis, artistaHis, lugarHis, entradasHis, totalHis;
    DatabaseReference databaseReference;
    Button volverUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        volverUser = findViewById(R.id.volverUsuario);
        fechaHistory = findViewById(R.id.fechaHistorico);
        artistaHistory = findViewById(R.id.artistaHistorico);
        lugarHistory = findViewById(R.id.lugarHistorico);
        entradasHistory = findViewById(R.id.entradasHistorico);
        totalHistory = findViewById(R.id.precioHistorico);


        Bundle extras = getIntent().getExtras();
        idID = extras.getParcelable("idId");

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference
                .child("compra")
                .orderByChild("id")
                .equalTo(idID)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        fechaHis = snapshot.child("fecha").getValue().toString();
                        artistaHis = snapshot.child("artista").getValue().toString();
                        lugarHis = snapshot.child("lugar").getValue().toString();
                        entradasHis = snapshot.child("entradas").getValue().toString();
                        totalHis = snapshot.child("total").getValue().toString();

                        fechaHistory.setText(fechaHis);
                        artistaHistory.setText(artistaHis);
                        lugarHistory.setText(lugarHis);
                        entradasHistory.setText(entradasHis);
                        totalHistory.setText(totalHis);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        volverUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoricoActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}