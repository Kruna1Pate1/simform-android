package com.krunal.demo.githubclient.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.krunal.demo.BR

abstract class BaseFragment<Binding : ViewDataBinding, ViewModel : androidx.lifecycle.ViewModel> :
    Fragment() {

    protected lateinit var binding: Binding
    protected abstract val viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, viewModel)
        }
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        initializeObservers()
    }

    @LayoutRes
    abstract fun getLayoutResId(): Int

    open fun initializeObservers() {}

    open fun initialize() {}
}