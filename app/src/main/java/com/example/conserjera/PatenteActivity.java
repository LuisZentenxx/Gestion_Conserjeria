package com.example.conserjera;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PatenteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patente_sospechosa_activity);

        // Obtener la patente sospechosa del Intent
        String patenteSospechosa = getIntent().getStringExtra("patente");

        // Actualizar el TextView con la patente sospechosa
        TextView txtPatenteSospechosa = findViewById(R.id.patenteSospechosa);
        txtPatenteSospechosa.setText(patenteSospechosa);
    }
}
