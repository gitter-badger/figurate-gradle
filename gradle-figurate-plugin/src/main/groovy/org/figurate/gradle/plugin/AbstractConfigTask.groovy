package org.figurate.gradle.plugin

import groovy.text.Template
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskOutputs

/**
 * Created by fortuna on 10/05/14.
 */
abstract class AbstractConfigTask extends DefaultTask {

    static final String DEFAULT_CONFIG_DIR = 'conf'

    def outputDir = "$project.buildDir/$DEFAULT_CONFIG_DIR"

    @TaskAction
    def createConfig() {
        TaskOutputs taskOutputs = outputs.dir project.file(outputDir)
        taskOutputs.files.each { outputDir ->
            outputDir.mkdirs()
        }
        def configTemplate = getTemplate()
        def config = configTemplate.make(getBinding())
        new File(outputDir, getConfigFilename()).write(config as String)
    }

    abstract Template getTemplate()

    abstract Map getBinding()

    abstract String getConfigFilename()
}
