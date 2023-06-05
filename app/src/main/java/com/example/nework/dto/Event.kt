package com.example.nework.dto

import com.example.nework.enumeration.AttachmentType
import com.example.nework.enumeration.TypeOffOnLine

data class Event(
    val id: Int,
    val authorId: String,
    val author: String,
    val authorAvatar: String? = null,
    val authorJob: String? = null,
    val content: String,
    val datetime: String,
    val published: String,
    val cords: Coordinates? = null,
    val typeOffOnLine: TypeOffOnLine,
    val likeOwnerIds: List<Int>,
    val likedByMe: Boolean,
    val speakerIds: List<Int>,
    val participantsIds: List<Int>,
    val participatedByMe: Boolean,
    val attachment: Attachment? = null,
    val link: String? = null,
    val ownedByMe: Boolean,
    val users: UserPreview
)

data class Coordinates(
    val lat: Double,
    val long: Double
)

data class UserPreview(
    val name: String,
    val avatar: String? = null
)

data class 	Attachment(
    val url: String,
    val type: AttachmentType
)
