package com.example.theclassicto_doapp.data.datasource

import com.example.theclassicto_doapp.data.room.ToDoDao
import com.example.theclassicto_doapp.data.room.ToDoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: ToDoDao
) : LocalDataSource {
    override suspend fun insertOrUpdate(entity: ToDoEntity) {
        dao.insert(entity)
    }

    override suspend fun delete(entity: ToDoEntity) {
        dao.delete(entity)
    }

    override fun getAll(): Flow<List<ToDoEntity>> {
        return dao.getAll()
    }

    override suspend fun getBy(id: Long): ToDoEntity? {
        return dao.getBy(id)
    }
}