package com.example.artisti_k;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    Button galeria, saveData,logOut, historicoCompra;
    EditText nombreEd, apellidosEd, phonEd;
    TextView emailTv;
    ImageView imagen;
    ActivityResultLauncher<String> launcherGalery;
    FirebaseAuth fAuth;
    DatabaseReference databaseReference;
    List<Usuario> listaUsuario = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        imagen = findViewById(R.id.imagenSet);
        galeria = findViewById(R.id.galeriaButton);
        nombreEd = findViewById(R.id.nombreEdit);
        apellidosEd = findViewById(R.id.apellidosEdit);
        phonEd = findViewById(R.id.phoneEdit);
        emailTv = findViewById(R.id.emailView);
        saveData = findViewById(R.id.comprarEntradas);
        historicoCompra = findViewById(R.id.historicoButton);
        logOut = findViewById(R.id.logOut);
        databaseReference= FirebaseDatabase.getInstance().getReference();

        fAuth = FirebaseAuth.getInstance();
        FirebaseUser user = fAuth.getCurrentUser();

        //Con este lanzador de actividad, programamos la apertura de la galería de imagenes del teléfono
        launcherGalery = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                imagen.setImageURI(result);
            }
        });

        //Funcionalidad botón de abrir la galería
        galeria.setOnClickListener(View -> launcherGalery.launch("image/*"));

        //Nos muestra en el TextView el email con el que se ha registrado el usuario en la ventana de datos de usuario
        emailTv.setText(user.getEmail());

        //guardar datos de usuario
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarUsuario();
            }
        });

        historicoCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, SelecHistorico.class);
                startActivity(intent);
            }
        });

        //Funcionalidad botón de cerrar sesión
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

    }

    public void agregarUsuario() {
        listaUsuario.clear();
        Usuario usuario = new Usuario(nombreEd.getText().toString(),
                apellidosEd.getText().toString(),
                phonEd.getText().toString(),
                emailTv.getText().toString()
        );
        databaseReference.child("usario").push().setValue(usuario,
                new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference databaseReference) {
                        Toast.makeText(SettingsActivity.this, "Usuario añadido",Toast.LENGTH_SHORT).show();
                    }
                });
        limpiarCampos();
    }
    public void limpiarCampos(){
        nombreEd.setText("");
        apellidosEd.setText("");
        phonEd.setText("");
    }
}