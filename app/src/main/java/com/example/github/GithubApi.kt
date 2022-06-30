package com.example.github

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface GithubApi {
    @GET("users/{user}/followers")
    fun getFollowers(@Path("user") url: String) : Call<List<Followers>>

    @GET("users/{user}")
    fun getUserInfo(@Path("user") url: String) : Call<UserInfo>

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