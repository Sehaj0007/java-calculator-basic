pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9.8'
        jdk 'JDK-17'
    }
    
    environment {
        MAVEN_OPTS = '--enable-native-access=ALL-UNNAMED --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/sun.misc=ALL-UNNAMED'
    }
    
    stages {
        stage('Initialize') {
            steps {
                script {
                    echo "========================================="
                    echo "Pipeline: ${env.JOB_NAME}"
                    echo "Branch: ${env.BRANCH_NAME}"
                    echo "Build: #${env.BUILD_NUMBER}"
                    echo "========================================="
                }
            }
        }
        
        stage('Build') {
            steps {
                bat 'mvn clean compile --no-transfer-progress'
            }
        }
        
        stage('Test') {
            steps {
                // Continue even if tests fail
                bat 'mvn test --no-transfer-progress -Dmaven.test.failure.ignore=true'
            }
            post {
                always {
                    // Always publish test results, even on failure
                    junit '**/target/surefire-reports/*.xml'
                    
                    // Archive test reports
                    archiveArtifacts artifacts: 'target/surefire-reports/*.txt, target/surefire-reports/*.xml', allowEmptyArchive: true
                }
                success {
                    echo '✅ All tests passed!'
                }
                unstable {
                    echo '⚠️ Some tests failed'
                    script {
                        // Read test failure details
                        def testReport = findFiles(glob: '**/target/surefire-reports/*.txt')
                        if (testReport) {
                            def content = readFile testReport[0].path
                            echo "Test Report Summary:\n${content}"
                        }
                    }
                }
            }
        }
        
        stage('Quality Gate') {
            when {
                // Only enforce quality gate on main/master branches
                expression { 
                    env.BRANCH_NAME == 'main' || 
                    env.BRANCH_NAME == 'master' ||
                    env.BRANCH_NAME == 'advance-calculator'
                }
            }
            steps {
                script {
                    // Check if tests passed
                    echo "Running quality checks..."
                    
                    // You can add more quality checks here:
                    // - Code coverage thresholds
                    // - Static analysis
                    // - Security scanning
                    
                    // For now, just check test results
                    def testFailed = currentBuild.result == 'UNSTABLE' || currentBuild.result == 'FAILURE'
                    
                    if (testFailed && (env.BRANCH_NAME == 'main' || env.BRANCH_NAME == 'master')) {
                        error "❌ Quality gate failed: Tests must pass on main/master branches"
                    } else if (testFailed) {
                        echo "⚠️ Tests failed on feature branch ${env.BRANCH_NAME}. Continuing..."
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }
        
        stage('Package') {
            when {
                // Package even if tests failed (except on main/master)
                not {
                    allOf {
                        expression { currentBuild.result == 'FAILURE' }
                        expression { env.BRANCH_NAME == 'main' || env.BRANCH_NAME == 'master' }
                    }
                }
            }
            steps {
                bat 'mvn package -DskipTests --no-transfer-progress'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
        
        stage('Deploy') {
            when {
                // Only deploy if tests passed
                expression { 
                    (env.BRANCH_NAME == 'main' || env.BRANCH_NAME == 'master') &&
                    currentBuild.result != 'UNSTABLE' && 
                    currentBuild.result != 'FAILURE'
                }
            }
            steps {
                script {
                    echo "Deploying to local repository..."
                    bat 'mvn deploy -DskipTests --no-transfer-progress -DaltDeploymentRepository=local::default::file:C:/maven-repo'
                }
            }
        }
    }
    
    post {
        always {
            script {
                // Clean up but keep artifacts
                bat '''
                    echo "=== Build Summary ==="
                    echo "Result: ${currentBuild.currentResult}"
                    echo "Duration: ${currentBuild.durationString}"
                    
                    if exist target\\*.jar (
                        echo "JAR files created:"
                        dir /b target\\*.jar
                    )
                    
                    if exist target\\surefire-reports (
                        echo "Test reports available"
                    )
                '''
            }
        }
        
        success {
            echo "✅ Pipeline completed successfully!"
        }
        
        unstable {
            echo "⚠️ Pipeline completed with test failures"
            
            script {
                // Send notification about failing test
                def testReport = findFiles(glob: '**/target/surefire-reports/*.txt')
                if (testReport) {
                    def summary = readFile testReport[0].path
                    def lines = summary.split('\n')
                    def failureLine = lines.find { it.contains('Failures:') }
                    echo "Test failure details: ${failureLine}"
                }
            }
        }
        
        failure {
            echo "❌ Pipeline failed!"
        }
        
        cleanup {
            // Optional: clean workspace after everything is done
            // cleanWs()
        }
    }
}
