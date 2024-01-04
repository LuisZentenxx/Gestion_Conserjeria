package com.example.conserjera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResidenteViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.residente_view_activity);

        Button btnVerDatos = findViewById(R.id.btnDatos);
        Button btnReserva = findViewById(R.id.btnReserva);

        btnVerDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResidenteViewActivity.this, MiPerfil.class);
                startActivity(intent);
            }
        });

        btnReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResidenteViewActivity.this, ReservaActivity.class);
                startActivity(intent);
            }
        });

    }

}
