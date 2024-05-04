import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Cette classe permet de sélectionner un fichier pour la lecture ou l'écriture à partir d'une boîte de dialogue JFileChooser.
 * 
 *  @version 1.1
 *  @author Héloïse Bousson
 *  @author Loryne Laffea-Didot
 */
public class ChoisirFichier {

    /**
     * La boîte de dialogue JFileChooser utilisée pour sélectionner un fichier.
     */
    JFileChooser fichierChoisi;

    /**
     * Constructeur par défaut qui initialise la boîte de dialogue JFileChooser.
     */
    public ChoisirFichier(){
        this.fichierChoisi = new JFileChooser();
    }

    /**
     * Affiche une boîte de dialogue JFileChooser pour permettre à l'utilisateur de sélectionner un fichier binaire à lire.
     *
     * @return Le chemin absolu du fichier sélectionné, ou null si aucun fichier n'a été sélectionné.
     */
    public String choisirUnFichierLecture() {
		JFrame frame = new JFrame();

		// Ajouter un filtre de fichier pour ne montrer que les fichiers binaires .bin
		// et .lab
		FileNameExtensionFilter filtre = new FileNameExtensionFilter("Fichiers binaires", "bin", "lab");
		fichierChoisi.setFileFilter(filtre);

		int result = fichierChoisi.showOpenDialog(frame);
		if (result == JFileChooser.APPROVE_OPTION) {
			String fichierSelectionne = fichierChoisi.getSelectedFile().getAbsolutePath();
			return fichierSelectionne;
		} else {
			System.err.println("Aucun fichier binaire sélectionné.");
			return null;
		}
    }

    /**
     * Affiche une boîte de dialogue JFileChooser pour permettre à l'utilisateur de sélectionner un fichier binaire à écrire.
     *
     * @return Le chemin absolu du fichier sélectionné, ou null si aucun fichier n'a été sélectionné.
     */
    public String choisirUnFichierEcriture() {

        // Ajouter un filtre de fichier pour ne montrer que les fichiers binaires .bin .lab
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers binaires", "bin", "lab");
        fichierChoisi.setFileFilter(filter);

        int result = fichierChoisi.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fichierChoisi.getSelectedFile();
            return selectedFile.getAbsolutePath();
        } else {
            System.err.println("Aucun fichier sélectionné.");
            return null;
        }
    }
}
