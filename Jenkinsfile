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
// One publish for both branches. The Nexus login is the Jenkins credential dim-nexus; the
// Maven Central login (the Portal token, CS_USERNAME/CS_PASSWORD) is the credential named by
// PORTAL_CREDENTIAL when Jenkins has it — without it bnd still releases to the Nexus, and says so.
// GPG_PASSPHRASE (set by the stage from GNUPG_PASSPHRASE) is what makes the fennec library add the
// Sonatype repository and sign: a release goes to Maven Central, a snapshot to the Portal's
// snapshot repository, as emf.osgi does it.
def PORTAL_CREDENTIAL = 'central-portal-token'

def publish() {
    def creds = [usernamePassword(credentialsId: 'dim-nexus', usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD')]
    def portal = false
    try {
        withCredentials([usernamePassword(credentialsId: PORTAL_CREDENTIAL, usernameVariable: 'CS_USERNAME', passwordVariable: 'CS_PASSWORD')]) {
            portal = true
        }
    } catch (e) {
        echo "No Jenkins credential '${PORTAL_CREDENTIAL}' — nothing goes to Maven Central from this run (${e.message})"
    }
    if (portal) {
        creds << usernamePassword(credentialsId: PORTAL_CREDENTIAL, usernameVariable: 'CS_USERNAME', passwordVariable: 'CS_PASSWORD')
    }
    if (!env.GPG_PASSPHRASE) {
        echo 'GNUPG_PASSPHRASE is not set on this Jenkins — unsigned, so nothing goes to Maven Central from this run'
    }
    withCredentials(creds) {
        sh './gradlew clean build release $COMMON_ARGS'
    }
}

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
                // dim-snapshot and the Sonatype repository to the Portal's snapshot repository.
                DO_RELEASE = 'false'
                GPG_PASSPHRASE = "${env.GNUPG_PASSPHRASE ?: ''}"
                GNUPGHOME = "${env.GNUPGHOME ?: '/var/jenkins_home/.gnupg'}"
            }
            steps {
                echo "Publishing a snapshot of ${env.BRANCH_NAME} to the DIM Nexus and the Maven Central snapshots"
                // The public agent (/opt/ci-public/agent) has no connection settings of its own:
                // the run of 2026-10-22 20:54 published with the dim-nexus credential, the one
                // without it got 401.
                script { publish() }
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
                script { publish() }
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
