/**
 * 
 * La classe Thesee représente le personnage du jeu qui doit sortir du
 * labyrinthe.
 * 
 * @version 1.1
 * @author Loryne Laffea-Didot
 * @author Héloïse Bousson
 */
public class Thesee {
    /*
     * 
     * La position actuelle de Thesee dans le labyrinthe.
     */
    Coordonnees position;

    /**
     * 
     * Constructeur de la classe Thesee.
     * @param position la position initiale de Thesee dans le labyrinthe.
     */
    public Thesee(Coordonnees position) {
        this.position = position;
    }

    /**
     * 
     * Renvoie la position actuelle de Thesee dans le labyrinthe.
     * @return la position actuelle de Thesee.
     */
    public Coordonnees getPosition() {
        return this.position;
    }

    /**
     * 
     * Change la position de Thesee dans le labyrinthe.
     * @param nouvellePosition la nouvelle position de Thesee.
     */
    public void nouvellePosition(Coordonnees nouvellePosition) {
        this.position.setX(nouvellePosition.getX());
        this.position.setY(nouvellePosition.getY());
    }
}