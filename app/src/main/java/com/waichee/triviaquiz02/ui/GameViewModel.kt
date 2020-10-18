package com.waichee.triviaquiz02.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.waichee.triviaquiz02.data.entities.ApiResponse
import com.waichee.triviaquiz02.data.entities.Question
import com.waichee.triviaquiz02.data.repository.QuizRepository
import com.waichee.triviaquiz02.utils.Resource
import kotlinx.coroutines.selects.select
import timber.log.Timber

class GameViewModel @ViewModelInject constructor(private val repository: QuizRepository) : ViewModel() {

    // Variables
    private var numberOfQuestions: Int = 0
    private var questionId = 0
    private var correctAnswerId = 0
    private lateinit var tempAnswerList: MutableList<String>
    lateinit var apiResponse: LiveData<Resource<ApiResponse>>

    var score: Int = 0

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

    private val _questionNumber = MutableLiveData<String>()
    val questionNumber: LiveData<String>
        get() = _questionNumber

    // Functions
    fun start(amount: Int) {
        numberOfQuestions = amount
        _questionNumber.value = "1"
        apiResponse = repository.getQuestions(numberOfQuestions)
    }


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

        if (input == correctAnswerId) {
            Timber.i("Answer correct")
            score += 1
        } else {
            Timber.i("Answer wrong")
        }
    }

    fun nextButtonOnClick(selectedAnswerID: Int) {

        checkAnswer(selectedAnswerID)

        if (questionId < numberOfQuestions - 1) {
            questionId += 1
            _questionNumber.value = (questionId + 1).toString()
            getQuestion(questionId)
        } else {
            _navigateToEndFragment.value = true
        }
    }

    fun navigationComplete() {
        _navigateToEndFragment.value = false
    }

}