package com.example.artisti_k;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatosEntrada extends AppCompatActivity {

    FirebaseAuth fAuth;
    DatabaseReference databaseReference;
    TextView emailEntradas, numeroEntradas, eventoEntradas, lugarEvento, fechaEvento, numEntr, totalPrecio, id2;
    EditText numTarjeta, mesTarjeta, anioTarjeta, cvv;
    Button compraEntrada;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_entrada);


        emailEntradas = findViewById(R.id.emailTView);
        numeroEntradas = findViewById(R.id.numEntradasTv);
        numEntr = findViewById(R.id.numEnt);
        totalPrecio = findViewById(R.id.totalEntradas);
        eventoEntradas = findViewById(R.id.eventoTv);
        id2 = findViewById(R.id.id);
        lugarEvento = findViewById(R.id.lugarEventoTv);
        fechaEvento = findViewById(R.id.fechaEventoTv);
        numTarjeta = findViewById(R.id.numeroTarjetaEd);
        mesTarjeta = findViewById(R.id.mesCaducidadEd);
        anioTarjeta = findViewById(R.id.añoCaducidadEd);
        cvv = findViewById(R.id.cvvEd);
        compraEntrada = findViewById(R.id.comprarEntradasBtn);

        //Instancia de Firebase Authentication y firebase Realtime Database
        fAuth=FirebaseAuth.getInstance();
        FirebaseUser user = fAuth.getCurrentUser();



        /*Nos muestra en el TextView el email con el que se ha registrado el usuario en la ventana de datos de usuario a través
         de una instancia de firebase autentication.*/

        emailEntradas.setText(user.getEmail());


        //TODO:: ANDRES aqui intento hacer lo mismo pero como soy un caraja y no me entero de na pues no se por que no sale
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
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                        for (DataSnapshot snapshot : datasnapshot.getChildren()){
                            String artista = snapshot.child("artista").getValue().toString();
                            String lugar = snapshot.child("lugar").getValue().toString();
                            String fecha = snapshot.child("fecha").getValue().toString();
                            String id = snapshot.child("id").getValue().toString();

                            eventoEntradas.setText(artista);
                            lugarEvento.setText(lugar);
                            fechaEvento.setText(fecha);
                            id2.setText(id);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




        //Llamamos a la función de agregar el espacio en blanco al introducir la tarjeta
        numTarjeta.addTextChangedListener(textWatcher);

        //Para introducir el número de la tarjeta de crédito
        numTarjeta.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String tarjeta = numTarjeta.getText().toString().trim();
                String tarjetaTrim = String.join("",tarjeta.split(" "));

                if (TextUtils.isEmpty(tarjeta)){
                    numTarjeta.setError("El número de tarjeta no puede estar vacío");
                    return;
                }
                System.out.println(tarjetaTrim);
                if (tarjetaTrim.length() < 16 || tarjetaTrim.length() >= 17 ){

                    numTarjeta.setError("El número de tarjeta debe tener al menos  16 números");
                    return;
                }
            }
        });

        //Introducir el número del mes de la tarjeta de credito
        mesTarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mes =  mesTarjeta.getText().toString().trim();
                Integer mesNum = Integer.parseInt(mes);
                if (TextUtils.isEmpty(mes)){
                    numTarjeta.setError("El número de mes no puede estar vacío");
                    return;
                }
                if (mesNum >= 1 && mesNum <= 9){
                    String newMes = "" + 0 + mesNum ;
                    String mesString = newMes.toString();
                    mesTarjeta.setText(mesString);
                }else if (mesNum < 1 || mesNum > 12){
                    mesTarjeta.setError("El mes introducido es incorrecto");
                }
            }
        });

        // Según el mes que se introduzca estará la tarjera caducada o no.
        anioTarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String anio =  anioTarjeta.getText().toString().trim();
                Integer anioA = Integer.parseInt(anio);

                String mes = mesTarjeta.getText().toString().trim();
                Integer mesNum = Integer.parseInt(mes);

                if (TextUtils.isEmpty(anio)){
                    numTarjeta.setError("El número de año no puede estar vacío");
                    return;
                }
                if ( anio.length() < 2 || anio.length() > 2 ){
                    anioTarjeta.setError("Introduzca el número de año");
                    return;
                }
                if (anioA >27){
                    anioTarjeta.setError("Año de tarjeta incorrecto");
                    return;
                }
                if (mesNum <= 9 && anioA <= 22){
                    anioTarjeta.setError("Tarjeta caducada");
                }
            }
        });

        //Código de seguridad de 3 dígitos
        cvv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codSecret =  cvv.getText().toString().trim();
                if (TextUtils.isEmpty(codSecret)){
                    cvv.setError("El código secreto no puede estar vacío");
                    return;
                }
                if (codSecret.length() > 3 || codSecret.length() < 3){
                    cvv.setError("Introduzca el código secreto");
                    return;
                }
            }
        });
    }

    //Función que recoge el numero de la tarjeta y añade un espacio en blanco cada 4 numeros.
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String numTarjetaInput = numTarjeta.getText().toString();
            Integer numTarjetaInputt = numTarjetaInput.length();

            if (numTarjetaInputt == 4 || numTarjetaInputt == 9 || numTarjetaInputt == 14){
                numTarjeta.setText(numTarjetaInput + " ");
                numTarjeta.setSelection(numTarjeta.getText().length());
            }
        }
    };
}