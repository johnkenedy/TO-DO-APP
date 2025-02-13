package com.example.theclassicto_doapp.data.di

import com.example.theclassicto_doapp.data.datasource.LocalDataSource
import com.example.theclassicto_doapp.data.datasource.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataSourceModule {

    @Binds
    fun bindLocalDataSource(impl: LocalDataSourceImpl): LocalDataSource

}