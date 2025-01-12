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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmanager.ui.components.AddTaskComponent
import com.example.taskmanager.ui.components.BottomComponent
import com.example.taskmanager.ui.theme.TaskManagerTheme
import com.example.taskmanager.ui.viewmodel.AddEditTaskViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEditTaskScreen(
    navController: NavController,
    viewModel: AddEditTaskViewModel,
    onTaskSaved: () -> Unit,
    taskId: String? = null,
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var start by remember { mutableStateOf("") }
    var end by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {BottomComponent()},
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate("taskScreen")},
                containerColor = Color.Black,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Check, contentDescription = "Check")
            }
        }
    ){
        Box(
            modifier = Modifier.fillMaxWidth().height(500.dp),
            contentAlignment = Alignment.Center
        )
        {
            AddTaskComponent(
                title = title,
                onTitleChange = {title = it},
                description = description,
                onDescriptionChange = {description = it},
                start = start,
                onStartChange = {start = it},
                end = end,
                onEndChange = {end = it}
            )
        }
    }
}

@Preview
@Composable
private fun AddEditTaskScreenPreview() {
    TaskManagerTheme {
        AddEditTaskScreen(
            navController = NavController(LocalContext.current),
            viewModel = AddEditTaskViewModel(),
            onTaskSaved = {},
            taskId = 1.toString()
        )
    }
}