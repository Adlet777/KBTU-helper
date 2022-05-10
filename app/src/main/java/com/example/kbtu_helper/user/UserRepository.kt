package com.example.kbtu_helper.user

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val listData: LiveData<List<User>> = userDao.listUsers()

    fun createUser(user: User){
        userDao.createUser(user)
    }

    fun getUserById(uid: String): User {
        return userDao.getUserByUId(uid)
    }

    fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }
}