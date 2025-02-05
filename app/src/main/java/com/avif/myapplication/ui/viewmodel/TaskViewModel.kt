package com.avif.myapplication.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avif.myapplication.data.model.TaskDTO
import com.avif.myapplication.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel(context: Context) : ViewModel() {

    private val repository = TaskRepository(context)
    private val _tasks = MutableStateFlow<List<TaskDTO>>(emptyList())
    val tasks = _tasks.asStateFlow()

    init {
        loadTasks()
    }

    private fun loadTasks() {
        _tasks.value = repository.getTasks()
    }

    fun addTask(task: TaskDTO) {
        viewModelScope.launch {
            repository.addTask(task)
            loadTasks()
        }
    }

    fun addTask(title: String, body: String?, startTime: String, endTime: String) {
        viewModelScope.launch {
            val newTask = TaskDTO(
                id = generateTaskId(),
                title = title,
                body = body,
                startTime = startTime,
                endTime = endTime
            )
            repository.addTask(newTask)
            loadTasks()
        }
    }

    fun editTask(task: TaskDTO) {
        viewModelScope.launch {
            repository.editTask(task)
            loadTasks()
        }
    }

    fun removeTask(taskId: Int) {
        viewModelScope.launch {
            repository.removeTask(taskId)
            loadTasks()
        }
    }

    fun toggleComplete(taskId: Int) {
        viewModelScope.launch {
            repository.toggleComplete(taskId)
            loadTasks()
        }
    }

    private fun generateTaskId(): Int {
        return (tasks.value.maxOfOrNull { it.id } ?: 0) + 1
    }
}
