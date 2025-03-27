package com.example.appli20240829;

import java.util.ArrayList;
import java.util.List;

public class PanierManager {
    private static PanierManager instance;
    private List<String> filmsDansPanier;

    private PanierManager() {
        filmsDansPanier = new ArrayList<>();
    }

    public static PanierManager getInstance() {
        if (instance == null) {
            instance = new PanierManager();
        }
        return instance;
    }

    public void ajouterAuPanier(String filmDetails) {
        filmsDansPanier.add(filmDetails);
    }

    public List<String> getFilmsDansPanier() {
        return filmsDansPanier;
    }

    public void viderPanier() {
        filmsDansPanier.clear();
    }
}