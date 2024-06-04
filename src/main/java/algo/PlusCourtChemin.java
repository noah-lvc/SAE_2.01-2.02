package algo;

import javafx.geometry.Pos;
import modele.Position;
import modele.Temple;
import vue.VBoxRoot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeSet;

/** Calcule de la manière la plus optimale le chemin à parcourir*/
public class PlusCourtChemin {
    /** Contient les configurations de jeu déjà visitées*/
    private ArrayList<Configuration> dejaVisite;
    /** Contient les configurations de jeu déjà visité qui ont été testées*/
    private ArrayList<Configuration> dejaTeste;
    /** File de priorité des configurations*/
    private PriorityQueue<Configuration> filePrio;
    /** nombre de pas au total */
    private int nbPas;

    /** Constructeur de la classe PlusCourtChemin
     *
     */
    public PlusCourtChemin() {
        ArrayList<Temple> listeTemples = VBoxRoot.getApprenti().getListeTemples();
        filePrio = new PriorityQueue<>();
        dejaVisite = new ArrayList<>();
        dejaTeste = new ArrayList<>();
     
        filePrio.add(new Configuration());


        while (filePrio.size() != 0) {
            Configuration configActuelle = filePrio.poll();
            int [] listeCristaux = configActuelle.getListeCristaux();
            for (int cristal : listeCristaux) {
                if (cristal != 0) {
                    Configuration configNouvelle = new Configuration(listeCristaux, cristal, configActuelle.getTempleEchange(), configActuelle.getDistance(), configActuelle.getConfigPrecedentes());
                    int [] positionEchange = {listeTemples.get(configActuelle.getTempleEchange()).getPosition().getAbscisse(),
                            listeTemples.get(configActuelle.getTempleEchange()).getPosition().getOrdonnee()};
                    configNouvelle.setDistance(configActuelle, positionEchange);
                    configNouvelle.setPositionApprenti();
                    configNouvelle.addConfig(configActuelle);
                    boolean visite = false;
                    boolean remplace = false;
                    Iterator<Configuration> iterateur = dejaVisite.iterator();
                    while (iterateur.hasNext()) {
                        Configuration configTest = iterateur.next();
                        if (configNouvelle.equals(configTest)) {
                            visite = true;
                            if (configNouvelle.compareTo(configTest) < 0) {
                                remplace = true;
                                dejaVisite.clear();
                            }
                        }
                    }
                    if (!visite  || remplace) {
                        filePrio.add(configNouvelle);
                    }
                }
            }
            dejaVisite.add(configActuelle);
        }
        parcoursListe();
//        Iterator<Configuration> iterateur = dejaVisite.iterator();
//        while (iterateur.hasNext()) {
//            Configuration config = iterateur.next();
//            Position positionApprenti = new Position(config.getPositionApprenti()[0], config.getPositionApprenti()[1]);
//            Position positionTemple = listeTemples.get(config.getTempleEchange()).getPosition();
//            VBoxRoot.getCanva().deplacementPriorityQueue(positionApprenti, positionTemple);
//        }
//

    }

    /** Parcours la liste pour pouvoir effectuer les déplacements
     *
     */
    public void parcoursListe () {
        Configuration id = dejaVisite.get(dejaTeste.size());
        dejaTeste.add(id);
        Position positionApprenti = new Position(id.getPositionApprenti()[0], id.getPositionApprenti()[1]);

        Position positionTemple = VBoxRoot.getApprenti().getListeTemples().get(id.getTempleEchange()).getPosition();
        System.out.println(positionTemple);
        VBoxRoot.getCanva().deplacementPriorityQueue(VBoxRoot.getApprenti().getPositionApprenti(), positionTemple);
    }

}
