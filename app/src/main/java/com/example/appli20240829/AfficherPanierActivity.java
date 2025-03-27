package com.example.appli20240829;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class AfficherPanierActivity extends AppCompatActivity {
    private ListView listePanierView;
    private TextView panierVideText;
    private Button btnValiderPanier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_panier);

        Log.d("PanierDebug", "AfficherPanierActivity onCreate");

        listePanierView = findViewById(R.id.listePanier);
        panierVideText = findViewById(R.id.panierVideText);
        btnValiderPanier = findViewById(R.id.btnValiderPanier);

        afficherFilmsDansPanier();

        btnValiderPanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validerPanierFinal();
            }
        });
    }

    private void afficherFilmsDansPanier() {
        List<String> filmsDansPanier = PanierManager.getInstance().getFilmsDansPanier();

        if (filmsDansPanier.isEmpty()) {
            listePanierView.setVisibility(View.GONE);
            panierVideText.setVisibility(View.VISIBLE);
            btnValiderPanier.setVisibility(View.GONE);
        } else {
            listePanierView.setVisibility(View.VISIBLE);
            panierVideText.setVisibility(View.GONE);
            btnValiderPanier.setVisibility(View.VISIBLE);

            Log.d("PanierDebug", "Nombre de films dans le panier : " + filmsDansPanier.size());
            Log.d("PanierDebug", "Contenu du panier : " + filmsDansPanier.toString());

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, filmsDansPanier);
            listePanierView.setAdapter(adapter);
        }
    }

    private void validerPanierFinal() {
        Log.d("PanierDebug", "Validation du panier déclenchée");

        // Récupérer le panier avant la validation
        List<String> panierAvantValidation = PanierManager.getInstance().getFilmsDansPanier();
        Log.d("PanierDebug", "Panier avant validation : " + panierAvantValidation.toString());

        // Implémentez votre logique de validation finale ici
        // Par exemple, vérifiez les stocks, calculez le prix total, etc.
        // Puis passez à l'étape suivante (par exemple, le paiement)
        Toast.makeText(this, "Panier validé !", Toast.LENGTH_SHORT).show();

        // Récupérer le panier après la validation
        List<String> panierApresValidation = PanierManager.getInstance().getFilmsDansPanier();
        Log.d("PanierDebug", "Panier après validation : " + panierApresValidation.toString());

        // Vérifier si le panier a été modifié après la validation
        if (panierAvantValidation.equals(panierApresValidation)) {
            Log.d("PanierDebug", "La validation n'a pas modifié le panier.");
        } else {
            Log.d("PanierDebug", "La validation a modifié le panier.");
        }

        // Vérifier si le panier est vide après la validation (si c'est le comportement attendu)
        if (panierApresValidation.isEmpty()) {
            Log.d("PanierDebug", "Le panier est vide après la validation.");
        } else {
            Log.d("PanierDebug", "Le panier n'est pas vide après la validation.");
        }

        // Vous pouvez ajouter ici le code pour passer à l'activité de paiement ou autre
    }
}