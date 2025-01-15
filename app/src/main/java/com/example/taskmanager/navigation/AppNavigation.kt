package com.example.taskmanager.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.taskmanager.ui.screens.AddEditTaskScreen
import com.example.taskmanager.ui.screens.TaskScreen
import com.example.taskmanager.ui.viewmodel.AddEditTaskViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = "taskScreen") {
        composable("taskScreen") {
            TaskScreen(navController)
        }
        composable("addEditTaskScreen") {
            AddEditTaskScreen(navController)
        }
    }
}
