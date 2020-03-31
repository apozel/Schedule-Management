# Algorithm.Schedule-Management
Technical Project M1 Big Data

# Explications :

il s'agit d'un algorithme pour remplir l'agenda d'un medecin en fonction de la position des patients et de la criticité du cas dont ils font preuves.
pour l'instant l'algorithme est dans sa forme la plus basique, c'est a dire que pour l'instant on ne prend en compte que un seul medecin avec la criticité qui ne compte pas dans les calculs.
a l'avernir les criticités doivent etre prise en compte dans les calculs ainsi que la possibilité d'avoir plusieur medecins (et donc un choix de a qui revient un patient en fonction de la position).

# Comment le faire marche ?
alors la c'est tres simple il suffit de compiler et de lancer la classe InterfaceGraphique en effet pour l'instant le programme ne tourne pas encore dans son environnement final donc une IHM est necessaire pour visionner les resultat.
dans l'IHM ce trouve plusieurs tableau :
 - a gauche en noir il s'agit de l'endroit ou on visionne les positions des patients et du medecin.
 - a droite il y a tout pour interagir avec l'algorithme :
    - la premiere est affichage : elle permet de gerer les informations sur les rendez vous et la map a gauche 
      pour cela il suffit de rentrer la date ou on desire s'informer et ensuite appuyer sur le bouton "selection date" en bas a droite

    - on a ensuite dans le panneau "patient" tout ce qui permet de creer de nouveau patient personnaliser ainsi que les demandes qui leurs corresponds 

    - on a enfin l'onglet "test" qui lui permet de faire la meme chose que le precedent mais beaucoup plus rapidement. en effet le bouton "nouveau patient" permet de creer des nouveaux patients et nouvelle demande permet lui de creer patient et demande en meme temps. ce dernier permet de rapidement tester l'algorithme.

**InterfaceGraphique** il s'agit de la partie graphique du projet, il est composer d'une JFrame principal composé de deux inner classes heritants de JPanel. il affiche toute les informations contenue dans la classe simulation. il permet aussi d'interagir avec cette derniere. les fonctions du mode graphique sont nombreuses (affichage des positions des rendez-vous deja pris,creation de ceux-ci,..), regarder la section 'le faire marcher' pour en savoir plus.

**Algorithme** l'algorithme principal verifie l'heure de la demande, si celle-ci est en dehors des horraires du docteur alors elle place le moment ou on regarde les prochains rendez vous au crenau horraire suivant 
ensuite l'algorithme regarde si a partir de ce moment la journée est pleine ou non, si c'est le cas alors on passe au lendemain sinon on prend tout les rendez vous a partir de ce moment ainsi que la nouvelle demande de rdv et on optimise le trajet du medecin 
enfin il renvoie la liste de rendez vous ainsi trier (les changements dans la base de donnees sont gerer par la classe JunctionInformation)

**simulation** cette classe sert a simuler une base de donnees, elle sert donc de lien en le programme et l'IHM. elle communique ses informations avec la classe `InterfaceGraphique` et `JunctionInformationSimulation`.

**JunctionInformation** est l'interface qui se place entre l'algorithme et les donnees. elle donc bien sure implementé par la classe `JunctionInformationSimulation` qui permet au programme de fonctionner avec la classe Simulation et donc par implication l'IHM. on notera que la classe `JunctionInformationJson` servira elle a communiquer par json avec les scripts d'un autre groupe. 



# a faire 
- (fait) (mais voir les TODO) integrer le projet marchant a l'algorithme
- (fait) gerer le systeme de tri des listes de rendez vous pour l'enregistrement 
- (fait) faire que l'affichage dans l'interface ce fasse en fonction de la date afficher (fait pour les informations rendez vous)
- (fait) pouvoir changer la date par appuis du bouton date 
- (en cours) faire de l'enregistrement
- fixer la taille de la fenetre (par rapport a la taille de la JFrame)
- (fait) s'occuper de l'affichage des droites qui lient les rendez vous
- (fait)faire l'affichage du lieux de depart du docteur 
- si possible changer l'architecture pour la rendre plus dynamique : 
    - decouper Interfacegraphique en plusieur sous classe 
    - faire que chaque classe ecoutent les autres et s'actualise automatiquement (pattern Observeur)
- si possible changer les noeud en position
- si possible changer les rendezvous en un heritage de la classe demande 
- enregistrement en json : faire deux inner classe dans junctionInformation (avec une interface commune) une pour le json est l'autre pour le la simulation
- (résolu) tester si les positions peuvent etre dans le negatif
- faire deux entrees pour les positions.
