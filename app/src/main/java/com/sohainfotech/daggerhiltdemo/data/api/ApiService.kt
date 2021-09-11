package com.sohainfotech.daggerhiltdemo.data.api

import com.sohainfotech.daggerhiltdemo.data.model.User
import retrofit2.Response
import retrofit2.http.GET

//Step 04.

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}