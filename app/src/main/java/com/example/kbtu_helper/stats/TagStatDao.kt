package com.example.kbtu_helper.stats

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TagStatDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createTagStat(tagStat: TagStat)

    @Query("SELECT * FROM tag_stat ORDER BY uid ASC")
    fun listTagStats(): LiveData<List<TagStat>>

    @Query("SELECT * FROM tag_stat WHERE tag=:tag")
    fun getTagStatByTag(tag: String): TagStat

    @Query("SELECT * FROM tag_stat")
    fun getAllTagStats(): List<TagStat>

    @Query("UPDATE tag_stat SET level=:level WHERE uid=:uid")
    fun updateLevel(uid: Int, level: String)
}