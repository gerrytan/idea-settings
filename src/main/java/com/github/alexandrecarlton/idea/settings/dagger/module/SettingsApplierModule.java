package com.github.alexandrecarlton.idea.settings.dagger.module;

import com.github.alexandrecarlton.idea.settings.applier.api.SettingsApplier;
import com.github.alexandrecarlton.idea.settings.applier.impl.project_settings.modules.ModuleSettingsApplier;
import com.github.alexandrecarlton.idea.settings.layout.project_settings.modules.ModuleSettings;

import dagger.Binds;
import dagger.Module;

@Module
public interface SettingsApplierModule {

  @Binds
  SettingsApplier<ModuleSettings> provideModuleSettingsApplier(ModuleSettingsApplier applier);

}
