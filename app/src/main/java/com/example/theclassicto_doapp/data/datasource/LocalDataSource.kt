package com.example.theclassicto_doapp.data.datasource

import com.example.theclassicto_doapp.data.room.ToDoEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertOrUpdate(entity: ToDoEntity)

    suspend fun delete(entity: ToDoEntity)

    fun getAll(): Flow<List<ToDoEntity>>

    suspend fun getBy(id: Long): ToDoEntity?
}