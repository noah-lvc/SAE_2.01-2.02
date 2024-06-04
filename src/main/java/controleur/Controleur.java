package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import modele.Temple;
import vue.VBoxRoot;
import java.io.File;
import java.util.Collection;

/** La Classe Controleur permet de controleur l'action du menu */
public class Controleur implements EventHandler {
    @Override
    public void handle(Event event) {
        Object userData = ((MenuItem)event.getSource()).getUserData();
        if (userData instanceof File) { // l'ut. a choisi un scénario
            File fichierScenario = (File) userData;
            System.out.println(fichierScenario.getName());
            File scenario = fichierScenario;
            Collection <Temple> temples = LectureScenario.lecture(fichierScenario);
            VBoxRoot.getApprenti().setTemples(temples);
            System.out.println(VBoxRoot.getApprenti());
            VBoxRoot.getCanva().clearCarte();
            VBoxRoot.getCanva().dessinerCarte();
            VBoxRoot.getCanva().dessinerTemples();
        }
    }
}


