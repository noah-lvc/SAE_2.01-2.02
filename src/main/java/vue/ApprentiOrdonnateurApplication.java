package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** la classe ApprentiOrdonnateurApplication permet de démarrer l'application */
public class ApprentiOrdonnateurApplication extends Application {

    /**
     * La méthode start est appelée lors du lancement de l'application.
     * @param stage la scène principale de l'application
     */
    public void start(Stage stage) {

        VBox root = new VBoxRoot();
        Scene scene = new Scene(root, 1000, 1000);
        stage.setScene(scene);
        stage.setTitle("Apprenti Ordonnateur");
        stage.show();

    }

    /**
     * Lance l'application JavaFX
     * @param args les arguments de la ligne de commande
     */
    public static void main (String [] args) {
        Application.launch(args);
    }
}
