package com.example.kbtu_helper.subjects

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kbtu_helper.chats.Chat
import com.example.kbtu_helper.stats.TagStat

@Dao
interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createSubject(subject: Subject)

    @Query("SELECT * FROM subjects ORDER BY id ASC")
    fun listSubjects(): LiveData<List<Subject>>

    @Query("SELECT * FROM subjects")
    fun getAllSubjects(): List<Subject>

    @Query("SELECT * FROM subjects WHERE tag=:tag")
    fun getAllSubjectsByTag(tag: String): List<Subject>
}