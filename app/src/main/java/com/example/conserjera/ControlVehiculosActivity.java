package com.example.conserjera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class ControlVehiculosActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_vehiculos_activity);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Inicializar Firebase Realtime Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("vehiculos");

        TextInputLayout txtPatenteLayout = findViewById(R.id.patenteLayout);
        TextInputEditText txtPatente = txtPatenteLayout.findViewById(R.id.txtPatente);

        TextInputLayout txtMarcaLayout = findViewById(R.id.marcaLayout);
        TextInputEditText txtMarca = txtMarcaLayout.findViewById(R.id.txtMarca);

        TextInputLayout txtColorLayout = findViewById(R.id.colorLayout);
        TextInputEditText txtColor = txtColorLayout.findViewById(R.id.txtColor);

        MaterialButton btnRegistro = findViewById(R.id.btnRegistroVehiculo);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener la información ingresada por el usuario
                String patente = txtPatente.getText().toString().trim();
                String marca = txtMarca.getText().toString().trim();
                String color = txtColor.getText().toString().trim();

                // Crear un mapa con los datos del vehículo
                Map<String, Object> vehiculoMap = new HashMap<>();
                vehiculoMap.put("patente", patente);
                vehiculoMap.put("marca", marca);
                vehiculoMap.put("color", color);

                // Verificar si la patente es sospechosa de manera aleatoria
                if (esPatenteSospechosaAleatoria()) {
                    // Lógica para manejar patente sospechosa
                    Toast.makeText(ControlVehiculosActivity.this, "¡Patente sospechosa detectada!", Toast.LENGTH_SHORT).show();

                    // Redirigir a la actividad PatenteActivity y pasar la patente como dato extra
                    Intent intent = new Intent(ControlVehiculosActivity.this, PatenteActivity.class);
                    intent.putExtra("patente", patente);
                    startActivity(intent);

                } else {
                    // Registrar los datos del vehículo en Firebase Realtime Database
                    mDatabase.child(patente).setValue(vehiculoMap);

                    Toast.makeText(ControlVehiculosActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                    // Redirigir a la actividad ConserjeViewActivity
                    Intent intent = new Intent(ControlVehiculosActivity.this, ConserjeViewActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    // Verificar de manera aleatoria si la patente es sospechosa
    private boolean esPatenteSospechosaAleatoria() {
        Random random = new Random();
        // Generar un número aleatorio (0 o 1)
        int aleatorio = random.nextInt(2);
        // Devolver true si el número es 1 (50% de probabilidad), indicando que es sospechosa
        return aleatorio == 1;
    }
}
