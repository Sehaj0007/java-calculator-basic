pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9.8'
        // REMOVE the jdk line - Jenkins will use system Java
    }
    
    environment {
        // Point to your system Java
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-25.0.1' // Adjust path if different
        PATH = "${env.JAVA_HOME}\\bin;${env.PATH}"
        
        // Suppress Java warnings
        MAVEN_OPTS = '--enable-native-access=ALL-UNNAMED --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/sun.misc=ALL-UNNAMED'
    }
    
    stages {
        stage('Check Java Version') {
            steps {
                bat '''
                    echo "=== Java Information ==="
                    where java
                    java -version
                    echo JAVA_HOME=%JAVA_HOME%
                    echo "=== Maven Information ==="
                    mvn --version
                '''
            }
        }
        
        stage('Build & Test') {
            steps {
                bat 'mvn clean test --no-transfer-progress'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Package') {
            when {
                expression { currentBuild.result != 'FAILURE' }
            }
            steps {
                bat 'mvn package -DskipTests --no-transfer-progress'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
    
    post {
        always {
            echo "Build ${currentBuild.currentResult} for ${env.BRANCH_NAME}"
        }
    }
}
