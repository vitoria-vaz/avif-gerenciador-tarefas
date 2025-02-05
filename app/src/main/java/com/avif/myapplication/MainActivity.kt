package com.avif.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.avif.myapplication.ui.viewmodel.TaskViewModel
import com.avif.myapplication.ui.components.TaskItem

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var selectedScreen by remember { mutableStateOf(1) }
            val screens = listOf("Calendar", "Home", "Notifications")

            val viewModel: TaskViewModel = viewModel()
            val tasks = viewModel.tasks.collectAsState().value

            Scaffold(
                bottomBar = {
                    BottomNavigation(
                        modifier = Modifier.height(90.dp),
                        backgroundColor = Color.White,
                        elevation = 0.dp
                    ) {
                        screens.forEachIndexed { index, _ ->
                            val icon = when (index) {
                                0 -> Icons.Filled.CalendarMonth
                                1 -> Icons.Filled.Home
                                2 -> Icons.Filled.Mail
                                else -> Icons.Filled.Home
                            }
                            BottomNavigationItem(
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
                                            contentDescription = null,
                                            modifier = Modifier.size(50.dp),
                                            tint = if (selectedScreen == index) Color.White else Color.Black
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    var title by remember { mutableStateOf("") }
                    var body by remember { mutableStateOf("") }
                    var startTime by remember { mutableStateOf("") }
                    var endTime by remember { mutableStateOf("") }

                    LazyColumn(contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)) {
                        item {
                            Text("Adicionar Nova Tarefa", style = MaterialTheme.typography.h6)
                        }
                        item {
                            TextField(value = title, onValueChange = { title = it }, label = { Text("Título") })
                            Spacer(modifier = Modifier.height(8.dp))
                            TextField(value = body, onValueChange = { body = it }, label = { Text("Descrição (opcional)") })
                            Spacer(modifier = Modifier.height(8.dp))
                            TextField(value = startTime, onValueChange = { startTime = it }, label = { Text("Hora de início") })
                            Spacer(modifier = Modifier.height(8.dp))
                            TextField(value = endTime, onValueChange = { endTime = it }, label = { Text("Hora de término") })
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(onClick = {
                                if (title.isNotBlank() && startTime.isNotBlank() && endTime.isNotBlank()) {
                                    viewModel.addTask(title, body.ifBlank { null }, startTime, endTime)
                                    title = ""
                                    body = ""
                                    startTime = ""
                                    endTime = ""
                                }
                            }) {
                                Icon(imageVector = Icons.Filled.Add, contentDescription = "Adicionar Tarefa")
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Adicionar Tarefa")
                            }
                        }
                        items(tasks) { task ->
                            TaskItem(
                                task = task,
                                onToggleComplete = { viewModel.toggleComplete(it) },
                                onDelete = { viewModel.removeTask(it) }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}
