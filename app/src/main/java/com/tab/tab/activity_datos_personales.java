package com.tab.tab;

import android.annotation.SuppressLint;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_datos_personales extends AppCompatActivity {


    TextInputEditText editTextNombres, editTextApellidos,  editTextTelefono, editTextIdentificacion;
    Button registrarDatosPersonales;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_datos_personales);

        editTextNombres = findViewById(R.id.nombres);
        editTextApellidos = findViewById(R.id.apellidos);
        editTextTelefono = findViewById(R.id.telefono);
        editTextIdentificacion = findViewById(R.id.identificacion);
        registrarDatosPersonales = findViewById(R.id.boton_registro_datos_personales);

        registrarDatosPersonales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombres, apellidos, ingreso_usuario, telefono, identificacion, ingreso_contrasena;
                nombres = String.valueOf(editTextNombres.getText());
                apellidos = String.valueOf(editTextApellidos.getText());
                telefono = String.valueOf(editTextTelefono.getText());
                identificacion = String.valueOf(editTextIdentificacion.getText());
                /*validamos que los campos no esten vacios mediante el metodo isEmpty*/

                if(TextUtils.isEmpty(nombres)){
                    Toast.makeText(activity_datos_personales.this, "Ingrese sus nombres", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(apellidos)){
                    Toast.makeText(activity_datos_personales.this, "Ingrese sus apellidos", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(telefono)){
                    Toast.makeText(activity_datos_personales.this, "Ingrese su telefono", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(identificacion)){
                    Toast.makeText(activity_datos_personales.this, "Ingrese su identificacion", Toast.LENGTH_SHORT).show();
                    return;
                }



            }
        });




    //    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.datos_personales), (v, insets) -> {
     //       Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
    //        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
     //       return insets;
    //    });
    }
}