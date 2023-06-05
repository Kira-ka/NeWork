package com.example.nework.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.nework.dto.Attachment
import com.example.nework.dto.Coordinates
import com.example.nework.dto.Post
import com.example.nework.dto.UserPreview
import com.example.nework.enumeration.AttachmentType

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey(true)
    val id: Int,
    val authorId: String,
    val author: String,
    val authorAvatar: String? = null,
    val authorJob: String? = null,
    val content: String,
    val published: String,
    @Embedded
    var cords: CoordinatesEmbedded?,
    val likeOwnerIds: List<Int>,
    val likedByMe: Boolean,
    val mentionIds: List<Int>,
    val mentionedMe: Boolean,
    @Embedded
    var attachment: AttachmentEmbedded?,
    val link: String? = null,
    val ownedByMe: Boolean,
    @Embedded
    var users: UserPreviewEmbedded
) {
    fun toDto() = Post(
        id,
        authorId,
        author,
        authorAvatar,
        authorJob,
        content,
        published,
        cords?.toDto(),
        likeOwnerIds,
        likedByMe,
        mentionIds,
        mentionedMe,
        attachment?.toDto(),
        link,
        ownedByMe,
        users.toDto()
    )

    companion object {
        fun fromDto(post: Post) = PostEntity(
            post.id,
            post.authorId,
            post.author,
            post.authorAvatar,
            post.authorJob,
            post.content,
            post.published,
            CoordinatesEmbedded.fromDto(post.cords),
            post.likeOwnerIds,
            post.likedByMe,
            post.mentionIds,
            post.mentionedMe,
            AttachmentEmbedded.fromDto(post.attachment),
            post.link,
            post.ownedByMe,
            UserPreviewEmbedded.fromDto(post.users)
        )
    }

}

data class AttachmentEmbedded(
    var url: String,
    var type: AttachmentType
) {
    fun toDto() = Attachment(url, type)

    companion object {
        fun fromDto(attachment: Attachment?) =
            attachment?.let { AttachmentEmbedded(it.url, it.type) }
    }
}

class Converters {
    @TypeConverter
    fun toAttachmentType(value: String) = enumValueOf<AttachmentType>(value)

    @TypeConverter
    fun fromAttachmentType(value: AttachmentType) = value.name
}

data class CoordinatesEmbedded(
    var lat: Double,
    var long: Double
) {
    fun toDto() = Coordinates(lat, long)

    companion object{
        fun fromDto(cords: Coordinates?) = cords?.let { CoordinatesEmbedded(it.lat, it.long) }
    }
}

data class UserPreviewEmbedded(
    var name: String,
    var avatar: String?
){
    fun toDto() = UserPreview(name, avatar)

    companion object{
        fun fromDto(users: UserPreview) = UserPreviewEmbedded(users.name, users.avatar)
    }
}
