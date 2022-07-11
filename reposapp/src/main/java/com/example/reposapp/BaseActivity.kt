package com.example.reposapp

import androidx.appcompat.app.AppCompatActivity
import github.domain.viewmodel.BaseViewModel

abstract class BaseActivity<VM : BaseViewModel>: LiveDataObserveProtocol, AppCompatActivity()