package com.example.theclassicto_doapp.data.repository

import com.example.theclassicto_doapp.domain.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    suspend fun insert(title: String, description: String?)

    suspend fun updateCompleted(id: Long, isDone: Boolean)

    suspend fun delete(id: Long)

    fun getAll(): Flow<List<ToDo>>

    suspend fun getBy(id: Long): ToDo?

}