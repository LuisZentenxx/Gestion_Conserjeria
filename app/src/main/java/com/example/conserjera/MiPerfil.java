package com.example.conserjera;

import android.os.Bundle;
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

public class MiPerfil extends AppCompatActivity {

    private TextView txtMiNombre;
    private TextView txtMiCorreo;
    private TextView txtMiRol;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mi_perfil_activity);

        // Inicializar las vistas
        txtMiNombre = findViewById(R.id.txtMiNombre);
        txtMiCorreo = findViewById(R.id.txtMiCorreo);
        txtMiRol = findViewById(R.id.txtMiRol);

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
                            txtMiNombre.setText("Nombre \n" + user.getUserName());
                            txtMiCorreo.setText("Correo electrónico \n" + user.getEmail());
                            txtMiRol.setText("Rol \n" + user.getRole());
                        }
                    } else {
                        // El usuario no existe en la base de datos
                        Toast.makeText(MiPerfil.this, "Usuario no encontrado en la base de datos", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Manejar errores de la consulta
                    Toast.makeText(MiPerfil.this, "Error en la consulta de la base de datos", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // El usuario actual es nulo
            Toast.makeText(this, "No se ha iniciado sesión", Toast.LENGTH_SHORT).show();
        }
    }
}
