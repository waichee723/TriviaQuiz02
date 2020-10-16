package com.waichee.triviaquiz02.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.waichee.triviaquiz02.data.entities.Question
import com.waichee.triviaquiz02.data.repository.QuizRepository
import kotlinx.coroutines.selects.select
import timber.log.Timber

class GameViewModel @ViewModelInject constructor(private val repository: QuizRepository) : ViewModel() {

    // Variables
    private val numberOfQuestions = 10
    private var questionId = 0
    private var correctAnswerId = 0
    private lateinit var tempAnswerList: MutableList<String>

    val apiResponse = repository.getQuestions(numberOfQuestions)

    // LiveData
    private val _currentQuestion = MutableLiveData<Question>()
    val currentQuestion: LiveData<Question>
        get() = _currentQuestion

    private val _currentAnswers = MutableLiveData<MutableList<String>>()
    val currentAnswers: LiveData<MutableList<String>>
        get() = _currentAnswers

    private val _navigateToEndFragment = MutableLiveData<Boolean>()
    val navigateToEndFragment: LiveData<Boolean>
        get() = _navigateToEndFragment

    // Functions
    fun getQuestion(id: Int) {
        _currentQuestion.value = apiResponse.value?.data?.results?.get(id)
        setCorrectAnswerId()
        updateAnswers()
    }

    private fun updateAnswers(){
        if (currentQuestion.value != null) {
            tempAnswerList = currentQuestion.value!!.incorrect_answers!!.toMutableList()
            currentQuestion.value!!.correct_answer?.let { tempAnswerList.add(correctAnswerId, it + " Correct") }

            _currentAnswers.value = tempAnswerList
        }
    }

    private fun setCorrectAnswerId() {
        correctAnswerId = (0..3).random()
    }

    private fun checkAnswer(input: Int) {
        Timber.i("$input, $correctAnswerId")

        if (input == correctAnswerId) {
            Timber.i("Answer correct")
        } else {
            Timber.i("Answer wrong")
        }
    }

    fun nextButtonOnClick(selectedAnswerID: Int) {

        checkAnswer(selectedAnswerID)

        if (questionId < numberOfQuestions - 1) {
            questionId += 1
            getQuestion(questionId)
        } else {
            _navigateToEndFragment.value = true
        }
    }

    fun navigationComplete() {
        _navigateToEndFragment.value = false
    }

}