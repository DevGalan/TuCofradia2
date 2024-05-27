package com.devgalan.tucofradia2.ui.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.databinding.FragmentLoginBinding
import com.devgalan.tucofradia2.databinding.FragmentSigninBinding
import com.devgalan.tucofradia2.ui.auth.signin.SigninFragment

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        initListeners()

        return binding.root
    }

    private fun initListeners() {
        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signinFragment)
        }
    }
}