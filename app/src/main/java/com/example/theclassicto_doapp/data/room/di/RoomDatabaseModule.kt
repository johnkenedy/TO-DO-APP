package com.example.theclassicto_doapp.data.room.di

import android.content.Context
import androidx.room.Room
import com.example.theclassicto_doapp.data.room.ToDoDao
import com.example.theclassicto_doapp.data.room.ToDoDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): ToDoDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            ToDoDataBase::class.java,
            "todo_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideToDoDao(
        dataBase: ToDoDataBase
    ): ToDoDao {
        return dataBase.toDoDao
    }

}