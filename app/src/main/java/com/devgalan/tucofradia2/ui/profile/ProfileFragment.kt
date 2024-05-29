package com.devgalan.tucofradia2.ui.profile

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.databinding.FragmentProfileBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

        suscribeObservers()

        initListeners()

        initUI()

        return binding.root
    }

    private fun checkIfUserIsLogged() {
        if (!profileViewModel.userIsLogged()) {
            findNavController().navigate(R.id.action_profileFragment_to_signinFragment)
        }
    }

    private fun suscribeObservers() {
        profileViewModel.onFinished.observe(viewLifecycleOwner) {
            if (it) {
                initUI()
            }
        }
    }

    private fun initListeners() {
        binding.btnLogout.setOnClickListener {
            profileViewModel.logout()
            findNavController().navigate(R.id.action_profileFragment_to_signinFragment)
        }

        binding.btnEditProfile.setOnClickListener() {
            showDialog()
        }
    }

    private fun showDialog() {
        val dialog = Dialog(binding.ivProfile.context)
        dialog.setContentView(R.layout.dialog_edit_profile)

        val btnSave = dialog.findViewById<Button>(R.id.btnSave)
        val etName = dialog.findViewById<EditText>(R.id.etName)
        val etMessage = dialog.findViewById<EditText>(R.id.etMessage)
        val fabCancel = dialog.findViewById<FloatingActionButton>(R.id.fabCancel)

        btnSave.setOnClickListener {
            profileViewModel.updateProfile(etName.toString(), etMessage.toString())
            dialog.dismiss()
        }

        fabCancel.setOnClickListener {
            dialog.dismiss()
        }

        etName.hint = profileViewModel.getUser()?.username ?: "Nombre de usuario"
        etMessage.hint = profileViewModel.getUser()?.profileMessage ?: "Mensaje de perfil"

        dialog.show()
    }

    private fun initUI() {
        binding.tvUsername.text = profileViewModel.getUser()?.username
        binding.tvEmail.text = profileViewModel.getUser()?.email
        binding.tvProfileMessage.text = profileViewModel.getUser()?.profileMessage
    }
}