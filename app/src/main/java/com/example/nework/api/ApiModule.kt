package com.example.nework.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return retrofit(okhttp(loggingInterceptor())).create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMediaService(): MediaService {
        return retrofit(okhttpMedia()).create(MediaService::class.java)
    }

}
