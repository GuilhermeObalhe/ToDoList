package com.example.taskmanager.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.data.TaskEntity
import com.example.taskmanager.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel(){

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title.asStateFlow()

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description.asStateFlow()

    private val _startTime = MutableStateFlow("")
    val startTime: StateFlow<String> = _startTime.asStateFlow()

    private val _endTime = MutableStateFlow("")
    val endTime: StateFlow<String> = _endTime.asStateFlow()

    fun updateTitle(newTitle: String){
        _title.value = newTitle
    }

    fun updateDescription(newDescription: String){
        _description.value = newDescription
    }

    fun updateStartTime(newStartTime: String){
        _startTime.value = newStartTime
    }

    fun updateEndTime(newEndTime: String){
        _endTime.value = newEndTime
    }

    fun saveTask(){
        val task = TaskEntity(
            title = _title.value,
            description = _description.value,
            startTime = _startTime.value,
            endTime = _endTime.value)

        viewModelScope.launch {
            repository.insertTask(task)
        }

    }

}