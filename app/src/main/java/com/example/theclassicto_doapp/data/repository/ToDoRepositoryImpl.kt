package com.example.theclassicto_doapp.data.repository

import com.example.theclassicto_doapp.data.datasource.LocalDataSource
import com.example.theclassicto_doapp.data.room.ToDoEntity
import com.example.theclassicto_doapp.domain.ToDo
import com.example.theclassicto_doapp.domain.util.ToDoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
) : ToDoRepository {

    override suspend fun insert(title: String, description: String?, id: Long?) {
        val entity = id?.let {
            localDataSource.getBy(it)?.copy(
                title = title,
                description = description
            )
        } ?: ToDoEntity(
            title = title,
            description = description,
            isDone = false
        )

        localDataSource.insertOrUpdate(entity)
    }

    override suspend fun isDone(id: Long, isDone: Boolean) {
        val existingEntity = localDataSource.getBy(id) ?: return
        val updatedEntity = existingEntity.copy(isDone = isDone)
        localDataSource.insertOrUpdate(updatedEntity)
    }

    override suspend fun delete(id: Long) {
        val existingEntity = localDataSource.getBy(id) ?: return
        localDataSource.delete(existingEntity)
    }

    override fun getAll(): Flow<List<ToDo>> {
        return localDataSource.getAll().map { entities ->
            entities.map { entity ->
                ToDoMapper.mapToDoEntityToToDo(entity)
            }
        }
    }

    override suspend fun getBy(id: Long): ToDo? {
        return localDataSource.getBy(id)?.let { entity ->
            ToDoMapper.mapToDoEntityToToDo(entity)
        }
    }
}