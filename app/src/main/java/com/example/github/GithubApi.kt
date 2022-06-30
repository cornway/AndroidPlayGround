package com.example.github

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users/cornway/followers")
    fun getFollowers() : Call<List<Followers>>

    companion object {
        var BASE_URL = "https://api.github.com/"

        fun create() : GithubApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(GithubApi::class.java)
        }
    }

}