package com.example.nework.dto

data class Post(
    val id: Int,
    val authorId: String,
    val author: String,
    val authorAvatar: String? = null,
    val authorJob: String? = null,
    val content: String,
    val published: String,
    val cords: Coordinates? = null,
    val likeOwnerIds: List<Int>,
    val likedByMe: Boolean,
    val mentionIds: List<Int>,
    val mentionedMe: Boolean,
    val attachment: Attachment? = null,
    val link: String? = null,
    val ownedByMe: Boolean,
    val users: UserPreview
)
