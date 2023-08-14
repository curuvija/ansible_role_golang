// docker plugin reference docs https://docs.cloudbees.com/docs/cloudbees-ci/latest/pipelines/docker-workflow
pipeline {
    agent { label 'k8s-ansible-pod' }

    options{
        buildDiscarder(logRotator(numToKeepStr: '5', daysToKeepStr: '5'))
        checkoutToSubdirectory('curuvija.ansible_role_golang')
    }

    environment{
        ANSIBLE_ROLE_NAME = "ansible_role_golang"
        GITHUB_REPO_NAME = "ansible_role_golang"
        ANSIBLE_GALAXY_CREDENTIALS = credentials('ansible-galaxy')
        ANSIBLE_GALAXY_SERVER = 'https://galaxy.ansible.com'
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
                    dir('curuvija.ansible_role_golang') {
                        sh 'ansible-lint'
                    }
                }
            }
        }
        stage ("Run yamllint") {
            steps {
                container('molecule') {
                    dir('curuvija.ansible_role_golang') {
                        sh 'yamllint .'
                    }
                }
            }
        }
        stage ("Run flake8") {
            steps {
                container('molecule') {
                    dir('curuvija.ansible_role_golang') {
                        sh 'flake8'
                    }
                }
            }
        }
        stage ("Run Molecule create") {
            steps {
                container('molecule') {
                    dir('curuvija.ansible_role_golang') {
                        sh 'molecule create'
                    }
                }
            }
        }
        stage ("Run Molecule converge") {
            steps {
                container('molecule') {
                    dir('curuvija.ansible_role_golang') {
                        sh 'molecule converge'
                    }
                }
            }
        }
        stage ("Run Molecule idemotence") {
            steps {
                container('molecule') {
                    dir('curuvija.ansible_role_golang') {
                        sh 'molecule idempotence'
                    }
                }
            }
        }
        stage ("Run Molecule verify") {
            steps {
                container('molecule') {
                    dir('curuvija.ansible_role_golang') {
                        sh 'molecule verify'
                    }
                }
            }
        }
        // TODO: import role in ansible-galaxy on master branch -> https://docs.ansible.com/ansible/latest/cli/ansible-galaxy.html#role-import
        // TODO: check https://galaxy.ansible.com/docs/contributing/importing.html
        // TODO: use api key you can find here https://galaxy.ansible.com/me/preferences
        stage ("Import role into Ansible Galaxy") {
            when {
                allOf {
                    expression {
                        env.BRANCH_NAME == 'master'
                    }
                    //changeset "VERSION"
                }
            }
            steps {
                container('molecule') {
                    // usage: ansible-galaxy role import [-h] [-s API_SERVER] [--token API_KEY] [-c] [-v] [--no-wait] [--branch REFERENCE] [--role-name ROLE_NAME] [--status] github_user github_repo
                    sh 'ansible-galaxy role import --server ${ANSIBLE_GALAXY_SERVER} --token ${ANSIBLE_GALAXY_CREDENTIALS_PSW} --role-name ${ANSIBLE_ROLE_NAME} ${ANSIBLE_GALAXY_CREDENTIALS_USR} ${GITHUB_REPO_NAME}'
                }
            }
        }
    }
}
