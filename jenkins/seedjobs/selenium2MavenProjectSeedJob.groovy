def giturl = 'https://github.com/rburton04/conference-app-tests'
job("selenium2-maven-project") {
  scm {
    git {
      remote {
        url(giturl)
      }
      extensions {
        cleanAfterCheckout()
      }
    }
  }
  triggers {
    scm('30/H * * * *')
    githubPush()
  }
  steps {
    maven {
        goals('clean test -Dgrid.server.url=http://192.168.1.44:4444/wd/hub')
        mavenInstallation('Maven 3.3.3')
        mavenOpts('-Xms512m -Xmx1024m')
        providedGlobalSettings('MyGlobalSettings')
    }
  }
  publishers {
    chucknorris()
    archiveJunit('**/target/surefire-reports/*.xml')
  }
}
listView('Selenium') {
    description('')
    filterBuildQueue()
    filterExecutors()
    jobs {
        regex(/selenium.*/)
    }
    columns {
        status()
        buildButton()
        weather()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
    }
}
