package com.example.conserjera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResidenteViewActivity extends AppCompatActivity {

    private TextView txtMiNombre;
    private Button btnVerDatos, btnReserva;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.residente_view_activity);

        btnVerDatos = findViewById(R.id.btnDatos);
        btnReserva = findViewById(R.id.btnReserva);

        // Inicializar las vistas
        txtMiNombre = findViewById(R.id.txtNombreUsuario);

        // Obtener el usuario actual
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            // Obtener la referencia del usuario desde la base de datos
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getUid());

            // Agregar un listener para escuchar los cambios en los datos del usuario
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // El usuario existe en la base de datos
                        User user = dataSnapshot.getValue(User.class);

                        // Mostrar los datos del usuario en las vistas
                        if (user != null) {
                            txtMiNombre.setText("Bienvenido " + user.getUserName());
                        }
                    } else {
                        // El usuario no existe en la base de datos
                        Toast.makeText(ResidenteViewActivity.this, "Usuario no encontrado en la base de datos", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Manejar cancelación (opcional)
                }
            });  // ¡Aquí falta cerrar el paréntesis y la llave!

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
}
