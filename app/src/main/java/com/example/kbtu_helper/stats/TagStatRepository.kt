package com.example.kbtu_helper.stats

import androidx.lifecycle.LiveData

class TagStatRepository(private val tagStatDao: TagStatDao) {

    val listData: LiveData<List<TagStat>> = tagStatDao.listTagStats()

    fun createTagStat(tagStat: TagStat){
        tagStatDao.createTagStat(tagStat)
    }

    fun getTagStatByTag(tag: String): TagStat {
        return tagStatDao.getTagStatByTag(tag)
    }

    fun getAllTagStats(): List<TagStat> {
        return tagStatDao.getAllTagStats()
    }

    fun updateLevel(uid: Int, level: String) {
        tagStatDao.updateLevel(uid, level)
    }
}