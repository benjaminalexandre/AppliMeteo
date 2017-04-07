package com.example.benja.applimeteo.Modele;

import java.io.Serializable;

/**
 * Created by benja on 26/03/2017.
 */

public enum PeriodeID implements Serializable {
    MATIN("matin"),
    MIDI("midi"),
    APRES_MIDI("apmidi"),
    SOIR("soir");

    String id;

    PeriodeID(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return id;
    }
}
