package com.sohainfotech.daggerhiltdemo.data.repository

import com.sohainfotech.daggerhiltdemo.data.api.ApiHelper
import javax.inject.Inject

//Step 07.
//In MainRepository we will pass ApiHelper in the constructor of the repository.

/**
 *Now, if you can see we have passed ApiHelper and ApiService in MainRepository and ApiHelperImpl respectively.
 * So, to inject everything in the constructor we also need to provide it using @Provide annotation in Dagger.
 */

class MainRepository @Inject constructor(private val apiHelper: ApiHelper){

    suspend fun getUsers() = apiHelper.getUsers()
}