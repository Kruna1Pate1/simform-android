package com.krunal.demo.appcomponents.ui.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commitNow
import com.krunal.demo.appcomponents.ui.fragments.FirstFragment
import com.krunal.demo.appcomponents.ui.viewmodels.FirstActivityViewModel
import com.krunal.demo.appcomponents.utils.IntentData
import com.krunal.demo.appcomponents.utils.LifecycleLogger
import com.krunal.demo.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding
    private val viewModel: FirstActivityViewModel by viewModels()
    private val activityResultLauncher: ActivityResultLauncher<Unit> = registerActivityForResult()

    init {
        LifecycleLogger(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        binding.btnSecondActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra(IntentData.MESSAGE, viewModel.message.value)
            }
            startActivityForResult(intent, MESSAGE_REQUEST_CODE)
        }

        binding.btnFragment.setOnClickListener {
            activityResultLauncher.launch(Unit)
        }
    }

    private fun registerActivityForResult(): ActivityResultLauncher<Unit> {
        return registerForActivityResult(object : ActivityResultContract<Unit, String?>() {
            override fun createIntent(context: Context, input: Unit): Intent = Intent(this@FirstActivity, FragmentDemoActivity::class.java).apply {
                putExtra(IntentData.MESSAGE, viewModel.message.value)
            }

            override fun parseResult(resultCode: Int, intent: Intent?): String? {
                if (resultCode == Activity.RESULT_OK) {
                    return intent?.getStringExtra(IntentData.MESSAGE)
                }
                return null
            }

        }) {
            it?.let(viewModel::setMessage)
            Log.d("TAG", "registerActivityForResult: ${viewModel.message.value}")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == MESSAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(IntentData.MESSAGE)?.let(viewModel::setMessage)
        }
    }

    companion object {
        private const val MESSAGE_REQUEST_CODE = 101
    }
}