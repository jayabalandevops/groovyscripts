node("$NodeName"){
	wrks = env.WORKSPACE
	
	stage("Prepare"){
		println("Preparing the workspace...")
		git(
			url: "git@github.com:jayabalandevops/groovyscripts.git",
			branch: "master"
		)
		dir('config'){
			git(
				url: "git@github.com:jayabalandevops/arappconfigscripts.git",
				branch: "master"
			)
		}
		println("Prepared.")
	}
	stage("Clone"){
		println("Cloning the App Repo...")
		load 'app/clone.groovy'
		println("Cloned the App.")
	}
	stage("Build"){
		println("Building the Application...")
		load 'app/build.groovy'
		println("Built the App.")
	}
	stage("Deploy"){
		println("Deploying the application...")
		sh "chmod +x scripts/dep.sh"
		sh "scripts/dep.sh"
		println("Deployed the App.")
	}
}
