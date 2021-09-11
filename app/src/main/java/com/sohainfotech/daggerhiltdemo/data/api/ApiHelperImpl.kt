package com.sohainfotech.daggerhiltdemo.data.api

import com.sohainfotech.daggerhiltdemo.data.model.User
import retrofit2.Response
import javax.inject.Inject

//Step 06.
//In ApiHelperImpl we would inject ApiService in the constructor using @Inject and implement ApiHelper.

//Here, @Inject is helping in passing the dependency required by ApiHelperImpl in the constructor itself.

class ApiHelperImpl @Inject constructor(private val apiService: ApiService):  ApiHelper{

    //Note: This function is override because of implementing ApiHelper
    override suspend fun getUsers(): Response<List<User>> = apiService.getUsers()

}