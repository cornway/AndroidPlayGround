package com.github.domain.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

interface LiveDataObserveProtocol {
    fun <T> observe(data: LiveData<T>, observer: ((T) -> Unit)? = null) {
        if (this is Fragment) {
            data.observe(viewLifecycleOwner, Observer { observer?.invoke(it) })
        }
        if (this is AppCompatActivity) {
            data.observe(this, Observer { observer?.invoke(it) })
        }
        if (this is LifecycleService) {
            data.observe(this, Observer { observer?.invoke(it) })
        }
    }
}
