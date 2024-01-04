package com.example.conserjera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText userNameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private RadioGroup radioGroupRoles;
    private Button registerButton;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Inicializar Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");

        userNameEditText = findViewById(R.id.user);
        emailEditText = findViewById(R.id.emailUser);
        passwordEditText = findViewById(R.id.passwUser);
        confirmPasswordEditText = findViewById(R.id.passw2);
        radioGroupRoles = findViewById(R.id.radioGroupRoles);
        registerButton = findViewById(R.id.btnRegister);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener valores del formulario
                String userName = userNameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();

                // Validar que las contraseñas coincidan
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Obtener el ID del RadioButton seleccionado
                int selectedRadioButtonId = radioGroupRoles.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                String selectedRole = selectedRadioButton.getText().toString();

                // Crear usuario en Firebase Auth
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, task -> {
                            if (task.isSuccessful()) {
                                // Registro exitoso, ahora guardamos el rol en la base de datos
                                FirebaseUser user = mAuth.getCurrentUser();
                                saveUserRole(user.getUid(), userName, email, selectedRole);

                                // Redirigir a la actividad de inicio de sesión
                                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(loginIntent);
                                finish(); // Cerrar la actividad actual para que el usuario no pueda volver atrás

                                // Mostrar un Toast de éxito
                                Toast.makeText(RegisterActivity.this, "Usuario " + selectedRole + " creado con éxito", Toast.LENGTH_SHORT).show();
                            } else {
                                // Si falla el registro, mostrar un mensaje al usuario.
                                Toast.makeText(RegisterActivity.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void saveUserRole(String userId, String userName, String email, String role) {
        // Guardar información del usuario en la base de datos
        User newUser = new User(userId, userName, email, role);
        mDatabase.child(userId).setValue(newUser);

    }
}
