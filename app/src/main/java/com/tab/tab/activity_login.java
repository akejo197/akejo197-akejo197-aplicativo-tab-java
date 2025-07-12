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
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
//import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_login extends AppCompatActivity {
/*realizamos el llamado de las variables necesarias para el login*/
    TextInputEditText editTextEmail, editTextPassword;
    Button buttonLogin;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
/*realizamos el llamado de las variables necesarias para el login  y poderlas utilizar*/
        editTextEmail = findViewById(R.id.ingreso_usuario);
        editTextPassword = findViewById(R.id.ingreso_contrasena);
        buttonLogin = findViewById(R.id.boton_iniciar_sesion);

/*realizamos el llamado de las variables necesarias para el login  y poderlas utilizar*/
        /* setOnClickListener me permite registrar una devolucion de una llamada */
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ingreso_usuario, ingreso_contrasena;
                ingreso_usuario = String.valueOf(editTextEmail.getText());
                ingreso_contrasena = String.valueOf(editTextPassword.getText());
/*validamos que los campos no esten vacios mediante el metodo isEmpty*/
                if(TextUtils.isEmpty(ingreso_usuario)){
                    Toast.makeText(activity_login.this, "Ingrese su email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(ingreso_contrasena)){
                    Toast.makeText(activity_login.this, "Ingrese su contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }
/* conectamos con firebase mediante el metodo signInWithEmailAndPassword*/
                firebaseAuth.signInWithEmailAndPassword(ingreso_usuario, ingreso_contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
 /*mediante el metodo isSuccessful validamos que el inicio de sesion sea exitoso*/
 /*usamos intent para navegar entre pantallas, es importante inicializar el intent con startActivity() y finalizar la actividad actual con finish()*/
                        if(task.isSuccessful()){
                /*mediante Toast mostramos un mensaje de confirmacion si el inicio de sesion es exitoso o no*/
                                Toast.makeText(activity_login.this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(activity_login.this, activity_eventos.class);
                                startActivity(intent);
                                finish();
                        }else{
                            Toast.makeText(activity_login.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                );
            }
        });


   //     ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
        //    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
       //     v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
       //     return insets;
      // })
    }
}