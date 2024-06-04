package controleur;

import modele.Constantes;
import modele.Position;
import modele.Temple;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/** La classe LectureScenario permet de charger une liste de temple a partir d'un scénario */
public class LectureScenario implements Constantes {

    /**
     * méthode permettant de scanner le fichier donner en paramètre
     * @param fichierScenario fichier .txt contenant les informations sur les temples a charger
     * @return une Collection de temple
     */
    public static Collection <Temple> lecture(File fichierScenario) {
        Collection <Temple> templesDuScenario = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(fichierScenario);
            Temple temple;
            while (scanner.hasNext()) {
                int posX = scanner.nextInt();
                int posY = scanner.nextInt();
                int couleur = scanner.nextInt();
                int cristal = scanner.nextInt();
                temple = new Temple(new Position(posX,posY), couleur, cristal);
                templesDuScenario.add(temple);
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return templesDuScenario;
    }
}
