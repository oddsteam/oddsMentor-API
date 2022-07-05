pipeline{
    //  กำหนด ชื่อ,IP,.. ของ agent --> any : can run any agent
    agent any
    //
    environment{
        ORGANIZATION = "odds-booking"
        REGISTRY = "swr.ap-southeast-2.myhuaweicloud.com"
        TAG = "api-mentor:${BRANCH_NAME}"
        API_BUILD_TAG = "${REGISTRY}/${ORGANIZATION}/${TAG}"
    }
    stages{
        stage("unit test"){
            steps{
                sh "echo '🚨 Unit tests when build.'"
            }
        }
        stage("build image and test "){
            steps{
                sh "docker build --rm --build-arg environment=${BRANCH_NAME} -t ${API_BUILD_TAG} ."
            }
        }
        stage("push docker image"){
            steps{
                sh """
                    docker login -u ap-southeast-2@OA4R6SQSJDS6O5TPXWUJ -p 092929273c8458b0141bdca0a6475a3f3103eb3f4fa57b4a5405635828bc4c9a ${REGISTRY}
                    docker push ${API_BUILD_TAG}
                """
            }
        }
        stage("deploy"){
            steps{
                    sh """
                       scp docker-compose.yml oddsbooking@159.138.240.167:./Mentor/docker-compose.yml
                       scp deploy-script.sh oddsbooking@159.138.240.167:./Mentor/deploy-script.sh
                       ssh -oStrictHostKeyChecking=no -t oddsbooking@159.138.240.167 \"
                           chmod +x ./Mentor/deploy-script.sh
                           REGISTRY=${REGISTRY} \
                           BRANCH_NAME=${BRANCH_NAME} \
                           ./Mentor/deploy-script.sh
                       \"
                    """
            }
        }
    }
}
