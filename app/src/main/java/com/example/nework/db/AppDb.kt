package com.example.nework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nework.dao.EventDao
import com.example.nework.dao.JobDao
import com.example.nework.dao.PostDao
import com.example.nework.entity.EventEntity
import com.example.nework.entity.JobEntity
import com.example.nework.entity.PostEntity


@Database(
    entities = [PostEntity::class, EventEntity::class, JobEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDb : RoomDatabase() {

    abstract fun getPostDao(): PostDao
    abstract fun getEventDao(): EventDao
    abstract fun getJobDao(): JobDao
}
