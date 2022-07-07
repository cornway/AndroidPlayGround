package github.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class BaseViewModel() : ViewModel() {
    fun execute (request: suspend () -> Unit) {
        viewModelScope.launch {
            request()
        }
    }
}