package com.devgalan.tucofradia2.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initListeners()

        return binding.root
    }

    private fun initListeners() {
        binding.btnSearchServers.setOnClickListener {
            if (homeViewModel.isUserLogged()) findNavController().navigate(R.id.action_homeFragment_to_serverListFragment)
            else findNavController().navigate(R.id.action_homeFragment_to_signinFragment)
        }
        binding.btnMyServers.setOnClickListener {
            if (homeViewModel.isUserLogged()) findNavController().navigate(R.id.action_homeFragment_to_myServersFragment)
            else findNavController().navigate(R.id.action_homeFragment_to_signinFragment)
        }
    }

}