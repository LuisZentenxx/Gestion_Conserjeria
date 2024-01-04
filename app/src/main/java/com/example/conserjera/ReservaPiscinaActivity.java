package com.example.conserjera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class ReservaPiscinaActivity extends AppCompatActivity {

    private MaterialButton btnPiscina;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserva_piscina);

        btnPiscina = findViewById(R.id.btnReservaPiscina);

        btnPiscina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ReservaPiscinaActivity.this, "Se ha reservado con éxito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReservaPiscinaActivity.this, ReservaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
