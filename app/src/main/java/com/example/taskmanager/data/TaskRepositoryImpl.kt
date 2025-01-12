package com.example.taskmanager.data

import com.example.taskmanager.domain.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(private val dao: TaskDao) : TaskRepository{
    override suspend fun insert(title: String, description: String?, id: Long?, startTime: String, endTime: String) {
        val entity = id?.let{
            dao.getBy(it)?.copy(
                title = title,
                description = description
            )
        }?: TaskEntity(
            title = title,
            description = description,
            isCompleted = false,
            startTime = startTime,
            endTime = endTime
        )

        dao.insert(entity)
    }

    override suspend fun updateCompleted(
        id: Long,
        isCompleted: Boolean,
        startTime: String,
        endTime: String
    ) {
        val existentEntity = dao.getBy(id) ?: return
        val updatedEntity = existentEntity.copy(isCompleted = isCompleted)
        dao.insert(updatedEntity)
    }

    override suspend fun delete(id: Long) {
        val existentEntity = dao.getBy(id) ?: return
        dao.delete(existentEntity)
    }

    override fun getAll(): Flow<List<Task>> {
        return dao.getAll().map {
            it.map { entity ->
                Task(
                    id = entity.id,
                    title = entity.title,
                    description = entity.description,
                    isCompleted = entity.isCompleted,
                    startTime = entity.startTime,
                    endTime = entity.endTime
                )
            }
        }
    }

    override suspend fun getBy(id: Long): Task? {
        return dao.getBy(id)?.let { entity ->
            Task(
                id = entity.id,
                title = entity.title,
                description = entity.description,
                isCompleted = entity.isCompleted,
                startTime = entity.startTime,
                endTime = entity.endTime
            )
        }
    }
}