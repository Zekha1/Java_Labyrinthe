import java.io.IOException;
import javax.swing.*;
import java.awt.*;

/**
 * 
 * La classe Algorithme représente l'algorithme utilisé pour résoudre le
 * labyrinthe.
 * Elle peut utiliser deux algorithmes différents : aléatoire ou décisionnel.
 * 
 * @version 1.1
 * @author Loryne Laffea-Didot
 * @author Héloïse Bousson
 */
public class Algorithme {
    int siAlea;
    int siMan;
    Thesee thésée;
    Arrivee sortie;
    Coordonnees demiTour;

    /**
     * 
     * Constructeur de la classe Algorithme.
     * 
     * @param Alea le choix de l'algorithme (0 pour décisionnel, 1 pour aléatoire).
     * @param Man  le choix du mode (0 pour automatique, 1 pour manuel).
     */
    public Algorithme(int Alea, int Man) {
        this.siAlea = Alea;
        this.siMan = Man;
        demiTour = new Coordonnees(-1, -1);
    }

    /**
    
    Méthode qui lance l'algorithme pour résoudre le labyrinthe.
    @param fichierChoisi le nom du fichier contenant la grille du labyrinthe.
    */
    public void lancerAlgorithme(String fichierChoisi) {
    Deplacement trajet = new Deplacement();
    Coordonnees direction = new Coordonnees(0, 0);
    int coups = 0;
    int moyenneCoups = 0;
    RecupFile reader;
    try {
    reader = new RecupFile(fichierChoisi);
    thésée = new Thesee(reader.getPositionThesee());
    sortie = new Arrivee(reader.getPositionSortie());
    if (siMan == 0) { // Si c'est en automatique
    if (siAlea == 1) { // si c'est l'algo auto
    for (int i = 0; i < 50; i++) {
    while (thésée.getPosition().memeCoordonees(sortie.getPosition()) == 0) {
    direction = trajet.directionAleatoire(reader.getMatrice(), reader.getNombreColonnes(),
    thésée);
    trajet.seDeplacer(direction, thésée);
    coups++;
    }
    moyenneCoups = moyenneCoups + coups;
    }
    moyenneCoups = moyenneCoups / 50;
    }
    else { // si c'est l'algo décisionnel
    while (/*thésée.getPosition().memeCoordonees(sortie.getPosition()) == 0*/coups<35) {
    direction = trajet.choixDirection(reader.getMatrice(), reader.getNombreColonnes(), thésée);
    trajet.seDeplacer(direction, thésée);
    coups++;
    }
    }
    JFrame fenetre = new JFrame();
    fenetre.setSize(500, 500);
    fenetre.setLocation(0, 0);
    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    String nbCoups = Integer.toString(coups);
    JLabel texte = new JLabel("Grille terminée en " + nbCoups + " coups");
    texte.setHorizontalAlignment(JLabel.CENTER);
    fenetre.add(texte, BorderLayout.CENTER);
    fenetre.setVisible(true);
    }
    else { // si c'est en mode manuel
    JFrame fenetre = new JFrame();
    AffichageGrille Grille = new AffichageGrille(fenetre);
    int clic = 0;
    if (siAlea == 1) { // si c'est l'algo auto
    while (thésée.getPosition().memeCoordonees(sortie.getPosition()) == 0) {
    Grille.AfficherGrille(reader, thésée/*, trajet.getHistorique()*/);
                        if (clic == coups){
                            direction = trajet.directionAleatoire(reader.getMatrice(), reader.getNombreColonnes(),
                                thésée);
                            trajet.seDeplacer(direction, thésée);
                            coups++;
                            fenetre.setVisible(true);
                        }
                        clic = Grille.getBoutonSuivant();
                    }
                } else { // si c'est l'algo décisionnel
                    while (thésée.getPosition().memeCoordonees(sortie.getPosition()) == 0) {
                        direction = trajet.choixDirection(reader.getMatrice(), reader.getNombreColonnes(), thésée);
                        if (direction.memeCoordonees(demiTour)==0) {
                            trajet.seDeplacer(direction, thésée);
                        }
                        else {
                            trajet.demiTour(thésée,direction);
                        }
                        coups++;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur de lecture : " + e.getMessage());
        }
    }
}