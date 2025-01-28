package com.example.taskmanager.ui.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanager.R
import com.example.taskmanager.domain.model.Task
import com.example.taskmanager.domain.model.taskList
import com.example.taskmanager.ui.theme.LightBlue
import com.example.taskmanager.ui.theme.LightGreen
import com.example.taskmanager.ui.theme.LightPurple
import com.example.taskmanager.ui.theme.TaskManagerTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskComponent(task: Task,
                  onEdit: () -> Unit,
                  onDelete: () -> Unit) {
    val taskColor = listOf(LightPurple, LightGreen, LightBlue).random()

    // Estado para controlar se a tarefa está selecionada
    var isSelected by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isSelected) Color.LightGray else Color.Transparent)
            .combinedClickable(
                onClick = { /* Não faz nada em clique simples */ },
                onLongClick = { isSelected = !isSelected } // Alterna o estado de seleção
            ),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${task.startTime}\nAM",
            fontFamily = FontFamily(Font(R.font.nunito_bold)),
            textAlign = TextAlign.Center
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .border(
                        border = BorderStroke(3.dp, Color.Black),
                        shape = CircleShape
                    )
            )

            HorizontalDivider(modifier = Modifier.width(6.dp), color = Color.Black)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .background(taskColor)
                        .weight(0.9f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = task.title,
                        fontFamily = FontFamily(Font(R.font.nunito_bold)),
                        modifier = Modifier.padding(
                            start = 12.dp,
                            top = 12.dp
                        )
                    )

                    if (task.description != null) {
                        Text(
                            text = task.description,
                            fontFamily = FontFamily(Font(R.font.nunito_bold)),
                            modifier = Modifier.padding(start = 12.dp),
                            color = Color.Gray
                        )
                    }

                    Text(
                        text = "${task.startTime} - ${task.endTime}",
                        fontFamily = FontFamily(Font(R.font.nunito_bold)),
                        modifier = Modifier.padding(
                            start = 12.dp,
                            bottom = 12.dp
                        )
                    )
                }

                // Botões de ação (só aparecem se a tarefa está selecionada)
                if (isSelected) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Botão Editar
                        IconButton(
                            onClick = {onEdit()},
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Editar",
                                tint = Color.Yellow
                            )
                        }
                        // Botão Excluir
                        IconButton(
                            onClick = {onDelete()},
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Excluir",
                                tint = Color.Red
                            )
                        }
                    }
                }

                HorizontalDivider(
                    modifier = Modifier
                        .width(6.dp)
                        .weight(0.1f), color = Color.Black
                )
            }
        }
    }
}

@Preview
@Composable
private fun TaskComponentPreview() {
    TaskManagerTheme {
        TaskComponent(taskList[0], onEdit = {}, onDelete = {})
    }
}