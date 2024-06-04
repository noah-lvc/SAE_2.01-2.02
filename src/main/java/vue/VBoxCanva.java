package vue;

import algo.Heuristique;
import algo.TriSel;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import modele.*;
import org.w3c.dom.ls.LSOutput;

import java.util.Timer;
import java.util.TimerTask;
import static vue.VBoxRoot.getApprenti;

/** La classe VBoxCanva permet de dessiner la carte du jeu */
public class VBoxCanva extends VBox implements Constantes {

    /** Label permettant de compter les nombres de pas parcourus */
    private Label labelNombreDePas;
    /** champs contenant le canva qui affiche la carte */
    private Canvas canvasCarte;
    /** champs permettant de dessiner la carte*/
    private GraphicsContext graphicsContext2D;

    /** Constructeur de la classe VBoxCanva */
    public VBoxCanva() {
        // appel de la fonction dessinant la carte
        dessinerCarte();
    }

    /** méthode permettant de supprimer tous les éléments du VBoxCanva */
    public void clearCarte() {
        VBoxRoot.getCanva().getChildren().clear();
    }

    /** méthode permettant de dessiner la carte sur le VBoxCanva */
    public void dessinerCarte() {
        // le label qui affiche le nombre de pas
        Position.resetNbPas();
        labelNombreDePas = new Label("Nombre de pas : 0");

        // le canvas et son contexte graphique
        canvasCarte = new Canvas();
        canvasCarte.setWidth(LARGEUR_CANVAS);
        canvasCarte.setHeight(HAUTEUR_CANVAS);
        graphicsContext2D = canvasCarte.getGraphicsContext2D();

        //les carrés
        graphicsContext2D.setStroke(COULEUR_GRILLE);
        for (int i = 0; i < LARGEUR_CANVAS; i += CARRE) {
            for (int j = 0; j < HAUTEUR_CANVAS; j += CARRE) {

                graphicsContext2D.strokeRect(i, j, CARRE, CARRE);
            }
        }

        // les numéros de colonne
        int numCol = -15;
        graphicsContext2D.setFill(COULEUR_GRILLE);
        for (int i = CARRE; i < LARGEUR_CANVAS; i += CARRE) {
            graphicsContext2D.fillText(Integer.toString(numCol), i + CARRE / 3-1, CARRE / 2);
            numCol++;
        }
        int numLigne = -15;
        graphicsContext2D.setFill(COULEUR_GRILLE);
        for (int i = CARRE; i < LARGEUR_CANVAS; i += CARRE) {
            graphicsContext2D.fillText(Integer.toString(numLigne), CARRE / 3-1, i + CARRE / 2);
            numLigne++;
        }
        // ajout des composants graphiques à la racine (this)
        this.getChildren().add(labelNombreDePas);
        VBox.setMargin(labelNombreDePas, new Insets(30));
        this.getChildren().add(canvasCarte);
        VBox.setMargin(canvasCarte, new Insets(30));


        // dessin apprenti

        VBoxRoot.getApprenti().resetApprenti();
        dessinerApprenti();

        // clique souris

        canvasCarte.setOnMouseClicked(event -> {
            int abscisse = (int) event.getX() / CARRE - 16;
            int ordonnee = (int) event.getY() / CARRE - 16;
            if (abscisse >= -15 && abscisse <= 15 && ordonnee >= -15 && ordonnee <= 15) {

                Position positionCliquee = new Position(abscisse, ordonnee);

                graphicsContext2D.setStroke(Color.RED);
                graphicsContext2D.strokeRect(abscisse * CARRE + 16 * CARRE, ordonnee * CARRE + 16 * CARRE, CARRE, CARRE);

                deplacementAvecTimer(getApprenti().getPositionApprenti(), positionCliquee);

            }
        });

    }

    /**
     * méthode permettant de deplacer l'apprenti depuis l'algorithme de tri par sélection
     * @param positionCourante position de l'apprenti
     * @param positionCible position d'arrivée de l'apprenti
     */
    public void deplacementTriSel(Position positionCourante, Position positionCible) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                graphicsContext2D.setStroke(COULEUR_GRILLE);

                graphicsContext2D.strokeRect(positionCourante.getAbscisse()*CARRE + 16*CARRE,positionCourante.getOrdonnee()*CARRE + 16*CARRE,
                        CARRE,CARRE);

                graphicsContext2D.setFill(getColor());
                graphicsContext2D.fillRect(positionCourante.getAbscisse()*CARRE + CARRE/8 + 16*CARRE,
                        positionCourante.getOrdonnee()*CARRE+CARRE/4 - 3 + 16*CARRE,
                        CARRE -3, CARRE - 3);

                dessinerTemples();


