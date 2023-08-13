// docker plugin reference docs https://docs.cloudbees.com/docs/cloudbees-ci/latest/pipelines/docker-workflow
pipeline {
    agent { label 'k8s-ansible-pod' }

    options{
        buildDiscarder(logRotator(numToKeepStr: '5', daysToKeepStr: '5'))
        checkoutToSubdirectory('curuvija.golang')
    }

    stages {
        stage('Print build info') {
            steps {
                sh 'ls -la'
                sh 'printenv'
            }
        }
        stage ("Run ansible-lint") {
            steps {
                container('molecule') {
                    dir('curuvija.golang') {
                        sh 'ansible-lint'
                    }
                }
            }
        }
        stage ("Run yamllint") {
            steps {
                container('molecule') {
                    dir('curuvija.golang') {
                        sh 'yamllint .'
                    }
                }
            }
        }
        stage ("Run flake8") {
            steps {
                container('molecule') {
                    dir('curuvija.golang') {
                        sh 'flake8'
                    }
                }
            }
        }
        stage ("Run Molecule create") {
            steps {
                container('molecule') {
                    dir('curuvija.golang') {
                        sh 'molecule create'
                    }
                }
            }
        }
        stage ("Run Molecule converge") {
            steps {
                container('molecule') {
                    dir('curuvija.golang') {
                        sh 'molecule converge'
                    }
                }
            }
        }
        stage ("Run Molecule idemotence") {
            steps {
                container('molecule') {
                    dir('curuvija.golang') {
                        sh 'molecule idempotence'
                    }
                }
            }
        }
        stage ("Run Molecule verify") {
            steps {
                container('molecule') {
                    dir('curuvija.golang') {
                        sh 'molecule verify'
                    }
                }
            }
        }
    }
}
