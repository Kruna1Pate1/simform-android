package com.krunal.demo.uicomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.behavior.SwipeDismissBehavior
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.krunal.demo.databinding.FragmentCoordinatorLayoutBinding

class CoordinatorLayoutFragment : Fragment() {

    private lateinit var binding: FragmentCoordinatorLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoordinatorLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        setupSnackBar()
    }

    private fun setupSnackBar() {
        binding.btnSwipeableSnackBar.setOnClickListener {
            Snackbar.make(requireView(), (it as Button).text, Snackbar.LENGTH_LONG).apply {
                behavior = BaseTransientBottomBar.Behavior()
                animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
                behavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY)
                show()
            }
        }
    }

}