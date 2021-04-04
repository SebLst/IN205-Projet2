package com.ensta.librarymanager.service;

import java.time.LocalDate;
import java.util.List;

import com.ensta.librarymanager.dao.EmpruntDaoImpl;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.model.Membre;

public class EmpruntServiceImpl implements EmpruntService {
  private static EmpruntServiceImpl instance;

  private EmpruntServiceImpl() {
  }

  public static EmpruntServiceImpl getInstance() {
    if (instance == null) {
      instance = new EmpruntServiceImpl();
    }
    return instance;
  }

  @Override
  public List<Emprunt> getList() throws ServiceException {
    try {
      return EmpruntDaoImpl.getInstance().getList();
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public List<Emprunt> getListCurrent() throws ServiceException {
    try {
      return EmpruntDaoImpl.getInstance().getListCurrent();
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
    try {
      return EmpruntDaoImpl.getInstance().getListCurrentByMembre(idMembre);
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
    try {
      return EmpruntDaoImpl.getInstance().getListCurrentByLivre(idLivre);
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public Emprunt getById(int id) throws ServiceException {
    try {
      return EmpruntDaoImpl.getInstance().getById(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
    try {
      EmpruntDaoImpl.getInstance().create(idMembre, idLivre, dateEmprunt);
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public void returnBook(int id) throws ServiceException {
    try {
      EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
      Emprunt emprunt = empruntDaoImpl.getById(id);
      emprunt.setDateRetour(LocalDate.now());
      empruntDaoImpl.update(emprunt);
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public int count() throws ServiceException {
    try {
      return EmpruntDaoImpl.getInstance().count();
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public boolean isLivreDispo(int idLivre) throws ServiceException {
    try {
      List<Emprunt> emprunts = EmpruntDaoImpl.getInstance().getListCurrentByLivre(idLivre);
      for (Emprunt emprunt : emprunts) {
        if (emprunt.getLivre().getPrimaryKey() == idLivre) {
          return false;
        }
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public boolean isEmpruntPossible(Membre membre) throws ServiceException {
    int limite_emprunts = 0;

    switch (membre.getAbonnement()) {
    case BASIC:
      limite_emprunts = 2;
      break;

    case PREMIUM:
      limite_emprunts = 5;
      break;

    case VIP:
      limite_emprunts = 20;
      break;

    default:
      break;
    }

    try {
      List<Emprunt> emprunts = EmpruntDaoImpl.getInstance().getListCurrentByMembre(membre.getPrimaryKey());
      int nb_emprunts = emprunts.size();
      return nb_emprunts < limite_emprunts ? true : false;
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }
}
