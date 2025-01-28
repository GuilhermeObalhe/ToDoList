package com.example.taskmanager.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomComponent(modifier: Modifier = Modifier, navController: NavController) {
    var selectedScreen by remember { mutableIntStateOf(1) }
    val screens = listOf("Calendar", "Home", "Notifications")
    BottomNavigation(
        modifier = Modifier
            .height(125.dp)
            .navigationBarsPadding(),
        backgroundColor = Color.White,
        elevation = 0.dp
    ) {
        screens.forEachIndexed { index, _ ->
            val icon: ImageVector = when (index) {
                0 -> Icons.Filled.DateRange
                1 -> Icons.Filled.Home
                2 -> Icons.Filled.Email
                else -> Icons.Filled.Home
            }
            BottomNavigationItem(
                modifier = Modifier.align(Alignment.CenterVertically),
                selected = selectedScreen == index,
                onClick = {
                    selectedScreen = index
                    if (index == 0) {
                        navController.navigate("calendarScreen")
                    }
                    if (index == 1) {
                        navController.navigate("taskScreen")
                    }
                    if (index == 2) {
                        navController.navigate("notificationsScreen")
                    }
                },
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
                                .padding(12.dp),
                            tint = if (selectedScreen == index) Color.White else Color.Black
                        )
                    }
                }
            )
        }
    }
}


@Preview
@Composable
private fun BottomComponentPreview() {
    BottomComponent(
        navController = NavController(LocalContext.current)
    )
}