package com.example.conserjera;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GestionVisitasActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private MaterialButton btnAbrirCamara;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_visitas_activity);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Inicializar Firebase Realtime Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("visitas");

        TextInputLayout nameLayout = findViewById(R.id.nameLayout);
        TextInputEditText txtName = nameLayout.findViewById(R.id.txtName);

        TextInputLayout rutLayout = findViewById(R.id.rutLayout);
        TextInputEditText txtRut = rutLayout.findViewById(R.id.txtRut);

        TextInputLayout motivoLayout = findViewById(R.id.motivoLayout);
        TextInputEditText txtMotivo = motivoLayout.findViewById(R.id.txtMotivo);

        btnAbrirCamara = findViewById(R.id.btnScanQR);

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtName.getText().toString().trim();
                String rut = txtRut.getText().toString().trim();
                String motivo = txtMotivo.getText().toString().trim();

                // Verificar que los campos no estén vacíos
                if (nombre.isEmpty() || rut.isEmpty() || motivo.isEmpty()) {
                    Toast.makeText(GestionVisitasActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Crear un mapa con los datos de la visita
                Map<String, Object> visitaMap = new HashMap<>();
                visitaMap.put("nombre", nombre);
                visitaMap.put("rut", rut);
                visitaMap.put("motivo", motivo);

                // Registrar los datos de la visita en Firebase Realtime Database
                mDatabase.push().setValue(visitaMap);

                // Mostrar un mensaje de éxito
                Toast.makeText(GestionVisitasActivity.this, "Visita registrada exitosamente", Toast.LENGTH_SHORT).show();

                // Limpiar los campos después de registrar la visita
                txtName.getText().clear();
                txtRut.getText().clear();
                txtMotivo.getText().clear();
            }
        });

        btnAbrirCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });
    }

    private void abrirCamara() {
        // Crear un intent para abrir la aplicación de la cámara
        Intent intentCamara = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);

        // Verificar si hay una aplicación de cámara disponible para manejar el intent
        if (intentCamara.resolveActivity(getPackageManager()) != null) {
            // Iniciar la actividad de la cámara
            startActivity(intentCamara);
        } else {
            Toast.makeText(this, "No hay aplicación de cámara disponible", Toast.LENGTH_SHORT).show();
        }
    }
}
