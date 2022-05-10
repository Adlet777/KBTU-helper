package com.example.kbtu_helper.stats

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TagStatViewModel(application: Application): AndroidViewModel(application) {

    val listTagStats: LiveData<List<TagStat>>
    private val repository: TagStatRepository

    init {
        val tagStatDao = TagStatDB.getDatabase(application).tagStatDao()
        repository = TagStatRepository(tagStatDao)
        listTagStats = repository.listData
    }

    fun createTagStat(tagStat: TagStat) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createTagStat(tagStat)
        }
    }

    fun getTagStatsByTag(tag: String): TagStat {
        return repository.getTagStatByTag(tag)
    }


    fun getAllTagStats(): List<TagStat> {
        return repository.getAllTagStats()
    }

    fun updateLevel(uid: Int, level: String) {
        repository.updateLevel(uid, level)
    }
}