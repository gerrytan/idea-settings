package com.github.alexandrecarlton.idea.settings.starter;

import com.intellij.compiler.CompilerConfiguration;
import com.intellij.externalDependencies.ExternalDependenciesManager;
import com.intellij.ide.impl.ProjectUtil;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import dagger.Module;
import dagger.Provides;
import org.infernus.idea.checkstyle.config.PluginConfigurationManager;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class ProjectModule {

  @Provides
  @Singleton
  static Project provideProject(@Named("project") String path) {
    return ProjectUtil.openOrImport(path, null, false);
  }

  @Provides
  static CompilerConfiguration provideCompilerConfiguration(Project project) {
    return CompilerConfiguration.getInstance(project);
  }

  @Provides
  static ExternalDependenciesManager provideExternalDependenciesManager(Project project) {
    return ExternalDependenciesManager.getInstance(project);
  }

  @Provides
  static ModuleManager provideModuleManager(Project project) {
    return ModuleManager.getInstance(project);
  }

  @Provides
  static PluginConfigurationManager providePluginConfigurationManager(Project project) {
    return PluginConfigurationManager.getInstance(project);
  }

}
