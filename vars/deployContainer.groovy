def call (String cmd) {
    sh "docker rm -f ${CONTAINER_NAME} || true"
    
    sh """
        docker run \
            -d \
            --restart always \
            -p ${CONTAINER_PORT} \
            -v ${WORKSPACE}:/app \
            --name ${CONTAINER_NAME} \
            ${CONTAINER_ENVIRONMENT} \
            sh -c "cd app && ${cmd}"
    """
}