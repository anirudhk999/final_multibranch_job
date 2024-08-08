pipeline {
    agent any

    tools
    {
        maven 'Maven 3.8.4'
        jdk 'jdk17'
    }


    environment {
        GIT_REPO = 'https://github.com/anirudhk999/final_multibranch_job'
        DEVELOPERS_EMAIL = 'developers@example.com'
        SONAR_SCANNER_HOME = tool name : 'SonarQube Scanner'
        SONAR_PROJECT_KEY = 'anirudhk999'
        SONAR_ORG = 'anirudhk999'
        SONAR_TOKEN = credentials('79897aa8-86ef-4c7e-a9fe-3117fe626b4f')
    }

 
    stages {
        stage('Checkout')
        {
            steps{
                git branch: "${BRANCH_NAME}", url: "${GIT_REPO}"
            }
        }
        stage('Build') {
            steps {
                // Run Maven build
                bat 'mvn -B -DskipTests clean package'
            }
        }
 
        stage('Test') {
            steps {
                // Run Maven tests
                bat 'mvn test'
            }
        }

        stage('Run App.java') {
            steps {
                bat 'java -cp target\\classes sortingalgo.App'
            }
        }

        stage('Setup Python')
        {
            steps{
                bat 'python -m venv venv2'
                bat '.\\venv2\\Scripts\\activate'
            }
        }
        
        stage('Install All Dependencies')
        {
            steps{
                bat 'pip install -r "requirements.txt"'
            }
        }
        
        stage('Run Python Script')
        {
            steps
            {
                bat 'python stats.py'
            }
        }
 
        // stage('SonarQube Analysis') {
        //     steps {
        //         withSonarQubeEnv('SonarQube') {
        //             sh 'mvn sonar:sonar'
        //         }
        //     }
        // }

        stage('SonarQube Analysis') {
            steps {
                script {
                        //def scannerHome = tool 'SonarQube Scanner'
                        withSonarQubeEnv('sonarQube') {
                            //bat "mvn sonar:sonar -Dsonar.projectKey=${SONAR_PROJECT_KEY} -Dsonar.organization=${SONAR_ORG} -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=${SONAR_TOKEN}"
                            sh "mvn sonar:sonar -Dsonar.host.url= https://sonarcloud.io -Dsonar.login=${SONAR_ORG} -Dsonar.password=${SONAR_TOKEN} -Dsonar.projectKey=${SONAR_PROJECT_KEY} -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml'"
                        }
                    }
                }
        }
 
        stage('Quality Gate') {
            steps {
                script {
                    timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                    }
                }
            }
        }


 
        stage('Merge to Master') {
            when {
                branch 'dev'
                expression {
                    return currentBuild.resultIsBetterOrEqualTo('SUCCESS')
                }
            }
            steps {
                script {
                    bat '''
                    git checkout -f master
                    git merge dev
                    git push
                    '''
                }
            }
        }

    }

    post
    {
        always
        {
            bat '.\\venv2\\Scripts\\deactivate'
            cleanWs()
        }
        failure {
            script {
                if (env.BRANCH_NAME == 'dev') {
                    emailext subject: "Build failed in Jenkins: ${currentBuild.fullDisplayName}",
                             body: "Something is wrong with ${env.BRANCH_NAME} branch.\n\nCheck console output at ${env.BUILD_URL} to view the results.",
                             to: "${env.DEVELOPERS_EMAIL}"
                }
            }
        }

    }
}