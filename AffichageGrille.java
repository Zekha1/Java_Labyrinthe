import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * 
 * Cette classe permet d'afficher la grille du jeu, Thésée et la sortie.
 *  
 * @version 1.1
 * @author Héloïse Bousson
 * @author Loryne Laffea-Didot
 */
public class AffichageGrille {

	/**
	 * La fenêtre qui contiendra la grille.
	 */
	private JFrame fenetre;

	/**
	 * Le bouton "suivant".
	 */
	private JButton suivantButton;

	/**
	 * Le nombre de clics sur le bouton "suivant".
	 */
	private int clic;

	/**
	 * Constructeur de la classe AffichageGrille.
	 * @param f La fenêtre qui contiendra la grille.
	 */
	public AffichageGrille(JFrame f) {
		this.fenetre = f;
	}

	/**
	 * Cette méthode permet d'afficher la grille du jeu, Thésée et la sortie.
	 * @param reader L'objet RecupFile qui contient les informations sur la grille.
	 * @param thésée L'objet Thesee qui représente Thésée.
	 */
	public void AfficherGrille(RecupFile reader, Thesee thésée/*, HistoriqueDeplacement cheminParcouru*/){

		int nombreLignes = reader.getNombreLignes();
		int nombreColonnes = reader.getNombreColonnes();
		fenetre.setSize(500, 500);
		fenetre.setLocation(0, 0);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// pour aller au suivant
		suivantButton = new JButton("Valider");
		suivantButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clic++ ;
			}
		});

		JPanel panneauBouton = new JPanel();
		JPanel panneauNumeroEtape = new JPanel();

		JLabel numeroEtape = new JLabel("ici sera le numéro de l'étape");

		JPanel panneauMatrice = new JPanel();
		GridLayout grille = new GridLayout(nombreLignes, nombreColonnes);
		panneauMatrice.setLayout(grille);

		int[][] matrice = reader.getMatrice();
		for (int i = 0; i < nombreLignes; i++) {
			for (int j = 0; j < nombreColonnes; j++) {
				if (matrice[i][j] == 1) {
					JPanel panneau = new JPanel(grille);
					Border lineborder = BorderFactory.createLineBorder(Color.WHITE, 1);
					panneau.setBackground(Color.BLACK);
					panneau.setBorder(lineborder);
					panneauMatrice.add(panneau);
				} else if (i == thésée.getPosition().getX() && j == thésée.getPosition().getY()) {
					// on met Thésée
					JPanel panneau = new JPanel(grille);
					Border lineborder = BorderFactory.createLineBorder(Color.BLACK, 1);						
					panneau.setBackground(Color.BLUE); //Il faudrait mettre l'image mais je n'ai pas fait
					panneau.setBorder(lineborder);
					panneauMatrice.add(panneau);
				} else if (i == reader.positionSortie.getX() && j == reader.positionSortie.getY()) {
					// on met la sortie
					JPanel panneau = new JPanel(grille);
					Border lineborder = BorderFactory.createLineBorder(Color.BLACK, 1);
					panneau.setBackground(Color.MAGENTA); //Il faudrait mettre l'image mais je n'ai pas fait
					panneau.setBorder(lineborder);
					panneauMatrice.add(panneau);
				} else {
					JPanel panneau = new JPanel(grille);
					Border lineborder = BorderFactory.createLineBorder(Color.BLACK, 1);
					panneau.setBackground(Color.WHITE);
					panneau.setBorder(lineborder);
					panneauMatrice.add(panneau);
				}
			}
		}
		panneauBouton.add(suivantButton);
		panneauNumeroEtape.add(numeroEtape);

		fenetre.add(panneauNumeroEtape, BorderLayout.NORTH);
		fenetre.add(panneauMatrice);
		fenetre.add(panneauBouton, BorderLayout.SOUTH);

		fenetre.setVisible(true);
	}

	/**
	 * Cette méthode retourne le nombre de clics sur le bouton "suivant".
	 * @return Le nombre de clics sur le bouton "suivant".
	 */
	public int getBoutonSuivant() {
		return this.clic;
	}
}
