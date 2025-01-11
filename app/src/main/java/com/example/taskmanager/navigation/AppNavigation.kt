package com.example.taskmanager.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmanager.ui.screens.AddEditTaskScreen
import com.example.taskmanager.ui.screens.TaskScreen
import com.example.taskmanager.ui.viewmodel.AddEditTaskViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "taskScreen") {
        composable("taskScreen") {
            TaskScreen(navController = navController, {})
        }
        composable("addEditTaskScreen/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")
            AddEditTaskScreen(navController = navController, viewModel = AddEditTaskViewModel(), onTaskSaved = {}, taskId = taskId)
        }
    }
}