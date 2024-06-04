package modele;

/** La classe Temple permet de gerer la couleur du temple, de son cristal et sa position */
public class Temple implements Constantes {

    /** champ contenant la position d'un temple */
    private Position chPositionTemple;
    /** champ contenant le numéro de la couleur du temple */
    private int chCouleurTemple;
    /** champ contenant le numéro de la couleur du cristal du temple */
    private int chCouleurCristal;

    /**
     * Constructeur de la classe Temple
     * @param parPosition position du temple
     * @param parCouleurTemple numéro de la couleur du temple
     * @param parCouleurCristal numéro de la couleur de cristal du temple
     */
    public Temple(Position parPosition, int parCouleurTemple, int parCouleurCristal) {
        chPositionTemple = parPosition;
        chCouleurTemple = parCouleurTemple;
        chCouleurCristal = parCouleurCristal;
    }

    /**
     * méthode permettant de redéfinir la couleur du cristal du temple
     * @param parCouleur nouvelle couleur du cristal du temple
     */
    public void setCristal(int parCouleur) {
        chCouleurCristal = parCouleur;
    }

    /**
     * méthode permettant de verifier si la couleur du cristal correspond a la couleur de son temple
     * @return True si le temple et son cristal sont de la meme couleur
     *         False si le temple et son cristal sont de couleur différente
     */
    public boolean verifCouleur() {
        return this.getCouleurTemple() == this.getCouleurCristal();
    }

    /**
     * Accesseur sur le champ chPositionTemple
     * @return chPositionTemple
     */
    public Position getPosition() {
        return chPositionTemple;
    }

    /**
     * Accesseur sur le champ chCouleurTemple
     * @return chCouleurTemple
     */
    public int getCouleurTemple() {
        return chCouleurTemple;
    }

    /**
     * Accesseur sur le champ chCouleurCristal
     * @return chCouleurCristal
     */
    public int getCouleurCristal() {
        return chCouleurCristal;
    }

    /**
     * méthode toString de la classe Temple
     * @return une chaine de caractères donnant les informations du temple
     */
    public String toString() {
        return "\nCristal " + chCouleurCristal + "\nTemple " + chCouleurTemple + "\n" + chPositionTemple.toString();
    }
}
