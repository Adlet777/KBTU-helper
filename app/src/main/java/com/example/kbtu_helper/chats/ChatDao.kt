package com.example.kbtu_helper.chats

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createChat(chat: Chat)

    @Query("SELECT * FROM chat ORDER BY uid ASC")
    fun listChats(): LiveData<List<Chat>>

    @Query("SELECT * FROM chat WHERE uid=:uid")
    fun getChatByUId(uid: String): Chat

    @Query("SELECT * FROM chat")
    fun getAllChats(): List<Chat>

    @Query("UPDATE chat SET isSeen=:isSeen WHERE uid=:uid")
    fun updateIsSeenInChatById(uid: String, isSeen: Boolean)
}