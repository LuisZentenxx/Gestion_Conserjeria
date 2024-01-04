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
import com.example.conserjera.User;

import java.util.List;

public class ResidentesAdapter extends ArrayAdapter<User> {

    private Context context;
    private int resource;
    private List<User> residentUsers;

    public ResidentesAdapter(@NonNull Context context, int resource, @NonNull List<User> residentUsers) {
        super(context, resource, residentUsers);
        this.context = context;
        this.resource = resource;
        this.residentUsers = residentUsers;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(resource, null);
        }

        // Obtén el usuario en la posición actual
        User residentUser = residentUsers.get(position);

        // Actualiza los TextViews con la información del usuario
        TextView nombreTextView = view.findViewById(R.id.nombreTextView);
        TextView correoTextView = view.findViewById(R.id.correoTextView);
        TextView rolTextView = view.findViewById(R.id.rolTextView);

        nombreTextView.setText(residentUser.getUserName());
        correoTextView.setText(residentUser.getEmail());
        rolTextView.setText(residentUser.getRole());

        return view;
    }
}
