package com.example.taskmanager.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanager.ui.theme.TaskManagerTheme


@Composable
fun AddTaskComponent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    startTime: String,
    onStartChange: (String) -> Unit,
    endTime: String,
    onEndChange: (String) -> Unit,
) {

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = title,
            onValueChange = onTitleChange,
            label = { Text("Título") },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = description,
            onValueChange = onDescriptionChange,
            label = { Text("Descrição") },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = startTime,
            onValueChange = onStartChange,
            label = { Text("Início") },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = endTime,
            onValueChange = onEndChange,
            label = { Text("Fim") },
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}


@Preview
@Composable
private fun AddTaskComponentPreview() {
    TaskManagerTheme {
        AddTaskComponent(
            title = "Exemplo de título",
            onTitleChange = {},
            description = "Exemplo de descrição",
            onDescriptionChange = {},
            startTime = "08:00",
            onStartChange = {},
            endTime = "09:00",
            onEndChange = {}
        )
    }
}
