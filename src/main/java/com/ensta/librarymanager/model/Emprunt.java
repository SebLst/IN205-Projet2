package com.ensta.librarymanager.model;

import java.text.DateFormat;

public class Emprunt {
    private int primaryKey;
    private int idMembre;
    private int idLivre;
    private DateFormat dateEmprunt;
    private DateFormat dateRetour;

    public Emprunt() {

    }

    public Emprunt(int primaryKey, int idMembre, int idLivre, DateFormat dateEmprunt, DateFormat dateRetour) {
        this.primaryKey = primaryKey;
        this.idMembre = idMembre;
        this.idLivre = idLivre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public int getIdMembre() {
        return idMembre;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public DateFormat getDateEmprunt() {
        return dateEmprunt;
    }

    public DateFormat getDateRetour() {
        return dateRetour;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public void setDateEmprunt(DateFormat dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public void setDateRetour(DateFormat dateRetour) {
        this.dateRetour = dateRetour;
    }

    public String toString() {
        return Integer.toString(primaryKey) + " " + Integer.toString(idMembre) + " " + Integer.toString(idLivre) + " "
                + dateEmprunt.toString() + " " + dateRetour.toString();
    }
}