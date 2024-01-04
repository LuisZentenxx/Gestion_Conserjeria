package com.example.conserjera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class ReservaActivity extends AppCompatActivity {

    private MaterialButton btnPiscina, btnSalaJuegos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserva_espacios_activity);

        btnPiscina = findViewById(R.id.btnPiscina);
        btnSalaJuegos = findViewById(R.id.btnSalaJuegos);

        btnPiscina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservaActivity.this, ReservaPiscinaActivity.class);
                startActivity(intent);
            }
        });

        btnSalaJuegos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservaActivity.this, ReservaSalaActivity.class);
                startActivity(intent);
            }
        });
    }
}
