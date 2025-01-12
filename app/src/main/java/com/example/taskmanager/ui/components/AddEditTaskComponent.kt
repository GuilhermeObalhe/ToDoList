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
    start: String,
    onStartChange: (String) -> Unit,
    end: String,
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
            value = start,
            onValueChange = onStartChange,
            label = { Text("Início") },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = end,
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
            start = "08:00",
            onStartChange = {},
            end = "09:00",
            onEndChange = {}
        )
    }
}
