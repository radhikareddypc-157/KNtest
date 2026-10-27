pipeline {
    agent any
    tools {
        maven 'Maven -3.10.0'
        jdk 'JDK17'
    }
    environment {
        KUBECONFIG = 'C:\\Users\\HP\\.kube\\config'
        DOCKER = '"C:\\Users\\HP\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe"'
        KUBECTL = '"C:\\Users\\HP\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\kubectl.exe"'
    }
    stages {
        stage('SET 1: Verify K8s') {
            steps {
                bat '%KUBECTL% cluster-info'
                bat '%KUBECTL% get nodes'
            }
        }
        stage('Build Maven') {
            steps { bat 'mvn clean package -DskipTests' }
        }
        stage('Build Docker') {
            steps { bat '%DOCKER% build -t greatest-java-app:latest .' }
        }
        stage('SET 3: Create Pod') {
            steps { bat '%KUBECTL% apply -f k8s/pod.yaml' }
        }
        stage('SET 8: ConfigMap') {
            steps { bat '%KUBECTL% apply -f k8s/configmap.yaml' }
        }
        stage('SET 9: PVC') {
            steps { bat '%KUBECTL% apply -f k8s/pvc.yaml' }
        }
        stage('SET 2,4,10: Deployment') {
            steps { bat '%KUBECTL% apply -f k8s/deployment.yaml' }
        }
        stage('SET 5: Service') {
            steps { bat '%KUBECTL% apply -f k8s/service.yaml' }
        }
        stage('SET 6: Scale to 3') {
            steps {
                bat '%KUBECTL% scale deployment java-app-deployment --replicas=3'
                bat '%KUBECTL% get pods'
            }
        }
        stage('SET 7: Update Image') {
            steps {
                bat '%DOCKER% build -t greatest-java-app:v2 .'
                bat '%KUBECTL% set image deployment/java-app-deployment java-container=greatest-java-app:v2'
            }
        }
        stage('SET 13: Rolling Update') {
            steps {
                bat '%KUBECTL% rollout status deployment/java-app-deployment'
                bat '%KUBECTL% get pods,svc,deployments'
            }
        }
    }
}
