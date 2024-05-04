import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * 
 * La classe Choix implémente l'interface ActionListener et est utilisée pour
 * gérer les choix de l'utilisateur.
 * 
 * @version 1.1
 * @author Loryne Laffea-Didot
 * @author Héloïse Bousson
 */
public class Choix implements ActionListener {

	/**
	 * 
	 * La fenêtre de l'application.
	 */
	JFrame fenetre;

	/**
	 * 
	 * Indique si l'utilisateur a choisi un mode aléatoire ou déterministe pour la
	 * génération de la grille.
	 */
	private int AleaOuDec;

	/**
	 * 
	 * Indique si l'utilisateur a choisi un mode manuel ou automatique pour la
	 * résolution de la grille.
	 */
	private int ManOuAuto;

	/**
	 * 
	 * Indique si l'utilisateur a choisi de créer une nouvelle grille ou d'importer
	 * une grille existante.
	 */
	private int NouvelleOuExistante;

	/**
	 * 
	 * Indique si l'utilisateur a choisi une grille blanche ou pré-remplie.
	 */
	private int BlancheOuPreRemplie;

	/**
	 * 
	 * Indique le que l'utilisateur a fait son choix
	 */
	private int ChoixFait;

	/**
	 * 
	 * Bouton pour créer une nouvelle grille.
	 */
	private JButton boutonNewGrille;

	/**
	 * 
	 * Bouton pour importer une grille existante.
	 */
	private JButton boutonImportGrille;

	/**
	 * 
	 * Bouton pour créer une grille blanche.
	 */
	private JButton boutonGrilleBlanche;

	/**
	 * 
	 * Bouton pour créer une grille pré-remplie.
	 */
	private JButton boutonGrillePreRemplie;

	/**
	 * 
	 * Bouton pour choisir le mode aléatoire pour la résolution de la grille.
	 */
	private JButton boutonAleatoire;

	/**
	 * 
	 * Bouton pour choisir le mode déterministe pour la résolution de la grille.
	 */
	private JButton boutonDeterministe;

	/**
	 * 
	 * Bouton pour choisir le mode manuel pour la résolution de la grille.
	 */
	private JButton boutonManuel;

	/**
	 * 
	 * Bouton pour choisir le mode automatique pour la résolution de la grille.
	 */
	private JButton boutonAutomatique;

	/**
	 * 
	 * Le nom du fichier contenant la grille importée.
	 */
	String nomFichier;

	/**
	 * 
	 * La fenêtre permettant à l'utilisateur de choisir un fichier à importer.
	 */
	ChoisirFichier fichierChoisi;

	/**
	 * 
	 * Constructeur par défaut de la classe Choix.
	 * Initialise les attributs de la classe.
	 */
	public Choix() {
		fichierChoisi = new ChoisirFichier();
		this.fenetre = new JFrame();
		this.ChoixFait = 0;
	}

	/**
	 * 
	 * Cette classe permet de créer une fenêtre de choix pour l'utilisateur
	 */
	public void ChoixAlgo() {
		fenetre.setSize(500, 500);
		fenetre.setLocation(0, 0);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridLayout grille = new GridLayout(3, 1);

		fenetre.setLayout(grille);

		JPanel panneau = new JPanel(grille);
		panneau.setBorder(new EmptyBorder(20, 20, 20, 20)); // ajoute une marge autour du panneau

		JLabel texte = new JLabel("Quel algorithme voulez-vous utiliser ?");
		texte.setHorizontalAlignment(JLabel.CENTER);
		panneau.add(texte, BorderLayout.CENTER);
		fenetre.add(panneau);

		boutonAleatoire = new JButton("Aléatoire");
		boutonAleatoire.addActionListener(this);

		boutonDeterministe = new JButton("Deterministe");
		boutonDeterministe.addActionListener(this);

		panneau.add(boutonAleatoire);
		fenetre.add(panneau);
		panneau.add(boutonDeterministe);
		fenetre.add(panneau);
		fenetre.setVisible(true);
	}

