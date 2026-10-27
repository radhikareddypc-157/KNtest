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
        stage('SET 1: Verify K8s Connection') {
            steps {
                bat '%KUBECTL% cluster-info'
                bat '%KUBECTL% get nodes'
            }
        }
        stage('Build Maven') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }
        stage('Build Docker Image') {
            steps {
                bat '%DOCKER% build -t greatest-java-app:latest .'
                bat '%DOCKER% build -t greatest-java-app:v2 .'
            }
        }
        stage('SET 3: Create Pod') {
            steps {
                bat '%KUBECTL% apply -f k8s/pod.yaml'
            }
        }
        stage('SET 8: ConfigMap') {
            steps {
                bat '%KUBECTL% apply -f k8s/configmap.yaml'
            }
        }
        stage('SET 9: PVC') {
            steps {
                bat '%KUBECTL% apply -f k8s/pvc.yaml'
            }
        }
        stage('SET 2,4,10: Deploy') {
            steps {
                bat '%KUBECTL% apply -f k8s/deployment.yaml'
            }
        }
        stage('SET 5: Expose Service') {
            steps {
                bat '%KUBECTL% apply -f k8s/service.yaml'
            }
        }
        stage('SET 6: Scale to 3') {
            steps {
                bat '%KUBECTL% scale deployment java-app-deployment --replicas=3'
                bat '%KUBECTL% get pods'
            }
        }
        stage('SET 7 & 13: Rolling Update') {
            steps {
                bat '%KUBECTL% set image deployment/java-app-deployment java-container=greatest-java-app:v2 || exit 0'
                bat '%KUBECTL% rollout status deployment/java-app-deployment'
            }
        }
        stage('SET 14: Verify All') {
            steps {
                bat '%KUBECTL% get pods,svc,deployments,pvc,configmaps'
            }
        }
    }
}
