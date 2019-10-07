package com.github.alexandrecarlton.idea.settings.layout.languages_frameworks;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.alexandrecarlton.idea.settings.layout.languages_frameworks.javascript.JavascriptSettings;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@JsonDeserialize(as = ImmutableLanguagesFrameworksSettings.class)
public interface LanguagesFrameworksSettings {
  Optional<JavascriptSettings> javascript();
}
