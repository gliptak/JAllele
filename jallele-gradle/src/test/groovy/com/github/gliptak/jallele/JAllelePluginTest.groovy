package com.github.gliptak.jallele

import org.junit.Test
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project

import com.github.gliptak.jallele.JAlleleTask;

import static org.junit.Assert.*

class JAllelePluginTest {
    @Test
    public void greeterPluginAddsGreetingTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        project.apply plugin: 'com.github.gliptak.jallele'

        assertTrue(project.tasks.jallele instanceof JAlleleTask)
    }
}