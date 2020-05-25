# Algorithm.Schedule-Management
Technical Project M1 Big Data

 Get-Content -Path "D:\payara5\glassfish\domains\domain1\logs\server.log" -Wait

docker run -d --name watchtower  -v /var/run/docker.sock:/var/run/docker.sock  containrrr/watchtower

docker network create schedule-net
docker run -d --name mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=appschedule -e MYSQL_USER=app -e MYSQL_PASSWORD=app -p 3306:3306 --network schedule-net mysql
docker run --rm -p 4848:4848 --name schedule --network schedule-net  -p 80:8080 m1/schedule

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

#Installation :

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
      CREATE DATABASE test;
      CREATE USER 'app'@'localhost' IDENTIFIED BY 'app';
      GRANT ALL PRIVILEGES ON test TO 'app'@'localhost';
  une fois fais vous avez maintenant une base de donnees sur laquel le serveur d'application va ce connecter

### retour sur payara

- asadmin start-domain
- asadmin add-library "chemin vers le jar du jconnector"
- asadmin create-jdbc-connection-pool --ping --restype javax.sql.DataSource --datasourceclassname com.mysql.cj.jdbc.MysqlDataSource --property user=app:password=app:DatabaseName=test:ServerName=127.0.0.1:port=3306:useSSL=false:zeroDateTimeBehavior=CONVERT_TO_NULL:useUnicode=true:serverTimezone=UTC:characterEncoding=UTF-8:useInformationSchema=true:nullCatalogMeansCurrent=true:nullNamePatternMatchesAll=false schedulePool

Dans la console d'administration de Payara, créez une nouvelle ressource JDBC, ce qui fournira à notre application le moyen de se connecter à la base de données test, au moyen d'un nom.
Pour cela, sélectionnez le noeud JDBC Ressources dans le volet des tâches placé à gauche, puis cliquez sur le bouton New... dans le volet de droite, ce qui permet d'accéder à l'écran de création d'une nouvelle ressource JDBC
Saisissez juste jdbc/test comme nom JNDI, et sélectionnez schedulePool pour le nom du pool de connexion qui a été créé auparavant ; puis cliquez sur le bouton OK pour confirmer la création de la ressource.


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

## installation Jenkins :

# POM (racine)
## proprieties :

## profiles :
il existe differents profils pour la compilation avec maven :
 - xTest
 - docker
 - payaraLocal

## payara et mysql Local :

## docker :


## JavaDoc :

pour generer la javadoc il executer la commande :
`mvn -P xTest package site:deploy`

les fichiers generés sont déposés dans le fichier javadoc du projet.
pour pouvoir les lires il suffit de les mettres sur un serveur web.
(je recommande si node est installé sur votre ordinateur de le lancer avec live-server (package node)).
une fois sur le site tous les sous projets sont detaillés.
il ne reste plus qu'a ce rendre dans "project reports" situé dans le volet de gauche pour trouver la javadoc.

# jenkins :
# API :
## postman :