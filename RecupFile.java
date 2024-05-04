import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 
 * Cette classe permet de récupérer les données d'un fichier binaire contenant
 * une grille de labyrinthe.
 * 
 * @version 1.1
 * @author Héloïse Bousson
 * @author Loryne Laffea-Didot
 */
public class RecupFile {

    /**
     * 
     * La matrice de la grille de labyrinthe.
     */
    private int[][] matriceGrille;
    /**
     * 
     * Le nombre de lignes de la grille de labyrinthe.
     */
    private int nombreLignes;
    /**
     * 
     * Le nombre de colonnes de la grille de labyrinthe.
     */
    private int nombreColonnes;
    /**
     * 
     * La position de Thésée dans la grille de labyrinthe.
     */
    Coordonnees positionThésée;
    /**
     * 
     * La position de la sortie dans la grille de labyrinthe.
     */
    Coordonnees positionSortie;

    /**
     * 
     * Le constructeur de la classe RecupFile.
     * 
     * @param filename le nom du fichier binaire contenant la grille de labyrinthe.
     * @throws IOException si une erreur d'entrée/sortie se produit.
     */
    public RecupFile(String filename) throws IOException {
        // ouvre le fichier
        File fichier = new File(filename);
        FileInputStream fis = new FileInputStream(fichier);

        // on récupère la taille de la matrice avec le premier octet
        int tailleMatrice = fis.read();
        this.nombreLignes = tailleMatrice;
        this.nombreColonnes = tailleMatrice;

        // on crée la matrice
        this.matriceGrille = new int[nombreLignes][nombreColonnes];

        // mettre le 3 et le 4 pour Thésée et pour la sortie
        int x_Thesee = fis.read();
        int y_Thesee = fis.read();
        int x_Sortie = fis.read();
        int y_Sortie = fis.read();
        positionThésée = new Coordonnees(x_Thesee, y_Thesee);
        positionSortie = new Coordonnees(x_Sortie, y_Sortie);
        // on lit le reste
        int octet;
        int indexLigne = 0;
        int indexColonne = 0;
        while ((octet = fis.read()) != -1 && indexColonne < nombreColonnes) {
            // bit a bit dans l'octet
            for (int i = 7; i >= 0; i--) {
                int Bit = (octet >>> i) & 1;
                this.matriceGrille[indexLigne][indexColonne] = Bit;
                indexLigne++;
                if (indexLigne == nombreLignes) {
                    indexColonne++;
                    indexLigne = 0;
                }
                if (indexColonne == nombreColonnes) {
                    break;
                }
            }
        }

        // on ferme le fichier => rajouter un try catch ?
        fis.close();
    }

    /**
     * 
     * Getter pour la matrice de la grille de labyrinthe.
     * @return la matrice de la grille de labyrinthe.
     */
    public int[][] getMatrice() {
        return this.matriceGrille;
    }

    /**
     * 
     * Getter pour le nombre de lignes de la grille de labyrinthe.
     * @return le nombre de lignes de la grille de labyrinthe.
     */
    public int getNombreLignes() {
        return this.nombreLignes;
    }

    /**
     * 
     * Cette méthode permet d'obtenir le nombre de colonnes de la matrice de la
     * grille.
     * @return le nombre de colonnes de la matrice de la grille
     */
    public int getNombreColonnes() {
        return this.nombreColonnes;
    }

    /**
     * 
     * Cette méthode permet d'obtenir la position de Thésée dans la grille.
     * @return la position de Thésée dans la grille
     */
    public Coordonnees getPositionThesee() {
        return this.positionThésée;
    }

    /**
     * 
     * Cette méthode permet d'obtenir la position de la sortie dans la grille.
     * @return la position de la sortie dans la grille
     */
    public Coordonnees getPositionSortie() {
        return this.positionSortie;
    }
}
