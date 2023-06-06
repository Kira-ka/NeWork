package com.example.nework.dto

import com.example.nework.enumeration.AttachmentType

data class EventMinimal(
    val id: Int,
    val authorId: String,
    val author: String,
    val authorAvatar: String? = null,
    val authorJob: String? = null,
    val content: String,
    val datetime: String,
    val published: String,
    val likedByMe: Boolean,
    val url: String? = null,
    val type: AttachmentType? = null,
    val ownedByMe: Boolean
)
