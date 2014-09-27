package com.github.gliptak.jallele

import org.gradle.api.Project
import org.gradle.api.Plugin

class JAllelePlugin implements Plugin<Project> {
    void apply(Project target) {
        target.task('jallele', type: JAlleleTask)
    }
}
