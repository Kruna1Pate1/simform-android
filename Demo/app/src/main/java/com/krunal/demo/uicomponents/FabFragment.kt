package com.krunal.demo.uicomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentFabBinding

class FabFragment : Fragment(R.layout.fragment_fab) {

    private lateinit var binding: FragmentFabBinding
    private var visible: Boolean = false
    private lateinit var snackBar: Snackbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFabBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabAdd.setOnClickListener {
            changeVisibility()
            if (visible) snackBar.show()
        }
        setupSnackBar()
    }

    private fun changeVisibility() {
        visible = visible.not()
        binding.fabAdd.setImageResource(
            if (visible) R.drawable.ic_cross
            else R.drawable.ic_add
        )
        binding.fabEdit.visibility = if (visible) View.VISIBLE else View.INVISIBLE
        binding.fabImage.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    private fun setupSnackBar() {
        snackBar = Snackbar.make(binding.root, "Fab button opened", Snackbar.LENGTH_SHORT)
        snackBar.anchorView = binding.fabImage
        snackBar.setAction("Close") {
            changeVisibility()
        }
    }
}