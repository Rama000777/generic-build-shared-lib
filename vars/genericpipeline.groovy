import groovy.io.FileType;
import java.io.File;
import java.util.Calendar.*;
import java.text.SimpleDateFormat
import hudson.model.*

@NonCPS
def call(Map config=[:]) {
	node {
		stage('SCM') {
			echo 'Gathering code from version control'
			git branch: '${branch}', url: 'https://github.com/Rama000777/jpademo.git'
		}
		stage('Build') {
			try {
				echo 'Building....'
				bat 'gradle clean build'
				releasenotes()
			} catch(ex) {
				echo 'Something went wrong'
				echo ex.toString()
				currentBuild.result = 'FAILURE'
			} finally {
				// clean up
			}
		}
		stage('Test') {
			echo 'Testing....'
		}
		stage('Deploy') {
			echo 'Deploying....'
		}
	}
}