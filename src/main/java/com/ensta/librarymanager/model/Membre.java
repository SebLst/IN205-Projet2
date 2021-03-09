package com.ensta.librarymanager.model;

public class Membre {
    private int primaryKey;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String telephone;
    private abonnementType abonnement;

    private enum abonnementType {
        BASIC, PREMIUM, VIP
    };

    public Membre() {
    }

    public Membre(int primaryKey, String nom, String prenom, String adresse, String email, String telephone,
            abonnementType abonnement) {
        this.primaryKey = primaryKey;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.abonnement = abonnement;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public abonnementType getAbonnement() {
        return abonnement;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAbonnement(abonnementType abonnement) {
        this.abonnement = abonnement;
    }

    public String toString() {
        return Integer.toString(primaryKey) + " " + nom + " " + prenom + " " + adresse + " " + email + " " + telephone
                + " " + abonnement.name();
    }
}
