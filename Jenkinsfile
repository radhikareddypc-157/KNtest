pipeline {
    agent any
    tools {
        maven 'Maven -3.10.0'
        jdk 'JDK17'
    }
    stages {
        stage('Build with Maven') {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage('Test with Maven') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Create Docker Image') {
            steps {
                bat '"C:\\Users\\HP\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" build -t greatest-java-app .'
            }
        }
        stage('Start and Stop Container') {
            steps {
                bat '"C:\\Users\\HP\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" rm -f my-java-container || exit 0'
                bat '"C:\\Users\\HP\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" run -d --name my-java-container greatest-java-app'
                bat '"C:\\Users\\HP\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" ps -a'
                bat '"C:\\Users\\HP\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" logs my-java-container'
                bat '"C:\\Users\\HP\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" stop my-java-container || exit 0'
                bat '"C:\\Users\\HP\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" rm -f my-java-container'
            }
        }
    }
}
