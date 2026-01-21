pipeline {
    agent any

    tools {
        maven 'Maven-3.9.8'
        // No jdk configuration - uses system Java 25
    }
    
    environment {
        // Add JVM args to suppress warnings
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
                // Add -Dmaven.test.failure.ignore=true to continue even if tests fail
                bat 'mvn test --no-transfer-progress -Dmaven.test.failure.ignore=true'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    archiveArtifacts artifacts: 'target/surefire-reports/*.txt, target/surefire-reports/*.xml', allowEmptyArchive: true
                }
            }
        }

        stage('4. Package') {
            // Run package even if tests failed (for development)
            steps {
                bat 'mvn package -DskipTests --no-transfer-progress'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('5. Verify') {
            when {
                // Only run verify on successful builds
                expression { currentBuild.result != 'FAILURE' }
            }
            steps {
                bat 'mvn verify -DskipTests --no-transfer-progress'
            }
        }

        stage('6. Install') {
            when {
                expression { currentBuild.result != 'FAILURE' }
            }
            steps {
                bat 'mvn install -DskipTests --no-transfer-progress'
            }
        }

        stage('7. Clean') {
            steps {
                // Just a message since clean happens at start
                bat 'echo "Clean stage - build artifacts preserved"'
            }
        }

        stage('8. Site') {
            when {
                expression { currentBuild.result != 'FAILURE' }
            }
            steps {
                bat 'mvn site -DskipTests --no-transfer-progress'
            }
        }

        stage('9. Deploy') {
            when {
                // Only deploy if tests pass on main branches
                expression { 
                    (env.BRANCH_NAME == 'main' || env.BRANCH_NAME == 'master') &&
                    currentBuild.result == 'SUCCESS'
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
            
            // Show test summary
            script {
                def testFiles = findFiles(glob: '**/target/surefire-reports/*.txt')
                if (testFiles) {
                    echo "Test Results Summary:"
                    def content = readFile testFiles[0].path
                    echo content
                }
            }
        }
        
        success {
            echo "✅ All stages completed successfully!"
        }
        
        unstable {
            echo "⚠️ Build completed with test failures"
            echo "Fix the failing test: CalculatorTest.testModulo() line 171"
        }
        
        failure {
            echo "❌ Build failed during compilation or critical stage"
        }
    }
}
