package com.waichee.triviaquiz02.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TitleViewModel @ViewModelInject constructor() : ViewModel() {
    private val _navigateToGame = MutableLiveData<Boolean?>()
    val navigateToGame: LiveData<Boolean?>
        get() = _navigateToGame

    fun startButtonClicked() {
        _navigateToGame.value = true
    }

    fun navigationComplete() {
        _navigateToGame.value = false
    }
}