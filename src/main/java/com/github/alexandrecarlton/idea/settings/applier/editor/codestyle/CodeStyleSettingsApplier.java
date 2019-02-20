package com.github.alexandrecarlton.idea.settings.applier.editor.codestyle;

import com.github.alexandrecarlton.idea.settings.applier_api.SettingsApplier;
import com.github.alexandrecarlton.idea.settings.layout.editor.codestyle.CodeStyleSettings;
import com.github.alexandrecarlton.idea.settings.layout.editor.codestyle.java.JavaCodeStyleSettings;
import com.intellij.openapi.project.Project;

public class CodeStyleSettingsApplier implements SettingsApplier<CodeStyleSettings> {

  private final SettingsApplier<JavaCodeStyleSettings> javaCodeStyleSettingsApplier;

  public CodeStyleSettingsApplier(SettingsApplier<JavaCodeStyleSettings> javaCodeStyleSettingsApplier) {
    this.javaCodeStyleSettingsApplier = javaCodeStyleSettingsApplier;
  }

  public void apply(Project project, CodeStyleSettings configuration) {
    configuration.java().ifPresent(java -> javaCodeStyleSettingsApplier.apply(project, java));
  }
}
