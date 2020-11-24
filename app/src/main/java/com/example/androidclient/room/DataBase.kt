package com.example.androidclient.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserDataBase::class], version = 3)
abstract class DataBase : RoomDatabase() {

    abstract fun dao(): Dao

    companion object {
        private var INSTANCE: DataBase? = null

        fun getInstance(context: Context): DataBase? {
            if (INSTANCE == null) {
                synchronized(UserDataBase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DataBase::class.java, "memo.db"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
