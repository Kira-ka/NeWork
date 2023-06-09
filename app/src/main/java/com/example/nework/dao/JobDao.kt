package com.example.nework.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.nework.dto.Job
import com.example.nework.entity.JobEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class JobDao : BaseDao<JobEntity> {

    @Query("SELECT * FROM jobs ORDER BY id DESC")
    abstract fun getAll(): Flow<List<Job>>

    @Query("DELETE FROM jobs WHERE id = :id")
    abstract suspend fun deleteJobById(id: Long)

    @Query("UPDATE jobs SET position = :position, start = :start, finish = :finish, link = :link WHERE id = :id")
    abstract suspend fun updateJob(
        id: Int,
        position: String,
        start: String,
        finish: String,
        link: String
    )

}
