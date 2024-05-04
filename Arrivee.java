/**
 * La classe Arrivee représente la position de l'arrivée dans le labyrinthe.
 * Elle contient une Coordonnees qui correspond à la position de l'arrivée dans la grille.
 * 
 *  @version 1.1
 *  @author Loryne Laffea-Didot
 *  @author Héloïse Bousson
 */

public class Arrivee {
    Coordonnees position;
    public Arrivee(Coordonnees position){
        this.position = position;
    }
    public Coordonnees getPosition(){
        return this.position;
    }
}
