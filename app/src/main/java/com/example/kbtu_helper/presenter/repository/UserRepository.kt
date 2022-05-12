package com.example.kbtu_helper.presenter.repository

import androidx.lifecycle.LiveData
import com.example.kbtu_helper.model.user.User
import com.example.kbtu_helper.presenter.db.UserDao

class UserRepository(private val userDao: UserDao) {

    val listData: LiveData<List<User>> = userDao.listUsers()

    fun createUser(user: User) {
        userDao.createUser(user)
    }

    fun getUserById(uid: String): User {
        return userDao.getUserByUId(uid)
    }

    fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }
}