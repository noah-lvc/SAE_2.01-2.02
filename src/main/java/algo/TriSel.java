package algo;

import modele.Temple;
import vue.VBoxRoot;
import static vue.VBoxRoot.getApprenti;

/** La classe Heuristique permet de terminer le jeu avec un algorithme de tri par sélection*/
public class TriSel {

    /** Constructeur de la classe TriSel */
    public TriSel() {

        for (int i=0; i < getApprenti().getListeTemples().size() ; i++) {

            // s'éxecute si la couleur du temple i et de son cristal sont différente
            if (getApprenti().getListeTemples().get(i).getCouleurTemple() != getApprenti().getListeTemples().get(i).getCouleurCristal()) {

                // s'execute si la couleur du temple i et la couleur du cristal de l'apprenti sont différente
                if (getApprenti().getCouleurCristal() != getApprenti().getListeTemples().get(i).getCouleurTemple()) {
                    int indiceTemple = getIndiceTemple(getApprenti().getListeTemples().get(i).getCouleurTemple());
                    VBoxRoot.getCanva().deplacementTriSel(getApprenti().getPositionApprenti(), getApprenti().getListeTemples().get(indiceTemple).getPosition());
                    break;
                }

                // s'execute si la couleur du temple i et la couleur du cristal de l'apprenti sont similaire
                if (getApprenti().getCouleurCristal() != 0) {
                    VBoxRoot.getCanva().deplacementTriSel(getApprenti().getPositionApprenti(), getApprenti().getListeTemples().get(i).getPosition());
                    break;
                }
            }
        }
    }

    /**
     * méthode permettant de connaitre l'indice du temple grace a la couleur de son cristal
     * @param couleurCristal la couleur du cristal dont on veux connaitre l'indice du temple qui l'a contient
     * @return l'indice du temple qui contient le cristal
     */
    public int getIndiceTemple(int couleurCristal) {
        int indice = 0;
        for (Temple temple: getApprenti().getListeTemples()) {
            if (temple.getCouleurCristal() == couleurCristal) {
                return indice;
            }
            indice++;
        }
        return -1;
    }
}
