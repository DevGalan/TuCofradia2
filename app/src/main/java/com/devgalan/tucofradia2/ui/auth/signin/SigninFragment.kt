package com.devgalan.tucofradia2.ui.auth.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.data.dto.RegisterUserDto
import com.devgalan.tucofradia2.databinding.FragmentSigninBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SigninFragment : Fragment() {

    private val signinViewModel by viewModels<SigninViewModel>()

    private lateinit var binding: FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSigninBinding.inflate(inflater, container, false)

        binding.cvError.visibility = View.GONE

        suscribeObservers()

        initListeners()

        return binding.root
    }

    private fun suscribeObservers() {
        signinViewModel.onError.observe(viewLifecycleOwner) {
            binding.cvError.visibility = View.VISIBLE
            binding.tvError.text = it
        }
        signinViewModel.onFinished.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_signinFragment_to_profileFragment)
            }
        }
    }

    private fun initListeners() {
        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signinFragment_to_loginFragment)
        }

        binding.btnRegister.setOnClickListener {
            binding.cvError.visibility = View.GONE

            val name = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etRepeatPassword.text.toString()

            val registerUserDto = RegisterUserDto(name, email, password)
            signinViewModel.registerUser(registerUserDto, confirmPassword)
        }
    }
}