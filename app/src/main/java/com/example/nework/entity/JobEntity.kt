package com.example.nework.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nework.dto.Job

@Entity(tableName = "jobs")
data class JobEntity(
    @PrimaryKey(true)
    val id: Int,
    val name: String,
    val position: String,
    val start: String,
    val finish: String? = null,
    val link: String? = null,
) {
    fun toDto() = com.example.nework.dto.Job(id, name, position, start, finish, link)

    companion object {
        fun fromDto(job: com.example.nework.dto.Job) =
            JobEntity(job.id, job.name, job.position, job.start, job.finish, job.link)
    }
}

fun List<Job>.toEntity(): List<JobEntity> = map(JobEntity::fromDto)
