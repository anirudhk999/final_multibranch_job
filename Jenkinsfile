pipeline {
    agent any
 
    // Get the current branch
    def currentBranch = env.GIT_BRANCH
 
    stages {
        stage('Checkout')
        {
            // Checkout the current branch
            git branch: currentBranch
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
                expression { currentBuild.currentResult == 'SUCCESS' && currentBranch == 'dev'}
            }
            steps {
                // Merge dev to master
                bat 'git checkout master'
                bat 'git merge ' + currentBranch
                bat 'git push origin master'
            }
        }
    }
}


