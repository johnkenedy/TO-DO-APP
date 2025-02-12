package com.example.theclassicto_doapp.data.repository

import com.example.theclassicto_doapp.data.room.ToDoDao
import com.example.theclassicto_doapp.data.room.ToDoEntity
import com.example.theclassicto_doapp.domain.ToDo
import com.example.theclassicto_doapp.domain.util.ToDoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ToDoRepositoryImpl(
    private val dao: ToDoDao
) : ToDoRepository {

    override suspend fun insert(title: String, description: String?, id: Long?) {
        val entity = id?.let {
            dao.getBy(it)?.copy(
                title = title,
                description = description
            )
        } ?: ToDoEntity(
            title = title,
            description = description,
            isDone = false
        )

        dao.insert(entity)
    }

    override suspend fun isDone(id: Long, isDone: Boolean) {
        val existingEntity = dao.getBy(id) ?: return
        val updatedEntity = existingEntity.copy(isDone = isDone)
        dao.insert(updatedEntity)
    }

    override suspend fun delete(id: Long) {
        val existingEntity = dao.getBy(id) ?: return
        dao.delete(existingEntity)
    }

    override fun getAll(): Flow<List<ToDo>> {
        return dao.getAll().map { entities ->
            entities.map { entity ->
                ToDoMapper.mapToDoEntityToToDo(entity)
            }
        }
    }

    override suspend fun getBy(id: Long): ToDo? {
        return dao.getBy(id)?.let { entity ->
            ToDoMapper.mapToDoEntityToToDo(entity)
        }
    }
}