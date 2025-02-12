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

object ToDoDataBaseProvider {

    @Volatile
    private var INSTANCE: ToDoDataBase? = null

    fun provide(context: Context): ToDoDataBase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                ToDoDataBase::class.java,
                "todo_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }

}