package com.example.benja.applimeteo.Modele;

/**
 * Created by benja on 26/03/2017.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Classe Prévision
 * Correspond à une prévision météorologique pour un jour.
 * Contient un string de jour, un string de description et un array de période.
 * Il y a quatres périodes possible : {@link PeriodeID}
 */

public class Prevision implements Serializable {
    String jour;
    String description;
    ArrayList<PartOfDay> arrayList;

    public Prevision(String jour, String description, ArrayList<PartOfDay> arrayList) {
        this.jour = jour;
        this.description = description;
        this.arrayList = arrayList;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<PartOfDay> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<PartOfDay> arrayList) {
        this.arrayList = arrayList;
    }

    public PartOfDay getCurrentPeriode() {
        Calendar rightNow = Calendar.getInstance();
        int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);

        PartOfDay toReturn = null;

        if (currentHour > 0 && currentHour < 12) {
            toReturn = getPeriodeFromID(PeriodeID.MATIN);
        } else if (currentHour == 12) {
            toReturn = getPeriodeFromID(PeriodeID.MIDI);
        } else if (currentHour > 12 && currentHour < 18) {
            toReturn = getPeriodeFromID(PeriodeID.APRES_MIDI);
        } else {
            toReturn = getPeriodeFromID(PeriodeID.SOIR);
        }

        return toReturn == null ? arrayList.get(0) : toReturn;
    }

    public PartOfDay getPeriodeFromID(PeriodeID periodeId) {
        for (PartOfDay p : arrayList) {
            if (p.getPeriodeId() == periodeId) {
                return p;
            }
        }
        return null;
    }
}
