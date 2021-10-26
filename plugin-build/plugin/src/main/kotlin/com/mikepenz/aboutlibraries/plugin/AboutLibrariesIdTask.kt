package com.mikepenz.aboutlibraries.plugin

import com.mikepenz.aboutlibraries.plugin.util.LibrariesProcessor
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.TaskAction

@CacheableTask
abstract class AboutLibrariesIdTask : BaseAboutLibrariesTask() {

    @TaskAction
    fun action() {
        val collectedDependencies = readInCollectedDependencies()
        val processor = LibrariesProcessor(getDependencyHandler(), collectedDependencies, getConfigPath(), exclusionPatterns, fetchRemoteLicense)
        val libraries = processor.gatherDependencies()
        for (library in libraries) {
            println("${library.name} (${library.artifactVersion}) -> ${library.uniqueId}")
        }
    }
}