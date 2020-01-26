package com.example.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Owner(
    val login: String = "",
    @field:Json(name = "avatar_url") val avatarUrl: String?
)

@JsonClass(generateAdapter = true)
data class Repository(
    @field:Json(name = "full_name") val fullName: String = "",
    val owner: Owner?,
    val language: String = "",
    val url: String?
)