package com.example.benja.applimeteo.Modele;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by benja on 26/03/2017.
 */

/**
 * Classe City
 * Possède un nom, un id qui correspond au nombre en fin d'URL du web service utilisé dans le projet, et une liste de prévisions, une par jour
 */

public class City implements Serializable {
    ArrayList<Prevision> listePrevision;
    String nomVille;
    String id;


    public City(String nomVille, String id) {
        this.nomVille = nomVille;
        this.id = id;
        listePrevision = new ArrayList<>();
    }

    public ArrayList<Prevision> getListePrevision() {
        return listePrevision;
    }

    public void setListePrevision(ArrayList<Prevision> listePrevision) {
        this.listePrevision = listePrevision;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addPrevision(Prevision prevision) {
        this.listePrevision.add(prevision);
    }
}
