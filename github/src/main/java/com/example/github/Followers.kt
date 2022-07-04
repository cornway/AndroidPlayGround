package com.example.github

import com.google.gson.annotations.SerializedName

data class Followers(@SerializedName("login") var login: String,
                     @SerializedName("avatar_url") var image: String) {
}