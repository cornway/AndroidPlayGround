package com.github.app.ui.common

import androidx.fragment.app.Fragment
import com.github.domain.viewmodel.LiveDataObserveProtocol

open class BaseFragment(private val layout: Int): Fragment(layout), LiveDataObserveProtocol {

}