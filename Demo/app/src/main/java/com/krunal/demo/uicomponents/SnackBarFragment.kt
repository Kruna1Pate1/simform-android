package com.krunal.demo.uicomponents

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.behavior.SwipeDismissBehavior
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_SLIDE
import com.google.android.material.snackbar.BaseTransientBottomBar.Behavior
import com.google.android.material.snackbar.Snackbar
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentSnackbarBinding

class SnackBarFragment : Fragment(R.layout.fragment_snackbar) {

    private lateinit var binding: FragmentSnackbarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSnackbarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSnackBars()
    }

    private fun setupSnackBars() {
        binding.btnShortSnackBar.setOnClickListener {
            Snackbar.make(requireView(), (it as Button).text, Snackbar.LENGTH_SHORT).show()
        }

        binding.btnLongSnackBar.setOnClickListener {
            Snackbar.make(requireView(), (it as Button).text, Snackbar.LENGTH_LONG).show()
        }

        binding.btnMultiLineSnackBar.setOnClickListener {
            Snackbar.make(requireView(), (it as Button).text, Snackbar.LENGTH_SHORT).show()
        }

        binding.btnActionSnackBar.setOnClickListener {
            val snackBar = Snackbar.make(requireView(), (it as Button).text, Snackbar.LENGTH_LONG)
                .setBackgroundTint(Color.GRAY).setActionTextColor(Color.GREEN)
            snackBar.setAction("Red") {
                snackBar.setBackgroundTint(Color.RED)
            }
            snackBar.show()
        }

        binding.btnAnchorSnackBar.setOnClickListener {
            Snackbar.make(requireView(), (it as Button).text, Snackbar.LENGTH_LONG)
                .setAnchorView(binding.btnShortSnackBar).show()
        }

        binding.btnSwipeableSnackBar.setOnClickListener {
            Snackbar.make(requireView(), (it as Button).text, Snackbar.LENGTH_LONG).apply {
                behavior = Behavior()
                animationMode = ANIMATION_MODE_SLIDE
                behavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY)
                show()
            }
        }
    }
}