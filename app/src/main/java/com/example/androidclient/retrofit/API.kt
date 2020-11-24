package com.example.androidclient.retrofit

import com.example.androidclient.data.TeamInfo
import com.example.androidclient.data.request.*
import com.example.androidclient.data.response.GetTeacher
import com.example.androidclient.data.response.RoomResponse
import com.example.androidclient.data.response.Status
import com.example.androidclient.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface API {
    @POST("room_list")
    fun getRoomList(@Body school: School) : Call<List<RoomResponse>>

    @POST("room_info")
    fun getRoom(
        @Body name : Name
    ) : Call<RoomResponse>

    @POST("user_login")
    fun login(
        @Body userRequest: UserRequest
    ) : Call<Status>

    @POST("user_info")
    fun getUser(
        @Body user:User
    ) : Call<UserResponse>

    @POST("teacher_login")
    fun teacherLogin(
        @Body userRequest: UserRequest
    ) : Call<Status>

    @POST("teacher_info")
    fun getTeacherInfo(
        @Body name : Name
    ) : Call<GetTeacher>

    @POST("team_add")
    fun addTeam(
        @Body teamInfo: TeamInfo
    ) : Call<Status>

    @POST("team_info")
    fun getTeamInfo(
        @Body name: Name
    ) : Call<TeamInfo>

    @POST("user_team")
    fun getUserTeam(
        @Body user: User
    ) : Call<List<String>>

    @POST("booked_list")
    fun getMyRoom(
        @Body user: User
    ) : Call<List<String>>

    @POST("room_book")
    fun bookRoom(
        @Body bookRoom: BookRoom
    ) : Call<Status>
}