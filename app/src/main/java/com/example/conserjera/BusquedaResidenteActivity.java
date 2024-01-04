package com.example.conserjera;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BusquedaResidenteActivity extends AppCompatActivity {

    private ListView residentesListView;
    private List<User> residentUsers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_residente_activity);

        residentesListView = findViewById(R.id.residentesListView);
        residentUsers = new ArrayList<>();

        // Obtén una referencia a tu base de datos
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users"); // Ajusta la referencia según tu estructura de datos

        // Escucha cambios en la base de datos
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                residentUsers.clear(); // Limpiar la lista antes de actualizarla

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    if (user != null && "Residente".equals(user.getRole())) {
                        residentUsers.add(user);
                    }
                }

                // Después de haber procesado los datos, actualiza la interfaz de usuario
                actualizarListaDeResidentes();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Manejar errores si es necesario
            }
        });
    }

    private void actualizarListaDeResidentes() {
        // Utiliza el adaptador personalizado para mostrar la lista en el ListView
        ResidentesAdapter adapter = new ResidentesAdapter(this, R.layout.item_residente, residentUsers);
        residentesListView.setAdapter(adapter);
    }

}
