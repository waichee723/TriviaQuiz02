package com.waichee.triviaquiz02.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.waichee.triviaquiz02.R
import com.waichee.triviaquiz02.databinding.FragmentGameBinding
import com.waichee.triviaquiz02.utils.Resource.Status.LOADING
import com.waichee.triviaquiz02.utils.Resource.Status.SUCCESS
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class GameFragment: Fragment() {
    private lateinit var binding: FragmentGameBinding
    private val viewModel: GameViewModel by viewModels()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentGameBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        Timber.i("init")

        setupObserver()

        return binding.root
    }

    private fun setupObserver() {
        viewModel.apiResponse.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                SUCCESS -> {
                    viewModel.getQuestion(0)
                    binding.statusText.text = "SUCCESS"
                }
                LOADING -> binding.statusText.text = "LOADING QUESTIONS"
                else -> binding.statusText.text = "ERROR"
            }
        })

        viewModel.currentQuestion.observe(viewLifecycleOwner, Observer {
            binding.questionText.text = it.question
        })

        viewModel.navigateToEndFragment.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    R.id.action_gameFragment_to_endFragment
                )
                viewModel.navigationComplete()
            }
        })
    }

}