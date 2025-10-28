pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps { checkout scm }
        }
        stage('Compile & Run') {
            steps {
                script {
                    if (isUnix()) {
                        sh '''
                        mkdir -p out
                        javac -d out flightsearch.java
                        printf "402\n" | java -cp out flightsearch
                        '''
                    } else {
                        bat '''
                        if not exist out mkdir out
                        javac -d out flightsearch.java
                        echo 402 | java -cp out flightsearch
                        '''
                    }
                }
            }
        }
        stage('Frontend') {
            steps {
                script {
                    // If a static frontend exists, copy it to out/static and archive it as an artifact
                    if (fileExists('src/main/resources/static/index.html')) {
                        if (isUnix()) {
                            sh '''
                            mkdir -p out/static
                            cp src/main/resources/static/index.html out/static/index.html
                            ls -la out/static || true
                            '''
                        } else {
                            bat '''
                            if not exist out\static mkdir out\static
                            copy /Y src\\main\\resources\\static\\index.html out\\static\\index.html
                            dir out\\static || echo no-frontend
                            '''
                        }
                    } else {
                        echo 'No frontend static file found at src/main/resources/static/index.html'
                    }
                }
            }
        }
    }
    post {
        always { echo 'Pipeline finished' }
    }
}
