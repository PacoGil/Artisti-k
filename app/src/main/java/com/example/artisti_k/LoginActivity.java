package com.example.artisti_k;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;


import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


import java.util.Objects;

public class LoginActivity extends AppCompatActivity{

    FirebaseAuth fAuth;
    EditText emailLogin,passwordLogin;
    TextView signIn,google;
    Button logIn;
    GoogleSignInClient mGoogleSingInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fAuth = FirebaseAuth.getInstance();
        emailLogin = findViewById(R.id.emailEditText);
        passwordLogin = findViewById(R.id.passwordEditText);
        signIn = findViewById(R.id.sigInTexView);
        logIn = findViewById(R.id.logInButton);
        google = findViewById(R.id.google);

        //Funcionalidad del botón de registro
        signIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });

        //Funcionalidad del botón de acceder
        logIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String email = emailLogin.getText().toString().trim();
                String password = passwordLogin.getText().toString().trim();

                if (TextUtils.isEmpty(email)){

                    emailLogin.setError("Introduce el email");
                    return;
                }

                if (TextUtils.isEmpty(password)){

                    passwordLogin.setError("Introduce la contraseña");
                    return;
                }

                if (password.length() < 6){

                    passwordLogin.setError("La contraseña debe tener al menos 6 caracteres");
                    return;
                }

                //Si el login es correcto muestra la siguiente pantalla
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(LoginActivity.this,"Sesíon iniciada correctamente", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }else{

                            Toast.makeText(LoginActivity.this,"ERROR ! "+ Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //Configuración de inicio de sesión con Google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSingInClient = GoogleSignIn.getClient(this,gso);

        google.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                launcher.launch(new Intent(mGoogleSingInClient.getSignInIntent()));
            }
        });
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {

        @Override
        public void onActivityResult(ActivityResult result) {

            if (result.getResultCode() == Activity.RESULT_OK){

                Intent intent = result.getData();
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);

                try {

                    GoogleSignInAccount account = task.getResult(ApiException.class);

                    assert  account != null;
                    firebaseAuthWhithGoogle(account.getIdToken());

                }catch (ApiException e){

                }
            }
        }
    });

    private void firebaseAuthWhithGoogle(String idToken){

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        fAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                          startActivity(new Intent( LoginActivity.this, MainActivity.class));
                          finish();
                          Toast.makeText(LoginActivity.this, "Login correcto", Toast.LENGTH_SHORT).show();

                        }else {

                            Toast.makeText(LoginActivity.this, "Error",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}