package com.example.appli20240829;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PanierAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> films;

    public PanierAdapter(@NonNull Context context, @NonNull List<String> films) {
        super(context, 0, films);
        this.context = context;
        this.films = films;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_panier, parent, false);
        }

        String film = films.get(position);

        TextView textFilm = itemView.findViewById(R.id.textFilm);
        Button btnSupprimer = itemView.findViewById(R.id.btnSupprimer);

        textFilm.setText(film);

        btnSupprimer.setOnClickListener(v -> {
            PanierManager.getInstance().retirerFilmDuPanier(film);
            notifyDataSetChanged();
        });

        return itemView;
    }
}
