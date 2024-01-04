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

public class BusquedaEncomiendaActivity extends AppCompatActivity {

    private ListView encomiendasListView;
    private List<Encomienda> encomiendas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_encomienda_activity);

        encomiendasListView = findViewById(R.id.encomiendasListView);
        encomiendas = new ArrayList<>();

        // Obtén una referencia a tu base de datos
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("encomiendas"); // Ajusta la referencia según tu estructura de datos

        // Escucha cambios en la base de datos
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                encomiendas.clear(); // Limpiar la lista antes de actualizarla

                for (DataSnapshot encomiendaSnapshot : dataSnapshot.getChildren()) {
                    Encomienda encomienda = encomiendaSnapshot.getValue(Encomienda.class);
                    if (encomienda != null) {
                        encomiendas.add(encomienda);
                    }
                }


                // Después de haber procesado los datos, actualiza la interfaz de usuario
                actualizarListaDeEncomiendas();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Manejar errores si es necesario
            }
        });
    }

    private void actualizarListaDeEncomiendas() {
        // Utiliza el adaptador personalizado para mostrar la lista en el ListView
        EncomiendaAdapter adapter = new EncomiendaAdapter(this, R.layout.item_encomienda, encomiendas);
        encomiendasListView.setAdapter(adapter);
    }

}
