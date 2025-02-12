package com.example.theclassicto_doapp.domain.util

import com.example.theclassicto_doapp.data.room.ToDoEntity
import com.example.theclassicto_doapp.domain.ToDo

object ToDoMapper {
    fun mapToDoEntityToToDo(entity: ToDoEntity): ToDo {
        return ToDo(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            isDone = entity.isDone
        )
    }
}