package com.example.kbtu_helper.chats

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "chat")
data class Chat(
    @PrimaryKey
    val uid: String,
    val sender: String,
    val receiver: String,
    val message: String,
    val time: String,
    val isSeen: Boolean
): Parcelable
