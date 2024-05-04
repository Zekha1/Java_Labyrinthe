import java.io.*;

/**
 * 
 * La classe EcritureFichierBinaire permet d'écrire une matrice sous forme
 * binaire dans un fichier.
 * 
 * @version 1.1
 * @author Héloïse Bousson
 * @author Loryne Laffea-Didot
 */
public class EcritureFichierBinaire {

    /**
    
    Cette méthode permet d'écrire une matrice sous forme binaire dans un fichier.

    @param matrice La matrice à écrire
    @param positionThésée La position de Thésée dans la matrice
    @param positionSortie La position de la sortie dans la matrice
    @param nomFichier Le nom du fichier dans lequel écrire la matrice
    @throws IOException si une erreur d'entrée/sortie se produit lors de l'écriture du fichier
    */
    public static void ecrireMatrice(int[][] matrice, Coordonnees positionThésée,Coordonnees positionSortie, String nomFichier) throws IOException {
        if (nomFichier == null) {
            return; // Sortir si aucun fichier n'a été sélectionné
        }

        int taille = matrice.length;
        File fichier = new File(nomFichier);

        try (FileOutputStream fos = new FileOutputStream(fichier)) {
            // Écriture de la taille de la matrice dans le premier octet
            fos.write(taille);

            // Écriture de la position de Thésée et la sortie
            fos.write(positionThésée.getX());
            fos.write(positionThésée.getY());
            fos.write(positionSortie.getX());
            fos.write(positionSortie.getY());

            // Écriture du contenu de la matrice colonne par colonne
            int nombreBitsRestants = 8;
            int bitActuel = 0;
            for (int colonne = 0; colonne < taille; colonne++) {
                for (int ligne = 0; ligne < taille; ligne++) {
                    int bit = matrice[ligne][colonne];
                    if (bit == 1) {
                        bitActuel |= (1 << (nombreBitsRestants - 1));
                    }
                    nombreBitsRestants--;
                    if (nombreBitsRestants == 0) {
                        fos.write(bitActuel);
                        nombreBitsRestants = 8;
                        bitActuel = 0;
                    }
                }
            }
            if (nombreBitsRestants < 8) {
                fos.write(bitActuel);
            }
        } catch (IOException e) {
            System.out.println("Erreur : impossible d'écrire dans le fichier " + nomFichier);
            throw e;
        }
    }  
}
