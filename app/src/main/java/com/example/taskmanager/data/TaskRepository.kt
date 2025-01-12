package com.example.taskmanager.data

import com.example.taskmanager.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun insert(title: String, description: String?, id: Long? = null, startTime: String, endTime: String)

    suspend fun updateCompleted(id: Long, isCompleted: Boolean, startTime: String, endTime: String)

    suspend fun delete(id: Long)

    fun getAll(): Flow<List<Task>>

    suspend fun getBy(id: Long): Task?
}