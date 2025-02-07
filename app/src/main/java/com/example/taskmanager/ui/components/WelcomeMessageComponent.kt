package com.example.taskmanager.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanager.R
import com.example.taskmanager.ui.theme.LightGray
import com.example.taskmanager.viewmodel.TaskViewModel

@Composable
fun WelcomeMessageComponent(viewModel : TaskViewModel) {

    val taskCount by viewModel.taskCount.collectAsState()


    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Olá!",
            fontFamily = FontFamily(Font(R.font.nunito_extrabold)),
            fontSize = 22.sp
        )

        val message = if (taskCount == 1) {
            "$taskCount tarefa para hoje"
        } else {
            "$taskCount tarefas para hoje"
        }

        Text(
            text = message,
            fontFamily = FontFamily(Font(R.font.nunito_regular)),
            fontSize = 18.sp,
            color = LightGray
        )
    }
}