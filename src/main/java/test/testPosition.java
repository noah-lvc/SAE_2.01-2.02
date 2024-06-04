package test;

import modele.Position;

/**
 * Classe testant la classe Position
 */
public class testPosition {

    public static void main(String[] args) {

        // Création des position de départ
        Position positionD1 = new Position(0,0);
        Position positionD2 = new Position(0,10);
        Position positionD3 = new Position(5,9);
        Position positionD4 = new Position(3,7);


        // Création des position d'arrivées

        Position positionA1 = new Position(0,0);
        Position positionA2 = new Position(8,6);
        Position positionA3 = new Position(5,14);
        Position positionA4 = new Position(6,7);

        // test de la méthode equals

        assert positionD1.equals(positionA1) : "positionD1 devrait être égale à positionA1";
        assert !positionD2.equals(positionA2) : "positionD2 ne devrait pas être égale à positionA2";
        assert !positionD3.equals(positionA3) : "positionD3 ne devrait pas être égale à positionA3";
        assert !positionD4.equals(positionA4) : "positionD4 ne devrait pas être égale à positionA4";

        System.out.println("Tous les tests ont réussi.");
    }
}
