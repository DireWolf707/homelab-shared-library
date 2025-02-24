def call () {
    def TUNNEL_NAME = "my-tunnel"
    def BASE_DOMAIN = "dire-wolf.tech"
    def HOSTNAME = "${JOB_NAME}.${BASE_DOMAIN}"

    // def hostPort = sh(script: """
    //     docker inspect \
    //     --format='{{(index (index .NetworkSettings.Ports "${CONTAINER_PORT}/tcp") 0).HostPort}}' \
    //     ${JOB_NAME} 
    // """, 
    // returnStdout: true)
                    
    sh "docker exec cloudflared cloudflared tunnel route dns -f ${TUNNEL_NAME} ${HOSTNAME}"

    sh """
        docker run \
            --rm \
            -v ${JENKINS_HOME}/scripts:/app \
            -v /var/cloudflare:/home/nonroot/.cloudflared \
            node:22-alpine \
            sh -c "cd app && yarn && node updateCloudflareConfig.js ${HOSTNAME} http://localhost:${HOST_PORT}"
    """

    sh "docker exec cloudflared cloudflared tunnel ingress validate"

    sh "docker restart cloudflared"    
                
}