package com.example.kbtu_helper.model.subjects

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kbtu_helper.presenter.db.SubjectDB
import com.example.kbtu_helper.presenter.repository.SubjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubjectViewModel(application: Application) : AndroidViewModel(application) {

    val listSubjects: LiveData<List<Subject>>
    private val repository: SubjectRepository

    init {
        val subjectDao = SubjectDB.getDatabase(application).todoDao()
        repository = SubjectRepository(subjectDao)
        listSubjects = repository.listData
    }

    fun createSubject(subject: Subject) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createSubject(subject)
        }
    }

    fun getAllSubjects(): List<Subject> {
        return repository.getAllSubjects()
    }

    fun getAllSubjectsByTag(tag: String): List<Subject> {
        return repository.getAllSubjectsByTag(tag)
    }
}