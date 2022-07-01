package com.example.github

import android.service.autofill.UserData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface GithubApi {

    @GET("users/{user}")
    suspend fun getUserInfo(@Path("user") url: String) : Response<UserInfo>

    @GET("users/{user}/repos")
    suspend fun getUserRepos(@Path("user") url: String) : Response<Array<UserRepos>>

    @GET("users/{user}")
    suspend fun getUserFollowers(@Path("user") url: String) : Response<Array<Followers>>

    companion object {
        var BASE_URL = "https://api.github.com/"

        fun create() : Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        }
    }

}