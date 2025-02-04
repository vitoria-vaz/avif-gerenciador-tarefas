package com.avif.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.avif.myapplication.ui.viewmodel.TaskViewModel
import com.avif.myapplication.ui.components.TaskItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: TaskViewModel = viewModel()

            val tasks = viewModel.tasks.collectAsState().value

            Column {
                tasks.forEach { task ->
                    TaskItem(
                        task = task,
                        onToggleComplete = { viewModel.toggleComplete(it) },
                        onDelete = { viewModel.removeTask(it) }
                    )
                }
            }
        }
    }
}
