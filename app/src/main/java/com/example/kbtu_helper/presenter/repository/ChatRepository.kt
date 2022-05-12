package com.example.kbtu_helper.presenter.repository

import androidx.lifecycle.LiveData
import com.example.kbtu_helper.model.chats.Chat
import com.example.kbtu_helper.presenter.db.ChatDao

class ChatRepository(private val chatDao: ChatDao) {

    val listData: LiveData<List<Chat>> = chatDao.listChats()

    fun createChat(chat: Chat) {
        chatDao.createChat(chat)
    }

    fun getChatById(uid: String): Chat {
        return chatDao.getChatByUId(uid)
    }

    fun getAllChats(): List<Chat> {
        return chatDao.getAllChats()
    }

    fun updateIsSeenInChatById(uid: String, isSeen: Boolean) {
        chatDao.updateIsSeenInChatById(uid, isSeen)
    }
}