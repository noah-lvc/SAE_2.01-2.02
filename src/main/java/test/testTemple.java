package test;

import modele.Position;
import modele.Temple;

/**
 *  Classe testant la classe Temple
 *  */
public class testTemple {

    public static void main(String[] args) {

        // Création des temples

        // temple avec un cristal de sa couleur
        Temple temple1 = new Temple(new Position(1,1), 3, 3);
        // temple avec un cristal d'une autre couleur
        Temple temple2 = new Temple(new Position(2,2), 5, 2);

        // test de la méthode verifCouleur
        assert temple1.verifCouleur() : "temple1 devrait avoir la couleur correcte";
        assert temple2.verifCouleur() : "temple2 ne devrait pas avoir la couleur correcte";

        System.out.println("Tous les tests ont réussi.");
    }
}
