package com.avif.myapplication.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TaskDTO(
    val id: Int,
    val title: String,
    val body: String? = null,
    val startTime: String,
    val endTime: String,
    var isCompleted: Boolean = false
)

