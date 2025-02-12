package com.example.theclassicto_doapp.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String?,
    val isDone: Boolean
)
