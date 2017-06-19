# Gradle Applivery plugin

This project is a [gradle](https://gradle.org/) plugin to deploy your apk in [Applivery](https://www.applivery.com).

## Usage
```groovy
buildscript {
  repositories {
    ...
    jcenter()
    mavenLocal()
  }
  dependencies {
    ...
    classpath 'com.applivery.gradle:applivery-deploy:1.0.0'
  }
}

apply plugin: 'com.android.application'
apply plugin: 'applivery-plugin'

applivery {
    appId = [your user key]
    apiKey = [your api key]
}
```

## About Applivery.com

With [Applivery.com](https://www.applivery.com) you can easily distribute your Android Apps throughout a customizable platform with no need of your users have to be registered on it.

**The main purpose of this plugin is to upload a new Android build to [Applivery.com](https://www.applivery.com).**

If you usually use Fastlane tools to automate the most common development tasks now you can start using out Fastlane Plugin to easily deploy new Android versions of your Apps to Applivery and close your development the cycle: Build, Test & deploy like a pro!

This **gradle** plugin will also help you to have more context about the build, attaching and displaying the most relevant information: Direct link to the repository(GitHub & Bitbucket), commit hash, Branch, Tag, etc:

## Additional Parameters
The above examples are the most simple configuration you can have but in addition to the `appId` and `apiKey` parameters, you can add additional ones to fully customize the deployment process. They are:

| Param       | Description                 | Mandatory | Values       |
|-------------|-----------------------------|-----------|--------------|
| `appId`     | Applivery App ID            | YES       | string -> Available in the App details |
| `apiKey`    | Applivery API Key           | YES       | string -> Available the [Developers](https://dashboard.applivery.com/dashboard/developers) area       |
| `name`      | Applivery Build name        | NO        | string. i.e.: "RC 1.0"       |
| `notify`    | Notify users after deploy   | NO        | true / false |
| `notes`     | Release notes               | NO        | string. i.e.: "Bug fixing"       |
| `tags`      | Tags to identify the build  | NO        | string comma separated. i.e.: "RC1, QA" |
| `autoremove`| Remove the last build       | NO        | true / false |
| `buildPath` | Build path to the APK file  | NO        | string, by default it takes the release APK build path |

## Issues and Feedback

For any other issues and feedback about this plugin, please submit it to this repository or contact us at [support@applivery.com](mailto:support@applivery.com)
