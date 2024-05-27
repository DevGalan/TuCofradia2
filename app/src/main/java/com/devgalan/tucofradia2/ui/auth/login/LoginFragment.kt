package com.devgalan.tucofradia2.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.data.dto.LoginUserDto
import com.devgalan.tucofradia2.data.dto.RegisterUserDto
import com.devgalan.tucofradia2.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val loginViewModel by viewModels<LoginViewModel>()

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.cvError.visibility = View.GONE

        suscribeObservers()

        initListeners()

        return binding.root
    }

    private fun suscribeObservers() {
        loginViewModel.onError.observe(viewLifecycleOwner) {
            binding.cvError.visibility = View.VISIBLE
            binding.tvError.text = it
        }
        loginViewModel.onFinished.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
            }
        }
    }

    private fun initListeners() {
        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signinFragment)
        }

        binding.btnLogin.setOnClickListener {
            binding.cvError.visibility = View.GONE

            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            val registerUserDto = LoginUserDto(email, password)
            loginViewModel.loginUser(registerUserDto)
        }
    }
}