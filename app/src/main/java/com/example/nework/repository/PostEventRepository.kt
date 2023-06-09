package com.example.nework.repository

import com.example.nework.dto.Media
import com.example.nework.dto.MediaUpload
import com.example.nework.dto.Minimal
import com.example.nework.dto.Post
import kotlinx.coroutines.flow.Flow

interface PostEventRepository {
    val data: Flow<List<Minimal>>
    suspend fun getAll()
    suspend fun save(post: Post, upload: MediaUpload?)
    suspend fun removeById(id: Long)
    suspend fun likeById(id: Long)
    suspend fun upload(upload: MediaUpload): Media
}
