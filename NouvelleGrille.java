
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * 
 * Classe représentant une nouvelle grille de jeu.
 * Cette classe hérite de la classe JFrame.
 * Elle est composée de différents éléments nécessaires à la construction d'une
 * grille de jeu :
 * des JPanel pour représenter les cellules de la grille, une matrice
 * représentant l'état des cellules,
 * des boutons de validation et de retour, la position du personnage principal
 * Thésée et celle de la sortie,
 * ainsi que des informations sur les dimensions de la grille et le nom du
 * fichier de sauvegarde.
 * 
 * @version 1.1
 * @author Héloïse Bousson
 * @author Loryne Laffea-Didot
 */
public class NouvelleGrille extends JFrame {
    /**
     * 
     * Le tableau de panneaux représentant la grille.
     */
    private JPanel[][] panneau;
    /**
     * 
     * La fenêtre principale.
     */
    private JFrame fenetre;
    /**
     * 
     * La matrice représentant la grille.
     */
    private int[][] matrice;
    /**
     * 
     * Un entier permettant de savoir si la grille est modifiable.
     */
    private int modifiable = 0;
    /**
     * 
     * Le bouton pour valider la grille.
     */
    private JButton validerButton;
    /**
     * 
     * Le bouton pour retourner à la fenêtre précédente.
     */
    private JButton retourButton;
    /**
     * 
     * Les coordonnées de la position de Thésée.
     */
    private Coordonnees positionThésée;
    /**
     * 
     * Les coordonnées de la position de la sortie.
     */
    private Coordonnees positionSortie;
    /**
     * 
     * Le nom du fichier de sauvegarde de la grille.
     */
    private String nomFichier;
    /**
     * 
     * Le nombre de lignes de la grille.
     */
    int lignes;
    /**
     * 
     * Le nombre de colonnes de la grille.
     */
    int colonnes;
    /**
     * 
     * L'objet permettant de choisir le fichier de sauvegarde de la grille.
     */
    ChoisirFichier fichierChoisi;

    /**
     * 
     * Construit une nouvelle grille en initialisant sa taille à partir de la saisie
     * de l'utilisateur et en permettant à l'utilisateur de choisir le fichier où
     * sauvegarder la grille.
     */
    public NouvelleGrille() {
        super("Grille");
        boolean valide = false;
        while (!valide) {
            String saisieLignes = JOptionPane.showInputDialog("Veuillez saisir la taille de la matrice :");
            try {
                this.lignes = Integer.parseInt(saisieLignes);
                this.colonnes = lignes;
                valide = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erreur : veuillez saisir un nombre entier.");
            }
        }
        ChoisirFichier fichierChoix = new ChoisirFichier();
        nomFichier = fichierChoix.choisirUnFichierEcriture();
    }

