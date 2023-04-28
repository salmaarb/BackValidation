package com.example.validaaation.beans;

import java.time.LocalDate;
import java.util.Date;

public class jourBeans {
    private int id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String description;
    public jourBeans(int id,LocalDate dateDebut, LocalDate dateFin, String description) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
    }
    public jourBeans() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}