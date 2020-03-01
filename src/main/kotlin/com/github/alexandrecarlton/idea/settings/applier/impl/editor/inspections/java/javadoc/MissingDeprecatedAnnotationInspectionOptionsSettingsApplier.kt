package com.github.alexandrecarlton.idea.settings.applier.impl.editor.inspections.java.javadoc

import com.github.alexandrecarlton.idea.settings.applier.api.SettingsApplier
import com.github.alexandrecarlton.idea.settings.layout.editor.inspections.java.javadoc.MissingDeprecatedAnnotationInspectionOptionsSettings
import com.siyeh.ig.javadoc.MissingDeprecatedAnnotationInspection
import javax.inject.Inject

class MissingDeprecatedAnnotationInspectionOptionsSettingsApplier @Inject
constructor(
    private val missingDeprecatedAnnotationInspection: MissingDeprecatedAnnotationInspection
) : SettingsApplier<MissingDeprecatedAnnotationInspectionOptionsSettings> {
    override fun apply(settings: MissingDeprecatedAnnotationInspectionOptionsSettings) {
        settings.warnOnMissingDeprecatedJavadocTagExplanation?.let {
            missingDeprecatedAnnotationInspection.warnOnMissingJavadoc = it
        }
    }
}