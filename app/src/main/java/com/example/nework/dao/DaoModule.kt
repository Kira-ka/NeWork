package com.example.nework.dao

import com.example.nework.db.AppDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DaoModule {

    @Provides
    fun providePostDao(db: AppDb):PostDao = db.getPostDao()

    @Provides
    fun provideEventDao(db: AppDb):EventDao = db.getEventDao()

    @Provides
    fun provideJobDao(db: AppDb):JobDao = db.getJobDao()
}
