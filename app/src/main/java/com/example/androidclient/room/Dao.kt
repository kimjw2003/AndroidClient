package com.example.androidclient.room

import androidx.room.Insert
import androidx.room.Query

interface Dao {
    @Query("SELECT * FROM user")
    fun getAll(): List<UserDataBase>

    @Insert
    fun insert(loginData: UserDataBase)

    @Query("DELETE FROM user")
    fun delete()
}