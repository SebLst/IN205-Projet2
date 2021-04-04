package ensta.librarymanager.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.model.Membre;
import com.ensta.librarymanager.model.Membre.abonnementType;

import org.junit.Test;

public class ModelTest {

  @Test
  public void livreContructorTest() {
    Livre livre = new Livre(42, "titre", "auteur", "isbn");
    assertEquals("42 titre auteur isbn", livre.toString());
  }

  @Test
  public void membreConstructorTest() {
    Membre membre = new Membre(42, "nom", "prenom", "adresse", "email", "telephone", abonnementType.BASIC);
    assertEquals("42 nom prenom adresse email telephone BASIC", membre.toString());
  }

  @Test
  public void empruntConstructorTest() {
    Livre livre = new Livre(42, "titre", "auteur", "isbn");
    Membre membre = new Membre(42, "nom", "prenom", "adresse", "email", "telephone", abonnementType.BASIC);
    LocalDate dateEmprunt = LocalDate.of(2012, 2, 15);
    LocalDate dateRetour = LocalDate.of(2012, 3, 5);
    Emprunt emprunt = new Emprunt(42, membre, livre, dateEmprunt, dateRetour);
    assertEquals("42 42 nom prenom adresse email telephone BASIC 42 titre auteur isbn 2012-02-15 2012-03-05",
        emprunt.toString());
  }

}
