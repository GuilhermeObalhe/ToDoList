package com.example.taskmanager.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.data.TaskEntity
import com.example.taskmanager.data.TaskRepository
import com.example.taskmanager.domain.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _taskList = MutableStateFlow<List<Task>>(emptyList())
    val taskList: StateFlow<List<Task>> = _taskList.asStateFlow()

    private val _taskCount = MutableStateFlow(0)
    val taskCount: StateFlow<Int> = _taskCount.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getTaskCount().collectLatest { count ->
                _taskCount.value = count
            }
        }
    }

    init {
        viewModelScope.launch {
            repository.getAllTasks().collectLatest { taskList ->
                _taskList.value = taskList.map { it.toTask() }
            }
        }
    }

    private fun TaskEntity.toTask(): Task {
        return Task(
            id = id,
            title = title,
            description = description,
            startTime = startTime,
            endTime = endTime
        )
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task.id)
        }
    }
}