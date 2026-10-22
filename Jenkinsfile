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
        stage('Clean workspace') {
            steps {
                // Jenkins reuses the checkout. Projects removed from git leave their ignored
                // bin/ and generated/ folders behind, and the bnd workspace plugin takes every
                // root folder for a project — so stale ones get built as empty projects. Drop
                // everything git does not track, except the workspace-local artifact cache.
                sh 'git clean -ffdx -e .m2'
            }
        }

        stage('Build and test') {
            when {
                not { anyOf { branch 'main'; branch 'snapshot'; branch 'sunorcloud' } }
            }
            steps {
                echo "Building ${env.BRANCH_NAME}"
                sh './gradlew clean build $COMMON_ARGS'
            }
        }

        stage('Publish snapshot') {
            when {
                // sunorcloud is in here only to try the chain before main and snapshot move onto
                // this line of history (2026-10-22); take it out again afterwards.
                anyOf { branch 'snapshot'; branch 'sunorcloud' }
            }
            environment {
                // ${tstamp}-SNAPSHOT on every bundle, which sends the Release repository to
                // dim-snapshot. Without GPG_PASSPHRASE the Sonatype repository is not even
                // configured, so nothing reaches Maven Central from here.
                DO_RELEASE = 'false'
            }
            steps {
                echo "Publishing a snapshot of ${env.BRANCH_NAME} to the DIM Nexus"
                // The Nexus login: the Jenkins credential dim-nexus (the one the Docker pushes
                // use), handed to bnd as NEXUS_USERNAME/NEXUS_PASSWORD for the inline server in
                // cnf/ext/release.bnd. The public agent (/opt/ci-public/agent) has no connection
                // settings of its own — the run of 2026-10-22 20:54 published with the credential,
                // the one without it got 401.
                withCredentials([
                    usernamePassword(credentialsId: 'dim-nexus', usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD')
                ]) {
                    sh './gradlew clean build release $COMMON_ARGS'
                }
            }
        }

        stage('Publish release') {
            when {
                branch 'main'
            }
            environment {
                // Bare versions, which send the Release repository to dim-release.
                DO_RELEASE = 'true'
                // GPG_PASSPHRASE switches the fennec library's releng/central.bnd on: the
                // Sonatype Central Portal repository, signing, '-releaserepo: Sonatype' — and
                // cnf/ext/release.bnd keeps the Nexus beside it. This Jenkins has carried the
                // passphrase as GNUPG_PASSPHRASE since the old DIM library (which read exactly
                // that); the fennec library's name is GPG_PASSPHRASE, so it is mapped here.
                GPG_PASSPHRASE = "${env.GNUPG_PASSPHRASE ?: ''}"
                // The keyring the old setup signed with; gpg reads GNUPGHOME natively. Set the
                // variable on the agent to use another one.
                GNUPGHOME = "${env.GNUPGHOME ?: '/var/jenkins_home/.gnupg'}"
            }
            steps {
                script {
                    if (!env.GPG_PASSPHRASE) {
                        error('GNUPG_PASSPHRASE is not set on this Jenkins — without it nothing is signed and nothing reaches Maven Central.')
                    }
                }
                echo "Releasing ${env.BRANCH_NAME} to the DIM Nexus and Maven Central"
                // The Nexus login as for the snapshot. The Portal login (server id
                // production.portal.central.sonatype.com) has to arrive as CS_USERNAME and
                // CS_PASSWORD — from a Jenkins credential once it exists, or the agent's
                // connection settings.
                withCredentials([
                    usernamePassword(credentialsId: 'dim-nexus', usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD')
                ]) {
                    sh './gradlew clean build release $COMMON_ARGS'
                }
            }
        }

        stage('Export the launch') {
            when {
                anyOf { branch 'main'; branch 'snapshot'; branch 'sunorcloud' }
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
