package com.github.gliptak.jallele

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class JAlleleTask extends DefaultTask {
    String greeting = 'hello from JAlleleTask'

    @TaskAction
    def greet() {
        println greeting
    }
}