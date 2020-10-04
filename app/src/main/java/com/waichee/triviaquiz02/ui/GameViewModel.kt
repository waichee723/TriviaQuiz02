package com.waichee.triviaquiz02.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.waichee.triviaquiz02.data.entities.Question
import com.waichee.triviaquiz02.data.repository.QuizRepository
import timber.log.Timber

class GameViewModel @ViewModelInject constructor(private val repository: QuizRepository) : ViewModel() {
    private val numberOfQuestions = 10

    val apiResponse = repository.getQuestions(numberOfQuestions)


    init {
        Timber.i("init")
    }

}