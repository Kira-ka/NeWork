package com.example.nework.repository

import com.example.nework.dto.Job
import kotlinx.coroutines.flow.Flow

interface JobRepository : Repository {
    suspend fun save(job: Job)

}
