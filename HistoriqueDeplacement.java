/**
 * 
 * Classe représentant l'historique des déplacements effectués.
 * 
 * @version 1.1
 * @author Loryne Laffea-Didot
 * @author Héloïse Bousson
 */
public class HistoriqueDeplacement {

    /**
     * Tableau de coordonnées représentant la pile d'historique des déplacements.
     */
    private Coordonnees[] pile;

    /** Index du sommet de la pile. */
    private int sommet;

    /**
     * 
     * Constructeur de la classe HistoriqueDeplacement.
     * Initialise la pile d'historique avec une taille initiale de 10 et le sommet à
     * -1.
     */
    public HistoriqueDeplacement() {
        this.pile = new Coordonnees[10];
        sommet = -1;
    }

    /**
     * 
     * Ajoute un déplacement à la pile d'historique.
     * Si la pile est pleine, elle est agrandie.
     * 
     * @param emplacement Les coordonnées de l'emplacement où le déplacement a été
     *                    effectué.
     */
    public void ajouterDeplacement(Coordonnees emplacement) {
        if (sommet == this.pile.length - 1) {
            agrandirPile();
        }
        pile[++sommet] = emplacement;
    }

    /**
     * 
     * Retire le dernier déplacement ajouté à la pile d'historique.
     * 
     * @return Les coordonnées du dernier déplacement.
     * @throws IllegalStateException Si la pile est vide.
     */
    public Coordonnees retirerDeplacement() {
        if (sommet == -1) {
            throw new IllegalStateException("La pile est vide");
        }
        Coordonnees deplacementRetire = this.pile[sommet];
        sommet -= 1;
        return deplacementRetire;
    }

    /**
     * 
     * Retourne les coordonnées du dernier déplacement ajouté à la pile
     * d'historique.
     * 
     * @return Les coordonnées du dernier déplacement.
     * @throws IllegalStateException Si la pile est vide.
     */
    public Coordonnees regarderDernierDeplacement() {
        if (sommet == -1) {
            throw new IllegalStateException("La pile est vide");
        }
        return this.pile[sommet];
    }

    /**
     * 
     * Vérifie si la pile d'historique est vide.
     * 
     * @return true si la pile est vide, false sinon.
     */
    public boolean pileVide() {
        return sommet == -1;
    }

    /**
     * 
     * Retourne la taille de la pile d'historique.
     * 
     * @return La taille de la pile.
     */
    public int taillePile() {
        return sommet + 1;
    }

    /**
     * 
     * Agrandit la taille de la pile d'historique en la multipliant par deux.
     */
    private void agrandirPile() {
        Coordonnees[] nouvellePile = new Coordonnees[pile.length * 2];
        System.arraycopy(pile, 0, nouvellePile, 0, pile.length);
        pile = nouvellePile;
    }

    /**
     * 
     * Cherche des coordonnées données dans la pile d'historique.
     * 
     * @param aChercher Les coordonnées à chercher dans la pile.
     * @return 1 si les coordonnées sont présentes dans la pile, 0 sinon.
     */
    public int chercherDansLaPile(Coordonnees aChercher) {
        int siPresent = 0;
        int taillePile = this.pile.length;
        if (pileVide() == false) {
            for (int i = 0; i < (taillePile); i++) {
                if (pile[i] != null) {
                    Coordonnees coordonnees = new Coordonnees(this.pile[i].getX(), this.pile[i].getY());
                    if (coordonnees.memeCoordonees(aChercher) == 1) {
                        // la coordonnées est dans la pile
                        siPresent = 1;
                    }
                }
            }
        }
        return siPresent;
    }
}
