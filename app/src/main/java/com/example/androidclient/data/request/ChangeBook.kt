package com.example.androidclient.data.request

data class ChangeBook(
    val team : String,
    val room : String,
    val new_date : Int,
    val old_date : Int
)