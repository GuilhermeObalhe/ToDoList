package com.example.taskmanager.domain.usecase

import com.example.taskmanager.domain.repository.TaskRepository
import com.example.taskmanager.domain.model.Task

class GetTaskByIdUseCase(private val taskRepository: TaskRepository) {
    suspend fun execute(id: Int) : Task?{
        return taskRepository.getTaskById(id)
    }
}