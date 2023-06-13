package com.example.nework.repository

import com.example.nework.dto.Minimal
import kotlinx.coroutines.flow.Flow

interface Repository {
    val data: Flow<List<Minimal>>
    suspend fun getAll()

    suspend fun removeById(id: Long)

}

