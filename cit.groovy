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
	}
}
