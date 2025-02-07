package com.example.taskmanager.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val dao: TaskDao) : TaskRepository {

    override suspend fun insertTask(task: TaskEntity) {
        dao.insertTask(task)
    }

    override suspend fun updateTask(task: TaskEntity) {
        dao.updateTask(task)
    }

    override suspend fun deleteTask(id: Int) {
        dao.deleteTask(id)
    }

    override suspend fun completeTask(id: Int) {
        dao.completeTask(id)
    }

    override fun getAllTasks(): Flow<List<TaskEntity>> {
        return dao.getAllTasks()
    }

    override fun getTaskById(id: Int): Flow<TaskEntity> {
        return dao.getTaskById(id)
    }

    override fun getTaskCount(): Flow<Int> {
        return dao.getTaskCount()
    }
}