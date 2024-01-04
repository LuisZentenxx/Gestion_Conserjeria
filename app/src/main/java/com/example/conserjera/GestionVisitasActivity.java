package com.example.conserjera;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.integration.android.IntentIntegrator;


public class GestionVisitasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_visitas_activity);

        Button btnScanQR = findViewById(R.id.btnScanQR);
        btnScanQR.setOnClickListener(view -> iniciarEscaneoQR());
    }

    // Método para iniciar el escaneo QR
    private void iniciarEscaneoQR() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("Escanea el código QR"); // Mensaje mostrado al usuario
        integrator.setBeepEnabled(true); // Habilitar sonido al escanear
        integrator.initiateScan(); // Iniciar la actividad de escaneo
    }
}
