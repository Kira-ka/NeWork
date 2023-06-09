package com.example.nework.repository

import com.example.nework.api.ApiService
import com.example.nework.api.MediaService
import com.example.nework.dao.EventDao
import com.example.nework.dto.Media
import com.example.nework.dto.MediaUpload
import com.example.nework.dto.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepositoryImpl @Inject constructor(
    private val eventDao: EventDao,
    private val apiService: ApiService,
    private val mediaService: MediaService
) : PostEventRepository {
    override val data = eventDao.getEventMinimal().flowOn(Dispatchers.Default)

    override suspend fun getAll() {
        TODO("Not yet implemented")
    }

    override suspend fun save(post: Post, upload: MediaUpload?) {
        TODO("Not yet implemented")
    }

    override suspend fun removeById(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun likeById(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun upload(upload: MediaUpload): Media {
        TODO("Not yet implemented")
    }
}
