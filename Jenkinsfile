// docker plugin reference docs https://docs.cloudbees.com/docs/cloudbees-ci/latest/pipelines/docker-workflow
pipeline {
    agent { label 'k8s-ansible-pod' }

    options{
        buildDiscarder(logRotator(numToKeepStr: '5', daysToKeepStr: '5'))
    }

    stages {
        stage('Print build info') {
            steps {
                sh 'ls -la'
                sh 'printenv'
            }
        }
        stage ("Run Molecule lint") {
            steps {
                container('molecule') {
                    sh 'molecule lint'
                }
            }
        }
        stage ("Run Molecule create") {
            steps {
                container('molecule') {
                    sh 'molecule create'
                }
            }
        }
        stage ("Run Molecule converge") {
            steps {
                container('molecule') {
                    sh 'molecule converge'
                }
            }
        }
        stage ("Run Molecule idemotence") {
            steps {
                container('molecule') {
                    sh 'molecule idempotence'
                }
            }
        }
        stage ("Run Molecule verify") {
            steps {
                container('molecule') {
                    sh 'molecule verify'
                }
            }
        }
    }
}
