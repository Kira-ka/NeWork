package com.example.nework.dto

import com.example.nework.enumeration.AttachmentType

data class PostMinimal  (
    override val id: Long,
    val authorId: String,
    val author: String,
    val authorAvatar: String? = null,
    val authorJob: String? = null,
    val content: String,
    val likedByMe: Boolean,
    val url: String? = null,
    val type: AttachmentType? = null,
    val ownedByMe: Boolean
) : Minimal
