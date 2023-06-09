package com.example.nework.repository

import com.example.nework.api.ApiService
import com.example.nework.api.MediaService
import com.example.nework.dao.PostDao
import com.example.nework.dto.Attachment
import com.example.nework.dto.Media
import com.example.nework.dto.MediaUpload
import com.example.nework.dto.Post
import com.example.nework.entity.toEntity
import com.example.nework.enumeration.AttachmentType
import com.example.nework.error.ApiError
import com.example.nework.error.AppError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepositoryImpl @Inject constructor(
    private val postDao: PostDao,
    private val apiService: ApiService,
    private val mediaService: MediaService
) : PostEventRepository {
    override val data = postDao.getPostsMinimal().flowOn(Dispatchers.Default)

    override suspend fun getAll() {
        try {
            val response = apiService.getAllPosts()
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            val body = response.body() ?: throw ApiError(response.code(), response.message())
            postDao.insert(body.toEntity())
        } catch (e: Throwable) {
            throw AppError.from(e)
        }
    }

    override suspend fun save(post: Post, upload: MediaUpload?) {
        try {
            val postWithAttachment = upload?.let {
                upload(it)
            }?.let {

                post.copy(attachment = Attachment(it.id, AttachmentType.IMAGE))
            }
            val response = apiService.savePost(postWithAttachment ?: post)
        } catch (e: Throwable) {
            throw AppError.from(e)
        }
    }

    override suspend fun removeById(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun likeById(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun upload(upload: MediaUpload): Media {
        try {
            val media = MultipartBody.Part.createFormData(
                "file",
                upload.file.name,
                upload.file.asRequestBody()
            )
            val respons = mediaService.upload(media)
            if (!respons.isSuccessful) {
                throw ApiError(respons.code(), respons.message())
            }
        } catch (e: Throwable) {
            throw AppError.from(e)
        }
    }
}
