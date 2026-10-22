// The publishing pipeline. GitHub Actions (.github/workflows/build.yml) only builds and tests;
// this is the one that publishes:
//
//   any other branch  build, test
//   snapshot          build, test, release ${tstamp}-SNAPSHOT bundles to the DIM Nexus (dim-snapshot)
//   main              build, test, release bare-version bundles to the DIM Nexus (dim-release)
//                     and to Maven Central through the Sonatype Central Portal (bnd 7.4,
//                     autopublish), signed; then export the launch as a drift check
//
// The mechanics live in cnf/ext/release.bnd and the fennec library (releng/*.bnd), see
// docs/12-build-and-release.md. DO_RELEASE decides the version the bundles are built with,
// so build and release share one invocation.
pipeline {
    agent any

    tools {
        // javac.source/javac.target 25 in cnf/ext/fennec.bnd; Gradle 9.6.1 runs on it.
        jdk 'OpenJDK25'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        // Two concurrent runs of a branch would publish the same coordinates twice.
        disableConcurrentBuilds()
    }

    environment {
        // A workspace-local artifact cache, so concurrent jobs cannot corrupt each other's.
        COMMON_ARGS = "--no-daemon --stacktrace -Dmaven.repo.local=${WORKSPACE}/.m2"
    }

    stages {
        stage('Build and test') {
            when {
                not { anyOf { branch 'main'; branch 'snapshot' } }
            }
            steps {
                echo "Building ${env.BRANCH_NAME}"
                sh './gradlew clean build $COMMON_ARGS'
            }
        }

        stage('Publish snapshot') {
            when {
                branch 'snapshot'
            }
            environment {
                // ${tstamp}-SNAPSHOT on every bundle, which sends the Release repository to
                // dim-snapshot. Without GPG_PASSPHRASE the Sonatype repository is not even
                // configured, so nothing reaches Maven Central from here.
                DO_RELEASE = 'false'
            }
            steps {
                echo "Publishing a snapshot of ${env.BRANCH_NAME} to the DIM Nexus"
                sh './gradlew clean build release $COMMON_ARGS'
            }
        }

        stage('Publish release') {
            when {
                branch 'main'
            }
            environment {
                // Bare versions, which send the Release repository to dim-release.
                DO_RELEASE = 'true'
            }
            steps {
                echo "Releasing ${env.BRANCH_NAME} to the DIM Nexus and Maven Central"
                // GPG_PASSPHRASE switches the fennec library's releng/central.bnd on: the
                // Sonatype Central Portal repository, signing, '-releaserepo: Sonatype' — and
                // cnf/ext/release.bnd keeps the Nexus beside it. The key pair named by
                // GPG_KEY_ID has to be in the agent's gpg keyring; CS_USERNAME/CS_PASSWORD are
                // the Portal's user token.
                withCredentials([
                    string(credentialsId: 'gpg-passphrase', variable: 'GPG_PASSPHRASE'),
                    string(credentialsId: 'gpg-key-id', variable: 'GPG_KEY_ID'),
                    usernamePassword(credentialsId: 'central-portal-token', usernameVariable: 'CS_USERNAME', passwordVariable: 'CS_PASSWORD')
                ]) {
                    sh './gradlew clean build release $COMMON_ARGS'
                }
            }
        }

        stage('Export the launch') {
            when {
                anyOf { branch 'main'; branch 'snapshot' }
            }
            steps {
                echo "Exporting the launch of ${env.BRANCH_NAME}"
                // resolve first as a drift check: it recomputes -runbundles and fails the build if
                // the checked-in set no longer resolves. It rewrites the bndrun in the Jenkins
                // workspace, which is discarded — the committed file is what a developer resolves.
                sh './gradlew :org.gecko.weather.runtime:resolve.launch $COMMON_ARGS'
                sh './gradlew :org.gecko.weather.runtime:export.launch $COMMON_ARGS'
                archiveArtifacts artifacts: 'org.gecko.weather.runtime/generated/distributions/executable/launch.jar', fingerprint: true
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true,
                  testResults: '*/generated/test-reports/**/TEST-*.xml'
        }
    }
}
