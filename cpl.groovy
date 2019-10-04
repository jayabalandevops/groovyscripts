folder("$BUName"){}
folder("$BUName/$ProductName"){}
pipelineJob("$BUName/$ProductName/CICD_$AppName"){
	parameters{
		stringParam("AppRepo", "$ApplicationRepo", "Git URL")
		stringParam("NodeName", "$NodeName", "")
		stringParam("AppName", "$AppName", "Application Name")
		stringParam("UnitTestTool", "$UnitTestRun", "")
		activeChoiceParam('Branch'){
			filterable()
			choiceType('SINGLE_SELECT')
			groovyScript{
				fallbackScript("Fallback choice script")	
				script('["select", "master", "develop"]')
			}
			description("select the branch to be built.")
		}
	}
	definition{
		cps {
			def jobScript = readFileFromWorkspace('cit.groovy')
			def approvals = org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval.get()
			approvals.approveScript(approvals.hash(jobScript, "groovy"))
			script(jobScript)
		}
	}	
}
