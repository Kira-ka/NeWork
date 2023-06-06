package com.example.nework.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nework.dto.Event
import com.example.nework.enumeration.TypeOffOnLine

@Entity(tableName = "events")
class EventEntity(
    @PrimaryKey(true)
    val id: Int,
    val authorId: String,
    val author: String,
    val authorAvatar: String? = null,
    val authorJob: String? = null,
    val content: String,
    val datetime: String,
    val published: String,
    @Embedded
    val cords: CoordinatesEmbedded?,
    val typeOffOnLine: TypeOffOnLine,
    val likeOwnerIds: List<Int>,
    val likedByMe: Boolean,
    val speakerIds: List<Int>,
    val participantsIds: List<Int>,
    val participatedByMe: Boolean,
    @Embedded
    val attachment: AttachmentEmbedded?,
    val link: String? = null,
    val ownedByMe: Boolean,
    @Embedded
    val users: UserPreviewEmbedded
) {
    fun toDto() = com.example.nework.dto.Event(
        id,
        authorId,
        author,
        authorAvatar,
        authorJob,
        content,
        datetime,
        published,
        cords?.toDto(),
        typeOffOnLine,
        likeOwnerIds,
        likedByMe,
        speakerIds,
        participantsIds,
        participatedByMe,
        attachment?.toDto(),
        link,
        ownedByMe,
        users.toDto()
    )

    companion object {
        fun fromDto(event: Event) = EventEntity(
            event.id,
            event.authorId,
            event.author,
            event.authorAvatar,
            event.authorJob,
            event.content,
            event.datetime,
            event.published,
            CoordinatesEmbedded.fromDto(event.cords),
            event.typeOffOnLine,
            event.likeOwnerIds,
            event.likedByMe,
            event.speakerIds,
            event.participantsIds,
            event.participatedByMe,
            AttachmentEmbedded.fromDto(event.attachment),
            event.link,
            event.ownedByMe,
            UserPreviewEmbedded.fromDto(event.users)
        )
    }
}
