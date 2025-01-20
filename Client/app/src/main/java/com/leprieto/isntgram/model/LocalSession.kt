package com.leprieto.isntgram.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "local_sessions",
    foreignKeys = [
        ForeignKey(
            entity = RemoteUser::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LocalSession(
    @PrimaryKey
    val userId: String,
    val token: String
)