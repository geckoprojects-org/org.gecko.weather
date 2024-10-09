pipeline  {
    agent any

    tools {
        jdk 'OpenJDK17'
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }

    stages {
        stage('Unit & Integration Tests') {
            steps {
                script {
                    try {
                        sh './gradlew clean testOSGi --info --stacktrace -Dmaven.repo.local=${WORKSPACE}/.m2 --no-daemon'
                    } finally {
                        junit testResults: '**/generated/test-reports/testOSGi/TEST-*.xml', skipPublishingChecks: true
                    }
                }
            }
        }
        stage('Main branch release') {
            when { 
                branch 'main' 
            }
            steps {
                echo "I am building on ${env.BRANCH_NAME}"
                sh "./gradlew clean build release -Drelease.dir=$JENKINS_HOME/repo.gecko/release/org.gecko.weather --info --stacktrace -Dmaven.repo.local=${WORKSPACE}/.m2"
            }
        }
        stage('Snapshot branch release') {
            when { 
                branch 'snapshot'
            }
            steps  {
                echo "I am building on ${env.JOB_NAME}"
                sh "./gradlew clean release --info --stacktrace -Dmaven.repo.local=${WORKSPACE}/.m2"
                sh "mkdir -p $JENKINS_HOME/repo.gecko/snapshot/org.gecko.weather"
                sh "rm -rf $JENKINS_HOME/repo.gecko/snapshot/org.gecko.weather/*"
                sh "cp -r cnf/release/* $JENKINS_HOME/repo.gecko/snapshot/org.gecko.weather"
            }
        }
        stage('Prepare Docker') {
            when {                                                                                                                         
                branch 'main'
            }
            steps  {
                echo "I am preparing docker: ${env.GIT_BRANCH}"

                sh "./gradlew prepareDocker --info --stacktrace -Dmaven.repo.local=${WORKSPACE}/.m2"

            }

        }

        stage('Docker Bridge Image build'){
            when {
                branch 'main'
            }
            steps  {
                echo "I am building and publishing a docker image on branch: ${env.GIT_BRANCH}"

                step([$class: 'DockerBuilderPublisher',
                      dockerFileDirectory: 'docker',
                            cloud: 'docker',
                            tagsString: """devel.data-in-motion.biz:6000/scj/weather:latest
                                        devel.data-in-motion.biz:6000/scj/weather:0.1.0.${VERSION}""",
                            pushOnSuccess: true,
                            pushCredentialsId: 'dim-nexus'])
          }
        }

    }

}
