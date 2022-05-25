
package com.example.artisti_k;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class SelecHistorico extends AppCompatActivity {

    ListView listView;
    Button boton;
    DatabaseReference databaseReference;
    FirebaseAuth fAuth;
    String userId;

    HashMap<Integer, String> listaComprasMap = new HashMap<Integer, String>();
    ArrayList<String> listaCompras = new ArrayList<String>();
    ArrayAdapter<String> arrayAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selec_historico);

        fAuth = FirebaseAuth.getInstance();
        FirebaseUser user = fAuth.getCurrentUser();
        userId = user.getUid();
        listView = findViewById(R.id.llList_View);
        boton = findViewById(R.id.buttonVolver2);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference
                .child("compra")
                .orderByChild("usuario")
                .equalTo(userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                        int i = 0;
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

                            listaComprasMap.put(i, id);

                            /* Texto que quieres que se muestre en la lista */
                            listaCompras.add(artista + " | " + fechaReal);

                            arrayAdaptor = new ArrayAdapter<String>(
                                    SelecHistorico.this,
                                    R.layout.listacompra,
                                    listaCompras
                            );

                            listView.setAdapter(arrayAdaptor);
                            listView.setClickable(true);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                    System.out.println("adapterView::: " + adapterView);

                                    Intent intent = new Intent(SelecHistorico.this, HistoricoActivity.class);

                                    //ID de la compra para llevarlo a la siguiente activity
                                    intent.putExtra("compraIdClick", listaComprasMap.get(i));
                                    startActivity(intent);

                                    // listaComprasMap.get(i) === el ID de la compra para llevarlo a la siguiente activity
                                    System.out.println("compra ID clickeado:: " + listaComprasMap.get(i));
                                }
                            });

                            i++;

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

                boton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SelecHistorico.this, SettingsActivity.class);
                        startActivity(intent);
                    }
                });
        }
}


