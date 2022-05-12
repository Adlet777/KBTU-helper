package com.example.kbtu_helper.presenter.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kbtu_helper.model.subjects.Subject

@Database(entities = [Subject::class], version = 1, exportSchema = false)
abstract class SubjectDB : RoomDatabase() {

    abstract fun todoDao(): SubjectDao

    companion object {
        @Volatile
        private var INSTANCE: SubjectDB? = null

        fun getDatabase(context: Context): SubjectDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SubjectDB::class.java,
                    "subject_database"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}