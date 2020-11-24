package com.example.androidclient.retrofit

import com.example.androidclient.data.response.RoomResponse
import retrofit2.Call
import retrofit2.http.POST

interface API {
    @POST("room_list")
    fun getRoomList() : Call<List<RoomResponse>>
}