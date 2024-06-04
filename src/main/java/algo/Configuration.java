package algo;

import modele.Temple;
import vue.VBoxRoot;
import java.util.ArrayList;
import static java.lang.Math.abs;

/** La classe Configuration représente des configuration de jeu avec la position de l'apprenti, la liste des cristaux dans
 * l'ordre des temples dans lesquels ils se trouvent, la distance parcourue pour attendre cette configuration depuis le
 * debut de la partie, le temple avec lequel on a eu un échange de cristaux depuis la dernière configuration, la
 * liste des temples et les configurations précédentes.
 */
public class Configuration implements Comparable<Configuration> {
    /** champ contenant la postion de l'apprenti*/
    private int[] positionApprenti;
    /** champ contenant la liste des cristaux*/
    private int[] listeCristaux;
    /** champ contenant la distance parcourue*/
    private int distance;
    /** champ contenant le numéro du temple avec lequel on échange un cristal*/
    private int templeEchange;
    /** champ contenant la liste des temples*/
    private ArrayList<Temple> listeTemples = VBoxRoot.getApprenti().getListeTemples();
    /** champ contenant les configurations précédentes*/
    private ArrayList<Configuration> configPrecedentes;

    /** Constructeur de la classe Configuration qui permet de créer les situation initiale
     */
    public Configuration() {
        positionApprenti = new int[]{0, 0};
        distance = 0;
        listeCristaux = new int[listeTemples.size() + 1];
        listeCristaux[0] = 0;
        configPrecedentes = new ArrayList<>();
        for (int i = 1; i < listeTemples.size(); i++) {
            listeCristaux[i] = listeTemples.get(i).getCouleurCristal();
        }
    }

    /** Constructeur de la classe Configuration pour toutes les autres configurations de la partie
     *
     * @param parlisteCristaux la liste des cristaux dans la configuration précédente
     * @param cristalEchange le cristal à échanger
     * @param templePrec le temple sur lequel se trouvait l'apprenti dans la configuration précédente
     * @param distancePrec la distance parcourue dans la configuration précédente
     * @param configPrec la liste des configurations précédentes
     */
    public Configuration(int[] parlisteCristaux, int cristalEchange, int templePrec, int distancePrec, ArrayList<Configuration> configPrec) {
        int temple = 0;
        distance = distancePrec;
        listeCristaux = parlisteCristaux;
        for (int cristal : listeCristaux) {
            if (cristal == cristalEchange) {
                templeEchange = temple;
            }
            temple++;
        }
        configPrecedentes = configPrec;
        positionApprenti = new int[]{listeTemples.get(templePrec).getPosition().getAbscisse(), listeTemples.get(templePrec).getPosition().getOrdonnee()};
        echangerCristaux(VBoxRoot.getApprenti().getCouleurCristal(), cristalEchange);
    }

    /** Ajoute une configuration au champ configPrecedentes
     *
     * @param configPrec la configuration à ajouter
     */
    public void addConfig(Configuration configPrec) {
        configPrecedentes.add(configPrec);
    }

    /** Vérifie si this est égale à configuration
     *
     * @param configuration la configuration à tester
     * @return true si leur liste de cristaux et la position de l'apprenti sont les mêmes et false sinon
     */
    public boolean equals(Configuration configuration) {
        for (int i = 0; i < listeCristaux.length; i++) {
            if (listeCristaux[i] != configuration.getListeCristaux()[i] || positionApprenti != configuration.getPositionApprenti()) {
                return false;
            }
        }
        return true;
    }

    /** echange les cristaux dans listeCristaux
     *
     * @param cristalApprenti le cristal actuellement porté par l'apprenti
     * @param cristalEchange le cristal actuellement dans le temple
     */
    private void echangerCristaux(int cristalApprenti, int cristalEchange) {
        listeCristaux[0] = cristalEchange;
        listeCristaux[templeEchange] = cristalApprenti;
    }

    /** Compare deux configurations grâce à leur distance parcourue
     *
     * @param configurationCompare the object to be compared.
     * @return un négatif si this précède configurationCompare, un positif si configurationCompare précède this
     * et 0 sinon
     */
    @Override
    public int compareTo(Configuration configurationCompare) {
        if (distance < configurationCompare.getDistance())
            return -1;
        else if (distance > configurationCompare.getDistance())
            return 1;
        else
            return 0;
    }

    /** Permet de mettre à jour la distance parcourue
     *
     * @param configActuelle la config précédente
     * @param posTemple la postion du temple sous la forme {x,y}
     */
    public void setDistance(Configuration configActuelle, int[] posTemple) {
        distance += abs(configActuelle.getPositionApprenti()[0] - posTemple[0]) + abs(configActuelle.getPositionApprenti()[1] - posTemple[1]);}

    /** Mutateur sur le champ positionApprenti qui modifie la position de l'apprenti pour la position du temple ou il se trouve
     *
     */
    public void setPositionApprenti() {
        int[] positionApprenti = {listeTemples.get(templeEchange).getPosition().getAbscisse(), listeTemples.get(templeEchange).getPosition().getOrdonnee()};
    }

    /** Accesseur sur le champ positionApprenti
     *
     * @return positionApprenti
     */
    public int[] getPositionApprenti() {
        return positionApprenti;
    }

    /** Accesseur sur le champ positionApprenti
     *
     * @return positionApprenti
     */
    public int[] getListeCristaux() {
        return listeCristaux;
    }

    /** Accesseur sur le champ distance
     *
     * @return distance
     */
    public int getDistance() {
        return distance;
    }

    /** Accesseur sur le champ templeEchange
     *
     * @return templeEchange
     */
    public int getTempleEchange() {
        return templeEchange;
    }

    /** Accesseur sur le champ configPrecedentes
     *
     * @return configPrecedentes
     */
    public ArrayList<Configuration> getConfigPrecedentes() {
        return configPrecedentes;
    }
}
