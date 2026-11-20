
def call(String imageName, String imageTag, String dockerHubUser) {

    withEnv([
        "IMAGE_NAME=${imageName}",
        "IMAGE_TAG=${imageTag}",
        "DOCKER_HUB_USER=${dockerHubUser}"
    ]) {
        sh '''
            docker rmi "$DOCKER_HUB_USER/$IMAGE_NAME:$IMAGE_TAG" || true
        '''
    }
}

