package com.example.conserjera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.conserjera.R;

import java.util.List;

public class VehiculosAdapter extends ArrayAdapter<Vehiculo> {

    private Context context;
    private int resource;
    private List<Vehiculo> vehiculos;  // Cambiado el nombre de residentUsers a vehiculos

    public VehiculosAdapter(@NonNull Context context, int resource, @NonNull List<Vehiculo> vehiculos) {
        super(context, resource, vehiculos);
        this.context = context;
        this.resource = resource;
        this.vehiculos = vehiculos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(resource, null);
        }

        // Obtén el vehículo en la posición actual
        Vehiculo vehiculo = vehiculos.get(position);

        // Actualiza los TextViews con la información del vehículo
        TextView patenteTextView = view.findViewById(R.id.patenteTextView);
        TextView marcaTextView = view.findViewById(R.id.marcaTextView);
        TextView colorTextView = view.findViewById(R.id.colorTextView);

        patenteTextView.setText(vehiculo.getPatente());
        marcaTextView.setText(vehiculo.getMarca());
        colorTextView.setText(vehiculo.getColor());

        return view;
    }
}
