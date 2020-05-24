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

        stage('Build avec test') {
            steps {
                sh 'mvn -B clean package'
            }
        }

        stages {
            def app
	currentBuild.result = "SUCCESS"

	try {
		stage('Clone repository') {
			checkout scm
		}

		stage('Build image') {
			app = docker.build("test")
		}

		stage('Test image'){
			app.inside {
				echo 'test'
			}
		}

		stage('Push image') {
			docker.withRegistry('https://127.0.0.1:5000') {
				app.push("latest")
			}
		}
	}
	catch (err) {
		currentBuild.result = "FAILURE"
		throw err
	}
        }

        stage('Test - To check MYSQL connect') {
                def dockerfile = 'Dockerfile.test'
                docker.build("rds-test", "-f ${dockerfile} .")
                def rds_test_image = docker.image('rds-test')

                docker.image('mysql:5.6').withRun('-e MYSQL_ALLOW_EMPTY_PASSWORD=yes') { container ->
                    rds_test_image.inside("--link ${container.id}:mysql") {
                    sh 'echo "Inside Container"'
                    }
  }
}

    }
}