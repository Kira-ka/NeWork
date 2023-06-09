package com.example.nework.repository

import com.example.nework.api.ApiService
import com.example.nework.dao.JobDao
import com.example.nework.dto.Job
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobRepositoryImpl @Inject constructor(
    private val jobDao: JobDao,
    private val apiService: ApiService
) : JobRepository {
    override val data = jobDao.getAll().flowOn(Dispatchers.Default)


    override suspend fun getAll() {
        TODO("Not yet implemented")
    }

    override suspend fun save(job: Job) {
        TODO("Not yet implemented")
    }

    override suspend fun removeById(id: Int) {
        TODO("Not yet implemented")
    }
}