	/**
	 * 
	 * Affiche une fenêtre permettant à l'utilisateur de choisir entre créer une
	 * nouvelle grille ou importer une grille existante.
	 * La fenêtre contient un panneau avec un texte "Choisissez votre grille :",
	 * deux boutons ("Nouvelle Grille" et "Grille Existante"), et une marge autour
	 * du panneau.
	 */
	public void ChoixGrille() {
		fenetre.setSize(500, 500);
		fenetre.setLocation(0, 0);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridLayout grille = new GridLayout(3, 1);
		fenetre.setLayout(grille);

		JPanel panneau = new JPanel(grille);
		panneau.setBorder(new EmptyBorder(20, 20, 20, 20)); // ajoute une marge autour du panneau

		JLabel texte = new JLabel("Choisissez votre grille :");
		texte.setHorizontalAlignment(JLabel.CENTER);
		panneau.add(texte, BorderLayout.CENTER);
		fenetre.add(panneau);

		boutonNewGrille = new JButton("Nouvelle Grille");
		boutonNewGrille.addActionListener(this);

		boutonImportGrille = new JButton("Grille Existante");
		boutonImportGrille.addActionListener(this);

		panneau.add(boutonNewGrille);
		fenetre.add(panneau);
		panneau.add(boutonImportGrille);
		fenetre.add(panneau);
		fenetre.setVisible(true);
	}

	/**
	 * 
	 * Affiche une fenêtre permettant à l'utilisateur de choisir entre le mode
	 * manuel et le mode automatique.
	 * La fenêtre contient un panneau avec un texte "Choisissez votre grille :",
	 * deux boutons ("Nouvelle Grille" et "Grille Existante"), et une marge autour
	 * du panneau.
	 */
	public void ChoixMode() {
		fenetre.setSize(500, 500);
		fenetre.setLocation(0, 0);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridLayout grille = new GridLayout(3, 1);

		fenetre.setLayout(grille);

		JPanel panneau = new JPanel(grille);
		panneau.setBorder(new EmptyBorder(20, 20, 20, 20)); // ajoute une marge autour du panneau
		JLabel texte = new JLabel("Préferez-vous le mode manuel ou automatique ?");
		texte.setHorizontalAlignment(JLabel.CENTER);
		panneau.add(texte);

		boutonManuel = new JButton("Manuel");
		boutonManuel.addActionListener(this);

		boutonAutomatique = new JButton("Automatique");
		boutonAutomatique.addActionListener(this);

		panneau.add(boutonManuel);
		panneau.add(boutonAutomatique);
		fenetre.add(panneau);
		fenetre.setVisible(true);
	}

	/**
	 * 
	 * Affiche une fenêtre permettant à l'utilisateur de choisir entre créer une
	 * nouvelle grille au départ blanche ou créer une nouvelle grille au départ
	 * remplie aléatoirement.
	 * La fenêtre contient un panneau avec un texte "Choisissez votre grille :",
	 * deux boutons ("Nouvelle Grille" et "Grille Existante"), et une marge autour
	 * du panneau.
	 */
	public void ChoixTypeGrille() {
		fenetre.setSize(500, 500);
		fenetre.setLocation(0, 0);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridLayout grille = new GridLayout(3, 1);
		fenetre.setLayout(grille);

		JPanel panneau = new JPanel(grille);
		panneau.setBorder(new EmptyBorder(20, 20, 20, 20)); // ajoute une marge autour du panneau

		JLabel texte = new JLabel("Préférez-vous une grille blanche ou pré-remplie ?");
		texte.setHorizontalAlignment(JLabel.CENTER);
		panneau.add(texte, BorderLayout.CENTER);
		fenetre.add(panneau);

		boutonGrilleBlanche = new JButton("Grille Blanche");
		boutonGrilleBlanche.addActionListener(this);

		boutonGrillePreRemplie = new JButton("Grille Pré-remplie");
		boutonGrillePreRemplie.addActionListener(this);

		panneau.add(boutonGrilleBlanche);
		fenetre.add(panneau);
		panneau.add(boutonGrillePreRemplie);
		fenetre.add(panneau);
		fenetre.setVisible(true);
	}

	/**
	 * 
	 * Retourne le mode de résolution de la grille (manuel ou automatique).
	 * 
	 * @return Le mode de résolution de la grille (1 pour manuel, 0 pour
	 *         automatique).
	 */
	public int getMode() {
		return ManOuAuto;
	}

	/**
	 * 
	 * Retourne l'algorithme de résolution choisi (aléatoire ou déterministe).
	 * 
	 * @return L'algorithme de résolution choisi (1 pour aléatoire, 0 pour
	 *         déterministe).
	 */
	public int getAlgo() {
		return AleaOuDec;
	}

