package github.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

open class BaseViewModel() : ViewModel(), KoinComponent {
    fun execute (request: suspend () -> Unit) {
        viewModelScope.launch {
            request()
        }
    }
}