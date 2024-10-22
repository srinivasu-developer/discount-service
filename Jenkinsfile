pipeline {
    agent any

    environment {
        DOCKER_REGISTRY_CREDENTIALS = 'docker-hub-credentials' // TODO: need to replace this
        IMAGE_NAME = 'myrepo/discount-service' // TODO: Replace with Docker image name
        IMAGE_TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/srinivasu-developer/discount-service.git'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build -x test'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build Docker image
                    sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    // Push the Docker image to Docker Hub or your private registry
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_REGISTRY_CREDENTIALS) {
                        sh "docker push ${IMAGE_NAME}:${IMAGE_TAG}"
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // using kubernates
                    sh 'kubectl apply -f deployment.yaml'
                }
            }
        }
    }

    post {
        success {
            echo 'Build, test, and deploy completed successfully!'
        }

        failure {
            echo 'Pipeline failed.'
        }

        always {
            // Cleanup Docker images to free up space
            sh 'docker system prune -f'
        }
    }
}
