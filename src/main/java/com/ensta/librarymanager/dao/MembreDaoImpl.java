package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Membre;
import com.ensta.librarymanager.model.Membre.abonnementType;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class MembreDaoImpl implements MembreDao {
  private static MembreDaoImpl instance;

  private MembreDaoImpl() {
  }

  public static MembreDaoImpl getInstance() {
    if (instance == null) {
      instance = new MembreDaoImpl();
    }
    return instance;
  }

  @Override
  public List<Membre> getList() throws DaoException {
    try {
      Connection connection = ConnectionManager.getConnection();
      String query = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre ORDER BY nom, prenom";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet res = preparedStatement.executeQuery();
      List<Membre> membres = new ArrayList<>();
      while (res.next()) {
        membres.add(new Membre(res.getInt("id"), res.getString("nom"), res.getString("prenom"),
            res.getString("adresse"), res.getString("email"), res.getString("telephone"),
            abonnementType.valueOf(res.getString("abonnement"))));
      }
      preparedStatement.close();
      return membres;

    } catch (Exception e) {
      e.printStackTrace();
      throw new DaoException();
    }
  }

  @Override
  public Membre getById(int id) throws DaoException {
    try {
      Connection connection = ConnectionManager.getConnection();
      String query = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, Integer.toString(id));
      ResultSet res = preparedStatement.executeQuery();
      Membre membre = new Membre();
      while (res.next()) {
        membre.setPrimaryKey(res.getInt("id"));
        membre.setNom(res.getString("nom"));
        membre.setPrenom(res.getString("prenom"));
        membre.setAdresse(res.getString("adresse"));
        membre.setEmail(res.getString("email"));
        membre.setTelephone(res.getString("telephone"));
        membre.setAbonnement(abonnementType.valueOf(res.getString("abonnement")));
      }
      preparedStatement.close();
      return membre;

    } catch (Exception e) {
      e.printStackTrace();
      throw new DaoException();
    }
  }

  @Override
  public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException {
    try {
      Connection connection = ConnectionManager.getConnection();
      String query = "INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?)";
      PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, nom);
      preparedStatement.setString(2, prenom);
      preparedStatement.setString(3, adresse);
      preparedStatement.setString(4, email);
      preparedStatement.setString(5, telephone);
      preparedStatement.setString(6, abonnementType.BASIC.name()); // on créé un nouveau membre avec initialement un
                                                                   // abonnement BASIC
      preparedStatement.executeUpdate();
      ResultSet res = preparedStatement.getGeneratedKeys();
      int id = -1;
      while (res.next()) {
        id = res.getInt(1);
      }
      preparedStatement.close();
      return id;

    } catch (Exception e) {
      e.printStackTrace();
      throw new DaoException();
    }
  }

  @Override
  public void update(Membre membre) throws DaoException {
    try {
      Connection connection = ConnectionManager.getConnection();
      String query = "UPDATE membre SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?, abonnement = ? WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, membre.getNom());
      preparedStatement.setString(2, membre.getPrenom());
      preparedStatement.setString(3, membre.getAdresse());
      preparedStatement.setString(4, membre.getEmail());
      preparedStatement.setString(5, membre.getTelephone());
      preparedStatement.setString(6, membre.getAbonnement().name());
      preparedStatement.setString(7, Integer.toString(membre.getPrimaryKey()));
      preparedStatement.executeUpdate();
      preparedStatement.close();

    } catch (Exception e) {
      e.printStackTrace();
      throw new DaoException();
    }
  }

  @Override
  public void delete(int id) throws DaoException {
    try {
      Connection connection = ConnectionManager.getConnection();
      String query = "DELETE FROM membre WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, Integer.toString(id));
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
      String query = "SELECT COUNT(id) AS count FROM membre";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet res = preparedStatement.executeQuery();
      int count = -1;
      while (res.next()) {
        count = res.getInt(1);
      }
      preparedStatement.close();
      return count;

    } catch (Exception e) {
      e.printStackTrace();
      throw new DaoException();
    }
  }
}
