# Algorithm.Schedule-Management
Technical Project M1 Big Data

(apozel) get log from server in powershell (windows):
 Get-Content -Path "D:\payara5\glassfish\domains\domain1\logs\server.log" -Wait



# POM (racine)

le POM que vous pouvez voir dans chaque fichiers du projet et le point centrale de configuration de maven. en effet ce projet est un assemblage de projets maven.
maven utilise effectivement le pom pour parametrer tout le projet et donc le build et les plugins.

## proprieties :

## profiles :
il existe differents profils pour la compilation avec maven :
 - xTest
 - docker
 - payaraLocal

 le premier permet de passer outre tous les tests
 le second permet de passer outre les test qui utilise un serveur payara local et une bdd mais creer aussi une image docker du projet
 la troisieme autorise les test et le deployment direct du projet sur le serveur (actif par defaut)

## payara et mysql Local :
s'utilise avec le profile payaraLocal (actif par defaut). ce mode de fonctionnement a besoin du driver jdbc pour fonctionner, il ce trouve dans le dossier /ear/docker (voir Installation local).

## docker :
docker permet de faciliter le deploiement du projet. en effet il suffit de d'activer le bon profile lors de la compilation pour qu'une image ce creer dans le repository local docker.
on utilise pour cela un plugin maven creer par SPOTIFY.
une fois dans le repository il deviens tres simple de creer un contaigneur a partir de celui ci.
le dockerfile de l'image ce trouve dans le dossier /ear.
l'image generer a besoin de plusieurs arguments pour etre build. les arguments sont donnés a la compilation par le plugin lui meme a partir des proprieties donner dans le pom de maven.
cette image contient deja le driver pour la bdd.

## JavaDoc :

pour generer la javadoc il executer la commande :
`mvn -P xTest package site:deploy`

les fichiers generés sont déposés dans le fichier javadoc du projet.
pour pouvoir les lires il suffit de les mettres sur un serveur web.
(je recommande si node est installé sur votre ordinateur de le lancer avec live-server (package node)).
une fois sur le site tous les sous projets sont detaillés.
il ne reste plus qu'a ce rendre dans "project reports" situé dans le volet de gauche pour trouver la javadoc.
la javadoc rassemble les explications de code, le nom des classe et leurs methodes.

# jenkins :
jenkins est un serveur de build a configurer. il peut etre interressant de l'integrer au projet pour une raison il peut build chaque partie du projet M1 et ensuite les assemblés pour creer un projet unique. on peut ensuiter envisager un deploiement. il utilise pour build un projet un jenkins file. une fois celui ci trouver il peut creer une pipeline qui suit les instructions de ce fichier.

# API :
l'api permet de communiquer avec ce projet au travers de l'url 'serverUrl/api-rest/schedule/API' on doit encore rajouter le endpoint qu'on veut. on peut voir la documentation de l'api sur postman dans le dossier api disponible a la racine. il suffit d'importer le fichier .json. on auras ainsi acces a la doc mais aussi au tests unitaires

## postman :
postman est un outil qui permet de tester et documenter son api. il permet ici d'acceder facilement a la documentation de l'api qui ce trouve dans le dossier api disponible a la racine du projet. une fois cette outils en votre possesion vous pouvez importer la collection disponible.

# Explications :

## JDBC :

