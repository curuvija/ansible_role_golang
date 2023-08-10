// docker plugin reference docs https://docs.cloudbees.com/docs/cloudbees-ci/latest/pipelines/docker-workflow
pipeline {
    agent { label 'jenkins-jenkins-agent_molecule' }

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
                container('molecule_ubuntu2004') {
                    sh 'molecule lint'
                }
            }
        }
        stage ("Run Molecule create") {
            steps {
                container('molecule_ubuntu2004') {
                    sh 'molecule create'
                }
            }
        }
        stage ("Run Molecule converge") {
            steps {
                container('molecule_ubuntu2004') {
                    sh 'molecule converge'
                }
            }
        }
        stage ("Run Molecule idemotence") {
            steps {
                container('molecule_ubuntu2004') {
                    sh 'molecule idempotence'
                }
            }
        }
        stage ("Run Molecule verify") {
            steps {
                container('molecule_ubuntu2004') {
                    sh 'molecule verify'
                }
            }
        }
    }
}
