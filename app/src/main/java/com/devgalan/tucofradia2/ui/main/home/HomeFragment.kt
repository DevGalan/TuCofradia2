package com.devgalan.tucofradia2.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initListeners()

        initUI()

        return binding.root
    }

    private fun initListeners() {
        binding.btnPlay.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_serverListFragment)
        })
    }

    private fun initUI() {
    }
}