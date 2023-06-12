package com.example.nework.repository

import com.example.nework.dto.Event
import com.example.nework.dto.Media
import com.example.nework.dto.MediaUpload

interface EventRepository : Repository {
    suspend fun save(event: Event, upload: MediaUpload?)
    suspend fun likeById(id: Int)
    suspend fun dislikeById(id: Int)
    suspend fun upload(upload: MediaUpload): Media
}
