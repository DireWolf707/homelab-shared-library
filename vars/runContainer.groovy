def call (String cmd) {
    sh """
        docker run \
            --rm \
            -v ${WORKSPACE}:/app \
            ${CONTAINER_ENVIRONMENT} \
            sh -c "cd app && ${cmd}"
    """
}