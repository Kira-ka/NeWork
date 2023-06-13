package com.example.nework.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.nework.dto.EventMinimal
import com.example.nework.entity.EventEntity
import com.example.nework.enumeration.AttachmentType
import kotlinx.coroutines.flow.Flow

@Dao
abstract class EventDao : BaseDao<EventEntity> {

    @Query("SELECT  id, authorId, author, authorAvatar, authorJob, content, datetime, published, likedByMe, ownedByMe, url, type  FROM events ORDER BY id DESC")
    abstract fun getEventMinimal(): Flow<List<EventMinimal>>

    @Query("DELETE FROM events WHERE id =:id")
    abstract suspend fun deleteEventById(id: Long)

    @Query("UPDATE events SET content = :content, datetime = :datetime, url = :url, type = :type WHERE id = :id")
    abstract suspend fun updateEventById(
        id: Long,
        content: String,
        datetime: String,
        url: String,
        type: AttachmentType
    )

    @Query("UPDATE events SET likedByMe = CASE WHEN likedByMe THEN 0 ELSE 1 END WHERE id = :id")
    abstract suspend fun likedEventById(id: Long)
}
