package com.github.alexandrecarlton.idea.settings.applier.editor.codestyle.java;

import com.github.alexandrecarlton.idea.settings.applier_api.SettingsApplier;
import com.github.alexandrecarlton.idea.settings.layout.editor.codestyle.java.JavaCodeStyleSettings;
import com.github.alexandrecarlton.idea.settings.layout.editor.codestyle.java.JavaImportsSettings;
import com.intellij.openapi.project.Project;

public class JavaCodeStyleSettingsApplier implements SettingsApplier<JavaCodeStyleSettings> {

  private final SettingsApplier<JavaImportsSettings> javaImportsSettingsApplier;

  public JavaCodeStyleSettingsApplier(SettingsApplier<JavaImportsSettings> javaImportsSettingsApplier) {
    this.javaImportsSettingsApplier = javaImportsSettingsApplier;
  }

  public void apply(Project project, JavaCodeStyleSettings java) {
    java.imports().ifPresent(imports -> javaImportsSettingsApplier.apply(project, imports));
  }
}
