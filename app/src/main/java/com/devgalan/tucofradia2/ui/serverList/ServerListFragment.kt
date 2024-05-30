package com.devgalan.tucofradia2.ui.serverList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.databinding.FragmentServerListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServerListFragment : Fragment() {

    private val serverListViewModel by viewModels<ServerListViewModel>()

    private lateinit var binding: FragmentServerListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentServerListBinding.inflate(inflater, container, false)

        initUI()

        return binding.root
    }

    private fun initUI() {
        TODO("Not yet implemented")
    }
}