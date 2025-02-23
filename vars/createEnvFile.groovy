def call() {
    withCredentials([file(credentialsId: ENV_CREDS_ID, variable: 'ENV_FILE')]) {
        sh "cp -f ${ENV_FILE} ${ENV_FILE_PATH}"
    }
}