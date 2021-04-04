package ensta.librarymanager.service;

import java.util.List;

import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.service.EmpruntServiceImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ServiceTest {

  @Before
  public void beforeServiceTest() {
    System.out.println("----------------------------------------------------------------");
    System.out.println("Début d'un nouveau test (Service)");
  }

  @After
  public void afterServiceTest() {
    System.out.println("Fin du test (Service)");
    System.out.println("----------------------------------------------------------------");
  }

  @Test
  public void empruntServiceGetListTest() {
    try {
      List<Emprunt> emprunts = EmpruntServiceImpl.getInstance().getList();
      for (Emprunt emprunt : emprunts) {
        System.out.println(emprunt.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void empruntServiceGetListCurrentTest() {
    try {
      List<Emprunt> emprunts = EmpruntServiceImpl.getInstance().getListCurrent();
      for (Emprunt emprunt : emprunts) {
        System.out.println(emprunt.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void empruntServiceGetListCurrentByMembreTest() {
    try {
      List<Emprunt> emprunts = EmpruntServiceImpl.getInstance().getListCurrentByMembre(5);
      for (Emprunt emprunt : emprunts) {
        System.out.println(emprunt.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void empruntServiceGetListCurrentByLivreTest() {
    try {
      List<Emprunt> emprunts = EmpruntServiceImpl.getInstance().getListCurrentByLivre(1);
      for (Emprunt emprunt : emprunts) {
        System.out.println(emprunt.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Plein de tests similaires à écrire...
}
