package modele;

/** La classe Position permet de gérer le déplacement de l'apprenti sur la carte */
public class Position {

    /** champ representant l'abscisse de la position */
    private int abscisse;
    /** champ representant l'ordonnée de la position */
    private int ordonnee;
    /** champ comptant le nombre de pas de l'apprenti */
    private static int nbPas;

    /**
     * Constructeur de la classe
     * @param abscisse abscisse de la position
     * @param ordonnee ordonnée de la position
     */
    public Position(int abscisse, int ordonnee) {
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
    }

    /**
     * la méthode deplacementUneCase déplace la position this d'une case
     * pour la rapprocher de celle du paramètre parPosition
     * elle incrémente le champ static nombreDePas
     *
     * @param parPosition : la position vers laquelle this se rapproche
     */
    public void deplacementUneCase(Position parPosition) {
        System.out.println(parPosition);
        if (this.abscisse > parPosition.abscisse) {
            this.abscisse -= 1;
            nbPas += 1;
            return;
        }
        if (this.abscisse < parPosition.abscisse) {
            this.abscisse += 1;
            nbPas += 1;
            return;
        }

        if (this.ordonnee > parPosition.ordonnee) {
            this.ordonnee -= 1;
            nbPas += 1;
            return;
        }
        if (this.ordonnee < parPosition.ordonnee) {
            this.ordonnee += 1;
            nbPas += 1;
        }
    }

    /**
     * méthode verifiant si this a la meme position que celle en paramètre
     * @param parPosition position à comparer a this
     * @return True si les positions sont les meme
     *         False si les positions sont differentes
     */
    public boolean equals(Position parPosition) {
        return this.abscisse == parPosition.abscisse && this.ordonnee == parPosition.ordonnee;
    }

    /** méthode permettant de réinitialiser le nombre de pas */
    public static void resetNbPas() {
        nbPas = 0;
    }

    /**
     * accesseur sur le champ abscisse
     * @return abscisse
     */
    public int getAbscisse() {
        return abscisse;
    }

    /**
     * accesseur sur le champ ordonnee
     * @return ordonnee
     */
    public int getOrdonnee() {
        return ordonnee;
    }

    /**
     * accesseur sur le champ nbPas
     * @return nbPas
     */
    public static int getNombreDePas() {
        return nbPas;
    }

    /**
     * méthode toString de la classe Position
     * @return une chaine de caractères indiquant l'abscisse et l'ordonné
     */
    public String toString() {
        return "(" + abscisse + "," + ordonnee +")";
    }
}

