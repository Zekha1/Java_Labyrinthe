/**
 * 
 * Cette classe contient la méthode principale qui permet de lancer
 * l'application du labyrinthe.
 * 
 * @version 1.1
 * @author Loryne Laffea-Didot
 * @author Héloïse Bousson
 */
public class MainLabyrinthe {

    /**
     * 
     * Méthode principale qui permet de lancer l'application du labyrinthe.
     * Elle instancie un objet de la classe Choix et appelle la méthode ChoixGrille
     * pour lancer l'interface graphique.
     * 
     * @param args les arguments de la ligne de commande (non utilisés dans cette
     *             application)
     */
    public static void main(String[] args) {
        Choix Grille = new Choix();
        Grille.ChoixGrille();
    }
}