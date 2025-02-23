def call() {
    withCredentials([file(credentialsId: "${JOB_NAME}-env", variable: 'ENV_FILE')]) {
        sh "cp -f ${ENV_FILE} ${ENV_FILE_PATH}"
    }
}