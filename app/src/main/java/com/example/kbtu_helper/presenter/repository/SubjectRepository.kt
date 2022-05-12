package com.example.kbtu_helper.presenter.repository

import androidx.lifecycle.LiveData
import com.example.kbtu_helper.model.subjects.Subject
import com.example.kbtu_helper.presenter.db.SubjectDao

class SubjectRepository(private val subjectDao: SubjectDao) {

    val listData: LiveData<List<Subject>> = subjectDao.listSubjects()

    fun createSubject(subject: Subject) {
        subjectDao.createSubject(subject)
    }

    fun getAllSubjects(): List<Subject> {
        return subjectDao.getAllSubjects()
    }

    fun getAllSubjectsByTag(tag: String): List<Subject> {
        return subjectDao.getAllSubjectsByTag(tag)
    }
}