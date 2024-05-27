package com.devgalan.tucofradia2.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val profileViewModel by viewModels<ProfileViewModel>()

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        checkIfUserIsLogged()

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        initUI()

        initListeners()

        return binding.root
    }

    private fun checkIfUserIsLogged() {
        if (profileViewModel.getUser() == null)
        {
            findNavController().navigate(R.id.action_profileFragment_to_signinFragment)
        }
    }

    private fun initListeners() {
        binding.btnLogout.setOnClickListener {
            profileViewModel.logout()
            findNavController().navigate(R.id.action_profileFragment_to_signinFragment)
        }
    }

    private fun initUI() {
        binding.tvUsername.text = profileViewModel.getUser()?.username
        binding.tvEmail.text = profileViewModel.getUser()?.email
    }
}