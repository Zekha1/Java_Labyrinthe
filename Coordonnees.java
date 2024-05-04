/**
 * 
 * La classe Coordonnees représente les coordonnées (x,y) d'une case dans une
 * grille.
 * 
 * @version 1.1
 * @author Loryne Laffea-Didot
 * @author Héloïse Bousson
 */
public class Coordonnees {
    /** La coordonnée x de la case dans la matrice */
    private int x;
    /** La coordonnée y de la case dans la matrice */
    private int y;

    /**
     * 
     * Constructeur de la classe Coordonnees.
     * 
     * @param x La coordonnée x de la case
     * @param y La coordonnée y de la case
     */
    public Coordonnees(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * Renvoie la coordonnée x de la case.
     * 
     * @return La coordonnée x de la case
     */
    public int getX() {
        return x;
    }

    /**
     * 
     * Modifie la coordonnée x de la case.
     * 
     * @param x La nouvelle coordonnée x de la case
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 
     * Renvoie la coordonnée y de la case.
     * 
     * @return La coordonnée y de la case
     */
    public int getY() {
        return y;
    }

    /**
     * 
     * Modifie la coordonnée y de la case.
     * 
     * @param y La nouvelle coordonnée y de la case
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * 
     * Vérifie si les coordonnées passées en paramètre sont les mêmes que celles de
     * l'instance actuelle.
     * 
     * @param position Les coordonnées à vérifier
     * @return 1 si les coordonnées sont les mêmes, 0 sinon
     */
    public int memeCoordonees(Coordonnees position) {
        int siPareil = 0;
        if ((this.x == position.getX()) && (this.y == position.getY())) {
            siPareil = 1;
        }
        return siPareil;
    }
}