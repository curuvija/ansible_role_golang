pipeline {
    agent any

    stages {
        stage("get latest code") {
            steps {
                sh 'echo something'
            }
        }
    }
}
