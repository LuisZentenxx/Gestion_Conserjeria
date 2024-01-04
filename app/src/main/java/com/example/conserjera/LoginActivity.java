package com.example.conserjera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.conserjera.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.typeEmail);
        passwordEditText = findViewById(R.id.typePasswd);
        loginButton = findViewById(R.id.btnLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, task -> {
                            if (task.isSuccessful()) {
                                // Inicio de sesión exitoso
                                FirebaseUser user = mAuth.getCurrentUser();
                                getUserRoleFromDatabase(user.getUid(), new UserRoleCallback() {
                                    @Override
                                    public void onUserRoleCallback(String userRole) {
                                        if (userRole != null) {
                                            redirectToUserRoleActivity(userRole);
                                        } else {
                                            // Manejar el caso en el que no se pudo obtener el rol del usuario
                                            Toast.makeText(LoginActivity.this, "Error al obtener el rol del usuario", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                // Si falla el inicio de sesión, mostrar un mensaje al usuario.
                                Toast.makeText(LoginActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void getUserRoleFromDatabase(String userId, UserRoleCallback callback) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // El usuario existe en la base de datos
                    String userRole = dataSnapshot.child("role").getValue(String.class);

                    // Llamar al callback con el rol del usuario
                    callback.onUserRoleCallback(userRole);
                } else {
                    // El usuario no existe en la base de datos
                    Toast.makeText(LoginActivity.this, "Usuario no encontrado en la base de datos", Toast.LENGTH_SHORT).show();
                    // Llamar al callback con null para indicar que no se pudo obtener el rol
                    callback.onUserRoleCallback(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar errores de la consulta
                Toast.makeText(LoginActivity.this, "Error en la consulta de la base de datos", Toast.LENGTH_SHORT).show();
                // Llamar al callback con null para indicar que no se pudo obtener el rol
                callback.onUserRoleCallback(null);
            }
        });
    }

    private void redirectToUserRoleActivity(String userRole) {
        Intent intent;
        switch (userRole) {
            case "Admin":
                intent = new Intent(LoginActivity.this, AdminViewActivity.class);
                break;
            case "Conserje":
                intent = new Intent(LoginActivity.this, ConserjeViewActivity.class);
                break;
            case "Residente":
                intent = new Intent(LoginActivity.this, ResidenteViewActivity.class);
                break;
            default:
                // Manejar el caso por defecto o un rol desconocido
                return;
        }
        startActivity(intent);
        finish(); // Cerrar la actividad actual para que el usuario no pueda volver atrás
    }
}