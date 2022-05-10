package com.example.kbtu_helper.chats

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Chat::class], version = 1, exportSchema = false)
abstract class ChatDB: RoomDatabase() {

    abstract fun chatDao(): ChatDao

    companion object {
        @Volatile
        private var INSTANCE: ChatDB? = null

        fun getDatabase(context: Context): ChatDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
           synchronized(this){
               val instance = Room.databaseBuilder(
                   context.applicationContext,
                   ChatDB::class.java,
                   "chat_database"
               )
                   .allowMainThreadQueries()
                   .build()
               INSTANCE = instance
               return  instance
           }
        }
    }
}