package com.ensta.librarymanager.model;

import java.time.LocalDate;

public class Emprunt {
  private int primaryKey;
  private Membre membre;
  private Livre livre;
  private LocalDate dateEmprunt;
  private LocalDate dateRetour;

  public Emprunt() {
  }

  public Emprunt(int primaryKey, Membre membre, Livre livre, LocalDate dateEmprunt, LocalDate dateRetour) {
    this.primaryKey = primaryKey;
    this.membre = membre;
    this.livre = livre;
    this.dateEmprunt = dateEmprunt;
    this.dateRetour = dateRetour;
  }

  public int getPrimaryKey() {
    return primaryKey;
  }

  public Membre getMembre() {
    return membre;
  }

  public Livre getLivre() {
    return livre;
  }

  public LocalDate getDateEmprunt() {
    return dateEmprunt;
  }

  public LocalDate getDateRetour() {
    return dateRetour;
  }

  public void setPrimaryKey(int primaryKey) {
    this.primaryKey = primaryKey;
  }

  public void setMembre(Membre membre) {
    this.membre = membre;
  }

  public void setLivre(Livre livre) {
    this.livre = livre;
  }

  public void setDateEmprunt(LocalDate dateEmprunt) {
    this.dateEmprunt = dateEmprunt;
  }

  public void setDateRetour(LocalDate dateRetour) {
    this.dateRetour = dateRetour;
  }

  public String toString() {
    String words = Integer.toString(primaryKey) + " " + membre.toString() + " " + livre.toString() + " "
        + dateEmprunt.toString() + " ";
    if (dateRetour != null) {
      words += dateRetour.toString();
    }
    return words;
  }
}