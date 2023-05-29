package com.krunal.demo.appcomponents.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.krunal.demo.R
import com.krunal.demo.appcomponents.ui.viewmodels.FirstFragmentViewModel
import com.krunal.demo.appcomponents.ui.viewmodels.FragmentDemoViewModel
import com.krunal.demo.appcomponents.ui.viewmodels.SecondFragmentViewModel
import com.krunal.demo.appcomponents.utils.IntentData
import com.krunal.demo.databinding.FragmentFirstBinding
import com.krunal.demo.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {


    private lateinit var binding: FragmentSecondBinding
    private val viewModel: SecondFragmentViewModel by viewModels()
    private val activityViewModel: FragmentDemoViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.activityViewModel = activityViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        setupInitialValue()
    }

    private fun setupInitialValue() {
        arguments?.getString(IntentData.MESSAGE)?.let(viewModel::setMessage)
    }
}