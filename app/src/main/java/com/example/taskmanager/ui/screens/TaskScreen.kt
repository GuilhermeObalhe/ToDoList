package com.example.taskmanager.ui.screens

import android.media.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanager.domain.model.taskList
import com.example.taskmanager.ui.components.ProfileHeaderComponent
import com.example.taskmanager.ui.components.TaskComponent
import com.example.taskmanager.ui.components.WelcomeMessageComponent
import com.example.taskmanager.ui.theme.TaskManagerTheme

enum class Screen {
    Calendar,
    Home,
    Notifications
}

@Composable
fun TaskScreen(onAddTaskClick: () -> Unit) {
    var selectedScreen by remember{mutableStateOf(Screen.Home)}
    val screens = listOf(Screen.Calendar, Screen.Home, Screen.Notifications)

    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(90.dp).fillMaxWidth(),
                containerColor = Color.White
            ) {
                screens.forEach { screen ->
                    val icon: ImageVector = when (screen) {
                        Screen.Calendar -> Icons.Filled.DateRange
                        Screen.Home -> Icons.Filled.Home
                        Screen.Notifications -> Icons.Filled.Email
                    }
                    NavigationBarItem(
                        selected = selectedScreen == screen,
                        onClick = { selectedScreen = screen },
                        icon = {
                            val isSelected = selectedScreen == screen
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(CircleShape)
                                    .background(if (isSelected) Color.Black else Color.Transparent),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = screen.name,
                                    modifier = Modifier.size(28.dp),
                                    tint = if (isSelected) Color.White else Color.Black
                                )
                            }
                        }
                    )

                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTaskClick,
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

                WelcomeMessageComponent()

                Spacer(modifier = Modifier.height(30.dp))
            }

            items(taskList) { task ->
                TaskComponent(task = task)

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
private fun TaskScreenPreview() {
    TaskManagerTheme {
        TaskScreen(onAddTaskClick = {})
    }
}