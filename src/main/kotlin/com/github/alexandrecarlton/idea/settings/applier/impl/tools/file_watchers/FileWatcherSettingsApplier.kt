package com.github.alexandrecarlton.idea.settings.applier.impl.tools.file_watchers

import com.github.alexandrecarlton.idea.settings.applier.api.SettingsApplier
import com.github.alexandrecarlton.idea.settings.applier.impl.common.FileTypeMapper
import com.github.alexandrecarlton.idea.settings.layout.tools.file_watchers.FileWatcherAdvancedOptionsSettings
import com.github.alexandrecarlton.idea.settings.layout.tools.file_watchers.FileWatcherSettings
import com.intellij.plugins.watcher.model.ProjectTasksOptions
import com.intellij.plugins.watcher.model.TaskOptions
import javax.inject.Inject

class FileWatcherSettingsApplier @Inject
constructor(private val projectTasksOptions: ProjectTasksOptions, private val fileTypeMapper: FileTypeMapper) : SettingsApplier<FileWatcherSettings> {

    override fun apply(settings: FileWatcherSettings) {
        val taskOptions = TaskOptions()
        taskOptions.name = settings.name
        settings.filesToWatch
            .fileType
            ?.let { fileTypeMapper.mapFileType(it) }
            ?.defaultExtension
            ?.let { taskOptions.fileExtension = it }
        settings.filesToWatch.scope?.let { taskOptions.scopeName = it }

        taskOptions.program = settings.toolToRunOnChanges.program
        taskOptions.arguments = settings.toolToRunOnChanges.arguments
        taskOptions.output = settings.toolToRunOnChanges.outputPathsToRefresh

        settings.advancedOptions?.autoSaveEditedFilesToTriggerTheWatcher?.let { taskOptions.isImmediateSync = it }
        settings.advancedOptions?.triggerTheWatcherRegardlessOfSyntaxErrors?.let { taskOptions.isCheckSyntaxErrors = it }
        settings.advancedOptions?.triggerTheWatcherOnExternalChanges?.let { taskOptions.isRunOnExternalChanges = it }

        projectTasksOptions.addTask(taskOptions, true)
    }
}