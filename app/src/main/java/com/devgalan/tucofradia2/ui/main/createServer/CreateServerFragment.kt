package com.devgalan.tucofradia2.ui.main.createServer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.data.dto.server.CreateServerDto
import com.devgalan.tucofradia2.data.dto.user.LoginUserDto
import com.devgalan.tucofradia2.databinding.FragmentCreateServerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateServerFragment : Fragment() {

    private lateinit var binding: FragmentCreateServerBinding

    private val createServerViewModel by viewModels<CreateServerViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateServerBinding.inflate(inflater, container, false)

        binding.cvError.visibility = View.GONE

        initObservers()

        initListeners()

        return binding.root
    }

    private fun initObservers() {
        createServerViewModel.onError.observe(viewLifecycleOwner) {
            binding.cvError.visibility = View.VISIBLE
            binding.tvError.text = it
        }
        createServerViewModel.onFinished.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_createServerFragment_to_gameActivity)
            }
        }
    }

    private fun initListeners() {
        binding.btnCreateServer.setOnClickListener {
            binding.cvError.visibility = View.GONE

            val name = binding.etName.text.toString()
            val description = binding.etDescription.text.toString()
            val password = binding.etPassword.text.toString()
            var maxPlayers = 0
            if (binding.etMaxPlayers.text.toString().isEmpty()) maxPlayers = 1
            else maxPlayers = binding.etMaxPlayers.text.toString().toInt()

            if (maxPlayers < 1)
                maxPlayers = 1
            else if (maxPlayers > 9)
                maxPlayers = 9

            val createServerDto = CreateServerDto(name, description, password, maxPlayers, createServerViewModel.getUserId())
            createServerViewModel.createServer(createServerDto)
        }
    }
}