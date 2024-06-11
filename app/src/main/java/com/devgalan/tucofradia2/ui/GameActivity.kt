package com.devgalan.tucofradia2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devgalan.tucofradia2.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}