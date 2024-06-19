package com.devgalan.tucofradia2.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.devgalan.tucofradia2.databinding.ActivityGameBinding
import com.devgalan.tucofradia2.ui.game.guilds.GuildsListAdapter
import com.devgalan.tucofradia2.ui.game.guilds.GuildsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    private val guildsListViewModel by viewModels<GuildsListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)

        initUI()

        setContentView(binding.root)
    }

    private fun initUI() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val guildsListAdapter = GuildsListAdapter(guildsListViewModel.getGuilds())
        binding.gameRecyclerView.adapter = guildsListAdapter
        binding.gameRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}