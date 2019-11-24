package com.github.alexandrecarlton.idea.settings.applier.impl.configurations.remote

import com.github.alexandrecarlton.idea.settings.applier.api.SettingsApplier
import com.github.alexandrecarlton.idea.settings.fixtures.IdeaSettingsTestFixture
import com.github.alexandrecarlton.idea.settings.layout.configurations.remote.ImmutableRemoteConfigurationSettings
import com.github.alexandrecarlton.idea.settings.layout.configurations.remote.ImmutableRemoteSettings
import com.github.alexandrecarlton.idea.settings.layout.configurations.remote.RemoteSettings
import com.intellij.execution.RunManager
import com.intellij.execution.remote.RemoteConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class RemoteSettingsApplierTest : IdeaSettingsTestFixture() {

    private lateinit var settingsApplier: SettingsApplier<RemoteSettings>
    private lateinit var runManager: RunManager

    @Before
    public override fun setUp() {
        runManager = RunManager.getInstance(project)
        settingsApplier = RemoteSettingsApplier(runManager, project)
    }

    @Test
    fun defaultRemoteApplied() {
        settingsApplier.apply(ImmutableRemoteSettings.builder()
                .name("Default Remote")
                .build())
        val runnerAndConfigurationSettings = runManager.findConfigurationByName("Default Remote")
        assertThat(runnerAndConfigurationSettings).isNotNull
    }

    @Test
    fun customRemoteApplied() {
        settingsApplier.apply(ImmutableRemoteSettings.builder()
                .name("Configured Remote")
                .configuration(ImmutableRemoteConfigurationSettings.builder()
                        .host("8.8.8.8")
                        .port(5000)
                        .build())
                .build())
        val runnerAndConfigurationSettings = runManager.findConfigurationByName("Configured Remote")
        assertThat(runnerAndConfigurationSettings).isNotNull
        assertThat(runnerAndConfigurationSettings!!.configuration).isInstanceOf(RemoteConfiguration::class.java)
        val remoteConfiguration = runnerAndConfigurationSettings.configuration as RemoteConfiguration
        assertThat(remoteConfiguration.HOST).isEqualTo("8.8.8.8")
        assertThat(remoteConfiguration.PORT).isEqualTo("5000")
    }
}