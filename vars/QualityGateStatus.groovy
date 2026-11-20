def call(String sonarCredentialsId) {

    timeout(time: 5, unit: 'MINUTES') {

        def qg = waitForQualityGate(
            abortPipeline: true
        )

        if (qg.status != 'OK') {
            error "SonarQube Quality Gate failed: ${qg.status}"
        }

        echo "SonarQube Quality Gate: ${qg.status}"
    }
}
