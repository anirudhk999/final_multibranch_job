pipeline {
    agent any
 
    tools
    {
        maven 'Maven 3.8.4'
    }


     environment {
        GIT_REPO = 'https://github.com/anirudhk999/final_multibranch_job'
        DEVELOPERS_EMAIL = 'developers@example.com'
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
                    body: "ERROR IN ${env.BRANCH_NAME} BRANCH.\n\n CONSOLE OUTPUT -  ${env.BUILD_URL}",
                    to: "${env.DEVELOPERS_EMAIL}"
                }
            }
        }

    }
}


