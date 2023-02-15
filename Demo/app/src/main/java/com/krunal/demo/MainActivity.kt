package com.krunal.demo

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.krunal.demo.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                val time = currTime
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


    private val currTime: String
        get() {
            val sdf = SimpleDateFormat("HH:mm:ss")
            return sdf.format(Date())
        }
}