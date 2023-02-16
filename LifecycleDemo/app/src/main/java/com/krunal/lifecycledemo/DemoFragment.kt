package com.krunal.lifecycledemo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class DemoFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        show("onAttach", TAG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        show("onCreate", TAG)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        show("onCreateView", TAG)
        return inflater.inflate(R.layout.fragment_demo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        show("onViewCreated", TAG)
    }

    override fun onStart() {
        super.onStart()
        show("onStart", TAG)
    }

    override fun onResume() {
        super.onResume()
        show("onResume", TAG)
    }

    override fun onPause() {
        super.onPause()
        show("onPause", TAG)
    }

    override fun onStop() {
        super.onStop()
        show("onStop", TAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        show("onDestroy", TAG)
    }

    override fun onDestroy() {
        super.onDestroy()
        show("onDestroy", TAG)
    }

    override fun onDetach() {
        super.onDetach()
        show("onDetach", TAG)
    }

    companion object {
        private const val TAG = "DemoFragment"
    }
}