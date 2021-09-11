package com.sohainfotech.daggerhiltdemo.data.api

import com.sohainfotech.daggerhiltdemo.data.model.User
import retrofit2.Response

//Step 05.

interface ApiHelper {

    suspend fun getUsers(): Response<List<User>>
}