package com.krunal.demo

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.krunal.demo.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mPlayer: MediaPlayer
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.timeFlow.collectLatest { time ->
                runOnUiThread {
                    binding.lblTime.text = getString(R.string.current_time, time)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        play()
    }

    override fun onPause() {
        super.onPause()
        stop()
    }

    private fun play() {
        mPlayer = MediaPlayer.create(this, R.raw.clock)
        mPlayer.isLooping = true
        mPlayer.start()
    }

    private fun stop() {
        mPlayer.stop()
        mPlayer.release()
    }
}