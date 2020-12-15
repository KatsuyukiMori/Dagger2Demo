package com.example.dagger2demo.data.model

import com.example.dagger2demo.data.model.Owner
import com.squareup.moshi.Json

data class Repo (
    val owner: Owner,
    @Json(name = "name")
    val repoName: String,
    @Json(name = "html_url")
    val url: String,
    @Json(name = "stargazers_count")
    val star: String
)