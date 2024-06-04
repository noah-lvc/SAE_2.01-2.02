package vue;

import algo.Heuristique;
import algo.PlusCourtChemin;
import algo.TriSel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import modele.Constantes;

/** la classe GridPaneCristal permet d'afficher des boutons pour interagir avec le jeu */
public class GridPaneCristal extends GridPane implements Constantes {

    /** champ contenant le bouton pour echanger les cristaux */
    private Button boutonCristal;
    /** champ contenant le bouton permettant de démarrer le tri par sélection */
    private Button boutonTri;
    /** champ contenant le bouton permettant de démarrer le tri heuristique */
    private Button boutonTriHeuristique;

    /** champ contenant le bouton permettant de démarrer le tri avec la priority queue */
    private Button boutonPriorityQueue;

    /** champ contenant l'algorithme du chemin le plus court */
    private PlusCourtChemin plusCourtChemin;


    /** Constructeur de la classe GridPaneCristal */
    public GridPaneCristal () {
        // paramétrage du GridPane
        this.setPadding(new Insets(5));
        this.setHgap(5);
        this.setVgap(5);
        this.setGridLinesVisible(false);

        boutonCristal = new Button("Echanger cristal");
        boutonCristal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VBoxRoot.getApprenti().echangerCristal();
            }
        });

        boutonTri = new Button("Tri Séléction");
        boutonTri.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new TriSel();

            }
        });

        boutonTriHeuristique = new Button("Tri Heuristique");
        boutonTriHeuristique.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new Heuristique();
            }
        });

        boutonPriorityQueue = new Button("Chemin optimal");

        boutonPriorityQueue.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                plusCourtChemin = new PlusCourtChemin();
            }
        });

        this.add(boutonCristal,1,3,1,1);
        this.add(boutonTriHeuristique, 2,3,1,1);
        this.add(boutonTri, 2,4,1,1);
        this.add(boutonPriorityQueue, 2,5,1,1);

    }

    /** Accesseur sur le champ plusCourtChemin
     *
     * @return plusCourtChemin
     */
    public PlusCourtChemin getPlusCourtChemin() {
        return plusCourtChemin;
    }
}