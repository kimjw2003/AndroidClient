package com.example.androidclient.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserDataBase(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "user") var user: String,
    @ColumnInfo(name = "school") var school: String,
    @ColumnInfo(name = "grade") var grade: Int,
    @ColumnInfo(name = "class") var classs: Int,
    @ColumnInfo(name = "name") var name: String
)