package com.github.alexandrecarlton.idea.settings.layout;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.alexandrecarlton.idea.settings.layout.build_execution_deployment.BuildExecutionDeploymentSettings;
import com.github.alexandrecarlton.idea.settings.layout.configurations.ConfigurationsSettings;
import com.github.alexandrecarlton.idea.settings.layout.editor.EditorSettings;
import com.github.alexandrecarlton.idea.settings.layout.languages_frameworks.LanguagesFrameworksSettings;
import com.github.alexandrecarlton.idea.settings.layout.other_settings.OtherSettings;
import com.github.alexandrecarlton.idea.settings.layout.project_settings.ProjectSettingsSettings;
import com.github.alexandrecarlton.idea.settings.layout.tools.ToolsSettings;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@JsonDeserialize(as = ImmutableIdeaSettings.class)
public interface IdeaSettings {

  Optional<EditorSettings> editor();

  Optional<BuildExecutionDeploymentSettings> buildExecutionDeployment();

  Optional<LanguagesFrameworksSettings> languagesFrameworks();

  Optional<ToolsSettings> tools();

  Optional<OtherSettings> otherSettings();

  Optional<ProjectSettingsSettings> projectSettings();

  Optional<ConfigurationsSettings> configurations();
}

