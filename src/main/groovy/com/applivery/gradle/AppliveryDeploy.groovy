package com.applivery.gradle


import com.applivery.gradle.exceptions.InvalidApiKeyException
import com.applivery.gradle.exceptions.InvalidAppIdException
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class AppliveryDeploy extends DefaultTask {

    @TaskAction
    def action() {

        def config = project.extensions.applivery

        if (config.apiKey == null || config.apiKey.length() == 0) {
            throw new InvalidApiKeyException()
        }

        if (config.appId == null || config.appId.length() == 0) {
            throw new InvalidAppIdException()
        }

        def command = "curl https://dashboard.applivery.com/api/builds"
        command += " -H Authorization:${config.apiKey}"
        command += " -F app=${config.appId}"
        command += " -F versionName=${config.name}"
        command += " -F notes=${config.notes}"
        command += " -F notify=${config.notify}"
        command += " -F autoremove=${config.autoremove}"
        command += " -F os=${config.os}"
        command += " -F tags=${config.tags}"
        command += " -F deployer=appliveryGradlePlugin"
        command += " -F package=@${config.buildPath}"
        command += " -F buildNumber=${getIntegrationNumber()}"
        command += GitUtils.getGitParams()

        def process = command.execute()
        process.waitFor()

        if (process.exitValue() != 0) {
            println "DEPLOY FAILED WITH CURL EXIT CODE ${process.exitValue()}"
            return 1
        } else {
            println("DEPLOY SUCCESSFUL")
            return 0
        }
    }

    static def getIntegrationNumber() {

        def jenkinsIntegrationNumber = System.getenv("BUILD_NUMBER")
        def travisIntegrationNumber = System.getenv("TRAVIS_BUILD_NUMBER")
        def command = ""

        if (jenkinsIntegrationNumber != null) {
            command += jenkinsIntegrationNumber
        } else if (travisIntegrationNumber != null) {
            command += travisIntegrationNumber
        }

        return command
    }
}