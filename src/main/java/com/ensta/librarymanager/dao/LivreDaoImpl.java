package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class LivreDaoImpl implements LivreDao {
  private static LivreDaoImpl instance;

  private LivreDaoImpl() {
  }

  public static LivreDaoImpl getInstance() {
    if (instance == null) {
      instance = new LivreDaoImpl();
    }
    return instance;
  }

  @Override
  public List<Livre> getList() throws DaoException {
    try {
      Connection connection = ConnectionManager.getConnection();
      String query = "SELECT id, titre, auteur, isbn FROM livre";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet res = preparedStatement.executeQuery();

      List<Livre> livres = new ArrayList<Livre>();
      while (res.next()) {
        livres.add(new Livre(res.getInt("id"), res.getString("titre"), res.getString("auteur"), res.getString("isbn")));
      }
      preparedStatement.close();
      return livres;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DaoException();
    } catch (Exception e) {
      e.printStackTrace();
      throw new DaoException();
    }
  }

  @Override
  public Livre getById(int id) throws DaoException {
    try {
      Connection connection = ConnectionManager.getConnection();
      String query = "SELECT id, titre, auteur, isbn FROM livre WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, Integer.toString(id));
      ResultSet res = preparedStatement.executeQuery();

      Livre livre = new Livre();
      while (res.next()) {
        livre.setPrimaryKey(res.getInt("id"));
        livre.setTitre(res.getString("titre"));
        livre.setAuteur(res.getString("auteur"));
        livre.setIsbn(res.getString("isbn"));
      }
      preparedStatement.close();
      return livre;

    } catch (SQLException e) {
      e.printStackTrace();
      throw new DaoException();
    } catch (Exception e) {
      e.printStackTrace();
      throw new DaoException();
    }
  }

  @Override
  public int create(String titre, String auteur, String isbn) throws DaoException {
    try {
      Connection connection = ConnectionManager.getConnection();
      String query = "INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?)";
      PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, titre);
      preparedStatement.setString(2, auteur);
      preparedStatement.setString(3, isbn);
      preparedStatement.executeUpdate();
      ResultSet res = preparedStatement.getGeneratedKeys();
      int id = -1;
      if (res.next()) {
        id = res.getInt(1);
      }
      preparedStatement.close();
      return id;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DaoException();
    } catch (Exception e) {
      e.printStackTrace();
      throw new DaoException();
    }
  }

  @Override
  public void update(Livre livre) throws DaoException {
    try {
      Connection connection = ConnectionManager.getConnection();
      String query = "UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, livre.getTitre());
      preparedStatement.setString(2, livre.getAuteur());
      preparedStatement.setString(3, livre.getIsbn());
      preparedStatement.setString(4, Integer.toString(livre.getPrimaryKey()));
      preparedStatement.executeUpdate();
      preparedStatement.close();

    } catch (SQLException e) {
      e.printStackTrace();
      throw new DaoException();
    } catch (Exception e) {
      e.printStackTrace();
      throw new DaoException();
    }

  }

  @Override
  public void delete(int id) throws DaoException {
    try {
      Connection connection = ConnectionManager.getConnection();
      String query = "DELETE FROM livre WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, Integer.toString(id));
      preparedStatement.executeUpdate();
      preparedStatement.close();

    } catch (SQLException e) {
      e.printStackTrace();
      throw new DaoException();
    } catch (Exception e) {
      e.printStackTrace();
      throw new DaoException();
    }

  }

  @Override
  public int count() throws DaoException {
    try {
      Connection connection = ConnectionManager.getConnection();
      String query = "SELECT COUNT(id) AS count FROM livre";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet res = preparedStatement.executeQuery();
      int count = -1;
      while (res.next()) {
        count = res.getInt(1);
      }
      return count;

    } catch (SQLException e) {
      e.printStackTrace();
      throw new DaoException();
    } catch (Exception e) {
      e.printStackTrace();
      throw new DaoException();
    }
  }
}
