package com.applivery.gradle


class GitUtils {

    static def getGitParams() {

        String command = ""

        if (isGitRepository()) {
            def gitBranchProcess = "git rev-parse --abbrev-ref HEAD".execute()
            gitBranchProcess.waitFor()

            def gitCommitProcess = "git rev-parse --short HEAD".execute()
            gitCommitProcess.waitFor()

            command += " -F gitBranch=${gitBranchProcess.text}"
            command += " -F gitCommit=${gitCommitProcess.text}"
            command += getGitRemote()
            command += getGitTag()
        }

        return command
    }

    static def isGitRepository() {
        try {
            def isGitProcess = "git rev-parse HEAD".execute()
            isGitProcess.waitFor()
            return true
        } catch (Exception e) {
            return false
        }
    }

    static def getGitRemote() {
        try {

            def gitRepositoryURLProcess = "git config --get remote.origin.url".execute()
            gitRepositoryURLProcess.waitFor()
            return " -F gitRepositoryURL=${gitRepositoryURLProcess.text}"
        } catch (Exception e) {
            return ""
        }
    }

    static def getGitTag() {
        try {
            def gitTagProcess = "git describe --abbrev=0 --tags".execute()
            gitTagProcess.waitFor()

            def gitTagCommitProcess = "git rev-list -n 1 --abbrev-commit #{gitTag}".execute()
            gitTagCommitProcess.waitFor()

            def gitCommitProcess = "git rev-parse --short HEAD'".execute()
            gitCommitProcess.waitFor()


            if (gitTagCommitProcess.text == gitCommitProcess.text) {
                return " -F gitTag=${gitTagProcess.text}"
            } else {
                return ""
            }
        } catch (Exception e) {
            return ""
        }
    }
}
