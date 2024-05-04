
/**
 * 
 * Cette classe permet de définir les déplacements du personnage Thesee dans le
 * labyrinthe.
 * 
 * @version 1.1
 * @author Loryne Laffea-Didot
 * @author Héloïse Bousson
 */
public class Deplacement {

    /*
     * Historique des déplacements effectués par Thesee.
     */
    HistoriqueDeplacement trajet;

    /**
     * Historique des culs-de-sac visités par Thesee.
     */
    HistoriqueDeplacement culDeSac;

    /**
     * Constructeur par défaut de la classe Deplacement.
     * Initialise les historiques de déplacement et de culs-de-sac de Thesee.
     */
    public Deplacement() {
        this.trajet = new HistoriqueDeplacement();
        culDeSac = new HistoriqueDeplacement();
    }

    /**
     * Renvoie l'historique des déplacements de Thesee.
     * @return l'historique des déplacements de Thesee.
     */
    public HistoriqueDeplacement getHistorique() {
        return this.trajet;
    }

    /**
     * Génère une direction aléatoire pour le déplacement de Thesee dans le
     * labyrinthe.
     * La direction aléatoire ne doit pas être un mur et ne doit pas sortir des
     * limites du labyrinthe.
     * 
     * @param labyrinthe    la matrice représentant le labyrinthe.
     * @param tailleMatrice la taille de la matrice représentant le labyrinthe.
     * @param thésée        le personnage Thesee.
     * @return la direction aléatoire générée.
     */
    public Coordonnees directionAleatoire(int[][] labyrinthe, int tailleMatrice, Thesee thésée) {
        Coordonnees direction = new Coordonnees(-1, -1);
        int alea;
        boolean mur = false;
        while (mur == false) {
            alea = (int) Math.floor(Math.random() * 4);
            mur = true;
            if (alea == 0) {
                direction.setX(thésée.getPosition().getX() + 1);
                direction.setY(thésée.getPosition().getY());
            } else if (alea == 1) {
                direction.setX(thésée.getPosition().getX());
                direction.setY(thésée.getPosition().getY() - 1);
            } else if (alea == 2) {
                direction.setX(thésée.getPosition().getX() - 1);
                direction.setY(thésée.getPosition().getY());
            } else if (alea == 3) {
                direction.setX(thésée.getPosition().getX());
                direction.setY(thésée.getPosition().getY() + 1);
            }
            if (((direction.getX()) < 0) || ((direction.getY()) < 0) || ((direction.getX()) > (tailleMatrice - 1))
                    || ((direction.getY()) > (tailleMatrice - 1))) {
                mur = false;
                //C'est les limites du labyrinthe
            } else if (labyrinthe[direction.getX()][direction.getY()] == 1) {
                mur = false;
                //il y a un mur
            }
        }
        return direction;
    }

    /**
     * 
     * Méthode pour choisir la prochaine direction à prendre par Thesee dans le
     * labyrinthe pour la méthode décisionnelle.
     * 
     * @param labyrinthe    la matrice représentant le labyrinthe.
     * @param tailleMatrice la taille de la matrice.
     * @param thésée        l'objet Thesee représentant le personnage dans le
     *                      labyrinthe.
     * @return la prochaine coordonnée à atteindre par Thesee.
     */
    public Coordonnees choixDirection(int[][] labyrinthe, int tailleMatrice, Thesee thésée) {
        Coordonnees direction = new Coordonnees(-1, -1);

        direction.setX(thésée.getPosition().getX() + 1);
        direction.setY(thésée.getPosition().getY());
        if ((((direction.getX()) >= 0) && ((direction.getX()) < tailleMatrice))) {
            if (labyrinthe[direction.getX()][direction.getY()] == 0) {
                if ((trajet.chercherDansLaPile(direction) == 0) && (culDeSac.chercherDansLaPile(direction) == 0)) {
                    //en bas
                    return direction;
                } else
                    //déjà allé
            } else
                //il y a un mur
        } else
            //limite du labyrinthe

        direction.setX(thésée.getPosition().getX());
        direction.setY(thésée.getPosition().getY() - 1);
        if ((((direction.getY()) >= 0) && ((direction.getY()) < tailleMatrice))) {
            if (labyrinthe[direction.getX()][direction.getY()] == 0) {
                if ((trajet.chercherDansLaPile(direction) == 0) && (culDeSac.chercherDansLaPile(direction) == 0)) {
                    //a gauche
                    return direction;
                } else
                    //déjà allé
            } else
                //il y a un mur
        } else
            //limites du labyrinthe

        direction.setX(thésée.getPosition().getX() - 1);
        direction.setY(thésée.getPosition().getY());
        if ((((direction.getX()) >= 0) && ((direction.getX()) < tailleMatrice))) {
            if (labyrinthe[direction.getX()][direction.getY()] == 0) {
                if ((trajet.chercherDansLaPile(direction) == 0) && (culDeSac.chercherDansLaPile(direction) == 0)) {
                    return direction;
                } else
                    //déja allé
            } else
                //il y a un mur
        } else
            //limites du labyrinthe

        direction.setX(thésée.getPosition().getX());
        direction.setY(thésée.getPosition().getY() + 1);
        if ((((direction.getY()) >= 0) && ((direction.getY()) < tailleMatrice))) {
            if (labyrinthe[direction.getX()][direction.getY()] == 0) {
                if ((trajet.chercherDansLaPile(direction) == 0) && (culDeSac.chercherDansLaPile(direction) == 0)) {
                    // a droite
                    return direction;
                } else
                    // déjà allé
            } else
                // il y a un mur
        } else
            // limites du labyrinthe
        return direction;
    }

    /**
     * Effectue un demi-tour et retourne la direction dans laquelle Thesee doit se
     * déplacer.
     * 
     * @param thésée l'objet Thesee
     * @return la direction dans laquelle Thesee doit se déplacer pour effectuer le
     *         demi-tour
     */
    public void demiTour(Thesee thésée,Coordonnees direction) {
        Coordonnees positionThesee = new Coordonnees(thésée.getPosition().getX(), thésée.getPosition().getY());
        culDeSac.ajouterDeplacement(positionThesee);
        // position mise dans la liste des cul-de-sac
        direction = trajet.retirerDeplacement();
        thésée.nouvellePosition(direction);
    }

    /**
     * Déplace Thesee dans la direction donnée.
     * 
     * @param direction la direction dans laquelle Thesee doit se déplacer
     * @param thésée    l'objet Thesee
     */
    public void seDeplacer(Coordonnees direction, Thesee thésée) {
        Coordonnees positionThesee = new Coordonnees(thésée.getPosition().getX(), thésée.getPosition().getY());
        trajet.ajouterDeplacement(positionThesee);
        thésée.nouvellePosition(direction);
    }

}
