package com.example.theclassicto_doapp.data.di

import com.example.theclassicto_doapp.data.repository.ToDoRepository
import com.example.theclassicto_doapp.data.repository.ToDoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindToDoRepository(repository: ToDoRepositoryImpl): ToDoRepository

}