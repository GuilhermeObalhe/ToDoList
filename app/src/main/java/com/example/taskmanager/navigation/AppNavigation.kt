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

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = "taskScreen") {
        composable("taskScreen") {
            TaskScreen(navController)
        }
        composable(
            route = "addEditTaskScreen/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") // Use getInt para pegar um Int
            AddEditTaskScreen(navController = navController, taskId = taskId ?: 0) // Passando 0 se for null
        }

        composable("calendarScreen") {
            // Implementar a tela de calendário aqui
        }

        composable("notificationsScreen") {
            // Implemente a tela de notificações aqui
        }
    }
}
