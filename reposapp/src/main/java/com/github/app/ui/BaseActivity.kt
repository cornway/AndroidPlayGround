package com.github.app.ui

import androidx.appcompat.app.AppCompatActivity
import com.github.domain.viewmodel.BaseViewModel
import com.github.domain.viewmodel.LiveDataObserveProtocol

abstract class BaseActivity<VM : BaseViewModel>: LiveDataObserveProtocol, AppCompatActivity()