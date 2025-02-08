package com.example.taskmanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.data.TaskEntity
import com.example.taskmanager.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _taskList = MutableStateFlow<List<TaskEntity>>(emptyList())
    val taskList: StateFlow<List<TaskEntity>> = _taskList.asStateFlow()

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
                _taskList.value = taskList.map {it}
            }
        }
    }

    fun completeTask(taskId: Int) {
        viewModelScope.launch {
            repository.completeTask(taskId)
        }
    }

    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            repository.deleteTask(taskId)
        }
    }
}