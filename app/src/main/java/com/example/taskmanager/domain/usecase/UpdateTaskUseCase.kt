package com.example.taskmanager.domain.usecase

import com.example.taskmanager.domain.repository.TaskRepository
import com.example.taskmanager.domain.model.Task

class UpdateTaskUseCase(private val taskRepository: TaskRepository) {
    suspend fun execute(task: Task){
        taskRepository.updateTask(task)
    }
}