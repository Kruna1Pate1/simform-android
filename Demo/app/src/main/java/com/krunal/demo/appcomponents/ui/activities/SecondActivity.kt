package com.krunal.demo.appcomponents.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.krunal.demo.appcomponents.ui.viewmodels.SecondActivityViewModel
import com.krunal.demo.appcomponents.utils.IntentData
import com.krunal.demo.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private val viewModel: SecondActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        setContentView(binding.root)

        setupUI()
        setupInitialValue(intent)
    }

    private fun setupInitialValue(intent: Intent) {
        intent.getStringExtra(IntentData.MESSAGE)?.let(viewModel::setMessage)
    }

    private fun setupUI() {
        binding.btnGoBackActivity.setOnClickListener {
            finish()
        }

        binding.btnFragment.setOnClickListener {
            val intent = Intent(IntentData.ACTION_MESSAGE).apply {
                putExtra(IntentData.MESSAGE, viewModel.message.value)
                type = "text/plain"
            }
            startActivity(intent)
        }
    }

    override fun finish() {
        val intent = Intent().apply {
            putExtra(IntentData.MESSAGE, viewModel.message.value)
        }
        setResult(Activity.RESULT_OK, intent)
        super.finish()
    }
}