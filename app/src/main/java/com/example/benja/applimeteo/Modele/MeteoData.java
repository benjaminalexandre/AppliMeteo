package com.example.benja.applimeteo.Modele;

import java.util.ArrayList;

/**
 * Created by benja on 26/03/2017.
 */

public class MeteoData {
    private static MeteoData ourInstance = new MeteoData();

    public static MeteoData getInstance() {
        return ourInstance;
    }

    private ArrayList<City> listeVille;

    private MeteoData() {
        listeVille = new ArrayList<>();
    }

    public ArrayList<City> getListeVille() {
        return listeVille;
    }

    public void setListeVille(ArrayList<City> listeVille) {
        this.listeVille = listeVille;
    }

    public void addVille(City ville) {
        this.listeVille.add(ville);
    }
}
