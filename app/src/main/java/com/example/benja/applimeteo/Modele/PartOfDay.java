package com.example.benja.applimeteo.Modele;

/**
 * Created by benja on 26/03/2017.
 */

import java.io.Serializable;

/**
 * Classe PartOfDay.
 * Contient les informations météorologiques de la période.
 */

public class PartOfDay implements Serializable{
    PeriodeID periodeID;
    String temp,precipitation,vent, seeing, pictoId;


    public PartOfDay(PeriodeID periodeId, String temp, String precipitation, String vent, String seeing, String pictoId) {
        this.periodeID = periodeId;
        this.temp = temp;
        this.precipitation = precipitation;
        this.vent = vent;
        this.seeing = seeing;
        this.pictoId = pictoId;
    }

    public PartOfDay() {

    }

    public PeriodeID getPeriodeId() {
        return periodeID;
    }

    public void setPeriodeId(PeriodeID periodeId) {
        this.periodeID = periodeId;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getVent() {
        return vent;
    }

    public void setVent(String vent) {
        this.vent = vent;
    }

    public String getSeeing() {
        return seeing;
    }

    public void setSeeing(String seeing) {
        this.seeing = seeing;
    }

    public String getPictoId() {
        return pictoId;
    }

    public void setPictoId(String pictoId) {
        this.pictoId = pictoId;
    }


}
