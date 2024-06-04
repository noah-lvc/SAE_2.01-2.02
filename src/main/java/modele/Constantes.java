package modele;

import javafx.scene.paint.Color;

/** Cette interface regroupe toute les constantes */
public interface Constantes {

    /** désigne la largeur du canva en pixel */
    int LARGEUR_CANVAS = 640;

    /** désigne la hauteur du canva en pixel */
    int HAUTEUR_CANVAS = 640;

    /** désigne la taille d'une case de la grille en pxiel */
    int CARRE = 20;

    /** désigne la couleur de la grille */
    Color COULEUR_GRILLE = Color.BLUE;

    /** désigne la couleur de l'apprenti */
    Color COULEUR_APPRENTI = Color.DARKRED;

    /** désigne la largeur de l'apprenti en pixel */
    int LARGEUR_OVALE = 9;

    /** désigne la hauteur de l'apprenti en pixel */
    int HAUTEUR_OVALE = 9;

    /** regroupe l'ensemble des couleurs possible pour un cristal ou un temple */
    Color [] COULEUR_TEMPLE = {null, Color.BLUE, Color.RED, Color.YELLOW, Color.CYAN, Color.GREEN,Color.ORANGE, Color.PURPLE, Color.PINK, Color.GREY, Color.BROWN};
}
