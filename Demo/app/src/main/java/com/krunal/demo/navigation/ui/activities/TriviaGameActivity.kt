package com.krunal.demo.navigation.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.krunal.demo.R
import com.krunal.demo.navigation.ui.viewmodels.TriviaGameViewModel

class TriviaGameActivity : AppCompatActivity() {

    private val viewModel: TriviaGameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia_game)
    }
}