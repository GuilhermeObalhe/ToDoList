package com.example.taskmanager.data

import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun insertTask(task: TaskEntity)

    suspend fun updateTask(task: TaskEntity)

    suspend fun deleteTask(id: Int)

    fun getAllTasks(): Flow<List<TaskEntity>>

    fun getTaskById(id: Int): Flow<TaskEntity>

    fun getTaskCount(): Flow<Int>
}