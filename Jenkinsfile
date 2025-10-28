pipeline {
    agent any
    options { timestamps() }
    stages {
        stage('Prepare') {
            steps {
                echo 'Checking workspace and listing files'
                sh 'ls -la'
            }
        }
        stage('Build') {
            steps {
                script {
                    // Prefer Maven if a pom.xml exists
                    if (fileExists('pom.xml')) {
                        echo 'Using Maven to build'
                        sh 'mvn -B -DskipTests clean package'
                    } else {
                        echo 'Compiling with javac'
                        sh 'javac -d out $(find . -name "*.java")'
                    }
                }
            }
        }
        stage('Run') {
            steps {
                // Run the main class; provide a sample input
                echo 'Running flightsearch with sample input'
                sh 'printf "402\n" | java -cp out flightsearch'
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'out/**', fingerprint: true
            }
        }
    }
    post {
        always {
            echo 'Pipeline finished'
        }
        failure {
            mail to: 'devops@example.com', subject: "Build failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}", body: "See Jenkins console output"
        }
    }
}
