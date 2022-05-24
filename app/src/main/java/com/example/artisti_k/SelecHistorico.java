package com.example.artisti_k;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SelecHistorico extends AppCompatActivity {

    ListView listView;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selec_historico);


        listView = findViewById(R.id.list_View);

        databaseReference= FirebaseDatabase.getInstance().getReference("compra");
        databaseReference
                .child("compra")
                .orderByChild("id")
                .equalTo("usuario")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                        for (DataSnapshot snapshot : datasnapshot.getChildren()){

                            String id = snapshot.child("id").getValue().toString();
                            String idEvento = snapshot.child("idEvento").getValue().toString();
                            String artista = snapshot.child("artista").getValue().toString();
                            String lugar = snapshot.child("lugar").getValue().toString();
                            String fecha = snapshot.child("fecha").getValue().toString();
                            String entradas = snapshot.child("entradas").getValue().toString();
                            String precioTotal = snapshot.child("total").getValue().toString();
                            String fechaReal = snapshot.child("fechaCompra").getValue().toString();
                            String email = snapshot.child("usuario").getValue().toString();



                            ConfirmacionCompras compraUsuario = new ConfirmacionCompras();
                            compraUsuario.setConfirmacionCompra(id, new ConfirmacionCompra(id, idEvento, artista, lugar, fecha, entradas, precioTotal, fechaReal, email));

                            ListView.LayoutParams parentContentParams = new ListView.LayoutParams(
                                    ListView.LayoutParams.MATCH_PARENT,
                                    ListView.LayoutParams.WRAP_CONTENT
                            );

                            ListView llList_View = findViewById(R.id.llList_View);
                            ListView eventParent = new ListView(llList_View.getContext());
                            eventParent.setLayoutParams(parentContentParams);

                            ListView llTextContent = new ListView(llList_View.getContext());
                            llTextContent.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);

                            TextView fechaTextView = new TextView(eventParent.getContext());
                            fechaTextView.setTextColor(Color.WHITE);
                            fechaTextView.setText(fechaReal);
                            fechaTextView.setLayoutParams(parentContentParams);

                            eventParent.addView(fechaTextView);
                            eventParent.addView(llTextContent);
                            llTextContent.addView(fechaTextView);

                            llList_View.addView(eventParent);


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }
}


