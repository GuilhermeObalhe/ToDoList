package com.example.taskmanager.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmanager.data.TaskDatabaseProvider
import com.example.taskmanager.data.TaskRepositoryImpl
import com.example.taskmanager.ui.components.AddTaskComponent
import com.example.taskmanager.ui.components.BottomComponent
import com.example.taskmanager.ui.theme.TaskManagerTheme
import com.example.taskmanager.ui.viewmodel.AddEditTaskViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEditTaskScreen(navController: NavController, taskId: Int?) {

    val context = LocalContext.current.applicationContext
    val database = TaskDatabaseProvider.provide(context)
    val repository = TaskRepositoryImpl(dao = database.taskDao)

    val viewModel = AddEditTaskViewModel(repository = repository)

    val title by viewModel.title.collectAsState()
    val description by viewModel.description.collectAsState()
    val startTime by viewModel.startTime.collectAsState()
    val endTime by viewModel.endTime.collectAsState()

    LaunchedEffect(taskId) {
        if (taskId != 0 && taskId != null) {
            viewModel.loadTask(taskId)
        } else {

            viewModel.resetFields()
        }
    }

    Scaffold(
        bottomBar = {BottomComponent(navController = navController)},
        floatingActionButton = {
            FloatingActionButton(
                onClick = {viewModel.saveTask(taskId = taskId)
                          navController.navigate("taskScreen")},
                containerColor = Color.Black,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Check, contentDescription = "Check")
            }
        }
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            contentAlignment = Alignment.Center
        )
        {
            AddTaskComponent(
                title = title,
                onTitleChange = {viewModel.updateTitle(it)},
                description = description,
                onDescriptionChange = {viewModel.updateDescription(it)},
                startTime = startTime,
                onStartChange = {viewModel.updateStartTime(it)},
                endTime = endTime,
                onEndChange = {viewModel.updateEndTime(it)}
            )
        }
    }
}

@Preview
@Composable
private fun AddEditTaskScreenPreview() {
    TaskManagerTheme {
        AddEditTaskScreen(navController = NavController(LocalContext.current), 0)
    }
}