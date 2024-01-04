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

public class BusquedaVehiculoActivity extends AppCompatActivity {

    private ListView vehiculosListView;
    private List<Vehiculo> vehiculos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_vehiculo_activity);

        vehiculosListView = findViewById(R.id.vehiculoListView);
        vehiculos = new ArrayList<>();

        // Obtén una referencia a tu base de datos
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference vehiculosRef = database.getReference("vehiculos"); // Ajusta la referencia según tu estructura de datos

        // Escucha cambios en la base de datos
        vehiculosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vehiculos.clear(); // Limpiar la lista antes de actualizarla

                for (DataSnapshot vehiculoSnapshot : dataSnapshot.getChildren()) {
                    Vehiculo vehiculo = vehiculoSnapshot.getValue(Vehiculo.class);
                    if (vehiculo != null) {
                        vehiculos.add(vehiculo);
                    }
                }

                // Después de haber procesado los datos, actualiza la interfaz de usuario
                actualizarListaDeVehiculos();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Manejar errores si es necesario
            }
        });
    }

    private void actualizarListaDeVehiculos() {
        // Utiliza el adaptador personalizado para mostrar la lista en el ListView
        VehiculosAdapter adapter = new VehiculosAdapter(this, R.layout.item_vehiculo, vehiculos);
        vehiculosListView.setAdapter(adapter);
    }
}
