package com.example.taskmanager.ui.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmanager.data.TaskDatabaseProvider
import com.example.taskmanager.data.TaskRepositoryImpl
import com.example.taskmanager.domain.model.Task
import com.example.taskmanager.ui.components.BottomComponent
import com.example.taskmanager.ui.components.ProfileHeaderComponent
import com.example.taskmanager.ui.components.TaskComponent
import com.example.taskmanager.ui.components.WelcomeMessageComponent
import com.example.taskmanager.ui.theme.TaskManagerTheme
import com.example.taskmanager.ui.viewmodel.TaskViewModel

@Composable
fun TaskScreen(navController: NavController){

    val context = LocalContext.current.applicationContext
    val database = TaskDatabaseProvider.provide(context)
    val repository = TaskRepositoryImpl(dao = database.taskDao)

    val viewModel = TaskViewModel(repository = repository)

    val taskList by viewModel.taskList.collectAsState()

    Scaffold(
        bottomBar =
        {
            BottomComponent(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate("addEditTaskScreen/0")},
                containerColor = Color.Black,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 16.dp,
                bottom = 16.dp
            )
        ) {
            item {
                ProfileHeaderComponent()
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))

                WelcomeMessageComponent(TaskViewModel(repository = repository))

                Spacer(modifier = Modifier.height(30.dp))
            }

            items(taskList) { task ->
                Box() {
                    TaskComponent(
                        task = task,
                        onEdit = {navController.navigate("addEditTaskScreen/${task.id}")},
                        onDelete = {viewModel.deleteTask(task)}
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
private fun TaskScreenPreview() {
    TaskManagerTheme {
        TaskScreen(navController = NavController(LocalContext.current))
    }
}