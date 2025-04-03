package com.example.appli20240829;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

public class AfficherDetailDvdsActivity extends AppCompatActivity {

    private TextView detailsTextView;
    private Button ajouterPanierButton;
    private Button voirPanierButton;
    private String dvdDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        // Initialiser les vues
        initialiserVues();

        // Récupérer et afficher les données
        recupererEtAfficherDonnees();

        // Configurer les boutons
        configurerBoutons();

        // Dans onCreate de AfficherDetailDvdsActivity
        voirPanierButton = findViewById(R.id.voirPanierButton);
        if (voirPanierButton == null) {
            Log.e("PanierDebug", "Le bouton voirPanierButton est null");
        } else {
            Log.d("PanierDebug", "Le bouton voirPanierButton est bien initialisé");
        }
    }

    private void initialiserVues() {
        detailsTextView = findViewById(R.id.detailsTextView);
        ajouterPanierButton = findViewById(R.id.ajouterPanierButton);
        voirPanierButton = findViewById(R.id.voirPanierButton);
    }

    private void recupererEtAfficherDonnees() {
        dvdDetails = getIntent().getStringExtra("DVD_DETAILS");
        if (dvdDetails != null) {
            detailsTextView.setText(dvdDetails);
        }
    }

    private void configurerBoutons() {
        // Configuration du bouton Ajouter au panier
        ajouterPanierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterAuPanier();
            }
        });

        // Configuration du bouton Voir Panier
        voirPanierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PanierDebug", "Bouton Voir Panier cliqué");
                ouvrirPanier();
            }
        });
    }

    private void ajouterAuPanier() {
        if (dvdDetails != null) {
            PanierManager.getInstance().ajouterAuPanier(dvdDetails);
            Toast.makeText(this, "Ajouté au panier", Toast.LENGTH_SHORT).show();
        }
    }

    // Dans AfficherDetailDvdsActivity
    private void ouvrirPanier() {
        Log.d("PanierDebug", "Tentative d'ouverture du panier");
        try {
            Intent intent = new Intent(AfficherDetailDvdsActivity.this, AfficherPanierActivity.class);
            startActivity(intent);
            Log.d("PanierDebug", "Intent lancé avec succès");
        } catch (Exception e) {
            Log.e("PanierDebug", "Erreur lors de l'ouverture du panier", e);
        }
    }
}
