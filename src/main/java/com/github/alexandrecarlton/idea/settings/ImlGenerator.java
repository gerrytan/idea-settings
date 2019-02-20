package com.github.alexandrecarlton.idea.settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.intellij.ide.impl.ProjectUtil;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.github.alexandrecarlton.idea.settings.applier.editor.codestyle.CodeStyleConfigurer;
import com.github.alexandrecarlton.idea.settings.applier.editor.EditorConfigurer;
import com.github.alexandrecarlton.idea.settings.applier.IdeaConfigurer;
import com.github.alexandrecarlton.idea.settings.applier.editor.codestyle.java.JavaImportsConfigurer;
import com.github.alexandrecarlton.idea.settings.applier.editor.codestyle.java.JavaCodeStyleConfigurer;
import com.github.alexandrecarlton.idea.settings.layout.IdeaSettings;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class ImlGenerator {
  private final ObjectMapper mapper = new YAMLMapper()
      // Snake case didn't work for some reason.
//      .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
      .registerModule(new Jdk8Module())
      .registerModule(new GuavaModule());

  /**
   * Generates *.iml files for the given path.
   * @param path the location of the project.
   */
  public void generate(Path path) {

    IdeaSettings config = loadConfig(path);

    Project project = ProjectUtil.openOrImport(path.toString(), null, false);
    project.save();

    // Yeah, it's probably time to add DI wiring.
    IdeaConfigurer configurer = new IdeaConfigurer(
        new EditorConfigurer(
            new CodeStyleConfigurer(
                new JavaCodeStyleConfigurer(
                    new JavaImportsConfigurer()))));
    configurer.configure(project, config);

    ModuleManager moduleManager = ModuleManager.getInstance(project);
    List<Module> modules = Arrays.asList(moduleManager.getModules());

    ModuleRootManager moduleRootManager = ModuleRootManager.getInstance(modules.get(0));
    ModifiableRootModel modifiableRootModel = moduleRootManager.getModifiableModel();
    List<ContentEntry> contentEntries = Arrays.asList(modifiableRootModel.getContentEntries());
    // We can add an excluded folder to a content entry via "file://" + full path to url

    System.out.println("Committing...");
    modifiableRootModel.commit();

    System.out.println("Saving...");
    project.save();
  }

  private IdeaSettings loadConfig(Path project) {
    Path configFile = project.resolve(".IDEA-config.yml");
    if (!Files.exists(configFile)) {
      System.out.println(configFile + " does not exist. Exiting.");
      System.exit(1);
    }
    try (InputStream inputStream = Files.newInputStream(configFile)) {
      return mapper.readValue(inputStream, IdeaSettings.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}