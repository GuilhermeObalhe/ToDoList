package com.example.taskmanager.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.taskmanager.ui.theme.TaskManagerTheme


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TaskScreen(modifier: Modifier = Modifier, onAddTaskClick: () -> Unit) {
    var selectedScreen by remember { mutableStateOf(1) }
    val screens = listOf("Calendar", "Home", "Notifications")

    TaskManagerTheme {
        Scaffold(bottomBar = {
            BottomNavigation(
                modifier = Modifier.height(90.dp),
                backgroundColor = Color.White
            ) {
                screens.forEachIndexed { index, _ ->
                    val icon: ImageVector = when (index) {
                        0 -> Icons.Filled.CalendarMonth
                        1 -> Icons.Filled.Home
                        2 -> Icons.Filled.Mail
                        else -> Icons.Filled.Home
                    }
                    BottomNavigationItem(
                        modifier = Modifier.height(90.dp),
                        selected = selectedScreen == index,
                        onClick = { selectedScreen = index },
                        icon = {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(CircleShape)
                                    .background(if (selectedScreen == index) Color.Black else Color.White),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = "Calendar",
                                    modifier = Modifier
                                        .size(50.dp)
                                        .padding(12.dp)
                                        .fillMaxHeight(),
                                    tint = if (selectedScreen == index) Color.White else Color.Black
                                )
                            }
                        }
                    )
                }
            }
        },
            floatingActionButton = {
                FloatingActionButton(onClick = onAddTaskClick,
                    backgroundColor = Color.Black,
                    contentColor = Color.White) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }) {
            LazyColumn(
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
                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}