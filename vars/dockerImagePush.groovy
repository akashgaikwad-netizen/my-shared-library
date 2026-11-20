def call(String imageName, String imageTag, String dockerHubUser) {

    withCredentials([
        usernamePassword(
            credentialsId: 'dockerhub-credentials',
            usernameVariable: 'DOCKER_USER',
            passwordVariable: 'DOCKER_PASSWORD'
        )
    ]) {
        withEnv([
            "IMAGE_NAME=${imageName}",
            "IMAGE_TAG=${imageTag}",
            "DOCKER_HUB_USER=${dockerHubUser}"
        ]) {
            sh '''
                set -e

                if [ "$DOCKER_HUB_USER" != "$DOCKER_USER" ]; then
                    echo "Docker Hub username does not match Jenkins credentials"
                    exit 1
                fi

                echo "$DOCKER_PASSWORD" | docker login \
                    -u "$DOCKER_USER" --password-stdin

                trap 'docker logout >/dev/null 2>&1 || true' EXIT

                docker push "$DOCKER_HUB_USER/$IMAGE_NAME:$IMAGE_TAG"
            '''
        }
    }
}
