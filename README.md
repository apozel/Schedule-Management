# Algorithm.Schedule-Management
Technical Project M1 Big Data

(apozel) get log from server in powershell (windows):
 Get-Content -Path "D:\payara5\glassfish\domains\domain1\logs\server.log" -Wait



# POM (racine)

le POM que vous pouvez voir dans chaque fichiers du projet et le point centrale de configuration de maven. en effet ce projet est un assemblage de projets maven.
maven utilise effectivement le pom pour paramétrer tout le projet et donc le build et les plugins.

## proprieties :

## profiles :
il existe differents profils pour la compilation avec maven :
 - xTest
 - docker
 - payaraLocal

 Le premier permet de passer outre tous les tests.
 Le second permet de passer outre les test qui utilise un serveur payara local et une bdd mais créer aussi une image docker du projet.
 la troisième autorise les test et le déploiement direct du projet sur le serveur (actif par défaut)

## payara et mysql Local :
S’utilise avec le profile payaraLocal (actif par défaut). Ce mode de fonctionnement a besoin du driver jdbc pour fonctionner, il se trouve dans le dossier /ear/docker (voir Installation local).

## docker :
Docker permet de faciliter le déploiement du projet. En effet il suffit de d'activer le bon profile lors de la compilation pour qu'une image ce créer dans le repository local docker.
on utilise pour cela un plugin Maven créer par SPOTIFY.
Une fois dans le repository il deviens très simple de créer un containeur à partir de celui-ci.
le docker file de l'image ce trouve dans le dossier /ear.
l'image générer a besoin de plusieurs arguments pour être build. Les arguments sont donnés a la compilation par le plugin lui-même à partir des proprieties donner dans le pom de Maven.
cette image contient déjà le driver pour la bdd.

## JavaDoc :

pour générer la javadoc il exécuter la commande :
`mvn -P xTest package site:deploy`

les fichiers générés sont déposés dans le fichier javadoc du projet.
pour pouvoir les lires il suffit de les mettre sur un serveur web.
(je recommande si Node est installé sur votre ordinateur de le lancer avec live-server (package Node)).
une fois sur le site tous les sous projets sont détaillés.
il ne reste plus qu'a ce rendre dans "Project reports" situé dans le volet de gauche pour trouver la javadoc.
la javadoc rassemble les explications de code, le nom des classe et leurs méthodes.

# jenkins :
Jenkins est un serveur de build à configurer. Il peut être intéressant de l'intégrer au projet pour une raison il peut build chaque partie du projet M1 et ensuite les assemblés pour créer un projet unique. on peut ensuite envisager un déploiement. il utilise pour build un projet un Jenkins file. Une fois celui-ci trouver il peut créer une pipeline qui suit les instructions de ce fichier.

# API :
L’api permet de communiquer avec ce projet au travers de l'url 'serverUrl/api-rest/schedule/API' on doit encore rajouter le Endpoint qu'on veut. On peut voir la documentation de l'api sur Postman dans le dossier api disponible a la racine. Il suffit d'importer le fichier. json. On auras ainsi accès a la doc mais aussi au tests unitaires

## postman :
postman est un outil qui permet de tester et documenter son api. il permet ici d'accéder facilement a la documentation de l'api qui se trouve dans le dossier api disponible à la racine du projet. Une fois cette outils en votre possession vous pouvez importer la collection disponible.

# Explications :

## JDBC :

