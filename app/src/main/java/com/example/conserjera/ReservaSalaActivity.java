package com.example.conserjera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class ReservaSalaActivity extends AppCompatActivity {

    private MaterialButton btnSala;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserva_piscina);

        btnSala = findViewById(R.id.btnReservaPiscina);

        btnSala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ReservaSalaActivity.this, "Se ha reservado con éxito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReservaSalaActivity.this, ReservaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
