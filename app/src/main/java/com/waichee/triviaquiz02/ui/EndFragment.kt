package com.waichee.triviaquiz02.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.waichee.triviaquiz02.R
import com.waichee.triviaquiz02.databinding.FragmentEndBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EndFragment: Fragment() {
    private lateinit var binding: FragmentEndBinding
    private val viewModel: EndViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEndBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("score")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.navigateToTitleFragment.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    R.id.action_endFragment_to_titleFragment
                )
                viewModel.navigateComplete()
            }
        })
    }
}