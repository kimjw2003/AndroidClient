package com.example.androidclient.data.response

data class RoomResponse(
    val charge : String,
    val name : String,
    val acceptable : Int,
    val state : String,
    val beem : Int,
    val board : Int
)