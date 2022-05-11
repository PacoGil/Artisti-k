package com.example.artisti_k;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatosEntrada extends AppCompatActivity {

    FirebaseAuth fAuth;
    DatabaseReference databaseReference;
    TextView emailEntradas, numeroEntradas, eventoEntradas, lugarEvento, fechaEvento;
    EditText numTarjeta, mesTarjeta, anioTarjeta, cvv;
    Button compraEntrada;
    Spinner spinner1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_entrada);


        emailEntradas = findViewById(R.id.emailTView);
        numeroEntradas = findViewById(R.id.numEntradasTv);
        eventoEntradas = findViewById(R.id.eventoTv);
        spinner1 = findViewById(R.id.spinner);
        lugarEvento = findViewById(R.id.lugarEventoTv);
        fechaEvento = findViewById(R.id.fechaEventoTv);
        numTarjeta = findViewById(R.id.numeroTarjetaEd);
        mesTarjeta = findViewById(R.id.mesCaducidadEd);
        anioTarjeta = findViewById(R.id.añoCaducidadEd);
        cvv = findViewById(R.id.cvvEd);
        compraEntrada = findViewById(R.id.comprarEntradasBtn);

        fAuth=FirebaseAuth.getInstance();
        FirebaseUser user = fAuth.getCurrentUser();

        databaseReference= FirebaseDatabase.getInstance().getReference();

        /*Nos muestra en el TextView el email con el que se ha registrado el usuario en la ventana de datos de usuario a través
          de una instancia de firebase autentication.*/
        emailEntradas.setText(user.getEmail());

        //Nos muestra en el Textview el número de entradas elegidas para comprar guardado en la base de datos eventos


        String [] opciones = {"1","2","3","4","5","6","7","8"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_compra,opciones);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        numTarjeta.addTextChangedListener(textWatcher);

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

        // si el mes introducido es enero la tarjeta esta caducada
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


        Intent intent = getIntent();
        //Datos Bumbury 2
        String mesageB2 = intent.getStringExtra("LugarB2");
        String mesageB21 = intent.getStringExtra("FechaB2");
        String mesageB22 = intent.getStringExtra("ArtistaB2");
        lugarEvento.setText(mesageB2);
        fechaEvento.setText(mesageB21);
        eventoEntradas.setText(mesageB22);


    }

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