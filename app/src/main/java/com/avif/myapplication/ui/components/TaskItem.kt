package com.avif.myapplication.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.avif.myapplication.data.model.TaskDTO
import com.avif.myapplication.ui.theme.LightBlue
import com.avif.myapplication.ui.theme.LightGreen
import com.avif.myapplication.ui.theme.LightPurple
import kotlin.random.Random

@Composable
fun TaskItem(
    task: TaskDTO,
    onToggleComplete: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    onAddTask: (String, String?, String, String) -> Unit
) {
    val taskColors = listOf(LightPurple, LightGreen, LightBlue)
    val taskColor = remember { taskColors[Random.nextInt(taskColors.size)] }

    var showDialog by remember { mutableStateOf(false) }
    var newTitle by remember { mutableStateOf("") }
    var newBody by remember { mutableStateOf("") }
    var newStartTime by remember { mutableStateOf("") }
    var newEndTime by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .border(BorderStroke(3.dp, Color.Black), CircleShape)
                .clickable { onToggleComplete(task.id) },
            contentAlignment = Alignment.Center
        ) {
            if (task.isCompleted) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(Color.Black, CircleShape)
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(14.dp))
                .background(taskColor)
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = task.title, style = MaterialTheme.typography.titleMedium)
            task.body?.let {
                Text(text = it, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(onClick = { onDelete(task.id) }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Deletar", tint = Color.Red)
        }

        IconButton(onClick = { showDialog = true }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Adicionar Tarefa", tint = Color.Blue)
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Adicionar Nova Tarefa") },
            text = {
                Column {
                    OutlinedTextField(
                        value = newTitle,
                        onValueChange = { newTitle = it },
                        label = { Text("Título") }
                    )
                    OutlinedTextField(
                        value = newBody,
                        onValueChange = { newBody = it },
                        label = { Text("Descrição (opcional)") }
                    )
                    OutlinedTextField(
                        value = newStartTime,
                        onValueChange = { newStartTime = it },
                        label = { Text("Hora de início (ex: 10:00)") }
                    )
                    OutlinedTextField(
                        value = newEndTime,
                        onValueChange = { newEndTime = it },
                        label = { Text("Hora de término (ex: 12:00)") }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newTitle.isNotBlank() && newStartTime.isNotBlank() && newEndTime.isNotBlank()) {
                            onAddTask(newTitle, newBody.takeIf { it.isNotBlank() }, newStartTime, newEndTime)
                            showDialog = false
                        }
                    }
                ) {
                    Text("Adicionar")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