jdbc est le connecteur qui permet au seveur payara de communiquer avec la base de données. le nom donner a la ressource jdbc peut etre changer dans le pom a la racine du projet. on doit aussi changer l'action de demarrage en effet on a 4 actions au choix : "drop-and-create","create","drop","none"
le premier sert a effacer et recreer la base de donnée a chaque demarrage. le second part du principe que la base de donnée est vierge et le dernier ne touche pas la base de données (c'est ce dernier qu'on utilisera pour l'application en production).
si la base de données est creer le driver jdbc utilisera les sripts dans "ejbs\src\main\resources\META-INF\sql" pour drop and create la base donnée.
les tests remplissent la bdd donc ils doivent etre skip pour un doployement en production.

## Algorithme :
## EJB :
les ejbs sont des classes java qui peuvent etre injecter dans la plupart des autres classes. l'injection est réalisé par le serveur lui meme.
il y en a deux dans le projet : le crud et celui pour l'algorithme. le crud lui utilise le driver jdbc pour pouvoir aller chercher dans la bdd toute les informations utiles. l'algorithme quand a lui utilise ce premier pour avoir les informations et les utilisent pour generer des rendezvous.
## API :
l'api permet la communications avec l'exterieur. elle renvoie des objets en json. les objets sont generer par l'implementation au sein du serveur payara d'un parseur json. si on veut changer la valeur des champs renvoyer pour un objet, il suffit de changer le nom de ses getters.

# Installation :

## Installation Local :

### JDK

- Télécharger et installer OpenJDK 8 depuis [AdoptOpenJDK](https://adoptopenjdk.net/installation.html?variant=openjdk8&jvmVariant=hotspot).

- Ajouter la variable d'environnement JAVA_HOME avec comme valeur, le chemin du répertoire d'installation du JDK.

- Ajouter` %JAVA_HOME%\bin` dans le _PATH_ du système.

Vérifier la version de Java (pour test) :

> `java -version`

### Maven

- Télécharger et dézipper [Apache Maven 3.6.3](https://maven.apache.org/download.cgi) dans le répertoire de votre choix.

- Ajouter le chemin vers le répertoire `bin` de Maven dans le _PATH_ du système.

Vérifier la version de Maven (pour test) :

> `mvn -v`


### Payara

- Payara Server 194 Full, https://www.payara.fish/software/downloads/

- Ajouter la variable d'environnement PAYARA_5_HOME contenant le chemin du répertoire d'installation de Payara 5.

### MySql :
MySql community https://dev.mysql.com/downloads/
prendre l'installer s'il est dispo pour votre systeme d'exploitation, sinon il faut:
- community server
- workbench (tu executer les request)
- notifier (pour gerer les instances: start,stop,etc..)
- connector/j 8.0.19 (connecteur jdbc)

  apres installation faire ce qu'il suit (1 fois)
  une fois a l'interieur de la bdd (passer par mysql workbench c'est plus facile), executer ce script :
      `CREATE DATABASE test;`
      `CREATE USER 'app'@'localhost' IDENTIFIED BY 'app';`
      `GRANT ALL PRIVILEGES ON test TO 'app'@'localhost';`
  une fois fais vous avez maintenant une base de donnees sur laquel le serveur d'application va ce connecter

### retour sur payara

- `asadmin start-domain`
- `asadmin add-library "chemin vers le jar du jconnector"`
- `asadmin create-jdbc-connection-pool --ping --restype javax.sql.DataSource --datasourceclassname com.mysql.cj.jdbc.MysqlDataSource --property user=app:password=app:DatabaseName=test:ServerName=127.0.0.1:port=3306:useSSL=false:zeroDateTimeBehavior=CONVERT_TO_NULL:useUnicode=true:serverTimezone=UTC:characterEncoding=UTF-8:useInformationSchema=true:nullCatalogMeansCurrent=true:nullNamePatternMatchesAll=false schedulePool`
- `create-jdbc-resource --connectionpoolid schedulePool --enabled=true jdbc/scheduleResource`



## Commandes :


### Commandes Payara

Démarrage de Payara (domain1) :
> `%PAYARA_5_HOME%\bin\asadmin start-domain`

Console d'administration :
http://localhost:4848/

Arrêt de Payara (domain1) :
> `%PAYARA_5_HOME%\bin\asadmin stop-domain`

### Commandes Maven avec le plugin Cargo

Déployer une application Java entreprise pour la première fois avec Cargo :
> `mvn cargo:deploy`

Redéployer une application Java entreprise avec Cargo :
> `mvn cargo:redeploy`

Supprimer le déploiement d'une application Java entreprise avec Cargo :
> `mvn cargo:undeploy`

## installation docker :

la machine docker peut maintenir a jour les images. pour cela on utilise l'image watchtower qui surveille et relance les contaigneur avec les memes paramettres si une nouvelle image existe .
et puisque c'est une nouvelle image qui est creer avec le build maven (profile docker) c'est ce qu'on va installer pour eviter de recreer le contaigneur a chaque nouvelle version :

`docker run -d --name watchtower  -v /var/run/docker.sock:/var/run/docker.sock  containrrr/watchtower`

ensuite on a maintenant plusieurs options soit on creer les contaigneurs requit independament soit on utilise le docker-compose qui nous permet de confier cette etape a docker lui meme :
(si c'est pour une installation de production il est preferable de passer par une installation individuelle qui permet de gerer individuellement les differentes options).
INFO :
 - log d'un container : `docker container logs <docker-container-name>`

### installation individuelle :
on creer un network pour que les deux contaigneur puissent communiquer entre eux.
`docker network create schedule-net`
on peut aussi juste creer un lien entre eux mais le network permet de connecter plus de contaigneur si requis.

on demarre ensuite le contaigner de la bdd (si c'est pour une installation de production et que la bdd existe deja, veuillez rebuild l'image en changeant les "proprieties" dans le pom a la racine du projet (profile docker))
`docker run -d --name mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=appschedule -e MYSQL_USER=app -e MYSQL_PASSWORD=app -p 3306:3306 --network schedule-net mysql`

il ne reste plus qu'a lancer le contaigneur qui contient le projet :
`docker run --rm -p 4848:4848 --name schedule --network schedule-net  -p 8080:8080 m1/schedule`

le contaigneur est maintenant accesible avec l'addresse de la machine docker sur le port 8080.

### installation docker-compose :

une fois a la racine du projet, l'installation est tres simple :
- build de l'image : `mvn -P docker package`
- ensuite a la racine du projet : `docker-compose up`

la machine docker va creer la base de donnee et le service schedule accessible de la meme facon que le precedente installation.


## installation Jenkins :

` docker container run --name jenkins-docker --detach --privileged --network jenkins --network-alias docker --env DOCKER_TLS_CERTDIR=/certs --volume jenkins-docker-certs:/certs/client --volume jenkins-data:/var/jenkins_home --publish 2376:2376 docker:dind `

 ` docker container run --name jenkins-blueocean --detach --network jenkins --env DOCKER_HOST=tcp://docker:2376 --env DOCKER_CERT_PATH=/certs/client --env DOCKER_TLS_VERIFY=1 --volume jenkins-data:/var/jenkins_home --volume jenkins-docker-certs:/certs/client:ro --publish 8080:8080 --publish 50000:50000 jenkinsci/blueocean `

une fois installer sur le serveur il faut configurer jenkins pour recuperer l'image sur le projet github ou alors dans un repository local (mais la premiere solution est preferé).
le jenkins file est deja dans le projet donc une fois ceci fait jenkins s'occupe du reste.
