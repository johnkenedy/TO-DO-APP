package com.example.theclassicto_doapp.domain

data class ToDo(
    val id: Long,
    val title: String,
    val description: String?,
    val isDone: Boolean
)

//fake objects
val todo1 = ToDo(1, "Title1", "Description1", false)
val todo2 = ToDo(2, "Title2", "Description2", true)
val todo3 = ToDo(3, "Title3", "Description3", true)
val todoListItems = listOf(todo1, todo2, todo3)
