package com.example.github

import com.example.example.Repositories
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("users/{user}")
    suspend fun getUserInfo(@Path("user") url: String) : Response<UserInfo>

    @GET("users/{user}/repos")
    suspend fun getUserRepos(@Path("user") url: String) : Response<Array<UserRepos>>

    @GET("repositories")
    suspend fun getRepos(@Query("since") since: String) : Response<Array<Repositories>>

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