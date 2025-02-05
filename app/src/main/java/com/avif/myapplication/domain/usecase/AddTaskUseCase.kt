package com.avif.myapplication.domain.usecase

import com.avif.myapplication.data.model.TaskDTO
import com.avif.myapplication.data.repository.TaskRepository

class AddTaskUseCase(private val taskRepository: TaskRepository) {

    fun execute(title: String, body: String?, startTime: String, endTime: String): Result<Unit> {
        if (title.isBlank()) {
            return Result.failure(IllegalArgumentException("O título da tarefa não pode estar vazio."))
        }

        if (startTime > endTime) {
            return Result.failure(IllegalArgumentException("O horário de início não pode ser maior que o horário de término."))
        }

        // Gerar um ID único
        val existingTasks = taskRepository.getTasks()
        val newId = (existingTasks.maxOfOrNull { it.id } ?: 0) + 1

        val newTask = TaskDTO(
            id = newId,
            title = title,
            body = body,
            startTime = startTime,
            endTime = endTime,
            isCompleted = false
        )

        taskRepository.addTask(newTask)
        return Result.success(Unit)
    }
}
