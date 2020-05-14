# Algorithm.Schedule-Management
Technical Project M1 Big Data

docker network create schedule-net
docker run -d --name mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=test -e MYSQL_USER=app -e MYSQL_PASSWORD=app -p 3306:3306 mysql
docker run --rm -p 4848:4848 --name schedule --network schedule-net  -p 8080:80 schedule

log d'un container :
docker container logs <docker-container-name>

docker container run --name jenkins-docker --detach \
  --privileged --network jenkins --network-alias docker \
  --env DOCKER_TLS_CERTDIR=/certs \
  --volume jenkins-docker-certs:/certs/client \
  --volume jenkins-data:/var/jenkins_home \
  --publish 2376:2376 docker:dind

  docker container run --name jenkins-blueocean --detach \
  --network jenkins --env DOCKER_HOST=tcp://docker:2376 \
  --env DOCKER_CERT_PATH=/certs/client --env DOCKER_TLS_VERIFY=1 \
  --volume jenkins-data:/var/jenkins_home \
  --volume jenkins-docker-certs:/certs/client:ro \
  --publish 8080:8080 --publish 50000:50000 jenkinsci/blueocean

# Installation : (depuis 1 avril)

## JDK

- Télécharger et installer OpenJDK 8 depuis [AdoptOpenJDK](https://adoptopenjdk.net/installation.html?variant=openjdk8&jvmVariant=hotspot).

- Ajouter la variable d'environnement JAVA_HOME avec comme valeur, le chemin du répertoire d'installation du JDK.

- Ajouter` %JAVA_HOME%\bin` dans le _PATH_ du système.

Vérifier la version de Java (pour test) :

> `java -version`

## Maven

- Télécharger et dézipper [Apache Maven 3.6.3](https://maven.apache.org/download.cgi) dans le répertoire de votre choix.

- Ajouter le chemin vers le répertoire `bin` de Maven dans le _PATH_ du système.

Vérifier la version de Maven (pour test) :

> `mvn -v`


## Payara

- Payara Server 194 Full, https://www.payara.fish/software/downloads/

- Ajouter la variable d'environnement PAYARA_5_HOME contenant le chemin du répertoire d'installation de Payara 5.

## MySql :
MySql community https://dev.mysql.com/downloads/
prendre l'installer s'il est dispo pour votre systeme d'exploitation, sinon il faut:
- community server
- workbench (tu executer les request)
- notifier (pour gerer les instances: start,stop,etc..)
- connector/j 8.0.19 (connecteur jdbc)

  apres installation faire ce qu'il suit (1 fois)
  une fois a l'interieur de la bdd (passer par mysql workbench c'est plus facile), executer ce script :
      CREATE DATABASE test;
      CREATE USER 'app'@'localhost' IDENTIFIED BY 'app';
      GRANT ALL PRIVILEGES ON test TO 'app'@'localhost';
  une fois fais vous avez maintenant une base de donnees sur laquel le serveur d'application va ce connecter

## retour sur payara
asadmin set server.ejb-container.property.disable-nonportable-jndi-names="true"
asadmin start-domain
asadmin add-library "chemin vers le jar du jconnector"
asadmin create-jdbc-connection-pool --ping --restype javax.sql.DataSource --datasourceclassname com.mysql.cj.jdbc.MysqlDataSource --property user=app:password=app:DatabaseName=test:ServerName=127.0.0.1:port=3306:useSSL=false:zeroDateTimeBehavior=CONVERT_TO_NULL:useUnicode=true:serverTimezone=UTC:characterEncoding=UTF-8:useInformationSchema=true:nullCatalogMeansCurrent=true:nullNamePatternMatchesAll=false schedulePool

Dans la console d'administration de Payara, créez une nouvelle ressource JDBC, ce qui fournira à notre application le moyen de se connecter à la base de données test, au moyen d'un nom.
Pour cela, sélectionnez le noeud JDBC Ressources dans le volet des tâches placé à gauche, puis cliquez sur le bouton New... dans le volet de droite, ce qui permet d'accéder à l'écran de création d'une nouvelle ressource JDBC
Saisissez juste jdbc/test comme nom JNDI, et sélectionnez schedulePool pour le nom du pool de connexion qui a été créé auparavant ; puis cliquez sur le bouton OK pour confirmer la création de la ressource.


# Commandes


## Commandes Payara

Démarrage de Payara (domain1) :
> `%PAYARA_5_HOME%\bin\asadmin start-domain`

Console d'administration :
http://localhost:4848/

Arrêt de Payara (domain1) :
> `%PAYARA_5_HOME%\bin\asadmin stop-domain`

## Commandes Maven avec le plugin Cargo

Déployer une application Java entreprise pour la première fois avec Cargo :
> `mvn cargo:deploy`

Redéployer une application Java entreprise avec Cargo :
> `mvn cargo:redeploy`

Supprimer le déploiement d'une application Java entreprise avec Cargo :
> `mvn cargo:undeploy`





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
