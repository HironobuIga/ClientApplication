package com.example.repository

import com.example.api.GithubAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class GithubRepository(
    private val okHttp: OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(HttpLoggingInterceptor()
            .apply { setLevel(HttpLoggingInterceptor.Level.BASIC) })
        .build(),
    private val retrofit: Retrofit = Retrofit.Builder()
        .client(okHttp)
        .baseUrl("https://api.github.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
) {
    private val api: GithubAPI = retrofit.create(GithubAPI::class.java)
    suspend fun callRepository(name: String) = api.repositories(name)
}