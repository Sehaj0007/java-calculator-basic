pipeline {
    agent any

    tools {
        maven 'Maven-3.9.8'
        // No jdk configuration - uses system Java
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
                // Clean is already done at the beginning, but you can run it again
                bat 'echo "Clean stage - workspace already cleaned"'
            }
        }

        stage('8. Site') {
            steps {
                bat 'mvn site -DskipTests --no-transfer-progress'
            }
        }

        stage('9. Deploy') {
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
        }
    }
}
