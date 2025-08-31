pipeline {
    agent any

    stages {
        stage('Checkout Source') {
            steps {
                git 'https://github.com/BalajiGeetha/BookManagementRestAssuredTestNgAuto.git'
            }
        }
        stage('Build and Test') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Publish Test Results') {
            steps {
                sh 'allure generate allure-results --clean -o allure-report'
                sh 'allure open allure-report'

            }
        }

    }
}
