package com.example.taskmanager.domain.model

data class Task(
    val id: Int,
    val title: String,
    val description: String? = null,
    val startTime: String,
    val endTime: String,
    val isCompleted: Boolean = false
)

// Fake Objects

val taskList = listOf(
    Task(
        1,
        "Do Laundry",
        "Wash and fold clothes",
        "10:00",
        "11:00"
    ),
    Task(
        2,
        "Clean Kitchen",
        "Clean and tidy kitchen",
        "11:00",
        "12:00"
    ),
    Task(
        3,
        "Vacuum Living Room",
        "Clean carpets and furniture",
        "13:00",
        "14:00"
    )
)