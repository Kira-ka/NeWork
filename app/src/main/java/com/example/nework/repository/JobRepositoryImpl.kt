package com.example.nework.repository

import com.example.nework.api.ApiService
import com.example.nework.dao.JobDao
import com.example.nework.dto.Job
import com.example.nework.entity.JobEntity
import com.example.nework.entity.toEntity
import com.example.nework.error.ApiError
import com.example.nework.error.AppError
import kotlinx.coroutines.Dispatchers
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
        try {
            val response = apiService.getAllMyJobs()
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            val body = response.body() ?: throw ApiError(response.code(), response.message())
            jobDao.insert(body.toEntity())
        } catch (e: Throwable) {
            AppError.from(e)
        }
    }

    override suspend fun save(job: Job) {
        try {
            val response = apiService.saveJob(job)
            if (!response.isSuccessful){
                throw ApiError(response.code(), response.message())
            }
            val body = response.body() ?: throw ApiError(response.code(), response.message())
            jobDao.insert(JobEntity.fromDto(body))
        } catch (e: Throwable) {
            AppError.from(e)
        }
    }

    override suspend fun removeById(id: Long) {
        try {
            val response = apiService.deleteJob(id)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            jobDao.deleteJobById(id)
        } catch (e: Throwable) {
            AppError.from(e)
        }
    }
}
