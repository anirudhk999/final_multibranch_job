pipeline {
    agent any
 
    tools
    {
        maven 'Maven 3.8.4'
    }


     environment {
        GIT_REPO = 'https://github.com/anirudhk999/java_sorting_algorithms'
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
            cleanWs()
        }
    }
}