                positionCourante.deplacementUneCase(positionCible);
                graphicsContext2D.setFill(COULEUR_APPRENTI);
                graphicsContext2D.fillRect(getApprenti().getPositionApprenti().getAbscisse()*CARRE + CARRE/16 + 16*CARRE + 4,
                        getApprenti().getPositionApprenti().getOrdonnee()*CARRE+CARRE/16 + 16*CARRE + 4,
                        LARGEUR_OVALE, HAUTEUR_OVALE
                );
                if (positionCourante.equals(positionCible)) {
                    VBoxRoot.getApprenti().echangerCristal();
                    if (VBoxRoot.getCanva().verifFinDePartie() == false) {
                        new TriSel();
                    }
                    timer.cancel();
                }
                Platform.runLater(() -> {
                    labelNombreDePas.setText("Nombre de pas : " + Position.getNombreDePas());
                });
            }

        };
        timer.scheduleAtFixedRate(timerTask, 1000, 200);

    }

    /**
     * méthode permettant de deplacer l'apprenti depuis l'algorithme de tri heuristique
     * @param positionCourante position de l'apprenti
     * @param positionCible position d'arrivée de l'apprenti
     */
    public void deplacementHeuristique(Position positionCourante, Position positionCible) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                graphicsContext2D.setStroke(COULEUR_GRILLE);

                graphicsContext2D.strokeRect(positionCourante.getAbscisse()*CARRE + 16*CARRE,positionCourante.getOrdonnee()*CARRE + 16*CARRE,
                        CARRE,CARRE);

                graphicsContext2D.setFill(getColor());
                graphicsContext2D.fillRect(positionCourante.getAbscisse()*CARRE + CARRE/8 + 16*CARRE,
                        positionCourante.getOrdonnee()*CARRE+CARRE/4 - 3 + 16*CARRE,
                        CARRE -3, CARRE - 3);

                dessinerTemples();


                positionCourante.deplacementUneCase(positionCible);
                graphicsContext2D.setFill(COULEUR_APPRENTI);
                graphicsContext2D.fillRect(getApprenti().getPositionApprenti().getAbscisse()*CARRE + CARRE/16 + 16*CARRE + 4,
                        getApprenti().getPositionApprenti().getOrdonnee()*CARRE+CARRE/16 + 16*CARRE + 4,
                        LARGEUR_OVALE, HAUTEUR_OVALE
                );
                if (positionCourante.equals(positionCible)) {
                    VBoxRoot.getApprenti().echangerCristal();
                    if (VBoxRoot.getCanva().verifFinDePartie()==false) {
                        new Heuristique();
                    }
                    timer.cancel();
                }
                Platform.runLater(() -> {
                    labelNombreDePas.setText("Nombre de pas : " + Position.getNombreDePas());
                });
            }

        };
        timer.scheduleAtFixedRate(timerTask, 1000, 200);

    }

    /**
     * méthode permettant de deplacer l'apprenti depuis l'algorithme de tri avec la file de priorité
     * @param positionCourante position de l'apprenti
     * @param positionCible position d'arrivée de l'apprenti
     */
    public void deplacementPriorityQueue(Position positionCourante, Position positionCible) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                graphicsContext2D.setStroke(COULEUR_GRILLE);

                graphicsContext2D.strokeRect(positionCourante.getAbscisse()*CARRE + 16*CARRE,positionCourante.getOrdonnee()*CARRE + 16*CARRE,
                        CARRE,CARRE);

                graphicsContext2D.setFill(getColor());
                graphicsContext2D.fillRect(positionCourante.getAbscisse()*CARRE + CARRE/8 + 16*CARRE,
                        positionCourante.getOrdonnee()*CARRE+CARRE/4 - 3 + 16*CARRE,
                        CARRE -3, CARRE - 3);

                dessinerTemples();

                positionCourante.deplacementUneCase(positionCible);
                graphicsContext2D.setFill(COULEUR_APPRENTI);
                graphicsContext2D.fillRect(getApprenti().getPositionApprenti().getAbscisse()*CARRE + CARRE/16 + 16*CARRE + 4,
                        getApprenti().getPositionApprenti().getOrdonnee()*CARRE+CARRE/16 + 16*CARRE + 4,
                        LARGEUR_OVALE, HAUTEUR_OVALE
                );
                if (positionCourante.equals(positionCible)) {
                    VBoxRoot.getApprenti().echangerCristal();
                    if (VBoxRoot.getCanva().verifFinDePartie()==false) {
                        VBoxRoot.getGridPane().getPlusCourtChemin().parcoursListe();
                    }
                    timer.cancel();
                }
                Platform.runLater(() -> {
                    labelNombreDePas.setText("Nombre de pas : " + Position.getNombreDePas());
                });
            }

        };
        timer.scheduleAtFixedRate(timerTask, 1000, 200);

    }


    /**
     * méthode permettant de deplacer l'apprenti lors d'un clique de l'utilisateur
     * @param positionCourante position de l'apprenti
     * @param positionCible position d'arrivée de l'apprenti
     */
    public void deplacementAvecTimer(Position positionCourante, Position positionCible) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                graphicsContext2D.setStroke(COULEUR_GRILLE);

                graphicsContext2D.strokeRect(positionCourante.getAbscisse()*CARRE + 16*CARRE,positionCourante.getOrdonnee()*CARRE + 16*CARRE,
                        CARRE,CARRE);

                graphicsContext2D.setFill(getColor());
                graphicsContext2D.fillRect(positionCourante.getAbscisse()*CARRE + CARRE/8 + 16*CARRE,
                        positionCourante.getOrdonnee()*CARRE+CARRE/4 - 3 + 16*CARRE,
                        CARRE -3, CARRE - 3);

                dessinerTemples();


                positionCourante.deplacementUneCase(positionCible);
                graphicsContext2D.setFill(COULEUR_APPRENTI);
                graphicsContext2D.fillRect(getApprenti().getPositionApprenti().getAbscisse()*CARRE + CARRE/16 + 16*CARRE + 4,
                        getApprenti().getPositionApprenti().getOrdonnee()*CARRE+CARRE/16 + 16*CARRE + 4,
                        LARGEUR_OVALE, HAUTEUR_OVALE
                );
                if (positionCourante.equals(positionCible)) {
                    timer.cancel();
                }
                Platform.runLater(() -> {
                    labelNombreDePas.setText("Nombre de pas : " + Position.getNombreDePas());
                });
            }

        };
        timer.scheduleAtFixedRate(timerTask, 1000, 200);

    }

    /**
     * méthode permettant de recuperer la couleur du temple sur lequel se trouve l'apprenti
     * @return la couleur du temple
     */
    private Color getColor() {
        for (Temple temple: getApprenti().getListeTemples()) {
            if (getApprenti().getPositionApprenti().getAbscisse() == temple.getPosition().getAbscisse() &&
                    getApprenti().getPositionApprenti().getOrdonnee() == temple.getPosition().getOrdonnee()) {
                return COULEUR_TEMPLE[temple.getCouleurTemple()];
            }
        }
        dessinerTemples();
        return Color.WHITE;
    }

    /** méthode permettant de dessiner les temples sur la carte */
    public void dessinerTemples() {
        for (Temple temple: getApprenti().getListeTemples()) {
            graphicsContext2D.setFill(COULEUR_TEMPLE[temple.getCouleurTemple()]);
            graphicsContext2D.fillRect(temple.getPosition().getAbscisse()*CARRE + 1 + 16*CARRE,
                    temple.getPosition().getOrdonnee()*CARRE + 1 + 16*CARRE,CARRE - 2,CARRE - 2);

            graphicsContext2D.setFill(Color.BLACK);
            graphicsContext2D.fillOval(temple.getPosition().getAbscisse()*CARRE + 3 + 16*CARRE,
                    temple.getPosition().getOrdonnee()*CARRE + 3 + 16*CARRE,CARRE - 6,CARRE - 6);


            graphicsContext2D.setFill(COULEUR_TEMPLE[temple.getCouleurCristal()]);
            graphicsContext2D.fillOval(temple.getPosition().getAbscisse()*CARRE + 4 + 16*CARRE,
                    temple.getPosition().getOrdonnee()*CARRE + 4 + 16*CARRE,CARRE - 8,CARRE - 8);
        }
    }

    /** méthode permettant de dessiner l'apprenti sur la carte */
    public void dessinerApprenti() {
        graphicsContext2D.setFill(COULEUR_APPRENTI);
        graphicsContext2D.fillRect(getApprenti().getPositionApprenti().getAbscisse()*CARRE + CARRE/16 + 16*CARRE + 4,
                getApprenti().getPositionApprenti().getOrdonnee()*CARRE+CARRE/16 + 16*CARRE + 4,
                LARGEUR_OVALE, HAUTEUR_OVALE
        );
    }

    /**
     * méthode verifiant si tous les cristaux sont sur leurs temples
     * @return True si tous les cristaux sont sur leurs temples
     *         False si les cristaux ne sont pas tous sur leurs temples
     */
    public boolean verifFinDePartie(){
        for (Temple temple: getApprenti().getListeTemples()) {
            if (! temple.verifCouleur()) {
                return false;
            }

        }
        return true;
    }

    /**
     * accesseur sur le champs getGraphicsContext2D
     * @return getGraphicsContext2D
     */
    public GraphicsContext getGraphicsContext2D() {
        return graphicsContext2D;
    }
}