package com.github.alexandrecarlton.idea.settings.applier.impl.build_execution_deployment

import com.github.alexandrecarlton.idea.settings.applier.api.SettingsApplier
import com.github.alexandrecarlton.idea.settings.fixtures.IdeaSettingsTestFixture
import com.github.alexandrecarlton.idea.settings.layout.build_execution_deployment.BuildExecutionDeploymentSettings
import com.github.alexandrecarlton.idea.settings.layout.build_execution_deployment.ImmutableBuildExecutionDeploymentSettings
import com.github.alexandrecarlton.idea.settings.layout.build_execution_deployment.ImmutableRequiredPlugin
import com.github.alexandrecarlton.idea.settings.layout.build_execution_deployment.build_tools.BuildToolsSettings
import com.github.alexandrecarlton.idea.settings.layout.build_execution_deployment.build_tools.ImmutableBuildToolsSettings
import com.github.alexandrecarlton.idea.settings.layout.build_execution_deployment.compiler.CompilerSettings
import com.github.alexandrecarlton.idea.settings.layout.build_execution_deployment.compiler.ImmutableCompilerSettings
import com.intellij.externalDependencies.DependencyOnPlugin
import com.intellij.externalDependencies.ExternalDependenciesManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify

class BuildExecutionDeploymentSettingsApplierTest : IdeaSettingsTestFixture() {

    private lateinit var settingsApplier: SettingsApplier<BuildExecutionDeploymentSettings>
    private lateinit var externalDependenciesManager: ExternalDependenciesManager
    @Mock
    private lateinit var buildToolsSettingsApplier: SettingsApplier<BuildToolsSettings>
    @Mock
    private lateinit var compilerSettingsApplier: SettingsApplier<CompilerSettings>

    @Before
    public override fun setUp() {
        externalDependenciesManager = ExternalDependenciesManager.getInstance(project)
        settingsApplier = BuildExecutionDeploymentSettingsApplier(externalDependenciesManager, buildToolsSettingsApplier, compilerSettingsApplier)
    }

    @After
    public override fun tearDown() {
        externalDependenciesManager.allDependencies = emptyList()
    }

    @Test
    fun requiredPluginsApplied() {
        settingsApplier.apply(ImmutableBuildExecutionDeploymentSettings.builder()
                .addRequiredPlugins(ImmutableRequiredPlugin.builder()
                        .plugin("CheckStyle-IDEA")
                        .build())
                .build())
        assertThat(externalDependenciesManager.allDependencies)
                .containsOnly(DependencyOnPlugin("CheckStyle-IDEA", null, null))
    }

    @Test
    fun requiredPluginsWithVersionApplied() {
        settingsApplier.apply(ImmutableBuildExecutionDeploymentSettings.builder()
                .addRequiredPlugins(ImmutableRequiredPlugin.builder()
                        .plugin("CheckStyle-IDEA")
                        .minimumVersion("5.23.0")
                        .maximumVersion("5.24.1")
                        .build())
                .build())
        assertThat(externalDependenciesManager.allDependencies)
                .containsOnly(DependencyOnPlugin("CheckStyle-IDEA", "5.23.0", "5.24.1"))
    }

    @Test
    fun childSettingsApplied() {
        settingsApplier.apply(ImmutableBuildExecutionDeploymentSettings.builder()
                .compiler(ImmutableCompilerSettings.builder().build())
                .buildTools(ImmutableBuildToolsSettings.builder().build())
                .build())
        verify(buildToolsSettingsApplier).apply(ImmutableBuildToolsSettings.builder().build())
        verify(compilerSettingsApplier).apply(ImmutableCompilerSettings.builder().build())
    }
}