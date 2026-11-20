def call(String sonarCredentialsId) {

    withSonarQubeEnv('SonarQube') {

        sh """
            mvn sonar:sonar \
            -Dsonar.projectKey=employee-api \
            -Dsonar.projectName=employee-api
        """
    }
}
