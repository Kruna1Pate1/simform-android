package com.krunal.demo.appcomponents.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.krunal.demo.R
import com.krunal.demo.appcomponents.ui.viewmodels.FirstFragmentViewModel
import com.krunal.demo.appcomponents.ui.viewmodels.FragmentDemoViewModel
import com.krunal.demo.appcomponents.utils.IntentData
import com.krunal.demo.appcomponents.utils.LifecycleLogger
import com.krunal.demo.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel: FirstFragmentViewModel by viewModels()
    private val activityViewModel: FragmentDemoViewModel by activityViewModels()

    init {
        LifecycleLogger(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.activityViewModel = activityViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        setupInitialValue()

        val data = Bundle().apply {
            putString(IntentData.MESSAGE, viewModel.message.value)
        }

        binding.btnSecondFragment.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragmentContainer, SecondFragment().apply {
                    arguments = data
                })
            }
        }

        binding.btnSecondActivity.setOnClickListener {
            activity?.finish()
        }
    }

    private fun setupInitialValue() {
        arguments?.getString(IntentData.MESSAGE)?.let(viewModel::setMessage)
    }
}