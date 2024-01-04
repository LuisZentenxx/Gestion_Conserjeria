package com.example.conserjera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class BusquedaActivity extends AppCompatActivity {

    private MaterialButton btnEncomiendas, btnResidentes, btnVisitas, btnVehiculos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_activity);

        btnResidentes = findViewById(R.id.btnResidentes);
        btnEncomiendas = findViewById(R.id.btnEncomiendas);
        btnVisitas = findViewById(R.id.btnVisitas);
        btnVehiculos = findViewById(R.id.btnVehiculos);

        btnResidentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BusquedaActivity.this, BusquedaResidenteActivity.class);
                startActivity(intent);
            }
        });

        btnEncomiendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BusquedaActivity.this, BusquedaEncomiendaActivity.class);
                startActivity(intent);
            }
        });

        btnVisitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BusquedaActivity.this, BusquedaVisitaActivity.class);
                startActivity(intent);
            }
        });

        btnVehiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BusquedaActivity.this, BusquedaVehiculoActivity.class);
                startActivity(intent);
            }
        });
    }
}
