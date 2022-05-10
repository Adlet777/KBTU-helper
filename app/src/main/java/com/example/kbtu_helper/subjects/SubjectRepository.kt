package com.example.kbtu_helper.subjects

import androidx.lifecycle.LiveData

class SubjectRepository(private val subjectDao: SubjectDao) {

    val listData: LiveData<List<Subject>> = subjectDao.listSubjects()

    fun createSubject(subject: Subject){
        subjectDao.createSubject(subject)
    }

    fun getAllSubjects(): List<Subject> {
        return subjectDao.getAllSubjects()
    }

    fun getAllSubjectsByTag(tag: String): List<Subject> {
        return subjectDao.getAllSubjectsByTag(tag)
    }
}