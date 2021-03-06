package com.github.data

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

        //@GET("users/{user}")
        //suspend fun getUserInfo(@Path("user") url: String) : Response<UserInfo>

        @GET("users/{user}/repos")
        suspend fun getUserRepos(@Path("user") url: String) : Response<Array<Repositories>>

        @GET("repositories")
        suspend fun getRepos(@Query("since") since: String, @Query("per_page") perPage: String) : Response<Array<Repositories>>

        companion object {
                var BASE_URL = "https://api.github.com/"

                fun build() : Retrofit {
                        return Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl(BASE_URL)
                            .build()
                    }
            }

    }

