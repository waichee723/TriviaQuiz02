package com.waichee.triviaquiz02.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EndViewModel @ViewModelInject constructor() :ViewModel() {

    private val _navigateToTitleFragment = MutableLiveData<Boolean>()
    val navigateToTitleFragment: LiveData<Boolean>
        get() = _navigateToTitleFragment

    fun returnToTitleButtonOnClick() {
        _navigateToTitleFragment.value = true
    }

    fun navigateComplete() {
        _navigateToTitleFragment.value = false
    }

}