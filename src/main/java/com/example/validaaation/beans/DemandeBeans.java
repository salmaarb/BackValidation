package com.example.validaaation.beans;

import java.util.Date;

public class DemandeBeans {
    private int id_demande;

    private Date date_debut;

    private Date date_fin;

    public DemandeBeans(int id_demande, Date date_debut, Date date_fin, int id_emp, int id_type, boolean etat) {
        this.id_demande = id_demande;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_emp = id_emp;
        this.id_type = id_type;
        this.etat = etat;
    }

    public int getId_demande() {
        return id_demande;
    }

    public void setId_demande(int id_demande) {
        this.id_demande = id_demande;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public int getId_emp() {
        return id_emp;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    private int id_emp;

    private int  id_type;

    private boolean etat;
}