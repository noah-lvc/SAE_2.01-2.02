package vue;

import controleur.Controleur;
import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import modele.ApprentiOrdonnateur;
import modele.Constantes;
import java.io.File;

/** * La classe VBoxRoot représente la racine de l'application. */
public class VBoxRoot extends VBox implements Constantes {

    /** champ contenant l'apprenti et les temples*/
    private static ApprentiOrdonnateur apprenti;
    /** champ contenant le controleur */
    private static Controleur controleur;
    /** champs contenant une VBox affichant la carte */
    private static VBoxCanva canva;
    /** champs contenant un GridPane affichant les boutons */
    private static GridPaneCristal gridPaneCristal;

    /** Constructeur de la Classe VBoxRoot */
    public VBoxRoot() {

        apprenti = new ApprentiOrdonnateur();
        controleur = new Controleur();

        // La barre de menus

        MenuBar menuBar = new MenuBar();
        this.getChildren().add(menuBar);
        VBox.setMargin(menuBar, new Insets(9));

        // Le menu des scénarios
        Menu menuScenario = new Menu("Liste des scénarios");
        menuBar.getMenus().add(menuScenario);

        // Les items du menu scénario
        File[] scenarios = new File("scenarios").listFiles();
        for (int i=0; i < scenarios.length; i++) {
            MenuItem menuItem = new MenuItem(scenarios[i].getName());
            menuItem.setUserData(scenarios[i]);
            menuItem.setOnAction(controleur);
            menuScenario.getItems().add(menuItem);
        }

        // la vue
        canva = new VBoxCanva();
        gridPaneCristal = new GridPaneCristal();
        this.getChildren().add(gridPaneCristal);
        this.getChildren().add(canva);
    }

    /**
     * accesseur sur le champ apprenti
     * @return apprenti
     */
    public static ApprentiOrdonnateur getApprenti() {
        return apprenti;
    }

    /**
     * accesseur sur le champ canva
     * @return canva
     */
    public static VBoxCanva getCanva() {
        return canva;
    }

    /**
     * accesseur sur le champ controleur
     * @return controleur
     */
    public static Controleur getControleur() {
        return controleur;
    }

    /**
     * accesseur sur le champ gridPaneCristal
     * @return gridPaneCristal
     */
    public static GridPaneCristal getGridPane() {
        return gridPaneCristal;
    }
}


