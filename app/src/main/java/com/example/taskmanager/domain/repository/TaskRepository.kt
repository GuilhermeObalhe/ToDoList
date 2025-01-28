package com.example.taskmanager.domain.repository

import com.example.taskmanager.domain.model.Task

interface TaskRepository {
    suspend fun getTaskById(id: Int): Task?;
    suspend fun getTasks() : List<Task>;
    suspend fun addTask(task: Task);
    suspend fun updateTask(task: Task);
    suspend fun deleteTask(task: Task);

}