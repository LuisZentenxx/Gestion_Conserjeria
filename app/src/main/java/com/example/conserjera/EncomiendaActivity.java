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

public class EncomiendaActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encomienda_activity);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Inicializar Firebase Realtime Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("encomiendas");

        TextInputLayout txtRemitenteLayout = findViewById(R.id.remitenteLayout);
        TextInputEditText txtRemitente = txtRemitenteLayout.findViewById(R.id.txtRemitente);

        TextInputLayout txtDestinatarioLayout = findViewById(R.id.DestinatarioLayout);
        TextInputEditText txtDestinatario = txtDestinatarioLayout.findViewById(R.id.txtDestinatario);

        TextInputLayout txtEncomiendaLayout = findViewById(R.id.EncomiendaLayout);
        TextInputEditText txtEncomienda = txtEncomiendaLayout.findViewById(R.id.txtEncomienda);

        TextInputLayout txtDeptoLayout = findViewById(R.id.DeptoLayout);
        TextInputEditText txtDepto = txtDeptoLayout.findViewById(R.id.txtDepto);

        TextInputLayout txtFechaLayout = findViewById(R.id.FechaLayout);
        TextInputEditText txtFecha = txtFechaLayout.findViewById(R.id.txtFecha);

        MaterialButton btnEncomienda = findViewById(R.id.btnEncomienda);

        btnEncomienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String remitente = txtRemitente.getText().toString().trim();
                String destinatario = txtDestinatario.getText().toString().trim();
                String encomienda = txtEncomienda.getText().toString().trim();
                String depto = txtDepto.getText().toString().trim();
                String fecha = txtFecha.getText().toString().trim();

                // Crear un mapa con los datos del vehículo
                Map<String, Object> encomiendasMap = new HashMap<>();
                encomiendasMap.put("remitente", remitente);
                encomiendasMap.put("destinatario", destinatario);
                encomiendasMap.put("encomienda", encomienda);
                encomiendasMap.put("depto", depto);
                encomiendasMap.put("fecha", fecha);

                // Registrar los datos de la visita en Firebase Realtime Database
                mDatabase.push().setValue(encomiendasMap);

                // Mostrar un mensaje de éxito
                Toast.makeText(EncomiendaActivity.this, "Encomienda registrada exitosamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EncomiendaActivity.this, ConserjeViewActivity.class);

                // Limpiar los campos después de registrar la visita
                txtRemitente.getText().clear();
                txtDestinatario.getText().clear();
                txtEncomienda.getText().clear();
                txtDepto.getText().clear();
                txtFecha.getText().clear();

                startActivity(intent);
                finish();

            }
        });

    }
}
