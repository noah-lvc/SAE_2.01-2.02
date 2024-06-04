package algo;

import modele.Temple;
import vue.VBoxRoot;
import static java.lang.Math.abs;
import static vue.VBoxRoot.getApprenti;

/** La classe Heuristique permet de terminer le jeu avec un algorithme heuristique*/
public class Heuristique{

    /** Constructeur de la classe Heuristique */
    public Heuristique() {

            // s'éxecute si l'apprenti ne porte pas de cristal
            if (getApprenti().getCouleurCristal() == 0) {
                VBoxRoot.getCanva().deplacementHeuristique(getApprenti().getPositionApprenti(), templePlusProche().getPosition());
            }

            // s'éxecute si l'apprenti porte un cristal
            if (getApprenti().getCouleurCristal() != 0) {
                VBoxRoot.getCanva().deplacementHeuristique(getApprenti().getPositionApprenti(), getApprenti().getListeTemples().get(getIndiceTemple(getApprenti().getCouleurCristal())).getPosition());
            }
        }

    /**
     * méthode permettant de déterminer le temple le plus proche de l'apprenti
     * @return le temple le plus proche de l'apprenti
     */
    public Temple templePlusProche() {
        int distance = 63;
        int indice = 0;
        int indicePlusProche = 0;
        for (Temple temple : getApprenti().getListeTemples()) {
            if (distanceApprenti(temple) < distance && temple.getCouleurTemple() != temple.getCouleurCristal()) {

                distance = distanceApprenti(temple);
                indicePlusProche = indice;
            }
            indice += 1;
        }
        return getApprenti().getListeTemples().get(indicePlusProche);
    }

    /**
     * méthode permettant de calculer la distance entre l'apprenti et le temple en paramètre
     * @param temple le temple dont on veux calculer la distance
     * @return un nombre correspondant au nombre de pas séparant l'apprenti du temple
     */
    public int distanceApprenti(Temple temple) {
        int posXApprenti = VBoxRoot.getApprenti().getPositionApprenti().getAbscisse();
        int posYApprenti = VBoxRoot.getApprenti().getPositionApprenti().getAbscisse();
        int posXTemple = temple.getPosition().getAbscisse();
        int posYTemple = temple.getPosition().getOrdonnee();
        return (abs(posXApprenti - posXTemple) + abs(posYApprenti+posYTemple));
    }

    /**
     * méthode permettant de connaitre l'indice du temple grace a sa couleur
     * @param couleurTemple la couleur du tempe dont on veux connaitre l'indice
     * @return l'indice du temple
     */
    public int getIndiceTemple(int couleurTemple) {
        int indice = 0;
        for (Temple temple: getApprenti().getListeTemples()) {
            if (temple.getCouleurTemple() == couleurTemple) {
                return indice;
            }
            indice++;
        }
        return -1;
    }
}



