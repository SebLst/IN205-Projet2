package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.model.Membre;
import com.ensta.librarymanager.model.Membre.abonnementType;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class EmpruntDaoImpl implements EmpruntDao {
  private static EmpruntDaoImpl instance;

  private EmpruntDaoImpl() {
  }

  public static EmpruntDaoImpl getInstance() {
    if (instance == null) {
      instance = new EmpruntDaoImpl();
    }
    return instance;
  }

  @Override
  public List<Emprunt> getList() throws DaoException {
    try {
      Connection connection = ConnectionManager.getConnection();
      String query = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre ORDER BY dateRetour DESC;";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet res = preparedStatement.executeQuery();
      List<Emprunt> emprunts = new ArrayList<>();
      while (res.next()) {
        Emprunt emprunt = new Emprunt();
        Membre membre = new Membre(res.getInt("idMembre"), res.getString("nom"), res.getString("prenom"),
            res.getString("adresse"), res.getString("email"), res.getString("telephone"),
            abonnementType.valueOf(res.getString("abonnement")));
        Livre livre = new Livre(res.getInt("idLivre"), res.getString("titre"), res.getString("auteur"),
            res.getString("isbn"));
        emprunt.setMembre(membre);
        emprunt.setLivre(livre);
        emprunt.setPrimaryKey(res.getInt("id"));
        emprunt.setDateEmprunt(res.getDate("dateEmprunt").toLocalDate());
        if (res.getString("dateRetour") == "NULL") {
          emprunt.setDateRetour(null);
        }
        emprunts.add(emprunt);
      }
      preparedStatement.close();
      return emprunts;

    } catch (Exception e) {
      e.printStackTrace();
      throw new DaoException();
    }
  }

  @Override
  public List<Emprunt> getListCurrent() throws DaoException {
    try {
      Connection connection = ConnectionManager.getConnection();
      String query = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet res = preparedStatement.executeQuery();
      List<Emprunt> emprunts = new ArrayList<>();
      while (res.next()) {
        Emprunt emprunt = new Emprunt();
        Membre membre = new Membre(res.getInt("idMembre"), res.getString("nom"), res.getString("prenom"),
            res.getString("adresse"), res.getString("email"), res.getString("telephone"),
            abonnementType.valueOf(res.getString("abonnement")));
        Livre livre = new Livre(res.getInt("idLivre"), res.getString("titre"), res.getString("auteur"),
            res.getString("isbn"));
        emprunt.setMembre(membre);
        emprunt.setLivre(livre);
        emprunt.setPrimaryKey(res.getInt("id"));
        emprunt.setDateEmprunt(res.getDate("dateEmprunt").toLocalDate());
        if (res.getString("dateRetour") == "NULL") {
          emprunt.setDateRetour(null);
        }
        emprunts.add(emprunt);
      }
      preparedStatement.close();
      return emprunts;

    } catch (Exception e) {
      e.printStackTrace();
      throw new DaoException();
    }
  }

  @Override
  public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
    try {
      Connection connection = ConnectionManager.getConnection();
      String query = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND membre.id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, Integer.toString(idMembre));
      ResultSet res = preparedStatement.executeQuery();
      List<Emprunt> emprunts = new ArrayList<>();
      while (res.next()) {
        Emprunt emprunt = new Emprunt();
        Membre membre = new Membre(res.getInt("idMembre"), res.getString("nom"), res.getString("prenom"),
            res.getString("adresse"), res.getString("email"), res.getString("telephone"),
            abonnementType.valueOf(res.getString("abonnement")));
        Livre livre = new Livre(res.getInt("idLivre"), res.getString("titre"), res.getString("auteur"),
            res.getString("isbn"));
        emprunt.setMembre(membre);
        emprunt.setLivre(livre);
        emprunt.setPrimaryKey(res.getInt("id"));
        emprunt.setDateEmprunt(res.getDate("dateEmprunt").toLocalDate());
        if (res.getString("dateRetour") == "NULL") {
          emprunt.setDateRetour(null);
        }
        emprunts.add(emprunt);
      }
      preparedStatement.close();
      return emprunts;

    } catch (Exception e) {
      e.printStackTrace();
      throw new DaoException();
    }
  }

  @Override
  public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
    try {
      Connection connection = ConnectionManager.getConnection();
      String query = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND livre.id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, Integer.toString(idLivre));
      ResultSet res = preparedStatement.executeQuery();
      List<Emprunt> emprunts = new ArrayList<>();
      while (res.next()) {
        Emprunt emprunt = new Emprunt();
        Membre membre = new Membre(res.getInt("idMembre"), res.getString("nom"), res.getString("prenom"),
            res.getString("adresse"), res.getString("email"), res.getString("telephone"),
            abonnementType.valueOf(res.getString("abonnement")));
        Livre livre = new Livre(res.getInt("idLivre"), res.getString("titre"), res.getString("auteur"),
            res.getString("isbn"));
        emprunt.setMembre(membre);
        emprunt.setLivre(livre);
        emprunt.setPrimaryKey(res.getInt("id"));
        emprunt.setDateEmprunt(res.getDate("dateEmprunt").toLocalDate());
        if (res.getString("dateRetour") == "NULL") {
          emprunt.setDateRetour(null);
        }
        emprunts.add(emprunt);
      }
      preparedStatement.close();
      return emprunts;

    } catch (Exception e) {
      e.printStackTrace();
      throw new DaoException();
    }
  }

  @Override
  public Emprunt getById(int id) throws DaoException {
    try {
      Connection connection = ConnectionManager.getConnection();
      String query = "SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE e.id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, Integer.toString(1));
      ResultSet res = preparedStatement.executeQuery();
      Emprunt emprunt = new Emprunt();
      while (res.next()) {
        Membre membre = new Membre(res.getInt("idMembre"), res.getString("nom"), res.getString("prenom"),
            res.getString("adresse"), res.getString("email"), res.getString("telephone"),
            abonnementType.valueOf(res.getString("abonnement")));
        Livre livre = new Livre(res.getInt("idLivre"), res.getString("titre"), res.getString("auteur"),
            res.getString("isbn"));
        emprunt.setMembre(membre);
        emprunt.setLivre(livre);
        emprunt.setPrimaryKey(res.getInt("id"));
        emprunt.setDateEmprunt(res.getDate("dateEmprunt").toLocalDate());
        if (res.getString("dateRetour") == "NULL") {
          emprunt.setDateRetour(null);
        }
      }
      preparedStatement.close();
      return emprunt;

    } catch (Exception e) {
      e.printStackTrace();
      throw new DaoException();
    }
  }

  @Override
  public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
    try {
      Connection connection = ConnectionManager.getConnection();
      String query = "INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES (?, ?, ?, ?)";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, Integer.toString(idMembre));
      preparedStatement.setString(2, Integer.toString(idLivre));
      preparedStatement.setString(3, dateEmprunt.toString());
      preparedStatement.setString(4, null); // date de retour égale à NULL initialement
      preparedStatement.executeUpdate();
      preparedStatement.close();

    } catch (Exception e) {
      e.printStackTrace();
      throw new DaoException();
    }

  }

  @Override
  public void update(Emprunt emprunt) throws DaoException {
    try {
      Connection connection = ConnectionManager.getConnection();
      String query = "UPDATE emprunt SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ? WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, Integer.toString(emprunt.getMembre().getPrimaryKey()));
      preparedStatement.setString(2, Integer.toString(emprunt.getLivre().getPrimaryKey()));
      preparedStatement.setString(3, emprunt.getDateEmprunt().toString());
      preparedStatement.setString(4, emprunt.getDateRetour().toString());
      preparedStatement.setString(5, Integer.toString(emprunt.getPrimaryKey()));
      preparedStatement.executeUpdate();
      preparedStatement.close();

    } catch (Exception e) {
      e.printStackTrace();
      throw new DaoException();
    }

  }

  @Override
  public int count() throws DaoException {
    try {
      Connection connection = ConnectionManager.getConnection();
      String query = "SELECT COUNT(id) AS count FROM emprunt";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet res = preparedStatement.executeQuery();
      int count = -1;
      while (res.next()) {
        count = res.getInt("count");
      }
      preparedStatement.close();
      return count;

    } catch (Exception e) {
      e.printStackTrace();
      throw new DaoException();
    }
  }
}
