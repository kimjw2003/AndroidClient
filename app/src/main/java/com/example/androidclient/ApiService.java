package com.example.androidclient;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("user_login")
    Call<User> getretrofitquery(@Body User user);
}