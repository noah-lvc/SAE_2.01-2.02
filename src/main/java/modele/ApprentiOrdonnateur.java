package modele;

import vue.VBoxCanva;
import vue.VBoxRoot;
import java.util.ArrayList;
import java.util.Collection;

/** La Classe ApprentiOrdonnateur gere la position et la couleur du cristal de l'apprenti*/
public class ApprentiOrdonnateur implements Constantes {

    /** champ contenant la position de l'apprenti */
    private Position chPositionApprenti;
    /** champ contenant le numéro de la couleur du cristal de l'apprenti */
    private Integer chCouleurCristal;
    /** ArrayList contenant tous les temples du scénario choisie */
    private ArrayList<Temple> listeTemples = new ArrayList<>();

    /** Constructeur de la classe ApprentiOrdonnateur */
    public ApprentiOrdonnateur(){
        chPositionApprenti = new Position(0,0);
        chCouleurCristal = 0;
    }

    /** méthode permettant d'echanger le cristal de l'apprenti avec celui sur sa position */
    public void echangerCristal() {
        for (Temple temple : listeTemples) {
            if (this.chPositionApprenti.equals(temple.getPosition())) {
                if (chCouleurCristal != null) {
                    int temporaire = temple.getCouleurCristal();
                    temple.setCristal(chCouleurCristal);
                    chCouleurCristal = temporaire;

                }
                else {
                    VBoxRoot.getCanva().getGraphicsContext2D().setFill(COULEUR_TEMPLE[temple.getCouleurTemple()]);
                    VBoxRoot.getCanva().getGraphicsContext2D().fillOval(temple.getPosition().getAbscisse()*CARRE + 5 + 16*CARRE,
                            temple.getPosition().getOrdonnee()*CARRE + 5 + 16*CARRE,CARRE - 10,CARRE - 10);
                    chCouleurCristal = temple.getCouleurCristal();
                    temple.setCristal(0);
                }
            }
        }
        VBoxRoot.getCanva().dessinerTemples();
        VBoxRoot.getCanva().dessinerApprenti();
    }

    /**
     * méthode permettant de stocker tous les temples du scénario choisie
     * @param temples Collection contenant les temples
     */
    public void setTemples(Collection<Temple> temples) {
        listeTemples.clear();
        for (Temple temple: temples) {
            listeTemples.add(temple);
        }
        System.out.println(listeTemples);
    }

    /**
     * Accesseur sur le champ chPositionApprenti
     * @return chPositionApprenti
     */
    public Position getPositionApprenti() {
        return chPositionApprenti;
    }

    /**
     * Accesseur sur le champ chCouleurCristal
     * @return chCouleurCristal
     */
    public int getCouleurCristal() {
        return chCouleurCristal;
    }

    /**
     * Accesseur sur le champ listeTemples
     * @return listeTemples
     */
    public ArrayList<Temple> getListeTemples() {
        return listeTemples;
    }

    /** méthode permettant de réinitialiser la position et le cristal de l'apprenti */
    public void resetApprenti () {
        chPositionApprenti = new Position(0,0);
        chCouleurCristal = 0;
    }

    /**
     * méthode toString de la classe ApprentiOrdonnateur
     * @return une chaine de caractères indiquant la position et la couleur du cristal de l'apprenti
     */
    public String toString() {
        return chPositionApprenti + " " + chCouleurCristal;
    }
}
