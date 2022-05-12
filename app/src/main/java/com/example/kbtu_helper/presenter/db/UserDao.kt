package com.example.kbtu_helper.presenter.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kbtu_helper.model.user.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createUser(user: User)

    @Query("SELECT * FROM user ORDER BY uid ASC")
    fun listUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE uid=:uid")
    fun getUserByUId(uid: String): User

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>
}