pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9.8'
        // Use OpenJDK-11 which is already configured
        jdk 'OpenJDK-21'
    }
    
    stages {
        stage('Build & Test') {
            steps {
                bat '''
                    echo "=== Building and Testing ==="
                    mvn clean test --no-transfer-progress
                '''
            }
            post {
                always {
                    // Publish test results
                    junit '**/target/surefire-reports/*.xml'
                    
                    // Archive test reports
                    archiveArtifacts artifacts: 'target/surefire-reports/*.txt, target/surefire-reports/*.xml', allowEmptyArchive: true
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
            echo "Build ${currentBuild.currentResult} for branch ${env.BRANCH_NAME}"
        }
        
        success {
            echo "✅ All stages completed successfully!"
        }
        
        unstable {
            echo "⚠️ Build unstable - tests failed"
        }
        
        failure {
            echo "❌ Build failed!"
        }
    }
}
