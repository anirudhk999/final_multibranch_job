pipeline {
    agent any
 
     environment {
        GIT_REPO = 'https://github.com/anirudhk999/java_sorting_algorithms'
        DEVELOPERS_EMAIL = 'developers@example.com'
    }

    stages {
        stage('Checkout')
        {
            git branch: "${BRANCH_NAME}", url: "${GIT_REPO}"
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
                // Only run this stage if all tests passed
                expression { currentBuild.currentResult == 'SUCCESS' && env.GIT_BRANCH == 'dev'}
            }
            steps {
                // Merge dev to master
                bat 'git checkout master'
                bat 'git merge ' + currentBranch
                bat 'git push origin master'
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


