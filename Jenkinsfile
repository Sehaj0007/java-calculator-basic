pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9.8'
        // No jdk line - uses system default
    }
    
    stages {
        stage('Verify Environment') {
            steps {
                bat '''
                    echo "Checking Java installation..."
                    java -version
                    echo.
                    echo "Checking Maven..."
                    mvn --version
                    echo.
                    echo "Workspace contents:"
                    dir
                '''
            }
        }
        
        stage('Build') {
            steps {
                bat 'mvn clean compile --no-transfer-progress'
            }
        }
        
        stage('Test') {
            steps {
                bat 'mvn test --no-transfer-progress -Dmaven.test.failure.ignore=true'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Package') {
            steps {
                bat 'mvn package -DskipTests --no-transfer-progress'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
    
    post {
        always {
            echo "Pipeline completed with status: ${currentBuild.currentResult}"
        }
    }
}
