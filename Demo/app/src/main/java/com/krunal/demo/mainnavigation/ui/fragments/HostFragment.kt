package com.krunal.demo.mainnavigation.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentHostBinding
import com.krunal.demo.mainnavigation.data.models.ComponentDetail
import com.krunal.demo.mainnavigation.ui.adapters.ComponentDetailAdapter
import com.krunal.demo.mainnavigation.ui.viewmodels.MainActivityViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HostFragment : Fragment() {

    private lateinit var binding: FragmentHostBinding
    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHostBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val adapter = ComponentDetailAdapter { component ->
            when (component) {
                is ComponentDetail.ActivityComponent -> {
                    startActivity(
                        Intent(context, component.clazz)
                    )
                }

                is ComponentDetail.FragmentComponent -> {
                    parentFragmentManager.commit {
                        replace(R.id.hostFragmentContainer, component.clazz, null)
                    }
                }

                is ComponentDetail.NavigatorComponent -> {
                    viewModel.changeComponentDetails(component.components)
                }
            }
        }

        binding.rvMainNav.apply {
            this.adapter = adapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(), DividerItemDecoration.VERTICAL
                )
            )
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.componentDetails.collectLatest { list ->
                    adapter.submitList(list)
                }
            }
        }
    }
}