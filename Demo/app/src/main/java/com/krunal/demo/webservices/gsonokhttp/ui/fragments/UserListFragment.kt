package com.krunal.demo.webservices.gsonokhttp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import com.krunal.demo.R
import com.krunal.demo.appcomponents.utils.IntentData
import com.krunal.demo.databinding.FragmentUserListBinding
import com.krunal.demo.webservices.gsonokhttp.ui.adapters.UserAdapter
import com.krunal.demo.webservices.gsonokhttp.ui.viewmodels.UserViewModel
import kotlinx.coroutines.launch

class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val adapter = UserAdapter { userDetail ->
            parentFragmentManager.commitNow(true) {
                add(
                    R.id.hostFragmentContainer,
                    UserRegisterFragment::class.java,
                    bundleOf(IntentData.USER_ID to userDetail.userId)
                )
            }
        }

        binding.rvUsers.apply {
            this.adapter = adapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(), DividerItemDecoration.VERTICAL
                )
            )
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.users.collect { list ->
                    adapter.submitList(list)
                }
            }
        }
    }
}