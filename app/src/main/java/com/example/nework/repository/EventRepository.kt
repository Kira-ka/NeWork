package com.example.nework.repository

import com.example.nework.dto.Event
import com.example.nework.dto.Media
import com.example.nework.dto.MediaUpload
import com.example.nework.dto.Post

interface EventRepository : Repository {
    suspend fun save(event: Event, upload: MediaUpload?)

}
