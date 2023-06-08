package com.krunal.demo.appcomponents.ui.activities

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.krunal.demo.R
import com.krunal.demo.appcomponents.ui.fragments.FirstFragment
import com.krunal.demo.appcomponents.ui.viewmodels.FragmentDemoViewModel
import com.krunal.demo.appcomponents.utils.IntentData
import com.krunal.demo.databinding.ActivityFragmentDemoBinding

class FragmentDemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentDemoBinding
    private val viewModel: FragmentDemoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFragmentDemoBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)
        setupInitialValue(intent)
        setupUI()
    }

    private fun setupInitialValue(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let(viewModel::setMessage)
        intent.getStringExtra(IntentData.MESSAGE)?.let(viewModel::setMessage)
    }

    private fun setupUI() {
        val data = Bundle().apply {
            putString(IntentData.MESSAGE, viewModel.message.value)
        }

        supportFragmentManager.commit {
            add(R.id.fragmentContainer, FirstFragment().apply {
                arguments = data
            })
        }
    }

    override fun finish() {
        val message = viewModel.message.value
        val intent = Intent().apply {
            putExtra(IntentData.MESSAGE, message)
        }
        setResult(Activity.RESULT_OK, intent)
        super.finish()
    }
}