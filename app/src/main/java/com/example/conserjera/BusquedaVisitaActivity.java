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

public class BusquedaVisitaActivity extends AppCompatActivity {

    private ListView visitasListView;
    private List<Visita> visitas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_visita_activity);

        visitasListView = findViewById(R.id.visitaListView);
        visitas = new ArrayList<>();

        // Obtén una referencia a tu base de datos
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference visitasRef = database.getReference("visitas"); // Ajusta la referencia según tu estructura de datos

        // Escucha cambios en la base de datos
        visitasRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                visitas.clear(); // Limpiar la lista antes de actualizarla

                for (DataSnapshot visitaSnapshot : dataSnapshot.getChildren()) {
                    Visita visita = visitaSnapshot.getValue(Visita.class);
                    if (visita != null) {
                        visitas.add(visita);
                    }
                }

                // Después de haber procesado los datos, actualiza la interfaz de usuario
                actualizarListaDeVisitas();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Manejar errores si es necesario
            }
        });
    }

    private void actualizarListaDeVisitas() {
        // Utiliza el adaptador personalizado para mostrar la lista en el ListView
        VisitaAdapter adapter = new VisitaAdapter(this, R.layout.item_visita, visitas);
        visitasListView.setAdapter(adapter);
    }
}
