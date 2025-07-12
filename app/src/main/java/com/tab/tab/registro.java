package com.tab.tab;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registro extends AppCompatActivity {


    TextInputEditText  editTextEmail, editTextPassword;
    Button registrarme;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);


        editTextEmail = findViewById(R.id.ingreso_usuario);
        editTextPassword = findViewById(R.id.ingreso_contrasena);
        registrarme = findViewById(R.id.registrarme);

        registrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ingreso_usuario, ingreso_contrasena;
                ingreso_usuario = String.valueOf(editTextEmail.getText());
                ingreso_contrasena = String.valueOf(editTextPassword.getText());
                /*validamos que los campos no esten vacios mediante el metodo isEmpty*/
                if(TextUtils.isEmpty(ingreso_usuario)){
                    Toast.makeText(registro.this, "Ingrese su email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(ingreso_contrasena)){
                    Toast.makeText(registro.this, "Ingrese su contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(ingreso_usuario, ingreso_contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            /*mediante Toast mostramos un mensaje de confirmacion si el inicio de sesion es exitoso o no*/
                            Toast.makeText(registro.this, "registro exitoso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(registro.this, activity_login.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(registro.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registro), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}