package com.example.nework.repository

import com.example.nework.dto.Media
import com.example.nework.dto.MediaUpload
import com.example.nework.dto.Post

interface PostRepository : Repository {
    suspend fun save(post: Post, upload: MediaUpload?)
    suspend fun likeById(id: Int)
    suspend fun dislikeById(id: Int)
    suspend fun upload(upload: MediaUpload): Media
}
