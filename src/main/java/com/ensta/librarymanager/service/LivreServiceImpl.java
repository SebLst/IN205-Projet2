package com.ensta.librarymanager.service;

import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.LivreDaoImpl;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Livre;

public class LivreServiceImpl implements LivreService {
  private static LivreServiceImpl instance;

  private LivreServiceImpl() {
  }

  public static LivreServiceImpl getInstance() {
    if (instance == null) {
      instance = new LivreServiceImpl();
    }
    return instance;
  }

  @Override
  public List<Livre> getList() throws ServiceException {
    try {
      return LivreDaoImpl.getInstance().getList();
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public List<Livre> getListDispo() throws ServiceException {
    try {
      List<Livre> livres = LivreDaoImpl.getInstance().getList();
      EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();
      List<Livre> livresDispo = new ArrayList<>();
      for (Livre livre : livres) {
        if (empruntServiceImpl.isLivreDispo(livre.getPrimaryKey())) {
          livresDispo.add(livre);
        }
      }
      return livresDispo;
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public Livre getById(int id) throws ServiceException {
    try {
      return LivreDaoImpl.getInstance().getById(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public int create(String titre, String auteur, String isbn) throws ServiceException {
    try {
      if (titre == "") {
        throw new ServiceException("Tentative de création d'un livre sans titre");
      } else {
        return LivreDaoImpl.getInstance().create(titre, auteur, isbn);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public void update(Livre livre) throws ServiceException {
    try {
      if (livre.getTitre() == "") {
        throw new ServiceException("Tentative de mise à jour d'un livre sans titre");
      } else {
        LivreDaoImpl.getInstance().update(livre);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public void delete(int id) throws ServiceException {
    try {
      LivreDaoImpl.getInstance().delete(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public int count() throws ServiceException {
    try {
      return LivreDaoImpl.getInstance().count();
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }
}
