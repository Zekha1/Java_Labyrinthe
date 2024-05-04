### NE MARCHE PAS / IL NE CREE PAS LES .CLASS : FAIRE "javac MainLabyrinthe.java" AVANT DE FAIRE LE "make run" ###

### VARIABLES ###
JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none

JVM = java
JVMFLAGS = 

### REGLES ESSENTIELLES ###
MainLabyrinthe.class : MainLabyrinthe.java Choix.class
    ${JC} ${JCFLAGS} MainLabyrinthe.java

AffichageGrille.class : AffichageGrille.java Coordonnees.class HistoriqueDeplacement.class RecupFile.class Thesee.class
    ${JC} ${JCFLAGS} AffichageGrille.java

Algorithme.class : Algorithme.java AffichageGrille.class Arrivee.class Coordonnees.class Deplacement.class RecupFile.class Thesee.class
    ${JC} ${JCFLAGS} Algorithme.java

Arrivee.class : Arrivee.java Coordonnees.class
    ${JC} ${JCFLAGS} Arrivee.java

ChoisirFichier.class : ChoisirFichier.java 
    ${JC} ${JCFLAGS} ChoisirFichier.java

Choix.class : Choix.java Algorithme.class ChoisirFichier.class NouvelleGrille.class
    ${JC} ${JCFLAGS} Choix.java

Coordonnees.class : Coordonnees.java
    ${JC} ${JCFLAGS} Coordonnees.java

Deplacement.class : Deplacement.java Coordonnees.class HistoriqueDeplacement.class Thesee.class 
    ${JC} ${JCFLAGS} Deplacement.java

EcritureFichierBinaire.class : EcritureFichierBinaire.java Coordonnees.class 
    ${JC} ${JCFLAGS} EcritureFichierBinaire.java

HistoriqueDeplacement.class : HistoriqueDeplacement.java Coordonnees.class
    ${JC} ${JCFLAGS} HistoriqueDeplacement.java

NouvelleGrille.class : NouvelleGrille.java ChoisirFichier.class Coordonnees.class EcritureFichierBinaire.class 
    ${JC} ${JCFLAGS} NouvelleGrille.java

RecupFile.class : RecupFile.java Coordonnees.class 
    ${JC} ${JCFLAGS} RecupFile.java

Thesee.class : Thesee.java Coordonnees.class
    ${JC} ${JCFLAGS} Thesee.java


### REGLES OPTIONNELLES ###
run : MainLabyrinthe.class
	${JVM} ${JVMFLAGS} MainLabyrinthe

clean :
	-rm -f *.class

mrproper : clean Main.class

### BUTS FACTICES ###
.PHONY : run clean mrproper

### FIN ###