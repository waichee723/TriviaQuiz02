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
    private var questionId = 0

    val apiResponse = repository.getQuestions(numberOfQuestions)

    private val _currentQuestion = MutableLiveData<Question>()
    val currentQuestion: LiveData<Question>
        get() = _currentQuestion

    fun getQuestion(id: Int) {
        _currentQuestion.value = apiResponse.value?.data?.results?.get(id)
    }

    fun nextButtonOnClick() {
        if (questionId < numberOfQuestions - 1) {
            questionId += 1
            getQuestion(questionId)
        } else {
            Timber.i("Game Finish")
        }
    }

}