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
// One publish for both branches. Everything that can publish comes from Jenkins credentials:
//   dim-nexus              username/password  the DIM Nexus login (NEXUS_USERNAME/NEXUS_PASSWORD)
//   central-portal-token   username/password  the Sonatype Central Portal user token (CS_USERNAME/CS_PASSWORD)
//   gnupg-passphrase       secret text        passphrase of the signing key (GPG_PASSPHRASE)
//   gnupg-private-key      secret file        ASCII export of the signing key pair; imported into a
//                                             keyring of this run only and removed afterwards, as
//                                             emf.osgi does it on GitHub. Without it, the keyring
//                                             GNUPGHOME names (the master's) is used as it is.
// GPG_PASSPHRASE is what makes the fennec library add the Sonatype repository and sign: a release
// goes to Maven Central, a snapshot to the Portal's snapshot repository. Both targets are the
// point of this stage, so a missing credential fails the run with its name instead of quietly
// publishing to the Nexus alone.
def publish() {
    def missing = []
    def found = []
    // The binding object itself cannot be read in the sandbox (no credentialsId field), so the id
    // travels beside it; Jenkins' own message says whether the id is unknown or of another type.
    def has = { id, binding ->
        try {
            withCredentials([binding]) { }
            found << id
            return true
        } catch (e) {
            missing << "${id} (${e.message})"
            return false
        }
    }
    def nexus = usernamePassword(credentialsId: 'dim-nexus', usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD')
    def portal = usernamePassword(credentialsId: 'central-portal-token', usernameVariable: 'CS_USERNAME', passwordVariable: 'CS_PASSWORD')
    def passphrase = string(credentialsId: 'gnupg-passphrase', variable: 'GPG_PASSPHRASE')
    def key = file(credentialsId: 'gnupg-private-key', variable: 'GNUPG_KEY_FILE')
    def creds = []
    if (has('dim-nexus', nexus)) creds << nexus
    if (has('central-portal-token', portal)) creds << portal
    if (has('gnupg-passphrase', passphrase)) creds << passphrase
    if (has('gnupg-private-key', key)) creds << key
    if (missing) {
        error("Jenkins credentials missing on this controller: ${missing.join('; ')}. Found: ${found.join(', ') ?: 'none'}. Nothing is published until all four exist under exactly these ids and types (Manage Jenkins > Credentials): dim-nexus and central-portal-token as 'Username with password', gnupg-passphrase as 'Secret text', gnupg-private-key as 'Secret file'.")
    }
    echo "publish on node '${env.NODE_NAME}' with credentials ${found.join(', ')}; signing key imported into a keyring of this run"
    withCredentials(creds) {
        withEnv(["GNUPGHOME=${env.WORKSPACE}/.gnupg-run"]) {
            try {
                sh 'rm -rf "$GNUPGHOME" && mkdir -p "$GNUPGHOME" && chmod 700 "$GNUPGHOME" && gpg --batch --quiet --import "$GNUPG_KEY_FILE" && gpg --batch --list-secret-keys --with-colons | grep -c "^sec" | sed "s/^/secret keys in the run keyring: /"'
                // What bnd will release to: both names, or the run is pointless.
                def repos = sh(returnStdout: true, script: './gradlew -q :org.gecko.weather.api:bndproperties $COMMON_ARGS 2>/dev/null | grep "^-releaserepo:" || true').trim()
                echo "bnd ${repos}"
                if (!repos.contains('Sonatype') || !repos.contains('Release')) {
                    error("bnd would release to '${repos}' - expected Sonatype and Release. GPG_PASSPHRASE did not reach bnd, or maven-central is off (cnf/ext/release.bnd, the fennec library's releng/central.bnd).")
                }
                sh './gradlew clean build release $COMMON_ARGS'
            } finally {
                sh 'rm -rf "$GNUPGHOME"'
            }
        }
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
