package com.example.nework.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.nework.dto.PostMinimal
import com.example.nework.entity.PostEntity
import com.example.nework.enumeration.AttachmentType
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PostDao : BaseDao<PostEntity> {

    @Query("SELECT id, authorId, author, authorAvatar, authorJob, content, likedByMe, ownedByMe, url, type FROM posts ORDER BY id DESC")
    abstract fun getPostsMinimal(): Flow<List<PostMinimal>>

    @Query("DELETE FROM posts WHERE id = :id")
    abstract suspend fun deletePostById(id: Int)

    @Query("UPDATE posts SET content = :content, url = :url, type = :type  WHERE id = :id")
    abstract suspend fun updatePostById(id: Int, content: String, url: String, type: AttachmentType)

    @Query("UPDATE posts SET likedByMe = CASE WHEN likedByMe THEN 0 ELSE 1 END WHERE id = :id")
    abstract suspend fun likedPostById(id: Int)
}
