package com.example.api

import com.example.entity.Repository
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubAPI {
    @GET("search/repositories")
    suspend fun repositories(@Query("q") q: String): List<Repository>
}