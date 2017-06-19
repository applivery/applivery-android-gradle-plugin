package com.applivery.gradle


import org.gradle.api.Plugin
import org.gradle.api.Project

class AppliveryPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.extensions.add("applivery", AppliveryConfig)

        project.task("appliveryDeploy", type: AppliveryDeploy) {
            group = "Deploy"
            description = "Deploy app to Applivery"
        }
    }
}
