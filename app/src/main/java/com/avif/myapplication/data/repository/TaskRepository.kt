package com.avif.myapplication.data.repository

import android.content.Context
import com.avif.myapplication.data.model.TaskDTO
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

class TaskRepository(private val context: Context) {

    private val fileName = "tasks.json"

    private fun getFile(): File {
        return File(context.filesDir, fileName)
    }

    fun getTasks(): MutableList<TaskDTO> {
        val file = getFile()
        if (!file.exists()) return mutableListOf()
        val json = file.readText()
        return Json.decodeFromString(json)
    }

    fun saveTasks(tasks: List<TaskDTO>) {
        val file = getFile()
        val json = Json.encodeToString(tasks)
        file.writeText(json)
    }

    fun addTask(task: TaskDTO) {
        val tasks = getTasks()
        tasks.add(task)
        saveTasks(tasks)
    }

    fun editTask(updatedTask: TaskDTO) {
        val tasks = getTasks().map { if (it.id == updatedTask.id) updatedTask else it }
        saveTasks(tasks)
    }

    fun removeTask(taskId: Int) {
        val tasks = getTasks().filter { it.id != taskId }
        saveTasks(tasks)
    }

    fun toggleComplete(taskId: Int) {
        val tasks = getTasks().map {
            if (it.id == taskId) it.copy(isCompleted = !it.isCompleted) else it
        }
        saveTasks(tasks)
    }
}
