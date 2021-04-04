package com.ensta.librarymanager.service;

import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.MembreDaoImpl;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Membre;

public class MembreServiceImpl implements MembreService {
  private static MembreServiceImpl instance;

  private MembreServiceImpl() {
  }

  public static MembreServiceImpl getInstance() {
    if (instance == null) {
      instance = new MembreServiceImpl();
    }
    return instance;
  }

  @Override
  public List<Membre> getList() throws ServiceException {
    try {
      return MembreDaoImpl.getInstance().getList();
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
    try {
      List<Membre> membres = getList();
      List<Membre> membresPouvantEmprunter = new ArrayList<>();
      EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();
      for (Membre membre : membres) {
        if (empruntServiceImpl.isEmpruntPossible(membre)) {
          membresPouvantEmprunter.add(membre);
        }
      }
      return membresPouvantEmprunter;
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public Membre getById(int id) throws ServiceException {
    try {
      return MembreDaoImpl.getInstance().getById(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException {
    try {
      if (nom == "" || prenom == "") {
        throw new ServiceException("Nom ou prénom vide : impossible de créer le membre");
      } else {
        return MembreDaoImpl.getInstance().create(nom.toUpperCase(), prenom, adresse, email, telephone);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public void update(Membre membre) throws ServiceException {
    try {
      if (membre.getNom() == "" || membre.getPrenom() == "") {
        throw new ServiceException("Nom ou prénom vide : impossible de mettre à jour le membre");
      } else {
        MembreDaoImpl.getInstance().update(membre);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public void delete(int id) throws ServiceException {
    try {
      MembreDaoImpl.getInstance().delete(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }

  @Override
  public int count() throws ServiceException {
    try {
      return MembreDaoImpl.getInstance().count();
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
  }
}
