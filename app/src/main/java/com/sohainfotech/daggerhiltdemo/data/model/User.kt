package com.sohainfotech.daggerhiltdemo.data.model

import com.squareup.moshi.Json

//Step 03.
// The User data class looks like

data class User(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "email")
    val email: String = "",
    @Json(name = "avatar")
    val avatar: String = ""
)
