package com.example.artisti_k;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    EditText emailRegister,passwordRegister;
    TextView signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth = FirebaseAuth.getInstance();
        emailRegister = findViewById(R.id.emailRegister);
        passwordRegister = findViewById(R.id.passwordRegister);
        signInButton = findViewById(R.id.signInButton);

        signInButton.setOnClickListener(view -> {
            String email = emailRegister.getText().toString().trim();
            String password = passwordRegister.getText().toString().trim();

            if (TextUtils.isEmpty(email)){

                emailRegister.setError("Introduce el email");
                return;
            }

            if (TextUtils.isEmpty(password)){

                passwordRegister.setError("Introduce la contraseña");
                return;
            }

            if (password.length() < 6){

                passwordRegister.setError("La contraseña debe tener al menos 6 caracteres");
                return;
            }

            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {

                if (task.isSuccessful()){

                    Toast.makeText(RegisterActivity.this,"Usuario creado",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                }else {

                    Toast.makeText(RegisterActivity.this,"ERROR ! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}