package com.example.github

import com.google.gson.annotations.SerializedName

data class UserRepos(
    @SerializedName("name") val name: String,
    @SerializedName("html_url") val url: String
)