	/**
	 * 
	 * Retourne le type de grille choisi (nouvelle ou existante).
	 * 
	 * @return Le type de grille choisi (1 pour existante, 0 pour nouvelle).
	 */
	public int getGrille() {
		return NouvelleOuExistante;
	}

	/**
	 * 
	 * Retourne le type de grille pré-remplie choisi (blanche ou aléatoire).
	 * 
	 * @return Le type de grille pré-remplie choisi (1 pour blanche, 0 pour
	 *         aléatoire).
	 */
	public int getTypeGrille() {
		return BlancheOuPreRemplie;
	}

	/**
	 * 
	 * Retourne le choix de grille effectué (0 pour aucun choix, 1 pour choix
	 * effectué).
	 * 
	 * @return Le choix de grille effectué.
	 */
	public int getChoixGrille() {
		return ChoixFait;
	}

	/**
	 * 
	 * Initialise le choix de grille à 0.
	 */
	public void initChoixGrille() {
		this.ChoixFait = 0;
	}

	/**
	 * 
	 * Override la méthode actionPerformed du ActionListener interface pour
	 * gérer le clic
	 * 
	 * 
	 * @param evenement l'évènement généré par le clic sur les boutons
	 */
	@Override
	public void actionPerformed(ActionEvent evenement) {
		String texteBouton = evenement.getActionCommand();
		// pour ChoixAlgo
		if (texteBouton.equals("Aléatoire")) { // Si on clique sur aléatoire
			boutonAleatoire.setEnabled(false);
			boutonDeterministe.setEnabled(false);
			ChoixMode();
			this.ChoixFait = 1;
			AleaOuDec = 1;
		}
		if (texteBouton.equals("Deterministe")) { // si on clique sur déterministe
			boutonAleatoire.setEnabled(false);
			boutonDeterministe.setEnabled(false);
			ChoixMode();
			this.ChoixFait = 1;
			AleaOuDec = 0;
		}
		// pour ChoixGrille
		if (texteBouton.equals("Grille Existante")) { // Si on clique sur grille existante
			boutonImportGrille.setEnabled(false);
			boutonNewGrille.setEnabled(false);
			nomFichier = fichierChoisi.choisirUnFichierLecture();
			ChoixAlgo();
			this.ChoixFait = 1;
			NouvelleOuExistante = 1;
		}
		if (texteBouton.equals("Nouvelle Grille")) { // si on clique sur nouvelle grille
			boutonImportGrille.setEnabled(false);
			boutonNewGrille.setEnabled(false);
			ChoixTypeGrille();
			this.ChoixFait = 1;
			NouvelleOuExistante = 0;
		}
		// pour ChoixMode
		if (texteBouton.equals("Manuel")) { // Si on clique sur manuel
			boutonManuel.setEnabled(false);
			boutonAutomatique.setEnabled(false);
			this.ChoixFait = 1;
			ManOuAuto = 1;
			Algorithme algo = new Algorithme(AleaOuDec, ManOuAuto);
			algo.lancerAlgorithme(nomFichier);
		}
		if (texteBouton.equals("Automatique")) { // si on clique sur automatique
			boutonManuel.setEnabled(false);
			boutonAutomatique.setEnabled(false);
			Algorithme algo = new Algorithme(AleaOuDec, ManOuAuto);
			algo.lancerAlgorithme(nomFichier);
			this.ChoixFait = 1;
			ManOuAuto = 0;
		}
		// pour choixTypeGrille
		if (texteBouton.equals("Grille Blanche")) { // Si on clique sur grille blanche
			boutonGrilleBlanche.setEnabled(false);
			boutonGrillePreRemplie.setEnabled(false);
			ChoixAlgo();
			NouvelleGrille nouvelleGrille = new NouvelleGrille();
			nouvelleGrille.NouvelleGrilleBlanche();
			nomFichier = nouvelleGrille.getNomFichier();
			BlancheOuPreRemplie = 1;
			this.ChoixFait = 1;
		}
		if (texteBouton.equals("Grille Pré-remplie")) { // si on clique sur grille pré-remplie
			boutonGrilleBlanche.setEnabled(false);
			boutonGrillePreRemplie.setEnabled(false);
			ChoixAlgo();
			NouvelleGrille nouvelleGrille = new NouvelleGrille();
			nouvelleGrille.NouvelleGrilleAleatoire();
			BlancheOuPreRemplie = 0;
			this.ChoixFait = 1;
		}

	}
}
