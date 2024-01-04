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

public class VisitaAdapter extends ArrayAdapter<Visita> {

    private Context context;
    private int resource;
    private List<Visita> visitas;

    public VisitaAdapter(@NonNull Context context, int resource, @NonNull List<Visita> visitas) {
        super(context, resource, visitas);
        this.context = context;
        this.resource = resource;
        this.visitas = visitas;
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
        Visita visita = visitas.get(position);

        // Actualiza los TextViews con la información del vehículo
        TextView nombreTextView = view.findViewById(R.id.visitaTextView);
        TextView rutTextView = view.findViewById(R.id.rutTextView);
        TextView motivoTextView = view.findViewById(R.id.motivoTextView);

        nombreTextView.setText(visita.getNombre());
        rutTextView.setText(visita.getRut());
        motivoTextView.setText(visita.getMotivo());

        return view;
    }
}
