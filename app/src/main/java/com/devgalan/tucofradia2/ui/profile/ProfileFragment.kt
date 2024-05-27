package com.devgalan.tucofradia2.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.devgalan.tucofradia2.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        findNavController().navigate(R.id.action_profileFragment_to_signinFragment)
        val profileViewModel = ProfileViewModel()
        profileViewModel.onCreate()
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }
}