jdbc est le connecteur qui permet au serveur payara de communiquer avec la base de données. le nom donner a la ressource jdbc peut être changer dans le pom a la racine du projet. on doit aussi changer l'action de demarrage en effet on a 4 actions au choix : "drop-and-create","create","drop","none"
le premier sert a effacer et recréer la base de donnée à chaque démarrage. Le second part du principe que la base de donnée est vierge et le dernier ne touche pas la base de données (c'est ce dernier qu'on utilisera pour l'application en production).
si la base de données est créer le driver jdbc utilisera les scripts dans "ejbs\src\main\resources\META-INF\sql" pour drop and create la base donnée.
les tests remplissent la bdd donc ils doivent être skip pour un déploiement en production.

## Algorithme :
## EJB :
les ejbs sont des classes java qui peuvent être injecter dans la plupart des autres classes. L’injection est réalisé par le serveur lui même.
il y en a deux dans le projet : le crud et celui pour l'algorithme. Le crud lui utilise le driver jdbc pour pouvoir aller chercher dans la bdd toutes les informations utiles. L’algorithme quant à lui utilise ce premier pour avoir les informations et les utilisent pour générer des rendez-vous.
## API :
L’api permet la communication avec l'extérieur. Elle renvoie des objets en json. Les objets sont générés par l'implémentation au sein du serveur payara d'un parseur json. Si on veut changer la valeur des champs renvoyer pour un objet, il suffit de changer le nom de ses getters.

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
prendre l'installer s'il est dispo pour votre système d'exploitation, sinon il faut:
- community server
- workbench (pour exécuter les requêtes)
- notifier (pour gérer les instances: start,stop,etc..)
- connector/j 8.0.19 (connecteur jdbc)

  Après installation faire ce qu'il suit (1 fois)
  Une fois à l'intérieur de la bdd (passer par mysql workbench c'est plus facile), exécuter ce script :
      `CREATE DATABASE test;`
      `CREATE USER 'app'@'localhost' IDENTIFIED BY 'app';`
      `GRANT ALL PRIVILEGES ON test TO 'app'@'localhost';`
  une fois fais vous avez maintenant une base de données sur laquelle le serveur d'application va se connecter

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

La machine docker peut maintenir à jour les images. Pour cela on utilise l'image watchtower qui surveille et relance les containeur avec les mêmes paramètres si une nouvelle image existe .
et puisque c'est une nouvelle image qui est créer avec le build Maven (profile docker) c'est ce qu'on va installer pour éviter de recréer le containeur a chaque nouvelle version :

`docker run -d --name watchtower  -v /var/run/docker.sock:/var/run/docker.sock  containrrr/watchtower`

Ensuite on a maintenant plusieurs options soit on créer les containeurs requit indépendamment soit on utilise le docker-compose qui nous permet de confier cette étape a docker lui-même :
(Si c'est pour une installation de production il est préférable de passer par une installation individuelle qui permet de gérer individuellement les différentes options).
INFO :
 - log d'un container : `docker container logs <docker-container-name>`

### installation individuelle :
on créer un network pour que les deux containeur puissent communiquer entre eux.
`docker network create schedule-net`
on peut aussi juste créer un lien entre eux mais le network permet de connecter plus de containeur si requis.

on démarre ensuite le container de la bdd (si c'est pour une installation de production et que la bdd existe déjà, veuillez re-build l'image en changeant les "proprieties" dans le pom a la racine du projet (profile docker))
`docker run -d --name mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=appschedule -e MYSQL_USER=app -e MYSQL_PASSWORD=app -p 3306:3306 --network schedule-net mysql`

Il ne reste plus qu'à lancer le containeur qui contient le projet :
`docker run --rm -p 4848:4848 --name schedule --network schedule-net  -p 8080:8080 m1/schedule`

Le containeur est maintenant accessible avec l'adresse de la machine docker sur le port 8080.

### installation docker-compose :

Une fois à la racine du projet, l'installation est très simple :
- build de l'image : `mvn -P docker package`
- ensuite à la racine du projet : `docker-compose up`

La machine docker va créer la base de donnée et le service Schedule accessible de la même façon que le précédente installation.


## installation Jenkins :

` docker container run --name jenkins-docker --detach --privileged --network jenkins --network-alias docker --env DOCKER_TLS_CERTDIR=/certs --volume jenkins-docker-certs:/certs/client --volume jenkins-data:/var/jenkins_home --publish 2376:2376 docker:dind `

 ` docker container run --name jenkins-blueocean --detach --network jenkins --env DOCKER_HOST=tcp://docker:2376 --env DOCKER_CERT_PATH=/certs/client --env DOCKER_TLS_VERIFY=1 --volume jenkins-data:/var/jenkins_home --volume jenkins-docker-certs:/certs/client:ro --publish 8080:8080 --publish 50000:50000 jenkinsci/blueocean `

Une fois installer sur le serveur il faut configurer Jenkins pour récupérer l'image sur le projet GitHub ou alors dans un repository local (mais la première solution est préféré).
le Jenkins file est déjà dans le projet donc une fois ceci fait Jenkins s'occupe du reste.
