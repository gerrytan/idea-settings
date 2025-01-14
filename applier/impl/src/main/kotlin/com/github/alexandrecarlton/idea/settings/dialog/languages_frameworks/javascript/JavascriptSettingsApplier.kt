package com.github.alexandrecarlton.idea.settings.dialog.languages_frameworks.javascript

import com.github.alexandrecarlton.idea.settings.dialog.SettingsApplier
import com.github.alexandrecarlton.idea.settings.dialog.languages_frameworks.javascript.JavascriptLanguageVersion.ECMASCRIPT_5_1
import com.github.alexandrecarlton.idea.settings.dialog.languages_frameworks.javascript.JavascriptLanguageVersion.ECMASCRIPT_6
import com.github.alexandrecarlton.idea.settings.dialog.languages_frameworks.javascript.JavascriptLanguageVersion.FLOW
import com.github.alexandrecarlton.idea.settings.dialog.languages_frameworks.javascript.JavascriptLanguageVersion.JAVASCRIPT_1_8_5
import com.github.alexandrecarlton.idea.settings.dialog.languages_frameworks.javascript.JavascriptLanguageVersion.NASHORN_JS
import com.github.alexandrecarlton.idea.settings.dialog.languages_frameworks.javascript.JavascriptLanguageVersion.REACT_JSX
import com.github.alexandrecarlton.idea.settings.dialog.languages_frameworks.javascript.code_quality_tools.JavascriptCodeQualityToolsSettings
import com.intellij.lang.javascript.dialects.JSLanguageLevel
import com.intellij.lang.javascript.settings.JSRootConfiguration
import javax.inject.Inject

class JavascriptSettingsApplier @Inject
constructor(
    private val jsRootConfiguration: JSRootConfiguration,
    private val javascriptCodeQualityToolsSettingsApplier: SettingsApplier<JavascriptCodeQualityToolsSettings>
) : SettingsApplier<JavascriptSettings> {

    override fun apply(settings: JavascriptSettings) {
        settings.javascriptLanguageVersion?.let { jsRootConfiguration.storeLanguageLevelAndUpdateCaches(toJSLanguageLevel(it)) }
        settings.codeQualityTools?.let(javascriptCodeQualityToolsSettingsApplier::apply)
    }

    private fun toJSLanguageLevel(version: JavascriptLanguageVersion) =
        when (version) {
            FLOW -> JSLanguageLevel.FLOW
            ECMASCRIPT_5_1 -> JSLanguageLevel.ES5
            ECMASCRIPT_6 -> JSLanguageLevel.ES6
            JAVASCRIPT_1_8_5 -> JSLanguageLevel.JS_1_8_5
            NASHORN_JS -> JSLanguageLevel.NASHORN
            REACT_JSX -> JSLanguageLevel.JSX
        }
}
