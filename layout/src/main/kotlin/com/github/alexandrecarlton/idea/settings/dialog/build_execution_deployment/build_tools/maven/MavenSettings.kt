package com.github.alexandrecarlton.idea.settings.dialog.build_execution_deployment.build_tools.maven

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.File

data class MavenSettings(

    @JsonProperty("Importing")
    val importing: MavenImportingSettings? = null,

    /**
     * To be properly set, this file must exist during generation.
     */
    @JsonProperty("Maven home directory")
    val mavenHomeDirectory: File? = null,

    @JsonProperty("Thread count")
    val threadCount: String? = null
)
