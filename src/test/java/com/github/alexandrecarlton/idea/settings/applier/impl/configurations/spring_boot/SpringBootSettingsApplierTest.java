package com.github.alexandrecarlton.idea.settings.applier.impl.configurations.spring_boot;

import com.github.alexandrecarlton.idea.settings.applier.api.SettingsApplier;
import com.github.alexandrecarlton.idea.settings.fixtures.IdeaSettingsTestFixture;
import com.github.alexandrecarlton.idea.settings.layout.configurations.spring_boot.*;
import com.intellij.execution.RunManager;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.spring.boot.run.SpringBootAdditionalParameter;
import com.intellij.spring.boot.run.SpringBootApplicationRunConfiguration;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringBootSettingsApplierTest extends IdeaSettingsTestFixture {

  private SettingsApplier<SpringBootSettings> settingsApplier;
  private RunManager runManager;

  @Before
  public void setUp() throws Exception {
    super.setUp();
    runManager = RunManager.getInstance(project);
    settingsApplier = new SpringBootSettingsApplier(project, runManager);
  }

  @Test
  public void basicSpringConfiguration() {
    settingsApplier.apply(ImmutableSpringBootSettings.builder()
      .name("Spring Boot")
      .configuration(ImmutableSpringBootConfigurationSettings.builder()
        .mainClass("com.Application")
        .build())
      .build());

    RunnerAndConfigurationSettings runnerAndConfigurationSettings = runManager.findConfigurationByName("Spring Boot");
    assertThat(runnerAndConfigurationSettings).isNotNull();
    assertThat(runnerAndConfigurationSettings.isShared()).isTrue();

    SpringBootApplicationRunConfiguration configuration = (SpringBootApplicationRunConfiguration) runnerAndConfigurationSettings.getConfiguration();
    assertThat(configuration.getSpringBootMainClass()).isEqualTo("com.Application");
  }

  @Test
  public void dependenciesWithProvidedScopeApplied() {
    settingsApplier.apply(ImmutableSpringBootSettings.builder()
      .name("Spring Boot")
      .configuration(ImmutableSpringBootConfigurationSettings.builder()
        .mainClass("com.Application")
        .environment(ImmutableSpringBootConfigurationEnvironmentSettings.builder()
          .includeDependenciesWithProvidedScope(false)
          .build())
        .build())
      .build());

    RunnerAndConfigurationSettings runnerAndConfigurationSettings = runManager.findConfigurationByName("Spring Boot");
    SpringBootApplicationRunConfiguration configuration = (SpringBootApplicationRunConfiguration) runnerAndConfigurationSettings.getConfiguration();
    assertThat(configuration).isNotNull();
    assertThat(configuration.isIncludeProvidedScope()).isFalse();
  }

  // TODO: test useClassPathOfModule - will likely require module creation.

  @Test
  public void vmOptionsApplied() {
    settingsApplier.apply(ImmutableSpringBootSettings.builder()
      .name("Spring Boot")
      .configuration(ImmutableSpringBootConfigurationSettings.builder()
        .mainClass("com.Application")
        .environment(ImmutableSpringBootConfigurationEnvironmentSettings.builder()
          .vmOptions("-Xmx123m")
          .build())
        .build())
      .build());

    RunnerAndConfigurationSettings runnerAndConfigurationSettings = runManager.findConfigurationByName("Spring Boot");
    SpringBootApplicationRunConfiguration configuration = (SpringBootApplicationRunConfiguration) runnerAndConfigurationSettings.getConfiguration();
    assertThat(configuration).isNotNull();
    assertThat(configuration.getVMParameters()).isEqualTo("-Xmx123m");
  }

  @Test
  public void overrideParametersApplied() {
    settingsApplier.apply(ImmutableSpringBootSettings.builder()
      .name("Spring Boot")
      .configuration(ImmutableSpringBootConfigurationSettings.builder()
        .mainClass("com.Application")
        .springBoot(ImmutableSpringBootConfigurationSpringBootSettings.builder()
          .addOverrideParameters(ImmutableOverrideParameter.builder()
            .key("Key")
            .value("Value")
            .build())
          .build())
        .build())
      .build());

    RunnerAndConfigurationSettings runnerAndConfigurationSettings = runManager.findConfigurationByName("Spring Boot");
    SpringBootApplicationRunConfiguration configuration = (SpringBootApplicationRunConfiguration) runnerAndConfigurationSettings.getConfiguration();
    assertThat(configuration).isNotNull();
    assertThat(configuration.getAdditionalParameters()).containsExactly(new SpringBootAdditionalParameter(true, "Key", "Value"));
  }

}