package com.krunal.demo.uicomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.krunal.demo.databinding.FragmentProgressBarBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProgressBarFragment : Fragment() {

    private lateinit var binding: FragmentProgressBarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProgressBarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupProgress()
    }

    private fun setupProgress() {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(10) {
                delay(1000)
                binding.pbCircularDeterminate.incrementProgressBy(5)
                binding.pbCircularIndeterminate1.incrementProgressBy(5)
                binding.pbCircularIndeterminate2.incrementProgressBy(5)
                binding.pbHorizontalDeterminate.incrementProgressBy(-5)
            }
        }
    }
}