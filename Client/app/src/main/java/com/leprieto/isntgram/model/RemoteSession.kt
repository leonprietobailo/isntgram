package com.leprieto.isntgram.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(
    tableName = "remote_sessions",
    foreignKeys = [
        ForeignKey(
            entity = RemoteUser::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RemoteSession(
    @PrimaryKey
    val userId: String,
    val token: String,
    val lastAccess: Timestamp
)