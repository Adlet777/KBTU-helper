package com.example.kbtu_helper.chats

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatViewModel(application: Application): AndroidViewModel(application) {

    val listChats: LiveData<List<Chat>>
    private val repository: ChatRepository

    init {
        val chatDao = ChatDB.getDatabase(application).chatDao()
        repository = ChatRepository(chatDao)
        listChats = repository.listData
    }

    fun createChat(chat: Chat) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createChat(chat)
        }
    }

    fun getChatById(uid: String): Chat {
        return repository.getChatById(uid)
    }


    fun getAllChats(): List<Chat> {
        return repository.getAllChats()
    }

    fun updateIsSeenById(uid: String, isSeen: Boolean) {
        repository.updateIsSeenInChatById(uid, isSeen)
    }
}