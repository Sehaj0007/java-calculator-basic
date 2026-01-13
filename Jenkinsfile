pipeline {
    agent any

    tools {
        maven 'Maven-3.9.8'
    }

    stages {

        stage('1. Validate') {
            steps {
                sh 'mvn validate'
            }
        }

        stage('2. Compile') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('3. Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('4. Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('5. Verify') {
            steps {
                sh 'mvn verify'
            }
        }

        stage('6. Install') {
            steps {
                sh 'mvn install'
            }
        }

        stage('7. Clean') {
            steps {
                sh 'mvn clean'
            }
        }

        stage('8. Site') {
            steps {
                sh 'mvn site'
            }
        }

        stage('9. Deploy') {
            steps {
                sh 'mvn deploy'
            }
        }
    }
}
