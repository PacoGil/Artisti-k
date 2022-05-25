package com.example.artisti_k;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class DatosEntrada extends AppCompatActivity {

    FirebaseAuth fAuth;
    DatabaseReference databaseReference;
    TextView emailEntradas, artista, lugarEvento, fechaEvento, numEntr, totalPrecio, idEvento;
    EditText numTarjeta, mesTarjeta, anioTarjeta, cvv;
    Button compraEntrada;
    Long unixTime;
    String fechaReal, id2, userId;
    ConfirmacionCompra compraConfirmada;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_entrada);

        emailEntradas = findViewById(R.id.emailTView);
        numEntr = findViewById(R.id.numEnt);
        totalPrecio = findViewById(R.id.totalEntradas);
        artista = findViewById(R.id.eventoTv);
        idEvento = findViewById(R.id.idEvent);
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
        databaseReference = FirebaseDatabase.getInstance().getReference();

        Bundle extras = getIntent().getExtras();
        CompraEntradas compraEvento = extras.getParcelable("compraEvento");

        /*Nos muestra en el TextView el email con el que se ha registrado el usuario en la ventana de datos de usuario a través
         de una instancia de firebase autentication.*/
        emailEntradas.setText(user.getEmail());

        unixTime = System.currentTimeMillis() / 1000L;
        id2 = Long.toString(unixTime);
        idEvento.setText(compraEvento.getIdEvento());
        numEntr.setText(compraEvento.getEntradas());
        artista.setText(compraEvento.getArtista());
        lugarEvento.setText(compraEvento.getLugarEvento());
        fechaEvento.setText(compraEvento.getFechaLugarEvento());
        totalPrecio.setText(compraEvento.getPrecioTotal());
        userId = user.getUid();

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

        Calendar calendar = Calendar.getInstance();
        fechaReal = DateFormat.getDateInstance().format(calendar.getTime());
        System.out.println("QUE ES FECHAREAL " + fechaReal);

        compraEntrada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String tarjeta = numTarjeta.getText().toString().trim();
                String mes = mesTarjeta.getText().toString().trim();
                String anio = anioTarjeta.getText().toString().trim();
                String codigo = cvv.getText().toString().trim();

                if (TextUtils.isEmpty(tarjeta)){

                    numTarjeta.setError("Introduce el número de tarjeta");
                    return;
                }

                if (TextUtils.isEmpty(mes)){

                    mesTarjeta.setError("Introduce el mes");
                    return;
                }

                if (TextUtils.isEmpty(anio)){

                    anioTarjeta.setError("Introduce el año");
                    return;
                }

                if (TextUtils.isEmpty(codigo)){

                    cvv.setError("Introduce el código de seguridad");
                    return;
                }

                compraConfirmada = new ConfirmacionCompra(
                        id2,
                        compraEvento.getIdEvento(),
                        compraEvento.getArtista(),
                        compraEvento.getLugarEvento(),
                        compraEvento.getFechaLugarEvento(),
                        compraEvento.getEntradas(),
                        compraEvento.getPrecioTotal(),
                        fechaReal,
                        userId
                );

                databaseReference.child("compra").push().setValue(compraConfirmada,
                        new DatabaseReference.CompletionListener() {

                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference databaseReference) {

                        Toast.makeText(DatosEntrada.this, "Compra realizada con éxito",Toast.LENGTH_SHORT).show();
                    }
                });

                Intent intent = new Intent(DatosEntrada.this, FinalActivity.class);
                intent.putExtra("idId", id2);
                startActivity(intent);
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