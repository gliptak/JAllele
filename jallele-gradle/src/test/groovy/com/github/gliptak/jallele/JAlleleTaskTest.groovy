package com.github.gliptak.jallele

import org.junit.Test
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project

import com.github.gliptak.jallele.JAlleleTask;

import static org.junit.Assert.*

// START SNIPPET test-task
class JAlleleTaskTest {
    @Test
    public void canAddTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        def task = project.task('greeting', type: JAlleleTask)
        assertTrue(task instanceof JAlleleTask)
    }
}
// END SNIPPET test-task
