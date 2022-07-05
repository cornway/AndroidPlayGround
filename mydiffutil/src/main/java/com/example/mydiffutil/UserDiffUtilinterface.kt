package com.example.mydiffutil

interface UserDiffUtilInterface<T> {
    fun theSame(item: T): Boolean = false
    fun contentTheSame(item: T): Boolean = false
}