package com.example.conserjera;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ConserjeViewActivity extends AppCompatActivity {

    private TextView btnClose;
    private Button btnVisitas;
    private Button btnVehiculos;
    private Button btnEncomienda;
    private Button btnBusqueda;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conserje_view_activity);

         btnVisitas = findViewById(R.id.GestionBtn);
         btnVehiculos = findViewById(R.id.ControlBtn);
         btnEncomienda = findViewById(R.id.EncomiendaBtn);
         btnBusqueda = findViewById(R.id.SearchBtn);
         btnClose = findViewById(R.id.closeSesion);

        btnVisitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConserjeViewActivity.this, GestionVisitasActivity.class);
                startActivity(intent);
            }
        });

        btnVehiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConserjeViewActivity.this, ControlVehiculosActivity.class);
                startActivity(intent);
            }
        });

        btnBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConserjeViewActivity.this, BusquedaActivity.class);
                startActivity(intent);
            }
        });

        btnEncomienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConserjeViewActivity.this, EncomiendaActivity.class);
                startActivity(intent);
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mostrar Toast antes de cerrar sesión
                Toast.makeText(ConserjeViewActivity.this, "Cerrando sesión...", Toast.LENGTH_SHORT).show();

                // Cerrar sesión en Firebase
                FirebaseAuth.getInstance().signOut();

                // Redirigir a la actividad de inicio de sesión
                Intent intent = new Intent(ConserjeViewActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });








    }

}
