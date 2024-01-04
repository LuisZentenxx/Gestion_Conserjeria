package com.example.conserjera;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ConserjeViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conserje_view_activity);

        Button btnVisitas = findViewById(R.id.GestionBtn);
        Button btnVehiculos = findViewById(R.id.ControlBtn);
        Button btnEncomienda = findViewById(R.id.EncomiendaBtn);
        Button btnBusqueda = findViewById(R.id.SearchBtn);

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



    }

}
