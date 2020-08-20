package com.example.todoapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoModel (
    val title: String,
    val description: String,
    val data: Long,
    val time: Long,
    val isFinished: Int = -1,
    @PrimaryKey(autoGenerate = true)
    val id: Int
)