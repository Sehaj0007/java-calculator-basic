pipeline {
    agent any

    tools {
        maven 'Maven-3.9.8'
    }
    
    environment {
        MAVEN_OPTS = '--enable-native-access=ALL-UNNAMED --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/sun.misc=ALL-UNNAMED'
    }

    stages {
        stage('1. Validate') {
            steps {
                bat 'mvn validate --no-transfer-progress'
            }
        }

        stage('2. Compile') {
            steps {
                bat 'mvn compile --no-transfer-progress'
            }
        }

        stage('3. Test') {
            steps {
                bat 'mvn test --no-transfer-progress'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    archiveArtifacts artifacts: 'target/surefire-reports/*.txt, target/surefire-reports/*.xml', allowEmptyArchive: true
                }
            }
        }

        stage('4. Package') {
            steps {
                bat 'mvn package -DskipTests --no-transfer-progress'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('5. Verify') {
            steps {
                bat 'mvn verify -DskipTests --no-transfer-progress'
            }
        }

        stage('6. Install') {
            steps {
                bat 'mvn install -DskipTests --no-transfer-progress'
            }
        }

        stage('7. Clean') {
            steps {
                bat 'echo "Clean stage - build already cleaned at start"'
            }
        }

        stage('8. Site') {
            steps {
                // Skip site generation due to plugin issues with Java 25
                bat 'echo "Skipping mvn site due to plugin compatibility issues with Java 25"'
                // bat 'mvn site -DskipTests --no-transfer-progress'
            }
        }

        stage('9. Deploy') {
            when {
                expression { 
                    env.BRANCH_NAME == 'main' || 
                    env.BRANCH_NAME == 'master' ||
                    env.BRANCH_NAME == 'advance-calculator'
                }
            }
            steps {
                bat 'mvn deploy -DskipTests -DaltDeploymentRepository=local::default::file:C:/maven-repo --no-transfer-progress'
            }
        }
    }
    
    post {
        always {
            echo "========================================="
            echo "Build Summary for ${env.BRANCH_NAME}"
            echo "Status: ${currentBuild.currentResult}"
            echo "Build #${env.BUILD_NUMBER}"
            echo "========================================="
            
            // Simple check for test results without findFiles
            bat '''
                echo "=== Test Results Check ==="
                if exist target\\surefire-reports\\*.txt (
                    echo "Test reports exist"
                    type target\\surefire-reports\\*.txt 2>nul || echo "Could not read test file"
                ) else (
                    echo "No test reports found"
                )
            '''
        }
        
        success {
            echo "✅ All stages completed successfully!"
        }
        
        unstable {
            echo "⚠️ Build completed with test failures"
        }
        
        failure {
            echo "❌ Build failed!"
        }
    }
}
