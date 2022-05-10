package com.example.kbtu_helper.stats

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TagStat::class], version = 1, exportSchema = false)
abstract class TagStatDB: RoomDatabase() {

    abstract fun tagStatDao(): TagStatDao

    companion object {
        @Volatile
        private var INSTANCE: TagStatDB? = null

        fun getDatabase(context: Context): TagStatDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
           synchronized(this){
               val instance = Room.databaseBuilder(
                   context.applicationContext,
                   TagStatDB::class.java,
                   "tag_stat_database"
               )
                   .allowMainThreadQueries()
                   .build()
               INSTANCE = instance
               return  instance
           }
        }
    }
}