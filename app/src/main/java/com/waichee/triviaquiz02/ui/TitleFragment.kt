package com.waichee.triviaquiz02.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.waichee.triviaquiz02.R
import com.waichee.triviaquiz02.databinding.FragmentTitleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TitleFragment: Fragment() {

    private lateinit var binding: FragmentTitleBinding
    private val viewModel: TitleViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentTitleBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setupSpinner()
        setupObserver()

        return binding.root
    }

    private fun setupSpinner() {
        // Number of Questions
        val numberOfQuestionsOptions = arrayOf("3", "5", "10")
        val numberOfQuestionsAdapter = ArrayAdapter<String>(requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            numberOfQuestionsOptions)
        binding.spinnerNumberOfQuestions.adapter = numberOfQuestionsAdapter
    }

    private fun setupObserver() {
        viewModel.navigateToGame.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                val spinnerItem = binding.spinnerNumberOfQuestions.selectedItem.toString()
                Toast.makeText(requireContext(), spinnerItem, Toast.LENGTH_SHORT).show()

                this.findNavController().navigate(
                    R.id.action_titleFragment_to_gameFragment
                )

                viewModel.navigationComplete()
            }
        })
    }
}