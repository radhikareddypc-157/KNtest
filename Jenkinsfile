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
                bat 'docker build -t greatest-java-app .'
            }
        }
        stage('Start and Stop Container') {
            steps {
                bat 'docker rm -f my-java-container || exit 0'
                bat 'docker run -d --name my-java-container greatest-java-app'
                bat 'docker ps -a'
                bat 'docker logs my-java-container'
                bat 'docker stop my-java-container || exit 0'
                bat 'docker rm -f my-java-container'
            }
        }
    }
}
