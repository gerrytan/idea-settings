package com.github.alexandrecarlton.idea.settings.applier.impl.configurations.spring_boot

import com.github.alexandrecarlton.idea.settings.applier.api.SettingsApplier
import com.github.alexandrecarlton.idea.settings.layout.configurations.SpringBootSettings
import com.intellij.execution.RunManager
import com.intellij.openapi.project.Project
import com.intellij.spring.boot.run.SpringBootAdditionalParameter
import com.intellij.spring.boot.run.SpringBootApplicationConfigurationType
import com.intellij.spring.boot.run.SpringBootApplicationRunConfiguration
import javax.inject.Inject

class SpringBootSettingsApplier @Inject
constructor(private val project: Project, private val runManager: RunManager) : SettingsApplier<SpringBootSettings> {

    override fun apply(settings: SpringBootSettings) {
        val configuration = SpringBootApplicationRunConfiguration(
            project,
            SpringBootApplicationConfigurationType().defaultConfigurationFactory,
            settings.name)
        configuration.setMainClassName(settings.configuration.mainClass)
        settings.configuration.environment?.includeDependenciesWithProvidedScope?.let { configuration.isIncludeProvidedScope = it }
        settings.configuration.environment?.useClasspathOfModule?.let { configuration.setModuleName(it) }
        settings.configuration.environment?.vmOptions?.let { configuration.vmParameters = it }

        settings.configuration.springBoot?.overrideParameters
            ?.map { SpringBootAdditionalParameter(true, it.name, it.value) }
            ?.let { configuration.additionalParameters = it }

        val runnerAndConfigurationSettings = runManager.createConfiguration(configuration, SpringBootApplicationConfigurationType().defaultConfigurationFactory)
        runManager.addConfiguration(runnerAndConfigurationSettings)
    }

}