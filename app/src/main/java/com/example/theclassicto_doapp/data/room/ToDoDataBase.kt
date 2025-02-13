package com.example.theclassicto_doapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ToDoEntity::class],
    version = 1
)
abstract class ToDoDataBase: RoomDatabase() {

    abstract val toDoDao: ToDoDao
}