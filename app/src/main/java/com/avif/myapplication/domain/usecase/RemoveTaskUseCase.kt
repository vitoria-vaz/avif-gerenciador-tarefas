package com.avif.myapplication.domain.usecase

import com.avif.myapplication.data.repository.TaskRepository

class RemoveTaskUseCase(private val taskRepository: TaskRepository) {

    fun execute(taskId: Int): Result<Unit> {
        val tasks = taskRepository.getTasks()
        if (tasks.none { it.id == taskId }) {
            return Result.failure(IllegalArgumentException("Tarefa não encontrada."))
        }

        taskRepository.removeTask(taskId)
        return Result.success(Unit)
    }
}