    /**
     * 
     * Cette méthode crée une nouvelle grille de jeu aléatoire de taille lignes x
     * colonnes. Elle permet également de
     * placer Thésée et la sortie, ainsi que d'enregistrer la grille dans un fichier
     * binaire.
     */
    public void NouvelleGrilleAleatoire() {
        fenetre = this;

        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(500, 500);

        // pour valider
        validerButton = new JButton("Valider");
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (modifiable == 0) {
                    modifiable += 1;
                }
                if (modifiable == 1) {
                    retourButton.setEnabled(true);
                    if (positionThésée != null) {
                        modifiable += 1;
                    }
                }
                if (modifiable == 2) {
                    if (positionSortie != null) {
                        modifiable += 1;
                    }
                }
                if (modifiable == 3) {
                    validerButton.setEnabled(false);
                    retourButton.setText("Fermer");
                    try {
                        EcritureFichierBinaire.ecrireMatrice(matrice, positionThésée, positionSortie, nomFichier);
                    } catch (IOException exception) {
                        System.err.println("Erreur lors de l'enregistrement de la matrice : " + exception.getMessage());
                    }
                }
            }
        }); // vérifier qu'on ne valide pas sans mettre Thésée ou la sortie d'abord

        retourButton = new JButton("Retour");
        retourButton.setEnabled(false);

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (modifiable == 1) {
                    retourButton.setEnabled(false);
                    modifiable -= 1;
                    // enlever Thésée
                    if (positionThésée != null) {
                        // on remet l'emplacement précédent de Thésée en blanc
                        panneau[positionThésée.getX()][positionThésée.getY()].setBackground(Color.WHITE);
                        Border lineborder = BorderFactory.createLineBorder(Color.BLACK, 1);
                        panneau[positionThésée.getX()][positionThésée.getY()].setBorder(lineborder);
                        // Les coordonnées de Thésée passent à null
                        positionThésée = null;
                    }
                }
                if (modifiable == 2) {
                    modifiable -= 1;
                    // enlever la sortie
                    if (positionSortie != null) {
                        // on remet l'emplacement précédent de la sortie en blanc
                        panneau[positionSortie.getX()][positionSortie.getY()].setBackground(Color.WHITE);
                        Border lineborder = BorderFactory.createLineBorder(Color.BLACK, 1);
                        panneau[positionSortie.getX()][positionSortie.getY()].setBorder(lineborder);
                        // Les coordonnées de la sortie passent à null
                        positionSortie = null;
                    }
                }
                if (modifiable == 3) {
                    fenetre.dispose();
                }
            }
        });

        JPanel panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new GridLayout(1, 2));

        JPanel panneauMatrice = new JPanel();
        panneauMatrice.setLayout(new GridLayout(lignes, colonnes));

        matrice = new int[lignes][colonnes];
        panneau = new JPanel[lignes][colonnes];
        // random
        Random rand = new Random();

        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                matrice[i][j] = rand.nextInt(2);
                // random == 0 == panneau blanc
                if (matrice[i][j] == 0) {
                    panneau[i][j] = new JPanel();
                    Border lineborder = BorderFactory.createLineBorder(Color.BLACK, 1);
                    panneau[i][j].setBackground(Color.WHITE);
                    panneau[i][j].setBorder(lineborder);
                }
                // random == 1 == panneau noir
                else {
                    panneau[i][j] = new JPanel();
                    Border lineborder = BorderFactory.createLineBorder(Color.WHITE, 1);
                    panneau[i][j].setBackground(Color.BLACK);
                    panneau[i][j].setBorder(lineborder);
                }

                final int ligne = i;
                final int colonne = j;

                // quand la souris est cliquée
                panneau[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (modifiable == 0) {
                            // la panneau cliqué était blanc, on le passe à noir et dans la matrice on
                            // remplace par 1
                            if (matrice[ligne][colonne] == 0) {
                                matrice[ligne][colonne] = 1;
                                panneau[ligne][colonne].setBackground(Color.BLACK);
                                Border lineborder = BorderFactory.createLineBorder(Color.WHITE, 1);
                                panneau[ligne][colonne].setBorder(lineborder);
                            }
                            // le panneau cliqué était noir, on le passe à blanc et dans la matrice on
                            // remplace par 0
                            else {
                                matrice[ligne][colonne] = 0;
                                panneau[ligne][colonne].setBackground(Color.WHITE);
                                Border lineborder = BorderFactory.createLineBorder(Color.BLACK, 1);
                                panneau[ligne][colonne].setBorder(lineborder);
                            }
                        } else if (modifiable == 1) {
                            // on place Thésee
                            if (matrice[ligne][colonne] == 0) {
                                if (positionThésée != null) {
                                    // on remet l'emplacement précédent de Thésée en blanc
                                    panneau[positionThésée.getX()][positionThésée.getY()].setBackground(Color.WHITE);
                                    Border lineborder = BorderFactory.createLineBorder(Color.BLACK, 1);
                                    panneau[ligne][colonne].setBorder(lineborder);
                                }

                                // on met le nouvel emplacement de Thésée
                                positionThésée = new Coordonnees(ligne, colonne);

                                // J'arrive pas à mettre l'image, j'en ai marre
                                panneau[ligne][colonne].setBackground(Color.BLUE);
                                Border lineborder = BorderFactory.createLineBorder(Color.BLACK, 1);
                                panneau[ligne][colonne].setBorder(lineborder);
                            }
                        } else if (modifiable == 2) {
                            // on place la sortie
                            if (matrice[ligne][colonne] == 0) {
                                if (ligne != positionThésée.getX() || colonne != positionThésée.getY()) { // on ne place
                                                                                                          // pas la
                                                                                                          // sortie sur
                                                                                                          // l'emplacement
                                                                                                          // de Thésée
                                    if (positionSortie != null) {
                                        // on remet l'emplacement précédent de la sortie en blanc
                                        panneau[positionSortie.getX()][positionSortie.getY()]
                                                .setBackground(Color.WHITE);
                                        Border lineborder = BorderFactory.createLineBorder(Color.BLACK, 1);
                                        panneau[ligne][colonne].setBorder(lineborder);
                                    }

                                    // on met le nouvel emplacement de la sortie en magenta
                                    positionSortie = new Coordonnees(ligne, colonne);
                                    panneau[ligne][colonne].setBackground(Color.MAGENTA); // juste pour l'instant, on
                                                                                          // mettra l'image apres
                                    Border lineborder = BorderFactory.createLineBorder(Color.BLACK, 1);
                                    panneau[ligne][colonne].setBorder(lineborder);
                                }
                            }
                        }
                    }
                });
                panneauMatrice.add(panneau[ligne][colonne]);
            }
        }

        panneauBoutons.add(validerButton);
        panneauBoutons.add(retourButton);

        fenetre.add(panneauMatrice);
        fenetre.add(panneauBoutons, BorderLayout.SOUTH);
        fenetre.setVisible(true);
    }

    /**
     * 
     * Cette méthode crée une nouvelle grille de jeu blanche de taille lignes x
     * colonnes. Elle permet également de
     * placer Thésée et la sortie, ainsi que d'enregistrer la grille dans un fichier
     * binaire.
     */
    public void NouvelleGrilleBlanche() {
        fenetre = this;

        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(500, 500);

        // pour valider
        validerButton = new JButton("Valider");
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (modifiable == 0) {
                    modifiable += 1;
                }
                if (modifiable == 1) {
                    retourButton.setEnabled(true);
                    if (positionThésée != null) {
                        modifiable += 1;
                    }
                }
                if (modifiable == 2) {
                    if (positionSortie != null) {
                        modifiable += 1;
                    }
                }
                if (modifiable == 3) {
                    validerButton.setEnabled(false);
                    retourButton.setText("Fermer");
                    try {
                        EcritureFichierBinaire.ecrireMatrice(matrice, positionThésée, positionSortie, nomFichier);
                    } catch (IOException exception) {
                        System.err.println("Erreur lors de l'enregistrement de la matrice : " + exception.getMessage());
                    }
                }
            }
        }); // vérifier qu'on ne valide pas sans mettre Thésée ou la sortie d'abord

        retourButton = new JButton("Retour");
        retourButton.setEnabled(false);

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (modifiable == 1) {
                    retourButton.setEnabled(false);
                    modifiable -= 1;
                    // enlever Thésée
                    if (positionThésée != null) {
                        // on remet l'emplacement précédent de Thésée en blanc
                        panneau[positionThésée.getX()][positionThésée.getY()].setBackground(Color.WHITE);
                        Border lineborder = BorderFactory.createLineBorder(Color.BLACK, 1);
                        panneau[positionThésée.getX()][positionThésée.getY()].setBorder(lineborder);
                        // Les coordonnées de Thésée passent à null
                        positionThésée = null;
                    }
                }
                if (modifiable == 2) {
                    modifiable -= 1;
                    // enlever la sortie
                    if (positionSortie != null) {
                        // on remet l'emplacement précédent de la sortie en blanc
                        panneau[positionSortie.getX()][positionSortie.getY()].setBackground(Color.WHITE);
                        Border lineborder = BorderFactory.createLineBorder(Color.BLACK, 1);
                        panneau[positionSortie.getX()][positionSortie.getY()].setBorder(lineborder);
                        // Les coordonnées de la sortie passent à null
                        positionSortie = null;
                    }
                }
                if (modifiable == 3) {
                    fenetre.dispose();
                }
            }
        });

        JPanel panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new GridLayout(1, 2));

        JPanel panneauMatrice = new JPanel();
        panneauMatrice.setLayout(new GridLayout(lignes, colonnes));

        matrice = new int[lignes][colonnes];
        panneau = new JPanel[lignes][colonnes];

        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                panneau[i][j] = new JPanel();
                Border lineborder = BorderFactory.createLineBorder(Color.BLACK, 1);
                panneau[i][j].setBackground(Color.WHITE);
                panneau[i][j].setBorder(lineborder);

                final int ligne = i;
                final int colonne = j;

                // quand la souris est cliquée
                panneau[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (modifiable == 0) {
                            // la panneau cliqué était blanc, on le passe à noir et dans la matrice on
                            // remplace par 1
                            if (matrice[ligne][colonne] == 0) {
                                matrice[ligne][colonne] = 1;
                                panneau[ligne][colonne].setBackground(Color.BLACK);
                                Border lineborder = BorderFactory.createLineBorder(Color.WHITE, 1);
                                panneau[ligne][colonne].setBorder(lineborder);
                            }
                            // le panneau cliqué était noir, on le passe à blanc et dans la matrice on
                            // remplace par 0
                            else {
                                matrice[ligne][colonne] = 0;
                                panneau[ligne][colonne].setBackground(Color.WHITE);
                                Border lineborder = BorderFactory.createLineBorder(Color.BLACK, 1);
                                panneau[ligne][colonne].setBorder(lineborder);
                            }
                        } else if (modifiable == 1) {
                            // on place Thésee
                            if (matrice[ligne][colonne] == 0) {
                                if (positionThésée != null) {
                                    // on remet l'emplacement précédent de Thésée en blanc
                                    panneau[positionThésée.getX()][positionThésée.getY()].setBackground(Color.WHITE);
                                    Border lineborder = BorderFactory.createLineBorder(Color.BLACK, 1);
                                    panneau[ligne][colonne].setBorder(lineborder);
                                }

                                // on met le nouvel emplacement de Thésée en bleu
                                positionThésée = new Coordonnees(ligne, colonne);
                                panneau[ligne][colonne].setBackground(Color.BLUE); // juste pour l'instant, on mettra
                                                                                   // l'image apres
                                Border lineborder = BorderFactory.createLineBorder(Color.BLACK, 1);
                                panneau[ligne][colonne].setBorder(lineborder);
                            }
                        } else if (modifiable == 2) {
                            // on place la sortie
                            if (matrice[ligne][colonne] == 0) {
                                if (ligne != positionThésée.getX() || colonne != positionThésée.getY()) { // on ne place
                                                                                                          // pas la
                                                                                                          // sortie sur
                                                                                                          // l'emplacement
                                                                                                          // de Thésée
                                    if (positionSortie != null) {
                                        // on remet l'emplacement précédent de la sortie en blanc
                                        panneau[positionSortie.getX()][positionSortie.getY()]
                                                .setBackground(Color.WHITE);
                                        Border lineborder = BorderFactory.createLineBorder(Color.BLACK, 1);
                                        panneau[ligne][colonne].setBorder(lineborder);
                                    }

                                    // on met le nouvel emplacement de la sortie en magenta
                                    positionSortie = new Coordonnees(ligne, colonne);
                                    panneau[ligne][colonne].setBackground(Color.MAGENTA); // juste pour l'instant, on
                                                                                          // mettra l'image apres
                                    Border lineborder = BorderFactory.createLineBorder(Color.BLACK, 1);
                                    panneau[ligne][colonne].setBorder(lineborder);
                                }
                            }
                        }
                    }
                });
                panneauMatrice.add(panneau[ligne][colonne]);
            }
        }
        panneauBoutons.add(validerButton);
        panneauBoutons.add(retourButton);

        fenetre.add(panneauMatrice);
        fenetre.add(panneauBoutons, BorderLayout.SOUTH);
        fenetre.setVisible(true);
    }

    /**
     * Renvoie le nom du fichier associé à l'objet courant.
     * 
     * @return le nom du fichier associé à l'objet courant
     */
    public String getNomFichier() {
        return this.nomFichier;
    }

    /**
     * Renvoie la matrice associée à l'objet courant.
     * 
     * @return la matrice associée à l'objet courant
     */
    public int[][] getMatrice() {
        return this.matrice;
    }

}
