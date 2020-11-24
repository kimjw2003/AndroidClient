package com.example.androidclient.data.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val user : String,
    val school : String,
    val grade : Int,
    @SerializedName("class")
    val numClass : Int,
    val team : List<String>,
    val name : String
)