package ensta.librarymanager.dao;

import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;
import java.util.List;

import com.ensta.librarymanager.dao.EmpruntDaoImpl;
import com.ensta.librarymanager.dao.LivreDaoImpl;
import com.ensta.librarymanager.dao.MembreDaoImpl;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.model.Membre;
import com.ensta.librarymanager.model.Membre.abonnementType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DaoTest {

  @Before
  public void beforeDaoTest() {
    System.out.println("----------------------------------------------------------------");
    System.out.println("Début d'un nouveau test (DAO)");
  }

  @After
  public void afterDaoTest() {
    System.out.println("Fin du test (DAO)");
    System.out.println("----------------------------------------------------------------");
  }

  @Test
  public void livreGetListTest() {
    LivreDaoImpl livreDAO = LivreDaoImpl.getInstance();
    try {
      List<Livre> livres = livreDAO.getList();
      for (Livre livre : livres) {
        System.out.println(livre.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void livreGetByIdTest() {
    LivreDaoImpl livreDAO = LivreDaoImpl.getInstance();
    try {
      Livre livre = livreDAO.getById(1);
      System.out.println(livre.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void livreCreateTest() {
    LivreDaoImpl livreDAO = LivreDaoImpl.getInstance();
    try {
      int id = livreDAO.create("Le Petit Prince", "Antoine de Saint-Exupéry", "978-2070408504");
      assertNotEquals(-1, id);
      Livre livre = livreDAO.getById(id);
      System.out.println(livre.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void livreUpdateTest() {
    LivreDaoImpl livreDAO = LivreDaoImpl.getInstance();
    try {
      Livre livre = livreDAO.getById(1);
      livre.setTitre("CECI EST UN SUPER TITRE POUR UN LIVRE");
      livreDAO.update(livre);
      livre = livreDAO.getById(1);
      System.out.println(livre.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void livreDeleteTest() {
    LivreDaoImpl livreDAO = LivreDaoImpl.getInstance();
    try {
      livreDAO.delete(3);
      List<Livre> livres = livreDAO.getList();
      for (Livre livre : livres) {
        System.out.println(livre.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void livreCountTest() {
    LivreDaoImpl livreDAO = LivreDaoImpl.getInstance();
    try {
      int count = livreDAO.count();
      System.out.println("La BD contient " + Integer.toString(count) + " entrée(s).");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void membreGetListTest() {
    try {
      MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();
      List<Membre> membres = membreDaoImpl.getList();
      for (Membre membre : membres) {
        System.out.println(membre.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void membreGetByIdTest() {
    try {
      MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();
      Membre membre = membreDaoImpl.getById(1);
      System.out.println(membre.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void membreCreateTest() {
    try {
      MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();
      int id = membreDaoImpl.create("nom", "prenom", "adresse", "email", "telephone");
      Membre membre = membreDaoImpl.getById(id);
      System.out.println(membre.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void membreUpdateTest() {
    try {
      MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();
      Membre membre = membreDaoImpl.getById(3);
      membre.setAbonnement(abonnementType.VIP);
      membre.setNom("VIP");
      membreDaoImpl.update(membre);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void membreDeleteTest() {
    try {
      MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();
      membreDaoImpl.delete(11);
      List<Membre> membres = membreDaoImpl.getList();
      for (Membre membre : membres) {
        System.out.println(membre.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void membreCountTest() {
    try {
      MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();
      int count = membreDaoImpl.count();
      System.out.println("Il y a " + Integer.toString(count) + " membre(s)");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void empruntGetListTest() {
    try {
      EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
      List<Emprunt> emprunts = empruntDaoImpl.getList();
      for (Emprunt emprunt : emprunts) {
        System.out.println(emprunt.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void empruntGetListCurrentTest() {
    try {
      EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
      List<Emprunt> emprunts = empruntDaoImpl.getListCurrent();
      for (Emprunt emprunt : emprunts) {
        System.out.println(emprunt.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void empruntGetListCurrentByMembreTest() {
    try {
      EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
      List<Emprunt> emprunts = empruntDaoImpl.getListCurrentByMembre(3);
      for (Emprunt emprunt : emprunts) {
        System.out.println(emprunt.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void empruntGetListCurrentByLivreTest() {
    try {
      EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
      List<Emprunt> emprunts = empruntDaoImpl.getListCurrentByLivre(1);
      for (Emprunt emprunt : emprunts) {
        System.out.println(emprunt.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void empruntGetByIdTest() {
    try {
      EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
      Emprunt emprunt = empruntDaoImpl.getById(5);
      System.out.println(emprunt.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void empruntCreateTest() {
    try {
      EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
      empruntDaoImpl.create(3, 1, LocalDate.of(2015, 6, 15));
      List<Emprunt> emprunts = empruntDaoImpl.getList();
      for (Emprunt emprunt : emprunts) {
        System.out.println(emprunt.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void empruntUpdateTest() {
    try {
      EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
      Emprunt emprunt = empruntDaoImpl.getById(3);
      emprunt.setDateRetour(LocalDate.of(2021, 04, 04));
      empruntDaoImpl.update(emprunt);
      emprunt = empruntDaoImpl.getById(3);
      System.out.println(emprunt.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void empruntCountTest() {
    try {
      EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
      int count = empruntDaoImpl.count();
      System.out.println(Integer.toString(count) + " emprunts ont été fait");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
