package com.example.nework.repository

import com.example.nework.api.ApiService
import com.example.nework.api.MediaService
import com.example.nework.dao.EventDao
import com.example.nework.dto.Attachment
import com.example.nework.dto.Event
import com.example.nework.dto.Media
import com.example.nework.dto.MediaUpload
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
class EventRepositoryImpl @Inject constructor(
    private val eventDao: EventDao,
    private val apiService: ApiService,
    private val mediaService: MediaService
) : EventRepository {
    override val data = eventDao.getEventMinimal().flowOn(Dispatchers.Default)

    override suspend fun getAll() {
        try {
            val response = apiService.getAllEvents()
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            val body = response.body() ?: throw ApiError(response.code(), response.message())
            eventDao.insert(body.toEntity())
        } catch (e: Throwable) {
            throw AppError.from(e)
        }
    }


    override suspend fun save(event: Event, upload: MediaUpload?) {
        try {
            val eventWithAttachment = upload?.let {
                upload(it)
            }?.let {

                event.copy(attachment = Attachment(it.id, AttachmentType.IMAGE))
            }
            val response = apiService.saveEvent(eventWithAttachment ?: event)
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
            val response = mediaService.upload(media)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            return response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e: Throwable) {
            throw AppError.from(e)
        }
    }
}
