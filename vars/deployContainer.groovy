def call (String cmd) {
    sh "docker rm -f ${JOB_NAME} || true"
    
    sh """
        docker run \
            -d \
            --restart always \
            -p ${HOST_PORT}:${CONTAINER_PORT} \
            -v ${WORKSPACE}:/app \
            --name ${JOB_NAME} \
            ${CONTAINER_ENVIRONMENT} \
            sh -c "cd app && ${cmd}"
    """
}