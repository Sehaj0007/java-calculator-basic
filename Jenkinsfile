pipeline {
    agent any

    tools {
        maven 'Maven-3.9.8'
    }

    stages {

        stage('1. Validate') {
            steps {
                bat 'mvn validate'
            }
        }

        stage('2. Compile') {
            steps {
                bat 'mvn compile'
            }
        }

        stage('3. Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('4. Package') {
            steps {
                bat 'mvn package'
            }
        }

        stage('5. Verify') {
            steps {
                bat 'mvn verify'
            }
        }

        stage('6. Install') {
            steps {
                bat 'mvn install'
            }
        }

        stage('7. Clean') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('8. Site') {
            steps {
                bat 'mvn site'
            }
        }

        stage('9. Deploy') {
            steps {
                bat 'mvn deploy -DaltDeploymentRepository=local::default::file:C:/maven-repo'
            }
        }
        }
    }
}
