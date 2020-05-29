pipeline {
  agent {
    docker {
      image 'maven:3-alpine'
      args '-v /root/.m2:/root/.m2'
    }

  }
  stages {
    stage('Build sans test') {
      steps {
        sh 'mvn -B -DskipTests clean package'
      }
    }

    stage('build image docker') {
      agent {
        dockerfile {
          filename 'dockerfile'
        }

      }
      steps {
        echo 'build '
      }
    }

  }
}