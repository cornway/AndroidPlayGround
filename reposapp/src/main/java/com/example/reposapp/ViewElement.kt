package com.example.reposapp

import android.graphics.Bitmap

data class ViewElement(
    var name: String,
    var url: String,
    val avatar: Bitmap?
) {
}