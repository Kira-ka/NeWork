package com.example.nework.dao

import androidx.room.Query
import com.example.nework.dto.PostMinimal
import com.example.nework.entity.PostEntity
import kotlinx.coroutines.flow.Flow
abstract class PostDao : BaseDao<PostEntity> {

    @Query("SELECT id, authorId, author, authorAvatar, authorJob, content, likedByMe, ownedByMe, url, type FROM posts ORDER BY id DESC")
    abstract fun getPostsMinimal(): Flow<List<PostMinimal>>

}
