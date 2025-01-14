package com.github.alexandrecarlton.idea.settings.dialog.build_execution_deployment.build_tools.maven

import com.fasterxml.jackson.annotation.JsonProperty

data class MavenImportingSettings(

    @JsonProperty("VM options for importer")
    val vmOptionsForImporter: String? = null
)
