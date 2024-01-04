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

public class EncomiendaAdapter extends ArrayAdapter<Encomienda> {

    private Context context;
    private int resource;
    private List<Encomienda> encomiendas;  // Cambiado el nombre de residentUsers a vehiculos

    public EncomiendaAdapter(@NonNull Context context, int resource, @NonNull List<Encomienda> encomiendas) {
        super(context, resource, encomiendas);
        this.context = context;
        this.resource = resource;
        this.encomiendas = encomiendas;
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
        Encomienda encomienda = encomiendas.get(position);

        // Actualiza los TextViews con la información del vehículo
        TextView remitenteTextView = view.findViewById(R.id.remitenteTextView);
        TextView destinatarioTextView = view.findViewById(R.id.destinatarioTextView);
        TextView encomiendaTextView = view.findViewById(R.id.encomiendaTextView);
        TextView deptoTextView = view.findViewById(R.id.deptoTextView);
        TextView dateTextView = view.findViewById(R.id.dateTextView);

        remitenteTextView.setText(encomienda.getRemitente());
        destinatarioTextView.setText(encomienda.getDestinatario());
        encomiendaTextView.setText(encomienda.getEncomienda());
        deptoTextView.setText(encomienda.getDepartamento());
        dateTextView.setText(encomienda.getFecha());

        return view;
    }
}
