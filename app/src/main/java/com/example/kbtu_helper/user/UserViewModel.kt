package com.example.kbtu_helper.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val listUsers: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val todoDao = UserDB.getDatabase(application).userDao()
        repository = UserRepository(todoDao)
        listUsers = repository.listData
    }

    fun createUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createUser(user)
        }
    }

    fun getUserById(uid: String): User {
        return repository.getUserById(uid)
    }


    fun getAllUsers(): List<User> {
        return repository.getAllUsers()
    }
}