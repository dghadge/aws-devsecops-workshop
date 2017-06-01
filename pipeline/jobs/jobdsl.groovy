def rubyVersion = 'ruby-2.2.5@devsecops'

pipelineJob('aws-devsecops-workshop') {
  displayName('AWS DevSecOps Workshop Pipeline')

  description('An example pipeline showcasing the deployment of an application with a security focused pipeline.')

  concurrentBuild(false)

  definition {
    cpsScm {
      scm {
        git {
          branch('master')
          remote {
            credentials('jenkins')
            url('git@github.com:dghadge/aws-devsecops-workshop.git')
          }
        }
      }

      scriptPath('Jenkinsfile')
    }
  }

  wrappers {
    rvm(rubyVersion)
  }

  triggers {
    scm('* * * * *') {
      ignorePostCommitHooks(true)
    }
  }
}
