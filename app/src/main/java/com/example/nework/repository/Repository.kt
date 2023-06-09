package com.example.nework.repository

import com.example.nework.dto.Media
import com.example.nework.dto.MediaUpload
import com.example.nework.dto.Minimal
import kotlinx.coroutines.flow.Flow

interface Repository {
    val data: Flow<List<Minimal>>
    suspend fun getAll()
    suspend fun likeById(id: Long)
    suspend fun upload(upload: MediaUpload): Media

    suspend fun removeById(id: Long)

}

