package com.example.dagger2demo.data.model

import com.squareup.moshi.Json

data class Owner (
    val id: String,
    @Json(name = "login")
    val name: String,
    @Json(name = "avatar_url")
    val icon: String
)